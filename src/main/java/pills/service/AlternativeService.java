/**
 * Interface for AlternativePill Service that handles all the services related to {@link AlternativeModel}
 */
package pills.service;

import java.util.List;

import pills.models.AddAlternativeModel;
import pills.models.AlternativeModel;
import pills.models.PillModel;

/**
 * @author satya
 *
 */
public interface AlternativeService{
	public AlternativeModel addAlternative(AddAlternativeModel alternative);
	public void deleteAlternative(Integer id);
	public List<AlternativeModel> viewAll();
	public AlternativeModel viewAlternative(Integer id);
	public void updateAlternative(AlternativeModel alternative);
    
  /**
   * Method to retrieve the alternate pills for particular pill 
   * @param pillId Id of the pill for which alternative pills need to be retrieved
   * @return List of {@link PillModel} List of alternate pills
   */
	public List<PillModel> getByPillId(Integer pillId);
}
