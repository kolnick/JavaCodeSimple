package com.caochaojie.smartdoc.model;

import java.util.Date;


public class Message {
	private Long id;
	private String text;
	private String summary;
	private Date createDate;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getSummary() {
		return this.summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	@Override
	public String toString() {
		return "Message{" +
				"id=" + id +
				", text='" + text + '\'' +
				", summary='" + summary + '\'' +
				", createDate=" + createDate +
				'}';
	}
}
