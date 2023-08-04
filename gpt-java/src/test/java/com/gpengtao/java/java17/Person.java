package com.gpengtao.java.java17;

/**
 * @author pengtao.geng on 2023/8/4 17:28.
 */
public record Person(String name, int age, String city) {

	public static void main(String[] args) {
		Person person = new Person("gpt", 0, "xxx");
		System.out.println(person.name());
	}

}
