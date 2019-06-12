package com.demien.sprcloud.itemservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.demien.sprcloud.itemservice.domain.Item;

@Component
public interface ItemRepository extends JpaRepository<Item, String> {


}
