/**
 * Rest Controller handling the basic CRUD apis for Alternative Model
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

import pills.models.AddAlternativeModel;
import pills.models.AlternativeModel;
import pills.service.AlternativeService;

@RestController
@RequestMapping(value = "/alternatives")
public class AlternativeController {

  @Autowired
  private AlternativeService alternativeService;

  /**
   * Gets the list of all categories
   * 
   * @return List of {@link AlternativeModel}
   * @throws BadRequestException Thrown when there is exception in database
   */
  @RequestMapping(method = RequestMethod.GET)
  @ResponseStatus(HttpStatus.OK)
  public List<AlternativeModel> viewAll() throws BadRequestException {
    return alternativeService.viewAll();
  }

  /**
   * Adds the alternative to the database
   * 
   * @param alternative Name of the alternative to be added
   * @return {@link AlternativeModel}
   * @throws BadRequestException Thrown when there is exception in database
   */
  @RequestMapping(method = RequestMethod.POST)
  @ResponseStatus(HttpStatus.CREATED)
  public AlternativeModel create(@RequestBody AddAlternativeModel alternative) throws BadRequestException {
    return alternativeService.addAlternative(alternative);
  }

  /**
   * Gets the details of alternative with alternativeId as id
   * 
   * @param id Id of the alternative to be viewed
   * @return AlternativeModel {@link AlternativeModel}
   * @throws NotFoundException Thrown when there is no alternative with given id
   * @throws BadRequestException Thrown when there is exception in database
   */
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  @ResponseStatus(HttpStatus.OK)
  public AlternativeModel view(@PathVariable Integer id)
      throws NotFoundException, BadRequestException {
    return alternativeService.viewAlternative(id);
  }

  /**
   * Updates the details of alternative with alternativeId as id
   * 
   * @param alternative {@link AlternativeModel}
   * @param id Id of the alternative to be updated
   * @return {@link AlternativeModel}
   * @throws BadRequestException Thrown when there is exception in database
   */
  @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
  @ResponseStatus(HttpStatus.OK)
  public void update(@RequestBody AlternativeModel alternative)
      throws BadRequestException {
     alternativeService.updateAlternative(alternative);
  }

  /**
   * Deletes the alternative with alternativeId as id
   * 
   * @param id of the alternative to be deleted
   * @throws NotFoundException Thrown when there is no alternative with given id
   * @throws BadRequestException Thrown when there is exception in database
   */
  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  @ResponseStatus(HttpStatus.OK)
  public void delete(@PathVariable Integer id) throws NotFoundException, BadRequestException {
    // Alternative alternative = new Alternative(id);
    alternativeService.deleteAlternative(id);
  }

}
