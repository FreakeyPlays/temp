package de.hse.swa.jaxrs.resource;

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

import de.hse.swa.orm.dao.AddressDao;
import de.hse.swa.orm.dao.CompanyDao;
import de.hse.swa.orm.model.Address;
import de.hse.swa.orm.model.Company;

@Path("/company")
public class CompanyResource {
  @Inject
  CompanyDao _companyDao;

  @Inject
  AddressDao _addressDao;

  /**
   * Creates a new Company with Data from the Body
   * @param company
   * @return the new Company
   */
  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Company addCompany(Company company){
    Address newAddress = _addressDao.addAddress(company.getAddress());
    Company newCompany = _companyDao.addCompany(company.getCompanyName(), company.getDepartment(), newAddress);
    _companyDao.addAddress(newCompany, newAddress);

    return newCompany;
  }

  /**
   * Get all Companies in the Database
   * @return List of Companies
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public List<Company> getAllCompanies(){
    return _companyDao.getAllCompanies();
  }

  /**
   * Get a Company by Company ID
   * @param id
   * @return a Company
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  @Path("{id}")
  public Company getCompany(@PathParam("id") Long id){
    return _companyDao.getCompany(id);
  }

  /**
   * Updates a Company with Data from Body
   * @param company
   * @return the updated Company
   */
  @PUT
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Company updateCompany(Company company){
    return _companyDao.updateCompany(company);
  }

  /**
   * Deletes a Company by ID
   * @param id
   */
  @DELETE
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  @Path("{id}")
  public void deleteCompany(@PathParam("id") Long id){
    _companyDao.deleteCompany(id);
  }
}
