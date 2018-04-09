package com.quartz.quartzTest;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class HelloScheduler {
	public static void main(String[] args) throws SchedulerException, InterruptedException {
		Date date=new Date();
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		//获取当前时间3秒后的时间
		date.setTime(date.getTime()+3000);
		
		//获取当前时间6秒后的时间
		Date endDate=new Date();
		endDate.setTime(endDate.getTime()+6000);
		
		//创建一个JobDetail实例，将实例与helloJob.class绑定
		JobDetail jobDetail=JobBuilder.newJob(HelloJob.class).
				withIdentity("myJob", "group1").
				usingJobData("message", "hello jobDetail").
				build();
		
		//创建一个Trigger实例，定义该job立即执行，并且每隔2秒重复执行一次，直到永远
		/*SimpleTrigger trigger =(SimpleTrigger)TriggerBuilder.newTrigger().
				withIdentity("myTrigger", "group1").
				usingJobData("message", "hello trigger").
				startAt(date).
				endAt(endDate).
				withSchedule(SimpleScheduleBuilder.simpleSchedule().
						withIntervalInSeconds(2).withRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY)).
				build();*/
		//CronTrigger实现每秒执行一次
		CronTrigger cronTrigger=(CronTrigger) TriggerBuilder.newTrigger().
				withIdentity("myTrigger", "group1").
				withSchedule(
						CronScheduleBuilder.cronSchedule("* * * * * ? *")).
				build();
		
		//创建scheduler
		SchedulerFactory schedulerFactory=new StdSchedulerFactory();
		Scheduler scheduler=schedulerFactory.getScheduler();
		scheduler.start();
		//返回值是最近一次要执行的时间
		scheduler.scheduleJob(jobDetail,cronTrigger);
		//程序2秒后挂起
		Thread.sleep(2000L);
		//程序挂起
		scheduler.standby();
		//程序挂起3秒后继续执行
		Thread.sleep(3000L);
		scheduler.start();
		
	}

}
