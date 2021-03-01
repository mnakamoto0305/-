package com.sample.springboot.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sample.springboot.domain.DateFormula;
import com.sample.springboot.mybatis.CalculationMapper;

@Service
public class CalculationService {

	@Autowired
	CalculationMapper calculationMapper;

	//計算式検索用メソッド
	@Transactional
	public List<DateFormula> findFormula() {
		return calculationMapper.findFormula();
	}

}
