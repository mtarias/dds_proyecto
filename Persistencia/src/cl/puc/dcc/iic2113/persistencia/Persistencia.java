package cl.puc.dcc.iic2113.persistencia;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

public class Persistencia{

	IPersistencia miPersistencia;
	MessageDigest md5;
	//Hashtable que contiene digest del archivo y forma de almacenamiento
	private Hashtable<byte[],IPersistencia> seguimientoArchivos;
	//Hashtable que contiene usersId y digest de todos sus tokens
	private Hashtable<String, List<byte[]>> digestsPorUsuario;
	//Hashtable que contiene deviceId y digest de todos sus tokens
	private Hashtable<String, List<byte[]>> digestsPorDispositivo;
	//Memento de los estados de token disponibles
	private TokenMemory tokenMemory;
	
	public Persistencia(IPersistencia persistencia)
	{
		tokenMemory = new TokenMemory();
		CambiarModoPersistencia(persistencia);
		seguimientoArchivos = new Hashtable<byte[],IPersistencia>();
		digestsPorUsuario = new Hashtable<String, List<byte[]>>();
		digestsPorDispositivo = new Hashtable<String, List<byte[]>>();
		
		try
		{
			md5  = MessageDigest.getInstance("MD5");
		}
		catch(Exception e)
		{}
	}
	
	public void CambiarModoPersistencia(IPersistencia persistencia)
	{	
		miPersistencia = persistencia;
	}
	
	public byte[] Guardar(String userId, String deviceId, String message)
	{
		String filename = userId + deviceId + new Date();
		byte[] myDigest = CalcularDigest(filename);
		Token t = new Token(userId, deviceId, message, myDigest);
		seguimientoArchivos.put(myDigest, miPersistencia);
		
		if(digestsPorUsuario.get(userId) == null)
			digestsPorUsuario.put(userId, new ArrayList<byte[]>());
		digestsPorUsuario.get(userId).add(myDigest);
		
		if(digestsPorDispositivo.get(deviceId) == null)
			digestsPorDispositivo.put(deviceId, new ArrayList<byte[]>());
		digestsPorDispositivo.get(deviceId).add(myDigest);
		
		if(miPersistencia.Guardar(t))
		{
			tokenMemory.AppendToken(t);
			return myDigest;
		}
		else
			return null;
	}
	
	public Token Recuperar(byte[] digest)
	{
		//Vemos como se guardo el archivo
		if(!seguimientoArchivos.containsKey(digest))
			return new Token("ERROR","ERROR","ERROR", digest);
		IPersistencia ip = seguimientoArchivos.get(digest);
		return ip.RecuperarPorDigest(digest);
		
	}
	
	public Token RecuperarUltimoMensaje()
	{
		return tokenMemory.getPreviousToken();
	}
	
	public String[] RecuperarPorUsuario(String userId)
	{
		List<byte[]> digests = digestsPorUsuario.get(userId);
		if(digests == null)
			return null;
		
		ArrayList<String> messages = new ArrayList<String>();
		
		for(int i = 0; i < digests.size(); i++)
		{
			messages.add(Recuperar(digests.get(i)).getMessage());
		}
		
		String[] messageArray = new String[messages.size()];
		return messages.toArray(messageArray);
	}
	public String[] RecuperarPorDispositivo(String deviceId)
	{
		List<byte[]> digests = digestsPorDispositivo.get(deviceId);
		if(digests == null)
			return null;
		
		ArrayList<String> messages = new ArrayList<String>();
		
		for(int i = 0; i < digests.size(); i++)
		{
			messages.add(Recuperar(digests.get(i)).getMessage());
		}
		
		String[] messageArray = new String[messages.size()];
		return messages.toArray(messageArray);
	}
	
	private byte[] CalcularDigest(String filename)
	{
		try
		{
			byte[] messageInBytes = filename.getBytes("UTF-8");
	        return md5.digest(messageInBytes);
		}
		catch(Exception e)
		{
			return null;
		}
	}
	

}
