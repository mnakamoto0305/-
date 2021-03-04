package com.sample.springboot.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sample.springboot.domain.DateFormula;

@Mapper
public interface CrudMapper {

	//計算式検索メソッド
	public List<DateFormula> findFormula();

	//計算式登録メソッド
	public Boolean registerFormula(DateFormula dateFormula);

	//計算式更新メソッド
	public Boolean updateFormula(DateFormula dateFormula);

	//計算式特定メソッド(更新・削除用)
	public DateFormula identifyFormula(Long id);

	//計算式削除メソッド
	public Boolean deleteFormula(Long id);
}
