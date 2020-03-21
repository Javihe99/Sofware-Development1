package Transport4Future.TokenManagement;

import java.util.Date;

public class TokenRequest {
	private String deviceName;
	//private Date requestDate;
	private String serialNumber;
	private String macAddress;
	private String supportEmail;
	private String driverVersion;
	private String typeOfDevice;
	
	public TokenRequest(String deviceName, String serialNumber, String macAddress,String supportEmail, String driverVersion,String typeOfDevice) {
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
		return "TokenRequest [\\n\\Device Name=" + this.deviceName + this.serialNumber + ",\n\t\\MAC Address=" + this.macAddress +  
				 ",\n\t\\Support e-mail=" + this.supportEmail + ",\n\t\\Driver Version="+ this.driverVersion+ 
				 ",\n\t\\Type of Device="+this.typeOfDevice +"\n]";
	}
	/*public String toString() {
		return "TokenRequest [\\n\\Device Name=" + this.deviceName + ",\n\t\\Request Date=" + this.requestDate + ",\n\t\\Serial Number="
				+ this.serialNumber + ",\n\t\\MAC Address=" + this.macAddress + "\n]";
	}*/
	
}
