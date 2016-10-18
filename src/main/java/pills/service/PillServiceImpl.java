package pills.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		return mapUtility.mapPill(pillDao.getById(id));
	}
	public List<PillModel> viewAll(){
		return mapUtility.mapPills(pillDao.getAll());
	}
	public void updatePill(PillModel pill){
		pillDao.update(mapUtility.mapPillModel(pill));
	}
}