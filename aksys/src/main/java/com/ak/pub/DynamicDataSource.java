package com.ak.pub;

import java.lang.annotation.*;
import java.util.HashMap;
import java.util.Map;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/** 动态数据源部分包括：
 * 1、 数据源和动态数据源定义，在本类实现。一个名为dataSource的Bean即可被系统接受作为dataSource。注意要有@Primary
 *    使用DynamicDataSource dataSource()维护数据源列表
 * 2、DataSourceSwitcher，使用contextHolder来向系统存储数据源名，程序中使用它即可切换数据源。如DataSourceSwitcher.setDataSource("dataSource2");
 * 3、DynamicDataSource，继承AbstractRoutingDataSource，通过注册为 dataSource()成为系统数据源，动态取得数据源。
 * 
 * 
 * 
 * */
public class DynamicDataSource extends org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource {
    
   private Map<Object, Object> _targetDataSources = new HashMap<Object, Object>();
   
    
   @Override
   protected Object determineCurrentLookupKey() { 	
   return DataSourceSwitcher.getDataSource(); 
   }
   
   /** 设置数据源列表 这个方法不用了，应该使用 addTargetDataSource
    * public void setTargetDataSources(Map<Object, Object> targetDataSources) {
    *     this._targetDataSources = targetDataSources;
    *     super.setTargetDataSources(this._targetDataSources);
    *     afterPropertiesSet();
    * }
    * 增加一个数据源注册
    * 
    * 
    * @param key 
    * @param dataSource
    * */
   public void addTargetDataSource(String key, Object dataSource) {
       this._targetDataSources.put(key, dataSource);
       super.setTargetDataSources(this._targetDataSources);
       afterPropertiesSet();
   }
   
   /** 创建一个数据源实例
    * 
    * 
    * @param driverClassName 
    * @param url 
    * @param username 
    * @param password
    * */
   public DriverManagerDataSource createDataSource(String driverClassName, String url, String username, String password) {
   	DriverManagerDataSource dataSource = new DriverManagerDataSource();
       dataSource.setDriverClassName(driverClassName);
       dataSource.setUrl(url);
       dataSource.setUsername(username);
       dataSource.setPassword(password);
       return dataSource;
   }
   
   /** 查询数据源是否存在
    * 
    * 
    * @param source
    * */
   public boolean queryDataSource(String source) {	
   	return _targetDataSources.containsKey(source);
   }

}