package pills.service;

import java.util.List;

import pills.models.Category;

public interface CategoryDao{
	public void save(Category category);
	public void delete(Category category);
	public List<Category> getAll();
	public Category getById(long id);
	public void update(Category category);
}