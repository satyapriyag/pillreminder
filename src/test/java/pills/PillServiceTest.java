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
import org.springframework.boot.test.context.TestComponent;
import org.springframework.orm.hibernate5.HibernateObjectRetrievalFailureException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import inti.ws.spring.exception.client.BadRequestException;
import pills.models.AddPillModel;
import pills.models.PillModel;
import pills.service.PillService;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestComponent
@ContextConfiguration(classes={TestDatabaseConfig.class})
public class PillServiceTest{
	@Autowired
	private PillService pillService;
	
	//Test Cases For Pill Service
	
	@Test
	@Transactional
	@Rollback(true)
	public void addPillTest() throws BadRequestException {
		
		AddPillModel addPill = new AddPillModel();
		addPill.setPillName("AddPillTest");
		addPill.setPillCategoryId(1);
		// Save and Get Test
		PillModel pillModel = pillService.addPill(addPill);

		assertTrue(pillModel.getPillId() > 0);
	}
	
	@Test
	@Rollback(true)
	public void deletePillTest() throws BadRequestException {

		 //Save and Get Test
		AddPillModel addPillModel = new AddPillModel();
		addPillModel.setPillName("deleteTest");
		addPillModel.setPillCategoryId(1);
		PillModel pillModel = pillService.addPill(addPillModel);
		assertTrue(pillModel.getPillId() > 0);
		Integer pillId = pillModel.getPillId();
		pillService.deletePill(pillId);
	}
	
	@Test
	@Rollback(true)
	public void getAllPillsTest() throws BadRequestException {
		List<PillModel> pillModels = pillService.viewAll();
		int size = pillModels.size();

		AddPillModel addPill = new AddPillModel();
		addPill.setPillName("AddPillTest");
		addPill.setPillCategoryId(3);
		// Save and Get Test
		PillModel pillModel = pillService.addPill(addPill);
		assertTrue(pillModel.getPillId() > 0);

		pillModels = pillService.viewAll();
		assertEquals(size + 1, pillModels.size());
	}
	


	@Test(expected = BadRequestException.class)
	@Transactional
	@Rollback(true)
	public void deletePillInvalidId() throws BadRequestException {
		pillService.deletePill(-1);
	}


	@Test
	@Transactional
	@Rollback(true)
	public void updatePillNameTest() throws BadRequestException {

		PillModel pillModel = new PillModel();
		pillModel.setPillId(1);
		pillModel.setPillName("Antibiotic");
		pillModel.setPillCategoryId(3);
		pillService.updatePill(pillModel);
		pillModel = pillService.viewPill(1);

		assertEquals(pillModel.getPillName(),"Antibiotic");
	}

	@Test
	@Transactional
	@Rollback(true)
	public void updatePillCategoryTest() throws BadRequestException {

		PillModel pillModel = new PillModel();
		pillModel.setPillId(1);
		pillModel.setPillName("Antibiotic");
		pillModel.setPillCategoryId(3);
		pillService.updatePill(pillModel);
		pillModel = pillService.viewPill(1);

		assertEquals(pillModel.getPillCategoryId(),(Integer)3);
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void getPillsForCategoryTest() throws BadRequestException{
		List<PillModel>getPills = pillService.getPillsForCategory(1);
		assertEquals(getPills.size(),4);
	}
}