package AvailabilityDemand;
import java.util.Date;

public class Publisher implements IPublisher {

	private ADS sys;

	private Broker bro;

	private PublishEvent pe;

/*
	Redundant Code Due to Association Relationship

	private PublishEvent publishEvent;

*/

	/**
	 * @see IPublisher#publish(String, String, Date, Date)
	 */
	public boolean publish(String providerName, String location, Date availableFrom, Date availableTo) {

		// Constructing Event
		pe = new PublishEvent(providerName,location,availableFrom,availableTo);
		
		//Sending Result to Broker
		boolean sent = this.bro.add_A_List(pe);
		
	
		// Event Has been sent to Notification Center
		if(sent == true) {
			
			/*
			 * After publish new event,
			 * 
			 * Broker check if there is a request in d-List match up
			 * 
			 * If yes, Broker notifies ADS to
			 * print out all match up Subscribe Event in the d-List
			 * 
			 * 
			 */
			
			this.bro.p_check(pe);
			
			
		}
		
		
		
		return false;
	}


	/**
	 * @see IPublisher#setSystem(ADS)
	 */
	public void setSystem(ADS sys) {
		
		this.sys = sys;

	}


	/**
	 * @see IPublisher#setBroker(Broker)
	 */
	public void setBroker(Broker bro) {

		this.bro = bro;

	}

}
