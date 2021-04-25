package com.cake.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cake.entity.Cake;

@Repository
@Transactional
public class CakeDao {
	@Resource
	private SessionFactory sessionFactory;
	/**
	 * 根据id查询蛋糕价格
	 * @param id
	 * @return
	 */
	public int getCakePriceById(int id) {
		Session session = sessionFactory.openSession();
		Cake cake = session.get(Cake.class, new Integer(id));
		session.close();
		if(cake != null) {
			return cake.getPrice();
		} else {
			return 0;
		}
	}

	/**
	 * 查询数据库中蛋糕数量
	 * 
	 * @return 蛋糕数
	 */
	public int getCakeNum() {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from Cake");
		int count = query.list().size();
		session.close();
		// 得到数据总条数
		return count;
	}

	/**
	 * 添加蛋糕
	 * 
	 * @param cake 要插入的蛋糕信息
	 * @return 插入条数
	 */
	public void addCakeInfo(Cake cake) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(cake);
	}

	/**
	 * 查询所有蛋糕信息
	 * 
	 * @return
	 */
	public List<Cake> findAll() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Cake");
		return query.list();
	}

	/**
	 * 修改蛋糕信息
	 * 
	 * @param cake 需要修改的蛋糕信息
	 * @return 修改的条数
	 */
	public int updateCakeInfo(Cake cake) {
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "update Cake c set c.weight = :weight , c.price = :price, c.pic = :pic where c.id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("weight", cake.getWeight());
		query.setParameter("price", cake.getPrice());
		query.setParameter("pic", cake.getPic());
		query.setParameter("id", cake.getId());
		return query.executeUpdate();
	}

	/**
	 * 删除蛋糕信息
	 * 
	 * @param id 蛋糕的id
	 * @return 删除的条数
	 */
	public int deleteCakeById(int id) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "delete from Cake c where c.id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		return query.executeUpdate();
	}

	/**
	 * 查询特定蛋糕信息
	 * 
	 * @param id
	 * @return
	 */
	public Cake findCakeById(int id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(Cake.class, new Integer(id));
	}
}
