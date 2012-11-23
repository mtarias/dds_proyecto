package usermanager.model;


public interface IConsumptionObs {
	public void consumptionFinished(int resource_id, Object object);
	public void consumptionFailed(int resource_id, String error);
	public void consumptionInterrupted(int resource_id, String error);
}