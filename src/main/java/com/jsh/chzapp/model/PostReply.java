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
	private int idx;
	
	@ColumnDefault("0")
	private int parentIdx;
	
	@Lob
	private String content;
	
	@ColumnDefault("0")
	private int likeCount;
	
	@ColumnDefault("0")
	private int dislikeCount;
	
	@CreationTimestamp
	private Timestamp createDate;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userIdx")
	private User user;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "postIdx")
	private Post post;
	
}
