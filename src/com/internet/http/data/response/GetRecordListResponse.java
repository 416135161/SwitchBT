package com.internet.http.data.response;

import java.util.List;

public class GetRecordListResponse extends CommonResponse {
	private List<Record> data;

	public void setData(List<Record> data) {
		this.data = data;
	}

	public List<Record> getData() {
		return this.data;
	}

	public class Record {
		private String id;

		private int head_id;

		private String content;

		private String help_date;

		private String title;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public int getHead_id() {
			return head_id;
		}

		public void setHead_id(int head_id) {
			this.head_id = head_id;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public String getHelp_date() {
			return help_date;
		}

		public void setHelp_date(String help_date) {
			this.help_date = help_date;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

	}

}
