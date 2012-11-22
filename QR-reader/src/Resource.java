
public interface Resource {
	public boolean reciveAction(String[] data, int num);
	public void setObserver(IConsuptionObs observer);
	public boolean isAvailable();
	public void cancelConsuption();
	public int getStatus();
	public void setId(int num);
}
