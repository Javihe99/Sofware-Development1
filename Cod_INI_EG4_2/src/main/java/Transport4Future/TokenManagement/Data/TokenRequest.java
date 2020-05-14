package Transport4Future.TokenManagement.Data;

import java.util.HashMap;
import java.util.regex.Pattern;

import Transport4Future.TokenManagement.Data.Attribute.DeviceName;
import Transport4Future.TokenManagement.Data.Attribute.DriverVersion;
import Transport4Future.TokenManagement.Data.Attribute.Email;
import Transport4Future.TokenManagement.Data.Attribute.MacAddress;
import Transport4Future.TokenManagement.Data.Attribute.SerialNumber;
import Transport4Future.TokenManagement.Data.Attribute.TypeOfDevice;
import Transport4Future.TokenManagement.Exception.TokenManagementException;
import Transport4Future.TokenManagement.Parser.JSONTokenRequestParser;
import Transport4Future.TokenManagement.Store.TokenRequestStore;
import Transport4Future.TokenManagement.Utils.MDHasher;

public class TokenRequest {
	
	private DeviceName deviceName;
	private TypeOfDevice typeOfDevice;
	private DriverVersion driverVersion;
	private Email supportEMail;
	private SerialNumber serialNumber;
	private MacAddress macAddress;
	private String hash;
		
	public TokenRequest(String InputFile) throws TokenManagementException {
		
		HashMap<String, String> myMap = new HashMap<String, String> ();
		JSONTokenRequestParser myFile = new JSONTokenRequestParser();
		myMap = (HashMap) myFile.createTokenRequest(InputFile);
		
		this.deviceName = new DeviceName(myMap.get("Device Name"));
		this.typeOfDevice = new TypeOfDevice(myMap.get("Type of Device"));
		this.driverVersion = new DriverVersion(myMap.get("Driver Version"));
		this.supportEMail = new Email(myMap.get("Support e-mail"));
		this.serialNumber = new SerialNumber(myMap.get("Serial Number"));
		this.macAddress = new MacAddress(myMap.get("MAC Address"));
		this.hash=generateHash();
		Store();
	
	}
	private void Store() throws TokenManagementException {
		TokenRequestStore myStore =new TokenRequestStore();
        myStore.storeTokenRequest(this, this.hash);
	}
	
	private String generateHash() throws TokenManagementException {
		
		MDHasher myHash = new  MDHasher();
		String hex = myHash.Hash(this.toString());
		return hex;
	}
	public String getDeviceName() {
		return deviceName.getValue();
	}
	public String getHash() {
		return this.hash;
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
