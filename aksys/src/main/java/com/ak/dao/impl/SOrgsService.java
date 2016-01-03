package com.ak.dao.impl;
import com.ak.dao.SOrgsRepository;
import com.ak.entity.SOrgs;

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
 
@Service("sorgsService")
public class SOrgsService{

    @Autowired
	private SOrgsRepository sorgsRepository;
	
	public List<SOrgs> getAll() {
		List<SOrgs> list = sorgsRepository.findAll();
		return list;
	}
	
	public void deleteById(Integer id) {
		 sorgsRepository.delete(id);
	}
	
	public Page<SOrgs> getListWithPaging(int pages, int pageSize) {
		Pageable pageAble = new PageRequest(pages, pageSize);
		Page<SOrgs> page = sorgsRepository.findAll(pageAble);
		return page;
	}
	
	public void updateById(SOrgs sorgs) {
		sorgsRepository.save(sorgs);
	}
	
	public SOrgs saveSOrgs(SOrgs sorgs){
		try {
		  return sorgsRepository.save(sorgs);
			
		} catch (Exception ex) {
			System.out.println("执行出错哦：" + ex.getMessage());		
			return null;	
		}
	}
	
	public Page<SOrgs> getListWithPaging(Pageable pageAble) {
		Page<SOrgs> page = sorgsRepository.findAll(pageAble);
		return page;
	}
	
	public SOrgs getById(Integer id) {
		return sorgsRepository.findOne(id);

	}

}