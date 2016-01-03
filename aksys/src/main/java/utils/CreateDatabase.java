package utils;

import java.sql.SQLException;
import javax.persistence.EntityManagerFactory;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import com.mysql.jdbc.Connection;
import com.ak.pub.Appctx;

 
public class CreateDatabase {
    
   public CreateDatabase() {
   	// TODO Auto-generated constructor stub
   }
   
   /** @exception SQLException
    * */
   public static void create() throws SQLException {
   	
   	DriverManagerDataSource dm=(DriverManagerDataSource)Appctx.ctx.getBean("dataSource1");
   	java.sql.Connection cnnConnection= dm.getConnection();
   	
   	cnnConnection.createStatement().execute("create database codedb");
   	
   	
   }

}