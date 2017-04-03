package model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import bean.Data;
import util.DesUtils;

/**
 * Created by wax on 2017/4/1.
 */

public class DataModel implements IDataModel {
    private SQLiteDatabase db;
    private Context context;
    private DesUtils jcush;
    public void initDB(){
        jcush = new DesUtils(DesUtils.keyyy[82]+DesUtils.keyyy[34]);
        db = context.openOrCreateDatabase("/data/data/top.laobo9.pwbox/data.db", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE  TABLE IF NOT EXISTS alldata" +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT," +
                " username VARCHAR, password VARCHAR, mark VARCHAR,type INTEGER, subtime VARCHAR)");
    }

    public DataModel(Context context) {
        this.context = context;
        initDB();
    }

    @Override
    public int addData(Data data) {
        try {
            data.setmPassword(jcush.encrypt(data.getmPassword()) );
        } catch (Exception e) {
            e.printStackTrace();
        }
        db.execSQL( "insert into alldata(title,username,password,mark,type,subtime) " +
                "values('"+data.getmTitle()+"','"+data.getmUsername()+"','"+
                data.getmPassword()+"','"+data.getmMark()+"',"+data.getmType()+",'"+data.getmTime()+"')");
        int ret = -1;//添加之后返回其id
        Cursor c = db.rawQuery("select max(_id)  from alldata",null);
        c.moveToNext();
        ret = c.getInt(c.getColumnIndex("max(_id)"));
        return ret;
    }

    @Override
    public List<Data> getAllData() {
        Cursor c = db.rawQuery("SELECT * FROM alldata ORDER BY _id DESC",null);
        List<Data> ret = new ArrayList<>();
        while (c.moveToNext()) {
            String pw = null;
            try {
                pw = jcush.decrypt(c.getString(c.getColumnIndex("password")));
            } catch (Exception e) {
                e.printStackTrace();
            }
            ret.add(new Data(
                        c.getInt(c.getColumnIndex("_id"))
                    , c.getString(c.getColumnIndex("title"))
                    , c.getString(c.getColumnIndex("username"))
                    , pw
                    , c.getString(c.getColumnIndex("mark"))
                    , c.getInt(c.getColumnIndex("type"))
                    , c.getString(c.getColumnIndex("subtime"))
            ));
        }
        c.close();
        return ret;
    }

    @Override
    public void EditData(Data data) {
        try {
            data.setmPassword(jcush.encrypt(data.getmPassword()) );
        } catch (Exception e) {
            e.printStackTrace();
        }
        int id = data.getId();
        db.execSQL("update alldata set title='"+data.getmTitle()+"'," +
                "username='"+data.getmUsername()+"', " +
                "password='"+data.getmPassword()+"', " +
                "mark='"+data.getmMark()+"',subtime='"+data.getmTime()+"' where _id="+id);
    }

    @Override
    public void DeleteData(int id) {
        db.execSQL("delete from alldata where _id="+id);
    }

}
