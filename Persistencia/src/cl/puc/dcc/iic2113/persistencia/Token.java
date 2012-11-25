package cl.puc.dcc.iic2113.persistencia;

import java.io.Serializable;

public class Token implements Serializable{


	private static final long serialVersionUID = 1L;
	private String userId, deviceId, message;
	private byte[] myDigest;
	
	public Token(String userId, String deviceId, String message, byte[] myDigest)
	{
		this.userId = userId;
		this.deviceId = deviceId;
		this.message = message;
		this.myDigest = myDigest;
	}

	public byte[] getDigest(){
		return myDigest;
	}
	public String getUserId() {
		return userId;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public String getMessage() {
		return message;
	}
	
	
}
