package AvailabilityDemand;

import java.util.Date;

public interface IPublisher {

	public abstract boolean publish(String providerName, String location, Date availableFrom, Date availableTo);

	public abstract void setSystem(ADS sys);

	public abstract void setBroker(Broker bro);

}
