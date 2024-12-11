package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/grocery-items")
public class GroceryItemController {

    @Autowired
    private GroceryItemService groceryItemService;

    // Retrieve all grocery items with pagination
    @GetMapping
    public ResponseEntity<List<GroceryItem>> getAllItems(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(groceryItemService.getAllItems(page, size));
    }

    // Retrieve a specific item by ID
    @GetMapping("/{id}")
    public ResponseEntity<GroceryItem> getItemById(@PathVariable Long id) {
        return groceryItemService.getItemById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Add a new grocery item
    @PostMapping
    public ResponseEntity<GroceryItem> addItem(@Validated @RequestBody GroceryItem groceryItem) {
        GroceryItem createdItem = groceryItemService.addItem(groceryItem);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdItem);
    }

    // Update an existing grocery item
    @PutMapping("/{id}")
    public ResponseEntity<GroceryItem> updateItem(@PathVariable Long id,
                                                   @Validated @RequestBody GroceryItem groceryItem) {
        return groceryItemService.updateItem(id, groceryItem)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Delete a grocery item
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        if (groceryItemService.deleteItem(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Search for items by name or category
    @GetMapping("/search")
    public ResponseEntity<List<GroceryItem>> searchItems(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String category) {
        return ResponseEntity.ok(groceryItemService.searchItems(name, category));
    }

    // Removed the duplicate method that caused the error
}
