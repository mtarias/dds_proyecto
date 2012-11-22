import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.result.ResultParser;
import com.google.zxing.common.HybridBinarizer;

public class QRReader implements Resource{

	private int id;
	List<IConsuptionObs> observers = new ArrayList<IConsuptionObs>();
	
	@Override
	public boolean reciveAction(String[] data, int num) {
		read(data);
		return true;
	}
	
	private void read(String[] data){

		Result result = null;
		BinaryBitmap binaryBitmap;

		try{
			FileInputStream file = new FileInputStream(data[0]);
			binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(ImageIO.read(file))));

			result = new MultiFormatReader().decode(binaryBitmap);
			
			ResultParser.parseResult(result).getType().toString();
			
			QRCode code = new QRCode(result.getText(), ResultParser.parseResult(result).getType().toString());

			//Fire the event with the QRCode
			
			notifyAllObservers(code);

		}catch(Exception ex){

			notifyAllObservers(null);
		}

	}

    // This private class is used to fire MyEvents
    void notifyAllObservers(QRCode code) {
    	Iterator<IConsuptionObs> it= observers.iterator();

        while(it.hasNext())
        {
        	QRObserver o = (QRObserver)it.next();
        	o.update(code);
        }
    }

	@Override
	public void setObserver(IConsuptionObs observer) {
		// TODO Auto-generated method stub
		observers.add(observer);
	}

	@Override
	public boolean isAvailable() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void cancelConsuption() {
		// TODO Auto-generated method stub
		return;
	}

	@Override
	public int getStatus() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setId(int num) {
		// TODO Auto-generated method stub
		id = num;
	}
}
