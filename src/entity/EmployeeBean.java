package entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;

public class EmployeeBean implements Serializable{

	private String emp_code;
	private String l_name;
	private String f_name;
	private String l_kana_name;
	private String f_kana_name;
	private byte sex;
	private Date birth_day;
	private String section_code;
	private Date emp_date;
	private Timestamp update_date;
	private Timestamp section_update_date;
	private String section_name;
	private ArrayList<String> license_name;
	private ArrayList<String> license_cd;
	private String license_cd_SQLinsert;
	private ArrayList<String> get_license_date;
	private Date get_license_SQLinsert;

	public String getEmp_code() {
		return emp_code;
	}
	public void setEmp_code(String emp_code) {
		this.emp_code = emp_code;
	}
	public String getL_name() {
		return l_name;
	}
	public void setL_name(String l_name) {
		this.l_name = l_name;
	}
	public String getF_name() {
		return f_name;
	}
	public void setF_name(String f_name) {
		this.f_name = f_name;
	}
	public String getL_kana_name() {
		return l_kana_name;
	}
	public void setL_kana_name(String l_kana_name) {
		this.l_kana_name = l_kana_name;
	}
	public String getF_kana_name() {
		return f_kana_name;
	}
	public void setF_kana_name(String f_kana_name) {
		this.f_kana_name = f_kana_name;
	}
	public byte getSex() {
		return sex;
	}
	public void setSex(byte sex) {
		this.sex = sex;
	}
	public Date getBirth_day() {
		return birth_day;
	}
	public void setBirth_day(Date birth_day) {
		this.birth_day = birth_day;
	}
	public String getSection_code() {
		return section_code;
	}
	public void setSection_code(String section_code) {
		this.section_code = section_code;
	}
	public Date getEmp_date() {
		return emp_date;
	}
	public void setEmp_date(Date emp_date) {
		this.emp_date = emp_date;
	}
	public Timestamp getUpdate_date() {
		return update_date;
	}
	public void setUpdate_date(Timestamp update_date) {
		this.update_date = update_date;
	}
	public Timestamp getSection_update_date() {
		return section_update_date;
	}
	public void setSection_update_date(Timestamp section_update_date) {
		this.section_update_date = section_update_date;
	}
	public String getSection_name() {
		return section_name;
	}
	public void setSection_name(String section_name) {
		this.section_name = section_name;
	}
	public ArrayList<String> getLicense_name() {
		return license_name;
	}
	public void setLicense_name(ArrayList<String> license_name) {
		this.license_name = license_name;
	}
	public ArrayList<String> getLicense_cd() {
		return license_cd;
	}
	public void setLicense_cd(ArrayList<String> license_cd) {
		this.license_cd = license_cd;
	}
	public String getLicense_cd_SQLinsert() {
		return license_cd_SQLinsert;
	}
	public void setLicense_cd_SQLinsert(String license_cd_SQLinsert) {
		this.license_cd_SQLinsert = license_cd_SQLinsert;
	}
	public ArrayList<String> getGet_license_date() {
		return get_license_date;
	}
	public void setGet_license_date(ArrayList<String> get_license_date) {
		this.get_license_date = get_license_date;
	}
	public Date getGet_license_SQLinsert() {
		return get_license_SQLinsert;
	}
	public void setGet_license_SQLinsert(Date get_license_SQLinsert) {
		this.get_license_SQLinsert = get_license_SQLinsert;
	}
}
