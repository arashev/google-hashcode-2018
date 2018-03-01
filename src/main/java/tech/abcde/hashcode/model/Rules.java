package tech.abcde.hashcode.model;

public class Rules {
	public final long vehicleNumber;
	public final long rideBonus;
	public final long stepsLimit;

	public Rules(long vehicleNumber, long rideBonus, long stepsLimit) {
		this.vehicleNumber = vehicleNumber;
		this.rideBonus = rideBonus;
		this.stepsLimit = stepsLimit;
	}
}
