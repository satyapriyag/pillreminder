package pills.service;

import pills.models.AddPillModel;
import pills.models.PillModel;

import java.util.List;

import inti.ws.spring.exception.client.BadRequestException;
/**
 * Interface handling methods pertaining to Pill Service
 * @author satya
 *
 */

public interface PillService{
  /**
   * Method to add pill to the repository
   * @param pill {@link AddPillModel}  Details of the pill to be added
   * @return {@link PillModel}
   * @throws BadRequestException Thrown when pillName is null or empty
   */
	public PillModel addPill(AddPillModel pill) throws BadRequestException;
	
    /**
     * Method to delete pill from the repository
     * @param id Id of the pill to be deleted
     * @throws BadRequestException Thrown when id is null or non-integer value
     */
	public void deletePill(Integer id) throws BadRequestException;
	
    /**
     * Method to view details of a particular pill
     * @param id of the pill to be viewed
     * @return {@link PillModel} contains all the details of pill 
     * @throws BadRequestException Thrown when id is null or non-integer value
     */
	public PillModel viewPill(Integer id) throws BadRequestException;
	
    /**
     * Method to retrieve all the pills from the repository
     * @return List of {@link PillModel}
     */ 
	public List<PillModel> viewAll() ;
	
    /**
     * Method to update the details of the pill
     * @param pill {@link PillModel} Details to be updated
     */
	public void updatePill(PillModel pill);
	
    /**
     * Method to retrieve the pills for particular category
     * @param categoryId Id of the category
     * @return List of {@link PillModel} List of pills for a particular category
     * @throws BadRequestException Thrown when categoryId is null or non-integer value
     */
	public List<PillModel> getPillsForCategory(Integer categoryId) throws BadRequestException;
}