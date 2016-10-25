package com.autoinhome.autolight.greendao;

import com.autoinhome.autolight.app.MyApplication;
import com.autoinhome.autolight.greendao.gen.DaoMaster;
import com.autoinhome.autolight.greendao.gen.DaoSession;

/**
 * Created by yanglong on 2016/9/26.
 */

public class GreenDaoManager {
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;


    public GreenDaoManager() {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(MyApplication.getMyApplication(), "autolight-db");
        mDaoMaster = new DaoMaster(devOpenHelper.getWritableDb());
        mDaoSession = mDaoMaster.newSession();
    }

    public static GreenDaoManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public DaoMaster getMaster() {
        return mDaoMaster;
    }

    public DaoSession getSession() {
        return mDaoSession;
    }

    public DaoSession getNewSession() {
        mDaoSession = mDaoMaster.newSession();
        return mDaoSession;
    }

    private static class SingletonHolder {
        private static final GreenDaoManager INSTANCE = new GreenDaoManager();
    }
}
