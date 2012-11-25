package cl.puc.dcc.iic2113.persistencia;

public interface IAlmacenaje {
	public boolean Guardar(String filename, String userId, String deviceId, String message);
	public String Recuperar();
	public int Capacidad();
	public int CapacidadDisponible();
	public int CantidadDesconexiones();
}
