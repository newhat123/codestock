package writecode;

import java.lang.annotation.*;
import java.util.ArrayList;
import java.util.List;

import com.ak.entity.ILoginfo;
import com.ak.entity.SDepart;
import com.ak.entity.SHuman;
import com.ak.entity.SModules;
import com.ak.entity.SOrgs;
import com.ak.entity.SRoldef;
import com.ak.entity.SRole;
import com.ak.entity.SRolepower;

/** import springidol.entity.*;
 * 
 * 
 * */
public class BeanUtilsTest {
   /** @param args
    * @exception Exception
    * */
   public static void main(String[] args) throws Exception {
   	BeanUtilsTest beanUtilTest = new BeanUtilsTest();
   	
   
   	List<Class> list = new ArrayList<Class>();
   
   	list.add(SModules.class);   	
   
   	for (Class c : list) {			
   		BeanUtils beanUtils = new BeanUtils();
   		beanUtilTest.beanTool(beanUtils,c);
   	}
   
   }
   
   /** @param beanUtils 
    * @param c
    * @exception Exception
    * */
   @SuppressWarnings("unchecked")
   public void beanTool(BeanUtils beanUtils, Class c) throws Exception {
   	beanUtils.init(c);
   	beanUtils.createBeanDao(c);
   	beanUtils.createBeanDaoImpl(c);
   	/*
   	 * beanUtils.createBeanService(c); beanUtils.createBeanServiceImpl(c);
   	 */
   
   
   }

}