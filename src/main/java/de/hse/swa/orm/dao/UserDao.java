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
  public User addUser(User newUser){
    entityManager.persist(newUser);
    return newUser;
  }

  public List<User> getAllUser(){
    TypedQuery<User> query = entityManager.createQuery("SELECT user FROM User user", User.class);
    return query.getResultList();
  }

  public List<User> getUsersByCompany(Company company){
    TypedQuery<User> query = entityManager.createQuery("SELECT user FROM User user where user.company.id=:id", User.class);
    query.setParameter("id", company.getId());
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
    user.setCustomerId(company);
    entityManager.merge(user);
  }

}
