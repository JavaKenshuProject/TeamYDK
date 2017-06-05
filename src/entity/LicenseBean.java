package entity;

import java.io.Serializable;

public class LicenseBean implements Serializable{

	private String licenseCd;
	private String licenseName;

	public String getLicenseCd() {
		return licenseCd;
	}
	public void setLicenseCd(String licenseCd) {
		this.licenseCd = licenseCd;
	}
	public String getLicenseName() {
		return licenseName;
	}
	public void setLicenseName(String licenseName) {
		this.licenseName = licenseName;
	}

}
