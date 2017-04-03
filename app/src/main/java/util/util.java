package util;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * Created by wax on 2017/4/2.
 */

public class util {
    public static String configfile = "/data/data/top.laobo9.pwbox/config.dat";
    public static String getTimeStr() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd\nHH:mm:ss");//设置日期格式
        return df.format(new Date());// new Date()为获取当前系统时间
    }

    public static Properties loadConfig(Context context) {
        Properties properties = new Properties();
        try {
            FileInputStream s = new FileInputStream(configfile);
            properties.load(s);
        } catch (Exception e) {

            e.printStackTrace();
        }
        return properties;
    }

    public static void saveConfig(Context context, Properties properties) {
        try {
            FileOutputStream s = new FileOutputStream(configfile, false);
            properties.store(s, "");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
