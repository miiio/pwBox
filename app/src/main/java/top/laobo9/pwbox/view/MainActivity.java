package top.laobo9.pwbox.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View.OnClickListener;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter.OnItemClickListener;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import bean.Data;
import presenter.MainPresenter;
import top.laobo9.pwbox.R;
import top.laobo9.pwbox.view.Interface.IMainActivity;
import top.laobo9.pwbox.view.adapt.MyPagerAdapter;
import top.laobo9.pwbox.view.adapt.MyRecyAdapter;
import top.laobo9.pwbox.view.PicSelecter;


public class MainActivity extends AppCompatActivity
        implements IMainActivity, NavigationView.OnNavigationItemSelectedListener, OnClickListener,
        OnItemClickListener{
    public static final int CODE_ADD = 719;
    public static final int CODE_EDIT = 944;
    public static final int CODE_SETTING = 537;
    private Toolbar toolbar;
    private FloatingActionButton fab;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private LayoutInflater mInflater;
    private View view_social, view_game, view_website;//页卡视图
    private List<View> mViewList = new ArrayList<>();//页卡视图集合
    private List<String> mTitleList = new ArrayList<>();//页卡视图集合

    private List<Data> mData = new ArrayList<>();
    private List<Data> mData_s = new ArrayList<>();
    private List<Data> mData_g = new ArrayList<>();
    private List<Data> mData_w = new ArrayList<>();
    private RecyclerView rv_social;
    private RecyclerView rv_game;
    private RecyclerView rv_website;
    private CommonAdapter<Data> rva_social;
    private CommonAdapter<Data> rva_game;
    private CommonAdapter<Data> rva_website;

    private MainPresenter mMainPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //先验证是否需要手势密码
        checkPw();
        setContentView(R.layout.activity_main);
        getView();
        initView();
        mMainPresenter = new MainPresenter(this,getApplicationContext());
        mMainPresenter.ShowAll();
    }

    private boolean checkPw(){
        Intent tmp = getIntent();
        if(tmp.getStringExtra("flag")==null){
            Properties mProp = util.util.loadConfig(this);
            String pw = null;
            if(mProp.size()!=0){
                pw = mProp.getProperty("pw");
                if(!pw.equals("null")){
                    Intent intent = new Intent(this,LockActivity.class);
                    intent.putExtra("pw",pw);
                    startActivity(intent);
                    finish();
                    return false;
                }
            }
        }
        return true;
    }

    private void getView(){
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tabLayout = (TabLayout)findViewById(R.id.tabLayout);
        viewPager = (ViewPager)findViewById(R.id.viewPage);
    }
    private void initView(){
        setSupportActionBar(toolbar);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        //initViewPager
        mInflater = LayoutInflater.from(this);
        view_social = mInflater.inflate(R.layout.view_list,null);
        view_game = mInflater.inflate(R.layout.view_list,null);
        view_website = mInflater.inflate(R.layout.view_list,null);
        mViewList.add(view_social);
        mViewList.add(view_game);
        mViewList.add(view_website);
        mTitleList.add("社交");
        mTitleList.add("游戏");
        mTitleList.add("网站");
        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(mViewList,mTitleList);
        viewPager.setAdapter(myPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        //fab的点击事件
        fab.setOnClickListener(this);

        //设置列表显示
        rv_social = (RecyclerView)view_social.findViewById(R.id.item_recyclerview);
        rv_social.setLayoutManager(new LinearLayoutManager(this));
        rva_social = new MyRecyAdapter(this,R.layout.view_item,mData_s);
        rva_social.setOnItemClickListener(this);
        rv_social.setAdapter(rva_social);
        /////////////////////////////////////////
        rv_game = (RecyclerView)view_game.findViewById(R.id.item_recyclerview);
        rv_game.setLayoutManager(new LinearLayoutManager(this));
        rva_game = new MyRecyAdapter(this,R.layout.view_item,mData_g);
        rva_game.setOnItemClickListener(this);
        rv_game.setAdapter(rva_game);
        //////////////////////////////////////////////
        rv_website = (RecyclerView)view_website.findViewById(R.id.item_recyclerview);
        rv_website.setLayoutManager(new LinearLayoutManager(this));
        rva_website = new MyRecyAdapter(this,R.layout.view_item,mData_w);
        rva_website.setOnItemClickListener(this);
        rv_website.setAdapter(rva_website);
    }


    class MyRecyAdapter extends CommonAdapter<Data>{
        public MyRecyAdapter(Context context, int layoutId, List<Data> datas) {
            super(context, layoutId, datas);
        }

        @Override
        protected void convert(final ViewHolder holder, final Data data, final int position) {
            holder.setText(R.id.item_name,data.getmTitle());
            holder.setText(R.id.item_mark,data.getmMark());
            holder.setText(R.id.item_time,data.getmTime());
            holder.setImageResource(R.id.item_pic,getResourceIdByFilter(data.getmPicname()));

            holder.setOnClickListener(R.id.item_pic, new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Data data_tmp=null;
                    int viewPageid = viewPager.getCurrentItem();
                    switch (viewPageid){
                        case 0:
                            data_tmp = mData_s.get(position);
                            break;
                        case 1:
                            data_tmp = mData_g.get(position);
                            break;
                        case 2:
                            data_tmp = mData_w.get(position);
                            break;
                    }
                    final Data data_ = data_tmp;
                    new PicSelecter(MainActivity.this, new PicSelecter.SelectListener() {
                        @Override
                        public void onSelect(int position, String pic_name) {
                            data_.setmPicname(pic_name);
                            mMainPresenter.editData(data_);
                            Snackbar.make(toolbar,"更换图标成功!",Snackbar.LENGTH_LONG).show();
                        }
                    }).show();
                }
            });
        }


        public int  getResourceIdByFilter(String imageName){
            Class mipmap = R.drawable.class;
            try {
                Field field = mipmap.getField(imageName);
                int resId = field.getInt(imageName);
                return resId;
            } catch (NoSuchFieldException e) {
                return R.drawable.defaultpic;
            } catch (IllegalAccessException e) {
                return R.drawable.defaultpic;
            }

        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab:
                int viewPageid = viewPager.getCurrentItem();//0社交 1游戏 2网站
                Intent intent = new Intent(this,EditActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("addOredit","add");
                bundle.putInt("type",viewPageid);
                intent.putExtras(bundle);
                startActivityForResult(intent, CODE_ADD);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(data == null)return;
        Bundle b;
        Data ret;
        switch (requestCode){
            case CODE_ADD:
                b = data.getExtras();
                ret = (Data) b.getSerializable("data");
                mMainPresenter.addDate(ret);
                break;
            case CODE_EDIT:
                b = data.getExtras();
                ret = (Data) b.getSerializable("data");
                mMainPresenter.editData(ret);
                break;
            case CODE_SETTING:
                if(data.getStringExtra("initdb").equals("yes")){
                    mMainPresenter.initdb();
                    mMainPresenter.ShowAll();
                }
                break;
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(MainActivity.this,SettingActivity.class);
            startActivityForResult(intent,CODE_SETTING);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_setting) {
            Intent intent = new Intent(MainActivity.this,SettingActivity.class);
            startActivityForResult(intent,CODE_SETTING);
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void dealTheData(){
        mData_s.clear();
        mData_g.clear();
        mData_w.clear();
        for(int i = 0;i<mData.size();i++){
            Data tmp  = mData.get(i);
            if(tmp.getmType()==0)
                mData_s.add(tmp);
            else if (tmp.getmType()==1)
                mData_g.add(tmp);
            else if (tmp.getmType()==2)
                mData_w.add(tmp);
        }
    }

    @Override
    public void showData(Data data) {
        mData.add(data);
        //rva_social.notifyDataSetChanged();
    }

    @Override
    public void clearAll() {
        mData.clear();
    }

    @Override
    public void notifyShow() {
        dealTheData();
        rva_social.notifyDataSetChanged();
        rva_game.notifyDataSetChanged();
        rva_website.notifyDataSetChanged();
    }

    @Override
    public List<Data> getData() {
        return mData;
    }

    @Override
    public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
        Data data_tmp=null;
        int viewPageid = viewPager.getCurrentItem();
        switch (viewPageid){
            case 0:
                data_tmp = mData_s.get(position);
                break;
            case 1:
                data_tmp = mData_g.get(position);
                break;
            case 2:
                data_tmp = mData_w.get(position);
                break;
        }
        Intent intent = new Intent(this,EditActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("addOredit","edit");
        bundle.putInt("type",viewPageid);
        bundle.putSerializable("data",data_tmp);
        intent.putExtras(bundle);
        startActivityForResult(intent, CODE_EDIT);
    }

    @Override
    public boolean onItemLongClick(final View view, final RecyclerView.ViewHolder holder, int position) {
        Data data_tmp = null;
        int viewPageid = viewPager.getCurrentItem();
        switch (viewPageid){
            case 0:
                data_tmp = mData_s.get(position);
                break;
            case 1:
                data_tmp = mData_g.get(position);
                break;
            case 2:
                data_tmp = mData_w.get(position);
                break;
        }

        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);
        builder.setTitle("删除");
        builder.setMessage("确定要删除吗？");
        builder.setNegativeButton("取消", null);
        final Data finalData_tmp = data_tmp;
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mMainPresenter.DeleteData(finalData_tmp.getId());
            }
        });
        builder.show();

        return true;
    }
}
