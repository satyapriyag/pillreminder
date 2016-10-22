/**
 * 
 */
package pills.service;

import java.util.List;

import pills.entity.Alternative;
//import pills.models.AddAlternativeModel;
//import pills.models.AlternativeModel;
import pills.models.PillModel;

/**
 * @author satya
 *
 */
public interface AlternativeService{
	/*public AlternativeModel addAlternative(AddAlternativeModel alternative);
	public void deleteAlternative(Alternative alternative);
	public List<AlternativeModel> viewAll();
	public AlternativeModel viewAlternative(Integer id);
	public void updateAlternative(AlternativeModel alternative);*/
	public List<PillModel> getByPillId(Integer pillId);
}
