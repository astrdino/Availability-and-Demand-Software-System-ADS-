package AvailabilityDemand;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ADS {

	public List<String> output_List;

	private Date now;

	private Message msg;
	
	/*
	 
	Redundant Code Generated Due to Association Relationship

	private Message message;

	private Message message;
	
	*/

	public ADS() {

		this.output_List = new ArrayList<String>();
		
		
		//Initialize NOW
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		try {
			
			now = sdf.parse("11/27/2021");
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public boolean setMsg(String name, String loc, String provider, Date from, Date to) {
			String output;
		boolean set = false;
		
		//Construct message 
		msg = new Message(name,loc,provider,from,to);
		output = msg.gen();
		
		//Adding to the output List
		output_List.add(output);
		
		set = true;
		
		return set;
	}

}
