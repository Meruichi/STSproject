package com.meruichi.yoyang.model;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder 
@Entity
public class Board { // ��������

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
	private int id;
	
	@Column(nullable = false, length = 100)
	private String title;
	
	//@Lob �νľȵ� ��?
	@Column(columnDefinition = "LONGTEXT")
	private String content; // ���ӳ�Ʈ(���̺귯��. <html>�±װ� ������ �������̵�.)�� ��
	
	@ManyToOne(fetch = FetchType.EAGER) // �ϳ��� ����(�� admin)�� �������� ���� �� �� �ִ�.
	@JoinColumn(name="user_id")
	private User user; 

	@CreationTimestamp
	private Timestamp createDate;
	
}
