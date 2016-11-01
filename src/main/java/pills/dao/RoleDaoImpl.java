package pills.dao;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pills.entity.Role;

@Repository
@Transactional
public class RoleDaoImpl implements RoleDao{
  
  @Autowired
  private SessionFactory _sessionFactory;
  
  private Session getSession() {
    return _sessionFactory.getCurrentSession();
  }

  public Role getById(Integer id) {
    Role role = (Role) getSession().load(Role.class, id);
    Hibernate.initialize(role);
    return role;
  }
}