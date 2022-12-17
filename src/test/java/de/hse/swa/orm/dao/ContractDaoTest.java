package de.hse.swa.orm.dao;

public class ContractDaoTest {
  @Inject
  ContractDao _contractDao;

  private Long current_id;

  private Address createContractObject(String prefix){
    Address address = new Address();
    address.setCountry(prefix + "Germany");
    address.setArea(prefix + "Baden-Wuettemberg");
    address.setCity(prefix + "Esslingen");
    address.setZipCode(12345);
    address.setStreetName(prefix + "Flandernstrasse");
    address.setHouseNumber(101);
    
    return address;
  }  

  private Address createContractInDatabase(String prefix){
    Address address = createContractObject(prefix);

    address = _addressDao.addAddress(address);
    this.current_id = address.getId();

    return address;
  }

  @BeforeEach
  public void clearDatabase(){
    _addressDao.removeAllAddresses();
  }

  @Test
  public void createContractTest(){

  }

  @Test
  public void getContractByIdTest(){

  }

  @Test
  public void getAllContractTest(){
    
  }

  @Test
  public void updateContractTest(){
    
  }

  @Test
  public void deleteContractTest(){
    
  }
}
