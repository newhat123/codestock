package com.ak.dao.impl;

import com.ak.dao.SUserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ak.entity.SUser;

/** @author <a href="mailto:sosowego@126.com">hanyouqing</a>
 * @version 1.0
 * 
 * 
 * @date 2015-07-31 */
@Service("suserService")
public class SUserService {
    

   @Autowired
   private SUserRepository suserRepository;
   
    
   public List<SUser> findAll() {
   	return suserRepository.findAll();
   }
   
   /** @param user
    * */
   public SUser create(SUser user) {
   	return suserRepository.save(user);
   }
   
   /** @param id
    * */
   public SUser findUserById(int id) {
   	return suserRepository.findOne(id);
   }
   
   /** @param email 
    * @param password
    * */
   public SUser login(String email, String password) {
   	return suserRepository.findByEmailAndPassword(email, password);
   }
   
   /** @param user
    * */
   public SUser update(SUser user) {
   	return suserRepository.save(user);
   }
   
   /** @param id
    * */
   public void deleteUser(int id) {
   	suserRepository.delete(id);
   }
   
   /** @param email
    * */
   public SUser findUserByEmail(String email) {
   	return suserRepository.findUserByEmail(email);
   }

}