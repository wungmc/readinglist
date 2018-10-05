/*
 * Copyright (C), 2011-2018.
 */
package com.wung;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * 打成war包时需要添加该类。
 * 并jar修改为war：<packaging>war</packaging>
 *
 * @author wung 2018/10/5.
 */
public class ReadingListServletInitializer extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(ReadingListApplication.class);
	}
}
