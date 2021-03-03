package com.sample.springboot.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sample.springboot.domain.DateFormula;

@Mapper
public interface CrudMapper {

	//計算式検索メソッド
	public List<DateFormula> findFormula();

	//計算式登録メソッド
	public Boolean registerFormula();
}
