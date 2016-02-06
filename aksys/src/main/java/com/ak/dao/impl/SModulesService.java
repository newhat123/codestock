package com.ak.dao.impl;

import com.ak.dao.SModulesRepository;
import com.ak.entity.SModules;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author <a href="mailto:sosowego@126.com">hanyouqing</a>
 * @date 2016-01-03
 *
 * @version 1.0
 */

@Service("smodulesService")
public class SModulesService {

	@Autowired
	private SModulesRepository smodulesRepository;

	public List<SModules> getAll() {
		List<SModules> list = smodulesRepository.findAll();
		return list;
	}

	public void deleteById(Integer id) {
		smodulesRepository.delete(id);
	}

	public Page<SModules> getListWithPaging(int pages, int pageSize) {
		Pageable pageAble = new PageRequest(pages, pageSize);
		Page<SModules> page = smodulesRepository.findAll(pageAble);
		return page;
	}

	public void updateById(SModules smodules) {
		smodulesRepository.save(smodules);
	}

	public SModules saveSModules(SModules smodules) {
		try {
			return smodulesRepository.save(smodules);

		} catch (Exception ex) {
			System.out.println("执行出错哦：" + ex.getMessage());
			return null;
		}
	}

	public Page<SModules> getListWithPaging(Pageable pageAble) {
		Page<SModules> page = smodulesRepository.findAll(pageAble);
		return page;
	}

	public SModules getById(Integer id) {
		return smodulesRepository.findOne(id);

	}
	
	public SModules getByByFcode(String fcode) {
		return smodulesRepository.getByFcode(fcode);

	}

	/*
	 * 根据用户id获取用户权限列表 考虑了用户有多个角色 合并了重复权限 增加分组的权限
	 */
	public List<SModules> powerList(Integer userid) {

		List<SModules> l = smodulesRepository.powerList(userid);
		Map<Integer, String> m = new HashMap<Integer, String>();
		for (Integer i = 0; i < l.size(); i++) {
			if (!m.containsValue(l.get(i).getParent())) {
				m.put(i, l.get(i).getParent());
			}
		}
		
		Integer i=0;
		for(Map.Entry<Integer, String> entry:m.entrySet()){
			l.add(entry.getKey()+i, smodulesRepository.getByFcode(entry.getValue()));
			i++;
		}
		
		return l;
	}
	
	//获取未登录用户的权限，issys传递true
	public List<SModules> sysPowerList(){
		return smodulesRepository.findByIssys(true);
	}

}