package com.jsh.chzapp.model;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
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
	private int id;

	@Lob
	private String content;
	
	@ColumnDefault("0")
	private int likeCount;
	
	@ColumnDefault("0")
	private int dislikeCount;
	
	@CreationTimestamp
	private Timestamp createDate;
	
	@Column(columnDefinition = "boolean default true")
	private boolean valid = true;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userId")
	private User user;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "postId")
	private Post post;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "replyId")
	private PostReply postReply;
	
	@Where( clause = "valid = true")
	@OneToMany(mappedBy = "postReply", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE) // mappedBy 연관관계 주인이 아님 -> DB에 FK를 만들지 않는다.
	@JsonIgnoreProperties({"postReply"})
	@OrderBy("id desc")
	private List<PostReply> rereplys;
	
	
}
