package com.ak.pub;

import java.lang.annotation.*;

/** 动态数据源部分包括：
 * 1、 数据源和动态数据源定义，在本类实现。一个名为dataSource的Bean即可被系统接受作为dataSource。注意要有@Primary
 *    使用DynamicDataSource dataSource()维护数据源列表
 * 2、DataSourceSwitcher，使用contextHolder来向系统存储数据源名，程序中使用它即可切换数据源。如DataSourceSwitcher.setDataSource("dataSource2");
 * 3、DynamicDataSource，继承AbstractRoutingDataSource，通过注册为 dataSource()成为系统数据源，动态取得数据源。
 * 
 * 
 * 
 * */
public class DataSourceSwitcher {
    
   @SuppressWarnings("rawtypes")
   private static final ThreadLocal contextHolder = new ThreadLocal();
   
   /** @param dataSource
    * */
   @SuppressWarnings("unchecked")
   public static void setDataSource(String dataSource) {
   	contextHolder.set(dataSource);
   }
   
    
   public static String getDataSource() {
   	return (String) contextHolder.get();
   }
   
    
   public static void clearDataSource() {
   contextHolder.remove();
   }

}