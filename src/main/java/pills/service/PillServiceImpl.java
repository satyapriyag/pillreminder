package pills.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pills.dao.PillDao;
import pills.entity.Pill;
import pills.models.AddPillModel;
import pills.models.PillModel;
import pills.utilities.MappingUtility;

@Service
@Transactional
public class PillServiceImpl implements PillService{
	
	@Autowired
	private MappingUtility mapUtility;
	
	@Autowired
	private PillDao pillDao;
	
	public PillModel addPill(AddPillModel pillModel){
	    Pill pill = mapUtility.mapAddPillModel(pillModel);
		pillDao.save(pill);
		return mapUtility.mapPill(pill);
	}
	public void deletePill(Pill pill){
		pillDao.delete(pill);
	}
	public PillModel viewPill(Integer id){
		Pill pill =pillDao.getById(id);
		return mapUtility.mapPill(pill);
	}
	public List<PillModel> viewAll(){
		List<Pill> pills =pillDao.getAll();
		return mapUtility.mapPills(pills);
	}
	public void updatePill(PillModel pillModel){
		Pill pill = mapUtility.mapPillModel(pillModel);
		pillDao.update(pill);
	}
	public List<PillModel> getPillsForCategory(Integer categoryId){
		List<Pill> pills = pillDao.getByCategoryId(categoryId);
		return mapUtility.mapPills(pills);
	}

}