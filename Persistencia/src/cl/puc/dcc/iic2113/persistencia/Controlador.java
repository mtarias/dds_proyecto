package cl.puc.dcc.iic2113.persistencia;

import java.util.Hashtable;

import cl.puc.dcc.iic2113.persistencia.*;


public class Controlador{
	private Persistencia persistencia;
			
	private TipoPersistencia persistenciaActual;
	
	/**
	 * Constructor de la clase. Por defecto, se inicia con
	 * persistencia Local. 
	 */
	 
	public Controlador()
	{
		persistencia = new Persistencia(new Local());
	}
	
	/**
	 * Cambia la persistencia a modo Local
	 */
	public void CambiarPersistenciaLocal()
	{
		persistenciaActual = TipoPersistencia.LOCAL;
		persistencia.CambiarModoPersistencia(new Local());
	}
	/*
	 * Cambia la persistencia a modo Distribuido
	 
	public void CambiarPersistenciaDistribuido()
	{
		persistenciaActual = TipoPersistencia.DISTRIBUIDO;
		persistencia.CambiarModoPersistencia(new Distribuido());
	}*/
	
	/**
	 * Cambia la persistencia a modo remoto (usando Dropbox)
	 */
	public void CambiarPersistenciaRemoto()
	{
		persistenciaActual = TipoPersistencia.REMOTO;
		persistencia.CambiarModoPersistencia(new Remoto());
	}
	
	/**
	 * Retorna un digest que permite identificar al mensaje guardado.
	 * El mensaje se guarda acorde al modo de persistencia escogido.
	 * 
	 * @param userId id del usuario en la aplicacion
	 * @param deviceId id del dispositivo en la aplicacion
	 * @param message mensaje a almacenar
	 * @return digest identificador del mensaje
	 * @see CambiarPersistenciaLocal
	 * @see CambiarPersistenciaRemoto
	 * @see CambiarPersistenciaDistribuido
	 */
	public byte[] Guardar(String userId, String deviceId, String message){
		return persistencia.Guardar(userId, deviceId, message);
	}
	/**
	 * Retorna el mensaje correspondiente al digest entregado.
	 * @param digest Id del mensaje a recuperar
	 * @return mensaje guardado
	 */
	public String RecuperarPorDigest(byte[] digest)
	{
		Token t = persistencia.Recuperar(digest);
		return t.getMessage();
	}
	
	/**
	 * Retorna todos los mensajes de la aplicación 
	 * correspondientes al usuario identificado en 
	 * el parametro.
	 * @param userId Identifcador de usuario
	 * @return Arreglo con todos los mensajes de ese usuario
	 */
	public String[] RecuperarPorUsuario(String userId){
		return persistencia.RecuperarPorUsuario(userId);
	}
	
	/**
	 * Retorna todos los mensajes de la aplicación 
	 * correspondientes al dispositivo identificado en 
	 * el parametro.
	 * @param deviceId Identifcador de usuario
	 * @return Arreglo con todos los mensajes de ese dispositivo
	 */
	public String[] RecuperarPorDispositivo(String deviceId){
		return persistencia.RecuperarPorDispositivo(deviceId);
	}
	
	public String RecuperarUltimoMensaje()
	{
		return persistencia.RecuperarUltimoMensaje().getMessage();
	}
	

	
}
