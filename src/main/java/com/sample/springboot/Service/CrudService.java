package com.sample.springboot.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sample.springboot.domain.DateFormula;
import com.sample.springboot.mybatis.CrudMapper;

@Service
public class CrudService {

	@Autowired
	CrudMapper crudMapper;

	//計算式検索用メソッド
	@Transactional
	public List<DateFormula> findFormula() {
		return crudMapper.findFormula();
	}

	//計算式登録用メソッド
	@Transactional
	public boolean registerFormula(DateFormula dateFormula) {
		return crudMapper.registerFormula(dateFormula);
	}

	//計算式特定メソッド(更新・削除用)
	@Transactional
	public DateFormula identifyFormula(Long id) {
		return crudMapper.identifyFormula(id);
	}

	//計算式更新用メソッド
	@Transactional
	public boolean updateFormula(DateFormula dateFormula) {
		return crudMapper.updateFormula(dateFormula);
	}

	//計算式削除用メソッド
	@Transactional
	public boolean deleteFormula(Long id) {
		return crudMapper.deleteFormula(id);
	}

}
