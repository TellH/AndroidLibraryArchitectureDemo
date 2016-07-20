package com.tellh.androidlibraryarchitecturedemo.greendao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.tellh.greendao.DaoMaster;
import com.tellh.greendao.DaoSession;
import com.tellh.greendao.Person;
import com.tellh.greendao.PersonDao;

import de.greenrobot.dao.query.QueryBuilder;

public class DBHelper {
    private volatile static DBHelper INSTANCE = null;
    private final SQLiteDatabase database;

    public static DBHelper getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (DBHelper.class) {
                if (INSTANCE != null)
                    INSTANCE = new DBHelper(context);
            }
        }
        return INSTANCE;
    }

    private static final String DB_NAME = "person.db";
    private DaoSession daoSession;

    private DBHelper(Context context) {
        //通过 DaoMaster 的内部类 DevOpenHelper，可以得到一个便利的SQLiteOpenHelper 对象
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, DB_NAME, null);
        database = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(database);
        daoSession = daoMaster.newSession();
    }

    public SQLiteDatabase getDatabase() {
        return database;
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    //增
    public void addPerson(Person person) {
        getDaoSession().getPersonDao().insert(new Person((long) 1, "abc", 20, "cf"));
    }

    //查
    public Person getPerson(long id) {
        return getDaoSession().getPersonDao().load(id);

    }

    //删
    public void deletePerson(long id) {
        //or deleteByKey(id);
        getDaoSession().getPersonDao().delete(getPerson(id));
    }
    //改
    public void updatePerson(Person person){
        getDaoSession().getPersonDao().update(person);
    }
    //多条件查询
//    gt  大于 greater than
//    ge  等于或大于  equal or greater
    public void queryPerson(String name,String card,int youngestAge,int OldestAge){
        QueryBuilder qb = getDaoSession().getPersonDao().queryBuilder();
        qb.where(PersonDao.Properties.Name.eq(name),
                qb.or(PersonDao.Properties.Card.eq(card), PersonDao.Properties.Age.between(youngestAge,OldestAge)),
                PersonDao.Properties.Age.ge(OldestAge));
    }

}