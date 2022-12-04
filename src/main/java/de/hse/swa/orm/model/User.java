package de.hse.swa.orm.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="T_user")
public class User {
  
  @Id
  @SequenceGenerator(name="userSeq", sequenceName="ZSEQ_USER_ID", allocationSize=1, initialValue=10)
  @GeneratedValue(generator="userSeq")
  @Column(name="ID", nullable=false, unique=true)
  private Long id;

  @Column(name="LAST_NAME", length=32, nullable=false)
  private String lastName;

  @Column(name="FIRST_NAME", length=32)
  private String firstName;

  @Column(name="USERNAME", length=32)
  private String username;

  @Column(name="EMAIL", length=64, nullable=false, unique=true)
  private String email;

  @OneToMany(mappedBy = "user", cascade = CascadeType.MERGE, orphanRemoval = true)
  private List<PhoneNumber> phoneNumbers;

  @Column(name="IS_ADMIN", nullable=false)
  private boolean isAdmin;

  @Column(name="PASSWORD", nullable=false)
  private String password;

  @ManyToMany(mappedBy = "users")
  private List<Contract> contracts;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="COMPANY_ID", referencedColumnName = "id")
  public Company companyId;

  public User(){}

  public User(
    String lastName, String firstName, 
    String username, String email,
    List<PhoneNumber> phoneNumbers,
    boolean isAdmin, String password, 
    Company companyId) 
  {
    this.lastName = lastName;
    this.firstName = firstName;
    this.username = username;
    this.email = email;
    this.phoneNumbers = phoneNumbers;
    this.isAdmin = isAdmin;
    this.password = password;
    this.companyId = companyId;
  }

  public Long getId() {
    return id;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public List<PhoneNumber> getPhoneNumbers() {
    return phoneNumbers;
  }

  public void setPhoneNumbers(List<PhoneNumber> phoneNumbers) {
    this.phoneNumbers = phoneNumbers;
  }

  public boolean getIsAdmin() {
    return isAdmin;
  }

  public void setIsAdmin(boolean isAdmin) {
    this.isAdmin = isAdmin;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public List<Contract> getContracts() {
    return contracts;
  }

  public void setContracts(List<Contract> contracts) {
    this.contracts = contracts;
  }

  public Company getCustomerId() {
    return companyId;
  }

  public void setCustomerId(Company companyId) {
    this.companyId = companyId;
  }

  @Override
  public String toString() {
    return "User [id=" + id + ", lastName=" + lastName + ", firstName=" + firstName + ", username=" + username
        + ", email=" + email + ", phoneNumbers=" + phoneNumbers + ", isAdmin=" + isAdmin + 
        ", password=" + password + ", contracts=" + contracts + ", companyId=" + companyId + "]";
  }
}
