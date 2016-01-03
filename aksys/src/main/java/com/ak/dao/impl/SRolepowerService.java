package com.ak.dao.impl;
import com.ak.dao.SRolepowerRepository;
import com.ak.entity.SRolepower;

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
 
@Service("srolepowerService")
public class SRolepowerService{

    @Autowired
	private SRolepowerRepository srolepowerRepository;
	
	public List<SRolepower> getAll() {
		List<SRolepower> list = srolepowerRepository.findAll();
		return list;
	}
	
	public void deleteById(Integer id) {
		 srolepowerRepository.delete(id);
	}
	
	public Page<SRolepower> getListWithPaging(int pages, int pageSize) {
		Pageable pageAble = new PageRequest(pages, pageSize);
		Page<SRolepower> page = srolepowerRepository.findAll(pageAble);
		return page;
	}
	
	public void updateById(SRolepower srolepower) {
		srolepowerRepository.save(srolepower);
	}
	
	public SRolepower saveSRolepower(SRolepower srolepower){
		try {
		  return srolepowerRepository.save(srolepower);
			
		} catch (Exception ex) {
			System.out.println("执行出错哦：" + ex.getMessage());		
			return null;	
		}
	}
	
	public Page<SRolepower> getListWithPaging(Pageable pageAble) {
		Page<SRolepower> page = srolepowerRepository.findAll(pageAble);
		return page;
	}
	
	public SRolepower getById(Integer id) {
		return srolepowerRepository.findOne(id);

	}

}