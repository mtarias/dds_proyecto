package modelo;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import cl.puc.dcc.iic2113.persistencia.Controlador;

public class PersistenciaRemotaTest {

	static String userId, deviceId, message;
	static Controlador myPersistencia;
	
	@BeforeClass
	public static void setUp()
	{
		userId="jfcalder";
		deviceId="Android1234";
		message = "hola, funciono!";
		myPersistencia = new Controlador();
		myPersistencia.CambiarPersistenciaRemoto();
		
	}
	
	@Test
	public void testGuardar() {
		assertNotNull(myPersistencia.Guardar(userId, deviceId, message));
	}

	@Test
	public void testRecuperar() {
		assertEquals(message,
				myPersistencia.RecuperarPorDigest(myPersistencia.Guardar(userId, deviceId, message)));
	}

	@Test
	public void testRecuperarUltimoMensaje() {
		myPersistencia.Guardar(userId, deviceId, "otro mensaje");
		assertEquals("otro mensaje", myPersistencia.RecuperarUltimoMensaje());
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testRecuperarPorUsuario() {
		myPersistencia.Guardar("afvasque", "AndroidMto", "compartir_foto");
		myPersistencia.Guardar("afvasque", "AndroidMto", "compartir_foto_2");
		myPersistencia.Guardar(userId, deviceId, message);
		
		String[] esperado = {"compartir_foto", "compartir_foto_2"};
		assertEquals(esperado,myPersistencia.RecuperarPorUsuario("afvasque"));
	}

	@Test
	public void testRecuperarPorDispositivo() {
		myPersistencia.Guardar("afvasque", "AndroidMto2", "compartir_foto");
		myPersistencia.Guardar("afvasque", "AndroidMto2", "compartir_foto_2");
		myPersistencia.Guardar(userId, deviceId, message);
		
		String[] esperado = {"compartir_foto", "compartir_foto_2"};
		assertEquals(esperado,myPersistencia.RecuperarPorDispositivo("AndroidMto2"));
	}

}
