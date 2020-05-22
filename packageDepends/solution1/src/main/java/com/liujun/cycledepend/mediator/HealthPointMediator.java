package com.liujun.cycledepend.mediator;

import com.liujun.cycledepend.item1.HealthRecord;
import com.liujun.cycledepend.item2.HealthTask;

/**
 * 抽象提取出一个中介者
 * @author liujun
 *
 */
public class HealthPointMediator {
	
	private HealthRecord record;

	public HealthPointMediator(HealthRecord record) {
		this.record = record;
	}
	
	
	/**
	 *  提供一个计算积分的方法，将原有的计算逻辑剥离,从而避免循环依赖
	 * @param task 健康任务
	 * @return 积分
	 */
	public Integer calculateHealthPointForTask(HealthTask task)
	{
		Integer healthLevel = record.getHealthLevel();
		//获取初始积分
		Integer initialHealthPoint = task.getInitialHealthPoint();
		Integer healthPoint = 12 / healthLevel + initialHealthPoint;
		
		return healthPoint;
	}

}
