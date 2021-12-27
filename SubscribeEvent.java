package AvailabilityDemand;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SubscribeEvent {

	protected String name;

	protected String loc;

	protected Date from;

	protected Date to;

	protected boolean olp_check = false;

	protected List<String> notifiedBnBList;

	public SubscribeEvent(String name, String location, Date from, Date to) {

		if(notifiedBnBList == null) {
			
			notifiedBnBList = new ArrayList<String>();
		}
		
		this.name = name;
		this.loc = location;
		this.from = from;
		this.to = to;

	}

	public void isNotified() {

		this.olp_check = true;

	}

	public void notifiedBy(String providerName) {

		this.notifiedBnBList.add(providerName);

	}

}
