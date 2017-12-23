package com.jing.ext;

import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Priority;

public class MyDailyRollingFileAppender extends DailyRollingFileAppender {

	@Override
	public boolean isAsSevereAsThreshold(Priority priority) {
		// 只判断相同的范围（层级）
		return this.getThreshold().equals(priority);
	}
}
