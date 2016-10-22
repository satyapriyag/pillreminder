package pills.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pills.entity.Alarm;
import pills.entity.Pill;

@Repository
@Transactional
public class AlarmDaoImpl implements AlarmDao{
  
  @Autowired
  private SessionFactory _sessionFactory;
  
  private Session getSession() {
    return _sessionFactory.getCurrentSession();
  }

  public void save(Alarm alarm) {
	  System.out.println(alarm.getPill().getPillId());
	  getSession().save(alarm);
  }
  
  public void delete(Alarm alarm) {
	getSession().delete(alarm);
    return;
  }
  public Alarm getById(Integer id) {
	Alarm alarm = (Alarm) getSession().load(Alarm.class, id);
	Hibernate.initialize(alarm);
	return alarm;
  }
  @SuppressWarnings("unchecked")
  public List<Alarm> getAll() {
    return getSession().createQuery("from Alarm").list();
  }

  public void update(Alarm alarm) {
    getSession().update(alarm);
    return;
  }
  
  @SuppressWarnings("unchecked")
  public List<Alarm> getByUserId(Integer UserId){
	  return getSession().createQuery("from Alarm where a_user_id ="+ UserId).list();
  }
  
  @SuppressWarnings("unchecked")
  public List<Alarm> getByRecurrence(int time){
	  return getSession().createQuery("from Alarm where a_recurrence ="+time).list();
  }

} // class AlarmDao
