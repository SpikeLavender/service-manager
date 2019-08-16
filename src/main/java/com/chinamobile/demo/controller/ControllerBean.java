package com.chinamobile.demo.controller;

import com.chinamobile.demo.entities.BeanUser;
import com.chinamobile.demo.mapper.BeanUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController

public class ControllerBean {

	@Autowired
	BeanUserMapper beanUserMapper;

	//查询

	@GetMapping("/get/{id}/user/{user_name}")

	public List<BeanUser> getBeanUser(@PathVariable("id") Integer id,
	                                  @PathVariable("user_name") String user_name){
		Map map = new HashMap();
		map.put("id", id);
		map.put("userName", user_name);
		return beanUserMapper.selectId(map);

	}
	@GetMapping("/get-by-entity/{id}/user/{user_name}")

	public List<BeanUser> getBeanUser1(@PathVariable("id") Integer id,
	                                  @PathVariable("user_name") String user_name){
		BeanUser beanUser = new BeanUser();
		beanUser.setId(id);
		beanUser.setUserName(user_name);
		return beanUserMapper.selectByEntity(beanUser);

	}
	//插入

	@PostMapping(value = "/insert")

	public BeanUser insertBeanUser(@RequestBody BeanUser beanUser){

		beanUserMapper.insertBean(beanUser);

		return beanUser;

	}

	@PostMapping(value = "/insert_batch")

	public List<BeanUser> insertBeanUserBatch(@RequestBody List<BeanUser> beanUser){

		beanUserMapper.insertBeanBatch(beanUser);

		return beanUser;

	}
	//修改

	@PostMapping("/update")

	public BeanUser updateBeanUser(@RequestBody BeanUser beanUser){

		beanUserMapper.UpdateBean(beanUser);

		return beanUser;

	}
	//删除

	@DeleteMapping("/delete/{id}")
	public String deleteBeanUser(@PathVariable("id")Integer id){

		beanUserMapper.deleteId(id);

		return "删除成功！";

	}
}