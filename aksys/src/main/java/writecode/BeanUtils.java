package writecode;

import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

/** @author hongten<br>
 * 
 * @date 2013-3-10 */
public class BeanUtils {
   /** public static final String BEAN_SERVICE_TEMPLATE_VM_PATH = "src/main/java/vms/beanService.vm";
    * public static final String BEAN_SERVICE_IMPL_TEMPLATE_VM_PATH = "src/main/java/vms/beanServiceImpl.vm";
    *  �ļ� ��ַ
    *  private static final String BEAN_PATH = "com/b510/base/bean";
    * 
    * 
    * */
   private static final String DAO_PATH = "com/ak/dao";
    
   private static final String DAO_IMPL_PATH = "com/ak/dao/impl";
   /** private static final String SERVICE_PATH = "com/b510/base/service";
    * private static final String SERVICE_IMPL_PATH = "com/b510/base/service/impl";
    *  ����
    * 
    * 
    * */
   private static final String BEAN_URL = "com.ak.entity";
    
   private static final String DAO_URL = "com.ak.dao";
    
   private static final String DAO_IMPL_URL = "com.ak.dao.impl";
    
   private static Bean bean = new Bean();
	private static Annotation annotation = new Annotation();
   
    
   public static final String BEAN_DAO_TEMPLATE_VM_PATH = "src/main/java/writecode/vms/beanDao.vm";
    
   public static final String BEAN_DAO_IMPL_TEMPLATE_VM_PATH = "src/main/java/writecode/vms/beanDaoImpl.vm";
   
   /** ��ʼ��bean��ע��
    * 
    * 
    * 
    * @param c
    * */
   public void init(Class c) {
   	if (c != null) {
   		String cName = c.getName();
   		System.out.println(getLastChar(cName));
   		bean.setName(getLastChar(cName));
   		bean.setBeanUrl(cName);
   		bean.setLowerName(getLowercaseChar(getLastChar(cName)));
   
   		annotation.setAuthorName("hanyouqing");
   		annotation.setAuthorMail("sosowego@126.com");
   		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
   				"yyyy-MM-dd");
   		annotation.setDate(simpleDateFormat.format(new Date()));
   		annotation.setVersion("1.0");
   	}
   }
   
   /** ����bean��Dao<br>
    * 
    * 
    * 
    * @param c
    * @throws Exception
    * */
   public void createBeanDao(Class c) throws Exception {
   	String cName = c.getName();
   	String path = System.getProperty("user.dir") + "/src/main/java/" + DAO_PATH + "/";
   	File filePath = new File(path);
   	createFilePath(filePath);
   
   	String fileName = path + getLastChar(cName) + "Repository.java";
   	File file = new File(fileName);
   	FileWriter fw = new FileWriter(file);
   
   	bean.setBeanDaoUrl(DAO_URL);
   
   	fw.write(createCode(BEAN_DAO_TEMPLATE_VM_PATH, bean, annotation));
   	fw.flush();
   	fw.close();
   	showInfo(fileName);
   }
   
   /** ����bean��Dao��ʵ��<br>
    * 
    * 
    * 
    * @param c
    * @throws Exception
    * */
   public void createBeanDaoImpl(Class c) throws Exception {
   	String cName = c.getName();
   	String path = System.getProperty("user.dir") + "/src/main/java/" + DAO_IMPL_PATH
   			+ "/";
   	File filePath = new File(path);
   	createFilePath(filePath);
   
   	String fileName = path + getLastChar(cName) + "Service.java";
   	File file = new File(fileName);
   	FileWriter fw = new FileWriter(file);
   
   	bean.setBeanDaoUrl(DAO_URL);
   	bean.setBeanDaoImplUrl(DAO_IMPL_URL);
   
   	fw.write(createCode(BEAN_DAO_IMPL_TEMPLATE_VM_PATH, bean, annotation));
   	fw.flush();
   	fw.close();
   	showInfo(fileName);
   }
   
   /** 	 * ����bean��Service<br>
    * 	 *
    * 	 * @param c
    * 	 * @throws Exception
    * 
    * 
    * 	 * public void createBeanService(Class c) throws Exception { String cName =
    * 	 * c.getName(); String path = System.getProperty("user.dir") + "/src/" +
    * 	 * SERVICE_PATH + "/"; File filePath = new File(path);
    * 	 * createFilePath(filePath);
    * 	 *
    * 	 * String fileName = path + getLastChar(cName) + "Service.java"; File file =
    * 	 * new File(fileName); FileWriter fw = new FileWriter(file);
    * 	 *
    * 	 * bean.setBeanServiceUrl(SERVICE_URL);
    * 	 *
    * 	 * fw.write(createCode(BEAN_SERVICE_TEMPLATE_VM_PATH,bean,annotation));
    * 	 * fw.flush(); fw.close(); showInfo(fileName); }
    * 
    * 
    * 	 * ����bean��Service��ʵ��<br>
    * 	 *
    * 	 * @param c
    * 	 * @throws Exception
    * 
    * 
    * 	 * public void createBeanServiceImpl(Class c) throws Exception { String
    * 	 * cName = c.getName(); String path = System.getProperty("user.dir") +
    * 	 * "/src/" + SERVICE_IMPL_PATH + "/"; File filePath = new File(path);
    * 	 * createFilePath(filePath);
    * 	 *
    * 	 * String fileName = path + getLastChar(cName) + "ServiceImpl.java"; File
    * 	 * file = new File(fileName); FileWriter fw = new FileWriter(file);
    * 	 *
    * 	 * bean.setBeanDaoUrl(DAO_URL); bean.setBeanServiceUrl(SERVICE_URL);
    * 	 * bean.setBeanServiceImplUrl(SERVICE_IMPL_URL);
    * 	 *
    * 	 * fw.write(createCode(BEAN_SERVICE_IMPL_TEMPLATE_VM_PATH,bean,annotation));
    * 	 * fw.flush(); fw.close(); showInfo(fileName); }
    * 
    * ���ģ����ɴ���
    * 
    * 
    * 
    * @param fileVMPath ģ��·��
    * @param bean Ŀ��bean
    * @param annotation ע��
    * @return
    * @throws Exception
    * */
   public String createCode(String fileVMPath, Bean bean, Annotation annotation) throws Exception {
   	VelocityEngine velocityEngine = new VelocityEngine();
   	velocityEngine.setProperty("input.encoding", "UTF-8");
   	velocityEngine.setProperty("output.encoding", "UTF-8");
   	velocityEngine.init();
   	Template template = velocityEngine.getTemplate(fileVMPath);
   	VelocityContext velocityContext = new VelocityContext();
   	velocityContext.put("bean", bean);
   	velocityContext.put("annotation", annotation);
   	StringWriter stringWriter = new StringWriter();
   	template.merge(velocityContext, stringWriter);
   	return stringWriter.toString();
   }
   
   /** �����ļ�
    * 
    * 
    * 
    * @param file
    * */
   public void createFilePath(File file) {
   	if (!file.exists()) {
   		System.out.println("����[" + file.getAbsolutePath() + "]�����"
   				+ file.mkdirs());
   	} else {
   		System.out.println("����Ŀ¼��" + file.getAbsolutePath());
   	}
   }
   
   /** ��ȡ·����������ַ�<br>
    * �磺<br>
    * <code>str = "com.b510.base.bean.User"</code><br>
    * <code> return "User";<code>
    * 
    * 
    * 
    * @param str
    * @return
    * 
    * */
   public String getLastChar(String str) {
   	if ((str != null) && (str.length() > 0)) {
   		int dot = str.lastIndexOf('.');
   		if ((dot > -1) && (dot < (str.length() - 1))) {
   			return str.substring(dot + 1);
   		}
   	}
   	return str;
   }
   
   /** �ѵ�һ����ĸ��ΪСд<br>
    * �磺<br>
    * <code>str = "UserDao";</code><br>
    * <code>return "userDao";</code>
    * 
    * 
    * 
    * @param str
    * @return
    * 
    * */
   public String getLowercaseChar(String str) {
   	return str.substring(0, 2).toLowerCase() + str.substring(2);
   }
   
   /** ��ʾ��Ϣ
    * 
    * 
    * 
    * @param info
    * */
   public void showInfo(String info) {
   	System.out.println("�����ļ���" + info + "�ɹ���");
   }
   
   /** ��ȡϵͳʱ��
    * 
    * 
    * 
    * @return
    * 
    * */
   public static String getDate() {
   	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
   	return simpleDateFormat.format(new Date());
   }

}