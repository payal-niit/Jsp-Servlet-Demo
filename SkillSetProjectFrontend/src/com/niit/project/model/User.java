package com.niit.project.model;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {

	private int user_id;
	private String fname;
	private String lname;
	private int age;
	private Date dob;
	private String email_id;
	private String phone_number;
	private int total_yrs_of_experience;
	private int total_nm_of_stdnts_handled;
	private int status;
	private String password;
	private int manager_id;
	private String user_type;
	
	private String phoneNumberErrorMessage;
	
	public String getPhoneNumberErrorMessage() {
		return phoneNumberErrorMessage;
	}

	public void setPhoneNumberErrorMessage(String phoneNumberErrorMessage) {
		System.out.println("Check the number you have entered");
	}

	public String getUser_type() {
		return user_type;
	}

	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}

	public User() {
		
	}
	
	public User(int user_id, String fname, String lname, int age) {
		this.user_id = user_id;
		this.fname = fname;
		this.lname = lname;
		this.age = age;
	}
	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		//Validation used for mobile number
		Pattern p = Pattern.compile("\\d{10}");
		Matcher matcher = p.matcher(phone_number);

		if (matcher.find()) {
			this.phone_number = phone_number;
		} else
			System.out.println("Check the phone number");
		getPhoneNumberErrorMessage();
	}

	

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getEmail_id() {
		return email_id;
	}

	public void setEmail_id(String email_id) {
		//Validation to check the format of email ID
		Pattern p = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		Matcher matcher = p.matcher(email_id);
		if (matcher.find()) {
			this.email_id = email_id;
		} else
			System.out.println("The format of Email is Invalid");

	}

	public int getTotal_yrs_of_experience() {
		return total_yrs_of_experience;
	}

	public void setTotal_yrs_of_experience(int total_yrs_of_experience) {
		this.total_yrs_of_experience = total_yrs_of_experience;
	}

	public int getTotal_nm_of_stdnts_handled() {
		return total_nm_of_stdnts_handled;
	}

	public void setTotal_nm_of_stdnts_handled(int total_nm_of_stdnts_handled) {
		this.total_nm_of_stdnts_handled = total_nm_of_stdnts_handled;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getManager_id() {
		return manager_id;
	}

	public void setManager_id(int manager_id) {
		this.manager_id = manager_id;
	}

}
