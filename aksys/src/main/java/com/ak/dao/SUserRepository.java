package com.ak.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.ak.entity.SUser;

/** @author <a href="mailto:sosowego@126.com">hanyouqing</a>
 * @version 1.0
 * 
 * 
 * @date 2015-07-31 */
public interface SUserRepository extends JpaRepository<SUser,Integer> {
   /** @param email 
    * @param password
    * */
   @Query("select u from SUser u where u.email=?1 and u.password=?2")
   SUser login(String email, String password);
   /** @param email 
    * @param password
    * */
   SUser findByEmailAndPassword(String email, String password);
   /** @param email
    * */
   SUser findUserByEmail(String email);

}