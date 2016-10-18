package pills.service;

import java.util.List;

import pills.entity.Pill;

public interface PillDao{
	public void save(Pill pill);
	public void delete(Pill pill);
	public List<Pill> getAll();
	public Pill getById(Integer id);
	public void update(Pill pill);
}