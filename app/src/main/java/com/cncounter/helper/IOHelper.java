package com.cncounter.helper;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;

/**
 * IO 辅助类
 * Created by renfufei on 2015/11/30.
 */
public abstract class IOHelper {
    /**
     * 判断外部存储是否可写
     */
    public static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /**
     *  判断外部存储是否可读
     */
    public static boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

    /**
     * 获取临时文件
     * @param context 上下文
     * @param url
     * @return
     */
    public static File getTempFile(Context context, String url) {
        File file = null;
        try {
            String fileName = Uri.parse(url).getLastPathSegment();
            file = File.createTempFile(fileName, null, context.getCacheDir());
        } catch (IOException e) {
            // Error while creating file
            Log.e("exception","创建临时文件出错; url="+url,e);
        }
        return file;
    }

    /**
     * 获取缓存文件
     * @param context 上下文
     * @param filename 文件名
     * @return
     */
    public static File getCacheFile(Context context, String filename){
        File cacheDir = context.getCacheDir();
        File file = getFile(cacheDir, filename);
        if(null != file && false == file.exists()){
            try {
                file = File.createTempFile(filename, null, cacheDir);
            } catch (IOException e) {
                Log.e("exception", "创建缓存文件出错; filename=" + filename, e);
            }
        }
        return  file;
    }

    /**
     * 获取内部文件
     * @param context 上下文
     * @param filename 文件名
     * @return
     */
    public static File getInternalFile(Context context, String filename){
        File internalDir = context.getFilesDir();
        File file = getFile(internalDir, filename);
        return  file;
    }

    /**
     * 获取文件
     * @param directory 目录
     * @param filename 文件名
     * @return
     */
    public static File getFile(File directory, String filename){
        if(false == directory.exists() || false == directory.isDirectory()){
            return null;
        }
        File file = new File(directory, filename);
        return  file;
    }

    /**
     * 获取可用空间Size
     * @param context 上下文
     * @return
     */
    public static long getFreeSpace(Context context){
        File internalDir = context.getFilesDir();
        long size = internalDir.getFreeSpace();
        return  size;
    }

    /**
     * 获取总的空间大小
     * @param context 上下文
     * @return
     */
    public static long getTotalSpace(Context context){
        File internalDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        long size = internalDir.getFreeSpace();
        return  size;
    }
}
