package com.jt.manage.vo;

public class EasyUITree 
{
	private Long id;//定义节点id
	private String text;//定义节点名称
	private String state;//定义节点的状态
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "EasyUITree [id=" + id + ", text=" + text + ", state=" + state + "]";
	}
}
