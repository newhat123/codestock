package utils;

import java.lang.reflect.Field;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import com.ak.pub.Appctx;

/** @author devstation
 * 动态创建Spring Bean，并注册
 * 本来用来创建数据源，后被DynamicDataSource的功能取代。
 * 可能的原因是没有调用DynamicDataSource的afterPropertiesSet()方法
 * 暂时不再测试这个推测。
 * 
 * */
public class GenerateDatasource {
    
   public GenerateDatasource() {
   	// TODO Auto-generated constructor stub
   }
   
    
   public static void generate() {
   		 //将applicationContext转换为ConfigurableApplicationContext
   		 ConfigurableApplicationContext configurableApplicationContext =
   		 (ConfigurableApplicationContext)Appctx.ctx;
   		
   		 // 获取bean工厂并转换为DefaultListableBeanFactory
   		 DefaultListableBeanFactory defaultListableBeanFactory =
   		 (DefaultListableBeanFactory) configurableApplicationContext
   		 .getBeanFactory();
   		
   		 // 通过BeanDefinitionBuilder创建bean定义
   		 BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder
   		 .genericBeanDefinition(DriverManagerDataSource.class);
   		 
   		 beanDefinitionBuilder.addPropertyValue("driverClassName", "com.mysql.jdbc.Driver");
   		 beanDefinitionBuilder.addPropertyValue("url", "jdbc:mysql://localhost:3306/dynamic");
   		 beanDefinitionBuilder.addPropertyValue("username", "root");
   		 beanDefinitionBuilder.addPropertyValue("password", "wsdgz");
   		 
   		 // 设置属性userAcctDAO,此属性引用已经定义的bean:userAcctDAO
   		 //beanDefinitionBuilder
   		 //.addPropertyReference("volunteer1", "Volunteer");
   		
   		 // 注册bean
   		 defaultListableBeanFactory.registerBeanDefinition("dataSource3",
   		 beanDefinitionBuilder.getRawBeanDefinition());
   		
   		 //DynamicDataSource dy= (DynamicDataSource)Appctx.ctx.getBean("dataSource");
   		 
   		
   		 
   		 
   		 Field f;
   	        try {
   	            f = Class.forName("org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource").getDeclaredField("targetDataSources");
   	            f.setAccessible(true);  
   	            //System.out.println(f.get(Appctx.getObject("dataSource")));
   	            Map<Object, Object> map=(Map<Object, Object>)f.get(Appctx.getObject("dataSource"));
   	            
   	            map.put("dataSource3", Appctx.getObject("dataSource3"));
   	            
   	            
   //	            Set set = map.entrySet();         
   //	            Iterator i = set.iterator();         
   //	            while(i.hasNext()){      
   //	                 Map.Entry<Object, Object> entry1=(Map.Entry<Object, Object>)i.next();    
   //	                 System.out.println(entry1.getKey()+"=="+entry1.getValue());    
   //	            }   
   	            
   	        } catch (Exception e) {
   	            e.printStackTrace();
   	        }		
   				
   			
   		 
   		 
   	}

}