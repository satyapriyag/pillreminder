package pills.service;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pills.models.Category;

@Repository
@Transactional
public class CategoryDaoImpl implements CategoryDao{
  
  @Autowired
  private SessionFactory _sessionFactory;
  
  private Session getSession() {
    return _sessionFactory.getCurrentSession();
  }

  public void save(Category category) {
    getSession().save(category);
    return;
  }
  
  public void delete(Category category) {
    getSession().delete(category);
    return;
  }
  
  @SuppressWarnings("unchecked")
  public List<Category> getAll() {
    return getSession().createQuery("from Category").list();
  }

  public Category getById(long id) {
	Category category = (Category) getSession().load(Category.class, id);
	Hibernate.initialize(category);
	return category;
  }

  public void update(Category category) {
    getSession().update(category);
    return;
  }

} // class CategoryDao
