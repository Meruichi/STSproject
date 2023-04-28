package com.meruichi.yoyang.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meruichi.yoyang.model.Item;

public interface ItemRepository extends JpaRepository<Item, Integer>{
	Optional<Item> findById(int id);
}
