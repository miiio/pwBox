package top.laobo9.pwbox.view;

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
        final String imagesName[] = new String[]{"a115.png", "a360.png", "a360_cloud.png", "a360_free_wifi.png", "acfun.png", "alipay.png", "ali_taobao.png", "ali_trip.png", "ali_wangxin.png", "ali_xianyu.png", "baicizhan.png", "baidu.png", "baidu_hi.png", "baidu_pan.png", "baidu_tieba.png", "baidu_wenku.png", "banciyuan.png", "bilibili.png", "blizzard_authenticator.png", "blizzard_wow_armory.png", "bukamanhua.png", "ccb_bank.png", "changba.png", "cib_bank.png", "cmbchina_bank.png", "contacts.png", "coolapk_alt.png", "ctrip_alt.png", "ct_client.png", "dianping_alt.png", "douban.png", "douyu.png", "ecitic_bank.png", "eleme.png", "email.png", "esbook_read.png", "facebook.png", "fanliwang.png", "fetion.png", "game_dont_starve.png", "game_mihoyo_bh_3.png", "game_tencent_hlddz.png", "google_gmail.png", "google_play_store.png", "google_plus.png", "guangdiu.png", "guokr.png", "hai360.png", "huluxia_gametools.png", "huputiyu.png", "huya.PNG", "icbc_bank.png", "icbc_bank_im.png", "ingress.png", "iqiyi.png", "ireader.png", "jd.png", "jianshu.png", "jinritoutiao.png", "kugou.png", "kuwo_music_player.png", "letv.png", "line.png", "lol.png", "luyinbao.PNG", "meituxiuxiu.png", "mengniangbaike.png", "microsoft_account.png", "microsoft_bing_search.png", "microsoft_onedrive.png", "microsoft_remote_desktop.png", "microsoft_xbox.png", "netease_epay.png", "netease_news_alt.png", "netease_open_class.png", "netease_youdao_dict.png", "pinterest.png", "pixiv.png", "sfly.png", "shadowsocks.png", "shadowsocksr.png", "shanbay.png", "shuqi.png", "skype.png", "sohu_tv.png", "suning.png", "team_viewer_remote_control.png", "ted.png", "telegram.png", "tencent_cf_sns.png", "tencent_news.png", "tencent_qq.png", "tencent_qq_music_alt.png", "tencent_wechat.png", "tencent_weiyun.png", "tencent_wesync.png", "tudou.png", "tumblr.png", "turbo_vpn.png", "tv_plus.png", "twitter.png", "unicom.png", "v2ex.PNG", "vipshop.png", "wandoujia.png", "weibo.png", "wikipedia.png", "wordpress.png", "wuba.PNG", "xiami.png", "xiaomi_browser.png", "xiaomi_community.png", "xiaomi_miui.png", "xiaomi_mi_talk.png","ximalayating.png", "xunlei.png", "yihaodian.png", "youku.png", "yuantiku_gaokao.png", "zhanqi_tv.png", "zhihu.png"};
        final int images[] = new int[]{R.drawable.a115, R.drawable.a360, R.drawable.a360_cloud, R.drawable.a360_free_wifi, R.drawable.acfun, R.drawable.alipay, R.drawable.ali_taobao, R.drawable.ali_trip, R.drawable.ali_wangxin, R.drawable.ali_xianyu, R.drawable.baicizhan, R.drawable.baidu, R.drawable.baidu_hi, R.drawable.baidu_pan, R.drawable.baidu_tieba, R.drawable.baidu_wenku, R.drawable.banciyuan, R.drawable.bilibili, R.drawable.blizzard_authenticator, R.drawable.blizzard_wow_armory, R.drawable.bukamanhua, R.drawable.ccb_bank, R.drawable.changba, R.drawable.cib_bank, R.drawable.cmbchina_bank, R.drawable.contacts, R.drawable.coolapk_alt, R.drawable.ctrip_alt, R.drawable.ct_client, R.drawable.dianping_alt, R.drawable.douban, R.drawable.douyu, R.drawable.ecitic_bank, R.drawable.eleme, R.drawable.email, R.drawable.esbook_read, R.drawable.facebook, R.drawable.fanliwang, R.drawable.fetion, R.drawable.game_dont_starve, R.drawable.game_mihoyo_bh_3, R.drawable.game_tencent_hlddz, R.drawable.google_gmail, R.drawable.google_play_store, R.drawable.google_plus, R.drawable.guangdiu, R.drawable.guokr, R.drawable.hai360, R.drawable.huluxia_gametools, R.drawable.huputiyu, R.drawable.huya, R.drawable.icbc_bank, R.drawable.icbc_bank_im, R.drawable.ingress, R.drawable.iqiyi, R.drawable.ireader, R.drawable.jd, R.drawable.jianshu, R.drawable.jinritoutiao, R.drawable.kugou, R.drawable.kuwo_music_player, R.drawable.letv, R.drawable.line, R.drawable.lol, R.drawable.luyinbao, R.drawable.meituxiuxiu, R.drawable.mengniangbaike, R.drawable.microsoft_account, R.drawable.microsoft_bing_search, R.drawable.microsoft_onedrive, R.drawable.microsoft_remote_desktop, R.drawable.microsoft_xbox, R.drawable.netease_epay, R.drawable.netease_news_alt, R.drawable.netease_open_class, R.drawable.netease_youdao_dict, R.drawable.pinterest, R.drawable.pixiv, R.drawable.sfly, R.drawable.shadowsocks, R.drawable.shadowsocksr, R.drawable.shanbay, R.drawable.shuqi, R.drawable.skype, R.drawable.sohu_tv, R.drawable.suning, R.drawable.team_viewer_remote_control, R.drawable.ted, R.drawable.telegram, R.drawable.tencent_cf_sns, R.drawable.tencent_news, R.drawable.tencent_qq, R.drawable.tencent_qq_music_alt, R.drawable.tencent_wechat, R.drawable.tencent_weiyun, R.drawable.tencent_wesync, R.drawable.tudou, R.drawable.tumblr, R.drawable.turbo_vpn, R.drawable.tv_plus, R.drawable.twitter, R.drawable.unicom, R.drawable.v2ex, R.drawable.vipshop, R.drawable.wandoujia, R.drawable.weibo, R.drawable.wikipedia, R.drawable.wordpress, R.drawable.wuba, R.drawable.xiami, R.drawable.xiaomi_browser, R.drawable.xiaomi_community, R.drawable.xiaomi_miui, R.drawable.xiaomi_mi_talk,R.drawable.ximalayating, R.drawable.xunlei, R.drawable.yihaodian, R.drawable.youku, R.drawable.yuantiku_gaokao, R.drawable.zhanqi_tv, R.drawable.zhihu};
        //创建list集合，存储图片
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        for(int i=0;i<images.length;i++){
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("img", images[i]);
            list.add(map);
        }
        //设置适配器
        simpleAdapter = new SimpleAdapter(context, list,R.layout.img , new String[]{"img"}, new int[]{R.id.img});
        //适配器
        gridView.setAdapter(simpleAdapter);
        //点击事件监听
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                selectListener.onSelect(position,imagesName[position]);
            }
        });
        new AlertDialog.Builder(context).setNegativeButton("取消",null)
                .setTitle("选择图标").setView(layout).show();
    }
    public interface SelectListener{
        void onSelect(int position, String pic_name);
    }

}
