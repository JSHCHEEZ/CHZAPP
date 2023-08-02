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
	private int USE_Idx;
	
	@Column(nullable = false, length = 30)
	private String USE_Id;
	
	@Column(nullable = false, length = 100)
	private String USE_Password;
	
	@Column(nullable = false, length = 30)
	private String USE_Name;
	
	@Column(nullable = false, length = 50)
	private String USE_Email;
	
	private int USE_BirthYear;
	
	private int USE_BirthMonth;
	
	private int USE_BirthDay;
	
	private String USE_Gender;
	
	private String USE_Address1;
	
	private String USE_Address2;
	
	private String USE_AddressDetail;
	
	@Enumerated(EnumType.STRING)
	private RoleType USE_ROLE;
	
	private boolean USE_IsValid;
	
	@CreationTimestamp
	private Timestamp USE_CreateDate;
	
	private Timestamp USE_UpdateDate;
	
	@ColumnDefault("0")
	private int UPT_Idx;
	
	private String DEL_LOG;
}
