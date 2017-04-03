package top.laobo9.pwbox.view;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import top.laobo9.pwbox.R;

/**
 * Created by wax on 2017/4/3.
 */

public class PicSelecter {
    private Context context;
    private GridView gridView;
    private SimpleAdapter simpleAdapter;
    private SelectListener selectListener;
    public PicSelecter(Context context, SelectListener selectListener) {
        this.context = context;
        this.selectListener = selectListener;
    }

    public void show(){
        LayoutInflater inflater = LayoutInflater.from(context);
        View layout = inflater.inflate(R.layout.view_selectpic, null);

        //获取布局组件
        gridView = (GridView) layout.findViewById(R.id.grid);
        //图片
        final String imagesName[] = new String[]{"defaultpic","a115", "a360", "a360_cloud", "a360_free_wifi", "acfun", "alipay", "ali_taobao", "ali_trip", "ali_wangxin", "ali_xianyu", "baicizhan", "baidu", "baidu_hi", "baidu_pan", "baidu_tieba", "baidu_wenku", "banciyuan", "bilibili", "blizzard_authenticator", "blizzard_wow_armory", "bukamanhua", "ccb_bank", "changba", "cib_bank", "cmbchina_bank", "contacts", "coolapk_alt", "ctrip_alt", "ct_client", "dianping_alt", "douban", "douyu", "ecitic_bank", "eleme", "email", "esbook_read", "facebook", "fanliwang", "fetion", "game_dont_starve", "game_mihoyo_bh_3", "game_tencent_hlddz", "google_gmail", "google_play_store", "google_plus", "guangdiu", "guokr", "hai360", "huluxia_gametools", "huputiyu", "huya", "icbc_bank", "icbc_bank_im", "ingress", "iqiyi", "ireader", "jd", "jianshu", "jinritoutiao", "kugou", "kuwo_music_player", "letv", "line", "lol", "luyinbao", "meituxiuxiu", "mengniangbaike", "microsoft_account", "microsoft_bing_search", "microsoft_onedrive", "microsoft_remote_desktop", "microsoft_xbox", "netease_epay", "netease_news_alt", "netease_open_class", "netease_youdao_dict", "pinterest", "pixiv", "sfly", "shadowsocks", "shadowsocksr", "shanbay", "shuqi", "skype", "sohu_tv", "suning", "team_viewer_remote_control", "ted", "telegram", "tencent_cf_sns", "tencent_news", "tencent_qq", "tencent_qq_music_alt", "tencent_wechat", "tencent_weiyun", "tencent_wesync", "tudou", "tumblr", "turbo_vpn", "tv_plus", "twitter", "unicom", "v2ex", "vipshop", "wandoujia", "weibo", "wikipedia", "wordpress", "wuba", "xiami", "xiaomi_browser", "xiaomi_community", "xiaomi_miui", "xiaomi_mi_talk","ximalayating", "xunlei", "yihaodian", "youku", "yuantiku_gaokao", "zhanqi_tv", "zhihu"};
        final int images[] = new int[]{R.drawable.defaultpic,R.drawable.a115, R.drawable.a360, R.drawable.a360_cloud, R.drawable.a360_free_wifi, R.drawable.acfun, R.drawable.alipay, R.drawable.ali_taobao, R.drawable.ali_trip, R.drawable.ali_wangxin, R.drawable.ali_xianyu, R.drawable.baicizhan, R.drawable.baidu, R.drawable.baidu_hi, R.drawable.baidu_pan, R.drawable.baidu_tieba, R.drawable.baidu_wenku, R.drawable.banciyuan, R.drawable.bilibili, R.drawable.blizzard_authenticator, R.drawable.blizzard_wow_armory, R.drawable.bukamanhua, R.drawable.ccb_bank, R.drawable.changba, R.drawable.cib_bank, R.drawable.cmbchina_bank, R.drawable.contacts, R.drawable.coolapk_alt, R.drawable.ctrip_alt, R.drawable.ct_client, R.drawable.dianping_alt, R.drawable.douban, R.drawable.douyu, R.drawable.ecitic_bank, R.drawable.eleme, R.drawable.email, R.drawable.esbook_read, R.drawable.facebook, R.drawable.fanliwang, R.drawable.fetion, R.drawable.game_dont_starve, R.drawable.game_mihoyo_bh_3, R.drawable.game_tencent_hlddz, R.drawable.google_gmail, R.drawable.google_play_store, R.drawable.google_plus, R.drawable.guangdiu, R.drawable.guokr, R.drawable.hai360, R.drawable.huluxia_gametools, R.drawable.huputiyu, R.drawable.huya, R.drawable.icbc_bank, R.drawable.icbc_bank_im, R.drawable.ingress, R.drawable.iqiyi, R.drawable.ireader, R.drawable.jd, R.drawable.jianshu, R.drawable.jinritoutiao, R.drawable.kugou, R.drawable.kuwo_music_player, R.drawable.letv, R.drawable.line, R.drawable.lol, R.drawable.luyinbao, R.drawable.meituxiuxiu, R.drawable.mengniangbaike, R.drawable.microsoft_account, R.drawable.microsoft_bing_search, R.drawable.microsoft_onedrive, R.drawable.microsoft_remote_desktop, R.drawable.microsoft_xbox, R.drawable.netease_epay, R.drawable.netease_news_alt, R.drawable.netease_open_class, R.drawable.netease_youdao_dict, R.drawable.pinterest, R.drawable.pixiv, R.drawable.sfly, R.drawable.shadowsocks, R.drawable.shadowsocksr, R.drawable.shanbay, R.drawable.shuqi, R.drawable.skype, R.drawable.sohu_tv, R.drawable.suning, R.drawable.team_viewer_remote_control, R.drawable.ted, R.drawable.telegram, R.drawable.tencent_cf_sns, R.drawable.tencent_news, R.drawable.tencent_qq, R.drawable.tencent_qq_music_alt, R.drawable.tencent_wechat, R.drawable.tencent_weiyun, R.drawable.tencent_wesync, R.drawable.tudou, R.drawable.tumblr, R.drawable.turbo_vpn, R.drawable.tv_plus, R.drawable.twitter, R.drawable.unicom, R.drawable.v2ex, R.drawable.vipshop, R.drawable.wandoujia, R.drawable.weibo, R.drawable.wikipedia, R.drawable.wordpress, R.drawable.wuba, R.drawable.xiami, R.drawable.xiaomi_browser, R.drawable.xiaomi_community, R.drawable.xiaomi_miui, R.drawable.xiaomi_mi_talk,R.drawable.ximalayating, R.drawable.xunlei, R.drawable.yihaodian, R.drawable.youku, R.drawable.yuantiku_gaokao, R.drawable.zhanqi_tv, R.drawable.zhihu};
        //创建list集合，存储图片
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        for(int i=0;i<images.length;i++){
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("img", images[i]);
            list.add(map);
        }
        //设置适配器
        simpleAdapter = new SimpleAdapter(context, list,R.layout.img , new String[]{"img"}, new int[]{R.id.select_img});
        //适配器
        gridView.setAdapter(simpleAdapter);
        //点击事件监听

        AlertDialog.Builder builder = new AlertDialog.Builder(context).setNegativeButton("取消",null)
                .setTitle("选择图标").setView(layout);
        final Dialog dialog = builder.show();
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                selectListener.onSelect(position,imagesName[position]);
                dialog.dismiss();
            }
        });
    }
    public interface SelectListener{
        void onSelect(int position, String pic_name);
    }

}
