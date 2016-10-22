package pills;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import inti.ws.spring.exception.client.BadRequestException;
import pills.models.CategoryModel;
import pills.service.CategoryService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceTest{
	@Autowired
	private CategoryService service;
	
	
	/*@Test
	@Transactional
	@Rollback(true)
	public void deleteCategoryTest() throws BadRequestException {

		/* //Save and Get Test
		CategoryModel categoryModel = service.addCategory("Pain Killers");
		assertTrue(categoryModel.getCategoryId() > 0);
		CategoryModel categoryModel;
		service.deleteCategory(1);
		categoryModel = service.viewCategory(1);
		System.out.println(categoryModel);
		assertNull(categoryModel);

	}*/

	@Test(expected = BadRequestException.class)
	@Transactional
	@Rollback(true)
	public void deleteCategoryInvalidId() throws BadRequestException {
		service.deleteCategory(-1);
	}

	@Test
	@Transactional
	@Rollback(true)
	public void addCategoryTest() throws BadRequestException {

		// Save and Get Test
		CategoryModel categoryModel = service.addCategory("Pain Killers");

		assertTrue(categoryModel.getCategoryId() > 0);
	}


	@Test
	@Transactional
	@Rollback(true)
	public void updateCategoryNameTest() throws BadRequestException {

		CategoryModel categoryModel = new CategoryModel();
		categoryModel.setCategoryId(1);
		categoryModel.setCategoryName("Antibiotic");
		service.updateCategory(1, categoryModel);
		categoryModel = service.viewCategory(1);

		assertEquals(categoryModel.getCategoryName(),"Antibiotic");
	}


	@Test
	@Transactional
	@Rollback(true)
	public void getAllCategorysTest() throws BadRequestException {
		List<CategoryModel> categoryModels = service.viewAll();
		int size = categoryModels.size();

		// Save and Get Test
		CategoryModel categoryModel = service.addCategory("GetAllTest");
		assertTrue(categoryModel.getCategoryId() > 0);

		categoryModels = service.viewAll();
		assertEquals(size + 1, categoryModels.size());
	}
}