package com.jph.takephoto.uitl;

import android.content.Context;
import android.util.Log;

import java.io.File;

/**
 * Created by whl on 2016/12/22.
 */

public class FileUtils {
    private static String DEFAULT_DISK_CACHE_DIR = "takephoto_cache";
    public static File getPhotoCacheDir(Context context, File file) {
        File cacheDir = context.getCacheDir();
        if (cacheDir != null) {
            File mCacheDir = new File(cacheDir,DEFAULT_DISK_CACHE_DIR);
            if (!mCacheDir.mkdirs() && (!mCacheDir.exists() || !mCacheDir.isDirectory())) {
                return file;
            }else {
                return new File(mCacheDir, file.getName());
            }
        }
        return file;
    }

    public static void delete(String path) {
        try {
            if(path == null) {
                return ;
            }
            File file = new File(path);
            if(!file.delete()) {
                file.deleteOnExit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
