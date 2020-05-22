//package com.liujun.cycledepend.run;
//
//import java.util.List;
//
//import com.liujun.cycledepend.item1.HealthRecord;
//import com.liujun.cycledepend.item2.HealthTask;
//
//public class RunHealTask {
//	
//	public static void main(String[] args) {
//		
//		HealthRecord record = new HealthRecord();
//		record.addTask("run", 10);
//		record.addTask("task", 20);
//		
//		List<HealthTask> tasks = record.getTasks();
//		
//		for (HealthTask healthTask : tasks) {
//			int countvalue =  healthTask.calculatePointForTask();
//			System.out.println("cout data:"+countvalue);
//		}
//		
//	}
//
//}
