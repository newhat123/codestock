package com.ak.dao.impl;
import com.ak.dao.SRoldefRepository;
import com.ak.entity.SRoldef;

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
 
@Service("sroldefService")
public class SRoldefService{

    @Autowired
	private SRoldefRepository sroldefRepository;
	
	public List<SRoldef> getAll() {
		List<SRoldef> list = sroldefRepository.findAll();
		return list;
	}
	
	public void deleteById(Integer id) {
		 sroldefRepository.delete(id);
	}
	
	public Page<SRoldef> getListWithPaging(int pages, int pageSize) {
		Pageable pageAble = new PageRequest(pages, pageSize);
		Page<SRoldef> page = sroldefRepository.findAll(pageAble);
		return page;
	}
	
	public void updateById(SRoldef sroldef) {
		sroldefRepository.save(sroldef);
	}
	
	public SRoldef saveSRoldef(SRoldef sroldef){
		try {
		  return sroldefRepository.save(sroldef);
			
		} catch (Exception ex) {
			System.out.println("执行出错哦：" + ex.getMessage());		
			return null;	
		}
	}
	
	public Page<SRoldef> getListWithPaging(Pageable pageAble) {
		Page<SRoldef> page = sroldefRepository.findAll(pageAble);
		return page;
	}
	
	public SRoldef getById(Integer id) {
		return sroldefRepository.findOne(id);

	}

}