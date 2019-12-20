package com.ibsplc.itin.database.dao;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ibsplc.itin.entity.CityMasterEntity;
import com.ibsplc.itin.entity.CommonMastersEntity;
import com.ibsplc.itin.entity.DTAApplicationDetailsEntity;
import com.ibsplc.itin.entity.DTACablEntity;
import com.ibsplc.itin.entity.DTACashAdvanceEntity;
import com.ibsplc.itin.entity.DTAFlightEntity;
import com.ibsplc.itin.entity.DTAFlightTrainEntity;
import com.ibsplc.itin.entity.DTAHotelEntity;
import com.ibsplc.itin.entity.DTAIndividualRequestEntity;
import com.ibsplc.itin.entity.DTATravelCCEntity;
import com.ibsplc.itin.entity.DTAVisaEntity;
import com.ibsplc.itin.entity.EmployeeDetailsEntity;
import com.ibsplc.itin.framework.iTinHibernateCallback;
import com.ibsplc.itin.service.ITinTravelServices;
import com.ibsplc.itin.vo.ApproverVO;
import com.ibsplc.itin.vo.BookingVO;
import com.ibsplc.itin.vo.BusinessOpportunityVO;
import com.ibsplc.itin.vo.CabVO;
import com.ibsplc.itin.vo.CashVO;
import com.ibsplc.itin.vo.CostCenterVO;
import com.ibsplc.itin.vo.CurrencyVO;
import com.ibsplc.itin.vo.EmployeeVO;
import com.ibsplc.itin.vo.HeadVO;
import com.ibsplc.itin.vo.HotelVO;
import com.ibsplc.itin.vo.MasterInfo;
import com.ibsplc.itin.vo.PlaceVO;
import com.ibsplc.itin.vo.PurposeVO;
import com.ibsplc.itin.vo.RequestVO;
import com.ibsplc.itin.vo.TravelVO;
import com.ibsplc.itin.vo.VisaVO;

/**
 * 
 * @author A-5152
 * 
 */

public class ITinTravelServiceDao extends HibernateDaoSupport implements
		ITinTravelServices {

	@Autowired
	private ITinTravelServiceJdbcDao jdbcDao;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ITinTravelServiceDao.class);

	private static final DateFormat DATE_ONLY_FORMATTER = new SimpleDateFormat(
			"dd-MMM-yyyy");
	private static final DateFormat DATE_AND_TIME_FORMATTER = new SimpleDateFormat(
			"dd-MMM-yyyy HH:mm");

	// //////////////////////////////////////////////////////////////////////////////////
	public ITinTravelServiceJdbcDao getJdbcDao() {
		return jdbcDao;
	}

	public void setJdbcDao(ITinTravelServiceJdbcDao jdbcDao) {
		this.jdbcDao = jdbcDao;
	}

	// //////////////////////////////////////////////////////////////////////////////////

	@Override
	// Method to view all the requests and its details raised by an employee.
	// input - employee code (4472 for test)
	// output - entire employee travel details
	public List<RequestVO> getTravelRequests(String userID, int start, int size) {

		return jdbcDao.getTravelRequests(userID, start, size);
	}

	// //////////////////////////////////////////////////////////////////////////////////
	@Override
	// Method to view the details of a particular request
	// input - dta code
	// output - entire travel details for a particular request
	public List<RequestVO> getDTA(String dtaCode) {
		return jdbcDao.getDTA(dtaCode);
	}

	// //////////////////////////////////////////////////////////////////////////////////
	// for getting the flight train booking details
	// input - dta code
	// output - list containing flight/train bookingdetails.
	public List<BookingVO> viewFlightTrainBkngDetails(String dtaCde) {
		return jdbcDao.viewFlightTrainBkngDetails(dtaCde);
	}

	// //////////////////////////////////////////////////////////////////////////////////
	// for getting the flight train request details
	// input - dta code
	// output - list containing flight/train details.
	public List<TravelVO> viewFlightTrainDetails(String dtaCde) {
		return jdbcDao.viewFlightTrainDetails(dtaCde);
	}

	// //////////////////////////////////////////////////////////////////////////////////
	// for getting the cost center details in case of a multi cc travel
	// input - dta code
	// output - list containing flight/train details.
	public List<CostCenterVO> viewCostCenterDetails(String dtaNo) {
		return jdbcDao.viewCostCenterDetails(dtaNo);
	}

	// //////////////////////////////////////////////////////////////////////////////////

	// for getting the expense request details
	// input - dta code
	// output - list containing flight/train details.
	public List<CashVO> viewCostDetails(String dtaNo) {
		return jdbcDao.viewCostDetails(dtaNo);
	}

	// //////////////////////////////////////////////////////////////////////////////////
	// for getting the hotel request details
	// input - dta code
	// output - list containing flight/train details.
	public List<HotelVO> viewHotelDetails(String dtaNo) {
		return jdbcDao.viewHotelDetails(dtaNo);
	}

	// //////////////////////////////////////////////////////////////////////////////////
	// for getting the cab request details
	// input - dta code
	// output - list containing flight/train details.
	public List<CabVO> viewCabDetails(String dtaNo) {
		return jdbcDao.viewCabDetails(dtaNo);
	}

	// //////////////////////////////////////////////////////////////////////////////////
	// for getting the flight train request details
	// input - dta code
	// output - list containing flight/train details.
	public List<VisaVO> viewVisaDetails(String dtaNo) {
		return jdbcDao.viewVisaDetails(dtaNo);
	}

	// //////////////////////////////////////////////////////////////////////////////////

	@Override
	public RequestVO updateRequest(RequestVO requestVO) {
		// TransactionDefinition paramTransactionDefinition = new
		// DefaultTransactionDefinition();
		// TransactionStatus status = transactionManager
		// .getTransaction(paramTransactionDefinition);
		//
		// try {
		//
		// transactionManager.commit(status);
		//
		// } catch (Exception e) {
		// transactionManager.rollback(status);
		// }

		return null;
	}

	// //////////////////////////////////////////////////////////////////////////////////

	@Override
	public RequestVO createRequest(RequestVO requestVO) {

		int flightTrainListSize;

		requestVO.setLegTravelDateString(requestVO.getLegTravelDateString());
		requestVO.setLastLegDepDateString(requestVO.getLastLegDepDateString());

		if (null != requestVO.getTravelDetails()
				&& requestVO.getTravelDetails().size() > 0) {
			requestVO.setLegOrigin(requestVO.getTravelDetails().get(0)
					.getOrginCode());
			requestVO.setLegDestination(requestVO.getTravelDetails().get(0)
					.getDestinationCode());
			requestVO.setLegTravelDateString(requestVO.getTravelDetails()
					.get(0).getDepartureDate());
			flightTrainListSize = requestVO.getTravelDetails().size();

	

			if ((requestVO.getRequestType().equalsIgnoreCase("D"))
					|| (requestVO.getRequestType().equalsIgnoreCase("C"))
					|| (requestVO.getRequestType().equalsIgnoreCase("G"))) {
				if (flightTrainListSize > 1) {
					requestVO.setLastLegDepDateString(requestVO
							.getTravelDetails().get(flightTrainListSize - 1)
							.getDepartureDate());
				}
				requestVO.setLastLegArrivalDate(requestVO.getTravelDetails()
						.get(requestVO.getTravelDetails().size() - 1)
						.getArrivalDate());
			} else if (requestVO.getRequestType().equalsIgnoreCase("S")) {
				
				requestVO.setLastLegDepDateString(requestVO.getTravelDetails()
						.get(flightTrainListSize - 1).getArrivalDate());
			}
		} else {
			
			if (requestVO.getCabDetails() != null
					&& requestVO.getCabDetails().size() != 0) {

				requestVO.setLegOrigin(requestVO.getCabDetails().get(0)
						.getCityCode());
				requestVO.setLegDestination(requestVO.getCabDetails().get(0)
						.getCityCode());
				requestVO.setLegTravelDateString(requestVO.getCabDetails()
						.get(0).getPickUpDate());
			}
		}
		if (requestVO.getAction().equalsIgnoreCase("ModifyDraft")) {

			if (null == requestVO.getTravelDetails()
					|| requestVO.getTravelDetails().size() == 0)
				deleteAllFlightDetails(requestVO);
			if (null == requestVO.getHotelDetails()
					|| requestVO.getHotelDetails().size() == 0)
				deleteAllHotelDetails(requestVO);
			if (null == requestVO.getCabDetails()
					|| requestVO.getCabDetails().size() == 0)
				deleteAllCabDetails(requestVO);
			if (null == requestVO.getVisaDetails()
					|| requestVO.getVisaDetails().size() == 0)
				deleteAllVisaDetails(requestVO);

			requestVO = modifyDraft(requestVO);
			requestVO.setAction("ModifyDraft");
			deleteIndHeadandDetailRequests(requestVO);
		} else {
			requestVO = createDta(requestVO);
		}
		requestVO.setRequestCode(requestVO.getRequestCode());
		requestVO.setNextAction(requestVO.getNextAction());
		requestVO.setNextApprover(requestVO.getNextApprover());
		requestVO.setRequestID(requestVO.getRequestID());
		createIndHeadandDetailRequests(requestVO);

		if (null != requestVO.getCashDetails()) {
			if (requestVO.getCashDetails().size() > 0) {
				addorUpdateCashAdvance(requestVO);
			}
		}

		/*
		 * if (!"".equals(dTAVo.getOldDtaNo())) { if
		 * ("ModifyDraft".equalsIgnoreCase(dTAVo.getAction())) {
		 * reportHelper.sendDTAMail(dTAVo.getDtaNo(), "None", "New", "1",
		 * 0,"","",0,dTAVo.getBaseStation()); } else {
		 * reportHelper.sendDTAMail(dTAVo.getOldDtaNo(), "None", "Stale", "3",
		 * 0,"","",0,dTAVo.getBaseStation());
		 * reportHelper.sendDTAMail(dTAVo.getDtaNo(), "None", "Modified", "1",
		 * 0,"","",0,dTAVo.getBaseStation()); } } else
		 * reportHelper.sendDTAMail(dTAVo.getDtaNo(), "None", "New", "1",
		 * 0,"","",0,dTAVo.getBaseStation());
		 */

		// uploadFiles(dTAVo); for uploading files
		return requestVO;
	}

	// //////////////////////////////////////////////////////////////////////////////////

	public boolean addorUpdateCashAdvance(RequestVO dtaVo) {

		if (null != dtaVo.getAction()
				&& dtaVo.getAction().equalsIgnoreCase("ModifyDraft")) {
			modifyDraftCashAdvanceMethod(dtaVo);
		} else {
			addorUpdateCashAdvanceMethod(dtaVo);
		}

		return true;
	}

	// //////////////////////////////////////////////////////////////////////////////////

	public void modifyDraftCashAdvanceMethod(RequestVO dtaVo) {

		getHibernateTemplate().execute(
				new iTinHibernateCallback(new Object[] { dtaVo }) {

					@Override
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						RequestVO dtaVo = (RequestVO) this.getArguments()[0];

						DTACashAdvanceEntity results = null;

						results = new DTACashAdvanceEntity();
						results.setDtaCode(dtaVo.getRequestCode());
						String query = "delete   from  "
								+ "ITIN_DTA_REQ_ADV dtaHotelEntity"
								+ " where dtaHotelEntity.DTA_CDE = '"
								+ dtaVo.getRequestCode() + "'";
						session.createSQLQuery(query).executeUpdate();

						addorUpdateCashAdvanceMethod(dtaVo);

						return null;
					}

				});
	}

	// //////////////////////////////////////////////////////////////////////////////////

	public boolean addorUpdateCashAdvanceMethod(RequestVO dtaVo) {

		Object result = getHibernateTemplate().execute(
				new iTinHibernateCallback(new Object[] { dtaVo }) {

					@Override
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {

						RequestVO dtaVo = (RequestVO) this.getArguments()[0];
						String dtaNo = dtaVo.getRequestCode();
						CashVO cashVo = dtaVo.getCashVo();
						String updtdBy = dtaVo.getUpdatedUser();
						Calendar cal = Calendar.getInstance();
						DateFormat formatter = new SimpleDateFormat(
								"dd-mm-yyyy HH:mm:ss");
						String dte = "";
						DTACashAdvanceEntity dtaCashAdvanceEntity = null;
						// List<CashVO> cashAdvanceVos =
						// cashVo.getCashAdvanceVos();
						List<CashVO> cashAdvanceVos = dtaVo.getCashDetails();

						CommonMastersEntity commonMastersEntity = new CommonMastersEntity();
						List<CommonMastersEntity> mstList = null;
						List<DTACashAdvanceEntity> cshAdvList = null;

						mstList = session
								.createCriteria(CommonMastersEntity.class)
								.add(Restrictions.eq("masterKey", "105"))
								.add(Restrictions.eq("status", "Y")).list();
						cshAdvList = session
								.createCriteria(DTACashAdvanceEntity.class)
								.add(Restrictions.eq("dtaCode", dtaNo))
								.add(Restrictions.eq("status", "Y"))

								.list();

						Map<String, Double> convRate = new HashMap<String, Double>();
						double convRte = 0;
						String curr = "";
						if (mstList.size() > 0) {
							for (Iterator<CommonMastersEntity> iterator = mstList
									.iterator(); iterator.hasNext();) {
								CommonMastersEntity commonMastersEntity1 = (CommonMastersEntity) iterator
										.next();
								convRte = Double
										.parseDouble(commonMastersEntity1
												.getConfigValue2());
								curr = commonMastersEntity1.getMasterCode();
								convRate.put(curr, convRte);
							}
						}
						ArrayList<Integer> cshAdvId = new ArrayList<Integer>();
						Map<Integer, DTACashAdvanceEntity> allCshAdvces = new HashMap<Integer, DTACashAdvanceEntity>();
						ArrayList<Integer> newCshAdvId = new ArrayList<Integer>();
						int cashAdvId;
						// if ("E".equalsIgnoreCase(dtaVo.getModorCop())) {
						if (cshAdvList.size() > 0) {
							for (Iterator<DTACashAdvanceEntity> iterator = cshAdvList
									.iterator(); iterator.hasNext();) {
								DTACashAdvanceEntity dTACashAdvanceEntity1 = (DTACashAdvanceEntity) iterator
										.next();
								cashAdvId = dTACashAdvanceEntity1
										.getCashAdvanceId();
								cshAdvId.add(cashAdvId);
								allCshAdvces.put(cashAdvId,
										dTACashAdvanceEntity1);
							}
						}
						// }

						for (Iterator iterator = cashAdvanceVos.iterator(); iterator
								.hasNext();) {
							CashVO currentCashVo = (CashVO) iterator.next();
							dtaCashAdvanceEntity = new DTACashAdvanceEntity();

							/*
							 * if ("E".equalsIgnoreCase(dtaVo.getModorCop())) {
							 * if ((null != currentCashVo.getAdvanceId()) &&
							 * (currentCashVo.getAdvanceId().length() > 0)) {
							 * dtaCashAdvanceEntity.setCashAdvanceId(Integer
							 * .parseInt(currentCashVo.getAdvanceId()));
							 * newCshAdvId.add(Integer.parseInt(currentCashVo
							 * .getAdvanceId())); } }
							 */
							dtaCashAdvanceEntity.setDtaCode(dtaNo);
							if (null != currentCashVo.getHeadCode()) {
								dtaCashAdvanceEntity.setTypeComboValue(Integer
										.parseInt(currentCashVo.getHeadCode()));
							}
							if (null != currentCashVo.getCurrencyCode()) {
								dtaCashAdvanceEntity
										.setAdvanceCurrency(currentCashVo
												.getCurrencyCode());
							}
							if (null != currentCashVo.getNoNights()) {
								dtaCashAdvanceEntity.setNumDays(currentCashVo
										.getNoNights());
							}
						

						
							if (null != currentCashVo.getAmount()) {
								dtaCashAdvanceEntity
										.setAdvanceTotal(currentCashVo
												.getAmount());
								dtaCashAdvanceEntity.setAmountInUsd(Math
										.round(Double.parseDouble(currentCashVo
												.getAmount())
												* convRate.get(currentCashVo
														.getCurrencyCode())));
							}
							
							if (null != currentCashVo.getRate()) {
								dtaCashAdvanceEntity
										.setAdvanceRate(currentCashVo.getRate());
							}

							if (null != updtdBy) {
								dtaCashAdvanceEntity
										.setLastUpdatedUser(updtdBy);
							}
							if (null != currentCashVo.getCreatedBy()) {
								dtaCashAdvanceEntity.setCreatedBy(currentCashVo
										.getCreatedBy());
							} else {
								if (null != updtdBy) {
									dtaCashAdvanceEntity.setCreatedBy(updtdBy);
								}
							}
							/*
							 * if (null !=
							 * currentCashVo.getMaximumLimitCurrencyCode()) { if
							 * (
							 * !"".equals(currentCashVo.getMaximumLimitCurrencyCode
							 * () .trim()))
							 * dtaCashAdvanceEntity.setMaxLimitCurrency(Integer
							 * .parseInt(currentCashVo
							 * .getMaximumLimitCurrencyCode().trim())); }
							 */
							if (null != currentCashVo.getBillable()) {

								if (currentCashVo.getBillable()
										.equalsIgnoreCase("Yes")) {
									dtaCashAdvanceEntity
											.setBillableToCustomer("Y");
								} else if (currentCashVo.getBillable()
										.equalsIgnoreCase(("No"))) {
									dtaCashAdvanceEntity
											.setBillableToCustomer("N");
								} else if (currentCashVo.getBillable()
										.equalsIgnoreCase(("Acc in Deal Pnl"))) {
									dtaCashAdvanceEntity
											.setBillableToCustomer("A");
								}
							}
							// if (null != currentCashVo.getBillable()) {
							if (currentCashVo.getBillable().equalsIgnoreCase(
									"Yes")) {
								dtaCashAdvanceEntity.setBillablePercent("100");
							}
							dtaCashAdvanceEntity.setCashAdvanceRequired("Y");
							dtaCashAdvanceEntity.setIsUserEntered("Y");
							dtaCashAdvanceEntity.setFromParDta("N");

							dtaCashAdvanceEntity.setStatus("Y");

							java.sql.Date sqltDate = new java.sql.Date(cal
									.getTime().getTime());
							dtaCashAdvanceEntity.setLastUpdatedDate(sqltDate);
							if (null != currentCashVo.getCreatedOn()) {
								try {
									dtaCashAdvanceEntity
											.setCreatedDate(formatter
													.parse(currentCashVo
															.getCreatedOn()));
								} catch (ParseException e) {
									throw new RuntimeException(e);
								}
							} else {
								dtaCashAdvanceEntity.setCreatedDate(sqltDate);
							}
						
							getHibernateTemplate().saveOrUpdate(
									dtaCashAdvanceEntity);
						}

						for (int i = 0; i < cshAdvId.size(); i++) {
							if (!newCshAdvId.contains(cshAdvId.get(i))) {

								dtaCashAdvanceEntity = allCshAdvces
										.get(cshAdvId.get(i));
								dtaCashAdvanceEntity.setStatus("N");
								java.sql.Date sqltDate = new java.sql.Date(cal
										.getTime().getTime());
								dtaCashAdvanceEntity
										.setLastUpdatedDate(sqltDate);
								getHibernateTemplate().saveOrUpdate(
										dtaCashAdvanceEntity);

							}
						}
						return false;
					}

				});
		return (Boolean) result;

	}

	// //////////////////////////////////////////////////////////////////////////////////

	@SuppressWarnings("unchecked")
	public void deleteAllFlightDetails(RequestVO vo) {
		getHibernateTemplate().execute(
				new iTinHibernateCallback(new Object[] { vo }) {

					@Override
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						RequestVO vo = (RequestVO) this.getArguments()[0];

						List<DTAFlightTrainEntity> list = null;
						list = session
								.createCriteria(DTAFlightTrainEntity.class)
								.add(Restrictions.eq("dtaNumber",
										Integer.parseInt(vo.getRequestCode())))
								.list();
						getHibernateTemplate().deleteAll(list);

						return null;
					}
				});

	}

	// //////////////////////////////////////////////////////////////////////////////////

	@SuppressWarnings("unchecked")
	public void deleteAllHotelDetails(RequestVO vo) {
		getHibernateTemplate().execute(
				new iTinHibernateCallback(new Object[] { vo }) {

					@Override
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						RequestVO vo = (RequestVO) this.getArguments()[0];

						List<DTAHotelEntity> list = null;
						list = session
								.createCriteria(DTAHotelEntity.class)
								.add(Restrictions.eq("dtaNumber",
										Integer.parseInt(vo.getRequestCode())))
								.list();
						getHibernateTemplate().deleteAll(list);
						return null;
					}
				});
	}

	// //////////////////////////////////////////////////////////////////////////////////
	@SuppressWarnings("unchecked")
	public void deleteAllCabDetails(RequestVO vo) {
		getHibernateTemplate().execute(
				new iTinHibernateCallback(new Object[] { vo }) {

					@Override
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						RequestVO vo = (RequestVO) this.getArguments()[0];

						List<DTACablEntity> list = null;
						list = session
								.createCriteria(DTACablEntity.class)
								.add(Restrictions.eq("dtaNumber",
										Integer.parseInt(vo.getRequestCode())))
								.list();
						getHibernateTemplate().deleteAll(list);
						return null;
					}
				});

	}

	// //////////////////////////////////////////////////////////////////////////////////

	@SuppressWarnings("unchecked")
	public void deleteAllVisaDetails(RequestVO vo) {
		getHibernateTemplate().execute(
				new iTinHibernateCallback(new Object[] { vo }) {

					@Override
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						RequestVO vo = (RequestVO) this.getArguments()[0];

						List<DTAVisaEntity> list = null;
						list = session
								.createCriteria(DTAVisaEntity.class)
								.add(Restrictions.eq("dtaNumber",
										Integer.parseInt(vo.getRequestCode())))
								.list();
						getHibernateTemplate().deleteAll(list);
						return null;
					}
				});
	}

	// //////////////////////////////////////////////////////////////////////////////////

	public void deleteIndHeadandDetailRequests(RequestVO dtaVo) {
		getHibernateTemplate().execute(
				new iTinHibernateCallback(new Object[] { dtaVo }) {

					@Override
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						RequestVO dtaVo = (RequestVO) this.getArguments()[0];

						DTAIndividualRequestEntity results = new DTAIndividualRequestEntity();
						ArrayList<DTAIndividualRequestEntity> saveList = new ArrayList<DTAIndividualRequestEntity>();

						if (null != dtaVo.getRequestCode()) {
							results = new DTAIndividualRequestEntity();
							results.setDtaNumber(Integer.parseInt(dtaVo
									.getRequestCode()));
							String query = "delete   from  "
									+ "ITIN_DTA_REQ_IND_DTL  DTAIndividualRequestEntity"
									+ " where DTAIndividualRequestEntity.DTA_CDE = '"
									+ dtaVo.getRequestCode() + "'";
							session.createSQLQuery(query).executeUpdate();
						}

						return null;
					}
				});

	}

	// //////////////////////////////////////////////////////////////////////////////////

	private int createIndHeadandDetailRequests(RequestVO dTAVo) {
		TravelVO travelVo = null;
		List<TravelVO> secondLeg = new ArrayList<TravelVO>();
		int indReqNum;

		travelVo = new TravelVO();

		if (null != dTAVo.getTravelDetails()
				&& dTAVo.getTravelDetails().size() > 0) {
			secondLeg.add(dTAVo.getTravelDetails().get(0));
			dTAVo.setHeadType(1033);
			if (dTAVo.getRequestType().equalsIgnoreCase("S")) {
				dTAVo.setBillableToCust(dTAVo.getTravelDetails().get(0)
						.getIsBillable());
			} else
				dTAVo.setBillableToCust(dTAVo.getFlightBillable());

			indReqNum = createIndividualRequest(dTAVo);


			if (dTAVo.isReturnTravel()) {
				travelVo.setDestinationCode(dTAVo.getTravelDetails().get(0)
						.getOrginCode());
				travelVo.setOrginCode(dTAVo.getTravelDetails().get(0)
						.getDestinationCode());
				travelVo.setDepartureDate(dTAVo.getTravelDetails().get(0)
						.getArrivalDate());

				travelVo.setDepartureTime(dTAVo.getTravelDetails().get(0)
						.getArrivalTime());
				secondLeg.add(travelVo);
				dTAVo.setTravelDetails(secondLeg);

			}
			if (null != dTAVo.getAction()
					&& dTAVo.getAction().equalsIgnoreCase("ModifyDraft")) {
				flightModifyDraft(dTAVo.getRequestCode(), indReqNum,
						dTAVo.getTravelDetails(), dTAVo.getEmpCode());
			} else {
				flightCreateRequest(dTAVo.getRequestCode(), indReqNum,
						dTAVo.getTravelDetails(), dTAVo.getEmpCode());
			}

		}
		
		/*
		 * if (null != dTAVo.getTravelDetails() &&
		 * dTAVo.getTravelDetails().size() > 0) { indReqNum =
		 * createIndividualRequest(dTAVo);
		 * createFltRequest(dTAVo.getRequestCode(), indReqNum, dTAVo
		 * .getTravelDetails(),dTAVo.getEmpCode());
		 * 
		 * } if (dTAVo.getTravelDetails() != null &&
		 * dTAVo.getTravelDetails().size() != 0) { indReqNum =
		 * createIndividualRequest(dTAVo);
		 * 
		 * flightCreateRequest(dTAVo.getRequestCode(), indReqNum, dTAVo
		 * .getTravelDetails(),dTAVo.getEmpCode());
		 * 
		 * }
		 */
		if (dTAVo.getCabDetails() != null && dTAVo.getCabDetails().size() != 0) {
			dTAVo.setHeadType(1035);

			indReqNum = createIndividualCabRequest(dTAVo); // modified by
															// SAVIN
															// (A-5152)
			if (null != dTAVo.getAction()
					&& dTAVo.getAction().equalsIgnoreCase("ModifyDraft")) {
				cabModifyDraft(dTAVo.getRequestCode(), indReqNum,
						dTAVo.getCabDetails(), dTAVo.getEmpCode());
				sendCreateRequestSMS(dTAVo.getDtaNo());
			} else {
				cabCreateRequest(dTAVo.getRequestCode(), indReqNum,
						dTAVo.getCabDetails(), dTAVo.getEmpCode());
				sendCreateRequestSMS(dTAVo.getDtaNo());
			}

		}
		if (dTAVo.getHotelDetails() != null
				&& dTAVo.getHotelDetails().size() != 0) {
			dTAVo.setHeadType(1036);
			if (dTAVo.getRequestType().equalsIgnoreCase("S")) {
				dTAVo.setBillableToCust(dTAVo.getHotelDetails().get(0)
						.getIsBillable());
			} else
				dTAVo.setBillableToCust(dTAVo.getHotelBillable());

			indReqNum = createIndividualRequest(dTAVo);
			if (null != dTAVo.getAction()
					&& dTAVo.getAction().equalsIgnoreCase("ModifyDraft")) {
				hotelModifyDraft(dTAVo.getRequestCode(), indReqNum,
						dTAVo.getHotelDetails(), dTAVo.getEmpCode());
			} else {
				hotelCreateRequest(dTAVo.getRequestCode(), indReqNum,
						dTAVo.getHotelDetails(), dTAVo.getEmpCode());

			}
			if (dTAVo.getVisaDetails() != null
					&& dTAVo.getVisaDetails().size() != 0) {
				dTAVo.setHeadType(1037);
				if (dTAVo.getRequestType().equalsIgnoreCase("S")) {
					dTAVo.setBillableToCust(dTAVo.getVisaDetails().get(0)
							.getIsBillable());
				} else
					dTAVo.setBillableToCust(dTAVo.getVisaBillable());
				indReqNum = createIndividualRequest(dTAVo);
				if (null != dTAVo.getAction()
						&& dTAVo.getAction().equalsIgnoreCase("ModifyDraft")) {
					visaModifyDraft(dTAVo.getRequestCode(), indReqNum,
							dTAVo.getVisaDetails());
				} else {
					visaCreateRequest(dTAVo.getRequestCode(), indReqNum,
							dTAVo.getVisaDetails());
				}
				// dtaDao.createIndividualRequest(dTAVo);
			}
			/*
			 * if (dTAVo.getInsuranceDetails() != null &&
			 * dTAVo.getInsuranceDetails().size() != 0) {
			 * dTAVo.setHeadType(1038); if
			 * ((dTAVo.getReqType().equalsIgnoreCase("D"))||
			 * (dTAVo.getReqType().equalsIgnoreCase("C")) ||
			 * (dTAVo.getReqType().equalsIgnoreCase("G"))) { for (int i = 0; i <
			 * dTAVo.getInsuranceDetails().size(); i++) { if (null !=
			 * dTAVo.getInsuranceDetails().get(i) .getExceptnRemarks()) {
			 * dTAVo.setExceptionRemarks(dTAVo .getInsuranceDetails().get(i)
			 * .getExceptnRemarks()); } } } //
			 * dTAVo.setExceptionRemarks(dTAVo.getInsuranceDetails().get(0). //
			 * getExceptnRemarks()); indReqNum =
			 * dtaDao.createIndividualRequest(dTAVo);
			 * insuranceService.createInsuranceRequest(dTAVo.getDtaNo(),
			 * indReqNum, dTAVo.getInsuranceDetails()); //
			 * dtaDao.createIndividualRequest(dTAVo);
			 */
		}
		return 0;

	}

	// //////////////////////////////////////////////////////////////////////////////////

	public int createIndividualRequest(RequestVO dtaVo) {

		DTAIndividualRequestEntity results = new DTAIndividualRequestEntity();
		ArrayList<DTAIndividualRequestEntity> saveList = new ArrayList<DTAIndividualRequestEntity>();

		results.setDtaNumber(Integer.parseInt(dtaVo.getRequestCode()));
		results.setCreatedBy(Integer.parseInt(dtaVo.getEmpCode()));
		results.setUpdatedBy(Integer.parseInt(dtaVo.getUpdatedUser()));
		try {
			results.setUpdatedOn((Date) DATE_AND_TIME_FORMATTER.parse(dtaVo
					.getRequestedDate()));
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		results.setIndItemCode(dtaVo.getHeadType());
		results.setDtaStatus(1834);
		results.setFlightRemarks(dtaVo.getRemarks());
		results.setBillableToCust(dtaVo.getBillableToCust());
		results.setCanTspView("Y"); // newly added by Savin Koshy on 30
									// October 2014 to fix the bug reported
									// by Vipin P.
		saveList.add(results);
		getHibernateTemplate().saveOrUpdateAll(saveList);

		return results.getDtaIndReqNum();
	}

	// //////////////////////////////////////////////////////////////////////////////////

	// SAVIN KOSHY (A-5152) on JUNE 16, 2014
	// For new Cab Request

	public int createIndividualCabRequest(RequestVO dtaVo) {
		// DateFormat formatter2 = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
		DTAIndividualRequestEntity results = new DTAIndividualRequestEntity();
		ArrayList<DTAIndividualRequestEntity> saveList = new ArrayList<DTAIndividualRequestEntity>();
		results.setDtaNumber(Integer.parseInt(dtaVo.getRequestCode()));
		results.setCreatedBy(Integer.parseInt(dtaVo.getEmpCode()));
		results.setUpdatedBy(Integer.parseInt(dtaVo.getUpdatedUser()));
		try {
			results.setUpdatedOn((Date) DATE_AND_TIME_FORMATTER.parse(dtaVo
					.getRequestedDate()));
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		results.setIndItemCode(dtaVo.getHeadType());
		results.setDtaStatus(1838);
		results.setQuoteTspId("IBS");
		results.setIsQuoteSelected('Y');
		results.setFlightRemarks(dtaVo.getRemarks());
		results.setBillableToCust(dtaVo.getBillableToCust());
		saveList.add(results);
		getHibernateTemplate().saveOrUpdateAll(saveList);
		return results.getDtaIndReqNum();
	}

	// //////////////////////////////////////////////////////////////////////////////////

	public void createFltRequest(String dtaNo, int dtaIndReqNo,
			List<TravelVO> travelList, String empCode) {

		DTAFlightEntity results = null;
		ArrayList<DTAFlightEntity> saveList = new ArrayList<DTAFlightEntity>();
		// DateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");
		

		for (int i = 0; i < travelList.size(); i++) {
			results = new DTAFlightEntity();
			results.setLegRemarks(travelList.get(i).getRemark());
			results.setIndReqNum(dtaIndReqNo);
			results.setOrgin(travelList.get(i).getOrginCode());
			results.setDestination(travelList.get(i).getDestinationCode());
			try {
				results.setDepartureDate(DATE_ONLY_FORMATTER.parse(travelList
						.get(i).getDepartureDate()));
				results.setArrivalDate(DATE_ONLY_FORMATTER.parse(travelList
						.get(i).getArrivalDate()));
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}
			results.setPrefferedTimeDep(String.valueOf(travelList.get(i)
					.getDepartureTime()));
			results.setPrefferedTimeDep(travelList.get(i).getArrivalTime());
			results.setDtaNumber(Integer.parseInt(dtaNo));
			results.setLegRemarks(travelList.get(i).getRemark());
			saveList.add(results);
		}
		getHibernateTemplate().saveOrUpdateAll(saveList);
	}

	// //////////////////////////////////////////////////////////////////////////////////

	public void flightModifyDraft(String dtaNo, int dtaIndReqNo,
			List<TravelVO> travelList, String empCode) {

		getHibernateTemplate().execute(
				new iTinHibernateCallback(new Object[] { dtaNo, dtaIndReqNo,
						travelList, empCode }) {

					@Override
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						String dtaNo = (String) this.getArguments()[0];
						int dtaIndReqNo = ((Integer) (this.getArguments()[1]))
								.intValue();
						List<TravelVO> travelList = ((List<TravelVO>) (this
								.getArguments()[2]));
						String empCode = (String) this.getArguments()[3];

						DTAFlightTrainEntity results = null;
						List<DTAFlightTrainEntity> saveList = new ArrayList<DTAFlightTrainEntity>();

						results = new DTAFlightTrainEntity();
						results.setDtaNumber(Integer.parseInt(dtaNo));
						String query = "delete   from  "
								+ "ITIN_DTA_REQ_TRV dtaFlightTrainEntity"
								+ " where dtaFlightTrainEntity.DTA_CDE = '"
								+ dtaNo + "'";
						session.createSQLQuery(query).executeUpdate();
						flightCreateRequest(dtaNo, dtaIndReqNo, travelList,
								empCode);

						return null;
					}

				});

	}

	// //////////////////////////////////////////////////////////////////////////////////

	public void flightCreateRequest(String dtaNo, int dtaIndReqNo,
			List<TravelVO> travelList, String empCode) {
		DTAFlightTrainEntity results = null;
		ArrayList<DTAFlightTrainEntity> saveList = new ArrayList<DTAFlightTrainEntity>();
		// DateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");
		Calendar calendar = Calendar.getInstance();

		// int lastColVal = 0;
		// int primaryKeyVal = 0;
		for (int i = 0; i < travelList.size(); i++) {
			results = new DTAFlightTrainEntity();
			results.setOriginCode(travelList.get(i).getOrginCode());
			results.setDtaNumber(Integer.parseInt(dtaNo));
			results.setIndReqNum(dtaIndReqNo);
			results.setOriginExactLocation(travelList.get(i).getExcatOrgin());
			results.setDestiantionExactLocation(travelList.get(i)
					.getExactDestination());

			try {
				if (null != travelList.get(i).getDepartureDate()
						&& travelList.get(i).getDepartureDate().trim().length() > 0
						&& !travelList.get(i).getDepartureDate()
								.equalsIgnoreCase("DD-MMM-YYYY"))
					results.setDepartureDate((java.util.Date) DATE_ONLY_FORMATTER
							.parse((travelList.get(i).getDepartureDate())));

				if (null != travelList.get(i).getArrivalDate()
						&& 0 != travelList.get(i).getArrivalDate().trim()
								.length()
						&& !travelList.get(i).getArrivalDate()
								.equalsIgnoreCase("DD-MMM-YYYY"))
					results.setArrivaldate((java.util.Date) DATE_ONLY_FORMATTER
							.parse((travelList.get(i).getArrivalDate())));
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}

			results.setPrefferedDepTime(travelList.get(i).getDepartureTime());
			results.setPrefferedArrTime(travelList.get(i).getArrivalTime());
			results.setDestinationCode(travelList.get(i).getDestinationCode());

			// time added by 2922
			results.setUpdatedOn(calendar.getTime());
			results.setLegRemarks(travelList.get(i).getRemark());
			results.setTravelMode(travelList.get(i).getModeOfTravel());
			saveList.add(results);

		}
		getHibernateTemplate().saveOrUpdateAll(saveList);

	}

	// //////////////////////////////////////////////////////////////////////////////////

	// newly added on July 16, 2014 by Savin Koshy (A-5152) for Cab
	public void cabModifyDraft(String dtaNo, int dtaIndReqNo,
			List<CabVO> travelList, String empCode) {

		DTACablEntity results = null;
		List<DTACablEntity> saveList = new ArrayList<DTACablEntity>();

		results = new DTACablEntity();
		results.setDtaNumber(Integer.parseInt(dtaNo));

		cabCreateRequest(dtaNo, dtaIndReqNo, travelList, empCode);
	}

	// //////////////////////////////////////////////////////////////////////////////////

	public void cabCreateRequest(String dtaNo, int dtaIndReqNo,
			List<CabVO> travelList, String empCode) {

		getHibernateTemplate().execute(
				new iTinHibernateCallback(new Object[] { dtaNo, dtaIndReqNo,
						travelList, empCode }) {

					@Override
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						String dtaNo = (String) this.getArguments()[0];
						int dtaIndReqNo = (Integer) this.getArguments()[1];
						List<CabVO> travelList = (List<CabVO>) this
								.getArguments()[2];
						String empCode = (String) this.getArguments()[3];

						DTACablEntity results = null;
						String conNumber = null;
						ArrayList<DTACablEntity> saveList = new ArrayList<DTACablEntity>();
						Calendar calendar = Calendar.getInstance();

						for (int i = 0; i < travelList.size(); i++) {
							results = new DTACablEntity();
							results.setDtaNumber(Integer.parseInt(dtaNo));
							results.setIndReqNum(dtaIndReqNo);
							results.setOrgin(travelList.get(i).getCityCode());
							results.setExactOrgin(travelList.get(i)
									.getPickUpPoint());
							results.setDestination(travelList.get(i)
									.getCityCode());
							results.setExactDestination(travelList.get(i)
									.getDropPoint());
							// results.setGuestContactNumber(travelList.get(i).getGuestContactNum());
							results.setContactNumber(travelList.get(i)
									.getContactNo());
							conNumber = travelList.get(i).getContactNo();
							results.setCabReturn(travelList.get(i)
									.getRetVehicle());
							results.setCabRetainDuration(travelList.get(i)
									.getRetDuration());
							if (travelList.get(i).getPickUpDate() != null
									|| travelList.get(i).getPickUpDate() != ""
									|| travelList.get(i).getPickUpDate() != "DD-MMM-YYYY") {
								try {
									results.setDepartureDate(DATE_ONLY_FORMATTER
											.parse(processDate(travelList
													.get(i).getPickUpDate())));
								} catch (ParseException e) {
									throw new RuntimeException(e);
								}
							}
							results.setPrefferedDepTime(travelList.get(i)
									.getPickUpTime());
							// time set by 2922
							results.setUpdatedOn(calendar.getTime());
							// results.setCabId(travelList.get(i).getSeqId());
							results.setUpdatedBy(travelList.get(i)
									.getUpdatedBy());
							// pass the employee code of the requester
							results.setLegRemarks(travelList.get(i).getRemark());
							saveList.add(results);
						}
						getHibernateTemplate().saveOrUpdateAll(saveList);
						if (null != conNumber) {
							session.flush();
							jdbcDao.updateMobileNumber(conNumber, empCode);
						}

						return null;
					}

				});

	}

	// //////////////////////////////////////////////////////////////////////////////////

	public void hotelModifyDraft(String dtaNo, int dtaIndReqNo,
			List<HotelVO> travelList, String empCode) {
		getHibernateTemplate().execute(
				new iTinHibernateCallback(new Object[] { dtaNo, dtaIndReqNo,
						travelList, empCode }) {

					@Override
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {

						String dtaNo = (String) this.getArguments()[0];
						int dtaIndReqNo = ((Integer) (this.getArguments()[1]))
								.intValue();
						List<HotelVO> travelList = ((List<HotelVO>) (this
								.getArguments()[2]));
						String empCode = (String) this.getArguments()[3];

						DTAHotelEntity results = null;
						// List<DTAHotelEntity> saveList = new
						// ArrayList<DTAHotelEntity>();

						results = new DTAHotelEntity();
						results.setDtaNumber(Integer.parseInt(dtaNo));
						String query = "delete   from  "
								+ "ITIN_DTA_REQ_HTL dtaHotelEntity"
								+ " where dtaHotelEntity.DTA_CDE = '" + dtaNo
								+ "'";
						session.createSQLQuery(query).executeUpdate();
						hotelCreateRequest(dtaNo, dtaIndReqNo, travelList,
								empCode);

						return null;
					}
				});

	}

	// //////////////////////////////////////////////////////////////////////////////////

	public void hotelCreateRequest(String dtaNo, int dtaIndReqNo,
			List<HotelVO> travelList, String empCode) {

		DTAHotelEntity results = null;

		// DateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");
		ArrayList<DTAHotelEntity> saveList = new ArrayList<DTAHotelEntity>();
		Calendar calendar = Calendar.getInstance();

		for (int i = 0; i < travelList.size(); i++) {
			results = new DTAHotelEntity();
			results.setDtaNumber(Integer.parseInt(dtaNo));
			results.setIndReqNum(dtaIndReqNo);
			results.setCity(travelList.get(i).getCityCode());
			results.setExactLocation(travelList.get(i).getExactLocation());

			try {
				if (null != travelList.get(i).getCheckInDate()
						&& 0 != travelList.get(i).getCheckInDate().trim()
								.length()
						&& !travelList.get(i).getCheckInDate()
								.equalsIgnoreCase("DD-MMM-YYYY"))
					results.setCheckInDate((java.util.Date) DATE_ONLY_FORMATTER
							.parse((travelList.get(i).getCheckInDate())));
				results.setCheckInPreferredTime(travelList.get(i)
						.getCheckInTime());

				if (null != travelList.get(i).getCheckOutDate()
						&& 0 != travelList.get(i).getCheckOutDate().trim()
								.length()
						&& !travelList.get(i).getCheckOutDate()
								.equalsIgnoreCase("DD-MMM-YYYY"))
					results.setCheckOutDate((java.util.Date) DATE_ONLY_FORMATTER
							.parse((travelList.get(i).getCheckOutDate())));

			} catch (ParseException e) {
				throw new RuntimeException(e);
			}
			results.setCheckOutPreferredTime(travelList.get(i)
					.getCheckOutTime());
			results.setUpdatedOn(calendar.getTime());
			results.setLegRemarks(travelList.get(i).getRemarks());
			saveList.add(results);
		}
		getHibernateTemplate().saveOrUpdateAll(saveList);
	}

	// //////////////////////////////////////////////////////////////////////////////////

	public void visaModifyDraft(String dtaNo, int dtaIndReqNo,
			List<VisaVO> visaDetails) {

		getHibernateTemplate().execute(
				new iTinHibernateCallback(new Object[] { dtaNo, dtaIndReqNo,
						visaDetails }) {

					@Override
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						String dtaNo = (String) this.getArguments()[0];
						int dtaIndReqNo = ((Integer) this.getArguments()[1])
								.intValue();
						List<VisaVO> visaDetails = (List<VisaVO>) this
								.getArguments()[2];

						DTAVisaEntity results = null;
						// List<DTAVisaEntity> saveList = new
						// ArrayList<DTAVisaEntity>();

						results = new DTAVisaEntity();
						results.setDtaNumber(Integer.parseInt(dtaNo));
						String query = " delete   from  "
								+ " ITIN_DTA_REQ_VIS dtaHotelEntity "
								+ " where dtaHotelEntity.DTA_CDE = '" + dtaNo
								+ "'";
						session.createSQLQuery(query).executeUpdate();
						visaCreateRequest(dtaNo, dtaIndReqNo, visaDetails);
						return null;
					}

				});

	}

	// //////////////////////////////////////////////////////////////////////////////////
	public String visaCreateRequest(String dtaNo, int dtaIndReqNo,
			List<VisaVO> visaDetails) {
		DTAVisaEntity results = null;
		ArrayList<DTAVisaEntity> saveList = new ArrayList<DTAVisaEntity>();
		Calendar calendar = Calendar.getInstance();

		for (int i = 0; i < visaDetails.size(); i++) {
			results = new DTAVisaEntity();

			results.setIndReqNum(dtaIndReqNo);
			if (null != visaDetails) {
				if (null != visaDetails.get(i)) {
					VisaVO visaDetailsVo = visaDetails.get(i);
					if (null != visaDetailsVo.getCountryCode()) {
						results.setCountry(visaDetails.get(i).getCountryCode());
					}
				}
			}

			results.setVisaType(visaDetails.get(i).getVisaName());
			try {
				if (null != visaDetails.get(i).getValidFrom())
					results.setValidFrom((java.util.Date) DATE_AND_TIME_FORMATTER
							.parse((visaDetails.get(i).getValidFrom())));
				if (null != visaDetails.get(i).getValidFrom())
					results.setValidTill((java.util.Date) DATE_AND_TIME_FORMATTER
							.parse((visaDetails.get(i).getValidTo())));
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}
			results.setRemarks(visaDetails.get(i).getRemarks());
			results.setDtaNumber(Integer.parseInt(dtaNo));
			results.setUpdatedOn(calendar.getTime());
			saveList.add(results);
		}
		getHibernateTemplate().saveOrUpdateAll(saveList);
		return null;
	}

	// //////////////////////////////////////////////////////////////////////////////////
	@SuppressWarnings("unchecked")
	public RequestVO modifyDraft(RequestVO dtaVo) {

		Object result = getHibernateTemplate().execute(
				new iTinHibernateCallback(new Object[] { dtaVo }) {

					@Override
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						RequestVO dtaVo = (RequestVO) this.getArguments()[0];

						String resultArray[] = null;
						DTAApplicationDetailsEntity entity = null;
						entity = new DTAApplicationDetailsEntity();
						DTATravelCCEntity entit = null;
						List<DTAApplicationDetailsEntity> saveList = new ArrayList<DTAApplicationDetailsEntity>();
						boolean isModifyDraft = false;

						if (dtaVo.getRequestCode() != null
								&& dtaVo.getAction().equalsIgnoreCase(
										"ModifyDraft")) {
							isModifyDraft = true;
						} else {
							isModifyDraft = false;
						}

						if (isModifyDraft) {

							dtaVo.setAction("Save");

							String query = " select dtaApplicationDetailsEntity from  "
									+ " DTAApplicationDetailsEntity dtaApplicationDetailsEntity "
									+ " where dtaApplicationDetailsEntity.dtaNumber = '"
									+ dtaVo.getRequestCode() + "'";
							saveList = getHibernateTemplate().find(query);
							if (saveList != null && saveList.size() > 0) {
								entity = saveList.get(0);
							}
						}

						if (null != dtaVo.getSendDraft()
								&& dtaVo.getSendDraft().equalsIgnoreCase(
										"SendDraft")) {
							dtaVo.setAction("Submit");
						}

						entity.setEmployeeId(dtaVo.getEmpCode());
						entity.setFirstLegOrigin(dtaVo.getLegOrigin());
						entity.setBo(dtaVo.getBO());
						entity.setCcCode(dtaVo.getCC());
						entity.setTravelPurpose(dtaVo.getPurpose());
						Integer t = Integer.parseInt(dtaVo.getPurposeCode());
						entity.setTravelCat(t);
						entity.setFirstLegDestination(dtaVo.getLegDestination());
						entity.setIsGuestTravel("N");
						try {
							if (null != dtaVo.getLegTravelDateString())
								entity.setFirstLegTravelDate((Date) DATE_ONLY_FORMATTER
										.parse(dtaVo.getLegTravelDateString()));
							if (null != dtaVo.getLastLegDepDateString())
								entity.setDtaLastLegDepDate((Date) DATE_ONLY_FORMATTER
										.parse(dtaVo.getLastLegDepDateString()));
						} catch (ParseException e) {
							throw new RuntimeException(e);
						}
						entity.setDtaLastApprovedEmpCode(0);
						entity.setLockByEmpCode(0);
						entity.setDtaReqType(dtaVo.getRequestType());
						entity.setCfoAppReq("N");
						entity.setOtherExp(dtaVo.getOtherExpenseDetails());
						entity.setComments(dtaVo.getRemarks());
						entity.setUpdatedBy(dtaVo.getUpdatedUser());
						try {
							entity.setUpdatedOn((Date) DATE_AND_TIME_FORMATTER
									.parse(dtaVo.getRequestedDate()));

						} catch (ParseException e) {
							throw new RuntimeException(e);
						}

						EmployeeDetailsEntity employeeDetailsEntity = getEmployeeDetails(dtaVo
								.getEmpCode());

						if (null != employeeDetailsEntity) {
							if ((null != employeeDetailsEntity.getDesignation())) {
								entity.setDesignation(String
										.valueOf(employeeDetailsEntity
												.getDesignation()));
							}
							if ((null != employeeDetailsEntity
									.getApproverCode())) {
								if (dtaVo.getAction()
										.equalsIgnoreCase("submit")) {
									entity.setDtaAcnBy((int) Integer
											.valueOf(employeeDetailsEntity
													.getApproverCode()
													.intValue()));
								}
							}
							if (null != employeeDetailsEntity.getGrade()
									&& !employeeDetailsEntity.getGrade()
											.equals("")) {
								entity.setGrade(String
										.valueOf(employeeDetailsEntity
												.getGrade()));
							}
						}

						String cmtstr = null;
						String action = null;
						String approvalFlow = "N";
						if (null != dtaVo.getRemarks()) {
							cmtstr = dtaVo.getRemarks();
						} else {
							cmtstr = "";
						}
						entity.setBookTicket("Y");
						entity.setTravelTentative("N");
						getHibernateTemplate().saveOrUpdate(entity);

						entit = new DTATravelCCEntity();
						entit.setBoCode(dtaVo.getBO());
						entit.setCcCode(dtaVo.getCC());
						entit.setCostAllocation(100);
						entit.setDtaNumber(Integer.parseInt(dtaVo
								.getRequestCode()));
						getHibernateTemplate().save(entit);

						if (dtaVo.getAction().equalsIgnoreCase("submit")) {
							action = "" + 2;
						} else {
							action = "" + 1;
						}

						String procString = "" + entity.getDtaNumber() + "~"
								+ entity.getEmployeeId() + "~"
								+ dtaVo.getUpdatedUser() + "~" + action + "~"
								+ approvalFlow;

						session.flush();
						String resultStatus = jdbcDao.callUpdateStatusProc(
								procString, cmtstr);

						resultArray = resultStatus.split("~");

						if (resultArray[2].equals("0")) {
							dtaVo.setNextApprover("");
						} else {
							dtaVo.setNextApprover(resultArray[2]);
						}
						if (resultArray[3].equals("0")) {
							dtaVo.setNextAction("");
						} else {
							dtaVo.setNextAction(resultArray[3]);
						}

						String workflowInputString = String.valueOf(entity
								.getDtaNumber());
						session.flush();
						String workflowResultString = jdbcDao
								.fetchItinFlowStatus(workflowInputString);

						dtaVo.setWorkFlow(workflowResultString);

						if (null == dtaVo.getRequestID()) {

							String query = " Select dtaApplicationDetailsEntity from  "
									+ " DTAApplicationDetailsEntity dtaApplicationDetailsEntity"
									+ " where dta_cde='"
									+ dtaVo.getRequestCode() + "'";
							saveList = session.createQuery(query).list();

							if (saveList != null && saveList.size() > 0) {
								dtaVo.setRequestID(String.valueOf(saveList.get(
										0).getDtaId()));
							}
						}
						if (action == "2") {
							dtaVo.setSendDraft("submit");
						} else if (action == "1") {
							dtaVo.setSendDraft("save");
						}

						return dtaVo;
					}

				});

		return (RequestVO) result;

	}

	// //////////////////////////////////////////////////////////////////////////////////

	@SuppressWarnings("unchecked")
	public synchronized RequestVO createDta(RequestVO dtaVo) {

		Object resulter = getHibernateTemplate().execute(
				new iTinHibernateCallback(new Object[] { dtaVo }) {

					@Override
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						RequestVO dtaVo = (RequestVO) this.getArguments()[0];

					
						TravelVO travelVo = null;
						String dtaNo = null;
						String resultArray[] = null;
						DTAApplicationDetailsEntity entity = null;
						DTATravelCCEntity entit = null;
						List<RequestVO> newList = new ArrayList<RequestVO>();
						List<DTAApplicationDetailsEntity> saveList = new ArrayList<DTAApplicationDetailsEntity>();
						List<DTATravelCCEntity> saveCCList = new ArrayList<DTATravelCCEntity>();
						// int dtaNum = 100;

						entity = new DTAApplicationDetailsEntity();
						entity.setEmployeeId(dtaVo.getEmpCode());
						entity.setFirstLegOrigin(dtaVo.getLegOrigin());
						entity.setBo(dtaVo.getBO());
						entity.setCcCode(dtaVo.getCC());
						entity.setTravelPurpose(dtaVo.getPurpose());
						Integer t = Integer.parseInt(dtaVo.getPurposeCode());
						entity.setTravelCat(t);
						entity.setFirstLegDestination(dtaVo.getLegDestination());
						entity.setIsGuestTravel("N");

						try {
							if (null != dtaVo.getLegTravelDateString()
									&& dtaVo.getLegTravelDateString().trim()
											.length() > 0
									&& !dtaVo.getLegTravelDateString()
											.equalsIgnoreCase("DD-MMM-YYYY"))
								entity.setFirstLegTravelDate((Date) DATE_ONLY_FORMATTER
										.parse(processDate(dtaVo
												.getLegTravelDateString())));
							if (null != dtaVo.getLastLegDepDateString()
									&& dtaVo.getLastLegDepDateString().trim()
											.length() > 0
									&& !dtaVo.getLastLegDepDateString()
											.equalsIgnoreCase("DD-MMM-YYYY"))
								entity.setDtaLastLegDepDate((Date) DATE_ONLY_FORMATTER
										.parse(dtaVo.getLastLegDepDateString()));
							if (null != dtaVo.getLastLegArrivalDate()
									&& dtaVo.getLastLegArrivalDate().trim()
											.length() > 0
									&& !dtaVo.getLastLegArrivalDate()
											.equalsIgnoreCase("DD-MMM-YYYY"))
								entity.setLastLegArrivalDate((Date) DATE_ONLY_FORMATTER
										.parse(dtaVo.getLastLegArrivalDate()));
						} catch (ParseException e) {
							throw new RuntimeException(e);
						}

						entity.setCreatedBy(dtaVo.getEmpCode());
						entity.setUpdatedBy(dtaVo.getUpdatedUser());
						entity.setDtaLastApprovedEmpCode(0);
						entity.setLockByEmpCode(0);
						entity.setDtaReqType(dtaVo.getRequestType());
						entity.setCfoAppReq("N");
						entity.setOtherExp(dtaVo.getOtherExpenseDetails());
						entity.setComments(dtaVo.getRemarks());
						entity.setNoofDays(dtaVo.getNumOfNights());
						try {
							entity.setUpdatedOn((Date) DATE_AND_TIME_FORMATTER
									.parse(dtaVo.getRequestedDate()));
							entity.setCreatedOn((Date) DATE_AND_TIME_FORMATTER
									.parse(dtaVo.getRequestedDate()));
						} catch (ParseException e) {
							throw new RuntimeException(e);
						}
						entity.setCcCode(dtaVo.getCC());
						EmployeeDetailsEntity employeeDetailsEntity = getEmployeeDetails(dtaVo
								.getEmpCode());

						if (null != employeeDetailsEntity) {
							if ((null != employeeDetailsEntity.getDesignation())) {
								entity.setDesignation(String
										.valueOf(employeeDetailsEntity
												.getDesignation()));
							}
							if ((null != employeeDetailsEntity
									.getApproverCode())) {
								if (dtaVo.getAction()
										.equalsIgnoreCase("submit")) {
									entity.setDtaAcnBy((int) Integer
											.valueOf(employeeDetailsEntity
													.getApproverCode()
													.intValue()));

								}
							}
							if (null != employeeDetailsEntity.getGrade()
									&& !employeeDetailsEntity.getGrade()
											.equals("")) {
								entity.setGrade(String
										.valueOf(employeeDetailsEntity
												.getGrade()));
							}
						}

						String typeofReq = null;
						int nextDtaNo = jdbcDao.generateNextDtaNumber();

						if ((null != dtaVo.getRequestCode())
								&& !(dtaVo.getRequestCode().trim()
										.equalsIgnoreCase(""))) {
							// oldDtaNo = dtaVo.getRequestCode();
							entity.setDtaNumber(nextDtaNo);
							typeofReq = "M";
						} else {
							// oldDtaNo = "";
							typeofReq = "N";
							entity.setDtaNumber(nextDtaNo);
						}

						String cmtstr = null;
						String action = null;
						String approvalFlow = "N";
						// int cabCheck = 0;
						// List<TravelVO> newCabList = null;
						if (null != dtaVo.getRemarks()) {
							cmtstr = dtaVo.getRemarks();
						} else {
							cmtstr = "";
						}
						entity.setBookTicket("Y");
						entity.setTravelTentative("N");

						if (dtaVo.isReturnTravel()) {
							entity.setIsReturnTravel("Y");
						} else {
							entity.setIsReturnTravel("N");
						}

						getHibernateTemplate().save(entity);

						if (dtaVo.getCostCenters() != null) {
							if (dtaVo.getCostCenters().size() > 0) {
								for (int i = 0; i < dtaVo.getCostCenters()
										.size(); i++) {
									entit = new DTATravelCCEntity();
									entit.setCcCode(dtaVo.getCostCenters()
											.get(i).getCostCenterCode());
									// entit.setBoCode(dtaVo.getCostCenters().get(i).getBoProjectCode());
									// entit.setCostAllocation(dtaVo.getCostCenters().get(i).getCostAllocation());
								}
								entit.setDtaNumber(nextDtaNo);
								getHibernateTemplate().save(entit);
							} else {
								entit = new DTATravelCCEntity();
								entit.setBoCode(dtaVo.getBO());
								entit.setCcCode(dtaVo.getCC());
								entit.setCostAllocation(100);
								entit.setDtaNumber(nextDtaNo);
								getHibernateTemplate().save(entit);
							}
						} else {
							entit = new DTATravelCCEntity();
							entit.setBoCode(dtaVo.getBO());
							entit.setCcCode(dtaVo.getCC());
							entit.setCostAllocation(100);
							entit.setDtaNumber(nextDtaNo);
							getHibernateTemplate().save(entit);

						}

						if (dtaVo.getAction().equalsIgnoreCase("submit")) {
							action = "" + 2;
						}

						else {
							action = "" + 1;
						}

						String procString = "" + entity.getDtaNumber() + "~"
								+ entity.getEmployeeId() + "~"
								+ dtaVo.getUpdatedUser() + "~" + action + "~"
								+ approvalFlow;

						String dtaCode = "" + entity.getDtaNumber();
						
						System.out.println("procSTring" + procString);
					

						session.flush(); // flush before invoking
											// jdbc
						String statusUpdate = jdbcDao.callUpdateStatusProc(
								procString, cmtstr);

						resultArray = statusUpdate.split("~");
						if (resultArray[2].equals("0")) {
							dtaVo.setNextApprover("");
						} else {
							dtaVo.setNextApprover(resultArray[2]);
						}
						if (resultArray[3].equals("0")) {
							dtaVo.setNextAction("");
						} else {
							dtaVo.setNextAction(resultArray[3]);
						}

						String workflowInputString = String.valueOf(entity
								.getDtaNumber());

						session.flush();
						String workflowResultString = jdbcDao
								.fetchItinFlowStatus(workflowInputString);

						dtaVo.setWorkFlow(workflowResultString);

						session.flush();
						String appCode = jdbcDao.fetchAppCode(
								String.valueOf(entity.getDtaNumber()),
								dtaVo.getRequestCode(),
								Integer.parseInt(entity.getEmployeeId()),
								typeofReq);
						dtaVo.setRequestID(appCode);

						// Mobile Notifications to approver
						// try {
						// System.out.println("Mobile Notification before fire "
						// + dtaVo.getRequestCode() + "  " + dtaCode);
						// mobileNotificationToApprover(dtaCode);
						// System.out.println("Mobile Notification after fire");
						//
						// } catch (Exception ex) {
						// ex.printStackTrace();
						// }

						dtaVo.setRequestCode(String.valueOf(entity
								.getDtaNumber()));

						return dtaVo;

					}

				});
		return (RequestVO) resulter;

	}

	// //////////////////////////////////////////////////////////////////////////////////
	public String processDate(String invDate) {
		String[] temp = new String[3];
		String dateStr = "";
		temp = invDate.split("-");
		if (temp[1].equalsIgnoreCase("01") || temp[1].equalsIgnoreCase("1"))
			temp[1] = "JAN";
		else if (temp[1].equalsIgnoreCase("02")
				|| temp[1].equalsIgnoreCase("2"))
			temp[1] = "FEB";
		else if (temp[1].equalsIgnoreCase("03")
				|| temp[1].equalsIgnoreCase("3"))
			temp[1] = "MAR";
		else if (temp[1].equalsIgnoreCase("04")
				|| temp[1].equalsIgnoreCase("4"))
			temp[1] = "APR";
		else if (temp[1].equalsIgnoreCase("05")
				|| temp[1].equalsIgnoreCase("5"))
			temp[1] = "MAY";
		else if (temp[1].equalsIgnoreCase("06")
				|| temp[1].equalsIgnoreCase("6"))
			temp[1] = "JUN";
		else if (temp[1].equalsIgnoreCase("07")
				|| temp[1].equalsIgnoreCase("7"))
			temp[1] = "JUL";
		else if (temp[1].equalsIgnoreCase("08")
				|| temp[1].equalsIgnoreCase("8"))
			temp[1] = "AUG";
		else if (temp[1].equalsIgnoreCase("09")
				|| temp[1].equalsIgnoreCase("9"))
			temp[1] = "SEP";
		else if (temp[1].equalsIgnoreCase("10"))
			temp[1] = "OCT";
		else if (temp[1].equalsIgnoreCase("11"))
			temp[1] = "NOV";
		else if (temp[1].equalsIgnoreCase("12"))
			temp[1] = "DEC";
		dateStr = temp[0] + "-" + temp[1] + "-" + temp[2];

		return dateStr;
	}

	// //////////////////////////////////////////////////////////////////////////////////
	// SMS triggered to employees when a new Cab request is created.
	// Added by Savin Koshy (A-5152) on July 16,2014
	public void sendCreateRequestSMS(String dtaCode) {
		jdbcDao.sendCreateRequestSMS(dtaCode);
	}

	// //////////////////////////////////////////////////////////////////////////////////

	@SuppressWarnings("unchecked")
	public EmployeeDetailsEntity getEmployeeDetails(String empCode) {
		Object result = getHibernateTemplate().execute(
				new iTinHibernateCallback(new Object[] { empCode }) {

					@Override
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						String empCode = (String) this.getArguments()[0];

						EmployeeDetailsEntity employeeDetailsEntity = null;

						Criteria criteria = session
								.createCriteria(EmployeeDetailsEntity.class);
						Criterion criterion = null;
						criterion = Restrictions.eq("employeeCode",
								Long.parseLong(empCode));
						criteria.add(criterion);
						List<EmployeeDetailsEntity> empList = criteria.list();
						if ((null != empList) && (empList.size() > 0)) {
							employeeDetailsEntity = empList.get(0);
						}

						return employeeDetailsEntity;
					}

				});
		return (EmployeeDetailsEntity) result;

	}

	// //////////////////////////////////////////////////////////////////////////////////

	@Override
	// for getting all the master informations for raising a request
	public MasterInfo getMasterInformation(String tokenDate) {
		String compareDate = "31-DEC-2008 12:00:00 AM";
		
		MasterInfo masterInfo = new MasterInfo();
		masterInfo.setCities(getCities(tokenDate));
		masterInfo.setIndianCities(getIndianCities(tokenDate));
		masterInfo.setCostCenters(getCostCenter(tokenDate));
		masterInfo.setCountryMasterList(getCountries(tokenDate));
		masterInfo.setPurposeDetails(getPurposeTravelList(tokenDate));
		masterInfo.setCurrencyDetails(getCurrency(tokenDate));
		masterInfo.setHeadDetails(getHeads(tokenDate));

		masterInfo.setTokenDate(getTokenDate());

		tokenDate = masterInfo.getTokenDate();

	

		return masterInfo;

	}

	// //////////////////////////////////////////////////////////////////////////////////

	// for getting the highest created date
	public String getTokenDate() {
		return jdbcDao.getTokenDate();
	}

	// //////////////////////////////////////////////////////////////////////////////////

	// for getting the cities list
	public List<PlaceVO> getCities(String tokenDate) {
		return jdbcDao.getCities(tokenDate);
	}

	// //////////////////////////////////////////////////////////////////////////////////

	// for getting the currency details for Expense Details
	public List<CurrencyVO> getCurrency(String tokenDate) {
		return jdbcDao.getCurrency(tokenDate);
	}

	// //////////////////////////////////////////////////////////////////////////////////

	// for getting the head details for Expense Details
	public List<HeadVO> getHeads(String tokenDate) {
		return jdbcDao.getHeads(tokenDate);
	}

	// //////////////////////////////////////////////////////////////////////////////////
	// for getting the Indian cities list only
	public List<PlaceVO> getIndianCities(String tokenDate) {
		return jdbcDao.getIndianCities(tokenDate);
	}

	// //////////////////////////////////////////////////////////////////////////////////
	// for getting the country list
	public List<PlaceVO> getCountries(String tokenDate) {
		return jdbcDao.getCountries(tokenDate);
	}

	// //////////////////////////////////////////////////////////////////////////////////

	// for getting the cost center list
	public List<CostCenterVO> getCostCenter(String tokenDate) {
		return jdbcDao.getCostCenter(tokenDate);
	}

	// //////////////////////////////////////////////////////////////////////////////////

	// for getting the purpose list
	public List<PurposeVO> getPurposeTravelList(String tokenDate) {
		return jdbcDao.getPurposeTravelList(tokenDate);
	}

	// //////////////////////////////////////////////////////////////////////////////////

	@Override
	// for getting the approver's pending request view
	// input - employee code
	public List<RequestVO> getApprovals(String userID, int start, int size) {
		return jdbcDao.getApprovals(userID, start, size);
	}

	// //////////////////////////////////////////////////////////////////////////////////

	@Override
	public boolean approve(ApproverVO approve) {

		if (null != approve.getDtavoList() && approve.getDtavoList().size() > 0) {
	
			for (int i = 0; i < approve.getDtavoList().size(); i++) {

				List<String> dtasToApprove = approve.getDtavoList();
				
				String dtaNum = approve.getDtavoList().get(i);
				approveDta(dtaNum, approve);
			}
			return true;
		} else {
			return false;
		}
	}

	// //////////////////////////////////////////////////////////////////////////////////

	public boolean approveDta(String dtaNum, ApproverVO approve) {
		return jdbcDao.approveDta(dtaNum, approve);
	}

	// //////////////////////////////////////////////////////////////////////////////////

	/*
	 * // Mobile Notification method to Initiator public List<Content>
	 * mobileNotificationToInitiator(String dtaNum) {
	 * 
	 * List<Content> contentList = null; Connection connection = null; try {
	 * session = getHibernateTemplate().getSessionFactory().openSession();
	 * String query =
	 * "select emp_id, emp_nam from ITIN_EMP_MST WHERE emp_cde = (SELECT emp_cde from ITIN_DTA_APP_DTL WHERE DTA_CDE = '"
	 * +dtaNum+"')"; session =
	 * getHibernateTemplate().getSessionFactory().openSession();
	 * 
	 * try { connection = session.connection(); ps =
	 * connection.prepareStatement(query); res = ps.executeQuery(); String
	 * empName = ""; contentList = new ArrayList<Content>(); while (res.next())
	 * { Content msg = new Content(); empName = res.getString("emp_nam");
	 * msg.setData("iTin Notification"); msg.setDescription("Hi '"+empName+
	 * "', your DTA has been recommended/approved in iTin. ");
	 * msg.setEmpID(res.getString("emp_id"));
	 * 
	 * System.out.println("Employee Id "+msg.getEmpID()); contentList.add(msg);
	 * 
	 * } } catch (SQLException e) { e.printStackTrace(); } } catch
	 * (HibernateException e) { e.printStackTrace(); } finally {
	 * session.close(); try { if (null != connection) connection.close();
	 * res.close(); ps.close(); } catch (SQLException e) { e.printStackTrace();
	 * } }
	 * 
	 * System.out.println("Message is reaching ******************** ");
	 * 
	 * for(Content content : contentList){ try{
	 * System.out.println(content.toString());
	 * 
	 * new PnsService().sendNotification(content);
	 * 
	 * }catch (Exception exception) { exception.printStackTrace(); } }
	 * 
	 * return contentList; }
	 * 
	 * 
	 * // Mobile Notification method to next Approver public List<Content>
	 * mobileNotificationToApprover(String dtaNum) {
	 * 
	 * List<Content> contentList = null; Connection connection = null; try {
	 * session = getHibernateTemplate().getSessionFactory().openSession();
	 * String query =
	 * "SELECT app.dta_id,app.dta_sts, emp.emp_id emp_id, emp.emp_nam emp_nam,(SELECT emp_id FROM itin_emp_mst  WHERE emp_cde = app.dta_acn_by) nextApprover FROM itin_dta_app_dtl app, itin_emp_mst emp WHERE emp.emp_cde = app.emp_cde AND DTA_CDE = '"
	 * +dtaNum+"'"; //String query =
	 * "select emp_id, emp_nam from ITIN_EMP_MST WHERE emp_cde = (SELECT emp_cde from ITIN_DTA_APP_DTL WHERE DTA_CDE = '"
	 * +dtaNum+"')"; session =
	 * getHibernateTemplate().getSessionFactory().openSession();
	 * 
	 * try { connection = session.connection(); ps =
	 * connection.prepareStatement(query); res = ps.executeQuery(); String
	 * reqNum = ""; String empName = ""; contentList = new ArrayList<Content>();
	 * while (res.next()) { Content msg = new Content(); reqNum =
	 * res.getString("dta_id"); empName = res.getString("emp_nam");
	 * msg.setData("iTin Notification");
	 * msg.setDescription("Please approve the DTA '"
	 * +reqNum+"' raised by '"+empName+"' in iTin. ");
	 * msg.setEmpID(res.getString("nextApprover"));
	 * 
	 * System.out.println("Next Approver is "+msg.getEmpID());
	 * 
	 * contentList.add(msg);
	 * 
	 * } } catch (SQLException e) { e.printStackTrace(); } } catch
	 * (HibernateException e) { e.printStackTrace(); } finally {
	 * session.close(); try { if (null != connection) connection.close();
	 * res.close(); ps.close(); } catch (SQLException e) { e.printStackTrace();
	 * } }
	 * 
	 * for(Content content : contentList){
	 * 
	 * try{ System.out.println(content.toString());
	 * 
	 * new PnsService().sendNotification(content);
	 * 
	 * }catch (Exception exception) { exception.printStackTrace(); } }
	 * 
	 * return contentList; }
	 */

	// //////////////////////////////////////////////////////////////////////////////////
	@Override
	// for getting the BO/Project
	// inputs - cost center and employee code
	// output - bo/project list
	public List<BusinessOpportunityVO> getBOProject(String costCenter,
			String userId) {
		return jdbcDao.getBOProject(costCenter, userId);
	}

	// //////////////////////////////////////////////////////////////////////////////////

	@Override
	public EmployeeVO isValidUser(String userID) {

		Object result = getHibernateTemplate().execute(
				new iTinHibernateCallback(new Object[] { userID }) {

					@Override
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						String userID = (String) this.getArguments()[0];

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

	@Override
	public boolean isValidToken() {
		// Default Stuff
		return true;
	}

}
