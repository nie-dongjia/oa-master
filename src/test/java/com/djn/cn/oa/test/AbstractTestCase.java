package com.djn.cn.oa.test;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/activiti.cfg.xml")
public abstract class AbstractTestCase {
	Logger logger = LoggerFactory.getLogger(getClass());
}
