package com.meruichi.yoyang.model;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
public class ItemReport {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Enumerated(EnumType.STRING)
	private ItemReportType reportType; // �������� �Ҹ�����

	@ManyToOne // �Ѹ��� �����ۿ� �������� ���� ���� �� �ִ�
	@JoinColumn(name = "item_id")
	private Item item; 
	
	@ManyToOne // �Ѹ��� ����ڰ� �������� ���� �� �� �ִ�
	@JoinColumn(name = "user_id")
	private User user; 
	
	@Column(nullable = false, length = 10)
	private Double itemQuantityReport; 
	
	@CreationTimestamp
	private Timestamp createDate;

	@Column(length = 200)
	private String itemReportComment;

}
