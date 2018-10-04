/*
 * Copyright (C), 2011-2018.
 */
package com.wung.actuator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.PublicMetrics;
import org.springframework.boot.actuate.metrics.Metric;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author wung 2018/10/4.
 */
@Component
public class ApplicationContextMetrics implements PublicMetrics {
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@Override
	public Collection<Metric<?>> metrics() {
		List<Metric<?>> metricList = new ArrayList<>();
		
		metricList.add(new Metric<>("spring.context.startup-date", applicationContext.getStartupDate()));
		metricList.add(new Metric<Number>("spring.beans", applicationContext.getBeanNamesForType(Object.class).length));
		metricList.add(new Metric<Number>("spring.controllers", applicationContext.getBeanNamesForAnnotation(Controller.class
		).length));
		
		return metricList;
	}
}
