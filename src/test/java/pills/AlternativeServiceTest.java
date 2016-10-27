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

import inti.ws.spring.exception.client.BadRequestException;
import pills.models.AddPillModel;
import pills.models.CategoryModel;
import pills.models.PillModel;
import pills.service.AlternativeService;
import pills.service.CategoryService;
import pills.service.PillService;


@RunWith(SpringRunner.class)
@SpringBootTest
@TestComponent
@ContextConfiguration(classes={TestDatabaseConfig.class})
public class AlternativeServiceTest{
	@Autowired
	AlternativeService alternateService;
	
	@Test
	@Transactional
	@Rollback(true)
	public void alternativePillTest() {
		List<PillModel> pill = alternateService.getByPillId(2);
		assertEquals(Integer.valueOf(pill.get(0).getPillId()), Integer.valueOf(3));
	}
}