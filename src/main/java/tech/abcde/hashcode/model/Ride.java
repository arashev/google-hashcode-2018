package tech.abcde.hashcode.model;

public class Ride {
	public final long id;
	public final Position start;
	public final Position end;
	public final long earliestStart;
	public final long latestFinish;

	public Ride(long id, Position start, Position end, long earliestStart, long latestFinish) {
		this.id = id;
		this.start = start;
		this.end = end;
		this.earliestStart = earliestStart;
		this.latestFinish = latestFinish;
	}

	@Override
	public String toString() {
		return "Ride{" +
				"id=" + id +
				", start=" + start +
				", end=" + end +
				", earliestStart=" + earliestStart +
				", latestFinish=" + latestFinish +
				'}';
	}
}
