package com.ak.dao.impl;
import com.ak.dao.SDepartRepository;
import com.ak.entity.SDepart;

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
 
@Service("sdepartService")
public class SDepartService{

    @Autowired
	private SDepartRepository sdepartRepository;
	
	public List<SDepart> getAll() {
		List<SDepart> list = sdepartRepository.findAll();
		return list;
	}
	
	public void deleteById(Integer id) {
		 sdepartRepository.delete(id);
	}
	
	public Page<SDepart> getListWithPaging(int pages, int pageSize) {
		Pageable pageAble = new PageRequest(pages, pageSize);
		Page<SDepart> page = sdepartRepository.findAll(pageAble);
		return page;
	}
	
	public void updateById(SDepart sdepart) {
		sdepartRepository.save(sdepart);
	}
	
	public SDepart saveSDepart(SDepart sdepart){
		try {
		  return sdepartRepository.save(sdepart);
			
		} catch (Exception ex) {
			System.out.println("执行出错哦：" + ex.getMessage());		
			return null;	
		}
	}
	
	public Page<SDepart> getListWithPaging(Pageable pageAble) {
		Page<SDepart> page = sdepartRepository.findAll(pageAble);
		return page;
	}
	
	public SDepart getById(Integer id) {
		return sdepartRepository.findOne(id);

	}
	
	public List<SDepart> findByFname(String fname){
		return sdepartRepository.findByFname(fname);
	}

}