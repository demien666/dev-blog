package com.demien.sboot.repository;

import com.demien.sboot.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "CategoryRepository")
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("FROM CATEGORY where CATEGORY_NAME like %?1% ")
    List<Category> findByName(String name);
}
