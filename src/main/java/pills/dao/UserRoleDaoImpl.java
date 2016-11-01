package pills.dao;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pills.entity.UserRole;

@Repository
@Transactional
public class UserRoleDaoImpl implements UserRoleDao{
  
  @Autowired
  private SessionFactory _sessionFactory;
  
  private Session getSession() {
    return _sessionFactory.getCurrentSession();
  }

  public void save(UserRole userRole) {
      getSession().save(userRole);
  }
}