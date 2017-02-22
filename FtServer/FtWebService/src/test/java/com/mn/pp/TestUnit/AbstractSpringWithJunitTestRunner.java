package com.mn.pp.TestUnit;

import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.test.context.ContextConfiguration;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by mn on 2016/11/19 0019.
 */
@RunWith(SpringJUnit4ClassRunner.class)

@WebAppConfiguration
@ContextConfiguration(locations = {"classpath*:/spring-*.xml"})
public abstract class AbstractSpringWithJunitTestRunner {


    public abstract void find();

    public abstract void update();

    public abstract void save();

    public abstract void delete();
}
