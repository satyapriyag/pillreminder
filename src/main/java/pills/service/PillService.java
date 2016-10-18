package pills.service;

import pills.entity.Pill;
import pills.models.AddPillModel;
import pills.models.PillModel;

import java.util.List;

public interface PillService{
	public PillModel addPill(AddPillModel pill);
	public void deletePill(Pill pill);
	public PillModel viewPill(Integer id);
	public List<PillModel> viewAll();
	public void updatePill(PillModel pill);
}