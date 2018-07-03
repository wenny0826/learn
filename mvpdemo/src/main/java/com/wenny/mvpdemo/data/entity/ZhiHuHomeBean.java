package com.wenny.mvpdemo.data.entity;

import com.wenny.mvpdemo.data.local.DaoSession;
import com.wenny.mvpdemo.data.local.ZhiHuHomeBeanDao;
import com.wenny.mvpdemo.data.local.ZhihuBanberBeanDao;
import com.wenny.mvpdemo.data.local.ZhihuNewBeanDao;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;

/**
 * Created by Administrator on 2018/5/23.
 */
@Entity(nameInDb = "news_home")
public class ZhiHuHomeBean {
    @Id
    private String date;
    @ToMany(referencedJoinProperty = "date")
    private List<ZhihuNewBean> stories;
    @ToMany(referencedJoinProperty = "date")
    private List<ZhihuBanberBean> top_stories;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 1652154629)
    private transient ZhiHuHomeBeanDao myDao;
    @Generated(hash = 1840204407)
    public ZhiHuHomeBean(String date) {
        this.date = date;
    }
    @Generated(hash = 1383278923)
    public ZhiHuHomeBean() {
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
    @Generated(hash = 1414087492)
    public List<ZhihuNewBean> getStories() {
        if (stories == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ZhihuNewBeanDao targetDao = daoSession.getZhihuNewBeanDao();
            List<ZhihuNewBean> storiesNew = targetDao
                    ._queryZhiHuHomeBean_Stories(date);
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
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 866994224)
    public List<ZhihuBanberBean> getTop_stories() {
        if (top_stories == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ZhihuBanberBeanDao targetDao = daoSession.getZhihuBanberBeanDao();
            List<ZhihuBanberBean> top_storiesNew = targetDao
                    ._queryZhiHuHomeBean_Top_stories(date);
            synchronized (this) {
                if (top_stories == null) {
                    top_stories = top_storiesNew;
                }
            }
        }
        return top_stories;
    }
    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 517496677)
    public synchronized void resetTop_stories() {
        top_stories = null;
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
    @Generated(hash = 1272431433)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getZhiHuHomeBeanDao() : null;
    }
    
}
