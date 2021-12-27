package AvailabilityDemand;
import java.util.Date;

public class Subscriber implements ISubscriber {

	private ADS sys;

	private Broker bro;

	private SubscribeEvent se;

	private UnsubscribeEvent use;

	private String name;

/*
	Redundant Code Generated Due To Association Relationship
	private UnsubscribeEvent unsubscribeEvent;

	private SubscribeEvent subscribeEvent;

	private SubscribeEvent subscribeEvent;

*/

	/**
	 * @see ISubscriber#subscribe(String, Date, Date)
	 */
	public boolean subscribe(String location, Date from, Date to) {

		
		//Constructing Event
		se = new SubscribeEvent(name,location,from,to);
		
		//Sending Result to Broker
		boolean sent = this.bro.add_D_List(se);
		
		/*
		 * 
		 * Broker check in A_List
		 * 
		 * if is not empty
		 * 
		 * Broker notifies ADS to print all possible match up in d_List
		 */
		if(sent == true) {
			
			this.bro.s_check(se);
			
		}
		
		return false;

	}


	/**
	 * @see ISubscriber#unsubscribe(String, Date, Date)
	 */
	public boolean unsubscribe(String location, Date from, Date to) {
		
		//Constructing Event
		use = new UnsubscribeEvent(name,location,from, to);
		
		//Sending Result to Broker
		this.bro.rm_D_List(use);
		
		return false;
	}


	/**
	 * @see ISubscriber#setSystem(ADS)
	 *  
	 */
	public void setSystem(ADS sys) {
		this.sys = sys;
	}


	/**
	 * @see ISubscriber#setBroker(Broker)
	 */
	public void setBroker(Broker bro) {
		this.bro = bro;
	}


	/**
	 * @see ISubscriber#setName(String)
	 */
	public void setName(String name) {
		this.name = name;
	}

}
