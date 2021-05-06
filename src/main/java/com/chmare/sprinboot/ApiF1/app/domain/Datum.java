package com.chmare.sprinboot.ApiF1.app.domain;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonInclude;



public class Datum {
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public String _id;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
    public String picture;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
    public Integer age;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
    public String name;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
    public String team;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public Integer globalPos;
	

	
	@JsonInclude(JsonInclude.Include.NON_NULL)
    public List<Race> races;
	
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	public List<Race> getRaces() {
		return races;
	}
	public void setRaces(List<Race> races) {
		this.races = races;
	}
	public Integer getGlobalPos() {
		return globalPos;
	}
	public void setGlobalPos(Integer globalPos) {
		this.globalPos = globalPos;
	}
	

	




    
    
}
