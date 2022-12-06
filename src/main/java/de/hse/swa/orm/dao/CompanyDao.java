package de.hse.swa.orm.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import de.hse.swa.orm.model.Address;
import de.hse.swa.orm.model.Company;

@ApplicationScoped
public class CompanyDao {
  @Inject
  EntityManager entityManager; 

  public List<Company> getAllCompanies() {
  	TypedQuery<Company> query = entityManager.createQuery("SELECT company FROM Company company", Company.class);
  	return query.getResultList();
  }

  public Company getCompanyById(Long id) {
	 	return entityManager.find(Company.class, id);
  }

  public Company getCompanyByName(String name) {
	 	TypedQuery<Company> query = entityManager.createQuery("SELECT company FROM Company company where company.companyName=:name", Company.class);
    query.setParameter("name", name);
    return query.getSingleResult();
  }

  @Transactional
  public Company save(Company company){
    if(company.getId() != null){
  	  entityManager.merge(company);
    } else {
      Address address = company.getAddress();
      address.setCompany(company);
      entityManager.persist(company);
    }

    return company;
  }

  @Transactional
  public void removeAllCompanies(){
    try{
      Query del = entityManager.createQuery("DELETE FROM Company WHERE id >= 0");
      del.executeUpdate();
    } catch (SecurityException | IllegalStateException e){
      e.printStackTrace();
    }

    return;
  }

  @Transactional 
  public void removeCompany(Long id) {
    entityManager.remove(entityManager.find(Company.class, id));
  }
}
