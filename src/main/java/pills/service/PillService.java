package pills.service;

import pills.entity.Pill;
import pills.models.AddPillModel;
import pills.models.PillModel;

import java.util.List;

import inti.ws.spring.exception.client.BadRequestException;

public interface PillService{
	public PillModel addPill(AddPillModel pill) throws BadRequestException;
	public void deletePill(Integer id) throws BadRequestException;
	public PillModel viewPill(Integer id) throws BadRequestException;
	public List<PillModel> viewAll() ;
	public void updatePill(PillModel pill);
	public List<PillModel> getPillsForCategory(Integer categoryId) throws BadRequestException;
}