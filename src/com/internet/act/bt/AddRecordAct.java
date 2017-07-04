package com.internet.act.bt;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.LocationClient;
import com.bumptech.glide.Glide;
import com.hexinpass.shequ.common.utils.permission.PermissionUtil;
import com.hexinpass.shequ.common.utils.permission.callback.PermissionResultAdapter;
import com.internet.app.MainApplication;
import com.internet.basic.BasicActivity;
import com.internet.fragment.SelectPhotoPathDialogFragment;
import com.internet.fragment.SelectPhotoPathDialogFragment.OnSelectPhotoPathClickListener;
import com.internet.http.api.ApiException;
import com.internet.http.api.ApiManager;
import com.internet.http.data.post.AddRecordPost;
import com.internet.switchbt.R;
import com.internet.view.HeaderView;
import com.internet.view.MyGridView;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoImpl;
import com.jph.takephoto.compress.CompressConfig;

@EActivity(R.layout.act_add_record)
public class AddRecordAct extends BasicActivity implements
		OnSelectPhotoPathClickListener, TakePhoto.TakeResultListener {
	final int DATE_DIALOG = 1;
	@ViewById
	HeaderView view_header;

	@ViewById
	EditText et_title, et_address, et_content;
	@ViewById
	TextView tv_date, et_helper;
	@ViewById
	ImageView image_date, image_add;
	@ViewById
	MyGridView listView;
	GridIconAdapter adapter;

	TakePhoto takePhoto;
	Uri imageUri;
	int mYear, mMonth, mDay;

	public LocationClient mLocationClient = null;
	public MyLocationListener myListener = new MyLocationListener();

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);

		takePhoto = new TakePhotoImpl(this, this);
		takePhoto.onCreate(arg0);
	}

	@AfterViews
	void init() {
		File file = new File(Environment.getExternalStorageDirectory(),
				"/temp/" + System.currentTimeMillis() + ".jpg");
		if (!file.getParentFile().exists())
			file.getParentFile().mkdirs();
		imageUri = Uri.fromFile(file);

		view_header.setTitle("帮扶日志");
		view_header.setRight("提交");
		view_header.getRightTextView().setTextColor(
				getResources().getColor(R.color.bg_header));
		view_header.getRightTextView().setBackgroundResource(
				R.drawable.btn_white);
		view_header.getRightTextView().setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						checkSubmitData();

					}
				});

		adapter = new GridIconAdapter(this);
		listView.setAdapter(adapter);

		Calendar ca = Calendar.getInstance();
		mYear = ca.get(Calendar.YEAR);
		mMonth = ca.get(Calendar.MONTH);
		mDay = ca.get(Calendar.DAY_OF_MONTH);

		mLocationClient = new LocationClient(getApplicationContext());
		// 声明LocationClient类
		mLocationClient.registerLocationListener(myListener);
		// 注册监听函数
		myListener.initLocation(mLocationClient);

		et_helper.setText(MainApplication.getInstance()
				.getLoginResponseEntity().getUsername());

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		mLocationClient.start();
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (mLocationClient.isStarted()) {
			mLocationClient.stop();
		}
	}

	@Click(R.id.image_add)
	void clickImageAdd() {
		SelectPhotoPathDialogFragment.builder().build()
				.show(getFragmentManager(), "");
	}

	@SuppressWarnings("deprecation")
	@Click(R.id.image_date)
	void clickDate() {
		showDialog(DATE_DIALOG);
	}

	@Background
	void doSubmit() {
		showLoading();
		boolean state = false;
		try {
			AddRecordPost data = new AddRecordPost();
			data.title = et_title.getText().toString();
			data.username = et_helper.getText().toString();

			data.help_date = tv_date.getText().toString();
			data.help_address = et_address.getText().toString();
			data.content = et_content.getText().toString();
			data.head_id = getIntent().getStringExtra("data");
			data.latitude = myListener.latitude + "";
			data.longitude = myListener.longitude + "";

			Map<String, String> mapFile = null;
			if (adapter.getCount() > 0) {
				mapFile = new HashMap<String, String>();
				List<String> pathList = adapter.getPathList();
				for (int i = 0; i < pathList.size(); i++) {
					mapFile.put(i + "", pathList.get(i));
					System.out.println("JJJJJJJJJJJJJJJJJJJ");
				}
			}

			state = ApiManager.getDefault().addRecord(data, mapFile);
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			showToast(e.getErrorMessage());
		}
		closeLoading();
		if (state) {
			showToast("上传日志成功!");
			finish();
		}
	}

	void checkSubmitData() {

		if (TextUtils.isEmpty(et_title.getText().toString())) {
			showToast("请输入主题");
			return;
		}

		if (TextUtils.isEmpty(et_helper.getText().toString())) {
			showToast("请输入帮扶人");
			return;
		}

		if (TextUtils.isEmpty(tv_date.getText())) {
			showToast("请输入日期");
			return;
		}

		if (TextUtils.isEmpty(et_address.getText().toString())) {
			showToast("请输入帮扶地址");
			return;
		}

		if (TextUtils.isEmpty(et_content.getText().toString())) {
			showToast("请输入帮扶内容");
			return;
		}
		doSubmit();
	}

	private DatePickerDialog.OnDateSetListener mdateListener = new DatePickerDialog.OnDateSetListener() {

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			// TODO Auto-generated method stub
			mYear = year;
			mMonth = monthOfYear;
			mDay = dayOfMonth;
			display();
		}
	};

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DATE_DIALOG:
			return new DatePickerDialog(this, mdateListener, mYear, mMonth,
					mDay);
		}
		return null;
	}

	public void display() {
		tv_date.setText(new StringBuffer().append(mYear).append("-")
				.append(mMonth + 1).append("-").append(mDay).append(" "));
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// 完成照相后回调用此方法
		super.onActivityResult(requestCode, resultCode, data);
		takePhoto.onActivityResult(requestCode, resultCode, data);
		return;
	}

	@Override
	public void takeSuccess(String imagePath) {
		// TODO Auto-generated method stub
		adapter.addPath(imagePath);
	}

	@Override
	public void takeFail(String msg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void takeCancel() {
		// TODO Auto-generated method stub

	}

	@Override
	public void selectAlbum() {
		// TODO Auto-generated method stub
		takePhoto.onEnableCompress(
				new CompressConfig.Builder().setMaxSize(100 * 1024)
						.setMaxPixel(800).create(), true).onPicSelectOriginal();
	}

	@Override
	public void selectCamera() {
		// TODO Auto-generated method stub
		PermissionUtil.getInstance().request(
				new String[] { Manifest.permission.CAMERA,
						Manifest.permission.WRITE_EXTERNAL_STORAGE },
				new PermissionResultAdapter() {
					@Override
					public void onPermissionGranted(String... permissions) {
						super.onPermissionGranted(permissions);
					}

					@Override
					public void onPermissionDenied(String... permissions) {
						Toast.makeText(AddRecordAct.this,
								"您拒绝了相关权限，使用该功能需重新在设置中打开相关权限。",
								Toast.LENGTH_SHORT).show();
					}

					@Override
					public void onPermissionGranted() {
						takePhoto.onEnableCompress(
								new CompressConfig.Builder()
										.setMaxSize(100 * 1024)
										.setMaxPixel(800).create(), true)
								.onPicTakeOriginal(imageUri);
					}

					@Override
					public void onRationalShow(String... permissions) {
						super.onRationalShow(permissions);
					}
				});
	}

	public class GridIconAdapter extends BaseAdapter {

		private Context mContext;
		private ArrayList<String> pathList = new ArrayList<String>();

		public GridIconAdapter(Context context) {
			this.mContext = context;

		}

		@Override
		public int getCount() {
			return pathList.size();
		}

		@Override
		public Object getItem(int position) {
			return pathList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			ViewHolder viewHolder;
			if (convertView == null) {
				convertView = LayoutInflater.from(mContext).inflate(
						R.layout.item_add_photo, null);
				viewHolder = new ViewHolder();
				viewHolder.photoTemp = (ImageView) convertView
						.findViewById(R.id.photo_temp);
				convertView.setTag(viewHolder);
			} else {
				viewHolder = (ViewHolder) convertView.getTag();
			}
			Glide.with(mContext).load(pathList.get(position))
					.into(viewHolder.photoTemp);
			return convertView;
		}

		public void addPath(String path) {
			this.pathList.add(path);
			notifyDataSetChanged();
		}

		public void removeAt(int location) {
			pathList.remove(location);
			notifyDataSetChanged();
		}

		public List<String> getPathList() {
			return pathList;
		}

		private class ViewHolder {

			public ImageView photoTemp;

		}
	}

}
