package com.ak.dao.impl;
import com.ak.dao.ILoginfoRepository;
import com.ak.entity.ILoginfo;

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
 
@Service("iloginfoService")
public class ILoginfoService{

    @Autowired
	private ILoginfoRepository iloginfoRepository;
	
	public List<ILoginfo> getAll() {
		List<ILoginfo> list = iloginfoRepository.findAll();
		return list;
	}
	
	public void deleteById(Integer id) {
		 iloginfoRepository.delete(id);
	}
	
	public Page<ILoginfo> getListWithPaging(int pages, int pageSize) {
		Pageable pageAble = new PageRequest(pages, pageSize);
		Page<ILoginfo> page = iloginfoRepository.findAll(pageAble);
		return page;
	}
	
	public void updateById(ILoginfo iloginfo) {
		iloginfoRepository.save(iloginfo);
	}
	
	public ILoginfo saveILoginfo(ILoginfo iloginfo){
		try {
		  return iloginfoRepository.save(iloginfo);
			
		} catch (Exception ex) {
			System.out.println("执行出错哦：" + ex.getMessage());		
			return null;	
		}
	}
	
	public Page<ILoginfo> getListWithPaging(Pageable pageAble) {
		Page<ILoginfo> page = iloginfoRepository.findAll(pageAble);
		return page;
	}
	
	public ILoginfo getById(Integer id) {
		return iloginfoRepository.findOne(id);

	}

}