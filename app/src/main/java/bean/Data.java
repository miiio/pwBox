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
    private String mPicname;
    private String mTime;

    private int mType;
    public Data(int id,String Title, String Username, String Password,String Mark,int type,String Time,String Picname) {
        this.Id = id;
        this.mTitle = Title;
        this.mUsername = Username;
        this.mPassword = Password;
        this.mMark = Mark;
        this.mType = type;
        this.mTime = Time;
        this.mPicname = Picname;
    }

    public Data(int id,String Title, String Username, String Password,String Mark,int type,String Time) {
        this.Id = id;
        this.mTitle = Title;
        this.mUsername = Username;
        this.mPassword = Password;
        this.mMark = Mark;
        this.mType = type;
        this.mTime = Time;
        this.mPicname = "defaultpic";
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

    public String getmPicname() {
        return mPicname;
    }

    public void setmPicname(String mPicname) {
        this.mPicname = mPicname;
    }

}
