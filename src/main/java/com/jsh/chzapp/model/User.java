package com.jsh.chzapp.model;

import java.sql.Timestamp;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idx;
	
	@Column(nullable = false, length = 30)
	private String id;
	
	@Column(nullable = false, length = 100)
	private String password;
	
	@Column(nullable = false, length = 30)
	private String name;
	
	@Column(nullable = false, length = 50)
	private String email;
	
	private int birthYear;
	
	private int birthMonth;
	
	private int birthDay;
	
	private String gender;
	
	private String address1;
	
	private String address2;
	
	private String addressDetail;
	
	@Enumerated(EnumType.STRING)
	private RoleType role;
	
	private boolean balid;
	
	@CreationTimestamp
	private Timestamp createDate;

}
