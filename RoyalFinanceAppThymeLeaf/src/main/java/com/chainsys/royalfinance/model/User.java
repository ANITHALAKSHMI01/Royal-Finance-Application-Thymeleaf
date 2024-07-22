package com.chainsys.royalfinance.model;
public class User 
{
	private String id;
	private String name;
	private String role;
	private String dateOfBirth;
	private long phoneNo;
	private String email;
	private String password;
	private String location;
	private int status;
	private int active;
	public User()
	{
		
	}
	public User(String id,String name,String role,String dateOfBirth,long phoneNo,String email,String password,String location)
	{
		this.id=id;
		this.name=name;
		this.role=role;
		this.dateOfBirth=dateOfBirth;
		this.phoneNo=phoneNo;
		this.email=email;
		this.password=password;
		this.location=location;
	}
	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	public String getRole()
	{
		return role;
	}
	public void setRole(String role)
	{
		this.role = role;
	}
	public String getDateOfBirth()
	{
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth)
	{
		this.dateOfBirth = dateOfBirth;
	}
	public long getPhoneNo()
	{
		return phoneNo;
	}
	public void setPhoneNo(long phoneNo)
	{
		this.phoneNo = phoneNo;
	}
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email) 
	{
		this.email = email;
	}
	public String getPassword() 
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	public String getLocation() 
	{
		return location;
	}
	public void setLocation(String location)
	{
		this.location = location;
	}
	public int getStatus() 
	{
		return status;
	}
	public void setStatus(int status)
	{
		this.status = status;
	}
	public int getActive() 
	{
		return active;
	}
	public void setActive(int active) 
	{
		this.active = active;
	}
	@Override
	public String toString()
	{
		return "User [id=" + id + ", name=" + name + ", role=" + role + ", dateOfBirth=" + dateOfBirth + ", phoneNo="
				+ phoneNo + ", email=" + email + ", password=" + password + ", location=" + location + ", status="
				+ status + ", active=" + active + "]";
	}
}
