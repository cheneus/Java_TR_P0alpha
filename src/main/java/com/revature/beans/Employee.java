package com.revature.beans;

import java.util.Date;

public class Employee {
	private int id;
	private String lastname;
	private String firstname;
	private String title;
	private Employee supervisor;
	private Date birthDate;
	private Date hireDate;
	private Department dept_id;
	private Address address;
	private double reimbursement_balance;
	private String phone;
	private String email;
	
	public Employee() {
		super();
	}
	public Employee(int id) {
		super();
		this.id = id;
	}

	public Employee(int id, String lastname, String firstname, String title, Employee supervisor, Date birthDate,
			Date hireDate, Department dept_id, Address address, int reimbursement_balance, String phone, String email) {
		super();
		this.id = id;
		this.lastname = lastname;
		this.firstname = firstname;
		this.title = title;
		this.supervisor = supervisor;
		this.birthDate = birthDate;
		this.hireDate = hireDate;
		this.dept_id = dept_id;
		this.address = address;
		this.reimbursement_balance = reimbursement_balance;
		this.phone = phone;
		this.email = email;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public Date getHireDate() {
		return hireDate;
	}
	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}
	public Department getDept_id() {
		return dept_id;
	}
	public void setDept_id(Department dept_id) {
		this.dept_id = dept_id;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public double getReimbursement_balance() {
		return reimbursement_balance;
	}
	public void setReimbursement_balance(double d) {
		this.reimbursement_balance = d;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Employee getSupervisor() {
		return supervisor;
	}
	public void setSupervisor(Employee supervisor) {
		this.supervisor = supervisor;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((birthDate == null) ? 0 : birthDate.hashCode());
		result = prime * result + ((dept_id == null) ? 0 : dept_id.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstname == null) ? 0 : firstname.hashCode());
		result = prime * result + ((hireDate == null) ? 0 : hireDate.hashCode());
		result = prime * result + id;
		result = prime * result + ((lastname == null) ? 0 : lastname.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		long temp;
		temp = Double.doubleToLongBits(reimbursement_balance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((supervisor == null) ? 0 : supervisor.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (birthDate == null) {
			if (other.birthDate != null)
				return false;
		} else if (!birthDate.equals(other.birthDate))
			return false;
		if (dept_id == null) {
			if (other.dept_id != null)
				return false;
		} else if (!dept_id.equals(other.dept_id))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstname == null) {
			if (other.firstname != null)
				return false;
		} else if (!firstname.equals(other.firstname))
			return false;
		if (hireDate == null) {
			if (other.hireDate != null)
				return false;
		} else if (!hireDate.equals(other.hireDate))
			return false;
		if (id != other.id)
			return false;
		if (lastname == null) {
			if (other.lastname != null)
				return false;
		} else if (!lastname.equals(other.lastname))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (Double.doubleToLongBits(reimbursement_balance) != Double.doubleToLongBits(other.reimbursement_balance))
			return false;
		if (supervisor == null) {
			if (other.supervisor != null)
				return false;
		} else if (!supervisor.equals(other.supervisor))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", lastname=" + lastname + ", firstname=" + firstname + ", title=" + title
				+ ", supervisor=" + supervisor + ", birthDate=" + birthDate + ", hireDate=" + hireDate + ", dept_id="
				+ dept_id + ", address=" + address + ", reimbursement_balance=" + reimbursement_balance + ", phone="
				+ phone + ", email=" + email + "]";
	}
}
