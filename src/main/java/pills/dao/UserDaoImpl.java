package pills.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pills.entity.User;
import pills.entity.Alarm;

@Repository
@Transactional
public class UserDaoImpl implements UserDao{
  
  @Autowired
  private SessionFactory _sessionFactory;
  
  private Session getSession() {
    return _sessionFactory.getCurrentSession();
  }

  public void save(User user) {
	  getSession().save(user);
  }
  
  public void delete(User user) {
	Session session = getSession();
	Query q = session.createQuery("from Alarm where a_user_id = :userId");
	q.setParameter("userId", user.getUserId());
	@SuppressWarnings("unchecked")
	List<Alarm> alarms = q.list();
	for(Alarm alarm:alarms){
		session.delete(alarm);
	}
    session.delete(user);
    return;
  }
  
  @SuppressWarnings("unchecked")
  public List<User> getAll() {
    return getSession().createQuery("from User").list();
  }

  public User getById(Integer id) {
	User user = (User) getSession().load(User.class, id);
	Hibernate.initialize(user);
	return user;
  }

  public void update(User user) {
    getSession().update(user);
    return;
  }
  public User getByMail(String mail){
		Session session = getSession();
	  	Query q = session.createQuery("from User where user_email = :mail");
		q.setParameter("mail", mail);
		User user = (User) q.uniqueResult();
		Hibernate.initialize(user);
		return user;
  }

} // class UserDao
