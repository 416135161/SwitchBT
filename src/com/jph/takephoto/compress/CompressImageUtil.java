package com.jph.takephoto.compress;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;

import com.jph.takephoto.uitl.FileUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * 压缩照片2.0
 * @author JPH
 * Date 2015-08-26 下午1:44:26
 */
public class CompressImageUtil implements CompressImage{
	private CompressConfig config;
	Handler mhHandler = new Handler();
	private Context context;
	public CompressImageUtil(Context context,CompressConfig config) {
		this.config=config==null?CompressConfig.getDefaultConfig():config;
		this.context = context;
	}
	@Override
	public void compress(String imagePath, CompressListener listener) {
		if (config.isEnablePixelCompress()){
			try {
				compressImageByPixel(imagePath,listener);
			} catch (FileNotFoundException e) {
				listener.onCompressFailed(imagePath,String.format("图片压缩失败,%s",e.toString()));
				e.printStackTrace();
			}
		}else {
			compressImageByQuality(BitmapFactory.decodeFile(imagePath),imagePath,listener);
		}
	}
	/**
	 * 多线程压缩图片的质量
	 * @author JPH
	 * @param bitmap 内存中的图片
	 * @param imgPath 图片的保存路径
	 * @date 2014-12-5下午11:30:43
	 */
	private void compressImageByQuality(final Bitmap bitmap, final String imgPath, final CompressListener listener){
		if(bitmap==null){
			sendMsg(false,imgPath,"像素压缩失败,bitmap is null",listener);
			return;
		}
		new Thread(new Runnable() {//开启多线程进行压缩处理
			@Override
			public void run() {
				// TODO Auto-generated method stub
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				int options = 100;
				bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);//质量压缩方法，把压缩后的数据存放到baos中 (100表示不压缩，0表示压缩到最小)
				while (baos.toByteArray().length >config.getMaxSize()) {//循环判断如果压缩后图片是否大于指定大小,大于继续压缩
					baos.reset();//重置baos即让下一次的写入覆盖之前的内容 
					options -= 10;//图片质量每次减少10
					if(options<0)options=0;//如果图片质量小于0，则将图片的质量压缩到最小值
					bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);//将压缩后的图片保存到baos中
					if(options==0)break;//如果图片的质量已降到最低则，不再进行压缩
				}
//				if(bitmap!=null&&!bitmap.isRecycled()){
//					bitmap.recycle();//回收内存中的图片
//				}
				try {
					File thumbnailFile=getThumbnailFile(new File(imgPath));
					FileOutputStream fos = new FileOutputStream(thumbnailFile);
					fos.write(baos.toByteArray());
					fos.flush();
					fos.close();
					sendMsg(true, thumbnailFile.getPath(),null,listener);
				} catch (Exception e) {
					sendMsg(false,imgPath,"质量压缩失败",listener);
					e.printStackTrace();
				}
			}
		}).start();
	}
	/**
	 * 按比例缩小图片的像素以达到压缩的目的
	 * @author JPH
	 * @param imgPath
	 * @return 
	 * @date 2014-12-5下午11:30:59
	 */
	private void compressImageByPixel(String imgPath,CompressListener listener) throws FileNotFoundException {
		if(imgPath==null){
			sendMsg(false,imgPath,"要压缩的文件不存在",listener);
			return;
		}
		BitmapFactory.Options newOpts = new BitmapFactory.Options();
		newOpts.inJustDecodeBounds = true;//只读边,不读内容
		BitmapFactory.decodeFile(imgPath, newOpts);
		newOpts.inJustDecodeBounds = false;
		int width = newOpts.outWidth;
		int height = newOpts.outHeight;
		float maxSize =config.getMaxPixel();
		int be = 1;
		if (width > height && width > maxSize) {//缩放比,用高或者宽其中较大的一个数据进行计算
			be = (int) (newOpts.outWidth / maxSize);
			be++;
		} else if (width < height && height > maxSize) {
			be = (int) (newOpts.outHeight / maxSize);
			be++;
		}
		newOpts.inSampleSize =be;//设置采样率
		newOpts.inPreferredConfig = Config.ARGB_8888;//该模式是默认的,可不设
		newOpts.inPurgeable = true;// 同时设置才会有效
		newOpts.inInputShareable = true;//。当系统内存不够时候图片自动被回收
		Bitmap bitmap = BitmapFactory.decodeFile(imgPath, newOpts);
		if (config.isEnableQualityCompress()){
			compressImageByQuality(bitmap,imgPath,listener);//压缩好比例大小后再进行质量压缩
		}else {
			File thumbnailFile=getThumbnailFile(new File(imgPath));
			bitmap.compress(Bitmap.CompressFormat.JPEG,100,new FileOutputStream(thumbnailFile));
			listener.onCompressSuccessed(thumbnailFile.getPath());
		}
	}
	/**
	 * 发送压缩结果的消息
	 * @param isSuccess 压缩是否成功
	 * @param imagePath
	 * @param message
	 */
	private void sendMsg(final boolean isSuccess, final String imagePath,final String message, final CompressListener listener){
		mhHandler.post(new Runnable() {
			@Override
			public void run() {
				if (isSuccess){
					listener.onCompressSuccessed(imagePath);
				}else{
					listener.onCompressFailed(imagePath,message);
				}
			}
		});
	}

	private File getThumbnailFile(File file){
		if (file==null||!file.exists())return file;
		return FileUtils.getPhotoCacheDir(context,file);
	}
}
