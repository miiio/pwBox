package model;

import java.util.List;

import bean.Data;

/**
 * Created by wax on 2017/4/1.
 */

public interface IDataModel {
    int addData(Data data);
    List<Data> getAllData();
    void EditData(Data data);
    void DeleteData(int id);
    void initDB();
}
