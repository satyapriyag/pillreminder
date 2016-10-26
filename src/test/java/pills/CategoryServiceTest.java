package pills;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.orm.hibernate5.HibernateObjectRetrievalFailureException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.hibernate.ObjectNotFoundException;

import inti.ws.spring.exception.client.BadRequestException;
import pills.models.AddPillModel;
import pills.models.CategoryModel;
import pills.models.PillModel;
import pills.service.CategoryService;
import pills.service.PillService;


@RunWith(SpringRunner.class)
@SpringBootTest
@TestComponent
@ContextConfiguration(classes={TestDatabaseConfig.class})
public class CategoryServiceTest{
	@Autowired
	private CategoryService service;
	
	@Autowired
	private PillService pillService;

	//@Test(expected = HibernateObjectRetrievalFailureException.class)
	@Test(expected = ObjectNotFoundException.class)
	@Rollback(true)
	public void deleteCategoryTest() throws BadRequestException {

		//Save and Get Test
		CategoryModel categoryModel = service.addCategory("Pain Killers");
		assertTrue(categoryModel.getCategoryId() > 0);

		service.deleteCategory(categoryModel.getCategoryId());
		categoryModel = service.viewCategory(categoryModel.getCategoryId());

	}

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
		CategoryModel categoryModel = service.addCategory("Anti Biotics");

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
	public void getAllCategorysTest() throws BadRequestException {
		List<CategoryModel> categoryModels = service.viewAll();
		int size = categoryModels.size();

		// Save and Get Test
		CategoryModel categoryModel = service.addCategory("GetAllTest");
		assertTrue(categoryModel.getCategoryId() > 0);

		categoryModels = service.viewAll();
		assertEquals(size + 1, categoryModels.size());
	}
	
	@Test
	public void getOneTest() throws BadRequestException{
		CategoryModel category = service.viewCategory(1);
		assertEquals(category.getCategoryName(),"test");
	}
	
}