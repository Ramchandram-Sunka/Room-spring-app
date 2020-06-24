package com.roommanagement.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.roommanagement.demo.repository.model.ItemDetails;

public interface ItemRepository extends JpaRepository<ItemDetails, Long>{

}
