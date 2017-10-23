/**
 * 
 */
package com.hibernate.entity;

import javax.persistence.*;

/**
 * @author phn
 */
@Table(name="user")
@Entity
public class UserEntity {
	private int id;
	private String username;
	private String password;
	private boolean sex; // 默认为0；0表示男，1表示女

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, length = 9)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "username", nullable = false, length = 20)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "password", nullable = false, length = 32)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "sex", nullable = false, length = 1)
	public boolean isSex() {
		return sex;
	}

	public void setSex(boolean sex) {
		this.sex = sex;
	}

	@Override
	public String toString() {
		return "User [ \nid : " + this.id + "\nusername : " + this.username
				+ "\npassword : " + this.password + "\nsex : " + this.sex
				+ " ]";
	}

}
