package pills.dao;

import java.util.List;

import pills.entity.Category;

public interface CategoryDao{
	public void save(Category category);
	public void delete(Category category);
	public List<Category> getAll();
	public Category getById(Integer id);
	public void update(Category category);
}