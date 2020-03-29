

import java.util.Date;

public class TokenRequest {
	private String deviceName;
	//private Date requestDate;
	private String serialNumber;
	private String macAddress;
	private String supportEmail;
	private String driverVersion;
	private String typeOfDevice;
	
	public TokenRequest(String deviceName,String typeOfDevice, String driverVersion,String supportEmail, String serialNumber, String macAddress) {
		this.deviceName = deviceName;
		this.serialNumber = serialNumber;
		this.macAddress = macAddress;
		this.supportEmail = supportEmail;
		this.driverVersion = driverVersion;
		this.typeOfDevice=typeOfDevice;
		
		//this.requestDate = creationDate;
	}
	
	@Override
	public String toString() {
		return "TokenRequest [\\n\\Device Name:" + this.deviceName+ 
				 ",\n\t\\Type of Device:"+this.typeOfDevice 
				 + ",\n\t\\Driver Version:"+ this.driverVersion
				 + ",\n\t\\Support e-mail:" + this.supportEmail 
				 + ",\n\t\\Serial Number:"+this.serialNumber + ",\n\t\\MAC Address:" + this.macAddress+"\n]";
	}
	/*public String toString() {
		return "TokenRequest [\\n\\Device Name=" + this.deviceName + ",\n\t\\Request Date=" + this.requestDate + ",\n\t\\Serial Number="
				+ this.serialNumber + ",\n\t\\MAC Address=" + this.macAddress + "\n]";
	}*/
	
}
