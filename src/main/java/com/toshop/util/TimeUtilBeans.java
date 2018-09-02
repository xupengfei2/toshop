package com.toshop.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Calendar;
import java.util.TimeZone;

@Configuration
public class TimeUtilBeans {

    @Bean
    public TimeZone getDefaultTimeZone(){
        return TimeZone.getDefault();
    }

    @Bean
    public Calendar getEmptyCalendar(){
        return Calendar.getInstance(this.getDefaultTimeZone());
    }

    @Bean
    public long getSystemTimeInMillis(){
        return System.currentTimeMillis();
    }
}
