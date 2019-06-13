package com.zhouzhu.springbootsecurity.demo.payload;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

/**
 * @author zhouzhu
 * @Description
 * @create 2019-06-13 15:31
 */
public class PollLength {
    @NotNull
    @Max(7)
    private Integer days;

    @NotNull
    @Max(23)
    private Integer hours;

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }
}
