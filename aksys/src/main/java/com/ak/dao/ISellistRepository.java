package com.ak.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.ak.entity.ISellist;

/**
 * @author <a href="mailto:sosowego@126.com">hanyouqing</a>
 * @date 2016-01-23
 *
 * @version 1.0
 */
public interface ISellistRepository extends JpaRepository<ISellist, Integer> {
	public List<ISellist> findByFtype(int ftype);
}