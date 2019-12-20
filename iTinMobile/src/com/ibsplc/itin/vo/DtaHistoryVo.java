package com.ibsplc.itin.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "ApproverDetail")
public class DtaHistoryVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String empId;
	private String empName;
	private String dtaNo;
	private String requestDate;
	private String Origin;
	private String Destination;
	private String applicationStatus;
	private String applicationStatusCode;
	private String workflowStatus;
	private String approverID;
	private String approverName;
	private String servicedBy;      
	private String sla;
	private boolean isLock;
	private boolean visible;
	private String dfltApplnStatus;
	private String dfltReqDateFrom;
	private String dfltReqDateTo;
	private String updatedUser;
	private String servicedByName;
	private String dtaId;
	private String remarks;
	private String remarksToolTip;
	private String lastRemark;
	private String totalCost;
	private String totalBillCost;
	private String totalNonBillCost;
	private String totalAccBillCost;
	private String purposeOfTravel;
	private String empCC;
	private String travelForCC;
	private String flightTrainCost;
	private String hotelCost;
	private String perdiemCost;
	private String otherCosts;
	private String visaCost;
	private String entertainmentCost;
	private String cCACost;
	private String flightTrainBilCost;
	private String hotelBilCost;
	private String perdiemBilCost;
	private String otherBilCosts;
	private String visaBilCost;
	private String entertainmentBilCost;
	private String cCABilCost;
	private String flightTrainNonBilCost;
	private String hotelNonBilCost;
	private String perdiemNonBilCost;
	private String otherNonBilCosts;
	private String visaNonBilCost;
	private String entertainmentNonBilCost;
	private String cCANonBilCost;
	private String Insurance;
	private String costToString;
	private String bilCostToString;
	private String nonBilCostToString;
	private boolean isSelected;
	private String travelDate;
	private String availBudget;
	private String totalTravelBudget;
	private String boProj;
	private List<DtaHistoryVo> dtaHistoryVos;
	
	
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
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getDtaNo() {
		return dtaNo;
	}
	public void setDtaNo(String dtaNo) {
		this.dtaNo = dtaNo;
	}
	public String getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(String requestDate) {
		this.requestDate = requestDate;
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
	public String getApproverID() {
		return approverID;
	}
	public void setApproverID(String approverID) {
		this.approverID = approverID;
	}
	public String getApproverName() {
		return approverName;
	}
	public void setApproverName(String approverName) {
		this.approverName = approverName;
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
	public boolean isLock() {
		return isLock;
	}
	public void setLock(boolean isLock) {
		this.isLock = isLock;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	public String getDfltApplnStatus() {
		return dfltApplnStatus;
	}
	public void setDfltApplnStatus(String dfltApplnStatus) {
		this.dfltApplnStatus = dfltApplnStatus;
	}
	public String getDfltReqDateFrom() {
		return dfltReqDateFrom;
	}
	public void setDfltReqDateFrom(String dfltReqDateFrom) {
		this.dfltReqDateFrom = dfltReqDateFrom;
	}
	public String getDfltReqDateTo() {
		return dfltReqDateTo;
	}
	public void setDfltReqDateTo(String dfltReqDateTo) {
		this.dfltReqDateTo = dfltReqDateTo;
	}
	public String getUpdatedUser() {
		return updatedUser;
	}
	public void setUpdatedUser(String updatedUser) {
		this.updatedUser = updatedUser;
	}
	public String getServicedByName() {
		return servicedByName;
	}
	public void setServicedByName(String servicedByName) {
		this.servicedByName = servicedByName;
	}
	public String getDtaId() {
		return dtaId;
	}
	public void setDtaId(String dtaId) {
		this.dtaId = dtaId;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getRemarksToolTip() {
		return remarksToolTip;
	}
	public void setRemarksToolTip(String remarksToolTip) {
		this.remarksToolTip = remarksToolTip;
	}
	public String getLastRemark() {
		return lastRemark;
	}
	public void setLastRemark(String lastRemark) {
		this.lastRemark = lastRemark;
	}
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
	public String getPurposeOfTravel() {
		return purposeOfTravel;
	}
	public void setPurposeOfTravel(String purposeOfTravel) {
		this.purposeOfTravel = purposeOfTravel;
	}
	public String getEmpCC() {
		return empCC;
	}
	public void setEmpCC(String empCC) {
		this.empCC = empCC;
	}
	public String getTravelForCC() {
		return travelForCC;
	}
	public void setTravelForCC(String travelForCC) {
		this.travelForCC = travelForCC;
	}
	public String getFlightTrainCost() {
		return flightTrainCost;
	}
	public void setFlightTrainCost(String flightTrainCost) {
		this.flightTrainCost = flightTrainCost;
	}
	public String getHotelCost() {
		return hotelCost;
	}
	public void setHotelCost(String hotelCost) {
		this.hotelCost = hotelCost;
	}
	public String getPerdiemCost() {
		return perdiemCost;
	}
	public void setPerdiemCost(String perdiemCost) {
		this.perdiemCost = perdiemCost;
	}
	public String getOtherCosts() {
		return otherCosts;
	}
	public void setOtherCosts(String otherCosts) {
		this.otherCosts = otherCosts;
	}
	public String getVisaCost() {
		return visaCost;
	}
	public void setVisaCost(String visaCost) {
		this.visaCost = visaCost;
	}
	public String getEntertainmentCost() {
		return entertainmentCost;
	}
	public void setEntertainmentCost(String entertainmentCost) {
		this.entertainmentCost = entertainmentCost;
	}
	public String getCCACost() {
		return cCACost;
	}
	public void setCCACost(String cost) {
		cCACost = cost;
	}
	public String getFlightTrainBilCost() {
		return flightTrainBilCost;
	}
	public void setFlightTrainBilCost(String flightTrainBilCost) {
		this.flightTrainBilCost = flightTrainBilCost;
	}
	public String getHotelBilCost() {
		return hotelBilCost;
	}
	public void setHotelBilCost(String hotelBilCost) {
		this.hotelBilCost = hotelBilCost;
	}
	public String getPerdiemBilCost() {
		return perdiemBilCost;
	}
	public void setPerdiemBilCost(String perdiemBilCost) {
		this.perdiemBilCost = perdiemBilCost;
	}
	public String getOtherBilCosts() {
		return otherBilCosts;
	}
	public void setOtherBilCosts(String otherBilCosts) {
		this.otherBilCosts = otherBilCosts;
	}
	public String getVisaBilCost() {
		return visaBilCost;
	}
	public void setVisaBilCost(String visaBilCost) {
		this.visaBilCost = visaBilCost;
	}
	public String getEntertainmentBilCost() {
		return entertainmentBilCost;
	}
	public void setEntertainmentBilCost(String entertainmentBilCost) {
		this.entertainmentBilCost = entertainmentBilCost;
	}
	public String getCCABilCost() {
		return cCABilCost;
	}
	public void setCCABilCost(String bilCost) {
		cCABilCost = bilCost;
	}
	public String getFlightTrainNonBilCost() {
		return flightTrainNonBilCost;
	}
	public void setFlightTrainNonBilCost(String flightTrainNonBilCost) {
		this.flightTrainNonBilCost = flightTrainNonBilCost;
	}
	public String getHotelNonBilCost() {
		return hotelNonBilCost;
	}
	public void setHotelNonBilCost(String hotelNonBilCost) {
		this.hotelNonBilCost = hotelNonBilCost;
	}
	public String getPerdiemNonBilCost() {
		return perdiemNonBilCost;
	}
	public void setPerdiemNonBilCost(String perdiemNonBilCost) {
		this.perdiemNonBilCost = perdiemNonBilCost;
	}
	public String getOtherNonBilCosts() {
		return otherNonBilCosts;
	}
	public void setOtherNonBilCosts(String otherNonBilCosts) {
		this.otherNonBilCosts = otherNonBilCosts;
	}
	public String getVisaNonBilCost() {
		return visaNonBilCost;
	}
	public void setVisaNonBilCost(String visaNonBilCost) {
		this.visaNonBilCost = visaNonBilCost;
	}
	public String getEntertainmentNonBilCost() {
		return entertainmentNonBilCost;
	}
	public void setEntertainmentNonBilCost(String entertainmentNonBilCost) {
		this.entertainmentNonBilCost = entertainmentNonBilCost;
	}
	public String getCCANonBilCost() {
		return cCANonBilCost;
	}
	public void setCCANonBilCost(String nonBilCost) {
		cCANonBilCost = nonBilCost;
	}
	public String getInsurance() {
		return Insurance;
	}
	public void setInsurance(String insurance) {
		Insurance = insurance;
	}
	public String getCostToString() {
		return costToString;
	}
	public void setCostToString(String costToString) {
		this.costToString = costToString;
	}
	public String getBilCostToString() {
		return bilCostToString;
	}
	public void setBilCostToString(String bilCostToString) {
		this.bilCostToString = bilCostToString;
	}
	public String getNonBilCostToString() {
		return nonBilCostToString;
	}
	public void setNonBilCostToString(String nonBilCostToString) {
		this.nonBilCostToString = nonBilCostToString;
	}
	public boolean isSelected() {
		return isSelected;
	}
	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
	public String getTravelDate() {
		return travelDate;
	}
	public void setTravelDate(String travelDate) {
		this.travelDate = travelDate;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	public String getBoProj() {
		return boProj;
	}
	public void setBoProj(String boProj) {
		this.boProj = boProj;
	}
	public List<DtaHistoryVo> getDtaHistoryVos() {
		return dtaHistoryVos;
	}
	public void setDtaHistoryVos(List<DtaHistoryVo> dtaHistoryVos) {
		this.dtaHistoryVos = dtaHistoryVos;
	}

}
