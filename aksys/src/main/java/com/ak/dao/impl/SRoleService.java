package com.ak.dao.impl;
import com.ak.dao.SRoleRepository;
import com.ak.entity.SRole;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author <a href="mailto:sosowego@126.com">hanyouqing</a>
 * @date 2015-08-26
 *
 * @version 1.0
 */
 
@Service("sroleService")
public class SRoleService{

    @Autowired
	private SRoleRepository sroleRepository;
	
	public List<SRole> getAll() {
		List<SRole> list = sroleRepository.findAll();
		return list;
	}
	
	public void deleteById(Integer id) {
		 sroleRepository.delete(id);
	}
	
	public Page<SRole> getListWithPaging(int pages, int pageSize) {
		Pageable pageAble = new PageRequest(pages, pageSize);
		Page<SRole> page = sroleRepository.findAll(pageAble);
		return page;
	}
	
	public void updateById(SRole srole) {
		sroleRepository.save(srole);
	}
	
	public SRole saveSRole(SRole srole){
		try {
		  return sroleRepository.save(srole);
			
		} catch (Exception ex) {
			System.out.println("执行出错哦：" + ex.getMessage());		
			return null;	
		}
	}
	
	public Page<SRole> getListWithPaging(Pageable pageAble) {
		Page<SRole> page = sroleRepository.findAll(pageAble);
		return page;
	}
	
	public SRole getById(Integer id) {
		return sroleRepository.findOne(id);

	}

}