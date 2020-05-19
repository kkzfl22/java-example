package com.liujun.spi.plugin.invoke;

import java.util.ServiceLoader;

import com.liujun.plugin.spi.DataAccessProvider;

public class InvokeMain {

	public static void main(String[] args) {
		System.out.println("开始执行");
		//提供了加载机制，基于最jdk的加载
		ServiceLoader<DataAccessProvider> loader = ServiceLoader.load(DataAccessProvider.class);
		
		for (DataAccessProvider dataAccessProvider : loader) {
			System.out.println("当前路径信息:"+dataAccessProvider.getClass());
			String dataInfo = dataAccessProvider.getDataInfo("userinfo ");
			System.out.println("得到结果:"+dataInfo);
			
			System.out.println("---------------------------------");
		}
	}
	
}
