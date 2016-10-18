package pills.utilities;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import pills.entity.Category;
import pills.entity.Pill;
import pills.models.AddPillModel;
import pills.models.CategoryModel;
import pills.models.PillModel;

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
		categoryModel.setCategoryId(category.getCategoryId());
		categoryModel.setCategoryName(category.getCategoryName());
		return categoryModel;
	}
	
	public Category mapCategoryModel(CategoryModel categoryModel){
		Category category = new Category();
		category.setCategoryId(categoryModel.getCategoryId());
		category.setCategoryName(categoryModel.getCategoryName());
		return category;
	}
	public List<PillModel> mapPills(List<Pill> pills) {
		List<PillModel> pillModels = new ArrayList<>();
		for (Pill pill : pills) {
			pillModels.add(mapPill(pill));
		}
		return pillModels;
	}
	
	public PillModel mapPill(Pill pill) {
		PillModel pillModel = new PillModel();
		pillModel.setPillId(pill.getPillId());
		pillModel.setPillName(pill.getPillName());
		pillModel.setPillCategoryId(pill.getCategory().getCategoryId());
		return pillModel;
	}
	
	public Pill mapPillModel(PillModel pillModel){
		Pill pill = new Pill();
		pill.setPillId(pillModel.getPillId());
		pill.setPillName(pillModel.getPillName());
		pill.setCategory(new Category(pillModel.getPillCategoryId()));
		return pill;
	}
	
	public Pill mapAddPillModel(AddPillModel pillModel){
		Pill pill = new Pill();
		pill.setPillName(pillModel.getPillName());
		pill.setCategory(new Category(pillModel.getPillCategoryId()));
		return pill;
	}
}
