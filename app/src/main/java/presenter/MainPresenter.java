package presenter;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import model.DataModel;
import model.IDataModel;
import top.laobo9.pwbox.view.Interface.IMainActivity;
import bean.Data;

public class MainPresenter {

    private IMainActivity mMainActivity;
    private IDataModel mDataModel;
    private Context mContext;
    public MainPresenter(IMainActivity mMainActivity, Context context) {
        this.mMainActivity = mMainActivity;
        this.mContext = context;
        mDataModel = new DataModel(context);
    }
    public void editData(Data data){
        mDataModel.EditData(data);
        ShowAll();
    }
    public void addDate(Data data){
        String pwtmp = data.getmPassword();
        int addid =  mDataModel.addData(data);
        data.setId(addid);
        data.setmPassword(pwtmp);
        mMainActivity.showData(data);
        mMainActivity.notifyShow();
    }
    public void ShowAll(){
        mMainActivity.clearAll();
        List<Data> ld = mDataModel.getAllData();
        //mMainActivity.showAll(ld);
        for(int i = 0;i<ld.size();i++)
            mMainActivity.showData(ld.get(i));
        mMainActivity.notifyShow();
    }
    public void DeleteData(int id){
        mDataModel.DeleteData(id);
        ShowAll();
    }

}
