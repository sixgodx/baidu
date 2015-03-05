package com.sto.utils;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;

public class CommonJson4List<T> implements Serializable {

	private static final long serialVersionUID = -369558847578246550L;

	private Integer code;

	private String message;

	private List<T> data;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public static CommonJson4List fromJson(String json, Class clazz) {
		Gson gson = new Gson();
		Type objectType = type(CommonJson4List.class, clazz);
		return gson.fromJson(json, objectType);
	}

	public String toJson(Class<T> clazz) {
		Gson gson = new Gson();
		Type objectType = type(CommonJson4List.class, clazz);
		return gson.toJson(this, objectType);
	}

	public static ParameterizedType type(final Class raw, final Type... args) {
		return new ParameterizedType() {
			public Type getRawType() {
				return raw;
			}

			public Type[] getActualTypeArguments() {
				return args;
			}

			public Type getOwnerType() {
				return null;
			}
		};
	}
}