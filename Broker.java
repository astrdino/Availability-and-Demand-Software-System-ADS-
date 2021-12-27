package AvailabilityDemand;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Broker {

	private List<SubscribeEvent> dList;

	private List<PublishEvent> aList;

	private String op;

	private String name;

	private String loc;

	private String fromS;

	private String toS;

	private Date from;

	private Date to;

	private Date now;

	private ADS sys;

	private SimpleDateFormat sdf;
	
/*
	Redundant Code Due to Association Relationship
	private Message msg;
	
	private Publisher publisher;

	private Subscriber subscriber;

*/





	public Broker() {

		sdf = new SimpleDateFormat("MM/dd/yyyy");
		
		this.dList = new ArrayList<SubscribeEvent>();
		this.aList = new ArrayList<PublishEvent>();
		
		//Initialize NOW	
		try {
			
			now = sdf.parse("11/27/2021");
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

	public void analyse(String command, Broker bro, ADS sys) {

		boolean hasS = false; //Date format "MM/DD/YYY" var
		boolean extra_par = false; //extra parameter check var
		int ck = -1;  //Month check var
		int ck2 = -1; //Month check var
		
		//Identify Notification Center
		this.sys = sys;
		
		//Command Partition			
		this.op = command.split(",")[0];
		
		this.name = command.split(",")[1];
		this.name = this.name.trim(); //remove lead white space
		
		this.loc = command.split(",")[2];
		this.loc = this.loc.trim();
		
		this.fromS = command.split(",")[3];
		this.toS = command.split(",")[4];
		
		//Checking if the command has extra parameter
		if(command.split(",").length > 5) {
			extra_par = true;
		}

		//Checking Date Format
		
		
		//Invalid DD MM YYYY
		if(this.fromS.contains("/") && this.toS.contains("/")) {
			
			
			//Invalid DD/MM/YYYY				
			ck = Integer.parseInt(this.fromS.split("/")[0].replaceAll("\\s", ""));
			ck2 = Integer.parseInt(this.fromS.split("/")[0].replaceAll("\\s", ""));
			
			hasS=true;
		}
		
		
		//Run if date format is correct
		if(ck >= 0 && ck <= 12 && ck2 >=0 && ck <= 12 && hasS==true && extra_par == false) {
			
			//Date Conversion
			
			try {
				
				this.from = sdf.parse(this.fromS);
				this.to = sdf.parse(this.toS);
				
				//check if to date before from date
				if(this.from.before(this.to) || this.from.equals(this.to)) {
					
					
					
					
					//Constructing Publisher & Subscriber				
					if(op.equals("publish")) {
						
						Publisher p = new Publisher();
						p.setSystem(sys);
						p.setBroker(bro);
						p.publish(this.name, this.loc, this.from, this.to);
						
						
						
					}
					else if (op.equals("subscribe")){
						
						Subscriber s = new Subscriber();
						
						s.setSystem(sys);	
						s.setBroker(bro);
						s.setName(this.name);
						s.subscribe(this.loc, this.from, this.to);
						
						
					}
					else if (op.equals("unsubscribe")) {
						
						Subscriber s = new Subscriber();
						
						s.setSystem(sys);
						s.setBroker(bro);
						s.setName(this.name);
						s.unsubscribe(this.loc, this.from, this.to);
						
						
					}
					else {
						
						
						
					}
					
					
				}
				
				
			} catch (ParseException e) {
				
			}
			
			
		}
		
		

	}

	public boolean add_A_List(PublishEvent event) {

		boolean added = false;
		boolean dp = false; 
		
		
		
		//Check if there is a duplicated public event
		for(PublishEvent pe: this.aList) {
			
			if(event.name.equals(pe.name) && event.loc.equals(pe.loc) &&
					event.from.equals(pe.from) && event.to.equals(pe.to)) {
				
				dp = true;
				break;
				
			}
			
		}
		
		
		
		/*The publish event iff 
		 * be added if it's a period after 11/27/2021 and it has not been
		 * notified by this. provider
		 */
		
		if(event.from.after(now) && event.to.after(now) && dp == false) {
			
			this.aList.add(event);
			added = true;
			
		}
		
		
		
		return added;
		

	}

	public boolean add_D_List(SubscribeEvent event) {

		boolean added = false;
		boolean dp = false; 
		
		//The subscribe event iff be added if it's a period after 11/27/2021
		if(event.from.after(now) && event.to.after(now)) {
			
			
			
			
			
			//Check if there is a duplicated public event
			for(SubscribeEvent se: this.dList) {
				
				if(event.name.equals(se.name) && event.loc.equals(se.loc) &&
						event.from.equals(se.from) && event.to.equals(se.to)) {
					
					dp = true;
					break;
					
				}
				
			}
			
			if(dp == false) {
				
				this.dList.add(event);
				
				added = true;
			}
			
		}
		
		return added;	

	}

	public void rm_D_List(UnsubscribeEvent event) {
		
			for(SubscribeEvent se_in_list: this.dList) {
			
			//Checking if there is a matchup SubscribeEvent in the System
			if(se_in_list.loc.equals(event.loc) &&
					se_in_list.from.equals(event.from) &&
						se_in_list.to.equals(event.to)
					) {
				
				//Remove the Subscribe Event
				dList.remove(se_in_list);
				
				break;
			}
		}
		
		

	}

	public void p_check(PublishEvent pe) {

		if(this.dList.isEmpty() == false) {
			
			for(SubscribeEvent se: this.dList) {
				
				//Location Check
				if(pe.loc.equals(se.loc)) {
					
					//From date check
					if(pe.from.before(se.from) || pe.from.equals(se.from)) {
						
						//To date check
						if(pe.to.after(se.to) || pe.to.equals(se.to)) {
							
							
							//Check if it is an overlapped p_event to s_event
							if(se.olp_check == false) { // Condition 1: the subscribe event has never been notified								
						
								
								//Notify ADS to generate message
								
								boolean sent = this.sys.setMsg(se.name,se.loc,pe.name,pe.from,pe.to);
								
								
								if(sent == true) {
									
									//Setup Overlapped Check
									se.isNotified();
									
									//Record the Provider that notified subscriber
									se.notifiedBy(pe.name);
								}
								
																
							}
							else if(se.olp_check == true) {
								
								/*
								 
								If the subscribe event has been notified
								Check if it is notified by the same provider
								If it is, Discard
								If it is not, notify
								
								*/
								
								boolean dpProvider = false;
								
								
								for(String provider: se.notifiedBnBList) {
									
									if(provider.equals(pe.name)) {
										dpProvider = true;
										break;
									}
									
								}
								
								if(dpProvider == false) {
									
									this.sys.setMsg(se.name,se.loc,pe.name,pe.from,pe.to);
									
									//Record the Provider that notified subscriber
									se.notifiedBy(pe.name);
								}
								
								
							}
							
							
						
												
						}						
						
					}
										
				}
			}			
		}
		

	}

	public void s_check(SubscribeEvent se) {

			if(this.aList.isEmpty() == false) {
			
			for(PublishEvent pe: this.aList) {
				
				//Location Check
				if(pe.loc.equals(se.loc)) {
					
					//From date check
					if(pe.from.before(se.from) || pe.from.equals(se.from)) {
						
						//To date check
						if(pe.to.after(se.to) || pe.to.equals(se.to)) {
							
							
							boolean tmp = this.sys.setMsg(se.name,se.loc,pe.name,pe.from,pe.to);
														
							
						}
						
						
						
						
					}
					
				
					
				}
				
			}
			
		}

		

	}

	public void clear() {

		this.dList.clear();
		this.aList.clear();

	}

}
