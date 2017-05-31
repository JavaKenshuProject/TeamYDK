package entity;

import java.io.Serializable;

public class LicenseBean implements Serializable{

	private String license_cd;
	private String license_name;

	public String getLicense_cd() {
		return license_cd;
	}
	public void setLicense_cd(String license_cd) {
		this.license_cd = license_cd;
	}
	public String getLicense_name() {
		return license_name;
	}
	public void setLicense_name(String license_name) {
		this.license_name = license_name;
	}

}
