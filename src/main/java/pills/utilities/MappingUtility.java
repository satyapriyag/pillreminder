package pills.utilities;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import pills.models.Category;
import pills.models.CategoryModel;

@Service
public class MappingUtility {

	public List<CategoryModel> mapCategories(List<Category> categories) {
		List<CategoryModel> categoryModels = new ArrayList<>();
		for (Category category : categories) {
			categoryModels.add(mapCategory(category));
		}
		return categoryModels;
	}
	
	public CategoryModel mapCategory(Category category) {
		CategoryModel categoryModel = new CategoryModel();
		categoryModel.setCategoryId(category.getId());
		categoryModel.setCategoryName(category.getName());
		return categoryModel;
	}
	
	public Category mapCategoryModel(CategoryModel categoryModel){
		Category category = new Category();
		category.setId(categoryModel.getCategoryId());
		category.setName(categoryModel.getCategoryName());
		return category;
	}
}
