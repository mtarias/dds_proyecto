package cl.puc.dcc.iic2113.persistencia;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


class Local implements IPersistencia {

	
	public Local()
	{
	}

	@Override
	public boolean Guardar(Token token) {
		try
	      {
	         FileOutputStream fileOut =
	         new FileOutputStream(token.getDigest().toString());
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(token);
	         out.close();
	         fileOut.close();
	         return true;
	      }catch(IOException i)
	      {
	          i.printStackTrace();
	          return false;
	      }
	}

	@Override
	public Token RecuperarPorDigest(byte[] digest) {
		Token t = null;
		try{
            FileInputStream fileIn =
                          new FileInputStream(digest.toString());
            ObjectInputStream in = new ObjectInputStream(fileIn);
            t = (Token) in.readObject();
            in.close();
            fileIn.close();
        }catch(IOException i)
        {
            i.printStackTrace();
            return null;
        }catch(ClassNotFoundException i)
        {}
        return t;
	}

	@Override
	public TipoPersistencia MiTipo() {
		return TipoPersistencia.LOCAL;
	}

}
