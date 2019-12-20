package com.ibsplc.itin.serviceimpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ibsplc.itin.entity.CityMasterEntity;
import com.ibsplc.itin.entity.CommonMastersEntity;
import com.ibsplc.itin.entity.EmployeeDetailsEntity;
import com.ibsplc.itin.framework.iTinHibernateCallback;
import com.ibsplc.itin.service.AuthenticationService;
import com.ibsplc.itin.vo.EmployeeVO;

/**
 * 
 * @author A-4211
 * 
 */
@Path("/authenticate")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthenticationServiceImpl extends HibernateDaoSupport implements
		AuthenticationService {

	@Override
	// for fetching the logged in employee details
	@GET
	@Path("/validuser/{userID}/{password}")
	public EmployeeVO isValidUser(String userID, String password) {

		Object result = getHibernateTemplate().execute(
				new iTinHibernateCallback(new Object[] { userID, password }) {

					@Override
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						String userID = (String) this.getArguments()[0];
						String password = (String) this.getArguments()[1];

						List<EmployeeDetailsEntity> userDetails = new ArrayList<EmployeeDetailsEntity>();
						EmployeeVO employeeDetailsVo = new EmployeeVO();

						String query = "select c from EmployeeDetailsEntity c where employeeID='"
								+ userID + "'";
						userDetails = session.createQuery(query).list();
						if (userDetails.size() > 0) {
							EmployeeDetailsEntity employeeDetailsEntity = userDetails
									.get(0);
							employeeDetailsVo.setUserID(employeeDetailsEntity
									.getEmployeeID());
							employeeDetailsVo.setEmpName(employeeDetailsEntity
									.getEmployeeName().toUpperCase());
							employeeDetailsVo.setEmpCode(""
									+ employeeDetailsEntity.getEmployeeCode());
							// employeeDetailsVo.setEmpcompany(employeeDetailsEntity.getEmpcompany());
							// employeeDetailsVo.setActOnAgentId(employeeDetailsEntity.getEmployeeID());
							// employeeDetailsVo.setActOnAgentNm(employeeDetailsEntity.getEmployeeName().toUpperCase());
							// employeeDetailsVo.setActOnAgentCode(""+
							// employeeDetailsEntity.getEmployeeCode());
							// employeeDetailsVo.setMyApproval(employeeDetailsEntity.getMyApproval());
							employeeDetailsVo
									.setBaseStation(employeeDetailsEntity
											.getBaseStation());

							List<CommonMastersEntity> commMstrList = new ArrayList<CommonMastersEntity>();
							query = "select common from CommonMastersEntity common where common.masterCode = "
									+ employeeDetailsEntity.getDesignation();
							commMstrList = session.createQuery(query).list();
							if (commMstrList.size() > 0)
								employeeDetailsVo.setDesignation(commMstrList
										.get(0).getMasterDesc());

							query = "select common from CommonMastersEntity common where common.masterCode = "
									+ employeeDetailsEntity.getGrade();
							commMstrList = session.createQuery(query).list();
							if (commMstrList.size() > 0)
								employeeDetailsVo.setGrade(commMstrList.get(0)
										.getMasterDesc());

							employeeDetailsVo
									.setLobDptUnit(employeeDetailsEntity
											.getDepartment());

							String ccNo = ""
									+ employeeDetailsEntity.getCostCenter();
							query = "select common from CommonMastersEntity common where common.masterCode = "
									+ ccNo;
							List<CommonMastersEntity> ccEntities = session
									.createQuery(query).list();
							// List<CostCenterEntity> ccEntities =
							if (ccEntities.size() > 0)
								employeeDetailsVo.setCostCenter(ccEntities.get(
										0).getMasterDesc());
							if (null != ccNo && !"null".equals(ccNo)) {
								employeeDetailsVo.setCostCenterCode(Integer
										.parseInt(ccNo));
							}
							employeeDetailsVo.setProject(employeeDetailsEntity
									.getProjectName());
							// employeeDetailsVo.setLoginRole(employeeDetailsEntity.getLoginRole());
							// employeeDetailsVo.setActOnAgentLoginRole(employeeDetailsEntity.getLoginRole());
							/*
							 * String SQL_QUERY =
							 * "   SELECT cmp_dsc FROM itin_cmp_mst WHERE cmp_cde='"
							 * + employeeDetailsEntity.getEmpcompany() + "'";
							 * List<String> list =
							 * getHibernateTemplate().getSessionFactory()
							 * .openSession ().createSQLQuery(SQL_QUERY).list();
							 * if (null != list && list.size() > 0) { String
							 * cmpName = "" + list.get(0);
							 * employeeDetailsVo.setActOnAgentCompanyName
							 * (cmpName); }
							 */
							List<CityMasterEntity> regMstrList = new ArrayList<CityMasterEntity>();
							query = "select region from CityMasterEntity region where region.cityCode = '"
									+ employeeDetailsEntity.getBaseStation()
									+ "'";
							regMstrList = session.createQuery(query).list();

							// if (null !=regMstrList && regMstrList.size()
							// > 0)

							if (null != regMstrList
									&& regMstrList.size() > 0
									&& null != regMstrList.get(0)
											.getRegionCode()) {

								employeeDetailsVo.setBaseStationReg(regMstrList
										.get(0).getRegionCode().toString());
							}

						}
						return employeeDetailsVo;
					}

				});
		return (EmployeeVO) result;

	}

}
