package pills.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pills.entity.Category;
import pills.models.CategoryModel;
import pills.utilities.MappingUtility;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private MappingUtility mapUtility;
	
	@Autowired
	private CategoryDao categoryDao;
	
	public CategoryModel addCategory(String categoryName){
		Category category= new Category(categoryName);
		categoryDao.save(category);
		return mapUtility.mapCategory(category);
	}
	public void deleteCategory(Category category){
		categoryDao.delete(category);
	}
	public CategoryModel viewCategory(Integer id){
		return mapUtility.mapCategory(categoryDao.getById(id));
	}
	public List<CategoryModel> viewAll(){
		return mapUtility.mapCategories(categoryDao.getAll());
	}
	public void updateCategory(CategoryModel category){
		categoryDao.update(mapUtility.mapCategoryModel(category));
	}
}