package de.hse.swa.orm.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import de.hse.swa.orm.model.Company;
import de.hse.swa.orm.model.User;

@ApplicationScoped
public class UserDao {
  @Inject
  EntityManager entityManager;

  @Transactional
  public User addUser(User user){
    entityManager.persist(user);
    return user;
  }

  public List<User> getAllUser(){
    TypedQuery<User> query = entityManager.createQuery("SELECT user FROM User user", User.class);
    return query.getResultList();
  }

  public List<User> getUsersByCompany(Company company){
    TypedQuery<User> query = entityManager.createQuery("SELECT user FROM User user where user.companyId.id=:id", User.class)
      .setParameter("id", company.getId());
    return query.getResultList();
  }

  public User getUser(Long id){
    TypedQuery<User> query = entityManager.createQuery("SELECT user FROM User user WHERE user.id=:id", User.class);
    query.setParameter("id", id);
    return query.getSingleResult();
  }

  public User getUser(String username){
    TypedQuery<User> query = entityManager.createQuery("SELECT user FROM User user where user.username=:username", User.class);
    query.setParameter("username", username);
    return query.getSingleResult();
  }

  public User getUser(User user){
    TypedQuery<User> query = entityManager.createQuery("SELECT user FROM User user where user.id=:id", User.class);
    query.setParameter("id", user.getId());
    return query.getSingleResult();
  }

  @Transactional
  public User updateUser(User user){
    entityManager.merge(user);
    return user;
  }

  @Transactional
  public void deleteUser(Long id){
    TypedQuery<User> query = entityManager.createQuery("SELECT user FROM User user WHERE user.id=:id", User.class);
    query.setParameter("id", id);
    User testUser = query.getSingleResult();
    entityManager.remove(entityManager.contains(testUser) ? testUser : entityManager.merge(testUser)); 
  }

  @Transactional
  public void setCompany(User user, Company company){
    user.setCompanyId(company);
    entityManager.merge(user);
  }






  // public User getUser(User user){
  //   return entityManager.find(User.class, user.getId());
  // }

  // public List<User> getAllUsers(){
  //   TypedQuery<User> query = entityManager.createQuery("SELECT user FROM User user", User.class);
  //   return query.getResultList();
  // }

  // public List<User> getUsersByCompany(Company company){
  //   TypedQuery<User> query = entityManager.createQuery("SELECT user FROM User user where user.companyId.id=:id", User.class)
  //     .setParameter("id", company.getId());
  //   return query.getResultList();
  // }

  // @Transactional
  // public User save(User user){
  //   if(user.getId() != null){
  //     entityManager.merge(user);
  //   } else {
  //     entityManager.persist(user);
  //   }

  //   return user;
  // }

  // @Transactional
  // public void removeUser(User user){
  //   entityManager.remove(user);
  // }

  // @Transactional
  // public void removeAllUser(){
  //   try {
  //     TypedQuery<User> query = entityManager.createQuery("DELETE FROM User WHERE id >= 0", User.class);
  //     query.executeUpdate();
  //   } catch(SecurityException | IllegalStateException e) {
  //     e.printStackTrace();
  //   }

  //   return;
  // }

  // public Boolean login(String username, String password){
  //   TypedQuery<User> query = entityManager.createQuery("SELECT user FROM User user WHERE user.username=:username AND user.password=:password", User.class)
  //     .setParameter("username", username)
  //     .setParameter("password", password);
  //   List<User> results = query.getResultList();
  //   return (results.size() > 0);
  // }
}
