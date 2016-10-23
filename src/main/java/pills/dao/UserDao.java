package pills.dao;

import java.util.List;

import pills.entity.User;

public interface UserDao{
	public void save(User user);
	public void delete(User user);
	public List<User> getAll();
	public User getById(Integer id);
	public void update(User user);
}