package de.hse.swa.jaxrs.resource;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
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
import de.hse.swa.orm.dao.FeatureDao;
import de.hse.swa.orm.dao.IpDao;
import de.hse.swa.orm.dao.UserDao;
import de.hse.swa.orm.model.Contract;
import de.hse.swa.orm.model.Feature;
import de.hse.swa.orm.model.Ip;
import de.hse.swa.orm.model.User;

@Path("/contract")
public class ContractResource {
  @ApplicationScoped

  @Inject
  ContractDao _contractDao;

  @Inject
  IpDao _ipDao;

  @Inject
  FeatureDao _featureDao;

  @Inject
  UserDao _userDao;

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Contract createContract(Contract contract){
    // for(int i = 0; i < contract.getUsers().size(); i++){
    //   contract.getUsers().set(i, _userDao.getUser(contract.getUsers().get(i).getId()));
    // }

    Contract tempContract = _contractDao.addContract(contract);

    for(int i = 0; i < contract.getIps().size(); i++){
      Ip tempIp = contract.getIps().get(i);

      tempIp.setContract(tempContract);
      _ipDao.addIp(tempIp);
    }

    for(int i = 0; i < contract.getFeatures().size(); i++){
      Feature tempFeature = contract.getFeatures().get(i);

      tempFeature.setContract(tempContract);
      _featureDao.addFeature(tempFeature);
    }

    return tempContract;
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
