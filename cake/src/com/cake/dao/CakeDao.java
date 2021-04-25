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
	 * ����id��ѯ����۸�
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
	 * ��ѯ���ݿ��е�������
	 * 
	 * @return ������
	 */
	public int getCakeNum() {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from Cake");
		int count = query.list().size();
		session.close();
		// �õ�����������
		return count;
	}

	/**
	 * ��ӵ���
	 * 
	 * @param cake Ҫ����ĵ�����Ϣ
	 * @return ��������
	 */
	public void addCakeInfo(Cake cake) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(cake);
	}

	/**
	 * ��ѯ���е�����Ϣ
	 * 
	 * @return
	 */
	public List<Cake> findAll() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Cake");
		return query.list();
	}

	/**
	 * �޸ĵ�����Ϣ
	 * 
	 * @param cake ��Ҫ�޸ĵĵ�����Ϣ
	 * @return �޸ĵ�����
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
	 * ɾ��������Ϣ
	 * 
	 * @param id �����id
	 * @return ɾ��������
	 */
	public int deleteCakeById(int id) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "delete from Cake c where c.id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		return query.executeUpdate();
	}

	/**
	 * ��ѯ�ض�������Ϣ
	 * 
	 * @param id
	 * @return
	 */
	public Cake findCakeById(int id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(Cake.class, new Integer(id));
	}
}
