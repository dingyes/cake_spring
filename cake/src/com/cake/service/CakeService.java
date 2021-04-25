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
	 * ��ӵ�����Ϣ
	 * 
	 * @param cake ������Ϣ
	 * @return ��������
	 */
	public void addCake(Cake cake) {
		System.out.println("dao���" + cake);
		cakeDao.addCakeInfo(cake);
	}

	/**
	 * ��ѯ���е�����Ϣ
	 * 
	 * @return
	 */
	public List<Cake> findAllCakes() {
		System.out.println("dao���е��⣺" + cakeDao.findAll());
		return cakeDao.findAll();
	}

	/**
	 * �޸ĵ�����Ϣ
	 * 
	 * @param cake ��Ҫ�޸ĵĵ�����Ϣ
	 * @return �޸ĵ�����
	 */
	public int updateCake(Cake cake) {
		System.out.println("dao���£�" + cake);
		return cakeDao.updateCakeInfo(cake);
	}

	/**
	 * ɾ��������Ϣ
	 * 
	 * @param id �����id
	 * @return ɾ��������
	 */
	public int deleteCake(int id) {
		System.out.println("daoɾ��");
		return cakeDao.deleteCakeById(id);
	}

	public Cake findSpecificCake(int id) {
		return cakeDao.findCakeById(id);
	}
}
