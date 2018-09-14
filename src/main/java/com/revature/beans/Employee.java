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
		int result = super.hashCode();
		result = prime * result + ((supervisor == null) ? 0 : supervisor.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
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
		return "Employee [supervisor=" + supervisor + ", title=" + title + "]";
	}
	public void setAddress(int i) {
		// TODO Auto-generated method stub
		
	}
}
