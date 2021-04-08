package com.chmare.sprinboot.ApiF1.app.domain;


import com.fasterxml.jackson.annotation.JsonInclude;

public class Race {
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
    public String name;
    
	@JsonInclude(JsonInclude.Include.NON_NULL)
    public String time;
    
	@JsonInclude(JsonInclude.Include.NON_NULL)
    public Integer position;
    
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	public Integer getPosition() {
		return position;
	}
	
	public void setPosition(Integer position) {
		this.position = position;
	}
    
    
    
}
