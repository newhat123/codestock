package writecode;

/** bean��
 * 
 * 
 * 
 * @author hongten<br>
 * 
 * @date 2013-3-10 */
public class Bean {
    
   private String name;
    
   private String lowerName;
    
   private String beanUrl;
    
   private String beanDaoUrl;
    
   private String beanDaoImplUrl;
    
   private String beanServiceUrl;
    
   private String beanServiceImplUrl;
    
   private String beanDaoName;
    
   private String beanImplName;
    
   private String lowerDaoName;
   
    
   public String getLowerDaoName() {
   	 return lowerName+"Repository";
   }
   
    
   public String getBeanDaoName() {
   	return name+"Repository";
   }
   
    
   public String getBeanImplName() {
   	return name+"Service";
   }
   
    
   public String getName() {
   	return name;
   }
   
   /** @param name
    * */
   public void setName(String name) {
   	this.name = name;
   }
   
    
   public String getLowerName() {
   	return lowerName;
   }
   
   /** @param lowerName
    * */
   public void setLowerName(String lowerName) {
   	this.lowerName = lowerName;
   }
   
    
   public String getBeanUrl() {
   	return beanUrl;
   }
   
   /** @param beanUrl
    * */
   public void setBeanUrl(String beanUrl) {
   	this.beanUrl = beanUrl;
   }
   
    
   public String getBeanDaoUrl() {
   	return beanDaoUrl;
   }
   
   /** @param beanDaoUrl
    * */
   public void setBeanDaoUrl(String beanDaoUrl) {
   	this.beanDaoUrl = beanDaoUrl;
   }
   
    
   public String getBeanDaoImplUrl() {
   	return beanDaoImplUrl;
   }
   
   /** @param beanDaoImplUrl
    * */
   public void setBeanDaoImplUrl(String beanDaoImplUrl) {
   	this.beanDaoImplUrl = beanDaoImplUrl;
   }
   
    
   public String getBeanServiceUrl() {
   	return beanServiceUrl;
   }
   
   /** @param beanServiceUrl
    * */
   public void setBeanServiceUrl(String beanServiceUrl) {
   	this.beanServiceUrl = beanServiceUrl;
   }
   
    
   public String getBeanServiceImplUrl() {
   	return beanServiceImplUrl;
   }
   
   /** @param beanServiceImplUrl
    * */
   public void setBeanServiceImplUrl(String beanServiceImplUrl) {
   	this.beanServiceImplUrl = beanServiceImplUrl;
   }

}