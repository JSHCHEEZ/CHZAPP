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
public class PostReply {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int POR_Idx;
	
	@ColumnDefault("0")
	private int POR_PIdx;
	
	@Lob
	private String POR_Content;
	
	@ColumnDefault("0")
	private int POR_Like;
	
	@ColumnDefault("0")
	private int POR_Dislike;
	
	@CreationTimestamp
	private Timestamp POR_CreateDate;
	
	private Timestamp POR_UpdateDate;
	
	@ColumnDefault("0")
	private int UPT_Idx;
	
	private String DEL_LOG;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "USE_Idx")
	private User user;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "POS_Idx")
	private Post post;
	
}
