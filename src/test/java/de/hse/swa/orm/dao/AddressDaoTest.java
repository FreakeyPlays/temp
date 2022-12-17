package de.hse.swa.orm.dao;

import javax.inject.Inject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.hse.swa.orm.model.Address;

public class AddressDaoTest {
  @Inject
  AddressDao _addressDao;

  private Long current_id;
  final String country = "Germany";
  final String area = "Baden-Wuettemberg";
  final String city = "Esslingen";
  final int zipCode = 12345;
  final String streetName = "Flandernstrasse";
  final int houseNumber = 101;

  private Address createAddressObject(String prefix){
    Address address = new Address();
    address.setCountry(prefix + this.country);
    address.setArea(prefix + this.area);
    address.setCity(prefix + this.city);
    address.setZipCode(this.zipCode);
    address.setStreetName(prefix + this.streetName);
    address.setHouseNumber(this.houseNumber);
    
    return address;
  }  

  private Address createAddressInDatabase(String prefix){
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
  public void createAddressTest(){
    final 
  }

  @Test
  public void getAddressByIdTest(){

  }

  @Test
  public void getAllAddressesTest(){
    
  }

  @Test
  public void updateAddressTest(){
    
  }

  @Test
  public void deleteAddressTest(){
    
  }
}
