package de.hse.swa.orm.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import de.hse.swa.orm.model.Address;
import de.hse.swa.orm.model.Company;
import de.hse.swa.orm.model.Contract;

@ApplicationScoped
public class CompanyDao {
  @Inject
  EntityManager entityManager; 

  @Transactional 
  public Company addCompany(String name, String department, Address address) {
    Company company = new Company();

    company.setCompanyName(name);
    company.setDepartment(department);
    company.setAddress(address);
    
    entityManager.persist(company);
    return company;
  }
  
  public List<Company> getAllCompanies() {
  	TypedQuery<Company> query = entityManager.createQuery("SELECT company FROM Company company", Company.class);
  	return query.getResultList();
  }
  
  public Company getCompany(Long id) {
	 	TypedQuery<Company> query = entityManager.createQuery("SELECT company FROM Company company WHERE company.id=:id", Company.class);
	  query.setParameter("id", id);
	 	return query.getSingleResult();
  }
  
  @Transactional 
  public Company updateCompany(Company company) {
  	entityManager.merge(company);
  	return company;
  }

  @Transactional 
  public void deleteCompany(Long id) {
    TypedQuery<Company> query = entityManager.createQuery("SELECT company FROM Company company WHERE company.id=:id", Company.class);
	  query.setParameter("id", id);
    Company company = query.getSingleResult();
  	entityManager.remove(entityManager.contains(company) ? company : entityManager.merge(company));
  }
	
	public List<Company> getFilteredCompanies(String filter){
	  TypedQuery<Company> query = entityManager.createQuery("SELECT company FROM Company company WHERE (company.name LIKE :filter)", Company.class);
	 	query.setParameter("filter", filter + "%");
	  return query.getResultList();
	}

  @Transactional
	public void addContract(Company company, Contract contract) {
		List<Contract> contracts = company.getContracts();
		contracts.add(contract);
		contract.setCompanyId(company);
		company.setContracts(contracts);
		entityManager.merge(company);
	}
	
  @Transactional 
  public void addAddress(Company company, Address address) {
  	company.setAddress(address);
  	address.setCompany(company);
  	entityManager.merge(company);
  }
}
