package Transport4Future.TokenManagement.Data;

import java.util.regex.Pattern;

import Transport4Future.TokenManagement.Data.Attribute.DeviceName;
import Transport4Future.TokenManagement.Data.Attribute.DriverVersion;
import Transport4Future.TokenManagement.Data.Attribute.Email;
import Transport4Future.TokenManagement.Data.Attribute.MacAddress;
import Transport4Future.TokenManagement.Data.Attribute.SerialNumber;
import Transport4Future.TokenManagement.Data.Attribute.TypeOfDevice;
import Transport4Future.TokenManagement.Exception.TokenManagementException;

public class TokenRequest {
	
	private DeviceName deviceName;
	private TypeOfDevice typeOfDevice;
	private DriverVersion driverVersion;
	private Email supportEMail;
	private SerialNumber serialNumber;
	private MacAddress macAddress;
		
	public TokenRequest(String deviceName, String typeOfDevice, String driverVersion, String supportEMail, String serialNumber, String macAddress) throws TokenManagementException {
		this.deviceName = new DeviceName(deviceName);
		this.typeOfDevice = new TypeOfDevice(typeOfDevice);
		this.driverVersion = new DriverVersion(driverVersion);
		this.supportEMail = new Email(supportEMail);
		this.serialNumber = new SerialNumber(serialNumber);
		this.macAddress = new MacAddress(macAddress);
	
	}
	

	public String getDeviceName() {
		return deviceName.getValue();
	}

	public String getTypeOfDevice() {
		return typeOfDevice.getValue();
	}

	public String getDriverVersion() {
		return driverVersion.getValue();
	}

	public String getSupportEMail() {
		return supportEMail.getValue();
	}

	public String getSerialNumber() {
		return serialNumber.getValue();
	}

	public String getMacAddress() {
		return macAddress.getValue();
	}
	
	@Override
	public String toString() {
		return "TokenRequest [\\n\\Device Name=" + this.getDeviceName() +
				",\n\t\\Type of Device=" + this.getTypeOfDevice() +
				",\n\t\\Driver Version=" + this.getDriverVersion() +	
				",\n\t\\Support e-Mail=" + this.getSupportEMail() +	
				",\n\t\\Serial Number=" + this.getSerialNumber() +
				",\n\t\\MAC Address=" + this.getMacAddress() + "\n]";
	}
}
