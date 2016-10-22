package pills.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pills.entity.Pill;

@Repository
@Transactional
public class PillDaoImpl implements PillDao{
  
  @Autowired
  private SessionFactory _sessionFactory;
  
  private Session getSession() {
    return _sessionFactory.getCurrentSession();
  }

  public void save(Pill pill) {
    getSession().save(pill);
    return;
  }
  
  public void delete(Pill pill) {
    getSession().delete(pill);
    return;
  }
  
  @SuppressWarnings("unchecked")
  public List<Pill> getAll() {
    return getSession().createQuery("from Pill").list();
  }

  public Pill getById(Integer id) {
	Pill pill = (Pill) getSession().load(Pill.class, id);
	Hibernate.initialize(pill);
	return pill;
  }

  public void update(Pill pill) {
    getSession().update(pill);
    return;
  }
  
  @SuppressWarnings("unchecked")
  public List<Pill> getByCategoryId(Integer id){
	return getSession().createQuery("from Pill where pill_category_id ="+id).list();
  }


} // class PillDao
