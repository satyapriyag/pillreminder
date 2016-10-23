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
import pills.models.AddPillModel;
import pills.models.PillModel;
import pills.service.PillService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PillServiceTest{
	@Autowired
	private PillService service;
	
	
	/*@Test
	@Transactional
	@Rollback(true)
	public void deletePillTest() throws BadRequestException {

		/* //Save and Get Test
		PillModel pillModel = service.addPill("Pain Killers");
		assertTrue(pillModel.getPillId() > 0);
		PillModel pillModel;
		service.deletePill(1);
		pillModel = service.viewPill(1);
		System.out.println(pillModel);
		assertNull(pillModel);

	}*/

	@Test(expected = BadRequestException.class)
	@Transactional
	@Rollback(true)
	public void deletePillInvalidId() throws BadRequestException {
		service.deletePill(-1);
	}

	@Test
	@Transactional
	@Rollback(true)
	public void addPillTest() throws BadRequestException {
		
		AddPillModel addPill = new AddPillModel();
		addPill.setPillName("AddPillTest");
		addPill.setPillCategoryId(3);
		// Save and Get Test
		PillModel pillModel = service.addPill(addPill);

		assertTrue(pillModel.getPillId() > 0);
	}


	@Test
	@Transactional
	@Rollback(true)
	public void updatePillNameTest() throws BadRequestException {

		PillModel pillModel = new PillModel();
		pillModel.setPillId(1);
		pillModel.setPillName("Antibiotic");
		pillModel.setPillCategoryId(3);
		service.updatePill(pillModel);
		pillModel = service.viewPill(1);

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
		service.updatePill(pillModel);
		pillModel = service.viewPill(1);

		assertEquals(pillModel.getPillCategoryId(),(Integer)3);
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void getAllPillsTest() throws BadRequestException {
		List<PillModel> pillModels = service.viewAll();
		int size = pillModels.size();

		AddPillModel addPill = new AddPillModel();
		addPill.setPillName("AddPillTest");
		addPill.setPillCategoryId(3);
		// Save and Get Test
		PillModel pillModel = service.addPill(addPill);
		assertTrue(pillModel.getPillId() > 0);

		pillModels = service.viewAll();
		assertEquals(size + 1, pillModels.size());
	}
}