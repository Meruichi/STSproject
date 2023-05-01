package com.meruichi.yoyang.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meruichi.yoyang.model.Item;
import com.meruichi.yoyang.model.ItemReport;
import com.meruichi.yoyang.model.User;
import com.meruichi.yoyang.repository.ItemReportRepository;
import com.meruichi.yoyang.repository.ItemRepository;

@Service
public class ItemReportService {

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private ItemReportRepository itemReportRepository;

	@Transactional
	public void 아이템보고(int id, ItemReport itemReport, User user) {
		Item persistance = itemRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("아이템찾기 실패");
		});
		itemReport.setItem(persistance);
		itemReport.setUser(user);
		itemReportRepository.save(itemReport);
	}

	@Transactional(readOnly = true)
	public Object 아이템보고목록(Pageable pageable) {
		return itemReportRepository.findAll(pageable);
	}

	public void 아이템보고삭제(int id) {
		itemReportRepository.deleteById(id);
	}

}
