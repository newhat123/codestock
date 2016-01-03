package com.ak.pub;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

 
public class Appctx {
   /** 注意，这里没有final
    * 
    * 
    * */
   public static ApplicationContext ctx = null;
   
   /** @param string
    * */
   public static Object getObject(String string) {
   	return ctx.getBean(string);
   }

}