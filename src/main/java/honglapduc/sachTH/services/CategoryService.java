package honglapduc.sachTH.services;

import honglapduc.sachTH.entity.Book;
import honglapduc.sachTH.entity.Category;
import honglapduc.sachTH.repository.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CategoryService {

    @Autowired
    private ICategoryRepository categoryRepository;
    public List<Category> getAllCategories(){ return categoryRepository.findAll();}
    public Category getCategoryById(Long id){
        return  categoryRepository.findById(id).orElse(null);
    }
    public Category saveCategory(Category category){ return categoryRepository.save(category);}
    public void deleteCategory(Long id) { categoryRepository.deleteById(id);}

    public void addCategory(Category category){
        categoryRepository.save(category);
    }

    public void updateCategory(Category category){
        categoryRepository.save(category);
    }

}
