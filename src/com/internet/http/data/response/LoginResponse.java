package com.internet.http.data.response;

public class LoginResponse extends CommonResponse {
	LoginResponseEntity data;

	public LoginResponse() {
		// TODO Auto-generated constructor stub
	}

	public LoginResponseEntity getData() {
		return data;
	}

	public void setData(LoginResponseEntity data) {
		this.data = data;
	}

	public static class LoginResponseEntity {
		String admin_id;// ": 1,
		String username;// ": "admin",
		String password;// ": "e10adc3949ba59abbe56e057f20f883e",
		String name;// ": "系统管理员",
		int role_id;// ":5是县，2是镇，3是村
		String town_id;// ": 0,
		String village_id;// ": 0
		String village_name;//": "杨柳村"
		String town_name;//

		public String getAdmin_id() {
			return admin_id;
		}

		public void setAdmin_id(String admin_id) {
			this.admin_id = admin_id;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getRole_id() {
			return role_id;
		}

		public void setRole_id(int role_id) {
			this.role_id = role_id;
		}

		public String getTown_id() {
			return town_id;
		}

		public void setTown_id(String town_id) {
			this.town_id = town_id;
		}

		public String getVillage_id() {
			return village_id;
		}

		public void setVillage_id(String village_id) {
			this.village_id = village_id;
		}

		public String getVillage_name() {
			return village_name;
		}

		public void setVillage_name(String village_name) {
			this.village_name = village_name;
		}

		public String getTown_name() {
			return town_name;
		}

		public void setTown_name(String town_name) {
			this.town_name = town_name;
		}
		
	}
}
