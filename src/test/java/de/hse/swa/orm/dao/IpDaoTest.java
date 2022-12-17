package de.hse.swa.orm.dao;

public class IpDaoTest {
  @Inject
  IpDao _ipDao;

  private Long current_id;

  private Address createIpObject(String prefix){
    Address address = new Address();
    address.setCountry(prefix + "Germany");
    address.setArea(prefix + "Baden-Wuettemberg");
    address.setCity(prefix + "Esslingen");
    address.setZipCode(12345);
    address.setStreetName(prefix + "Flandernstrasse");
    address.setHouseNumber(101);
    
    return address;
  }  

  private Address createIpInDatabase(String prefix){
    Address address = createAddressObject(prefix);

    address = _addressDao.addAddress(address);
    this.current_id = address.getId();

    return address;
  }

  @BeforeEach
  public void clearDatabase(){
    _addressDao.removeAllAddresses();
  }

  @Test
  public void createIpTest(){

  }

  @Test
  public void getIpByIdTest(){

  }

  @Test
  public void getAllIpTest(){
    
  }

  @Test
  public void updateIpTest(){
    
  }

  @Test
  public void deleteIpTest(){
    
  }
}
