package com.kevinblandy.simple.webchat.service.generic;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.kevinblandy.simple.webchat.base.BaseEntity;
import com.kevinblandy.simple.webchat.base.BaseMapper;
import com.kevinblandy.simple.webchat.base.BaseOperation;

/**
 * @author	KevinBlandy
 * @version	1.0
 * @date	2017年5月21日 下午4:01:05
 */
public class GenericService<T extends BaseEntity> implements BaseOperation<T>{

	@Autowired
	private BaseMapper<T> baseMapper;
	
	@Transactional(readOnly = true)
	@Override
	public T queryByPrimaryKey(Serializable primaryKey) throws Exception {
		return this.baseMapper.queryByPrimaryKey(primaryKey);
	}

	@Transactional(readOnly = true)
	@Override
	public PageList<T> queryByParamSelective(T entity, PageBounds pageBounds) throws Exception {
		return this.baseMapper.queryByParamSelective(entity, pageBounds);
	}

	@Transactional(readOnly = true)
	@Override
	public T queryByParamSelectiveSole(T entity) throws Exception {
		return this.baseMapper.queryByParamSelectiveSole(entity);
	}

	
	@Transactional
	@Override
	public Integer create(T entity) throws Exception {
		return this.baseMapper.create(entity);
	}

	@Transactional
	@Override
	public Integer updateByPrimaryKeySelective(T entity) throws Exception {
		return this.baseMapper.updateByPrimaryKeySelective(entity);
	}

	@Transactional
	@Override
	public Integer updateByPrimaryKey(T entity) throws Exception {
		return this.updateByPrimaryKey(entity);
	}

	@Transactional
	@Override
	public Integer deleteByPrimaryKey(Serializable primaryKey) throws Exception {
		return this.baseMapper.deleteByPrimaryKey(primaryKey);
	}

	@Transactional
	@Override
	public Integer deleteByParamSelective(T entity) throws Exception {
		return this.baseMapper.deleteByParamSelective(entity);
	}

	protected BaseMapper<T> getBaseMapper() {
		return baseMapper;
	}

//	public void setBaseMapper(BaseMapper<T> baseMapper) {
//		this.baseMapper = baseMapper;
//	}
}
