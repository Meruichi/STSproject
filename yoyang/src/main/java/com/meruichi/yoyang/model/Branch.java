package com.meruichi.yoyang.model;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Branch {

	@Id // P키
	@GeneratedValue(strategy = GenerationType.IDENTITY)  
	private int id; // auto_increment
	
	@Column(nullable = false, length = 20, unique = true)
	private String branchName; 
	
	@Column(nullable = false, length = 50)
	private String branchAddress;
	  
	@Column(nullable = false, length = 20)
	private String branchDirectNumber;
	
	@CreationTimestamp 
	private Timestamp createDate; 
	
	@Column(nullable = false, columnDefinition="boolean default false")
	private boolean branchWithdrawn; // 지점 폐점여부
	
	
}
