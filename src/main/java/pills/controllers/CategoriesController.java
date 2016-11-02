/**
 * Rest Controller handling the basic CRUD apis for Category Model
 */
package pills.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import inti.ws.spring.exception.client.BadRequestException;
import inti.ws.spring.exception.client.NotFoundException;

import pills.models.AddCategoryModel;
import pills.models.CategoryModel;
import pills.models.PillModel;
import pills.service.CategoryService;
import pills.service.PillService;

@RestController
@RequestMapping(value = "/categories")
public class CategoriesController {

  @Autowired
  private CategoryService categoryService;

  @Autowired
  private PillService pillService;

  /**
   * Gets the list of all categories
   * 
   * @return List of {@link CategoryModel}
   * @throws BadRequestException Thrown when there is exception in database
   */
  @RequestMapping(method = RequestMethod.GET)
  @ResponseStatus(HttpStatus.OK)
  public List<CategoryModel> viewAll() throws BadRequestException {
    return categoryService.viewAll();
  }

  /**
   * Adds the category to the database
   * 
   * @param category Name of the category to be added
   * @return {@link CategoryModel}
   * @throws BadRequestException Thrown when there is exception in database
   */
  @RequestMapping(method = RequestMethod.POST)
  @ResponseStatus(HttpStatus.CREATED)
  public CategoryModel create(@RequestBody AddCategoryModel category) throws BadRequestException {
    return categoryService.addCategory(category.getCategoryName());
  }

  /**
   * Gets the details of category with categoryId as id
   * 
   * @param id Id of the category to be viewed
   * @return CategoryModel {@link CategoryModel}
   * @throws NotFoundException Thrown when there is no category with given id
   * @throws BadRequestException Thrown when there is exception in database
   */
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  @ResponseStatus(HttpStatus.OK)
  public CategoryModel view(@PathVariable Integer id)
      throws NotFoundException, BadRequestException {
    return categoryService.viewCategory(id);
  }

  /**
   * Updates the details of category with categoryId as id
   * 
   * @param category {@link CategoryModel}
   * @param id Id of the category to be updated
   * @return {@link CategoryModel}
   * @throws BadRequestException Thrown when there is exception in database
   */
  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  @ResponseStatus(HttpStatus.OK)
  public CategoryModel update(@PathVariable Integer id, @RequestBody CategoryModel category)
      throws BadRequestException {
    return categoryService.updateCategory(id, category);
  }

  /**
   * Deletes the category with categoryId as id
   * 
   * @param id of the category to be deleted
   * @throws NotFoundException Thrown when there is no category with given id
   * @throws BadRequestException Thrown when there is exception in database
   */
  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  @ResponseStatus(HttpStatus.OK)
  public void delete(@PathVariable Integer id) throws NotFoundException, BadRequestException {
    // Category category = new Category(id);
    categoryService.deleteCategory(id);
  }

  /**
   * Fetches all the pills with categoryId as id
   * 
   * @param id of the category
   * @return List of {@link PillModel}
   * @throws BadRequestException Thrown when there is exception in database
   */
  @RequestMapping(value = "/{id}/pills", method = RequestMethod.GET)
  @ResponseStatus(HttpStatus.OK)
  public List<PillModel> getPills(@PathVariable Integer id) throws BadRequestException {
    return pillService.getPillsForCategory(id);
  }

}
