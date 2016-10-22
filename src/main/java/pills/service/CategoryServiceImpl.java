package pills.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import inti.ws.spring.exception.client.BadRequestException;
import pills.dao.CategoryDao;
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
	
	public CategoryModel addCategory(String categoryName) throws BadRequestException{
		if (categoryName == null || categoryName.equals(""))
			throw new BadRequestException("Required parameters are either missing or invalid");
		Category category= new Category(categoryName);
		categoryDao.save(category);
		return mapUtility.mapCategory(category);
	}
	public void deleteCategory(Integer id) throws BadRequestException{
		if (id <= 0)
			throw new BadRequestException("Required parameters are either missing or invalid");
		Category category = new Category(id);
		categoryDao.delete(category);
	}
	public CategoryModel viewCategory(Integer id) throws BadRequestException{
		if (id <= 0)
			throw new BadRequestException("Required parameters are either missing or invalid");
		return mapUtility.mapCategory(categoryDao.getById(id));
	}
	public List<CategoryModel> viewAll(){
		return mapUtility.mapCategories(categoryDao.getAll());
	}
	public CategoryModel updateCategory(Integer id,CategoryModel category) throws BadRequestException{
		if (category.getCategoryId() <= 0 || category.getCategoryName()==null || category.getCategoryName().equals(""))
			throw new BadRequestException("Required parameters are either missing or invalid");
		Category updatedCategory = mapUtility.mapCategoryModel(category);
		categoryDao.update(updatedCategory);
		return mapUtility.mapCategory(categoryDao.getById(id));
	}
}