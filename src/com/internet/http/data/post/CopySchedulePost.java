package com.internet.http.data.post;

public class CopySchedulePost {
	public String sign 		 		;//String 签名[必填]
	public String driverId	 		;//Long 教练id driverId=xx
	public String calenderDate 		;//String 日期 date=yyyy-MM-dd
	public String scheduleTypeCode 	;//String 日程类型:练场地、练路、陪练 scheduleTypeCode=1\2\3
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getDriverId() {
		return driverId;
	}
	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}
	public String getCalenderDate() {
		return calenderDate;
	}
	public void setCalenderDate(String calenderDate) {
		this.calenderDate = calenderDate;
	}
	public String getScheduleTypeCode() {
		return scheduleTypeCode;
	}
	public void setScheduleTypeCode(String scheduleTypeCode) {
		this.scheduleTypeCode = scheduleTypeCode;
	}
	public CopySchedulePost(String sign, String driverId, String calenderDate,
			String scheduleTypeCode) {
		super();
		this.sign = sign;
		this.driverId = driverId;
		this.calenderDate = calenderDate;
		this.scheduleTypeCode = scheduleTypeCode;
	}
	public CopySchedulePost() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
