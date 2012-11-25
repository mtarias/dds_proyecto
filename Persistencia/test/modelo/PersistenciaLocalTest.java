package modelo;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import cl.puc.dcc.iic2113.persistencia.*;

public class PersistenciaLocalTest {

	static String userId, deviceId, message;
	static Controlador controlador;

	@BeforeClass
	public static void setUp() {
		userId = "jfcalder";
		deviceId = "Android1234";
		message = "hola, funciono!";
		controlador = new Controlador();
	}

	@Test
	public void testGuardar() {
		assertNotNull(controlador.Guardar(userId, deviceId, message));
	}

	@Test
	public void testRecuperar() {
		assertEquals(
				message,
				controlador.RecuperarPorDigest(
						controlador.Guardar(userId, deviceId, message)));
	}

	@Test
	public void testRecuperarUltimoMensaje() {
		controlador.Guardar(userId, deviceId, "otro mensaje");
		assertEquals("otro mensaje", controlador.RecuperarUltimoMensaje());
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testRecuperarPorUsuario() {
		controlador.Guardar("afvasque", "AndroidMto", "compartir_foto");
		controlador.Guardar("afvasque", "AndroidMto", "compartir_foto_2");
		controlador.Guardar(userId, deviceId, message);

		String[] esperado = { "compartir_foto", "compartir_foto_2" };
		assertEquals(esperado, controlador.RecuperarPorUsuario("afvasque"));
	}

	@Test
	public void testRecuperarPorDispositivo() {
		controlador.Guardar("afvasque", "AndroidMto2", "compartir_foto");
		controlador.Guardar("afvasque", "AndroidMto2", "compartir_foto_2");
		controlador.Guardar(userId, deviceId, message);

		String[] esperado = { "compartir_foto", "compartir_foto_2" };
		assertEquals(esperado,
				controlador.RecuperarPorDispositivo("AndroidMto2"));
	}

}
