package de.hse.swa.orm.dao;

public class FeatureDaoTest {
  @Inject
  FeatureDao _featureDao;

  private Long current_id;

  private Address createFeatureObject(String prefix){
    Address address = new Address();
    address.setCountry(prefix + "Germany");
    address.setArea(prefix + "Baden-Wuettemberg");
    address.setCity(prefix + "Esslingen");
    address.setZipCode(12345);
    address.setStreetName(prefix + "Flandernstrasse");
    address.setHouseNumber(101);
    
    return address;
  }  

  private Address createFeatureInDatabase(String prefix){
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
  public void createFeatureTest(){

  }

  @Test
  public void getFeatureByIdTest(){

  }

  @Test
  public void getAllFeatureTest(){
    
  }

  @Test
  public void updateFeatureTest(){
    
  }

  @Test
  public void deleteFeatureTest(){
    
  }
}
