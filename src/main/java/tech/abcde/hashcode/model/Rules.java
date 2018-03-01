package tech.abcde.hashcode.model;

public class Rules {

	public final long rows;
	public final long cols;
	public final long vehiclesAmount;
	public final long rideBonus;
	public final long stepsLimit;

	public Rules(long rows, long cols, long vehiclesAmount, long rideBonus, long stepsLimit) {
		this.rows = rows;
		this.cols = cols;
		this.vehiclesAmount = vehiclesAmount;
		this.rideBonus = rideBonus;
		this.stepsLimit = stepsLimit;
	}
}
