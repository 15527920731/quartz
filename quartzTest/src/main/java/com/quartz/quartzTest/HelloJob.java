package com.quartz.quartzTest;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.TriggerKey;

public class HelloJob implements Job{
	public void execute(JobExecutionContext context) throws JobExecutionException {
		Date date=new Date();
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		System.out.println("current time is:"+format.format(date));
		//编写具体业务
		System.out.println("hello world!");
		
		//获取jobDetail信息
		JobKey jobKey=context.getJobDetail().getKey();
		//获取JobDetail的name和group信息
		System.out.println("JobDetail name和group："+jobKey.getName()+":"+jobKey.getGroup());
		//获取JobDataMap
		JobDataMap jobMap=context.getJobDetail().getJobDataMap();
		//获取键值
		String jobMessage=jobMap.getString("message");
		System.out.println(" JobDetail message "+jobMessage);
		
		//获取trigger信息
		TriggerKey triggerKey=context.getTrigger().getKey();
		//获取JobDetail的name和group信息
		System.out.println("trigger name和group："+triggerKey.getName()+":"+triggerKey.getGroup());
				
		//获取JobDataMap
		JobDataMap triggerMap=context.getTrigger().getJobDataMap();
		//获取键值
		String trigger=triggerMap.getString("message");
		System.out.println(" JobDetail message "+trigger);
				
		
		
	}

}
