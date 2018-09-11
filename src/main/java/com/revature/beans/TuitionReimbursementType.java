package com.revature.beans;

import java.util.Date;

public class TuitionReimbursementType {
	private int id;
	private String name;
	private int rate;
	
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