package com.learn.mn.pojo;

public class LocalizedMessage {
	private String id;
	private String text;
	private String locale;

	public LocalizedMessage(String id, String text, String locale) {
		super();
		this.id = id;
		this.text = text;
		this.locale = locale;
	}

	public LocalizedMessage() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getLocale() {
		return locale;
	}

	public void setLanguage(String locale) {
		this.locale = locale;
	}
}
