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

import de.hse.swa.orm.dao.ContractDao;
import de.hse.swa.orm.model.Contract;

@Path("/contract")
public class ContractResource {
  @Inject
  ContractDao _contractDao;

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Contract createContract(Contract contract){
    return null;
  }

  @GET
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Path("{id}")
  public Contract getContract(@PathParam("id") Long id){
    return _contractDao.getContract(id);
  }

  @GET
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public List<Contract> getAllContracts(){
    return _contractDao.getContracts();
  }

  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Contract updateContract(Contract contract){
    return _contractDao.updateContract(contract);
  }

  @DELETE
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Path("{id}")
  public void deleteContract(@PathParam("id") Long id){
    _contractDao.deleteContract(id);
  }
}
