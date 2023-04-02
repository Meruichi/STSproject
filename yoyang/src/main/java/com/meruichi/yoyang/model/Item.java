package com.meruichi.yoyang.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne // �Ѹ��� ������ �������� �������� ���� �� �ִ�.
	@JoinColumn(name = "branch_id")
	private Branch branch;
	
	@Column(nullable = false, length = 20)
	private String itemCategory; // �Ҹ�ǰ, ������ǰ, ��Ÿ���
	
	@Column(nullable = false, length = 50)
	private String itemName; 
	
	@Column(nullable = false, length = 10, columnDefinition="Double default 0")
	private Double itemQuantity; 

	@Column(length = 200)
	private String itemListComment;

}
