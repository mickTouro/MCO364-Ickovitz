package analogClock.ickovitz.analogClock;

import ickovitz.analogClock.AnalogClock;
import junit.framework.Assert;

import org.junit.Test;

public class AnalogClockTest {

	@Test
	public void testAnalogClock() {

		AnalogClock ac = new AnalogClock(0, 0, 0);

		Assert.assertEquals(0.0, ac.getAngleBetweenHands());

		ac.setHour(3);
		ac.setMinutes(0);

		Assert.assertEquals(90.0, ac.getAngleBetweenHands());

		ac.setHour(3);
		ac.setMinutes(30);

		Assert.assertEquals(75.0, ac.getAngleBetweenHands());

		ac.setHour(3);
		ac.setMinutes(52);

		Assert.assertEquals(164.0, ac.getAngleBetweenHands());

		ac.setHour(12);
		ac.setMinutes(0);
		
		Assert.assertEquals(0.0, ac.getAngleBetweenHands());
	}
}
