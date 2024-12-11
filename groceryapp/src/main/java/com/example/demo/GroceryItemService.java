package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroceryItemService {

    @Autowired
    private GroceryItemRepository groceryItemRepository;

    public List<GroceryItem> getAllItems(int page, int size) {
        return groceryItemRepository.findAll(PageRequest.of(page, size)).getContent();
    }

    public Optional<GroceryItem> getItemById(Long id) {
        return groceryItemRepository.findById(id);
    }

    public GroceryItem addItem(GroceryItem groceryItem) {
        return groceryItemRepository.save(groceryItem);
    }

    public Optional<GroceryItem> updateItem(Long id, GroceryItem groceryItem) {
        return groceryItemRepository.findById(id).map(existingItem -> {
            groceryItem.setId(id);
            return groceryItemRepository.save(groceryItem);
        });
    }

    public boolean deleteItem(Long id) {
        if (groceryItemRepository.existsById(id)) {
            groceryItemRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<GroceryItem> searchItems(String name, String category) {
        if (name != null && !name.isEmpty()) {
            return groceryItemRepository.findByNameContainingIgnoreCase(name);
        } else if (category != null && !category.isEmpty()) {
            return groceryItemRepository.findByCategoryContainingIgnoreCase(category);
        }
        return groceryItemRepository.findAll();
    }
}
