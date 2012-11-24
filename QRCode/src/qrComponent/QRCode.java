package qrComponent;

public class QRCode {
	private String text;
	private String type;
	
	public QRCode(String text, String type){
		this.text = text;
		this.type = type;
	}

	public String getType() {
		return type;
	}
	

	public String getText() {
		return text;
		
	}
	
	
}
