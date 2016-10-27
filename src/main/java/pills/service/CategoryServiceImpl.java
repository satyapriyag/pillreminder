package pills.service;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import inti.ws.spring.exception.client.BadRequestException;
import pills.dao.CategoryDao;
import pills.entity.Category;
import pills.models.CategoryModel;
import pills.utilities.MappingUtility;

@Service
public class CategoryServiceImpl implements CategoryService {

	private static final Logger LOG = Logger.getLogger(AuthenticationServiceImpl.class);
	
	@Autowired
	private MappingUtility mapUtility;

	@Autowired
	private CategoryDao categoryDao;

	@Transactional
	public CategoryModel addCategory(String categoryName) throws BadRequestException {
		if (categoryName == null || categoryName.equals(""))
			throw new BadRequestException("Required parameters are either missing or invalid");
		Category category = new Category(categoryName);
		categoryDao.save(category);
		LOG.info("Category is added");
		return mapUtility.mapCategory(category);
	}

	@Transactional
	public void deleteCategory(Integer id) throws BadRequestException {
		if (id <= 0)
			throw new BadRequestException("Required parameters are either missing or invalid");
		Category category = new Category(id);
		categoryDao.delete(category);
		LOG.info("Category is deleted");
	}

	@Transactional
	public CategoryModel viewCategory(Integer id) throws BadRequestException {
		if (id < 0)
			throw new BadRequestException("Required parameters are either missing or invalid");
		return mapUtility.mapCategory(categoryDao.getById(id));
	}

	@Transactional
	public List<CategoryModel> viewAll() {
		return mapUtility.mapCategories(categoryDao.getAll());
	}

	@Transactional
	public CategoryModel updateCategory(Integer id, CategoryModel category) throws BadRequestException {
		if (category.getCategoryId() <= 0 || category.getCategoryName() == null
				|| category.getCategoryName().equals(""))
			throw new BadRequestException("Required parameters are either missing or invalid");
		Category updatedCategory = mapUtility.mapCategoryModel(category);
		categoryDao.update(updatedCategory);
		LOG.info("Category is updtaed");
		LOG.warn("Testing");
		return mapUtility.mapCategory(categoryDao.getById(id));
	}
}