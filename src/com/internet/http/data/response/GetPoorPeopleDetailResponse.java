package com.internet.http.data.response;

import java.util.List;

public class GetPoorPeopleDetailResponse extends CommonResponse {
	private Data data;

	public void setData(Data data) {
		this.data = data;
	}

	public Data getData() {
		return this.data;
	}

	public static class Data {
		private int id;

		private int head_id;

		private String head_name;

		private int head_number;

		private String head_tel;

		private int poor_prop;

		private int poor_why;

		private String address;

		private String head_attribute;

		private String head_why;

		private List<Family> family;

		public void setId(int id) {
			this.id = id;
		}

		public int getId() {
			return this.id;
		}

		public void setHead_id(int head_id) {
			this.head_id = head_id;
		}

		public int getHead_id() {
			return this.head_id;
		}

		public void setHead_name(String head_name) {
			this.head_name = head_name;
		}

		public String getHead_name() {
			return this.head_name;
		}

		public void setHead_number(int head_number) {
			this.head_number = head_number;
		}

		public int getHead_number() {
			return this.head_number;
		}

		public void setHead_tel(String head_tel) {
			this.head_tel = head_tel;
		}

		public String getHead_tel() {
			return this.head_tel;
		}

		public void setPoor_prop(int poor_prop) {
			this.poor_prop = poor_prop;
		}

		public int getPoor_prop() {
			return this.poor_prop;
		}

		public void setPoor_why(int poor_why) {
			this.poor_why = poor_why;
		}

		public int getPoor_why() {
			return this.poor_why;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getAddress() {
			return this.address;
		}

		public void setHead_attribute(String head_attribute) {
			this.head_attribute = head_attribute;
		}

		public String getHead_attribute() {
			return this.head_attribute;
		}

		public void setHead_why(String head_why) {
			this.head_why = head_why;
		}

		public String getHead_why() {
			return this.head_why;
		}

		public void setFamily(List<Family> family) {
			this.family = family;
		}

		public List<Family> getFamily() {
			return this.family;
		}

	}

	public static class Family {

		private String family_name;

		private int age;

		private int gender;

		private String healthy;

		private String dictionary_name;

		private String health;

		public void setFamily_name(String family_name) {
			this.family_name = family_name;
		}

		public String getFamily_name() {
			return this.family_name;
		}

		public void setAge(int age) {
			this.age = age;
		}

		public int getAge() {
			return this.age;
		}

		public void setGender(int gender) {
			this.gender = gender;
		}

		public int getGender() {
			return this.gender;
		}

		public void setHealthy(String healthy) {
			this.healthy = healthy;
		}

		public String getHealthy() {
			return this.healthy;
		}

		public void setDictionary_name(String dictionary_name) {
			this.dictionary_name = dictionary_name;
		}

		public String getDictionary_name() {
			return this.dictionary_name;
		}

		public void setHealth(String health) {
			this.health = health;
		}

		public String getHealth() {
			return this.health;
		}

	}
}
