package com.liujun.cycledepend.headler;


/**
 * 包含了对等级的计算过程
 * @author liujun
 *
 */
public class HealthLevelHandler {
	
	private Integer taskCount;

	public HealthLevelHandler(Integer taskCount) {
		this.taskCount = taskCount;
	}
	
	public Integer getHealthLevel()
	{
		if(taskCount > 5)
		{
			return 1;
		}
		if(taskCount < 2)
		{
			return 3;
		}
		return 2;
	}
	

}
