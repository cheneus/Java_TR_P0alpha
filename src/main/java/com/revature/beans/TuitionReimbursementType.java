package com.revature.beans;

public class TuitionReimbursementType {
	
	private int id;
	private String name;
	private int rate;
	
	public TuitionReimbursementType() {
		super();
	}
	public TuitionReimbursementType(int id) {
		super();
		this.id = id;
	}
	
	public TuitionReimbursementType(int id, String name, int rate) {
		super();
		this.id = id;
		this.name = name;
		this.rate = rate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "TuitionReimbursementType []";
	}
}