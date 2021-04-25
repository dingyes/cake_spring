package com.cake.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cake.dao.CakeDao;
import com.cake.entity.Cake;

@Service
public class CakeService {

	@Resource
	private CakeDao cakeDao;

	/**
	 * 添加蛋糕信息
	 * 
	 * @param cake 蛋糕信息
	 * @return 插入条数
	 */
	public void addCake(Cake cake) {
		System.out.println("dao添加" + cake);
		cakeDao.addCakeInfo(cake);
	}

	/**
	 * 查询所有蛋糕信息
	 * 
	 * @return
	 */
	public List<Cake> findAllCakes() {
		System.out.println("dao所有蛋糕：" + cakeDao.findAll());
		return cakeDao.findAll();
	}

	/**
	 * 修改蛋糕信息
	 * 
	 * @param cake 需要修改的蛋糕信息
	 * @return 修改的条数
	 */
	public int updateCake(Cake cake) {
		System.out.println("dao更新：" + cake);
		return cakeDao.updateCakeInfo(cake);
	}

	/**
	 * 删除蛋糕信息
	 * 
	 * @param id 蛋糕的id
	 * @return 删除的条数
	 */
	public int deleteCake(int id) {
		System.out.println("dao删除");
		return cakeDao.deleteCakeById(id);
	}

	public Cake findSpecificCake(int id) {
		return cakeDao.findCakeById(id);
	}
}
