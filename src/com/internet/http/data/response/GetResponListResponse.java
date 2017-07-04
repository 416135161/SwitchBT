package com.internet.http.data.response;

import java.util.List;

public class GetResponListResponse extends CommonResponse {
	private List<ResponUser> data;

	public void setData(List<ResponUser> data) {
		this.data = data;
	}

	public List<ResponUser> getData() {
		return this.data;
	}

	public static class ResponUser {
		private int help_personnel_id;

		private int head_id;

		private String company;

		private String telphone;

		private String name;

		private int gender;

		private String photos;

		public void setHelp_personnel_id(int help_personnel_id) {
			this.help_personnel_id = help_personnel_id;
		}

		public int getHelp_personnel_id() {
			return this.help_personnel_id;
		}

		public void setHead_id(int head_id) {
			this.head_id = head_id;
		}

		public int getHead_id() {
			return this.head_id;
		}

		public void setCompany(String company) {
			this.company = company;
		}

		public String getCompany() {
			return this.company;
		}

		public void setTelphone(String telphone) {
			this.telphone = telphone;
		}

		public String getTelphone() {
			return this.telphone;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getName() {
			return this.name;
		}

		public void setGender(int gender) {
			this.gender = gender;
		}

		public int getGender() {
			return this.gender;
		}

		public void setPhotos(String photos) {
			this.photos = photos;
		}

		public String getPhotos() {
			return this.photos;
		}
	}

}
