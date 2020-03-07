////////////////////////////////////////////////////////////////////
// checkstyle:
// Checks Java source code for adherence to a set of rules.
// Copyright (C) 2020 Jiajia Ye Jiawang He
////////////////////////////////////////////////////////////////////

package Transport4Future.TokenManagement;

import java.util.Date;

public class TokenRequest {
	
	private String deviceName;
	private Date requestDate;
	private String serialNumber;
	private String macAddress;
	private String typeOfDevice ;
	private String driverVersion ;
	private String supportEmail ;
	
	
	public TokenRequest(String deviceName, Date creationDate, String serialNumber, String macAddress) {
		this.deviceName = deviceName;
		this.requestDate = creationDate;
		this.serialNumber = serialNumber;
		this.macAddress = macAddress;
	}
	
	@Override
	public String toString() {
		return "TokenRequest [\\n\\Device Name=" + this.deviceName + ",\n\t\\Request Date=" + 
		    this.requestDate + ",\n\t\\Serial Number="
				+ this.serialNumber + ",\n\t\\MAC Address=" + this.macAddress + "\n]";
	}

  public String getDriverVersion() {
    return driverVersion;
  }

  public void setDriverVersion(String driverVersion) {
    this.driverVersion = driverVersion;
  }

  public String getTypeOfDevice() {
    return typeOfDevice;
  }

  public void setTypeOfDevice(String typeOfDevice) {
    this.typeOfDevice = typeOfDevice;
  }

  public String getSupportEmail() {
    return supportEmail;
  }

  public void setSupportEmail(String supportEmail) {
    this.supportEmail = supportEmail;
  }
}
