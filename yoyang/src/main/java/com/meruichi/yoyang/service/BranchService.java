package com.meruichi.yoyang.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.meruichi.yoyang.model.Branch;
import com.meruichi.yoyang.repository.BranchRepository;

@Service
public class BranchService {

	@Autowired
	private BranchRepository branchRepository;

	@Transactional
	public void �������(Branch branch) {
		branchRepository.save(branch);
	}

	@Transactional(readOnly = true)
	public Object �������(Pageable pageable) {
		return branchRepository.findAll(pageable);
	}
}
