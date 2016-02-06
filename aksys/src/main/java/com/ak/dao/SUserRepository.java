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
   
   //@Query("select u from SUser u where u.email=?1")
   //@Query(value="select u.did,u.org_did,u.bref,u.dob,u.email,u.hum_did,u.mobile,u.`password`,u.qq,r.fname from s_user u inner join s_role r on u.did=r.use_did where u.email=?1",nativeQuery = true)
   @Query(value="select * from s_user u where u.email=?1",nativeQuery = true)
   SUser test(String email);
   
   /** @param email 
    * @param password
    * */
   SUser findByEmailAndPassword(String email, String password);
   /** @param email
    * */
   SUser findUserByEmail(String email);

}