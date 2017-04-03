package top.laobo9.pwbox.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.Properties;
import java.util.Vector;

import bean.Data;
import top.laobo9.pwbox.R;


public class SettingActivity extends AppCompatActivity implements View.OnClickListener{
    private Toolbar toolbar;
    private boolean hasPw;
    private Properties mProp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        toolbar = (Toolbar)findViewById(R.id.setting_toolbar);
        setSupportActionBar(toolbar);
        ActionBar mActionBar = getSupportActionBar();
        toolbar.setTitle("设置");
        mActionBar.setDisplayHomeAsUpEnabled(true);//设置返回箭头显示
        mActionBar.setHomeButtonEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.setting_setPw).setOnClickListener(this);
        findViewById(R.id.setting_backup).setOnClickListener(this);
        findViewById(R.id.setting_rebackup).setOnClickListener(this);
        findViewById(R.id.setting_reset).setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        mProp = util.util.loadConfig(this);
        if(mProp.size()!=0 && !mProp.getProperty("pw").equals("null")){
            unPw(false);
            hasPw=true;
        }else{
            hasPw=false;
            if(mProp.size()==0) {
                Properties prop = new Properties();
                prop.put("pw", "null");
                util.util.saveConfig(this, prop);
            }
        }
        super.onResume();
    }

    public void unPw(boolean is){
        TextView t1 = (TextView)findViewById(R.id.setting_setPw_t1);
        TextView t2 = (TextView)findViewById(R.id.setting_setPw_t2);
        if(is){
            t1.setText("设置手势密码保护");
            t2.setText("每次进入软件不需要手势密码验证");
        }else {
            t1.setText("取消手势密码保护");
            t2.setText("每次进入软件需要手势密码验证");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.setting_setPw:
                if(!hasPw){
                    Intent intent = new Intent(this,SetLockActivity.class);
                    startActivity(intent);
                }else{
                    unPw(true);
                    hasPw=false;
                    Properties prop = new Properties();
                    prop.put("pw", "null");
                    util.util.saveConfig(this, prop);
                    Snackbar.make(v,"手势密码验证已取消!",Snackbar.LENGTH_LONG).show();
                }
                break;
            case R.id.setting_backup:
                if(util.util.backupData("/mnt/sdcard/pwBoxBackUp/")){
                    Snackbar.make(v,":)数据已备份,备份文件存放于/mnt/sdcard/pwBoxBackUp/",Snackbar.LENGTH_LONG).show();
                }else{
                    Snackbar.make(v,"备份失败...!",Snackbar.LENGTH_LONG).show();
                }
                break;
            case R.id.setting_rebackup:
                Vector<String> file = util.util.getBackUpFileName("/mnt/sdcard/pwBoxBackUp/");
                if(file.size()==0){
                    Snackbar.make(toolbar,"请把要恢复的备份放在/sdcard/pwBoxBackUp/下",Snackbar.LENGTH_LONG).show();
                }
                else{
                    showBackUpList(file);
                }
                break;
            case R.id.setting_reset:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("警告");
                builder.setMessage("确定要重置吗？清空的数据无法恢复！");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        util.util.deleteFile(util.util.configfile+"data.db");
                        Intent intent = new Intent();
                        intent.putExtra("initdb","yes");
                        setResult(MainActivity.CODE_SETTING,intent);
                        Snackbar.make(toolbar,"数据已重置...",Snackbar.LENGTH_LONG).show();
                    }
                });
                builder.setNegativeButton("取消",null).show();
                break;
        }
    }
    public void showBackUpList(final Vector<String> file){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("请选择一个备份:");
        final String[] items = (String[]) file.toArray(new String[0]);/*设置列表的内容*/
        builder.setNegativeButton("取消",null);
        builder.setItems(items, new DialogInterface.OnClickListener() {/*设置列表的点击事件*/
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Toast.makeText(MainActivity.this, items[which], Toast.LENGTH_SHORT).show();
                rebackup(file.get(which));
            }
        });
        builder.setCancelable(true);
        builder.show();
    }
    public void rebackup(String file){
        util.util.deleteFile(util.util.configfile+"data.db");
        if(util.util.moveFile("/mnt/sdcard/pwBoxBackUp/"+file,util.util.configfile+"data.db")){
            Intent intent = new Intent();
            intent.putExtra("initdb","yes");
            setResult(MainActivity.CODE_SETTING,intent);
            Snackbar.make(toolbar,"还原备份成功!",Snackbar.LENGTH_LONG).show();
        }else{
            Snackbar.make(toolbar,"还原备份失败!",Snackbar.LENGTH_LONG).show();
        }
    }
}
