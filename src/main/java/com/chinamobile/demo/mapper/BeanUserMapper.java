package com.chinamobile.demo.mapper;

//指定这是一个操作数据库的mapper

import com.chinamobile.demo.entities.BeanUser;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface BeanUserMapper {



	//查询,map方式
	List<BeanUser> selectId(Map param);
	//查询,entity方式
	List<BeanUser> selectByEntity(BeanUser beanUser);

	//单条插入
	int insertBean(BeanUser beanUser);

	//批量插入
	int insertBeanBatch(List<BeanUser> beanUserList);

	//修改

	//@Update("update table_one set user_name=#{user_name},pass_word=#{pass_word} where id=#{id}")

	int UpdateBean(BeanUser beanUser);
	int deleteId(Integer id);


}