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
import pills.models.AddPillModel;
import pills.models.PillModel;
import pills.service.AlternativeService;
import pills.service.PillService;

@RestController
@RequestMapping(value = "/pills")
public class PillsController {

	@Autowired
	private PillService pillService;

	@Autowired
	private AlternativeService alternativeService;

	/**
	 * Gets the list of all pills
	 * 
	 * @return List<PillModel>
	 * @throws BadRequestException
	 */
	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<PillModel> viewAll() {
		return pillService.viewAll();
	}
	
	/**
	 * Adds the pill to the database
	 * @param pill
	 * @return PillModel
	 * @throws BadRequestException
	 */
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public PillModel create(@RequestBody AddPillModel pill) throws BadRequestException {
		System.out.println(pill.getPillCategoryId());
		return pillService.addPill(pill);
	}

	/**
	 * Gets the details of pill with pillId as id
	 * @param id
	 * @return PillModel
	 * @throws BadRequestException
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public PillModel view(@PathVariable Integer id) throws BadRequestException {
		return pillService.viewPill(id);
	}

	/**
	 * Updates the details of pill with pillId as id
	 * @param pill
	 * @return PillModel
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
	@ResponseStatus(HttpStatus.OK)
	public PillModel update(@RequestBody PillModel pill) {
		pillService.updatePill(pill);
		return pill;
	}
	
	/**
	 * Deletes the pill with pillId as id
	 * @param id
	 * @throws BadRequestException
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable Integer id) throws BadRequestException {
		pillService.deletePill(id);
	}
	
	/**
	 * Gets all alternate pills for pill with pillId as id
	 * @param id
	 * @return List<PillModel>
	 * @throws BadRequestException
	 */
	@RequestMapping(value = "/{id}/alternatives", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<PillModel> getPills(@PathVariable Integer id) throws BadRequestException {
		return alternativeService.getByPillId(id);
	}
}