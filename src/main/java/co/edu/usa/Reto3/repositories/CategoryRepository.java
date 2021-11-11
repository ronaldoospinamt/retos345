/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.usa.Reto3.repositories;

import co.edu.usa.Reto3.models.Category;
import co.edu.usa.Reto3.repositories.crud.CategoryCrudRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * 
 */
@Repository
public class CategoryRepository {
    
    @Autowired
    private CategoryCrudRepository repo;
    
    public List<Category> getAll() {
        return (List<Category>) repo.findAll();
    }
    
    public Category save(Category category) {
        return repo.save(category);
    }
    
    public Optional<Category> getById(int id) {
        return repo.findById(id);
    }
    
    public String deleteById(int id){
        repo.deleteById(id);
        return "Registro con id " + id + " ha sido eliminado"; 
    }
    
    public void delete(Category category){
        repo.delete(category);
    }
}
