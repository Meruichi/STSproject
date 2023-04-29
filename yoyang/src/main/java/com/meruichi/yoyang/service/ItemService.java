package com.meruichi.yoyang.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meruichi.yoyang.model.Branch;
import com.meruichi.yoyang.model.Item;
import com.meruichi.yoyang.model.ItemReport;
import com.meruichi.yoyang.model.User;
import com.meruichi.yoyang.repository.ItemRepository;

@Service
public class ItemService{
	
	@Autowired
	private ItemRepository itemRepository;

	@Transactional
	public void �����۵��(Item item, User user) {
		Branch persistance = user.getBranch();
		item.setItemQuantity(0.0);
		item.setBranch(persistance);
		itemRepository.save(item);
	}
	@Transactional(readOnly = true)
	public Object �����۸��(Pageable pageable) {
		return itemRepository.findAll(pageable);
	}

	@Transactional
	public void �����ۼ���(int id, ItemReport itemReport) {
		Item persistance = itemRepository.findById(id)
				.orElseThrow(()->{
					return new IllegalArgumentException("������ã�� ����");
				});		
		String ReportType = itemReport.getReportType().toString();
		Double persistanceValue = persistance.getItemQuantity();
		Double itemReportValue = itemReport.getItemQuantityReport();
		if(ReportType == "STORE") {
			persistance.setItemQuantity(persistanceValue += itemReportValue);
		}else{persistance.setItemQuantity(persistanceValue -= itemReportValue);
		}
		itemRepository.save(persistance);		
	}

	public void �����ۻ���(int id) {
		itemRepository.deleteById(id);
	}

}
