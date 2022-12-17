package de.hse.swa.orm.dao;

public class UserDaoTest {
  @Inject
  UserDao _userDao;

  private Long current_id;

  private Address createUserObject(String prefix){
    Address address = new Address();
    address.setCountry(prefix + "Germany");
    address.setArea(prefix + "Baden-Wuettemberg");
    address.setCity(prefix + "Esslingen");
    address.setZipCode(12345);
    address.setStreetName(prefix + "Flandernstrasse");
    address.setHouseNumber(101);
    
    return address;
  }  

  private Address createUserInDatabase(String prefix){
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
  public void createUserTest(){

  }

  @Test
  public void getUserByIdTest(){

  }

  @Test
  public void getAllUserTest(){
    
  }

  @Test
  public void updateUserTest(){
    
  }

  @Test
  public void deleteUserTest(){
    
  }
}
