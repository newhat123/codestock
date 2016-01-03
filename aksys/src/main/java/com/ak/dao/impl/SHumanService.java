package com.ak.dao.impl;
import com.ak.dao.SHumanRepository;
import com.ak.entity.SHuman;

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
 
@Service("shumanService")
public class SHumanService{

    @Autowired
	private SHumanRepository shumanRepository;
	
	public List<SHuman> getAll() {
		List<SHuman> list = shumanRepository.findAll();
		return list;
	}
	
	public void deleteById(Integer id) {
		 shumanRepository.delete(id);
	}
	
	public Page<SHuman> getListWithPaging(int pages, int pageSize) {
		Pageable pageAble = new PageRequest(pages, pageSize);
		Page<SHuman> page = shumanRepository.findAll(pageAble);
		return page;
	}
	
	public void updateById(SHuman shuman) {
		shumanRepository.save(shuman);
	}
	
	public SHuman saveSHuman(SHuman shuman){
		try {
		  return shumanRepository.save(shuman);
			
		} catch (Exception ex) {
			System.out.println("执行出错哦：" + ex.getMessage());		
			return null;	
		}
	}
	
	public Page<SHuman> getListWithPaging(Pageable pageAble) {
		Page<SHuman> page = shumanRepository.findAll(pageAble);
		return page;
	}
	
	public SHuman getById(Integer id) {
		return shumanRepository.findOne(id);

	}

}