package qrComponent;

public interface Resource {
	public void setObserver(IConsuptionObs observer);
	public boolean isAvailable();
	public void cancelConsuption();
	public int getStatus();
	public void setId(int num);
	boolean reciveAction(Object data, int num);
}
