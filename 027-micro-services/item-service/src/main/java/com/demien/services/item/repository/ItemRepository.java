package com.demien.services.item.repository;

import com.demien.services.item.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface ItemRepository extends JpaRepository<Item, String> {


}
