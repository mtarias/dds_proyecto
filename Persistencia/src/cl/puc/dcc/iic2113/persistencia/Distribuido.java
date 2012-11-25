package cl.puc.dcc.iic2113.persistencia;

class Distribuido implements IPersistencia {

	@Override
	public boolean Guardar(Token token) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Token RecuperarPorDigest(byte[] digest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TipoPersistencia MiTipo() {
		return TipoPersistencia.DISTRIBUIDO;
	}

}
