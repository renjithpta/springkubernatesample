package com.ibsplc.itin.entity;

import java.util.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.log4j.Logger;

@Entity
@Table(name = "ITIN_DTA_APP_DTL")
public class DTAApplicationDetailsEntity {

	@Id
	@Column(name = "DTA_CDE")
	private int dtaNumber;

	@Column(name = "DTA_ID")
	private String dtaId;

	@Column(name = "EMP_CDE")
	private String employeeId;

	@Column(name = "EMP_GRD")
	private String grade;

	@Column(name = "EMP_DSG")
	private String designation;

	@Column(name = "EMP_PRJ")
	private String project;

	@Column(name = "TVL_CC_CDE")
	private String ccCode;

	@Column(name = "TVL_BO")
	private String bo;
	
	@Column(name= "TVL_TTL_AMT")
	private String totalAmount;

	@Column(name = "TVL_LOB")
	private String lob;

	@Column(name = "TYP_TRV")
	private String travelType;

	@Column(name = "TVL_PRI")
	private String priority;

	@Column(name = "PUR_TRAVEL")
	private String travelPurpose;

	@Column(name = "DTA_STS")
	private Integer status;

	@Column(name = "DTA_SUB_STS")
	private Integer dtaSubSts;

	@Column(name = "DTA_ACN_BY")
	private Integer dtaAcnBy;

	@Column(name = "CMT")
	private String comments;

	@Column(name = "CRT_USR")
	private String createdBy;

	@Column(name = "CRT_DAT")
	private Date createdOn;

	@Column(name = "LST_UPD_USR")
	private String updatedBy;

	@Column(name = "LST_UPD_DAT")
	private Date updatedOn;

	@Column(name = "FST_LEG_ORG")
	private String firstLegOrigin;

	@Column(name = "FST_LEG_DST")
	private String firstLegDestination;

	@Column(name = "FST_LEG_TVL_DAT")
	private Date firstLegTravelDate;

	@Column(name = "CAn_emp_est")
	private String canEmployeeEstimate;

	@Column(name = "can_emp_qte_sel")
	private String canEmployeeSelectQuote;

	@Column(name = "DTA_REQ_TYP")
	private String dtaReqType;

	@Column(name = "LCK_EMP_CDE")
	private Integer lockByEmpCode;

	@Column(name = "DTA_APP_DAT")
	private Date dtaApplnDate;

	@Column(name = "DTA_APR_DAT")
	private Date dtaApprovedDate;

	@Column(name = "DTA_LST_APR_EMP")
	private Integer dtaLastApprovedEmpCode;

	@Column(name = "DTA_CLM_DAT")
	private Date dtaClaimedDate;

	@Column(name = "DTA_CLS_DAT")
	private Date dtaClosedDate;

	@Column(name = "LST_LEG_DEP_DAT")
	private Date dtaLastLegDepDate;

	@Column(name = "APR_USD_CST")
	private String aprvdTotCostUsd;

	@Column(name = "BUD_AVL")
	private String budgetAvail;

	@Column(name = "IFIN_ACC_ID")
	private String ifinAccId;

	@Column(name = "IFIN_CC_ID")
	private String ifinCcId;

	@Column(name = "CFO_APR_REQ")
	private String cfoAppReq;

	@Column(name = "ANY_EXP")
	private String otherExp;

	@Column(name = "IS_INS_REQ")
	private String insuranceRequired;

	@Column(name = "ERP_PRJ_CDE")
	private String projCde;

	@Column(name = "QTE_FOR_APR")
	private String qteFrApr;
	@Column(name = "BOOK_TIK")
	private String bookTicket;
	@Column(name = "TVL_TNTVE")
	private String travelTentative;
	@Column(name = "LST_LEG_AVL_DAT")
	private Date lastLegArrivalDate;
	@Column(name = "IS_GST_TRL")
	private String isGuestTravel;
	@Column(name = "IS_CLM_CLS")
	private String isClaimClosed;

	@Column(name = "IS_CLM_RSD")
	private String isClaimRaised;
	
	@Column(name = "IS_RTN_TVL")
	private String isReturnTravel;
	
	@Column(name = "IS_RCH_CST")
	private String rechargeCustomer;
	
	@Column(name = "INV_NO")
	private String invoiceNo;
	
	@Column(name = "TVL_CAT")
	private Integer travelCat; 
	
	@Column(name = "NO_DYS")
	private Integer noofdays; 
	
	private final static Logger LOGGER = Logger
			.getLogger(DTAApplicationDetailsEntity.class);

	public int getLockByEmpCode() {
		return lockByEmpCode;
	}

	public void setLockByEmpCode(int lockByEmpCode) {
		this.lockByEmpCode = lockByEmpCode;
	}

	public Date getDtaApplnDate() {
		return dtaApplnDate;
	}

	public void setDtaApplnDate(Date dtaApplnDate) {
		this.dtaApplnDate = dtaApplnDate;
	}

	public Date getDtaApprovedDate() {
		return dtaApprovedDate;
	}

	public void setDtaApprovedDate(Date dtaApprovedDate) {
		this.dtaApprovedDate = dtaApprovedDate;
	}

	public int getDtaLastApprovedEmpCode() {
		return dtaLastApprovedEmpCode;
	}

	public void setDtaLastApprovedEmpCode(int dtaLastApprovedEmpCode) {
		this.dtaLastApprovedEmpCode = dtaLastApprovedEmpCode;
	}

	public Date getDtaClaimedDate() {
		return dtaClaimedDate;
	}

	public void setDtaClaimedDate(Date dtaClaimedDate) {
		this.dtaClaimedDate = dtaClaimedDate;
	}

	public Date getDtaClosedDate() {
		return dtaClosedDate;
	}

	public void setDtaClosedDate(Date dtaClosedDate) {
		this.dtaClosedDate = dtaClosedDate;
	}

	public String getFirstLegOrigin() {
		return firstLegOrigin;
	}

	public void setFirstLegOrigin(String firstLegOrigin) {
		this.firstLegOrigin = firstLegOrigin;
	}

	public String getFirstLegDestination() {
		return firstLegDestination;
	}

	public void setFirstLegDestination(String firstLegDestination) {
		this.firstLegDestination = firstLegDestination;
	}

	public String getCanEmployeeEstimate() {
		return canEmployeeEstimate;
	}

	public void setCanEmployeeEstimate(String canEmployeeEstimate) {
		this.canEmployeeEstimate = canEmployeeEstimate;
	}

	public String getCanEmployeeSelectQuote() {
		return canEmployeeSelectQuote;
	}

	public void setCanEmployeeSelectQuote(String canEmployeeSelectQuote) {
		this.canEmployeeSelectQuote = canEmployeeSelectQuote;
	}

	public String getDtaReqType() {
		return dtaReqType;
	}

	public void setDtaReqType(String dtaReqType) {
		this.dtaReqType = dtaReqType;
	}

	public Date getFirstLegTravelDate() {
		return firstLegTravelDate;
	}

	public void setFirstLegTravelDate(Date firstLegTravelDate) {
		this.firstLegTravelDate = firstLegTravelDate;
	}

	public int getDtaNumber() {
		return dtaNumber;
	}

	public void setDtaNumber(int dtaNumber) {
		this.dtaNumber = dtaNumber;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getCcCode() {
		return ccCode;
	}

	public void setCcCode(String ccCode) {
		this.ccCode = ccCode;
	}

	public String getBo() {
		return bo;
	}

	public void setBo(String bo) {
		this.bo = bo;
	}

	public String getLob() {
		return lob;
	}

	public void setLob(String lob) {
		this.lob = lob;
	}

	public String getTravelType() {
		return travelType;
	}

	public void setTravelType(String travelType) {
		this.travelType = travelType;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getTravelPurpose() {
		return travelPurpose;
	}

	public void setTravelPurpose(String travelPurpose) {
		this.travelPurpose = travelPurpose;
	}

	public Integer getStatus() {
		return status;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public int getDtaSubSts() {
		return dtaSubSts;
	}

	public void setDtaSubSts(int dtaSubSts) {
		this.dtaSubSts = dtaSubSts;
	}

	public int getDtaAcnBy() {
		return dtaAcnBy;
	}

	public void setDtaAcnBy(int dtaAcnBy) {
		this.dtaAcnBy = dtaAcnBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getDtaLastLegDepDate() {
		return dtaLastLegDepDate;
	}

	public void setDtaLastLegDepDate(Date dtaLastLegDepDate) {
		this.dtaLastLegDepDate = dtaLastLegDepDate;
	}

	public String toString() {
		String tmp = " dtaNumber = " + dtaNumber + " , employeeId = "
				+ employeeId + " grade = " + grade + " designation = "
				+ designation;
		tmp += "project = " + project + " ccCode = " + ccCode + " bo = " + bo
				+ " lob = " + lob + " travelType = " + travelType;
		tmp += " priority = " + priority + " travelPurpose = " + travelPurpose
				+ " status = " + status + " dtaSubSts = " + dtaSubSts;
		tmp += " dtaAcnBy = " + dtaAcnBy + " comments = " + comments
				+ " createdBy = " + createdBy + " createdOn = " + createdOn;
		tmp += " updatedBy = " + updatedBy + " updatedOn = " + updatedOn
				+ " firstLegOrigin = " + firstLegOrigin;
		tmp += " firstLegDestination = " + firstLegDestination
				+ " firstLegTravelDate = " + firstLegTravelDate;
		tmp += "canEmployeeEstimate = " + canEmployeeEstimate
				+ " canEmployeeSelectQuote = " + canEmployeeSelectQuote
				+ " dtaReqType = " + dtaReqType;
		return tmp;
	}

	public String getAprvdTotCostUsd() {
		return aprvdTotCostUsd;
	}

	public void setAprvdTotCostUsd(String aprvdTotCostUsd) {
		this.aprvdTotCostUsd = aprvdTotCostUsd;
	}

	public String getBudgetAvail() {
		return budgetAvail;
	}

	public void setBudgetAvail(String budgetAvail) {
		this.budgetAvail = budgetAvail;
	}

	public String getIfinAccId() {
		return ifinAccId;
	}

	public void setIfinAccId(String ifinAccId) {
		this.ifinAccId = ifinAccId;
	}

	public String getIfinCcId() {
		return ifinCcId;
	}

	public void setIfinCcId(String ifinCcId) {
		this.ifinCcId = ifinCcId;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setDtaSubSts(Integer dtaSubSts) {
		this.dtaSubSts = dtaSubSts;
	}

	public void setDtaAcnBy(Integer dtaAcnBy) {
		this.dtaAcnBy = dtaAcnBy;
	}

	public void setLockByEmpCode(Integer lockByEmpCode) {
		this.lockByEmpCode = lockByEmpCode;
	}

	public void setDtaLastApprovedEmpCode(Integer dtaLastApprovedEmpCode) {
		this.dtaLastApprovedEmpCode = dtaLastApprovedEmpCode;
	}

	public String getDtaId() {
		return dtaId;
	}

	public void setDtaId(String dtaId) {
		this.dtaId = dtaId;
	}

	public String getCfoAppReq() {
		return cfoAppReq;
	}

	public void setCfoAppReq(String cfoAppReq) {
		this.cfoAppReq = cfoAppReq;
	}

	public String getOtherExp() {
		return otherExp;
	}

	public void setOtherExp(String otherExp) {
		this.otherExp = otherExp;
	}

	public String getInsuranceRequired() {
		return insuranceRequired;
	}

	public void setInsuranceRequired(String insuranceRequired) {
		this.insuranceRequired = insuranceRequired;
	}

	public String getProjCde() {
		return projCde;
	}

	public void setProjCde(String projCde) {
		this.projCde = projCde;
	}

	public String getQteFrApr() {
		return qteFrApr;
	}

	public void setQteFrApr(String qteFrApr) {
		this.qteFrApr = qteFrApr;
	}

	public String getBookTicket() {
		return bookTicket;
	}

	public void setBookTicket(String bookTicket) {
		this.bookTicket = bookTicket;
	}

	public String getTravelTentative() {
		return travelTentative;
	}

	public void setTravelTentative(String travelTentative) {
		this.travelTentative = travelTentative;
	}

	public Date getLastLegArrivalDate() {
		return lastLegArrivalDate;
	}

	public void setLastLegArrivalDate(Date lastLegArrivalDate) {
		this.lastLegArrivalDate = lastLegArrivalDate;
	}

	public String getIsGuestTravel() {
		return isGuestTravel;
	}

	public void setIsGuestTravel(String isGuestTravel) {
		this.isGuestTravel = isGuestTravel;
	}

	public String getIsClaimClosed() {
		return isClaimClosed;
	}

	public void setIsClaimClosed(String isClaimClosed) {
		this.isClaimClosed = isClaimClosed;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getIsClaimRaised() {
		return isClaimRaised;
	}

	public void setIsClaimRaised(String isClaimRaised) {
		this.isClaimRaised = isClaimRaised;
	}

	public String getIsReturnTravel() {
		return isReturnTravel;
	}

	public void setIsReturnTravel(String isReturnTravel) {
		this.isReturnTravel = isReturnTravel;
	}

	public String getRechargeCustomer() {
		return rechargeCustomer;
	}

	public void setRechargeCustomer(String rechargeCustomer) {
		this.rechargeCustomer = rechargeCustomer;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public Integer getTravelCat() {
		return travelCat;
	}

	public void setTravelCat(Integer travelCat) {
		this.travelCat = travelCat;
	}
	public Integer getNoofDays() {
		return noofdays;
	}

	public void setNoofDays(Integer noofdays) {
		this.noofdays = noofdays;
	}



}
