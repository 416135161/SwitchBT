package com.internet.http.data.vo;

import java.io.Serializable;

public class PoorPeopleVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;

	private int head_id;

	private int poor_prop;

	private String head_name;

	private String village_name;

	private String town_name;

	private String dictionary_name;

	private String head_attribute;

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}

	public void setHead_id(int head_id) {
		this.head_id = head_id;
	}

	public int getHead_id() {
		return this.head_id;
	}

	public void setPoor_prop(int poor_prop) {
		this.poor_prop = poor_prop;
	}

	public int getPoor_prop() {
		return this.poor_prop;
	}

	public void setHead_name(String head_name) {
		this.head_name = head_name;
	}

	public String getHead_name() {
		return this.head_name;
	}

	public void setVillage_name(String village_name) {
		this.village_name = village_name;
	}

	public String getVillage_name() {
		return this.village_name;
	}

	public void setTown_name(String town_name) {
		this.town_name = town_name;
	}

	public String getTown_name() {
		return this.town_name;
	}

	public void setDictionary_name(String dictionary_name) {
		this.dictionary_name = dictionary_name;
	}

	public String getDictionary_name() {
		return this.dictionary_name;
	}

	public void setHead_attribute(String head_attribute) {
		this.head_attribute = head_attribute;
	}

	public String getHead_attribute() {
		return this.head_attribute;
	}

}
