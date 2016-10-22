package pills.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pills.entity.Alternative;

@Repository
@Transactional
public class AlternativeDaoImpl implements AlternativeDao{
  
  @Autowired
  private SessionFactory _sessionFactory;
  
  private Session getSession() {
    return _sessionFactory.getCurrentSession();
  }

  public void save(Alternative alternative) {
	  getSession().save(alternative);
  }
  
  public void delete(Alternative alternative) {
	getSession().delete(alternative);
    return;
  }
  public Alternative getById(Integer id) {
	Alternative alternative = (Alternative) getSession().load(Alternative.class, id);
	Hibernate.initialize(alternative);
	return alternative;
  }
  @SuppressWarnings("unchecked")
  public List<Alternative> getAll() {
    return getSession().createQuery("from Alternative").list();
  }

  public void update(Alternative alternative) {
    getSession().update(alternative);
    return;
  }
  
  @SuppressWarnings("unchecked")
  public List<Alternative> getByPillId(Integer pillId){
	  return getSession().createQuery("from Alternative where pill_id ="+ pillId).list();
  }

} // class AlternativeDao
