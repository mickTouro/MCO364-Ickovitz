package ickovitz.analogClock;

public class AnalogClock {

	private static final double DEGREES_OF_HOUR_HAND_PER_MINUTER = .5;
	private static final int DEGREES_PER_HOUR = 30;
	private static final int DEGRESS_PER_MINUTE = 6;
	private int hour;
	private int minutes;
	private int seconds;

	public AnalogClock(int hour, int minutes, int seconds) {
		this.hour = hour;
		this.minutes = minutes;
		this.seconds = seconds;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	public int getSeconds() {
		return seconds;
	}

	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}

	public double getAngleBetweenHands() {
		double angleMin = minutes * DEGRESS_PER_MINUTE;
		double angleHour = (hour * DEGREES_PER_HOUR) + (minutes * DEGREES_OF_HOUR_HAND_PER_MINUTER);

		double absuluteDifference = Math.abs(angleMin - angleHour);
		if (absuluteDifference <= 180)
			return absuluteDifference;
		else
			return 360 - absuluteDifference;
	}
}
