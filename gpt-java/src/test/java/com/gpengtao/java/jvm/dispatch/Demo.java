package com.gpengtao.java.jvm.dispatch;

/**
 * Dispatch分类：动态分配Dynamic Dispatch、静态分配Static Dispatch
 *
 * @author pengtao.geng on 2023/7/11 19:48.
 */
public class Demo {

	public static void main(String[] args) {
		Person a = new Man();
		//  a.age内部主要通过如下字节码实现：
		//  getfield      #5                  // Field test/Person.age:I
		System.out.println(a.age);
		//  a.getAge()内部主要通过如下字节码实现：
		//  invokevirtual #7                  // Method test/Person.getAge:()I
		System.out.println(a.getAge());
	}

	static class Person {
		int age = 30;

		int getAge() {
			return age;
		}
	}

	static class Man extends Person {
		int age = 40;
		int height = 160;

		int getAge() {
			return age;
		}
	}
}
