package com.onepiecejoker.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

/**
 * <p>
 * 
 * </p>
 *
 * @author baomidou
 * @since 2024-03-18
 */
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Generate a LambdaQueryWrapper for the User class.
     *
     * @return          the LambdaQueryWrapper for the User class
     */
    public static final LambdaQueryWrapper<User> gw() {
        return Wrappers.lambdaQuery(User.class);
    }

    /**
     * ID
     */
    private Long id;

    private String name;

    private Integer age;

    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
        "id = " + id +
        ", name = " + name +
        ", age = " + age +
        ", email = " + email +
        "}";
    }
}
