package bean;

import java.io.Serializable;

/**
 * Created by wax on 2017/4/1.
 */

public class Data implements Serializable {

    private int Id;
    private String mTitle;
    private String mUsername;
    private String mPassword;
    private String mMark;


    private String mTime;

    private int mType;
    public Data(int id,String mTitle, String mUsername, String mPassword,String mMark,int type,String mTime) {
        this.Id = id;
        this.mTitle = mTitle;
        this.mUsername = mUsername;
        this.mPassword = mPassword;
        this.mMark = mMark;
        this.mType = type;
        this.mTime = mTime;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmUsername() {
        return mUsername;
    }

    public void setmUsername(String mUsername) {
        this.mUsername = mUsername;
    }

    public String getmPassword() {
        return mPassword;
    }

    public void setmPassword(String mPassword) {
        this.mPassword = mPassword;
    }

    public String getmMark() {
        return mMark;
    }

    public void setmMark(String mMark) {
        this.mMark = mMark;
    }

    public int getmType() {
        return mType;
    }

    public void setmType(int mType) {
        this.mType = mType;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getmTime() {
        return mTime;
    }

    public void setmTime(String mTime) {
        this.mTime = mTime;
    }
}
