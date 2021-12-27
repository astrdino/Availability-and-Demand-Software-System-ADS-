package AvailabilityDemand;

import java.util.List;

public class AvailabilityDemand {

	private ADS sys;

	private Broker bro;

	public AvailabilityDemand() {

		if(sys == null) {
			
			sys = new ADS(); 
			bro = new Broker();
			
		}

	}

	public void processInput(String command) {
		bro.analyse(command, this.bro, this.sys);
	}

	public List<String> getAggregatedOutput() {

		for(String s: this.sys.output_List) {
			
			System.out.println(s);
		}
		
		
		return this.sys.output_List;
	}

	public void reset() {

		bro.clear(); // clear AList and DList
		this.sys.output_List.clear();

	}

}
