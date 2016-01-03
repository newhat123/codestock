package com.ak.dao;
import org.springframework.data.jpa.repository.JpaRepository;  
import org.springframework.data.jpa.repository.Query;
import com.ak.entity.SRole;

/**
 * @author <a href="mailto:sosowego@126.com">hanyouqing</a>
 * @date 2015-08-26
 *
 * @version 1.0
 */
public interface SRoleRepository extends JpaRepository<SRole,Integer> {

}