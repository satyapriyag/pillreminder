package pills.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pills.dao.AlternativeDao;
import pills.entity.Alternative;
import pills.models.PillModel;
//import pills.models.AddAlternativeModel;
//import pills.models.AlternativeModel;
import pills.utilities.MappingUtility;

@Service
@Transactional
public class AlternativeServiceImpl implements AlternativeService{
	
	@Autowired
	private MappingUtility mapUtility;
	
	@Autowired
	private AlternativeDao alternativeDao;
	
/*	public AlternativeModel addAlternative(AddAlternativeModel alternativeModel){
	    Alternative alternative = mapUtility.mapAddAlternativeModel(alternativeModel);
		alternativeDao.save(alternative);
		return mapUtility.mapAlternative(alternative);
	}
	public void deleteAlternative(Alternative alternative){
		alternativeDao.delete(alternative);
	}
	public AlternativeModel viewAlternative(Integer id){
		Alternative alternative =alternativeDao.getById(id);
		return mapUtility.mapAlternative(alternative);
	}
	public List<AlternativeModel> viewAll(){
		List<Alternative> alternatives =alternativeDao.getAll();
		return mapUtility.mapAlternatives(alternatives);
	}
	public void updateAlternative(AlternativeModel alternativeModel){
		Alternative alternative = mapUtility.mapAlternativeModel(alternativeModel);
		alternativeDao.update(alternative);
	}*/
	public List<PillModel> getByPillId(Integer pillId){
		System.out.println("pilll id "+pillId);
		List<Alternative> alternatives = alternativeDao.getByPillId(pillId);
		return mapUtility.mapAlternatives(alternatives);
	}

}