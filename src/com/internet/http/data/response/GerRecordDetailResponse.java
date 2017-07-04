package com.internet.http.data.response;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class GerRecordDetailResponse extends CommonResponse {
	private RecordDetail data;

	public void setData(RecordDetail data) {
		this.data = data;
	}

	public RecordDetail getData() {
		return this.data;
	}

	public class RecordDetail {
		private String id;
		private String title;
		@SerializedName("head_id")
		private String headId;
		private String content;
		@SerializedName("help_date")
		private String helpDate;
		@SerializedName("help_address")
		private String helpAddress;
		private List<Images> images;

		public void setId(String id) {
			this.id = id;
		}

		public String getId() {
			return id;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getTitle() {
			if (title == null)
				title = "";
			return title;
		}

		public void setHeadId(String headId) {
			this.headId = headId;
		}

		public String getHeadId() {
			return headId;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public String getContent() {
			if (content == null)
				content = "";
			return content;
		}

		public void setHelpDate(String helpDate) {
			this.helpDate = helpDate;
		}

		public String getHelpDate() {
			if (helpDate == null) {
				helpDate = "";
			}
			return helpDate;
		}

		public void setHelpAddress(String helpAddress) {
			this.helpAddress = helpAddress;
		}

		public String getHelpAddress() {
			if (helpAddress == null)
				helpAddress = "";
			return helpAddress;
		}

		public void setImages(List<Images> images) {
			this.images = images;
		}

		public List<Images> getImages() {
			return images;
		}
	}

	public class Images {

		@SerializedName("attachment_url")
		private String attachmentUrl;

		public void setAttachmentUrl(String attachmentUrl) {
			this.attachmentUrl = attachmentUrl;
		}

		public String getAttachmentUrl() {
			return attachmentUrl;
		}

	}

}
