package com.meruichi.yoyang.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meruichi.yoyang.model.Branch;

public interface BranchRepository extends JpaRepository<Branch, Integer>{
}
