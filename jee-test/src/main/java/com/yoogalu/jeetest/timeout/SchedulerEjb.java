package com.yoogalu.jeetest.timeout;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.ScheduleExpression;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.inject.Inject;
import javax.ejb.Singleton;

@Singleton
@Startup
public class SchedulerEjb {

	@Inject
	SingletonEjb ejb;

	@Resource
	TimerService timerService;

	@PostConstruct
	public void init() {
		timerService.createCalendarTimer(new ScheduleExpression().hour("*").minute("*").second("*/1"), new TimerConfig(
				"Scheduler", false));
	}

	@Timeout
	public void timeout() {
		System.out.println("Timeout starting.");
		ejb.process();
		System.out.println("Timeout completed.");
	}

}
