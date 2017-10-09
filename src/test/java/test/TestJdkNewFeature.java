
package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;

/*
 * @Project Name: ctosb-codebuild-web
 * @File Name: TestJdkNewFeature.java
 * @Package Name:
 * @Date: 2017年9月28日上午11:30:22
 * @Creator: liliangang-1163
 * @line------------------------------
 * @修改人:
 * @修改时间:
 * @修改内容:
 */
/**
 * TODO
 * @author liliangang-1163
 * @date 2017年9月28日上午13:30:22
 * @see
 */
public class TestJdkNewFeature {

	@Test
	public void test() {
		List<Integer> list = Arrays.asList(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 });
		System.out.println("测试foreach遍历");
		list.stream().forEach(System.out::print);
		System.out.println();
		System.out.println("测试map计算，并遍历");
		list.stream().map(e -> e * e).forEach(e -> System.out.print("," + e));
		System.out.println();
		System.out.println("测试map计算，collect合并集合，并遍历");
		List<Integer> newlist = list.stream().map(e -> e * e).collect(Collectors.toList());
		newlist.stream().forEach(e -> System.out.print("," + e));
		System.out.println();
		System.out.println("测试filter过滤");
		list.stream().filter(e -> e > 3).forEach(System.out::print);
		System.out.println();
		System.out.println("测试optional空处理");
		Optional<User> user = Optional.ofNullable(null);
		String name = user.map(e -> e.getUsername()).map(n -> n.toUpperCase()).orElse(null);
		System.out.println(name);
		System.out.println();
		System.out.println("测试多集合合并");
		List<User> us = new ArrayList<>();
		for (int i = 1; i <= 5; i++) {
			User u = new User("test" + i, i);
			for (int n = 1; n < 3; n++) {
				User sub = new User("test" + i + n, n);
				u.addUsers(sub);
			}
			us.add(u);
		}
		List<User> users = us.stream().flatMap(t -> t.users.stream()).collect(Collectors.toList());
		Assert.assertTrue(users.size() == 10);
	}

	public static class User {

		private String username;
		private int age;
		private List<User> users = new ArrayList<>();

		public User() {
		}

		public User(String username) {
			this.username = username;
		}

		public User(String username, int age) {
			this.username = username;
			this.age = age;
		}

		public String getUsername() {
			return username;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public List<User> getUsers() {
			return users;
		}

		public void setUsers(List<User> users) {
			this.users = users;
		}

		public void addUsers(User user) {
			this.users.add(user);
		}
	}
}
