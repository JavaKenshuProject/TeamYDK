package entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;

public class EmployeeBean implements Serializable{

	private String empCode;
	private String lName;
	private String fName;
	private String lKanaName;
	private String fKanaName;
	private byte sex;
	private String birthDay;
	private String sectionCode;
	private String empDate;
	private Timestamp updateDate;
	private Timestamp sectionUpdateDate;
	private String sectionName;
	private ArrayList<String> licenseName;
	private ArrayList<String> licenseCd;
	private String licenseCdSQLinsert;
	private ArrayList<String> getLicenseDate;
	private String getLicenseDateSQLinsert;

	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	public String getLName() {
		return lName;
	}
	public void setLName(String lName) {
		this.lName = lName;
	}
	public String getFName() {
		return fName;
	}
	public void setFName(String fName) {
		this.fName = fName;
	}
	public String getLKanaName() {
		return lKanaName;
	}
	public void setLKanaName(String lKanaName) {
		this.lKanaName = lKanaName;
	}
	public String getFKanaName() {
		return fKanaName;
	}
	public void setFKanaName(String fKanaName) {
		this.fKanaName = fKanaName;
	}
	public byte getSex() {
		return sex;
	}
	public void setSex(byte sex) {
		this.sex = sex;
	}
	public String getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}
	public String getSectionCode() {
		return sectionCode;
	}
	public void setSectionCode(String sectionCode) {
		this.sectionCode = sectionCode;
	}
	public String getEmpDate() {
		return empDate;
	}
	public void setEmpDate(String empDate) {
		this.empDate = empDate;
	}
	public Timestamp getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}
	public Timestamp getSectionUpdateDate() {
		return sectionUpdateDate;
	}
	public void setSectionUpdateDate(Timestamp sectionUpdateDate) {
		this.sectionUpdateDate = sectionUpdateDate;
	}
	public String getSectionName() {
		return sectionName;
	}
	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}
	public ArrayList<String> getLicenseName() {
		return licenseName;
	}
	public void setLicenseName(ArrayList<String> licenseName) {
		this.licenseName = licenseName;
	}
	public ArrayList<String> getLicenseCd() {
		return licenseCd;
	}
	public void setLicenseCd(ArrayList<String> licenseCd) {
		this.licenseCd = licenseCd;
	}
	public String getLicenseCdSQLinsert() {
		return licenseCdSQLinsert;
	}
	public void setLicenseCdSQLinsert(String licenseCdSQLinsert) {
		this.licenseCdSQLinsert = licenseCdSQLinsert;
	}
	public ArrayList<String> getGetLicenseDate() {
		return getLicenseDate;
	}
	public void setGetLicenseDate(ArrayList<String> getLicenseDate) {
		this.getLicenseDate = getLicenseDate;
	}
	public String getGetLicenseDateSQLinsert() {
		return getLicenseDateSQLinsert;
	}
	public void setGetLicenseDateSQLinsert(String getLicenseDateSQLinsert) {
		this.getLicenseDateSQLinsert = getLicenseDateSQLinsert;
	}

}
