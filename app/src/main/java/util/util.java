package util;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Vector;

/**
 * Created by wax on 2017/4/2.
 */

public class util {
    public static String configfile = "/data/data/top.laobo9.pwbox/";
    public static String getTimeStr() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd\nHH:mm:ss");//设置日期格式
        return df.format(new Date());// new Date()为获取当前系统时间
    }

    public static String getTimeStrForBackUp() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        return df.format(new Date());// new Date()为获取当前系统时间
    }

    public static Properties loadConfig(Context context) {
        Properties properties = new Properties();
        try {
            FileInputStream s = new FileInputStream(configfile+"config.dat");
            properties.load(s);
        } catch (Exception e) {

            e.printStackTrace();
        }
        return properties;
    }

    public static void saveConfig(Context context, Properties properties) {
        try {
            FileOutputStream s = new FileOutputStream(configfile+"config.dat", false);
            properties.store(s, "");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public static boolean moveFile(String oldPath, String newPath){
        try {
            int bytesum = 0;
            int byteread = 0;
            File oldfile = new File(oldPath);
            if (oldfile.exists()) { //文件存在时
                InputStream inStream = new FileInputStream(oldPath); //读入原文件
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1444];
                int length;
                while ( (byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread; //字节数 文件大小
                    System.out.println(bytesum);
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
            }
        }
        catch (Exception e) {
            System.out.println("复制单个文件操作出错");
            e.printStackTrace();
        }
        return new File(oldPath).exists();
    }
    public static boolean backupData(String path) {
        /// path = mnt/sdcard/pwBoxBackUp/
        String oldPath = configfile+"data.db";
        String newPath =path+"backup_"+getTimeStrForBackUp()+".pb";
        File checkpath = new File(path);
        if(!checkpath.exists()){
            checkpath.mkdir();
        }
        try {
            int bytesum = 0;
            int byteread = 0;
            File oldfile = new File(oldPath);
            if (oldfile.exists()) { //文件存在时
                InputStream inStream = new FileInputStream(oldPath); //读入原文件
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1444];
                int length;
                while ( (byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread; //字节数 文件大小
                    System.out.println(bytesum);
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
            }
        }
        catch (Exception e) {
            System.out.println("复制单个文件操作出错");
            e.printStackTrace();
        }
        return new File(oldPath).exists();
    }

    public static Vector<String> getBackUpFileName(String fileAbsolutePath) {
        Vector<String> vecFile = new Vector<String>();
        File file = new File(fileAbsolutePath);
        File[] subFile = file.listFiles();

        for (int iFileLength = 0; iFileLength < subFile.length; iFileLength++) {
            // 判断是否为文件夹
            if (!subFile[iFileLength].isDirectory()) {
                String filename = subFile[iFileLength].getName();
                // 判断是否为pb结尾
                if (filename.trim().toLowerCase().endsWith(".pb")) {
                    vecFile.add(filename);
                }
            }
        }
        return vecFile;
    }

    public static boolean deleteFile(String filePath) {
        File file = new File(filePath);
        if (file.isFile() && file.exists()) {
            return file.delete();
        }
        return false;
    }
}
