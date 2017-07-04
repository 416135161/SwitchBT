package com.internet.http.data.vo;

import java.io.Serializable;

public class VillageVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String village_id;

	private String village_name;
	
	private String heads_number;
	private String family_number;

	public void setVillage_id(String village_id) {
		this.village_id = village_id;
	}

	public String getVillage_id() {
		return this.village_id;
	}

	public void setVillage_name(String village_name) {
		this.village_name = village_name;
	}

	public String getVillage_name() {
		return this.village_name;
	}

	public String getHeads_number() {
		return heads_number;
	}

	public void setHeads_number(String heads_number) {
		this.heads_number = heads_number;
	}

	public String getFamily_number() {
		return family_number;
	}

	public void setFamily_number(String family_number) {
		this.family_number = family_number;
	}
	
	
}
