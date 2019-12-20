package com.ibsplc.itin.database.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.ibsplc.itin.framework.iTinJdbcConnectionCallback;
import com.ibsplc.itin.vo.ApproverVO;
import com.ibsplc.itin.vo.BookingVO;
import com.ibsplc.itin.vo.BusinessOpportunityVO;
import com.ibsplc.itin.vo.CabVO;
import com.ibsplc.itin.vo.CashVO;
import com.ibsplc.itin.vo.CostCenterVO;
import com.ibsplc.itin.vo.CurrencyVO;
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

public class ITinTravelServiceJdbcDao extends JdbcDaoSupport {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ITinTravelServiceJdbcDao.class);
	
	public static final Format DATE_ONLY_FORMATTER = new SimpleDateFormat(
			"dd-MMM-yyyy");
	public static final DateFormat DATE_AND_TIME_FORMATTER = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	// //////////////////////////////////////////////////////////////////////////////////
	// Method to view all the requests and its details raised by an employee.
	// input - employee code (4472 for test)
	// output - entire employee travel details
	public List<RequestVO> getTravelRequests(String userID, int start, int size) {

		String query = "SELECT DISTINCT NVL((a.DTA_ID),a.dta_cde) DTA_ID,a.dta_cde,e.emp_cde,e.emp_id,e.emp_nam,"
				+ "a.fst_leg_org orginfreeText,a.fst_leg_dst destfreeText,(SELECT citymaster.cty_nam FROM "
				+ "itin_cty_mst citymaster WHERE citymaster.cty_cde = a.fst_leg_org)origin,"
				+ "(SELECT citymaster.cty_nam FROM itin_cty_mst citymaster WHERE citymaster.cty_cde = a.fst_leg_dst)dest, "
				+ "a.CRT_DAT req_date, a.fst_leg_tvl_dat fst_leg_tvl_date, a.lst_leg_dep_dat, "
//				+ "(SELECT MST_DSC FROM ITIN_COM_MST WHERE MST_CDE=c.TVL_BO) AS bo, "
//				+ "(SELECT MST_CDE FROM ITIN_COM_MST WHERE MST_CDE=c.TVL_BO) AS bo_code, "
//				+ "(SELECT MST_KEY FROM ITIN_COM_MST WHERE MST_CDE=c.TVL_BO) AS bo_type, "
//				+ "(SELECT CV1_VAL FROM ITIN_COM_MST WHERE MST_CDE=c.TVL_BO) AS bo_commercial, "
//				+ "(SELECT NVL(ifin_cc_nm,mst_dsc) FROM itin_ifin_erp_cc ifin,itin_com_mst mst "
//				+ "WHERE mst.mst_cde = c.cc_cde AND mst.mst_dsc   = ifin.erp_cc_nam(+) ) AS cc, "
				+ "(SELECT MST_DSC FROM ITIN_COM_MST WHERE MST_CDE=A.TVL_CAT) AS trvpurposename, "
				+ "(SELECT MST_DSC FROM itin_com_mst WHERE mst_cde = a.DTA_STS) sts, a.dta_sts stscde, "
				+ "(SELECT EMP_BSE_STN FROM ITIN_EMP_MST WHERE EMP_CDE=A.EMP_CDE) AS BASE_STATION, "
				+ "(case when (a.is_clm_rsd <> 'Y' OR a.is_clm_rsd IS NULL) then (select sum(adv_in_usd) from itin_dta_req_adv where dta_cde=A.dta_cde and mst_sts='Y') "
				+ "else (select nvl(sum(amt_in_usd),0) from itin_dtc_clm where dta_cde=A.dta_cde and mst_sts='Y') "
				+ " +(select nvl(sum(exp_amt_usd),0) from itin_dtc_misc_dtl where dta_cde=A.dta_cde and mst_sts='Y') "
				+ " end) TOT_AMT,"
				+ "(SELECT DECODE (NVL(INSTR (wm_concat(is_bil_cst),'Y'),0), 0, 'N', 'Y') FROM ITIN_DTA_REQ_ADV WHERE dta_cde = a.dta_cde AND adv_itm_typ ='1020') ft_billable_yn, "
				+ "(SELECT DECODE (NVL(INSTR (wm_concat(is_bil_cst),'Y'),0), 0, 'N', 'Y') FROM ITIN_DTA_REQ_ADV WHERE dta_cde = a.dta_cde AND adv_itm_typ ='1024') hotal_billable_yn, "
				+ "(SELECT DECODE (NVL(INSTR (wm_concat(is_bil_cst),'Y'),0), 0, 'N', 'Y') FROM ITIN_DTA_REQ_ADV WHERE dta_cde = a.dta_cde AND adv_itm_typ ='1023') visa_billable_yn, "
				+ "A.IS_RTN_TVL isReturnTvl,A.PUR_TRAVEL, A.DTA_REQ_TYP, NVL ((SELECT EMP_NAM FROM itin_emp_mst "
				+ "WHERE emp_cde=A.DTA_LST_APR_EMP),'NO_EMP' ) AS DTA_LST_APR_EMP, "
				+ "DECODE(a.DTA_ACN_BY,1,'GTD',0,'-','','-', (SELECT emp_id FROM itin_emp_mst "
				+ "WHERE emp_cde = a.DTA_ACN_BY )) acn_emp_cde, CASE WHEN "
				+ "((CASE WHEN NVL (a.qte_for_apr, 'N') = 'Y' THEN 'Y' WHEN a.book_tik = 'N' THEN (CASE "
				+ "WHEN (NVL ((SELECT COUNT (dta_cde) FROM itin_dta_req_ind_dtl WHERE dta_cde  = a.dta_cde"
				+ " AND ind_itm_cde <> 1033), 0 ) = 0 ) THEN 'Y' ELSE (SELECT DECODE "
				+ "(INSTR (wm_concat (NVL (is_qte_sel, 'N' ) ), 'N' ), 0, 'Y', 'N' ) FROM itin_dta_req_ind_dtl "
				+ "WHERE dta_cde    = a.dta_cde AND ind_itm_cde <> 1033) END ) ELSE (SELECT DECODE "
				+ "(INSTR (wm_concat (NVL (is_qte_sel, 'N')), 'N' ), 0, 'Y', 'N' ) FROM itin_dta_req_ind_dtl "
				+ "WHERE dta_cde = a.dta_cde) END)) = 'N' AND a.dta_sts IN (1821) THEN 'GTD' ELSE "
				+ "DECODE (a.dta_acn_by, '', '',0,'-', (SELECT emp_nam FROM itin_emp_mst WHERE "
				+ "emp_cde = a.dta_acn_by) ) END acn_emp_nme FROM itin_dta_app_dtl a, "
				+ "itin_emp_mst e,ITIN_DTA_TRAVEL_CC_ALL c WHERE e.emp_cde = a.emp_cde AND c.dta_cde = a.dta_cde AND"
				+ " e.emp_cde = "
				+ userID
				+ " AND a.dta_sts  <> 1855 ORDER BY to_number(a.dta_cde) DESC";

		Object result = getJdbcTemplate().execute(
				new iTinJdbcConnectionCallback(new Object[] { userID, start,
						size, query }) {

					@Override
					public Object doInConnection(Connection connection)
							throws SQLException, DataAccessException {
						String userID = (String) this.getArguments()[0];
						int start = ((Integer) (this.getArguments()[1]))
								.intValue();
						int size = ((Integer) (this.getArguments()[2]))
								.intValue();
						String query = (String) this.getArguments()[3];

						List<RequestVO> travelList = new ArrayList<RequestVO>();
						PreparedStatement ps = null;
						ResultSet rs = null;
						RequestVO requestVo = null;

						ps = connection.prepareStatement(query,
								ResultSet.TYPE_SCROLL_SENSITIVE,
								ResultSet.CONCUR_READ_ONLY);
						rs = ps.executeQuery();

						
						int count = 0;
						int rowCount = 0;

						if (rs.last()) {
							rowCount = rs.getRow();
							rs.beforeFirst();
							if (rowCount >= start) {
								if (start == 1 || start == 0)
									rs.beforeFirst();
								else
									rs.absolute(start - 1);

							} else {
								return travelList;
							}

						}

						while (rs.next()) {

							if (size > count++) {
								System.out.println(count
										+ " : Hello rs Has Next element "
										+ rs.getRow());

								requestVo = new RequestVO();
								requestVo.setRequestID(rs.getString("DTA_ID"));
								requestVo.setRequestCode(rs
										.getString("DTA_CDE"));
								requestVo.setEmpCode(rs.getString("EMP_CDE"));
								requestVo.setUserID(rs.getString("EMP_ID"));
								requestVo.setEmpName(rs.getString("EMP_NAM"));

								requestVo
										.setTravelDetails(viewFlightTrainDetails(requestVo
												.getRequestCode()));
								requestVo
										.setCostCenters(viewCostCenterDetails(requestVo
												.getRequestCode()));
								requestVo
										.setCashDetails(viewCostDetails(requestVo
												.getRequestCode()));
								requestVo
										.setHotelDetails(viewHotelDetails(requestVo
												.getRequestCode()));
								requestVo
										.setCabDetails(viewCabDetails(requestVo
												.getRequestCode()));
								requestVo
										.setVisaDetails(viewVisaDetails(requestVo
												.getRequestCode()));

								
								requestVo
										.setBookingDetails(viewFlightTrainBkngDetails(requestVo
												.getRequestCode()));
							

								//requestVo.setCC(rs.getString("CC"));
//								requestVo.setBO(rs.getString("BO"));
//								requestVo.setBOtype(rs.getString("BO_TYPE"));
//								requestVo.setBOcommercial(rs
//										.getString("BO_COMMERCIAL"));
//								requestVo.setBOcode(rs.getString("BO_CODE"));

								requestVo.setPurpose(rs
										.getString("trvpurposename")); // combo
																		// box
																		// value
								requestVo.setPurposeDetail(rs
										.getString("PUR_TRAVEL")); // Text
																	// Box
																	// content
								requestVo.setApproverCode(rs
										.getString("acn_emp_cde")); // pending
																	// with
																	// Manager
								requestVo.setApproverName(rs
										.getString("acn_emp_nme"));
								requestVo.setRequestType(rs
										.getString("DTA_REQ_TYP"));
								requestVo.setRequestStatus(rs.getString("STS"));
								requestVo.setTotalCost(rs.getString("TOT_AMT"));
//								System.out.println("...." + count + "  "
//										+ rs.getString("BO_CODE") + "  "
//										+ rs.getString("BO_COMMERCIAL"));

								if ((rs.getString("ft_billable_yn")
										.equalsIgnoreCase("Y"))
										|| (rs.getString("hotal_billable_yn")
												.equalsIgnoreCase("Y"))
										|| (rs.getString("visa_billable_yn")
												.equalsIgnoreCase("Y"))) {
									requestVo.setBillableToCust("Y"); // if
																		// either
																		// of
																		// flight/hotel/visa
																		// is
																		// billable
								} else {
									requestVo.setBillableToCust("N");
								}

							
							//	if ( null != rs.getString("isReturnTvl") && rs.getString("isReturnTvl").equals("Y")) // return
									if ( rs.getString("isReturnTvl").equals("Y")) 										// travel
								{
									requestVo.setReturnTravel(true);
								} else {
									requestVo.setReturnTravel(false);
								}
								requestVo.setRequestStatusCode(rs
										.getString("STSCDE"));

								travelList.add(requestVo);

								System.out.println(" in travellist");

								System.out.println(travelList.get(0));
							} else {

								System.out.println(travelList.get(0));
								return travelList;

							}
						}

						return travelList;
					}

				});

		return (List<RequestVO>) result;
	}

	// //////////////////////////////////////////////////////////////////////////////////
	// Method to view the details of a particular request
	// input - dta code
	// output - entire travel details for a particular request
	public List<RequestVO> getDTA(String dtaCode) {
		System.out.println("GetDTA......");
		PreparedStatement ps = null;
		ResultSet rs = null;
		RequestVO requestVo = null;
		// List<RequestVO> travelList = new ArrayList<RequestVO>();

		String query = " SELECT A.IS_RCH_CST as rchgtoC,A.INV_NO as invno, A.TVL_BO as boCode,A.TVL_CC_CDE as ccCode,A.TVL_CAT as trvpurpose, A.CMT as genremarks,A.NO_DYS as NO_DYS , "
				+ " (SELECT MST_DSC FROM ITIN_COM_MST where MST_CDE=A.TVL_BO ) AS bo,"
				+
				// "(SELECT MST_KEY FROM ITIN_COM_MST WHERE MST_CDE=c.TVL_BO) AS bo_type, "
				// +
				// "(SELECT CV1_VAL FROM ITIN_COM_MST WHERE MST_CDE=c.TVL_BO) AS bo_commercial, "
				// +
				"  (SELECT nvl(ifin_cc_nm,mst_dsc) FROM itin_ifin_erp_cc ifin, itin_com_mst mst WHERE mst.mst_cde = a.tvl_cc_cde "
				+ " AND mst.mst_dsc = ifin.erp_cc_nam(+)) AS cc, "
				+ " (SELECT EMP_DPT FROM ITIN_EMP_MST WHERE EMP_CDE=A.EMP_CDE ) AS LOB, "
				+ " (SELECT MST_DSC FROM ITIN_COM_MST WHERE MST_CDE=A.TVL_CAT ) AS trvpurposename, "
				+ "(SELECT MST_DSC FROM ITIN_COM_MST WHERE MST_CDE=A.DTA_STS ) AS trvstatus,"
				+ "(SELECT EMP_BSE_STN FROM ITIN_EMP_MST WHERE EMP_CDE=A.EMP_CDE"
				+ ") AS BASE_STATION,"
				+ " (SELECT EMP_ID FROM ITIN_EMP_MST where EMP_CDE=A.EMP_CDE ) AS Dta_Emp_Id, "
				+ " (SELECT EMP_NAM FROM ITIN_EMP_MST where EMP_CDE=A.EMP_CDE ) AS Dta_Emp_Name, "
				+ " A.CMT, A.TVL_LOB,A.EMP_DSG,A.DTA_CDE,A.EMP_CDE,A.FST_LEG_TVL_DAT,A.IS_RTN_TVL isReturnTvl, "
				+ " A.FST_LEG_DST,A.DTA_APP_DAT,A.TYP_TRV, "
				+ " A.TVL_PRI,A.PUR_TRAVEL,A.DTA_STS,A.DTA_ACN_BY,A.DTA_REQ_TYP, "
				+ " NVL(A.BUD_AVL ,'Y') AS BUD_AVL, NVL ( (SELECT EMP_NAM FROM itin_emp_mst "
				+ " WHERE emp_cde=A.DTA_LST_APR_EMP ),'NO_EMP' ) AS DTA_LST_APR_EMP, NVL(A.Dta_id ,'NO_MODIFICATION') AS DTA_MOD_ID ,"
				+ "  NVL(A.CFO_APR_REQ ,'N') AS CFO_APR_REQ,A.ERP_PRJ_CDE as ERP_PRJ_CDE,A.QTE_FOR_APR as QTE_FOR_APR,A.IS_CLM_CLS IS_CLM_CLS, "
				+ " A.IS_CLM_RSD IS_CLM_RSD,(case when (a.is_clm_rsd <> 'Y' OR a.is_clm_rsd IS NULL) then (select sum(adv_in_usd) from itin_dta_req_adv where dta_cde=A.dta_cde and mst_sts='Y') "
				+ " else (select nvl(sum(amt_in_usd),0) from itin_dtc_clm where dta_cde=A.dta_cde and mst_sts='Y') "
				+ " +(select nvl(sum(exp_amt_usd),0) from itin_dtc_misc_dtl where dta_cde=A.dta_cde and mst_sts='Y') "
				+ "  end) TOT_AMT, (Select mst_dsc  from itin_com_mst where mst_cde = itin_dtc_bse_cur(a.dta_cde)) baseCurLbl ,itin_dtc_bse_cur(a.dta_cde) baseCurCde, (case when nvl(a.qte_for_apr,'N')='Y' then 'Y'   when a.book_tik='N' then "
				+ " (case when (nvl((select count(dta_cde) from itin_dta_req_ind_dtl where dta_cde = a.dta_cde and ind_itm_cde<>1033),0)=0) "
				+ " then 'Y'  else (SELECT decode(instr(wm_concat(nvl(is_qte_sel,'N')),'N'),0,'Y','N') "
				+ " FROM itin_dta_req_ind_dtl WHERE dta_cde = a.dta_cde and ind_itm_cde<>1033)  end)  else  "
				+ "(SELECT decode(instr(wm_concat(nvl(is_qte_sel,'N')),'N'),0,'Y','N')  FROM itin_dta_req_ind_dtl WHERE dta_cde = a.dta_cde)   end	 "
				+ " ) is_qte_sel, (SELECT DECODE (NVL(INSTR (wm_concat(is_bil_cst),'Y'),0), 0, 'N', 'Y') FROM ITIN_DTA_REQ_IND_DTL WHERE dta_cde = a.dta_cde AND ind_itm_cde='1033') ft_billable_yn,"
				+ " (SELECT DECODE (NVL(INSTR (wm_concat(is_bil_cst),'Y'),0), 0, 'N', 'Y') FROM ITIN_DTA_REQ_IND_DTL WHERE dta_cde = a.dta_cde AND ind_itm_cde='1036') hotal_billable_yn,"
				+ " (SELECT DECODE (NVL(INSTR (wm_concat(is_bil_cst),'Y'),0), 0, 'N', 'Y') FROM ITIN_DTA_REQ_IND_DTL WHERE dta_cde = a.dta_cde AND ind_itm_cde='1037') visa_billable_yn,"
				+ " Is_Fbk_Reqd(a.dta_cde) as fbk, (SELECT distinct approver  FROM itin_dta_approvers_all aprvl "
				+ "  WHERE  aprvl.dta_cde = a.dta_cde and aprvl.aprvl_order IN (SELECT MAX (NVL (aprvl_order, 0)) "
				+ " FROM itin_dta_approvers_all WHERE dta_cde = aprvl.dta_cde) ) as lst_apvr FROM ITIN_DTA_APP_DTL A   WHERE DTA_CDE= '"
				+ dtaCode + "'";

		Object result = getJdbcTemplate()
				.execute(
						new iTinJdbcConnectionCallback(new Object[] { dtaCode,
								query }) {

							@Override
							public Object doInConnection(Connection connection)
									throws SQLException, DataAccessException {

								String dtaCode = (String) this.getArguments()[0];
								String query = (String) this.getArguments()[1];

								List<RequestVO> travelList = new ArrayList<RequestVO>();
								PreparedStatement ps = null;
								ResultSet rs = null;
								RequestVO requestVo = null;

								ps = connection.prepareStatement(query);
								rs = ps.executeQuery();
								while (rs.next()) {

									requestVo = new RequestVO();
									requestVo.setRequestCode(rs
											.getString("DTA_CDE"));
									requestVo.setEmpCode(rs
											.getString("EMP_CDE"));
									requestVo.setUserID(rs
											.getString("DTA_EMP_ID"));
									requestVo.setEmpName(rs
											.getString("DTA_EMP_NAM"));
									requestVo
											.setTravelDetails(viewFlightTrainDetails(requestVo
													.getRequestCode()));
									requestVo
											.setCostCenters(viewCostCenterDetails(requestVo
													.getRequestCode()));
									requestVo
											.setCashDetails(viewCostDetails(requestVo
													.getRequestCode()));
									requestVo
											.setHotelDetails(viewHotelDetails(requestVo
													.getRequestCode()));
									requestVo
											.setCabDetails(viewCabDetails(requestVo
													.getRequestCode()));
									requestVo
											.setVisaDetails(viewVisaDetails(requestVo
													.getRequestCode()));
									requestVo
											.setBookingDetails(viewFlightTrainBkngDetails(requestVo
													.getRequestCode()));
									requestVo.setCC(rs.getString("CC"));
									requestVo.setBO(rs.getString("BO"));
									requestVo.setPurpose(rs
											.getString("trvpurposename")); // combo
																			// box
																			// value
									requestVo.setPurposeDetail(rs
											.getString("PUR_TRAVEL")); // Text
																		// Box
																		// content
									requestVo.setApproverCode(rs
											.getString("DTA_ACN_BY")); // pending
																		// with
																		// Manager
									requestVo.setRequestType(rs
											.getString("DTA_REQ_TYP"));
									requestVo.setRequestStatus(rs
											.getString("TRVSTATUS"));

									System.out.println(" in travel list"
											+ rs.getString("BO_COMMERCIAL"));

									if ((rs.getString("ft_billable_yn")
											.equalsIgnoreCase("Y"))
											|| (rs.getString("hotal_billable_yn")
													.equalsIgnoreCase("Y"))
											|| (rs.getString("visa_billable_yn")
													.equalsIgnoreCase("Y"))) {
										requestVo.setBillableToCust("Y");
									} else {
										requestVo.setBillableToCust("N");
									}
									if (rs.getString("isReturnTvl") != null && rs.getString("isReturnTvl").equals("Y")) // return
																					// travel
									{
										requestVo.setReturnTravel(true);
									} else {
										requestVo.setReturnTravel(false);
									}
									requestVo.setRequestStatusCode(rs
											.getString("DTA_STS"));

									/*
									 * requestVo.setTravelDetails(
									 * viewFlightTrainDetails(requestVo.
									 * getRequestCode()));
									 * requestVo.setCostCenters
									 * (viewCostCenterDetails
									 * (requestVo.getRequestCode())); requestVo
									 * .setCashDetails(viewCostDetails
									 * (requestVo.getRequestCode())); requestVo
									 * .setHotelDetails(viewHotelDetails
									 * (requestVo.getRequestCode())); requestVo
									 * .setCabDetails(viewCabDetails
									 * (requestVo.getRequestCode())); requestVo
									 * .setVisaDetails(viewVisaDetails
									 * (requestVo.getRequestCode()));
									 */
									travelList.add(requestVo);
								}

								return travelList;
							}
						});

		return (List<RequestVO>) result;

	}

	// //////////////////////////////////////////////////////////////////////////////////
	// for getting the flight train booking details
	// input - dta code
	// output - list containing flight/train bookingdetails.
	public List<BookingVO> viewFlightTrainBkngDetails(String dtaCde) {

		Object result = getJdbcTemplate().execute(
				new iTinJdbcConnectionCallback(new Object[] { dtaCde }) {

					@Override
					public Object doInConnection(Connection connection)
							throws SQLException, DataAccessException {
						List<BookingVO> travelVOList = new ArrayList<BookingVO>();
						BookingVO bookingVo = null;
						PreparedStatement ps = null;
						ResultSet rs = null;
						String dtaCde = (String) this.getArguments()[0];

						DateFormat formatter = new SimpleDateFormat(
								"yyyy-MM-dd");

						String query = "SELECT (SELECT MST_VAL FROM ITIN_COM_MST WHERE MST_CDE=BKG.TVL_MOD) TRAVLEMODE,"
								+ " (SELECT mst_dsc FROM itin_com_mst WHERE mst_cde = bkg.tvl_mod) travelmodeLbl, bkg.tvl_mod travelmodeCode,"
								+ " BKG.TRV_ORG_CDE ORIGIN,(SELECT CTY_NAM FROM ITIN_CTY_MST WHERE CTY_CDE=BKG.TRV_ORG_CDE) AS ORIGINNAME,"
								+ " BKG.TRV_ORG_STN EXACTORIGIN, BKG.TRV_DST_CDE DEST, (SELECT CTY_NAM FROM ITIN_CTY_MST WHERE CTY_CDE=BKG.TRV_DST_CDE) AS DESTINATIONNAME, "
								+ " BKG.TRV_DES_STN ECACTDEST,BKG.TRV_PNR_NUM PNRNO, BKG.TRV_REF_NUM TICKETNO,"
								+ "  ROUND(DETAIL.BKG_AMT_USD,2) as BKG_AMT_USD, (SELECT CMP_DSC FROM ITIN_CMP_MST WHERE CMP_CDE="
								+ " DETAIL.BKG_GVN_BY ) AS BOOKEDBY,"
								+ " BKG.FLT_TRN_NUM FLTTRNNO ,BKG.TRV_DEP_DTE DEPDATE,BKG.TRV_DEP_TIM DEPTIM,"
								+ " BKG.TRV_ARR_DTE ARRDATE, BKG.TRV_ARR_TIM arrTim, NVL (bkg.flt_crf_typ, ' ') airline,"
								+ " cshadv.adv_amt amount, cshadv.adv_cur currCode, nvl(cshadv.is_bil_cst,'N') billable, NVL (cshadv.bil_prt, 0) percentBillable, "
								+ " NVL (cshadv.max_lmt, 0) maximumLimit, cshadv.cst_cur billCurrCode, "
								+ " cshadv.dta_adv_id cashAdvId,(select mst_dsc from itin_com_mst where mst_cde=cshadv.adv_cur) currVal, (select mst_dsc from itin_com_mst "
								+ " where mst_cde=cshadv.cst_cur) billCurrVal,nvl(cshadv.is_csh_adv_req,'N') cashAdvReq FROM ITIN_DTA_BKG_TRV BKG,ITIN_DTA_BKG_DTL detail, itin_dta_req_adv cshadv "
								+ " WHERE BKG.DTA_CDE='"
								+ dtaCde
								+ "' AND DETAIL.BKG_ID=BKG.BKG_ID AND BKG.MST_STS='Y' AND cshadv.dta_cde = bkg.dta_cde AND cshadv.mst_sts = 'Y' AND "
								+ " cshadv.is_usr_ent = 'N' AND cshadv.adv_itm_typ = '1020' "
								+ " and cshadv.adv_amt =  detail.BKG_AMT  and PAR_DTA_ADV_ID in(0,cshadv.dta_adv_id)";

						ps = connection.prepareStatement(query);
						rs = ps.executeQuery();

						System.out.println("Booking VO");

						while (rs.next()) {

							System.out.println("Bookiing VO has some values");

							bookingVo = new BookingVO();
							bookingVo.setOrigin(rs.getString("originname"));

							bookingVo.setOrginCode(rs.getString("origin"));
							bookingVo.setDestination(rs
									.getString("destinationname"));
							bookingVo.setDestinationCode(rs.getString("dest"));
							bookingVo.setArrivalTime(rs.getString("arrtim"));
							bookingVo.setDepartureTime(rs.getString("deptim"));
							bookingVo.setAirline(rs.getString("airline"));
							bookingVo.setFlightNum(rs.getString("flttrnno"));
							bookingVo.setTicketNum(rs.getString("ticketno"));
							bookingVo.setPnr(rs.getString("pnrno"));
							bookingVo.setModeOfTravel(rs
									.getString("travelmodeLbl"));

							String depdate = rs.getString("depdate");
							String arrdate = rs.getString("arrdate");
							// String remark = rs.getString("remark");

							try {
								if (null != depdate) {
									bookingVo.setDepartureDate(formatter
											.format((Date) formatter
													.parse(depdate)));
								}
								if (null != arrdate) {
									bookingVo.setArrivalDate(formatter
											.format((Date) formatter
													.parse(arrdate)));
								}

							} catch (ParseException e) {
								throw new RuntimeException(e);
							}

							System.out.println(bookingVo.toString());

							travelVOList.add(bookingVo);
						}

						System.out.println(travelVOList.toArray());
						return travelVOList;

					}

				});

		return (List<BookingVO>) result;

	}

	// //////////////////////////////////////////////////////////////////////////////////
	// for getting the flight train request details
	// input - dta code
	// output - list containing flight/train details.
	public List<TravelVO> viewFlightTrainDetails(String dtaCde) {

		Object result = getJdbcTemplate().execute(
				new iTinJdbcConnectionCallback(new Object[] { dtaCde }) {

					@Override
					public Object doInConnection(Connection connection)
							throws SQLException, DataAccessException {

						String dtaCde = (String) this.getArguments()[0];

						List<TravelVO> travelVOList = new ArrayList<TravelVO>();
						TravelVO travelVO = null;
						PreparedStatement ps = null;
						ResultSet rs = null;

						DateFormat formatter = new SimpleDateFormat(
								"yyyy-MM-dd");

						String query = " SELECT  NVL((SELECT mst_dsc  FROM itin_com_mst  WHERE mst_cde=TRAINSERVICE.TRV_MDE  ),' ')   AS TRV_MDE,TRAINSERVICE.TRV_MDE AS MODEOFTRAVEL, "
								+ "TRAINSERVICE.DTA_CDE AS DTACODE , TRAINSERVICE.TRV_ORG_CDE AS ORGCODE ,"
								+ " TRAINSERVICE.TRV_LEG_REM     AS REMARK  , TRAINSERVICE.TRV_ORG_EXC_LOC AS EXTORG  ,"
								+ " TRAINSERVICE.TRV_REQ_ID      AS TRAINID , TRAINSERVICE.TRV_DST_CDE     AS DESTCODE,"
								+ " TRAINSERVICE.TRV_DST_EXC_LOC AS EXTDEST , TRAINSERVICE.TRV_DEP_DTE     AS DEPDATE ,"
								+ " TRAINSERVICE.TRV_ARR_DAT     AS ARRDATE , TRAINSERVICE.TRV_DEP_PRE_TIM AS PREFTIME, TRAINSERVICE.trv_arr_pre_tim AS ARRPREFTIME ,"
								+ " NVL( (SELECT CTY_NAM FROM ITIN_CTY_MST CITYMASTER WHERE CITYMASTER.CTY_CDE = TRAINSERVICE.TRV_ORG_CDE )"
								+ ",TRAINSERVICE.TRV_ORG_CDE ) AS ORIGIN , NVL( (SELECT CTY_NAM FROM ITIN_CTY_MST CITYMASTER"
								+ " WHERE CITYMASTER.CTY_CDE = TRAINSERVICE.TRV_DST_CDE ),TRAINSERVICE.TRV_DST_CDE ) AS DEPARTURE,"
								+ " NVL ((SELECT reg_cde FROM itin_cty_mst citymaster WHERE citymaster.cty_cde = trainservice.trv_org_cde), "
								+ " 0) AS originreg, NVL ((SELECT reg_cde FROM itin_cty_mst citymaster WHERE citymaster.cty_cde = "
								+ " trainservice.trv_dst_cde),0 ) AS destinationreg, NVL(servicedtl.is_bil_cst,'N') AS isbillable"
								+ " FROM ITIN_DTA_REQ_TRV TRAINSERVICE,ITIN_DTA_REQ_IND_DTL servicedtl WHERE TRAINSERVICE.DTA_CDE ='"
								+ dtaCde
								+ "'"
								+ " AND TRAINSERVICE.DTA_IND_REQ_NUM=servicedtl.DTA_IND_REQ_NUM order by TRAINSERVICE.TRV_REQ_ID";

						ps = connection.prepareStatement(query);
						rs = ps.executeQuery();

						while (rs.next()) {

							travelVO = new TravelVO();
							travelVO.setOrigin(rs.getString("origin"));
							travelVO.setOrginCode(rs.getString("orgcode"));
							travelVO.setDestination(rs.getString("departure"));
							travelVO.setDestinationCode(rs
									.getString("destcode"));
							travelVO.setArrivalTime(rs.getString("arrpreftime"));
							travelVO.setDepartureTime(rs.getString("preftime"));
							travelVO.setIsBillable(rs.getString("isbillable"));
							travelVO.setDepartureTime(rs.getString("PREFTIME"));

							String depdate = rs.getString("depdate");
							String arrdate = rs.getString("arrdate");
							String remark = rs.getString("remark");
							try {
								if (null != depdate) {
									travelVO.setDepartureDate(formatter
											.format((Date) formatter
													.parse(depdate)));
								}
								if (null != arrdate) {
									travelVO.setArrivalDate(formatter
											.format((Date) formatter
													.parse(arrdate)));
								}
							} catch (ParseException e) {
								throw new RuntimeException(e);
							}
							if (null != remark && remark.length() > 0)
								travelVO.setRemark(remark);
							else
								travelVO.setRemark("");
							travelVOList.add(travelVO);
						}

						System.out.println(travelVOList.toArray());
						return travelVOList;

					}

				});
		return (List<TravelVO>) result;
	}

	// //////////////////////////////////////////////////////////////////////////////////
	// for getting the cost center details in case of a multi cc travel
	// input - dta code
	// output - list containing flight/train details.
	public List<CostCenterVO> viewCostCenterDetails(String dtaNo) {

		Object result = getJdbcTemplate().execute(
				new iTinJdbcConnectionCallback(new Object[] { dtaNo }) {

					@Override
					public Object doInConnection(Connection connection)
							throws SQLException, DataAccessException {
						String dtaNo = (String) this.getArguments()[0];

						System.out.println(" in cost center details");
						CostCenterVO costCenterVO = null;
						List<CostCenterVO> costVos = new ArrayList<CostCenterVO>();
						PreparedStatement ps = null;
						ResultSet rs = null;

						String query = "SELECT (SELECT mst_dsc FROM ITIN_COM_MST WHERE mst_cde = cc.cc_cde AND mst_key = 135) cc, cc.cc_cde ccCode,cc.tvl_bo boCode, "
									   + "(SELECT MST_KEY FROM ITIN_COM_MST WHERE MST_CDE=cc.TVL_BO) AS bo_type, "
									   + "(SELECT CV1_VAL FROM ITIN_COM_MST WHERE MST_CDE=cc.TVL_BO) AS bo_commercial, "
									   + " (SELECT mst_dsc FROM ITIN_COM_MST WHERE mst_cde = cc.tvl_bo) bo,cc.erp_prj_cde erpprojectcode,CST_ALCN_PRT costAllocation "
									   + " FROM ITIN_DTA_TRAVEL_CC_ALL cc WHERE cc.dta_cde ='"
									   + dtaNo + "'";
						

						ps = connection.prepareStatement(query);
						rs = ps.executeQuery();
						while (rs.next()) {
							costCenterVO = new CostCenterVO();
							costCenterVO.setCostCenter(rs.getString("CC"));
							costCenterVO.setCostCenterCode(rs
									.getString("CCCODE"));
							costCenterVO.setBoProject(rs.getString("BO"));
							costCenterVO.setBoProjectCode(rs
									.getString("BOCODE"));
							costCenterVO.setErpProjCde(rs
									.getString("ERPPROJECTCODE"));
								
							costCenterVO.setBOtype(rs
									.getString("bo_type"));
							
							costCenterVO.setBOcommercial(rs
									.getString("bo_commercial"));
							System.out.println("bocommercial......"+ rs.getString("bo_commercial"));
							costCenterVO.setCostAllocation(Integer.parseInt(rs
									.getString("COSTALLOCATION")));
							costVos.add(costCenterVO);
						}

						return costVos;
					}

				});

		return (List<CostCenterVO>) result;
	}

	// //////////////////////////////////////////////////////////////////////////////////
	// for getting the expense request details
	// input - dta code
	// output - list containing flight/train details.
	public List<CashVO> viewCostDetails(String dtaNo) {

		Object result = getJdbcTemplate().execute(
				new iTinJdbcConnectionCallback(new Object[] { dtaNo }) {

					@Override
					public Object doInConnection(Connection connection)
							throws SQLException, DataAccessException {
						String dtaNo = (String) this.getArguments()[0];

						System.out.println(" in cash details");
						CashVO cashVo = null;
						List<CashVO> cashVos = new ArrayList<CashVO>();
						PreparedStatement ps = null;
						ResultSet rs = null;

						String query = "Select R.DTA_ADV_ID advanceId, nvl(R.ITM_QTY,'') numDays, nvl(R.ADV_RTE,'') rate, nvl(R.ADV_AMT,'') amount,"
								+ "(SELECT com.MST_VAL FROM ITIN_COM_MST com  WHERE R.ADV_CUR = com.MST_CDE) as currency,"
								+ "(SELECT com.MST_VAL FROM ITIN_COM_MST com  WHERE R.ADV_ITM_TYP = com.MST_CDE) as head,"
								+ " nvl(R.INI_REM,' ') remarks, R.ADV_ITM_TYP headCode , R.ADV_CUR currencyCode ,  R.PDM_ID perdiemId, "
								+ " DECODE (r.is_csh_adv_req, 'Y', 'Yes', 'No') cashadvreq, r.adv_in_usd amtInUsd, r.crt_usr crtdby "
								+ " , TO_CHAR (r.crt_dat, 'dd-mm-yyyy hh24:mm:ss') crtdon, DECODE (r.is_bil_cst, 'Y', 'Yes','A','Acc in Deal Pnl','No') isbillable,NVL (r.bil_prt, 0) billamt,NVL (r.max_lmt, ' ') maxlmt, NVL (r.cst_cur, 0) currcode, "
								+ " nvl((SELECT mst_val FROM itin_com_mst WHERE mst_key = 105 AND mst_cde = trim(r.cst_cur)),' ') curr "
								+ " ,r.is_usr_ent userent,r.frm_par_dta frompardta ,(SELECT dta_sts FROM itin_dta_app_dtl WHERE dta_cde = '"
								+ dtaNo
								+ "') pardtastatus, "
								+ "(SELECT COUNT (dta_adv_id) FROM itin_dta_pymnt_dtl WHERE dta_adv_id = r.dta_adv_id AND mst_sts = 'Y') pymntcnt "
								+ ",(SELECT COUNT (CSH_ADV_ID)FROM ITIN_DTC_CLM WHERE CSH_ADV_ID = r.dta_adv_id) clmcnt "
								+ "FROM ITIN_DTA_REQ_ADV R  WHERE R.DTA_CDE='"
								+ dtaNo
								+ "' AND MST_STS='Y' /*AND r.adv_amt <> 0 */ ";
						ps = connection.prepareStatement(query,
								ResultSet.TYPE_SCROLL_SENSITIVE,
								ResultSet.CONCUR_READ_ONLY);
						rs = ps.executeQuery();

						while (rs.next()) {
							cashVo = new CashVO();

							System.out.println("Amount In USD"
									+ rs.getString("amtInUsd"));

							cashVo.setHeadName(rs.getString("head"));
							cashVo.setAmount(rs.getString("amount"));
							cashVo.setCurrency(rs.getString("currency"));
							if (null != rs.getString("numDays")
									&& rs.getString("numDays").length() > 0)
								cashVo.setNoNights(rs.getString("numDays"));
							else
								cashVo.setNoNights("");
							if (null != rs.getString("rate")
									&& rs.getString("rate").length() > 0)
								cashVo.setRate(rs.getString("rate"));
							else
								cashVo.setRate("");

							cashVo.setHeadCode(rs.getString("headCode"));
							cashVo.setCurrencyCode(rs.getString("currencyCode"));

							cashVo.setAmountInUSD(rs.getString("amtInUsd"));
							cashVo.setBillable(rs.getString("isbillable"));

							cashVos.add(cashVo);

						}

						System.out.println("cashVOS........." + cashVos.size());

						return cashVos;

					}
				});

		return (List<CashVO>) result;

	}

	// //////////////////////////////////////////////////////////////////////////////////
	// for getting the hotel request details
	// input - dta code
	// output - list containing flight/train details.
	public List<HotelVO> viewHotelDetails(String dtaNo) {

		Object result = getJdbcTemplate().execute(
				new iTinJdbcConnectionCallback(new Object[] { dtaNo }) {

					@Override
					public Object doInConnection(Connection connection)
							throws SQLException, DataAccessException {
						String dtaNo = (String) this.getArguments()[0];

						List<HotelVO> travelList = new ArrayList<HotelVO>();
						HotelVO hotelVo = null;
						PreparedStatement ps = null;
						ResultSet rs = null;
						DateFormat formatter = new SimpleDateFormat(
								"yyyy-MM-dd");

						String query = "SELECT distinct DTA_REQ_TYP as requestType FROM ITIN_DTA_APP_DTL WHERE  DTA_CDE='"
								+ dtaNo + "'";
						ps = connection.prepareStatement(query);
						rs = ps.executeQuery();
						String reqTyp = "";
						while (rs.next()) {
							reqTyp = rs.getString("requestType");
						}
						if (!("s").equalsIgnoreCase(reqTyp)) {
							query = " SELECT hotelservice.DTA_CDE    AS dtacode ,"
									+ "hotelservice.HTL_LEG_REM      AS remark        ,"
									+ "hotelservice.HTL_CTY_EXC_LOC  AS exatLocation  ,"
									+ "hotelservice.HTL_CTY_CDE      AS org_code      ,"
									+ "hotelservice.HTL_CTY_EXC_LOC  AS exactLoc      ,"
									+ "hotelservice.HLT_REQ_ID       AS hotelid       ,"
									+ "hotelservice.CHK_IN_DTE       AS checkinDate   ,"
									+ "hotelservice.CHK_OUT_DTE      AS checkOutDate  ,"
									+ "hotelservice.HTL_CKIN_PRE_TIM AS prefchkintime ,"
									+ "hotelservice.HTL_CKOT_PRE_TIM AS prefchkouttime,"
									+ "NVL ( (SELECT CTY_NAM FROM itin_cty_mst WHERE cty_cde=hotelservice.HTL_CTY_CDE"
									+ "), hotelservice.HTL_CTY_CDE ) AS origin, NVL(servicedtl.is_bil_cst,'N') AS isbillable "
									+ "FROM ITIN_DTA_REQ_HTL hotelservice,ITIN_DTA_REQ_IND_DTL servicedtl "
									+ "WHERE hotelservice.dta_cde ='"
									+ dtaNo
									+ "' AND hotelservice.DTA_IND_REQ_NUM=servicedtl.DTA_IND_REQ_NUM";
						} else {
							query = " SELECT hotelservice.DTA_CDE as dtacode, hotelservice.HTL_LEG_REM as remark, "
									+ "hotelservice.HTL_CTY_EXC_LOC as exatLocation, "
									+ " hotelservice.HTL_CTY_CDE AS org_code,hotelservice.HTL_CTY_EXC_LOC as "
									+ " exactLoc,hotelservice.HLT_REQ_ID as hotelid,hotelservice.CHK_IN_DTE AS "
									+ " checkinDate,hotelservice.CHK_OUT_DTE AS checkOutDate,hotelservice.HTL_CKIN_PRE_TIM AS "
									+ " prefchkintime,hotelservice.HTL_CKOT_PRE_TIM AS prefchkouttime, "
									+ "NVL ( (SELECT CTY_NAM FROM itin_cty_mst WHERE cty_cde=hotelservice.HTL_CTY_CDE"
									+ "), hotelservice.HTL_CTY_CDE ) AS origin, NVL(servicedtl.is_bil_cst,'N') AS isbillable "
									+ "FROM ITIN_DTA_REQ_HTL hotelservice,ITIN_DTA_REQ_IND_DTL servicedtl "
									+ "WHERE hotelservice.dta_cde ='"
									+ dtaNo
									+ "' AND hotelservice.DTA_IND_REQ_NUM=servicedtl.DTA_IND_REQ_NUM";
						}
						ps = connection.prepareStatement(query);
						rs = ps.executeQuery();
						while (rs.next()) {
							hotelVo = new HotelVO();
							hotelVo.setCityName(rs.getString("origin"));
							hotelVo.setCityCode(rs.getString("org_code"));
							hotelVo.setCheckInTime(rs
									.getString("prefchkintime"));
							hotelVo.setCheckOutTime(rs
									.getString("prefchkouttime"));

							try {
								if (null != rs.getString("checkinDate")) {
									hotelVo.setCheckInDate(formatter.format((java.util.Date) formatter
											.parse(rs.getString("checkinDate"))));
								}
								if (null != rs.getString("checkOutDate")) {
									hotelVo.setCheckOutDate(formatter.format((java.util.Date) formatter
											.parse(rs.getString("checkOutDate"))));
								}
							} catch (ParseException e) {
								throw new RuntimeException(e);
							}

							if (null != rs.getString("remark")
									&& rs.getString("remark").length() > 0)
								hotelVo.setRemarks(rs.getString("remark"));
							else
								hotelVo.setRemarks("");
							if (rs.getString("exatLocation") != null)
								hotelVo.setExactLocation(rs
										.getString("exatLocation"));
							else
								hotelVo.setExactLocation("");
							travelList.add(hotelVo);

						}
						return travelList;
					}

				});
		return (List<HotelVO>) result;

	}

	// //////////////////////////////////////////////////////////////////////////////////
	// for getting the cab request details
	// input - dta code
	// output - list containing flight/train details.
	public List<CabVO> viewCabDetails(String dtaNo) {
		Object result = getJdbcTemplate().execute(
				new iTinJdbcConnectionCallback(new Object[] { dtaNo }) {

					@Override
					public Object doInConnection(Connection connection)
							throws SQLException, DataAccessException {
						String dtaNo = (String) this.getArguments()[0];
						List<CabVO> travelVOList = new ArrayList<CabVO>();
						CabVO cabVo = null;
						PreparedStatement ps = null;
						ResultSet rs = null;

						String query = " SELECT cabservice.DTA_CDE as dtacode,cabservice.CAB_ORG_CDE AS orgcode,"
								+ " cabservice.CAB_ORG_EXC_LOC as exactOrgin,cabservice.CAB_TSP as sendToTsp,"
								+ " cabservice.CAB_DST_EXC_LOC as exactDes,cabservice.CAB_DEP_PRE_TIM depTim, "
								+ " cabservice.CAB_RET as retainVehicle,cabservice.CAB_RET_DUR as retainDur,cabservice.CAB_LEG_REM as remark, cabservice.CON_NUM "
								+ " as contNum, cabservice.CAB_REQ_ID as cabid,cabservice.CAB_DEP_DTE AS depDate,"
								+ " cabservice.CAB_DEP_PRE_TIM as depPreferedTime,cabservice.CAB_DST_CDE as desCode,"
								+ " cabservice.CAB_ARR_DAT AS arrDate,cabservice.CAB_ARR_PRE_TIM AS arrPreferedTime,"
								+ " NVL( (SELECT citymaster.CTY_NAM FROM ITIN_CTY_MST citymaster WHERE "
								+ " citymaster.CTY_CDE = cabservice.CAB_ORG_CDE ),cabservice.CAB_ORG_CDE ) AS ORIGIN,"
								+ " NVL( (SELECT citymaster.CTY_NAM FROM ITIN_CTY_MST citymaster WHERE "
								+ " citymaster.CTY_CDE = cabservice.CAB_DST_CDE ),cabservice.CAB_DST_CDE ) AS departure,"
								+ " (SELECT decode(count(dta_cde), 0,'N','Y') FROM itin_dta_req_cab c WHERE UPPER (c.cab_org_cde)"
								+ " NOT IN('TRV','COK','BLR','TRIVANDRUM','COCHIN','BANGALORE') AND"
								+ " c.dta_cde = cabservice.dta_cde) is_int_cab"
								+ " FROM ITIN_DTA_REQ_CAB cabservice  WHERE cabservice.dta_cde ='"
								+ dtaNo
								+ "'"
								+ " order by cabservice.cab_dep_dte, cabservice.cab_dep_pre_tim";

						ps = connection.prepareStatement(query);
						rs = ps.executeQuery();

						while (rs.next()) {
							cabVo = new CabVO();
							cabVo.setCityName(rs.getString("origin"));
							cabVo.setCityCode(rs.getString("orgcode"));
							cabVo.setPickUpPoint(rs.getString("exactOrgin"));
							cabVo.setDropPoint(rs.getString("exactDes"));
							cabVo.setPickUpTime(rs.getString("deppreferedtime"));
							cabVo.setRetVehicle(rs.getString("retainVehicle"));
							cabVo.setRetDuration(rs.getString("retainDur"));
							cabVo.setContactNo(rs.getString("contNum"));

							try {
								if (null != rs.getString("depdate")) {
									cabVo.setPickUpDate(DATE_ONLY_FORMATTER
											.format((java.util.Date) DATE_AND_TIME_FORMATTER.parse(rs
													.getString("depdate"))));
								}
							} catch (ParseException e) {
								throw new RuntimeException(e);
							}

							if (null != rs.getString("remark")
									&& rs.getString("remark").length() > 0)
								cabVo.setRemark(rs.getString("remark"));
							else
								cabVo.setRemark("");
							cabVo.setPickUpTime(rs.getString("depTim"));
							travelVOList.add(cabVo);
						}

						return travelVOList;
					}

				});
		return (List<CabVO>) result;
	}

	// //////////////////////////////////////////////////////////////////////////////////
	// for getting the flight train request details
	// input - dta code
	// output - list containing flight/train details.
	public List<VisaVO> viewVisaDetails(String dtaNo) {
		Object result = getJdbcTemplate().execute(
				new iTinJdbcConnectionCallback(new Object[] { dtaNo }) {

					@Override
					public Object doInConnection(Connection connection)
							throws SQLException, DataAccessException {
						String dtaNo = (String) this.getArguments()[0];
						List<VisaVO> visaBkngDetails = new ArrayList<VisaVO>();
						VisaVO visaVo = null;
						PreparedStatement ps = null;
						ResultSet rs = null;

						String query = "  SELECT INITCAP((SELECT MST_DSC FROM ITIN_COM_MST WHERE MST_KEY=129 AND MST_CDE=BKG.VIS_CNY_CDE)) COUNTRY ,"
								+ "BKG.VIS_NME VISANAME, BKG.VIS_FDT FROMDATE, ROUND(DETAIL.BKG_AMT_USD,2) as BKG_AMT_USD,(SELECT CMP_DSC FROM ITIN_CMP_MST WHERE CMP_CDE="
								+ "DETAIL.BKG_GVN_BY ) AS BOOKEDBY, BKG.VIS_TDT TODATE"
								+ " FROM ITIN_DTA_BKG_VIS BKG , ITIN_DTA_BKG_DTL DETAIL WHERE BKG.DTA_CDE='"
								+ dtaNo
								+ "' AND  DETAIL.BKG_ID=BKG.BKG_ID AND BKG.MST_STS='Y'";
						ps = connection.prepareStatement(query);
						rs = ps.executeQuery();
						int i = 0;
						while (rs.next()) {
							visaVo = new VisaVO();
							visaVo.setBookingAmount(rs.getString("BKG_AMT_USD"));
							visaVo.setBookingCompany(rs.getString("BOOKEDBY"));
							visaVo.setCountryName(rs.getString("country"));
							visaVo.setVisaName(rs.getString("visaName"));
							try {
								if (null != (rs.getString("fromDate"))) {
									visaVo.setValidFrom(DATE_ONLY_FORMATTER
											.format((java.util.Date) DATE_AND_TIME_FORMATTER.parse(rs
													.getString("fromDate"))));
								}
								if (null != (rs.getString("toDate"))) {
									visaVo.setValidTo(DATE_ONLY_FORMATTER
											.format((java.util.Date) DATE_AND_TIME_FORMATTER.parse(rs
													.getString("toDate"))));
								}
							} catch (ParseException e) {
								e.printStackTrace();
							}
							visaBkngDetails.add(visaVo);
						}

						return visaBkngDetails;
					}

				});

		return (List<VisaVO>) result;
	}

	// //////////////////////////////////////////////////////////////////////////////////

	public void updateMobileNumber(String contactNumber, String employeeCode) {
		getJdbcTemplate().execute(
				new iTinJdbcConnectionCallback(new Object[] { contactNumber,
						employeeCode }) {

					@Override
					public Object doInConnection(Connection connection)
							throws SQLException, DataAccessException {
						String contactNumber = (String) this.getArguments()[0];
						String employeeCode = (String) this.getArguments()[1];

						String query = "UPDATE ITIN_EMP_MST SET MOB_NUM ='"
								+ contactNumber + "' WHERE EMP_CDE='"
								+ employeeCode + "' ";
						PreparedStatement ps = connection
								.prepareStatement(query);
						ps.executeQuery();

						return null;
					}

				});

	}

	// //////////////////////////////////////////////////////////////////////////////////
	public String callUpdateStatusProc(String procString, String cmtstr) {
		Object result = getJdbcTemplate().execute(
				new iTinJdbcConnectionCallback(new Object[] { procString,
						cmtstr }) {

					@Override
					public Object doInConnection(Connection connection)
							throws SQLException, DataAccessException {
						String procString = (String) this.getArguments()[0];
						String cmtstr = (String) this.getArguments()[1];

						CallableStatement st = connection
								.prepareCall("{call UPDATE_STATUS (?,?,?)}");
						st.setString(1, procString);
						st.setString(2, cmtstr);
						st.registerOutParameter(3, Types.VARCHAR);
						st.execute();
						return st.getString(3);
					}

				});
		return (String) result;
	}

	// //////////////////////////////////////////////////////////////////////////////////
	public String fetchItinFlowStatus(String workflowInputString) {
		Object result = getJdbcTemplate().execute(
				new iTinJdbcConnectionCallback(
						new Object[] { workflowInputString }) {

					@Override
					public Object doInConnection(Connection connection)
							throws SQLException, DataAccessException {
						String workflowInputString = (String) this
								.getArguments()[0];

						CallableStatement st1 = connection
								.prepareCall("{call get_itinflow (?,?,?)}");
						st1.setString(1, workflowInputString);
						st1.setInt(2, 0);
						st1.registerOutParameter(3, Types.VARCHAR);
						st1.execute();
						return st1.getString(3);
					}

				});
		return (String) result;
	}

	// //////////////////////////////////////////////////////////////////////////////////
	public String fetchAppCode(String dtaNumber, String requestCode,
			int employeeId, String requestType) {
		Object result = getJdbcTemplate().execute(
				new iTinJdbcConnectionCallback(new Object[] { dtaNumber,
						requestCode, employeeId, requestType }) {

					@Override
					public Object doInConnection(Connection connection)
							throws SQLException, DataAccessException {
						String dtaNumber = (String) this.getArguments()[0];
						String requestCode = (String) this.getArguments()[1];
						int employeeId = (Integer) this.getArguments()[2];
						String requestType = (String) this.getArguments()[3];

						CallableStatement st = connection
								.prepareCall("{call get_app_cde (?,?,?,?,?)}");
						st.setString(1, dtaNumber);
						st.setString(2, requestCode);
						st.setInt(3, employeeId);
						st.setString(4, requestType);
						st.registerOutParameter(5, Types.VARCHAR);
						st.execute();
						return st.getString(5);
					}

				});
		return (String) result;
	}

	// //////////////////////////////////////////////////////////////////////////////////
	public int generateNextDtaNumber() {

		Object result = getJdbcTemplate().execute(
				new iTinJdbcConnectionCallback(new Object[] {}) {

					@Override
					public Object doInConnection(Connection connection)
							throws SQLException, DataAccessException {
						int dtaNum = 100;
						int nextDtaNo = 100;

						String query = "select itin_dta_app_dtl_seq.nextval from dual";
						PreparedStatement ps = connection
								.prepareStatement(query);
						ResultSet rs = ps.executeQuery();

						// what is the need for this loop?
						while (rs.next()) {
							dtaNum = rs.getInt(1);
						}
						nextDtaNo = dtaNum;

						return nextDtaNo;
					}

				});
		return (Integer) result;

	}

	// //////////////////////////////////////////////////////////////////////////////////
	// SMS triggered to employees when a new Cab request is created.
	// Added by Savin Koshy (A-5152) on July 16,2014
	public void sendCreateRequestSMS(String dtaCode) {

		getJdbcTemplate().execute(
				new iTinJdbcConnectionCallback(new Object[] { dtaCode }) {

					@Override
					public Object doInConnection(Connection connection)
							throws SQLException, DataAccessException {

						String dtaCode = (String) this.getArguments()[0];

						HttpURLConnection urlconnection = null;
						BufferedReader in = null;
						PreparedStatement prStmt = null;
						ResultSet rs = null;
						String querry = "";
						String user_name = "";
						String passwd = "";
						String emp_name = "";
						String mobNumber = "";
						String requestNumber = "";

						querry = " SELECT a.dta_id, e.emp_id, e.emp_nam, trim(e.MOB_NUM)  FROM itin_dta_app_dtl a, "
								+ " itin_emp_mst e  WHERE a.emp_cde = e.emp_cde AND a.dta_cde = '"
								+ dtaCode + "'";

						System.out.println("querry == " + querry);
						prStmt = connection.prepareStatement(querry);
						rs = prStmt.executeQuery();
						while (rs.next()) {
							requestNumber = rs.getString(1);
							emp_name = rs.getString(3);
							mobNumber = rs.getString(4);
						}

						user_name = "Ibs";
						passwd = "Admin@12345";

						try {
							String numbers = mobNumber;
							String message = "Dear "
									+ emp_name
									+ ","
									+ "%0AYour iTin cab request"
									+ " "
									+ requestNumber
									+ " is being processed. For assist, call 08943066557(TRV)/ 09946038083(COK)/ 09731000533(BLR)/ 08589055105(Other Cities)."
									+ "%0ATeam Admin, IBS";
							String msg = message.replace(" ", "%20");

							// SMS interface (connecting to 3rd party SMS tool)
							// by Savin Mammen
							// Koshy (A-5152)
							String urlString = "http://alerts.sinfini.com/api/web2sms.php?username="
									+ user_name
									+ "&password="
									+ passwd
									+ "&sender=IBSADM&to="
									+ numbers
									+ "&message=" + msg + "";
							System.out.println("URL --> " + urlString);

							URL url = new URL(urlString);

							// Enable the below code in test environment
							/*
							 * String proxy = "webproxy.ibsplc.com"; String
							 * port1 = "80"; Properties systemProperties =
							 * System.getProperties();
							 * systemProperties.setProperty
							 * ("http.proxyHost",proxy);
							 * systemProperties.setProperty
							 * ("http.proxyPort",port1);
							 * System.setProperty("http.proxyUser", "A-5152");
							 * System.setProperty("http.proxyPassword",
							 * "777@winner");
							 */

							urlconnection = (HttpURLConnection) url
									.openConnection();
							urlconnection
									.addRequestProperty("User-Agent",
											"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");

							urlconnection.connect();
							in = new BufferedReader(new InputStreamReader(url
									.openStream()));

						} catch (MalformedURLException e) {
							LOGGER.error(e.getMessage(), e);
						} catch (IOException e) {
							LOGGER.error(e.getMessage(), e);
						} catch (Exception e) {
							throw new RuntimeException(e.getMessage(), e);
						} finally {
							try {
								in.close();
							} catch (Exception e) {
								LOGGER.error(e.getMessage(), e);
							}
							urlconnection.disconnect();
						}

						return null;
					}

				});

	}

	// //////////////////////////////////////////////////////////////////////////////////
	// //////////////////////////////////////////////////////////////////////////////////

	// for getting the highest created date
	public String getTokenDate() {

		Object result = getJdbcTemplate().execute(
				new iTinJdbcConnectionCallback(new Object[] {}) {

					@Override
					public Object doInConnection(Connection connection)
							throws SQLException, DataAccessException {
						Format formatter = new SimpleDateFormat(
								"dd-MMM-yyyy  HH:mm");
						String tknDate = null;
						PreparedStatement ps = null;
						ResultSet res = null;

						String query = "SELECT MAX(to_char(CRT_DAT,'dd-MON-yyyy HH:mm:ss AM')) AS CRT_DAT FROM ITIN_COM_MST ";
						try {
							ps = connection.prepareStatement(query);
							res = ps.executeQuery();
							while (res.next()) {
								tknDate = (res.getString("CRT_DAT"));
								System.out.println(" tknDate  "
										+ res.getString("CRT_DAT"));
							}
						} catch (SQLException e) {
							e.printStackTrace();
						}
						return tknDate;

					}

				});
		return (String) result;

	}

	// //////////////////////////////////////////////////////////////////////////////////

	// for getting the cities list
	public List<PlaceVO> getCities(String tokenDate) {

		Object result = getJdbcTemplate().execute(
				new iTinJdbcConnectionCallback(new Object[] { tokenDate }) {

					@Override
					public Object doInConnection(Connection connection)
							throws SQLException, DataAccessException {

						String tokenDate = (String) this.getArguments()[0];
						System.out.println("tokenDate cities" + tokenDate);
						List<PlaceVO> cityList = new ArrayList<PlaceVO>();
						String cityQuery = "SELECT CTY_CDE, CTY_NAM, APT_NME, CNY_CDE,CRT_DAT,LST_UPD_DAT FROM ITIN_CTY_MST WHERE CRT_DAT > to_date('"
								+ tokenDate
								+ "','DD-MON-YYYY HH:MI:SS AM') ORDER BY CTY_NAM";
						PreparedStatement ps = null;
						ResultSet res = null;

						ps = connection.prepareStatement(cityQuery);
						res = ps.executeQuery();
						while (res.next()) {

							PlaceVO place = new PlaceVO();
							place.setPlaceCode(res.getString("CTY_CDE"));
							place.setPlaceName(res.getString("CTY_NAM"));
							cityList.add(place);

						}
						return cityList;

					}

				});
		return (List<PlaceVO>) result;

	}

	// //////////////////////////////////////////////////////////////////////////////////

	// for getting the currency details for Expense Details
	public List<CurrencyVO> getCurrency(String tokenDate) {

		Object result = getJdbcTemplate().execute(
				new iTinJdbcConnectionCallback(new Object[] { tokenDate }) {

					@Override
					public Object doInConnection(Connection connection)
							throws SQLException, DataAccessException {
						String tokenDate = (String) this.getArguments()[0];

						List<CurrencyVO> currencyList = new ArrayList<CurrencyVO>();
						String purposeQuery = "Select c.MST_KEY,c.MST_CDE,c.MST_VAL,c.MST_DSC,c.CRT_DAT,c.LST_UPD_DAT from ITIN_COM_MST c , ITIN_COM_KEY master where c.MST_KEY = master.MAS_KEY and master.MAS_KEY ='105'and c.MST_STS='Y' AND c.CRT_DAT > to_date('"
								+ tokenDate + "','DD-MON-YYYY HH:MI:SS AM') ";
						PreparedStatement ps = null;
						ResultSet res = null;
						try {
							ps = connection.prepareStatement(purposeQuery);
							res = ps.executeQuery();
							while (res.next()) {

								CurrencyVO currency = new CurrencyVO();
								currency.setMstKey(res.getString("MST_KEY"));
								currency.setMstCode(res.getString("MST_CDE"));
								currency.setMstValue(res.getString("MST_VAL"));
								currency.setMstDsc(res.getString("MST_DSC"));
								currencyList.add(currency);

							}
						} catch (SQLException sqlException) {
							sqlException.printStackTrace();
						}

						return currencyList;
					}

				});

		return (List<CurrencyVO>) result;

	}

	// //////////////////////////////////////////////////////////////////////////////////

	// for getting the head details for Expense Details
	public List<HeadVO> getHeads(String tokenDate) {
		Object result = getJdbcTemplate().execute(
				new iTinJdbcConnectionCallback(new Object[] { tokenDate }) {

					@Override
					public Object doInConnection(Connection connection)
							throws SQLException, DataAccessException {
						String tokenDate = (String) this.getArguments()[0];

						List<HeadVO> headList = new ArrayList<HeadVO>();
						String purposeQuery = " Select c.MST_KEY,c.MST_CDE,c.MST_VAL,c.MST_DSC,c.CRT_DAT,c.LST_UPD_DAT "
								+ " FROM ITIN_COM_MST c, ITIN_COM_KEY master where c.MST_KEY = master.MAS_KEY and c.CRT_DAT > to_date('"
								+ tokenDate
								+ "','DD-MON-YYYY HH:MI:SS AM') "
								+ " AND master.KEY_DSC='Cash Head Type' and nvl(c.CV1_VAL,'X') != 'US'";
						PreparedStatement ps = null;
						ResultSet res = null;

						ps = connection.prepareStatement(purposeQuery);
						res = ps.executeQuery();
						while (res.next()) {

							HeadVO head = new HeadVO();
							head.setMstKey(res.getString("MST_KEY"));
							head.setMstCode(res.getString("MST_CDE"));
							head.setMstValue(res.getString("MST_VAL"));
							head.setMstDsc(res.getString("MST_DSC"));
							headList.add(head);

						}

						return headList;

					}

				});
		return (List<HeadVO>) result;

	}

	// //////////////////////////////////////////////////////////////////////////////////

	// for getting the Indian cities list only
	public List<PlaceVO> getIndianCities(String tokenDate) {

		Object result = getJdbcTemplate().execute(
				new iTinJdbcConnectionCallback(new Object[] { tokenDate }) {

					@Override
					public Object doInConnection(Connection connection)
							throws SQLException, DataAccessException {
						String tokenDate = (String) this.getArguments()[0];

						List<PlaceVO> indianCityList = new ArrayList<PlaceVO>();
						String purposeQuery = "SELECT CTY_CDE, CTY_NAM, APT_NME, CNY_CDE,CRT_DAT,LST_UPD_DAT  FROM ITIN_CTY_MST  WHERE CNY_CDE = 'IN' AND CRT_DAT > to_date('"
								+ tokenDate
								+ "','DD-MON-YYYY HH:MI:SS AM') ORDER BY CTY_NAM";
						PreparedStatement ps = null;
						ResultSet res = null;

						ps = connection.prepareStatement(purposeQuery);
						res = ps.executeQuery();
						while (res.next()) {

							PlaceVO place = new PlaceVO();
							place.setPlaceCode(res.getString("CTY_CDE"));
							place.setPlaceName(res.getString("CTY_NAM"));
							indianCityList.add(place);
						}

						return indianCityList;
					}

				});
		return (List<PlaceVO>) result;
	}

	// //////////////////////////////////////////////////////////////////////////////////

	// for getting the country list
	public List<PlaceVO> getCountries(String tokenDate) {

		Object result = getJdbcTemplate().execute(
				new iTinJdbcConnectionCallback(new Object[] { tokenDate }) {

					@Override
					public Object doInConnection(Connection connection)
							throws SQLException, DataAccessException {
						String tokenDate = (String) this.getArguments()[0];

						List<PlaceVO> countryList = null;
						PreparedStatement ps = null;
						ResultSet res = null;
						String query = "SELECT initcap(MST_DSC) as MST_DSC,MST_VAL, MST_CDE,CRT_DAT,LST_UPD_DAT FROM ITIN_COM_MST A WHERE MST_KEY=129 AND CRT_DAT > to_date('"
								+ tokenDate
								+ "','DD-MON-YYYY HH:MI:SS AM') ORDER BY MST_DSC";

						ps = connection.prepareStatement(query);
						res = ps.executeQuery();
						countryList = new ArrayList<PlaceVO>();
						while (res.next()) {
							PlaceVO countries = new PlaceVO();
							countries.setPlaceCode(res.getString("MST_CDE"));
							countries.setPlaceName(res.getString("MST_DSC"));
							countryList.add(countries);

						}
						return countryList;
					}

				});
		return (List<PlaceVO>) result;

	}

	// //////////////////////////////////////////////////////////////////////////////////

	// for getting the cost center list
	public List<CostCenterVO> getCostCenter(String tokenDate) {

		Object result = getJdbcTemplate().execute(
				new iTinJdbcConnectionCallback(new Object[] { tokenDate }) {

					@Override
					public Object doInConnection(Connection connection)
							throws SQLException, DataAccessException {
						String tokenDate = (String) this.getArguments()[0];

						PreparedStatement ps = null;
						ResultSet res = null;
						List<CostCenterVO> ccList = null;
						String query = " SELECT a.mst_cde ,a.CV3_VAL,NVL(b.ifin_cc_nm, a.mst_dsc) AS IFIN_CC_NM,a.CRT_DAT,a.LST_UPD_DAT FROM itin_com_mst a, "
								+ " itin_ifin_erp_cc b,itin_emp_mst e WHERE a.mst_key = 135 AND a.MST_STS = 'Y'"
								+ " and a.cv1_val = e.cct_hol_nam(+) AND a.mst_dsc = b.erp_cc_nam(+) AND a.CRT_DAT > to_date('"
								+ tokenDate + "','DD-MON-YYYY HH:MI:SS AM') ";
						query = query + " ORDER BY ifin_cc_nm";

						ps = connection.prepareStatement(query);
						res = ps.executeQuery();
						ccList = new ArrayList<CostCenterVO>();
						while (res.next()) {
							CostCenterVO cc = new CostCenterVO();
							cc.setCostCenterCode(res.getString("MST_CDE"));
							cc.setCostCenter(res.getString("IFIN_CC_NM"));
							// cc.setCcValue(res.getString("CV3_VAL"));
							// cc.setCreatedDate(res.getString("CRT_DAT"));
							// cc.setLastUpdatedDate(res.getString("LST_UPD_DAT"));
							ccList.add(cc);
						}

						return ccList;
					}

				});
		return (List<CostCenterVO>) result;

	}

	// //////////////////////////////////////////////////////////////////////////////////

	// for getting the purpose list
	public List<PurposeVO> getPurposeTravelList(String tokenDate) {

		Object result = getJdbcTemplate().execute(
				new iTinJdbcConnectionCallback(new Object[] { tokenDate }) {

					@Override
					public Object doInConnection(Connection connection)
							throws SQLException, DataAccessException {
						String tokenDate = (String) this.getArguments()[0];

						List<PurposeVO> purposeTravelList = new ArrayList<PurposeVO>();
						String purposeQuery = "SELECT MST_CDE AS CODE, MST_DSC AS NAME,CRT_DAT,LST_UPD_DAT FROM ITIN_COM_MST WHERE MST_KEY = 162 AND MST_STS = 'Y' AND CRT_DAT > to_date('"
								+ tokenDate
								+ "','DD-MON-YYYY HH:MI:SS AM') ORDER BY NAME";
						PreparedStatement ps = null;
						ResultSet res = null;

						ps = connection.prepareStatement(purposeQuery);
						res = ps.executeQuery();
						while (res.next()) {

							PurposeVO purpose = new PurposeVO();
							purpose.setPurposeCode(res.getString("CODE"));
							purpose.setPurposeDetail(res.getString("NAME"));

							purposeTravelList.add(purpose);

						}

						return purposeTravelList;
					}

				});
		return (List<PurposeVO>) result;

	}

	// //////////////////////////////////////////////////////////////////////////////////

	// for getting the approver's pending request view
	// input - employee code
	public List<RequestVO> getApprovals(String userID, int start, int size) {

		Object result = getJdbcTemplate().execute(
				new iTinJdbcConnectionCallback(new Object[] { userID, start,
						size }) {

					@Override
					public Object doInConnection(Connection connection)
							throws SQLException, DataAccessException {
						String userID = (String) this.getArguments()[0];
						int start = (Integer) this.getArguments()[1];
						int size = (Integer) this.getArguments()[2];

						int flightSize = 0;
						int hotelSize = 0;
						int cabSize = 0;
						int visaSize = 0;

						PreparedStatement ps = null;
						ResultSet res = null;
						RequestVO historyVo = null;
						StringBuffer query = new StringBuffer();
						List<RequestVO> historyVOS = new ArrayList<RequestVO>();

						query.append("SELECT 0 pend_pro, a.dta_cde, e.emp_id, INITCAP (e.emp_nam) ");
						query.append("                emp_nam, ");
						query.append("                Round(NVL((SELECT SUM (NVL(adv_in_usd,0)) ");
						query.append("                    FROM itin_dta_req_adv ");
						query.append("                    WHERE dta_cde = a.dta_cde and mst_sts='Y'),0)) total_usd, ");
						query.append("                  Round(NVL((SELECT SUM (NVL(adv_in_usd,0) * NVL(bil_prt,0) / 100) ");
						query.append("                     FROM itin_dta_req_adv ");
						query.append("                    WHERE dta_cde = a.dta_cde and mst_sts = 'Y' ");
						query.append("					and NVL(IS_BIL_CST,'N') ='Y'),0)) total_bil_usd, ");
						query.append("                  ROUND (NVL ((SELECT SUM (NVL (adv_in_usd, 0)) ");
						query.append("                  FROM itin_dta_req_adv ");
						query.append("                  WHERE dta_cde = a.dta_cde AND mst_sts = 'Y' ");
						query.append("				   AND NVL (is_bil_cst, 'N') = 'A'),0)) acc_dealpl_usd, ");
						query.append("                  (Round(NVL((SELECT SUM (NVL(adv_in_usd,0) * (100 - nvl(bil_prt,0)) / 100 ");
						query.append("                              ) ");
						query.append("                     FROM itin_dta_req_adv  ");
						query.append("                    WHERE dta_cde = a.dta_cde and mst_sts = 'Y'");
						query.append("					and NVL(IS_BIL_CST,'N') ='N'),0)) ");
						query.append("					+ Round(NVL((SELECT SUM (nvl(adv_in_usd,0) * (100 - nvl(bil_prt,0)) / 100 ");
						query.append("                              ) ");
						query.append("                     FROM itin_dta_req_adv ");
						query.append("                    WHERE dta_cde = a.dta_cde and mst_sts = 'Y' ");
						query.append("					and NVL(IS_BIL_CST,'N') ='Y'),0))) total_non_bil_usd, ");
						query.append("                  NVL ((SELECT cty_nam ");
						query.append("                          FROM itin_cty_mst citymaster ");
						query.append("                         WHERE citymaster.cty_cde = a.fst_leg_org), ");
						query.append("                       a.fst_leg_org ");
						query.append("                      ) origin, ");
						query.append("                  NVL ((SELECT cty_nam ");
						query.append("                          FROM itin_cty_mst citymaster ");
						query.append("                         WHERE citymaster.cty_cde = a.fst_leg_dst), ");
						query.append("                       a.fst_leg_dst ");
						query.append("                      ) dest, ");
						query.append("                  a.fst_leg_tvl_dat tvl_date, ");
						query.append("                  NVL ((SELECT emp_id ");
						query.append("                          FROM itin_emp_mst ");
						query.append("                         WHERE emp_cde = a.dta_lst_apr_emp), '-') apr_emp_cde, ");
						query.append("                  NVL ((SELECT emp_nam ");
						query.append("                          FROM itin_emp_mst ");
						query.append("                         WHERE emp_cde = a.dta_lst_apr_emp), ");
						query.append("                       '-') apr_emp_name, 'APP_STS' app_sts, ");
						query.append("                  (SELECT mst_dsc ");
						query.append("                     FROM itin_com_mst ");
						query.append("                    WHERE mst_cde = a.dta_sub_sts) wrf_sts, ");
						query.append("                  NVL ((SELECT INITCAP (emp_nam) ");
						query.append("                          FROM itin_emp_mst ");
						query.append("                         WHERE emp_cde = ");
						query.append("                                  DECODE (a.lck_emp_cde, ");
						query.append("                                          '-1', a.dta_acn_by, ");
						query.append("                                          '', a.dta_acn_by, ");
						query.append("                                          a.dta_acn_by ");
						query.append("                                         )), ");
						query.append("                       '-' ");
						query.append("                      ) acn_emp_cde, ");
						query.append("                  a.lck_emp_cde locked_emp_cde, 'SLA' sla, dta_app_dat, ");
						query.append("                  NVL (a.dta_id, a.dta_cde) dta_id, a.pur_travel,a.dta_req_typ, a.is_rtn_tvl,  ");
						query.append("                  (SELECT mst_dsc ");
						query.append("                     FROM itin_com_mst ");
						query.append("                    WHERE mst_cde = a.tvl_bo) trvl_bo, ");
						query.append("                  (SELECT mst_dsc ");
						query.append("                     FROM itin_com_mst ");
						query.append("                    WHERE mst_cde = a.tvl_cc_cde) trvl_cc, ");
						query.append("                  (SELECT mst_dsc ");
						query.append("                     FROM itin_com_mst ");
						query.append("                    WHERE mst_cde = e.emp_ccn_cde) emp_cc, ");
						query.append("                  (SELECT dta_apr_rem ");
						query.append("                     FROM itin_dta_apr_dtl ");
						query.append("                    WHERE dta_cde = a.dta_cde ");
						query.append("                      AND dta_apr_emp = a.dta_lst_apr_emp ");
						query.append("                      AND dta_apr_act <> 'G' ");
						query.append("                      AND ROWNUM = 1) lst_remarks, ");
						query.append(" (case ");
						query.append("    when nvl(a.qte_for_apr,'N')='Y'  ");
						query.append("      then 'Y'  ");
						query.append("    when a.book_tik='N'  ");
						query.append("      then (case ");
						query.append("              when (nvl((select count(dta_cde) from itin_dta_req_ind_dtl ");
						query.append("                          where dta_cde = a.dta_cde and ind_itm_cde<>1033),0)=0) ");
						query.append("                 then 'Y' ");
						query.append("              else ");
						query.append("                  (SELECT decode(instr(wm_concat(nvl(is_qte_sel,'N')),'N'),0,'Y','N') ");
						query.append("                    FROM itin_dta_req_ind_dtl WHERE dta_cde = a.dta_cde and ind_itm_cde<>1033) ");
						query.append("            end)   ");
						query.append("    else  ");
						query.append("      (SELECT decode(instr(wm_concat(nvl(is_qte_sel,'N')),'N'),0,'Y','N') ");
						query.append("        FROM itin_dta_req_ind_dtl WHERE dta_cde = a.dta_cde)  ");
						query.append(" end	 ");
						query.append(" ) is_qte_selected,a.dta_app_dat appdate ");
						query.append(",(case when a.tvl_tntve = 'Y' then 'Yes' else 'No' end) TVL_TNT");
						query.append(" , (select sum(decode(act_usd,0,bud_usd,act_usd))  from vw_itin_bud_cur_yr ");
						query.append("  WHERE ifin_cc_cd = a.ifin_cc_id ");
						query.append("     and nvl(ifin_acc_cd,'-1')= nvl(a.ifin_acc_id,'-1')) actual_budget,");
						query.append(" (select sum(nvl(bud_usd,0)) from vw_itin_bud_cur_yr ");
						query.append("    WHERE ifin_cc_cd = a.ifin_cc_id ");
						query.append("        and nvl(ifin_acc_cd,'-1')= nvl(a.ifin_acc_id,'-1')) total_budget ");
						query.append("             FROM itin_dta_app_dtl a, itin_emp_mst e ");
						query.append("            WHERE e.emp_cde = a.emp_cde ");
						query.append("              AND a.dta_sts = 1821 ");
						query.append("              AND dta_acn_by = " + userID);
						query.append(" ORDER BY a.dta_app_dat DESC NULLS LAST ");

						ps = connection.prepareStatement(query.toString(),
								ResultSet.TYPE_SCROLL_SENSITIVE,
								ResultSet.CONCUR_READ_ONLY);
						res = ps.executeQuery();

						int count = 0;
						int rowCount = 0;

						if (res.last()) {
							rowCount = res.getRow();
							res.beforeFirst();
							if (rowCount >= start) {
								if (start == 1 || start == 0)
									res.beforeFirst();
								else
									res.absolute(start - 1);

							} else {
								return historyVOS;
							}

						}

						while (res.next()) {

							if (size > count++) {
								System.out.println(count
										+ " : Hello rs Has Next element "
										+ res.getRow());
								RequestVO historyvo = new RequestVO();
								historyvo.setRequestID(res.getString("DTA_ID"));
								historyvo.setRequestCode(res
										.getString("DTA_CDE"));
								// historyvo.setRequestType("D");
								// historyVo.setReturnTravel(false);

								historyvo.setUserID(res.getString("EMP_ID"));
								System.out.println("EMP ID...."
										+ res.getString("EMP_ID"));
								if (res.getString("EMP_NAM") != null)
									historyvo.setEmpName(res
											.getString("EMP_NAM"));
								else
									historyvo.setEmpName(" ");
								if (res.getString("ORIGIN") != null)
									historyvo.setOrigin(res.getString("ORIGIN"));
								else
									historyvo.setOrigin("");
								if (res.getString("DEST") != null)
									historyvo.setDestination(res
											.getString("DEST"));
								else
									historyvo.setDestination("");
								historyvo.setTotalCost(res
										.getString("TOTAL_USD") == null ? "0"
										: res.getString("TOTAL_USD"));
								historyvo.setTotalBillCost(res
										.getString("TOTAL_BIL_USD") == null ? "0"
										: res.getString("TOTAL_BIL_USD"));
								historyvo.setTotalNonBillCost(res
										.getString("TOTAL_NON_BIL_USD") == null ? "0"
										: res.getString("TOTAL_NON_BIL_USD"));
								historyvo.setTotalAccBillCost(res
										.getString("acc_dealpl_usd") == null ? "0"
										: res.getString("acc_dealpl_usd"));
								DateFormat formatter1 = new SimpleDateFormat(
										"yyyy-MM-dd HH:mm:ss");

								try {
									// if (null !=
									// res.getString("TVL_DATE")) {
									// historyvo.setT(formatter
									// .format((java.util.Date)
									// formatter1.parse(res
									// .getString("TVL_DATE"))));
									// }
									// else
									// historyvo.setRequestDate("");
									
								//if (null != res.getString("appdate")) {
//										historyvo.setRequestDate(DATE_ONLY_FORMATTER
//														.format((java.util.Date) formatter1.parse(res.getString("appdate"))));
//									}
								} catch (Exception e) {
									e.printStackTrace();
								}
								historyvo.setApproverCode(res
										.getString("APR_EMP_CDE"));
								if (res.getString("APR_EMP_NAME") != null)
									historyvo.setApproverName(res
											.getString("APR_EMP_NAME"));
								else
									historyvo.setApproverName("");
								historyvo.setWorkflowStatus(res
										.getString("WRF_STS"));
								historyvo.setServicedBy(res
										.getString("ACN_EMP_CDE"));
								historyvo.setSla(res.getString("SLA"));
								historyvo.setApplicationStatus(res
										.getString("PEND_PRO"));
								if (res.getString("EMP_CC") != null)
									historyvo.setEmpCC(res.getString("EMP_CC")); // CC
																					// of
																					// Employee
								else
									historyvo.setEmpCC("");
								if (res.getString("TRVL_CC") != null)
									historyvo.setCC(res.getString("TRVL_CC")); // CC
																				// in
																				// which
																				// employee
																				// is
																				// traveling
								else
									historyvo.setCC("");
								if (res.getString("PUR_TRAVEL") != null)
									historyvo.setPurpose(res
											.getString("PUR_TRAVEL"));
								else
									historyvo.setPurpose("");
								historyvo.setRequestType(res
										.getString("dta_req_typ"));
								if (res.getString("is_rtn_tvl")
										.equalsIgnoreCase("Y"))
									historyvo.setReturnTravel(true);
								else
									historyvo.setReturnTravel(false);
								historyvo.setAvailBudget(res
										.getString("actual_budget"));
								historyvo.setTotalTravelBudget(res
										.getString("total_budget"));
								historyvo.setLastRemark(res
										.getString("LST_REMARKS") == null ? ""
										: res.getString("LST_REMARKS"));
								historyvo
										.setVisible(historyvo
												.getApplicationStatus().equals(
														"0") ? true : false);
								if (historyvo.isVisible()) {
									historyvo.setVisible(res.getString(
											"is_qte_selected")
											.equalsIgnoreCase("Y") ? true
											: false);
								}

								historyvo.setBO(res.getString("trvl_bo")); // for
																			// fetching
																			// the
																			// BO/Project

								historyvo.setSelected(false);

								LOGGER.trace(historyvo.getTotalBillCost());
								
								if (historyvo.getTotalBillCost().equals("0")) {
									historyvo.setBillableToCust("N");
								} else {
									historyvo.setBillableToCust("Y");
								}

								historyvo
										.setTravelDetails(viewFlightTrainDetails(historyvo
												.getRequestCode()));
								historyvo
										.setCostCenters(viewCostCenterDetails(historyvo
												.getRequestCode()));
								historyvo
										.setCashDetails(viewCostDetails(historyvo
												.getRequestCode()));
								historyvo
										.setHotelDetails(viewHotelDetails(historyvo
												.getRequestCode()));
								historyvo
										.setCabDetails(viewCabDetails(historyvo
												.getRequestCode()));
								historyvo
										.setVisaDetails(viewVisaDetails(historyvo
												.getRequestCode()));

								flightSize = historyvo.getTravelDetails()
										.size();
								hotelSize = historyvo.getHotelDetails().size();
								cabSize = historyvo.getCabDetails().size();
								visaSize = historyvo.getVisaDetails().size();

								// F - Flight only, H - Hotel only, V - Visa
								// only, C - Cab
								// only, S - Standard (Combination) requests

								if (flightSize != 0 && hotelSize == 0
										&& cabSize == 0 && visaSize == 0) {
									historyvo.setReqHeadType("F");
								} else if (flightSize == 0 && hotelSize != 0
										&& cabSize == 0 && visaSize == 0) {
									historyvo.setReqHeadType("H");
								} else if (flightSize == 0 && hotelSize == 0
										&& cabSize != 0 && visaSize == 0) {
									historyvo.setReqHeadType("C");
								} else if (flightSize == 0 && hotelSize == 0
										&& cabSize == 0 && visaSize != 0) {
									historyvo.setReqHeadType("V");
								} else {
									historyvo.setReqHeadType("S");
								}

								System.out.println("DTA Header Type...."
										+ historyvo.getReqHeadType());

								
								historyVOS.add(historyvo);
							} else {

								return historyVOS;
							}

							// historyVo = new RequestVO();
							// historyVo.setDtaHistoryVos(historyVOS);

						}

						return historyVOS;

					}

				});
		return (List<RequestVO>) result;
	}

	// //////////////////////////////////////////////////////////////////////////////////
	public boolean approveDta(String dtaNum, ApproverVO approve) {

		// TransactionDefinition paramTransactionDefinition = new
		// DefaultTransactionDefinition();
		// TransactionStatus status = transactionManager
		// .getTransaction(paramTransactionDefinition);

		Object result = getJdbcTemplate()
				.execute(
						new iTinJdbcConnectionCallback(new Object[] { dtaNum,
								approve }) {

							@Override
							public Object doInConnection(Connection connection)
									throws SQLException, DataAccessException {
								String dtaNum = (String) this.getArguments()[0];
								ApproverVO approve = (ApproverVO) this
										.getArguments()[1];

								String[] outParameters = null;
								Calendar cal = Calendar.getInstance();
								DateFormat formatter = new SimpleDateFormat(
										"dd-MMM-yy");

								String instring = null;
								String cmtstr = approve.getApproverRemarks();
								approve.setDtaNo(dtaNum);
								String outstring = null;

								if (null == approve.getApproveBy()) {
									instring = "" + approve.getDtaNo() + "~"
											+ approve.getDtaActionBy() + "~"
											+ approve.getCurntOpndEmpCode()
											+ "~" + 4 + "~" + "N";

								

									outstring = callUpdateStatusProc(instring,
											cmtstr);
									
									outParameters = outstring.split("~");
									
								} else {
									instring = approve.getDtaNo();

									CallableStatement st = connection
											.prepareCall("{call ITIN_GTD_APPVL (?,?,?,?)}");
									st.setString(1, instring);
									st.setString(2, cmtstr);
									st.setString(3,
											approve.getCurntOpndEmpCode());
									st.registerOutParameter(4, Types.VARCHAR);
									st.execute();
									outstring = st.getString(4);
									outParameters = outstring.split("~");

								}

							
								return true;
							}

						});

		return (Boolean) result;

	}

	// //////////////////////////////////////////////////////////////////////////////////

	// for getting the BO/Project
	// inputs - cost center and employee code
	// output - bo/project list
	public List<BusinessOpportunityVO> getBOProject(String costCenter,
			String userId) {

		Object result = getJdbcTemplate().execute(
				new iTinJdbcConnectionCallback(new Object[] { costCenter,
						userId }) {

					@Override
					public Object doInConnection(Connection connection)
							throws SQLException, DataAccessException {
						String costCenter = (String) this.getArguments()[0];
						String userId = (String) this.getArguments()[1];

						int ccCode = Integer.parseInt(costCenter);
						String query = "SELECT a.mst_cde, a.mst_val, a.mst_dsc, 'Financial' botype, 'BO' bo_proj,  "
								+ " b.account_name,a.crt_usr crtdby,a.CRT_DAT crtDate,a.LST_UPD_DAT lstupdDate "
								+ "FROM itin_com_mst a, itin_iwise_bo_dtls b  "
								+ "WHERE a.mst_key = 141  "
								+ "AND b.bus_ops_cod = a.mst_val "
								+ "AND a.cv1_val = 'Financial'  "
								+ "AND a.cv2_val = 'Active'  "
								+ "AND a.cv3_val IN (  "
								+ "SELECT iw_lob_cd  "
								+ "        FROM itin_ifin_lob_cc "
								+ "       WHERE cc_cde =  "
								+ "                    (SELECT ifin_cc_id "
								+ "                       FROM itin_ifin_erp_cc "
								+ "                      WHERE erp_cc_nam = (SELECT mst_dsc "
								+ "                                            FROM itin_com_mst "
								+ "                                           WHERE mst_cde =  "

								+ ccCode

								+ "		)))  "
								+ "union  "
								+ "SELECT a.mst_cde, a.mst_val, a.mst_dsc, 'Financial' botype, 'BO' bo_proj,  "
								+ "b.account_name,a.crt_usr crtdby,a.CRT_DAT crtDate,a.LST_UPD_DAT lstupdDate "
								+ "FROM itin_com_mst a, itin_iwise_bo_dtls b  "
								+ "WHERE a.mst_key = 141  "
								+ "AND b.bus_ops_cod = a.mst_val "
								+ "AND a.cv1_val = 'Financial'  "
								+ "AND a.cv2_val = 'Active'  "
								+ "AND a.mst_val IN (  "
								+ "SELECT distinct bus_ops_cod "
								+ "FROM itin_iwise_bo_dtls  "
								+ "WHERE reg_cod IN (  "
								+ "SELECT iw_lob_cd  "
								+ "        FROM itin_ifin_lob_cc "
								+ "       WHERE cc_cde =  "
								+ "                    (SELECT ifin_cc_id "
								+ "                       FROM itin_ifin_erp_cc "
								+ "                      WHERE erp_cc_nam = (SELECT mst_dsc "
								+ "                                            FROM itin_com_mst "
								+ "                                           WHERE mst_cde =  "
								+ ccCode
								+ "))) and status = 'Active' ) "
								+ "UNION  "
								+ "SELECT a.mst_cde, a.mst_val, a.mst_dsc, 'Non Financial' botype, 'BO' bo_proj, "
								+ "b.account_name, a.crt_usr crtdby,a.CRT_DAT crtDate,a.LST_UPD_DAT lstupdDate "
								+ "FROM itin_com_mst a, itin_iwise_bo_dtls b  "
								+ "WHERE a.mst_key = 141  "
								+ "AND b.bus_ops_cod = a.mst_val "
								+ "AND cv1_val = 'Non Financial'  "
								+ "AND cv2_val = 'Active'  "
								+ "AND (   b.account_name IS NULL "
								+ "OR b.account_name IN (  "
								+ "SELECT ifin_acc_nam  "
								+ "   FROM itin_ifin_acc_cc "
								+ "  WHERE ifin_cc_id IN (  "
								+ "                       SELECT ifin_cc_id  "
								+ "                         FROM itin_ifin_erp_cc "
								+ "                        WHERE erp_cc_nam =  "
								+ "                                         (SELECT mst_dsc "
								+ "                                            FROM itin_com_mst "
								+ "                                           WHERE mst_cde =  "
								+ ccCode
								+ ")))  "
								+ ")  "
								+ "UNION "
								+ "SELECT a.mst_cde, a.mst_val, a.mst_dsc, '-' botype, 'Projects' bo_proj, "
								+ "b.ifin_acc_nam, a.crt_usr crtdby,a.CRT_DAT crtDate,a.LST_UPD_DAT lstupdDate "
								+ "FROM itin_com_mst a, itin_ifin_prj_acc_cc b  "
								+ "WHERE b.erp_prj_nam = a.mst_dsc  "
								+ "AND a.cv1_val = 'Y'  "
								+ "AND a.mst_key = 143  "
								+ "AND a.mst_dsc IN (  "
								+ "  SELECT erp_prj_nam  "
								+ "    FROM itin_ifin_prj_acc_cc "
								+ "   WHERE ifin_cc_id =  "
								+ "                    (SELECT ifin_cc_id "
								+ "                       FROM itin_ifin_erp_cc "
								+ "                      WHERE erp_cc_nam = (SELECT mst_dsc "
								+ "                                            FROM itin_com_mst "
								+ "                                           WHERE mst_cde =  "
								+ ccCode
								+ ")))  "
								+ "UNION  "
								+ "SELECT mst_cde, mst_val, mst_dsc, '-' botype, 'Others' bo_proj, '', a.crt_usr crtdby,a.CRT_DAT crtDate,a.LST_UPD_DAT lstupdDate "
								+ "FROM itin_com_mst a WHERE mst_key = 144  AND (cv2_val = 'N' OR (cv2_val = 'Y' AND cv1_val ='"
								+ ccCode
								+ "')) AND MST_STS='Y'"
								+ "UNION "
								+ "SELECT mst_cde, mst_val, mst_dsc, '-' botype, 'Leads' bo_proj, '', a.crt_usr crtdby,a.CRT_DAT crtDate,a.LST_UPD_DAT lstupdDate "
								+ " FROM itin_com_mst a WHERE mst_key = 157 AND mst_sts = 'Y' order by mst_dsc";

						List<BusinessOpportunityVO> projBOList = new ArrayList<BusinessOpportunityVO>();
						PreparedStatement ps = null;
						ResultSet res = null;

						ps = connection.prepareStatement(query);
						res = ps.executeQuery();
						try {
							while (res.next()) {
								BusinessOpportunityVO vo = new BusinessOpportunityVO();

								vo.setMasterCode(res.getString("MST_CDE"));
								vo.setMasterValue(res.getString("ACCOUNT_NAME"));
								vo.setMasterDesc(res.getString("MST_DSC"));
								vo.setIsBOOrProj(res.getString("BOTYPE"));
								vo.setBOType(res.getString("BO_PROJ"));
								vo.setCrtdBy(res.getString("crtdby"));
								// vo.setCreatedDate(res.getString("crtdby"));
								// vo.setLastUpdatedDate(res.getString("crtdby"));
								projBOList.add(vo);
							}
						} catch (SQLException e) {
							e.printStackTrace();
						}

						return projBOList;

					}

				});
		return (List<BusinessOpportunityVO>) result;

	}

	// //////////////////////////////////////////////////////////////////////////////////

	public class RequestVORowMapper implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			RequestVO requestVO = new RequestVO();
			// set Request Objects
			return requestVO;
		}

	}

	public class MasterInfoRowMapper implements RowMapper {

		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			MasterInfo masterInfo = new MasterInfo();
			// set Request Objects
			return masterInfo;
		}

	}

}
