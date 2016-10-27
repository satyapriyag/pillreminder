package pills.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pills.entity.Category;
import pills.entity.Pill;

@Repository
public class CategoryDaoImpl implements CategoryDao {

  private static final Logger LOG = Logger.getLogger(CategoryDaoImpl.class);

  @Autowired
  private SessionFactory _sessionFactory;

  private Session getSession() {
    return _sessionFactory.getCurrentSession();
  }

  public void save(Category category) {
    LOG.debug("Category with id"+category.getCategoryId() +" is to be added");
    getSession().save(category);
  }

  public void delete(Category category) {
    Session session = getSession();
    LOG.debug("Deleting category wit id " + category.getCategoryId());
    Query q = session.createQuery("from Pill where pill_category_id = :categoryId");
    q.setParameter("categoryId", category.getCategoryId());
    @SuppressWarnings("unchecked")
    List<Pill> pills = q.list();
    for (Pill pill : pills) {
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
    LOG.debug("Category with id"+id +" is to be initialized");
    return category;
    // DetachedCriteria criteria = DetachedCriteria.forClass(Category.class);
    // criteria.add(Restrictions.eq("categoryId", id));
    // Criteria executableCriteria = criteria.getExecutableCriteria(getSession());
    // return (Category) executableCriteria.uniqueResult();
  }

  public void update(Category category) {
    LOG.debug("Category with id "+category.getCategoryId()+" is to be updated");
    getSession().update(category);
    return;
  }

} // class CategoryDao
