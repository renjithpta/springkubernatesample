package com.ibsplc.itin.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ITIN_EMP_MST")
public class EmployeeDetailsEntity {
	@Id
	@Column(name = "EMP_CDE")
	private Long employeeCode;
	@Column(name = "EMP_ID")
	private String employeeID;
	@Column(name = "EMP_NAM")
	private String employeeName;
	
	@Column(name = "EMP_GEN")
	private String gender;
	
	@Column(name = "EMP_DOJ")
	private Date dateOfJoin;
	@Column(name = "EMP_DOB")
	private Date dob;
	@Column(name = "EMP_IMG_PTH")
	private String empImagePath;
	
	@Column(name = "EMP_STS")
	private Long empStatus;
	@Column(name = "REC_CDE")
	private Long recommenderCode;
	@Column(name = "APP_CDE")
	private Long approverCode;

	@Column(name = "EMP_DPT")
	private String department;
	@Column(name = "PRJ_NAM")
	private String projectName;
	@Column(name = "EMP_CCN_CDE")
	private Long costCenter;
	
	@Column(name = "EMP_GRD")
	private Integer grade;
	@Column(name = "EMP_DSG")
	private Integer designation;
	@Column(name = "EMP_BSE_STN")
	private String baseStation;
	
	@Column(name = "OFF_EMAIL_ID")
	private String officeEmail;
	@Column(name = "EMA_ID")
	private String emailID;
	@Column(name = "MOB_NUM")
	private String mobileNumber;
	
	@Column(name = "PER_EMAIL_ID")
	private String personalEmail;
	@Column(name = "RES_ADD_PRT1")
	private String residenceAddressPart1;
	@Column(name = "RES_ADD_PRT2")
	private String residenceAddressPart2;
	@Column(name = "RES_ADD_PRT3")
	private String residenceAddressPart3;
	@Column(name = "NOM_NAM")
	private String nomineeName;
	
	@Column(name = "NOM_REL")
	private Long nomineeRelation;
	@Column(name = "RES_TEL")
	private String residencePhone;
	@Column(name = "CON_PER_NAM")
	private String contactPersonName;

	@Column(name = "CON_PER_REL")
	private Long relationship;
	@Column(name = "CON_NUM")
	private String contactNumber;
	@Column(name = "ISS_BAN")
	private String issuedBank;

	@Column(name = "ACC_NUM")
	private String accountNumber;
	@Column(name = "IBS_TRA_CRD_NUM")
	private String ibsTravelCardNo;
	@Column(name = "CRD_VAL_FRM")
	private Date cardValidFrom;

	@Column(name = "CRD_VAL_TO")
	private Date cardValidTo;
	@Column(name = "FLT_PRE_MEAL")
	private Long meal;
	@Column(name = "FLT_PRE_SEAT")
	private Long flightSeat;

	@Column(name = "TRN_PRE_SEAT")
	private Long trainSeat;
	@Column(name = "EMP_REM")
	private String remarks;
	@Column(name = "LOG_ROL")
	private Long loginRole;		
	
	@Column(name = "LOG_SCN_CDE")
	private Long loginScreenCode;
	@Column(name = "ONL_ACC")
	private Character onlineAccess;
	@Column(name = "ASS_PA")
	private Long assignPA;
	
	@Column(name = "CRT_USR")
	private Integer createdBy;
	@Column(name = "CRT_DAT")
	private Date createdOn;
	@Column(name = "LST_UPD_USR")
	private Integer updatedBy;
	
	@Column(name = "LST_UPD_DAT")
	private Date updatedOn;
	@Column(name = "DEL_IND")
	private String delIndicator;
	
	@Column(name = "ASS_PA_CDE")
	private Long assignPACode;
	
	@Column(name = "IS_APR")
	private Character myApproval;
	
	@Column(name = "EMP_OFC_NUM")
	private String empOfficeNumber;
	
	@Column(name = "EMP_COM")
	private String empcompany;
	
	@Column(name = "CCT_HOL_NAM") 
	private String costCenterHeadName;
	
	@Column(name = "EMT_CCT_NAM")
	private String emtName;
	@Column(name = "AC_NON_AC_CDE")
	private Long acOrNonAc;
	private transient String employeeIDAndName;
	
	@Column(name = "CON_PER_ADD_PRT1")
	private String contactPersonAddressPart1;
	
	@Column(name = "CON_PER_ADD_PRT2")
	private String contactPersonAddressPart2;
	
	@Column(name = "CON_PER_ADD_PRT3")
	private String contactPersonAddressPart3;
	
	public String getEmployeeIDAndName() {
		return employeeIDAndName;
	}
	public void setEmployeeIDAndName(String employeeIDAndName) {
		this.employeeIDAndName = employeeIDAndName;
	}
	public Character getMyApproval() {
		return myApproval;
	}
	public void setMyApproval(Character myApproval) {
		this.myApproval = myApproval;
	}
	public Long getEmployeeCode() {
		return employeeCode;
	}
	@GeneratedValue(strategy = GenerationType.AUTO)
	public void setEmployeeCode(Long employeeCode) {
		this.employeeCode = employeeCode;
	}
	public String getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public Date getDateOfJoin() {
		return dateOfJoin;
	}
	public void setDateOfJoin(Date dateOfJoin) {
		this.dateOfJoin = dateOfJoin;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getEmpImagePath() {
		return empImagePath;
	}
	public void setEmpImagePath(String empImagePath) {
		this.empImagePath = empImagePath;
	}
	public Long getEmpStatus() {
		return empStatus;
	}
	public void setEmpStatus(Long empStatus) {
		this.empStatus = empStatus;
	}
	public Long getRecommenderCode() {
		return recommenderCode;
	}
	public void setRecommenderCode(Long recommenderCode) {
		this.recommenderCode = recommenderCode;
	}
	public Long getApproverCode() {
		return approverCode;
	}
	public void setApproverCode(Long approverCode) {
		this.approverCode = approverCode;
	}
	
	
	public Long getCostCenter() {
		return costCenter;
	}
	public void setCostCenter(Long costCenter) {
		this.costCenter = costCenter;
	}
	

	public Integer getGrade() {
		return grade;
	}
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	public String getBaseStation() {
		return baseStation;
	}
	public void setBaseStation(String baseStation) {
		this.baseStation = baseStation;
	}
	public String getOfficeEmail() {
		return officeEmail;
	}
	public void setOfficeEmail(String officeEmail) {
		this.officeEmail = officeEmail;
	}
	public String getEmailID() {
		return emailID;
	}
	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}
	
	public String getPersonalEmail() {
		return personalEmail;
	}
	public void setPersonalEmail(String personalEmail) {
		this.personalEmail = personalEmail;
	}
	
	public String getNomineeName() {
		return nomineeName;
	}
	public void setNomineeName(String nomineeName) {
		this.nomineeName = nomineeName;
	}
	public Long getNomineeRelation() {
		return nomineeRelation;
	}
	public void setNomineeRelation(Long nomineeRelation) {
		this.nomineeRelation = nomineeRelation;
	}
	
	public String getContactPersonName() {
		return contactPersonName;
	}
	public void setContactPersonName(String contactPersonName) {
		this.contactPersonName = contactPersonName;
	}
	public Long getRelationship() {
		return relationship;
	}
	public void setRelationship(Long relationship) {
		this.relationship = relationship;
	}
	
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getResidencePhone() {
		return residencePhone;
	}
	public void setResidencePhone(String residencePhone) {
		this.residencePhone = residencePhone;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getEmpOfficeNumber() {
		return empOfficeNumber;
	}
	public void setEmpOfficeNumber(String empOfficeNumber) {
		this.empOfficeNumber = empOfficeNumber;
	}
	public String getEmpcompany() {
		return empcompany;
	}
	public void setEmpcompany(String empcompany) {
		this.empcompany = empcompany;
	}
	public String getIssuedBank() {
		return issuedBank;
	}
	public void setIssuedBank(String issuedBank) {
		this.issuedBank = issuedBank;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getIbsTravelCardNo() {
		return ibsTravelCardNo;
	}
	public void setIbsTravelCardNo(String ibsTravelCardNo) {
		this.ibsTravelCardNo = ibsTravelCardNo;
	}
	public Date getCardValidFrom() {
		return cardValidFrom;
	}
	public void setCardValidFrom(Date cardValidFrom) {
		this.cardValidFrom = cardValidFrom;
	}
	public Date getCardValidTo() {
		return cardValidTo;
	}
	public void setCardValidTo(Date cardValidTo) {
		this.cardValidTo = cardValidTo;
	}
	public Long getMeal() {
		return meal;
	}
	public void setMeal(Long meal) {
		this.meal = meal;
	}
	public Long getFlightSeat() {
		return flightSeat;
	}
	public void setFlightSeat(Long flightSeat) {
		this.flightSeat = flightSeat;
	}
	public Long getTrainSeat() {
		return trainSeat;
	}
	public void setTrainSeat(Long trainSeat) {
		this.trainSeat = trainSeat;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Long getLoginRole() {
		return loginRole;
	}
	public void setLoginRole(Long loginRole) {
		this.loginRole = loginRole;
	}
	public Long getLoginScreenCode() {
		return loginScreenCode;
	}
	public void setLoginScreenCode(Long loginScreenCode) {
		this.loginScreenCode = loginScreenCode;
	}
	public Character getOnlineAccess() {
		return onlineAccess;
	}
	public void setOnlineAccess(Character onlineAccess) {
		this.onlineAccess = onlineAccess;
	}
	public Long getAssignPA() {
		return assignPA;
	}
	public void setAssignPA(Long assignPA) {
		this.assignPA = assignPA;
	}
	
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	
	
	public Integer getDesignation() {
		return designation;
	}
	public void setDesignation(Integer designation) {
		this.designation = designation;
	}
	public Integer getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
	public Integer getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Date getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
	public String getDelIndicator() {
		return delIndicator;
	}
	public void setDelIndicator(String delIndicator) {
		this.delIndicator = delIndicator;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public Long getAssignPACode() {
		return assignPACode;
	}
	public void setAssignPACode(Long assignPACode) {
		this.assignPACode = assignPACode;
	}
	public String getCostCenterHeadName() {
		return costCenterHeadName;
	}
	public void setCostCenterHeadName(String costCenterHeadName) {
		this.costCenterHeadName = costCenterHeadName;
	}
	public String getEmtName() {
		return emtName;
	}
	public void setEmtName(String emtName) {
		this.emtName = emtName;
	}
	public String getResidenceAddressPart1() {
		return residenceAddressPart1;
	}
	public void setResidenceAddressPart1(String residenceAddressPart1) {
		this.residenceAddressPart1 = residenceAddressPart1;
	}
	public String getResidenceAddressPart2() {
		return residenceAddressPart2;
	}
	public void setResidenceAddressPart2(String residenceAddressPart2) {
		this.residenceAddressPart2 = residenceAddressPart2;
	}
	public String getResidenceAddressPart3() {
		return residenceAddressPart3;
	}
	public void setResidenceAddressPart3(String residenceAddressPart3) {
		this.residenceAddressPart3 = residenceAddressPart3;
	}
	public String getContactPersonAddressPart1() {
		return contactPersonAddressPart1;
	}
	public void setContactPersonAddressPart1(String contactPersonAddressPart1) {
		this.contactPersonAddressPart1 = contactPersonAddressPart1;
	}
	public String getContactPersonAddressPart2() {
		return contactPersonAddressPart2;
	}
	public void setContactPersonAddressPart2(String contactPersonAddressPart2) {
		this.contactPersonAddressPart2 = contactPersonAddressPart2;
	}
	public String getContactPersonAddressPart3() {
		return contactPersonAddressPart3;
	}
	public void setContactPersonAddressPart3(String contactPersonAddressPart3) {
		this.contactPersonAddressPart3 = contactPersonAddressPart3;
	}
	public Long getAcOrNonAc() {
		return acOrNonAc;
	}
	public void setAcOrNonAc(Long acOrNonAc) {
		this.acOrNonAc = acOrNonAc;
	}
	
}
