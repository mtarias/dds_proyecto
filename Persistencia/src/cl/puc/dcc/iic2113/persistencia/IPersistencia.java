package cl.puc.dcc.iic2113.persistencia;

public interface IPersistencia {
	boolean Guardar(Token token);
	Token RecuperarPorDigest(byte[] digest);
	TipoPersistencia MiTipo();
}
