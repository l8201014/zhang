package com.zhang.model;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author ：zhangwn
 * @date ：Created in 2019/1/25 15:46
 * @modified By：
 */
//@ConfigurationProperties(prefix="user")
//@Component
public class User {
    @Value("${username}")
    private String username;
    @Value("${password}")
    private String password;
    @Value("${phone}")
    private String phone;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
