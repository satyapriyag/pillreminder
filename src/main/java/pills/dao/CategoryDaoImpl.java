package pills.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pills.entity.Category;
import pills.entity.Pill;

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
  }
  
  public void delete(Category category) {
	Session session = getSession();
	Query q = session.createQuery("from Pill where pill_category_id = :categoryId");
	q.setParameter("categoryId", category.getCategoryId());
	@SuppressWarnings("unchecked")
	List<Pill> pills = q.list();
	for(Pill pill:pills){
		session.delete(pill);
	}
    session.delete(category);
    return;
  }
  
  @SuppressWarnings("unchecked")
  public List<Category> getAll() {
    return getSession().createQuery("from Category").list();
  }

  public Category getById(Integer id) {
	Category category = (Category) getSession().load(Category.class, id);
	Hibernate.initialize(category);
	return category;
  }

  public void update(Category category) {
    getSession().update(category);
    return;
  }

} // class CategoryDao
