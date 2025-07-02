package com.ecommerce.backend.service;

import com.ecommerce.backend.model.Category;
import com.ecommerce.backend.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CategoryService {


    @Autowired
    private CategoryRepo categoryRepo;

    public Category  addCategory(Category category){
        return categoryRepo.save(category);

    }

    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }

    public Optional<Category> getCategoryById(Long id) {
        return categoryRepo.findById(id);
    }

    public Category updateCategory(Long id, Category updatedCategory) {
        return categoryRepo.findById(id)
                .map(existingCategory -> {
                    existingCategory.setName(updatedCategory.getName());
                    existingCategory.setDescription(updatedCategory.getDescription());
                    return categoryRepo.save(existingCategory);
                })
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
    }


    public void deleteCategory(Long id) {
        categoryRepo.deleteById(id);
    }
}
