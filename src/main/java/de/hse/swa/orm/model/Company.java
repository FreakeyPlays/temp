package de.hse.swa.orm.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="T_company")
public class Company {
  @Id
  @SequenceGenerator(name="companySeq", sequenceName="ZSEQ_COMPANY_ID", allocationSize=1, initialValue=10)
  @GeneratedValue(generator="companySeq")
  @Column(name="ID", unique=true)
  private Long id;

  @Column(name="COMPANY_NAME", unique=true, length=64)
  public String companyName;

  @Column(name="DEPARTMENT", length=64)
  private String department;

  @OneToMany(mappedBy = "companyId")
  private List<Contract> contracts;

  @OneToMany(mappedBy = "companyId")
  private List<User> users;

  @OneToOne(cascade = CascadeType.MERGE, orphanRemoval = true)
  @JoinColumn(name="ADDRESS_ID")
  private Address address;

  public Company(){}

  public Company(
    String companyName, String department, 
    List<Contract> contracts, List<User> users, 
    Address address) 
  {
    this.companyName = companyName;
    this.department = department;
    this.contracts = contracts;
    this.users = users;
    this.address = address;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCompanyName() {
    return companyName;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  public String getDepartment() {
    return department;
  }

  public void setDepartment(String department) {
    this.department = department;
  }

  public List<Contract> getContracts() {
    return contracts;
  }

  public void setContracts(List<Contract> contracts) {
    this.contracts = contracts;
  }

  public List<User> getUsers() {
    return users;
  }

  public void setUsers(List<User> users) {
    this.users = users;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  } 

  @Override
  public String toString() {
    return "Company [id=" + id + ", companyName=" + companyName + ", department=" + department + ", contracts="
        + contracts + ", users=" + users + ", address=" + address + "]";
  }
}
