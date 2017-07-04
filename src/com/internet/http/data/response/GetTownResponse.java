package com.internet.http.data.response;

import java.util.List;

import com.internet.http.data.vo.TownVO;

public class GetTownResponse extends CommonResponse {
	private List<TownVO> data;

	public void setData(List<TownVO> data) {
		this.data = data;
	}

	public List<TownVO> getData() {
		return this.data;
	}

}
