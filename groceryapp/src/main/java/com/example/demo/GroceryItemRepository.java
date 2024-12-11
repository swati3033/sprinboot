package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroceryItemRepository extends JpaRepository<GroceryItem, Long> {
    List<GroceryItem> findByNameContainingIgnoreCase(String name);
    List<GroceryItem> findByCategoryContainingIgnoreCase(String category);
}