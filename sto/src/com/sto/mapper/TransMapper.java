package com.sto.mapper;

import java.util.List;
import java.util.Map;

import com.sto.entity.Trans;

public interface TransMapper {

	public List<Trans> findTransByCondition(Map map);
}
