package pills.service;

import pills.models.Category;
import pills.models.CategoryModel;

import java.util.List;

public interface CategoryService{
	public CategoryModel addCategory(String category);
	public void deleteCategory(Category category);
	public CategoryModel viewCategory(Long id);
	public List<CategoryModel> viewAll();
	public void updateCategory(CategoryModel category);
}