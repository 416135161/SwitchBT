package com.internet.http.data.response;

import java.util.List;

import com.internet.http.data.vo.VillageVO;

public class GetVillageListResponse  extends CommonResponse {
	private List<VillageVO> data;

	public void setData(List<VillageVO> data) {
		this.data = data;
	}

	public List<VillageVO> getData() {
		return this.data;
	}

}
