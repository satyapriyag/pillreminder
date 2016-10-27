package pills.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pills.entity.Alarm;

@Repository
@Transactional
public class AlarmDaoImpl implements AlarmDao {

  private static final Logger LOG = Logger.getLogger(AlarmDaoImpl.class);
  
  @Autowired
  private SessionFactory _sessionFactory;

  private Session getSession() {
    return _sessionFactory.getCurrentSession();
  }

  public void save(Alarm alarm) {
    LOG.debug("Alarm with id"+alarm.getAId()+" is to be added");
    getSession().save(alarm);
  }

  public void delete(Alarm alarm) {
    LOG.debug("Alarm with id"+alarm.getAId()+" is to be deleted");
    getSession().delete(alarm);
    return;
  }

  public Alarm getById(Integer id) {
    LOG.debug("Alarm with id"+ id +" is to be initialized");
    Alarm alarm = (Alarm) getSession().load(Alarm.class, id);
    Hibernate.initialize(alarm);
    return alarm;
  }

  @SuppressWarnings("unchecked")
  public List<Alarm> getAll() {
    LOG.debug("Retrieving all the alarms");
    return getSession().createQuery("from Alarm").list();
  }

  public void update(Alarm alarm) {
    LOG.debug("Alarm with id"+alarm.getAId()+" is to be updated");
    getSession().update(alarm);
    return;
  }

  @SuppressWarnings("unchecked")
  public List<Alarm> getByUserId(Integer UserId) {
    LOG.debug("Retrieving alarms for user with id" + UserId);
    return getSession().createQuery("from Alarm where a_user_id =" + UserId).list();
  }

  @SuppressWarnings("unchecked")
  public List<Alarm> getByRecurrence(int time) {
    LOG.debug("Fetching all alarms for recurrence time " + time);
    return getSession().createQuery("from Alarm where a_recurrence =" + time).list();
  }

  @SuppressWarnings("unchecked")
  public List<Alarm> getForToday(Integer UserId) {
    return getSession()
        .createQuery("from Alarm where a_user_id =" + UserId
            + "AND a_start_date<=CURDATE() AND a_end_date>=CURDATE() ORDER BY a_recurrence ASC")
        .list();
  }
} // class AlarmDao
