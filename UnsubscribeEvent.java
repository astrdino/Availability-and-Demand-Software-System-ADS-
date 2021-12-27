
package AvailabilityDemand;
import java.util.Date;

public class UnsubscribeEvent {

	protected String name;

	protected String loc;

	protected Date from;
	
	protected Date to;

	public UnsubscribeEvent(String name, String location, Date from, Date to) {

		this.name = name;
		this.loc = location;
		this.from = from;
		this.to = to;

	}

}
