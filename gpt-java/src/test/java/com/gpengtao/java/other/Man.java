package com.gpengtao.java.other;

import com.google.common.collect.Lists;
import com.gpengtao.java.model.Person;

import java.util.List;

/**
 * Created by pengtao.geng on 2017/11/2.
 */
public class Man implements Cloneable {
	private String name;
	private String city;
	private Integer age;

	private List<Object> list;

	public Man() {
	}

	public Man(String name) {
		this.name = name;
	}

	public Man(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public List<Object> getList() {
		return list;
	}

	public void setList(List<Object> list) {
		this.list = list;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
