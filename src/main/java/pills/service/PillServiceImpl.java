package pills.service;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import inti.ws.spring.exception.client.BadRequestException;
import pills.dao.PillDao;
import pills.entity.Category;
import pills.entity.Pill;
import pills.models.AddPillModel;
import pills.models.PillModel;
import pills.utilities.MappingUtility;

@Service
@Transactional
public class PillServiceImpl implements PillService {

	private static final Logger LOG = Logger.getLogger(PillServiceImpl.class);
	
	@Autowired
	private MappingUtility mapUtility;

	@Autowired
	private PillDao pillDao;

	public PillModel addPill(AddPillModel pillModel) throws BadRequestException {
		if (pillModel.getPillName() == null || pillModel.getPillName().equals(""))
			throw new BadRequestException("Required parameters are either missing or invalid");
		Pill pill = mapUtility.mapAddPillModel(pillModel);
		pillDao.save(pill);
		LOG.info("Pill with pillId =" + pill.getPillId() +" is added");
		return mapUtility.mapPill(pill);
	}

	public void deletePill(Integer id) throws BadRequestException {
		if (id <= 0)
			throw new BadRequestException("Required parameters are either missing or invalid");
		Pill pill = new Pill(id);
		pillDao.delete(pill);
		LOG.info("Pill with pillId =" + id +" is deleted");
	}

	public PillModel viewPill(Integer id) throws BadRequestException {
		if (id <= 0)
			throw new BadRequestException("Required parameters are either missing or invalid");
		Pill pill = pillDao.getById(id);
		return mapUtility.mapPill(pill);
	}

	public List<PillModel> viewAll() {
		List<Pill> pills = pillDao.getAll();
		return mapUtility.mapPills(pills);
	}

	public void updatePill(PillModel pillModel) {
		Pill pill = mapUtility.mapPillModel(pillModel);
		pillDao.update(pill);
		LOG.info("Pill with pillId =" + pillModel.getPillId() +" is updated");
	}

	public List<PillModel> getPillsForCategory(Integer categoryId) throws BadRequestException {
		if (categoryId <= 0)
			throw new BadRequestException("Required parameters are either missing or invalid");
		List<Pill> pills = pillDao.getByCategoryId(categoryId);
		return mapUtility.mapPills(pills);
	}

}