package de.hse.swa.jaxrs.resource;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import de.hse.swa.orm.dao.CompanyDao;
import de.hse.swa.orm.dao.PhoneNumberDao;
import de.hse.swa.orm.dao.UserDao;
import de.hse.swa.orm.model.Company;
import de.hse.swa.orm.model.PhoneNumber;
import de.hse.swa.orm.model.User;

@Path("/user")
public class UserResource {
  @Inject
  UserDao _userDao;

  @Inject
  PhoneNumberDao _phoneNumberDao;

  @Inject
  CompanyDao _companyDao;

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  @Path("add")
  public User addUser(User user){
    System.out.println(user);
    User tempUser = _userDao.addUser(user);
    // _userDao.setCompany(tempUser, company);

    List<PhoneNumber> phoneList = new ArrayList<>();
    for(int i = 0; i <= 1; i++){
      tempUser.getPhoneNumbers().get(i).setUser(tempUser);
      phoneList.add(_phoneNumberDao.addPhoneNumber(tempUser.getPhoneNumbers().get(i)));
    }


    return tempUser;
  }

  /**
   * Get all Users in the Database
   * @return a List of all Users
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public List<User> getAllUsers(){
    return _userDao.getAllUser();
  }

  /**
   * Get User by ID
   * @param id
   * @return a User
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  @Path("{id}")
  public User getUser(@PathParam("id") Long id){
    return _userDao.getUser(id);
  }

  /**
   * Returns all User of the Company
   * @param companyId
   * @return a List of Users in Company
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  @Path("company/{id}")
  public List<User> getUserByCompany(@PathParam("id") Long companyId){
    Company company = _companyDao.getCompany(companyId);
    return _userDao.getUsersByCompany(company);
  }

  /**
   * Updates a User with Data from the Body
   * @return the Updated User
   */
  @PUT
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public User updateUser(User user){
    return _userDao.updateUser(user);
  }

  /**
   * Deletes user by ID
   * @param id
   */
  @DELETE
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  @Path("{id}")
  public void deleteUser(@PathParam("id") Long id){
    _userDao.deleteUser(id);
  }
}
