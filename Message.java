package AvailabilityDemand;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Message {

	private String sub_Name;

	private String loc;

	private String provider;
	
	private Date from;
	
	private Date to;
	
	private SimpleDateFormat sdf;	

	public Message(String sub_Name, String loc, String provider, Date from, Date to) {


		this.sub_Name = sub_Name;
		this.loc = loc;
		this.provider = provider;
		this.from = from;
		this.to = to;

	}

	public String gen() {

		sdf = new SimpleDateFormat("MM/dd/yyyy");
		
		String s = this.sub_Name.toLowerCase() + " notified of B&B availability in " +
					this.loc.toLowerCase() + " from " + sdf.format(this.from) + " to " + sdf.format(this.to) +
						" by " + this.provider.toLowerCase() + " b&b";
		
		return s;
	}

}
