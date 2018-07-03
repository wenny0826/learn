package com.wenny.mvpdemo.data.entity;

import com.wenny.mvpdemo.data.local.DaoSession;
import com.wenny.mvpdemo.data.local.ZhiHuListBeanDao;
import com.wenny.mvpdemo.data.local.ZhihuNewBeanDao;
import com.wenny.mvpdemo.util.TimeUtil;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;

/**
 * Created by Administrator on 2018/5/24.
 */
@Entity(nameInDb = "news_date")
public class ZhiHuListBean {
    @Id
    private String date;
    private String pagedate;
    @ToMany(referencedJoinProperty = "date")
    private List<ZhihuNewBean> stories;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 799710258)
    private transient ZhiHuListBeanDao myDao;


    @Generated(hash = 721746838)
    public ZhiHuListBean(String date, String pagedate) {
        this.date = date;
        this.pagedate = pagedate;
    }


    @Generated(hash = 881960479)
    public ZhiHuListBean() {
    }


    public String getShowData() {
        long time = TimeUtil.string2Date(date, TimeUtil.DATE_FORMAT_13).getTime();
        return TimeUtil.date2String(time,TimeUtil.DATE_FORMAT_12)+" " +TimeUtil.getWeekTime(time);
    }


    public String getDate() {
        return this.date;
    }


    public void setDate(String date) {
        this.date = date;
    }


    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 315534977)
    public List<ZhihuNewBean> getStories() {
        if (stories == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ZhihuNewBeanDao targetDao = daoSession.getZhihuNewBeanDao();
            List<ZhihuNewBean> storiesNew = targetDao._queryZhiHuListBean_Stories(date);
            synchronized (this) {
                if (stories == null) {
                    stories = storiesNew;
                }
            }
        }
        return stories;
    }


    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 2006863238)
    public synchronized void resetStories() {
        stories = null;
    }


    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }


    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }


    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }


    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1436077726)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getZhiHuListBeanDao() : null;
    }


    public String getPagedate() {
        return this.pagedate;
    }


    public void setPagedate(String pagedate) {
        this.pagedate = pagedate;
    }
}
