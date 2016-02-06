package com.ak.dao;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;  
import org.springframework.data.jpa.repository.Query;
import com.ak.entity.SModules;

/**
 * @author <a href="mailto:sosowego@126.com">hanyouqing</a>
 * @date 2016-01-03
 *
 * @version 1.0
 */
public interface SModulesRepository extends JpaRepository<SModules,Integer> {
	
	//不包括系统菜单。系统菜单是无权限用户的菜单。
	@Query(value="select m.* from s_modules m inner join s_rolepower r on m.fcode=r.mod_did INNER JOIN s_role rl on rl.rol_did=r.rol_did WHERE rl.use_did=?1 and issys=0 order by m.fcode",nativeQuery = true)
	List<SModules> powerList(Integer userid);
	
	//获取未登录用户的权限，issys传递true
	List<SModules> findByIssys(Boolean issys);
	
	SModules getByFcode(String fcode);
}