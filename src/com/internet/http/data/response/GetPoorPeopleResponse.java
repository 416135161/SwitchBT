package com.internet.http.data.response;

import java.util.List;

import com.internet.http.data.vo.PoorPeopleVO;

public class GetPoorPeopleResponse extends CommonResponse {
	private List<PoorPeopleVO> data;

	public void setData(List<PoorPeopleVO> data) {
		this.data = data;
	}

	public List<PoorPeopleVO> getData() {
		return this.data;
	}

}