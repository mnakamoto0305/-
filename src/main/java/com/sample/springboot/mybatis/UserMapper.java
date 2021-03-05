package com.sample.springboot.mybatis;

import org.apache.ibatis.annotations.Mapper;

import com.sample.springboot.domain.User;

@Mapper
public interface UserMapper {

	public User identifyUser(String userId);
}
