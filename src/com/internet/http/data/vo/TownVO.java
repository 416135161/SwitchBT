package com.internet.http.data.vo;

import java.io.Serializable;

public class TownVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String town_id;

	private String town_name;

	private String heads_number;
	private String family_number;

	public void setTown_id(String town_id) {
		this.town_id = town_id;
	}

	public String getTown_id() {
		return this.town_id;
	}

	public void setTown_name(String town_name) {
		this.town_name = town_name;
	}

	public String getTown_name() {
		return this.town_name;
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
