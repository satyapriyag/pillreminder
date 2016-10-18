package pills.service;

import pills.entity.Category;
import pills.models.CategoryModel;

import java.util.List;

public interface CategoryService{
	public CategoryModel addCategory(String category);
	public void deleteCategory(Category category);
	public CategoryModel viewCategory(Integer id);
	public List<CategoryModel> viewAll();
	public void updateCategory(CategoryModel category);
}