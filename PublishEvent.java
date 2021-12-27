package AvailabilityDemand;
import java.util.Date;

public class PublishEvent {

	protected String name;

	protected String loc;

	protected Date from;

	protected Date to;

	public PublishEvent(String providerName, String location, Date availableFrom, Date availableTo) {


		this.name = providerName;
		this.loc = location;
		this.from = availableFrom;
		this.to = availableTo;
	}

}
