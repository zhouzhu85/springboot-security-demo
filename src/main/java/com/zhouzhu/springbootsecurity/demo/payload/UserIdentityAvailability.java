package com.zhouzhu.springbootsecurity.demo.payload;

/**
 * @author zhouzhu
 * @Description
 * @create 2019-06-13 15:36
 */
public class UserIdentityAvailability {
    private Boolean available;

    public UserIdentityAvailability(Boolean available) {
        this.available = available;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}
