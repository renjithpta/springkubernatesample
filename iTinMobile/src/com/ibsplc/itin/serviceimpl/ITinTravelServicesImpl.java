package com.ibsplc.itin.serviceimpl;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.ibsplc.itin.database.dao.ITinTravelServiceDao;
import com.ibsplc.itin.service.ITinTravelServices;
import com.ibsplc.itin.vo.ApproverVO;
import com.ibsplc.itin.vo.BusinessOpportunityVO;
import com.ibsplc.itin.vo.EmployeeVO;
import com.ibsplc.itin.vo.MasterInfo;
import com.ibsplc.itin.vo.RequestVO;

/**
 * 
 * @author A-4211
 * 
 */
@Path("/itinservice")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ITinTravelServicesImpl implements ITinTravelServices {

	@Autowired
	private ITinTravelServiceDao travelServiceDAO;

	public ITinTravelServicesImpl() {
	}

	/**
	 * @param travelServiceDAO
	 *            the travelServiceDAO to set
	 */
	public void setTravelServiceDAO(ITinTravelServiceDao travelServiceDAO) {
		this.travelServiceDAO = travelServiceDAO;
	}

	@POST
	@Path("/approve")
	@Override
	public boolean approve(ApproverVO approve) { // to approve request
		// TODO Auto-generated method stub
		return travelServiceDAO.approve(approve);
	}

	// create travel request
	@POST
	@Path("/createrequest")
	public RequestVO createRequest(RequestVO requestVO) {
		RequestVO vo = requestVO;
		try {
			vo = travelServiceDAO.createRequest(requestVO);
		} catch (RuntimeException e) {
			vo.setAction("ERROR");
		}
		return vo;
	}

	@GET
	@Path("/getApprovals/{userID}")
	public List<RequestVO> getApprovals(@PathParam("userID") String userID,
			@QueryParam("start") int start, @QueryParam("size") int size) {
		// TODO Auto-generated method stub
		return travelServiceDAO.getApprovals(userID, start, size);

	}

	@GET
	@Path("/masterInfo")
	public MasterInfo getMasterInformation(
			@QueryParam("tokenDate") String tokenDate) {
		return travelServiceDAO.getMasterInformation(tokenDate);
	}

	@GET
	@Path("/getRequests/{userID}")
	public List<RequestVO> getTravelRequests(
			@PathParam("userID") String userID, @QueryParam("start") int start,
			@QueryParam("size") int size) {
		// TODO Auto-generated method stub
		return travelServiceDAO.getTravelRequests(userID, start, size);
	}

	@GET
	@Path("/getDTA/{dtaCode}")
	public List<RequestVO> getDTA(@PathParam("dtaCode") String dtaCode) {
		// TODO Auto-generated method stub
		return travelServiceDAO.getDTA(dtaCode);
	}

	@POST
	@Path("/updateRequest")
	public RequestVO updateRequest(RequestVO requestVO) {
		System.out.println("Unimplemented updateRequest method invoked !!!");
		return null;
		// return travelServiceDAO.updateRequest(requestVO);

	}

	@GET
	@Path("/BOProject/{costCenter}/{userId}")
	@Override
	public List<BusinessOpportunityVO> getBOProject(
			@PathParam("costCenter") String costCenter,
			@PathParam("userId") String userId) {
		// TODO Auto-generated method stub
		return travelServiceDAO.getBOProject(costCenter, userId);
	}

	@Override
	@GET
	@Path("/getUserDetails/{userID}")
	public EmployeeVO isValidUser(@PathParam("userID") String userID) {

		return travelServiceDAO.isValidUser(userID);
	}

	@Override
	@GET
	@Path("/validToken")
	public boolean isValidToken() {
		// Default Stuff
		return travelServiceDAO.isValidToken();
	}

}
