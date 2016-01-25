package com.ak.dao.impl;

import com.ak.dao.ISellistRepository;
import com.ak.entity.ISellist;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author <a href="mailto:sosowego@126.com">hanyouqing</a>
 * @date 2016-01-23
 *
 * @version 1.0
 */

@Service("isellistService")
public class ISellistService {

	@Autowired
	private ISellistRepository isellistRepository;
	
	//选择列表参数，只从数据库加载一次。
	private List<ISellist> sexList = null;
    
	//ftype=1是性别列表
	public List<ISellist> getSexList() {
		if (sexList == null) {
			sexList = isellistRepository.findByFtype(1);
		}
		return sexList;
	}
	
	//科室类型列表
	private List<ISellist> depTypeList = null;
	
	//ftype=2是科室类型
		public List<ISellist> getDepTypeList() {
			if (depTypeList == null) {
				depTypeList = isellistRepository.findByFtype(2);
			}
			return depTypeList;
		}

	public void deleteById(Integer id) {
		isellistRepository.delete(id);
	}

	public Page<ISellist> getListWithPaging(int pages, int pageSize) {
		Pageable pageAble = new PageRequest(pages, pageSize);
		Page<ISellist> page = isellistRepository.findAll(pageAble);
		return page;
	}

	public void updateById(ISellist isellist) {
		isellistRepository.save(isellist);
	}

	public ISellist saveISellist(ISellist isellist) {
		try {
			return isellistRepository.save(isellist);

		} catch (Exception ex) {
			System.out.println("执行出错哦：" + ex.getMessage());
			return null;
		}
	}

	public Page<ISellist> getListWithPaging(Pageable pageAble) {
		Page<ISellist> page = isellistRepository.findAll(pageAble);
		return page;
	}

	public ISellist getById(Integer id) {
		return isellistRepository.findOne(id);

	}

}