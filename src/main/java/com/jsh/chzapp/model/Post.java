package com.jsh.chzapp.model;

import java.sql.Timestamp;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
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
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int POS_Idx;
	
	@Lob
	private String POS_Content;
	
	@ColumnDefault("0")
	private int POS_Like;
	
	@ColumnDefault("0")
	private int POS_Dislike;
	
	private String POS_Tag;
	
	private boolean POS_IsValid;
	
	@CreationTimestamp
	private Timestamp POS_CreateDate;
	
	private Timestamp POS_UpdateDate;
	
	@ColumnDefault("0")
	private int UPT_Idx;
	
	private String DEL_LOG;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "USE_Idx")
	private User user;
}
