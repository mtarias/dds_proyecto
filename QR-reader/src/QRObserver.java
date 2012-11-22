
public class QRObserver implements IConsuptionObs{

	QRCode code;
	
	@Override
	public void update(Object obj) {
		// TODO Auto-generated method stub
		if (obj != null)
			code = (QRCode) obj;
	}
	
	public QRCode getQRCode(){
		return code;
	}

}
