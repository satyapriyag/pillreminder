package pills.service;

import pills.models.CategoryModel;

import java.util.List;

import inti.ws.spring.exception.client.BadRequestException;
/**
 * Interface handling methods pertaining to category Service
 * @author satya
 *
 */
public interface CategoryService{
  
  /**
   * Method to add category to the repository
   * @param category  Category Name to be added
   * @return {@link CategoryModel}
   * @throws BadRequestException Thrown when categoryName is null or empty
   */
	public CategoryModel addCategory(String category) throws BadRequestException;
	
    /**
     * Method to delete category from the repository
     * @param id Id of the category to be deleted
     * @throws BadRequestException Thrown when id is null or non-integer value
     */
	public void deleteCategory(Integer id) throws BadRequestException;
	
    /**
     * Method to view details of a particular category
     * @param id of the category to be viewed
     * @return {@link CategoryModel} contains all the details of category 
     * @throws BadRequestException Thrown when id is null or non-integer value
     */	
	public CategoryModel viewCategory(Integer id) throws BadRequestException;
	
    /**
     * Method to retrieve all the categories from the repository
     * @return List of {@link CategoryModel}
     */	
	public List<CategoryModel> viewAll();
	
    /**
     * Method to update the details of the category
     * @param categoryId Id of the category to be updated
     * @param category {@link CategoryModel} Details to be updated
     * @return {@link CategoryModel}
     * @throws BadRequestException Thrown when id is null or non-integer value
     */
	public CategoryModel updateCategory(Integer categoryId,CategoryModel category) throws BadRequestException;
	
}