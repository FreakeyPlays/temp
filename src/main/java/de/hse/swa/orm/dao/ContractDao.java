package de.hse.swa.orm.dao;

import java.time.LocalDate;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import de.hse.swa.orm.model.Company;
import de.hse.swa.orm.model.Contract;

@ApplicationScoped
public class ContractDao {
  @Inject
  EntityManager entityManager;

  @Transactional 
  public Contract addContract(LocalDate startDate, LocalDate endDate, String licenseKey) {
      Contract contract = new Contract();
      contract.setStartDate(startDate);
      contract.setEndDate(endDate);
      contract.setLicenseKey(licenseKey);
      entityManager.persist(contract);
      return contract;
  } 

  @Transactional 
  public Contract addContract(Contract contract) {
      entityManager.persist(contract);
      return contract;
  }

  public List<Contract> getActiveContracts() {
  	LocalDate today = LocalDate.now();
    	TypedQuery<Contract> query = entityManager.createQuery("SELECT contract FROM Contract contract WHERE contract.endDate >:today", Contract.class);
    	query.setParameter("today", today);
    	return query.getResultList();
   }

  public List<Contract> getContractsByCompany(Company company) { 
    	TypedQuery<Contract> query = entityManager.createQuery("SELECT contract FROM Contract contract WHERE contract.company.id=:id", Contract.class);
    	query.setParameter("id", company.getId());
    	return query.getResultList();
  }
  
  public List<Contract> getContracts() { 
  	TypedQuery<Contract> query = entityManager.createQuery("SELECT contract FROM Contract contract", Contract.class);
  	return query.getResultList();
  }
 
  public Contract getContract(Long id) { 
  	 TypedQuery<Contract> query = entityManager.createQuery("SELECT contract FROM Contract contract WHERE contract.id=:id", Contract.class);
  	 query.setParameter("id", id);
  	 return query.getSingleResult();
  }

  @Transactional
  public Contract updateContract(Contract contract) {
  	entityManager.merge(contract);
    return contract;
  }
  
  @Transactional
  public void deleteContract(Long id) {
  	 TypedQuery<Company> query = entityManager.createQuery("SELECT contract FROM Contract contract WHERE contract.id=:id", Company.class);
	  query.setParameter("id", id);
    Company company = query.getSingleResult();
  	entityManager.remove(entityManager.contains(company) ? company : entityManager.merge(company));
  }
}
