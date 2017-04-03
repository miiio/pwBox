package top.laobo9.pwbox.view.adapt;

import android.content.Context;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import bean.Data;
import top.laobo9.pwbox.R;

/**
 * Created by wax on 2017/4/2.
 */


public class MyRecyAdapter extends CommonAdapter<Data> {
    private Context context;
    public MyRecyAdapter(Context context, int layoutId, List<Data> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, Data data, int position) {
        holder.setText(R.id.item_name,data.getmTitle());
        holder.setText(R.id.item_mark,data.getmMark());
        holder.setText(R.id.item_time,data.getmTime());
        Field fileid = null;
        try {
            fileid =R.drawable.class.getField(data.getmPicname());
            holder.setImageResource(R.id.item_pic,Integer.parseInt(fileid.get(null).toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        holder.setImageResource(R.id.item_pic,R.drawable.defaultpic);
    }
}