package com.liujun.spi.plugin.invoke;

import java.util.ServiceLoader;

import com.liujun.plugin.spi.DataAccessProvider;

public class InvokeMain {

	public static void main(String[] args) {
		System.out.println("��ʼִ��");
		//�ṩ�˼��ػ��ƣ�������jdk�ļ���
		ServiceLoader<DataAccessProvider> loader = ServiceLoader.load(DataAccessProvider.class);
		
		for (DataAccessProvider dataAccessProvider : loader) {
			System.out.println("��ǰ·����Ϣ:"+dataAccessProvider.getClass());
			String dataInfo = dataAccessProvider.getDataInfo("userinfo ");
			System.out.println("�õ����:"+dataInfo);
			
			System.out.println("---------------------------------");
		}
	}
	
}
