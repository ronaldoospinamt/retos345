/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.usa.Reto3.services;

import java.util.List;
import org.springframework.stereotype.Service;
import co.edu.usa.Reto3.models.Category;
import co.edu.usa.Reto3.repositories.CategoryRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Alexander Arango
 */
@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repoCategory;

    //Create
    public Category saveCategory(Category category) {
        Category categoryNew = repoCategory.save(category);
        return getCategoryById(categoryNew.getId());
    }

    //Read
    public List<Category> getAll() {
        return repoCategory.getAll();
    }

    //Update
    public Category updateCategory(Category category) {        
        if (category.getId() != null){
            Optional<Category> categoryConsultada = repoCategory.getById(category.getId());
            if (categoryConsultada.isPresent()){
                categoryConsultada.get().setDescription(category.getDescription());
                categoryConsultada.get().setName(category.getName());
                categoryConsultada.get().setComputer(category.getComputer());
                return repoCategory.save(categoryConsultada.get());
            }
        }
        return category;
    }

    //Delete
    public boolean deleteCategory(int id) {
        boolean del = repoCategory.getById(id).map(category -> {
            repoCategory.delete(category);
            return true;
        }).orElse(false);
        return del;
    }

    //Filtro por ID
    public Category getCategoryById(int id) {
        Optional<Category> admin = repoCategory.getById(id);
        return admin.orElse(new Category());
    }
}
