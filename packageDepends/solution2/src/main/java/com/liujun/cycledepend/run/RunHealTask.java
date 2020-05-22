package com.liujun.cycledepend.run;

import java.util.List;

import com.liujun.cycledepend.headler.HealthLevelHandler;
import com.liujun.cycledepend.item1.HealthRecord;
import com.liujun.cycledepend.item2.HealthTask;

public class RunHealTask {
	
	public static void main(String[] args) {
		
		
		HealthRecord record = new HealthRecord();
		record.addTask("忌烟酒", 5);
		record.addTask("一周慢跑三次", 4);
		record.addTask("一天喝两升水", 20);
		record.addTask("坐1小时起来活动5分钟", 2);
		record.addTask("晚上10点按时睡觉", 3);
		record.addTask("晚上8点之后不再饮食", 1);
		
		HealthLevelHandler healder = record.getHealthPointHandler();
		
		List<HealthTask> tasks = record.getTasks();
		
		for (HealthTask healthTask : tasks) {
			int countvalue =  healthTask.calculatePointForTask(healder);
			System.out.println("cout data:"+countvalue);
		}
		
	}

}
