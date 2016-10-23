package pills.service;

import pills.models.CategoryModel;

import java.util.List;

import inti.ws.spring.exception.client.BadRequestException;

public interface CategoryService{
	public CategoryModel addCategory(String category) throws BadRequestException;
	public void deleteCategory(Integer id) throws BadRequestException;//Category category);
	public CategoryModel viewCategory(Integer id) throws BadRequestException;
	public List<CategoryModel> viewAll();
	public CategoryModel updateCategory(Integer categoryId,CategoryModel category) throws BadRequestException;
	
}