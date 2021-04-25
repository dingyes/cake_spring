package com.cake.quartz;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.cake.dao.CakeDao;

@Service
public class AnnotationJob {
	@Resource
	private CakeDao cakeDao;

	@Scheduled(cron = "0 0 1 ? * TUE")
	public void showTime() {
		System.out.println("现有的蛋糕数目为：" + cakeDao.getCakeNum());
	}

}
