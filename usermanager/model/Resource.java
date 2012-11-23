package usermanager.model;

public class Resource implements IResource {
    // 0 1 2 3 4 5
    public static final String[] RESOURCES = { "AVAILABLE", "IN USE", "NOT PRESENT", "RECORDING", "STREAMING",
            "NOT AVAILABLE" };
    private int id;
    private int status = 0;
    private IConsumptionObs observer;
    private int pic_counter = 0;
    private int vid_counter = 0;

    public boolean isAvailable() {
        return true;
    }

    public void cancelConsumption() {

    }

    public void setObserver(IConsumptionObs _observer) {

    }

    public boolean recieveAction(int action_id, String[] param) {

        return true;
    }

    public int getStatus() {
        return status;
    }

    public void setId(int _id) {
        this.id = _id;
    }

}