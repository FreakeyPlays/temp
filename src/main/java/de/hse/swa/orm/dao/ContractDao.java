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

  public List<Contract> getAllContracts() { 
  	TypedQuery<Contract> query = entityManager.createQuery("SELECT contract FROM Contract contract", Contract.class);
  	return query.getResultList();
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

  public Contract getContract(Long id) { 
  	 return entityManager.find(Contract.class, id);
  }

  @Transactional
  public Contract save(Contract contract){
    if(contract.getId() != null){
  	  entityManager.merge(contract);
    } else {
      entityManager.persist(contract);
    }

    return contract;
  }

  @Transactional
  public void removeAllContracts(){
    try{
      entityManager.createQuery("DELETE FROM Feature WHERE id >= 0")
        .executeUpdate();
      entityManager.createQuery("DELETE FROM Ip WHERE id >= 0")
        .executeUpdate();
      entityManager.createQuery("DELETE FROM Contract WHERE id >= 0")
        .executeUpdate();
    } catch (SecurityException | IllegalStateException e){
      e.printStackTrace();
    }

    return;
  }

  @Transactional
  public void removeContract(Long id) {
    entityManager.remove(entityManager.find(Contract.class, id));
  }
}
