package com.ak.dao;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;  
import org.springframework.data.jpa.repository.Query;
import com.ak.entity.SDepart;

/**
 * @author <a href="mailto:sosowego@126.com">hanyouqing</a>
 * @date 2015-08-26
 *
 * @version 1.0
 */
public interface SDepartRepository extends JpaRepository<SDepart,Integer> {
public List<SDepart> findByFname(String fname);
}