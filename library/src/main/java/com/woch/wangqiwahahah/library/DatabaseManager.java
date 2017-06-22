package com.woch.wangqiwahahah.library;

import android.content.Context;

import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.assit.QueryBuilder;
import com.litesuits.orm.db.assit.WhereBuilder;
import com.litesuits.orm.db.model.ColumnsValue;
import com.litesuits.orm.db.model.ConflictAlgorithm;

import java.util.HashMap;
import java.util.List;

/**
 * Created by a5yearge on 2016/12/8.
 */

public class DatabaseManager {

    private static LiteOrm liteOrm;
    private static DatabaseManager ourInstance;
    private static FunctionConfig mFunctionConfig;
    private static Context mContext;

    private DatabaseManager() {
        liteOrm = LiteOrm.newCascadeInstance(mContext.getApplicationContext(), mFunctionConfig.getDB_NAME()) ;
        liteOrm.setDebugged(mFunctionConfig.isShow_log());
    }

    public static DatabaseManager getInstance(FunctionConfig functionConfig, Context context) {

        if (ourInstance == null){

            synchronized (DatabaseManager.class){

                if (ourInstance == null){

                    mFunctionConfig = functionConfig;
                    mContext = context;
                    ourInstance = new DatabaseManager();

                }

            }
        }
        return ourInstance;
    }

    public LiteOrm getLiteOrm(){
        return liteOrm;
    }

    /**
     * 插入一条记录
     * @param t
     */
    public <T> long insert(T t) {
        return liteOrm.save(t);
    }

    /**
     * 插入所有记录
     * @param list
     */
    public <T> void insertAll(List<T> list) {
        liteOrm.save(list);
    }

    /**
     * 查询所有
     * @param cla
     * @return
     */
    public <T> List<T> getQueryAll(Class<T> cla) {
        List<T> list = liteOrm.query(cla);
        //liteOrm.close();
        return list;
    }

    /**
     * 查询  某字段 等于 Value的值
     * @param cla
     * @param field
     * @param value
     * @return
     */
    public <T> List<T> getQueryByWhere(Class<T> cla, String field, String value) {

        return liteOrm.<T>query(new QueryBuilder(cla).whereEquals(field, value));
        //return null;
    }

    /**
     * 查询  某字段 等于 Value的值 并且等于 value的值
     * @param cla
     * @param field
     * @param value
     * @return
     */
    public <T> List<T> getQueryByWhereValueAndtValue(Class<T> cla, String field, String value, String field1, String value1) {

        return liteOrm.<T>query(new QueryBuilder(cla).whereEquals(field, value).whereAppendAnd().whereEquals(field1, value1));
        //return null;
    }

    /**
     * 查询  某列
     * @param cla
     * @param field
     * @return
     */
    public <T> List<T> getQueryByWhere(Class<T> cla, String field) {

        return liteOrm.<T>query(new QueryBuilder(cla).columns(new String[]{field}).distinct(true));
        //return null;
    }

    /**
     * 查询  某列
     * @param cla
     * @param field
     * @return
     */
    public <T> List<T> getQueryByWhereGroup(Class<T> cla, String field, String field1) {

        return liteOrm.<T>query(new QueryBuilder(cla).columns(new String[]{field, field1}).distinct(true).groupBy(field));
        //return null;
    }

    /**
     * 查询  某字段 等于 Value的值  可以指定从1-20，就是分页
     * @param cla
     * @param field
     * @param value
     * @param start
     * @param length
     * @return
     */
    public <T> List<T> getQueryByWhereLength(Class<T> cla, String field, String value, int start, int length) {
        //return liteOrm.<T>query(new QueryBuilder(cla).where(field + "=?", value).limit(start, length));
        return  null;
    }

    /**
     * 查询  某列
     * @param cla
     * @param field
     * @return
     */
    public <T> long getQueryByWhereCount(Class<T> cla, String field) {

        return liteOrm.queryCount(new QueryBuilder(cla).columns(new String[]{field}));
        //return liteOrm.<T>query(new QueryBuilder(cla).columns(new String[]{field, field1}).distinct(true).groupBy(field));
        //return null;
    }

    /**
     * 删除一个数据
     * @param t
     * @param <T>
     */
    public <T> void delete( T t){
        liteOrm.delete( t ) ;
    }

    /**
     * 删除一个表
     * @param cla
     * @param <T>
     */
    public <T> void delete( Class<T> cla ){
        liteOrm.delete( cla ) ;
    }
//
//    /**
//     * 删除指定数据
//     * @param cla
//     * @param <T>
//     */
//    public <T> void delete( Class<T> cla ){
//        liteOrm.delete(cla) ;
//    }

    /**
     * 删除集合中的数据
     * @param list
     * @param <T>
     */
    public <T> void deleteList( List<T> list ){
        liteOrm.delete( list ) ;
    }

    /**
     * 删除数据库
     */
    public void deleteDatabase(){
        liteOrm.deleteDatabase() ;
    }

    /**
     * 更新一个元素
     * @param t
     * @param <T>
     */
    public <T> void update(T t){
        liteOrm.update(t);
    }


    /**
     * 全部更新
     * @param cla
     * @param field  不空的列名
     * @param field1 更新的列名
     * @param value  更新的值
     * @param <T>
     */
    public <T> void updateAll(Class<T> cla, String field, String field1, String value){

        HashMap<String, Object> bookIdMap = new HashMap<String, Object>();
        bookIdMap.put(field1, value);
        //liteOrm.
        liteOrm.update(new WhereBuilder(cla).noEquals(field, "-1"), new ColumnsValue(bookIdMap), ConflictAlgorithm.Fail);
        //liteOrm.update(new QueryBuilder(cla).whereNoEquals(field, "-1"), new ColumnsValue(bookIdMap), ConflictAlgorithm.Fail);

    }

    /**
     * 全部更新
     * @param cla
     * @param field  不空的列名
     * @param value  更新的值
     * @param field1 更新的列名
     * @param value1  更新的值
     * @param <T>
     */
    public <T> void updateAllOne(Class<T> cla, String field, String value, String field1, String value1){

        HashMap<String, Object> bookIdMap = new HashMap<String, Object>();
        bookIdMap.put(field1, value1);
        //liteOrm.
        liteOrm.update(new WhereBuilder(cla).equals(field, value), new ColumnsValue(bookIdMap), ConflictAlgorithm.Fail);
        //liteOrm.update(new QueryBuilder(cla).whereNoEquals(field, "-1"), new ColumnsValue(bookIdMap), ConflictAlgorithm.Fail);

    }

}
