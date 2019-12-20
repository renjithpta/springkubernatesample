package com.ibsplc.itin.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
/**
 * 
 * @author A-4211
 *
 */
@XmlRootElement(name = "request")
public class RequestVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String requestID;
	private String requestCode;  // newly added on May 08, 2013 by Savin Koshy (A-5152)
	private String userID;
	private String empCode;
	private String empName;      // newly added on May 08, 2013 by Savin Koshy (A-5152)
	private String approverCode;
	private String approverName;  // newly added on May 08, 2013 by Savin Koshy (A-5152)
	
	private String projectName;
	private String deptName;
	private String CC;           // newly added on May 08, 2013 by Savin Koshy (A-5152)
	private String BO;           // newly added on May 08, 2013 by Savin Koshy (A-5152)
	private String purposeDetail;
	private String purposeCode;
	private String purpose;
	private String remarks;
	
	private String BOcode;
	
	private String BOtype;// added by 4144 to show BO/type
	private String BOcommercial; // added by 4144 to show BO/type
	
	
	private String empStatus;
	private String officeEmail;
	private String requestDate;
	private String requestTime;
	private String requestType;
	private int headType;
	private String requestStatus;
	private String requestStatusCode;   // newly added on May 08, 2013 by Savin Koshy (A-5152)
	
	private String totalCost;
	private String totalBillCost;
	private String totalNonBillCost;
	private String totalAccBillCost;
	
	private String Origin;
	private String Destination;
	private String applicationStatus;
	private String applicationStatusCode;
	private String workflowStatus;
	private String servicedBy;      
	private String sla;
	private String empCC;
	private boolean isSelected;
	private String availBudget;
	private String totalTravelBudget;
	
	private boolean visible;
	private String lastRemark;
  
	/////////////////////////////////////////////
	// Used only for one-way trip and round trip //
	private int numOfNights;
	/////////////////////////////////////////////
	
	//private boolean flight = false;
	//private boolean hotel = false;
	//private boolean cab = false;
	//private boolean visa = false;
	
	private String reqHeadType;       // to identify whether DTA is flight only/hotel only/cab only/visa only/combination
	
	private String nextAction;
	private String nextApprover;
	private String legOrigin;
	private String legDestination;
	private String action;
	private String updatedUser;
	private String otherExpenseDetails;
	
	private List<TravelVO> travelDetails = null;
	private List<CostCenterVO> costCenters = null;
	private List<CostDetail> costDetails = null;
	private List<HotelVO> hotelDetails = null;
	private List<CabVO> cabDetails = null;
	private List<VisaVO> visaDetails = null;
	

	//to add booking details
	private List<BookingVO> bookingDetails = null;
	
	private CashVO cashVo;
	
	//to add expense details 
	private List<CashVO> cashDetails = null;
	private List<RequestVO> dtaHistoryVos;
	private String legTravelDateString;
	private String lastLegDepDateString;
	private String lastLegArrivalDate;
	
	private String requestedDate;
	private String sendDraft;
	private String workFlow;
	private boolean returnTravel;
	
	private String dtaNo;   
	
	private String billableToCust;
	
	private String flightBillable;
	private String hotelBillable;
	private String visaBillable;
	private String ModorCop;
	
	
	/**
	 * @return the bookingDetails
	 */
	public List<BookingVO> getBookingDetails() {
		return bookingDetails;
	}
	/**
	 * @param bookingDetails the bookingDetails to set
	 */
	public void setBookingDetails(List<BookingVO> bookingDetails) {
		this.bookingDetails = bookingDetails;
	}
	
	
	public String getModorCop() {
		return ModorCop;
	}
	public void setModorCop(String getModorCop) {
		this.ModorCop = ModorCop;
	}
	@XmlElement(name="billableToCust")
	public String getBillableToCust() {
		return billableToCust;
	}
	public void setBillableToCust(String billableToCust) {
		this.billableToCust = billableToCust;
	}
	
	@XmlElement(name= "returnTravel")
	public boolean isReturnTravel() {
		return returnTravel;
	}
	public void setReturnTravel(boolean returnTravel) {
		this.returnTravel = returnTravel;
	}
	
	public String getWorkFlow() {
		return workFlow;
	}
	public void setWorkFlow(String workFlow) {
		this.workFlow = workFlow;
	}
	public String getSendDraft() {
		return sendDraft;
	}
	public void setSendDraft(String sendDraft) {
		this.sendDraft = sendDraft;
	}
	
	@XmlElement(name="requestedDate")
	public String getRequestedDate() {
		return requestedDate;
	}
	public void setRequestedDate(String requestedDate) {
		this.requestedDate = requestedDate;
	}
	
	@XmlElement(name="lastLegArrivalDate")
	public String getLastLegArrivalDate() {
		return lastLegArrivalDate;
	}
	public void setLastLegArrivalDate(String lastLegArrivalDate) {
		this.lastLegArrivalDate = lastLegArrivalDate;
	}
	
	@XmlElement(name="legTravelDateString")
	public String getLegTravelDateString() {
		return legTravelDateString;
	}
	public void setLegTravelDateString(String legTravelDateString) {
		this.legTravelDateString = legTravelDateString;
	}
	
	@XmlElement(name="lastLegDepDateString")
	public String getLastLegDepDateString() {
		return lastLegDepDateString;
	}
	public void setLastLegDepDateString(String lastLegDepDateString) {
		this.lastLegDepDateString = lastLegDepDateString;
	}
	public List<CashVO> getCashDetails() {
		return cashDetails;
	}
	public void setCashDetails(List<CashVO> cashDetails) {
		this.cashDetails = cashDetails;
	}
	
	@XmlElement(name="reqHeadType")
	public String getReqHeadType() {
		return reqHeadType;
	}
	public void setReqHeadType(String reqHeadType) {
		this.reqHeadType = reqHeadType;
	}
	
	/**
	 * @return the requestID
	 */
	@XmlElement(name="requestID")
	public String getRequestID() {
		return requestID;
	}
	/**
	 * @param requestID the requestID to set
	 */
	public void setRequestID(String requestID) {
		this.requestID = requestID;
	}
	/**
	 * @return the userID
	 */
	@XmlElement(name="userID")
	public String getUserID() {
		return userID;
	}
	/**
	 * @param userID the userID to set
	 */
	public void setUserID(String userID) {
		this.userID = userID;
	}
	/**
	 * @return the empCode
	 */
	@XmlElement(name="empCode")
	public String getEmpCode() {
		return empCode;
	}
	/**
	 * @param empCode the empCode to set
	 */
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	/**
	 * @return the approverCode
	 */
	@XmlElement(name="approverCode")
	public String getApproverCode() {
		return approverCode;
	}
	/**
	 * @param approverCode the approverCode to set
	 */
	public void setApproverCode(String approverCode) {
		this.approverCode = approverCode;
	}
	
	/**
	 * @return the projectName
	 */
	@XmlElement(name="projectName")
	public String getProjectName() {
		return projectName;
	}
	/**
	 * @param projectName the projectName to set
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	/**
	 * @return the deptName
	 */
	@XmlElement(name="deptName")
	public String getDeptName() {
		return deptName;
	}
	/**
	 * @param deptName the deptName to set
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	/**
	 * @return the purposeDetail
	 */
	@XmlElement(name="purposeDetail")
	public String getPurposeDetail() {
		return purposeDetail;
	}
	/**
	 * @param purposeDetail the purposeDetail to set
	 */
	public void setPurposeDetail(String purposeDetail) {
		this.purposeDetail = purposeDetail;
	}
	/**
	 * @return the purposeCode
	 */
	@XmlElement(name="purposeCode")
	public String getPurposeCode() {
		return purposeCode;
	}
	/**
	 * @param purposeCode the purposeCode to set
	 */
	public void setPurposeCode(String purposeCode) {
		this.purposeCode = purposeCode;
	}
	/**
	 * @return the purpose
	 */
	@XmlElement(name="purpose")
	public String getPurpose() {
		return purpose;
	}
	/**
	 * @param purpose the purpose to set
	 */
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	/**
	 * @return the remarks
	 */
	@XmlElement(name="remarks")
	public String getRemarks() {
		return remarks;
	}
	/**
	 * @param remarks the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	/**
	 * @return the empStatus
	 */
	@XmlElement(name="empStatus")
	public String getEmpStatus() {
		return empStatus;
	}
	/**
	 * @param empStatus the empStatus to set
	 */
	public void setEmpStatus(String empStatus) {
		this.empStatus = empStatus;
	}
	/**
	 * @return the officeEmail
	 */
	@XmlElement(name="officeEmail")
	public String getOfficeEmail() {
		return officeEmail;
	}
	/**
	 * @param officeEmail the officeEmail to set
	 */
	public void setOfficeEmail(String officeEmail) {
		this.officeEmail = officeEmail;
	}
	/**
	 * @return the requestDate
	 */
	@XmlElement(name="requestDate")
	public String getRequestDate() {
		return requestDate;
	}
	/**
	 * @param requestDate the requestDate to set
	 */
	public void setRequestDate(String requestDate) {
		this.requestDate = requestDate;
	}
	/**
	 * @return the requestTime
	 */
	@XmlElement(name="requestTime")
	public String getRequestTime() {
		return requestTime;
	}
	/**
	 * @param requestTime the requestTime to set
	 */
	public void setRequestTime(String requestTime) {
		this.requestTime = requestTime;
	}
	/**
	 * @return the travelInfo
	 */
	//@XmlElement(name="travelDetails")
	public List<TravelVO> getTravelDetails() {
		if(travelDetails == null){
			travelDetails = new ArrayList<TravelVO>();
		}
		return travelDetails;
	}
	/**
	 * @param travelInfo the travelInfo to set
	 */
	public void setTravelDetails(List<TravelVO> travelDetails) {
		this.travelDetails = travelDetails;
	}

	
	//@XmlElement(name="costDetails")
	public List<CostDetail> getCostDetails() {
		if(costDetails == null)
			costDetails = new ArrayList<CostDetail>();
		
		return costDetails;
	}

	public void setCostDetails(List<CostDetail> costDetails) {
		this.costDetails = costDetails;
	}
	/**
	 * @return the requestType
	 */
	@XmlElement(name="requestType")
	public String getRequestType() {
		return requestType;
	}
	/**
	 * @param requestType the requestType to set
	 */
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	/**
	 * @return the costCenters
	 */
	//@XmlElement(name="costCenters")
	public List<CostCenterVO> getCostCenters() {
		if(costCenters == null){
			costCenters = new ArrayList<CostCenterVO>();
		}
		return costCenters;
	}
	/**
	 * @param costCenters the costCenters to set
	 */
	public void setCostCenters(List<CostCenterVO> costCenters) {
		this.costCenters = costCenters;
	}
	
	@XmlElement(name="numOfNights")
	public int getNumOfNights() {
		return numOfNights;
	}
	/**
	 * @param numOfNights the numOfNights to set
	 */
	public void setNumOfNights(int numOfNights) {
		this.numOfNights = numOfNights;
	}
	/**
	 * @return the hotel
	 */
//	@XmlElement(name="hotel")
//	public boolean isHotel() {
//		return hotel;
//	}
	/**
	 * @param hotel the hotel to set
	 */
//	public void setHotel(boolean hotel) {
//		this.hotel = hotel;
//	}
	
	/**
	 * @return the cab
	 */
//	@XmlElement(name="cab")
//	public boolean isCab() {
//		return cab;
//	}
	/**
	 * @param cab the cab to set
	 */
//	public void setCab(boolean cab) {
//		this.cab = cab;
//	}
	/**
	 * @return the visa
	 */
//	@XmlElement(name="visa")
//	public boolean isVisa() {
//		return visa;
//	}
	/**
	 * @param visa the visa to set
	 */
//	public void setVisa(boolean visa) {
//		this.visa = visa;
//	}
	/**
	 * @return the requestStatus
	 */
	@XmlElement(name="requestStatus")
	public String getRequestStatus() {
		return requestStatus;
	}
	/**
	 * @param requestStatus the requestStatus to set
	 */
	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}
	

	
	/**
	 * @return the hotelDetails
	 */
	public List<HotelVO> getHotelDetails() {
		if(hotelDetails == null){
			hotelDetails = new ArrayList<HotelVO>();
		}
		return hotelDetails;
	}
	/**
	 * @param hotelDetails the hotelDetails to set
	 */
	public void setHotelDetails(List<HotelVO> hotelDetails) {
		this.hotelDetails = hotelDetails;
	}
	/**
	 * @return the cabDetails
	 */
	public List<CabVO> getCabDetails() {
		if(cabDetails == null){
			cabDetails = new ArrayList<CabVO>();
		}
		return cabDetails;
	}
	/**
	 * @param cabDetails the cabDetails to set
	 */
	public void setCabDetails(List<CabVO> cabDetails) {
		this.cabDetails = cabDetails;
	}
	/**
	 * @return the visaDetails
	 */
	public List<VisaVO> getVisaDetails() {
		if(visaDetails == null){
			visaDetails = new ArrayList<VisaVO>();
		}
		return visaDetails;
	}
	/**
	 * @param visaDetails the visaDetails to set
	 */
	public void setVisaDetails(List<VisaVO> visaDetails) {
		this.visaDetails = visaDetails;
	}
	
	@XmlElement(name="requestCode")
	public String getRequestCode() {
		return requestCode;
	}
	public void setRequestCode(String requestCode) {
		this.requestCode = requestCode;
	}
	
	@XmlElement(name = "empName")
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	
	@XmlElement(name="CC")
	public String getCC() {
		return CC;
	}
	public void setCC(String cc) {
		CC = cc;
	}
	
	
	@XmlElement(name="BO")
	public String getBO() {
		return BO;
	}
	public void setBO(String bo) {
		BO = bo;
	}
	
	@XmlElement(name="approverName")
	public String getApproverName() {
		return approverName;
	}
	public void setApproverName(String approverName) {
		this.approverName = approverName;
	}
	
	public String getRequestStatusCode() {
		return requestStatusCode;
	}
	public void setRequestStatusCode(String requestStatusCode) {
		this.requestStatusCode = requestStatusCode;
	}
	
	@XmlElement(name="totalCost")
	public String getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(String totalCost) {
		this.totalCost = totalCost;
	}
	public String getTotalBillCost() {
		return totalBillCost;
	}
	public void setTotalBillCost(String totalBillCost) {
		this.totalBillCost = totalBillCost;
	}
	public String getTotalNonBillCost() {
		return totalNonBillCost;
	}
	public void setTotalNonBillCost(String totalNonBillCost) {
		this.totalNonBillCost = totalNonBillCost;
	}
	public String getTotalAccBillCost() {
		return totalAccBillCost;
	}
	public void setTotalAccBillCost(String totalAccBillCost) {
		this.totalAccBillCost = totalAccBillCost;
	}
	public String getOrigin() {
		return Origin;
	}
	public void setOrigin(String origin) {
		Origin = origin;
	}
	public String getDestination() {
		return Destination;
	}
	public void setDestination(String destination) {
		Destination = destination;
	}
	public String getApplicationStatus() {
		return applicationStatus;
	}
	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}
	public String getApplicationStatusCode() {
		return applicationStatusCode;
	}
	public void setApplicationStatusCode(String applicationStatusCode) {
		this.applicationStatusCode = applicationStatusCode;
	}
	public String getWorkflowStatus() {
		return workflowStatus;
	}
	public void setWorkflowStatus(String workflowStatus) {
		this.workflowStatus = workflowStatus;
	}
	public String getServicedBy() {
		return servicedBy;
	}
	public void setServicedBy(String servicedBy) {
		this.servicedBy = servicedBy;
	}
	public String getSla() {
		return sla;
	}
	public void setSla(String sla) {
		this.sla = sla;
	}
	public String getEmpCC() {
		return empCC;
	}
	public void setEmpCC(String empCC) {
		this.empCC = empCC;
	}
	public boolean isSelected() {
		return isSelected;
	}
	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
	public String getAvailBudget() {
		return availBudget;
	}
	public void setAvailBudget(String availBudget) {
		this.availBudget = availBudget;
	}
	public String getTotalTravelBudget() {
		return totalTravelBudget;
	}
	public void setTotalTravelBudget(String totalTravelBudget) {
		this.totalTravelBudget = totalTravelBudget;
	}
	
	@XmlElement(name="visible")
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	public String getLastRemark() {
		return lastRemark;
	}
	public void setLastRemark(String lastRemark) {
		this.lastRemark = lastRemark;
	}
	public List<RequestVO> getDtaHistoryVos() {
		return dtaHistoryVos;
	}
	public void setDtaHistoryVos(List<RequestVO> dtaHistoryVos) {
		this.dtaHistoryVos = dtaHistoryVos;
	}
	
	@XmlElement(name="nextAction")
	public String getNextAction() {
		return nextAction;
	}
	public void setNextAction(String nextAction) {
		this.nextAction = nextAction;
	}
	
	@XmlElement(name="nextApprover")
	public String getNextApprover() {
		return nextApprover;
	}
	public void setNextApprover(String nextApprover) {
		this.nextApprover = nextApprover;
	}
	@XmlElement(name="legOrigin")
	public String getLegOrigin() {
		return legOrigin;
	}
	public void setLegOrigin(String legOrigin) {
		this.legOrigin = legOrigin;
	}
	@XmlElement(name="legDestination")
	public String getLegDestination() {
		return legDestination;
	}
	public void setLegDestination(String legDestination) {
		this.legDestination = legDestination;
	}
	
	@XmlElement(name="action")
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	
	@XmlElement(name="updatedUser")
	public String getUpdatedUser() {
		return updatedUser;
	}
	public void setUpdatedUser(String updatedUser) {
		this.updatedUser = updatedUser;
	}
	@XmlElement(name="expenseDetails")
	public String getOtherExpenseDetails() {
		return otherExpenseDetails;
	}
	public void setOtherExpenseDetails(String otherExpenseDetails) {
		this.otherExpenseDetails = otherExpenseDetails;
	}
	@XmlElement(name="headType")
	public int getHeadType() {
		return headType;
	}
	public void setHeadType(int headType) {
		this.headType = headType;
	}
	public String getFlightBillable() {
		return flightBillable;
	}
	public void setFlightBillable(String flightBillable) {
		this.flightBillable = flightBillable;
	}
	public String getHotelBillable() {
		return hotelBillable;
	}
	public void setHotelBillable(String hotelBillable) {
		this.hotelBillable = hotelBillable;
	}
	public String getVisaBillable() {
		return visaBillable;
	}
	public void setVisaBillable(String visaBillable) {
		this.visaBillable = visaBillable;
	}
	
	@XmlElement(name="dtaNo")
	public String getDtaNo() {
		return dtaNo;
	}
	public void setDtaNo(String dtaNo) {
		this.dtaNo = dtaNo;
	}
	
	public CashVO getCashVo() {
		return cashVo;
	}
	public void setCashVo(CashVO cashVo) {
		this.cashVo = cashVo;
	}
	
	@XmlElement(name="BOtype")
	public String getBOtype() {
		return BOtype;
	}
	public void setBOtype(String bOtype) {
		BOtype = bOtype;
	}
	
	@XmlElement(name="BOcommercial")
	public String getBOcommercial() {
		return BOcommercial;
	}
	public void setBOcommercial(String bOcommercial) {
		BOcommercial = bOcommercial;
	}
	
	@XmlElement(name="BOcode")
	public String getBOcode() {
		return BOcode;
	}
	public void setBOcode(String bOcode) {
		BOcode = bOcode;
	}
	
	
	
	
}
