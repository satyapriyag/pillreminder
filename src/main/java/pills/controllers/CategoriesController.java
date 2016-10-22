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
import pills.entity.Category;
import pills.models.CategoryModel;
import pills.models.PillModel;
import pills.service.CategoryService;
import pills.service.PillService;

@RestController
@RequestMapping(value="/categories")
public class CategoriesController {

  @Autowired
  private CategoryService categoryService;
  
  @Autowired
  private PillService pillService;
  

  /**
 * @return
 * @throws BadRequestException
 */
@RequestMapping(method = RequestMethod.GET)
  @ResponseStatus(HttpStatus.OK)
  public List<CategoryModel> viewAll() throws BadRequestException{
	  return categoryService.viewAll();
  }
  
  /**
 * @param category
 * @return
 * @throws BadRequestException
 */
@RequestMapping(method = RequestMethod.POST)
  @ResponseStatus(HttpStatus.CREATED)
  public CategoryModel create(@RequestBody AddCategoryModel category) throws BadRequestException {
     return categoryService.addCategory(category.getCategoryName());
  }
  
  /**
 * @param id
 * @return
 * @throws NotFoundException
 * @throws BadRequestException, ObjectNotFoundException 
 */
@RequestMapping(value="/{id}",method = RequestMethod.GET)
  @ResponseStatus(HttpStatus.OK)
  public CategoryModel view(@PathVariable Integer id) throws NotFoundException, BadRequestException{
	 return categoryService.viewCategory(id);
  }
  
  /**
 * @param category
 * @return
 * @throws BadRequestException
 */
@RequestMapping(value="/{id}",method = RequestMethod.PUT)
  @ResponseStatus(HttpStatus.OK)
  public CategoryModel update(@PathVariable Integer id,@RequestBody CategoryModel category) throws BadRequestException  {
	  return categoryService.updateCategory(id,category);
  }
  
 /**
 * @param id
 * @throws NotFoundException
 * @throws BadRequestException
 */
@RequestMapping(value="/{id}",method = RequestMethod.DELETE)
  @ResponseStatus(HttpStatus.OK)
  public void delete(@PathVariable Integer id) throws NotFoundException,BadRequestException {
      //Category category = new Category(id);
      categoryService.deleteCategory(id);
  }

/**
 * 
 * @param id
 * @throws BadRequestException
 */
@RequestMapping(value="/{id}/pills",method = RequestMethod.GET)
@ResponseStatus(HttpStatus.OK)
public List<PillModel> getPills(@PathVariable Integer id) throws BadRequestException {
	return pillService.getPillsForCategory(id);
}


} 