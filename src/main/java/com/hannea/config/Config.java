package com.hannea.config;

import com.hannea.util.SpringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * Class
 *
 * @author wgm
 * @date 2018/06/10
 */
@Configuration
public class Config {

    @Bean
    public SpringUtils springUtils(){
        return new SpringUtils();
    }

    @Bean("schedulerFactoryBean")
    public SchedulerFactoryBean schedulerFactoryBean(){
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        //schedulerFactoryBean.setAutoStartup(false);
        return schedulerFactoryBean;
    }
}
