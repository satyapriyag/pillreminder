package pills.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import pills.models.AddCategoryModel;
import pills.models.Category;
import pills.models.CategoryModel;
import pills.service.CategoryService;

@RestController
@RequestMapping(value="/categories")
public class CategoriesController {

  @Autowired
  private CategoryService categoryService;
  
  @RequestMapping(method = RequestMethod.GET)
  @ResponseStatus(HttpStatus.OK)
  public List<CategoryModel> viewAll(){
	  return categoryService.viewAll();
  }
  
  @RequestMapping(method = RequestMethod.POST)
  @ResponseStatus(HttpStatus.CREATED)
  public CategoryModel create(@RequestBody AddCategoryModel category) {
     return categoryService.addCategory(category.getCategoryName());
  }
  
  @RequestMapping(value="/{id}",method = RequestMethod.GET)
  @ResponseStatus(HttpStatus.OK)
  public CategoryModel view(@PathVariable Long id){
	 return categoryService.viewCategory(id);
  }
  
  @RequestMapping(value="/{id}",method = RequestMethod.PATCH)
  @ResponseStatus(HttpStatus.OK)
  public CategoryModel update(@RequestBody CategoryModel category) {
	  categoryService.updateCategory(category);
	  return category;
  }
  
  @RequestMapping(value="/{id}",method = RequestMethod.DELETE)
  @ResponseStatus(HttpStatus.OK)
  public void delete(@PathVariable long id) {
      Category category = new Category(id);
      categoryService.deleteCategory(category);
  }

} 