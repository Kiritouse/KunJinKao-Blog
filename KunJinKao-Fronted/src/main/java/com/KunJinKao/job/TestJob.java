package com.KunJinKao.job;

import com.KunJinKao.annotation.SystemLog;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TestJob {
    //要执行的代码
    @Scheduled(cron = " 0/5 * * * * ? ")
    public void testJob(){
        System.out.println("testJob");

    }
}
