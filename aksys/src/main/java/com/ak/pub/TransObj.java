package com.ak.pub;

import java.util.List;

//这是一个泛型List的实例，其中的list可以被实例赋值。
public class TransObj {

	public TransObj() {

	}

	private  List list = null;
	
	private String ttt="ttt";

	public String getTtt() {
		return ttt;
	}

	public void setTtt(String ttt) {
		this.ttt = ttt;
	}

	public <T> List<T> getList() {
		return list;
	}

	public <T> void setList(List<T> list) {
		this.list = list;
	}

}
