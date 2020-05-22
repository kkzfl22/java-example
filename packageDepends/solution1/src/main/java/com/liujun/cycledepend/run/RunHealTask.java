package com.liujun.cycledepend.run;

import com.liujun.cycledepend.item1.HealthRecord;
import com.liujun.cycledepend.item2.HealthTask;
import com.liujun.cycledepend.mediator.HealthPointMediator;

public class RunHealTask {
	
	public static void main(String[] args) {
		
		HealthRecord record = new HealthRecord();
		record.addTask("忌烟酒", 5);
		record.addTask("一周慢跑三次", 4);
		record.addTask("一天喝两升水", 20);
		record.addTask("坐1小时起来活动5分钟", 2);
		record.addTask("晚上10点按时睡觉", 3);
		record.addTask("晚上8点之后不再饮食", 1);
		
		//初始化中介者
		HealthPointMediator mediator = new HealthPointMediator(record);
		
		
		for (HealthTask task : record.getTasks()) {
			//执行计算积分
			Integer healthPoint = mediator.calculateHealthPointForTask(task);
			System.out.println("输出:"+healthPoint);
		}
		
	}

}
