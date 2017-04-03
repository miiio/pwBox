package top.laobo9.pwbox.view.Interface;

import java.util.List;

import bean.Data;

/**
 * Created by wax on 2017/4/1.
 */

public interface IMainActivity {
    void clearAll();
    void showData(Data data);
    List<Data> getData();
    void notifyShow();
}
