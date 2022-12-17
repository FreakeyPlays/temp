package de.hse.swa.orm.dao;

public class PhoneNumberDaoTest {
  @Inject
  PhoneNumberDao _phoneNumberDao;

  private Long current_id;

  private Address createPhoneNumberObject(String prefix){
    Address address = new Address();
    address.setCountry(prefix + "Germany");
    address.setArea(prefix + "Baden-Wuettemberg");
    address.setCity(prefix + "Esslingen");
    address.setZipCode(12345);
    address.setStreetName(prefix + "Flandernstrasse");
    address.setHouseNumber(101);
    
    return address;
  }  

  private Address createPhoneNumberInDatabase(String prefix){
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
  public void createPhoneNumberTest(){

  }

  @Test
  public void getPhoneNumberByIdTest(){

  }

  @Test
  public void getAllPhoneNumberTest(){
    
  }

  @Test
  public void updatePhoneNumberTest(){
    
  }

  @Test
  public void deletePhoneNumberTest(){
    
  }
}
