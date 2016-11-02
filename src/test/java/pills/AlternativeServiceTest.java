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
import pills.models.AddAlternativeModel;
import pills.models.AddPillModel;
import pills.models.AlternativeModel;
import pills.models.CategoryModel;
import pills.models.PillModel;
import pills.service.AlternativeService;
import pills.service.CategoryService;
import pills.service.PillService;


@RunWith(SpringRunner.class)
@SpringBootTest
@TestComponent
@ContextConfiguration(classes = {TestDatabaseConfig.class})
public class AlternativeServiceTest {
  @Autowired
  AlternativeService alternateService;

  @Test
  @Transactional
  @Rollback(true)
  public void alternativePillTest() {
    List<PillModel> pill = alternateService.getByPillId(2);
    assertEquals(Integer.valueOf(pill.get(0).getPillId()), Integer.valueOf(3));
  }
  
  @Test
  @Rollback(true)
  public void vieAlternativeTest() throws BadRequestException {
      AlternativeModel model = alternateService.viewAlternative(1);
      assertEquals((Integer)model.getAlternatePillId(),(Integer)3);
  }
  
  @Test
  @Transactional
  @Rollback(true)
  public void addCategoryTest() throws BadRequestException {

    // Save and Get Test
    AddAlternativeModel alternateModel = new AddAlternativeModel();
    alternateModel.setPillId(2);
    alternateModel.setAlternatePillId(4);

    AlternativeModel alternate =alternateService.addAlternative(alternateModel);
    assertTrue(alternate.getAltId() > 0);
  }
  
  @Test
  @Rollback(true)
  public void updateTest() throws BadRequestException {

    AlternativeModel model = alternateService.viewAlternative(1);
    model.setPillId(2);
    alternateService.updateAlternative(model);
    assertEquals((Integer)model.getPillId(),(Integer)2);
  }
  
  @Test
  @Rollback(true)
  public void deleteAlternateTest() throws BadRequestException {

       //Save and Get Test
    AddAlternativeModel alternateModel = new AddAlternativeModel();
    alternateModel.setPillId(2);
    alternateModel.setAlternatePillId(4);

    AlternativeModel alternate =alternateService.addAlternative(alternateModel);
    assertTrue(alternate.getAltId() > 0);
      Integer id = alternate.getAltId();
      alternateService.deleteAlternative(id);
  }
  
  @Test
  @Rollback(true)
  public void getAllAlternativeTest() throws BadRequestException {
      List<AlternativeModel> models = alternateService.viewAll();
      int size = models.size();

      AddAlternativeModel alternateModel = new AddAlternativeModel();
      alternateModel.setPillId(4);
      alternateModel.setAlternatePillId(2);

      AlternativeModel alternate =alternateService.addAlternative(alternateModel);
      assertTrue(alternate.getAltId() > 0);

      models = alternateService.viewAll();
      assertEquals(size + 1, models.size());
  }
  
}
