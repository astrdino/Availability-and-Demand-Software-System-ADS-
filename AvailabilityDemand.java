package AvailabilityDemand;

import java.util.List;

/**
 * This is the main class which process the command and collects and returns output notifications.
 * You can find commands format in project description document.
 */
public class AvailabilityDemand {
    public void processInput(String command) {
        /**
         *
         * 1. ASSUMPTION: tokens are delimited by comma (as specified in the document)
         * 2. All publish, subscribe, unsubscribe commands take 5 parameters and all the tokens are mandatory to perform operations
         *    For example, “subscribe, John Doe, New York City, 12/01/2021, 12/05/2021” corresponds to =>
         *    => subscribe, [customer name], [location],[fromDate], [toDate]
         *    “publish, High-Mountains, New  York City, 11/30/2021, 12/15/2021”  corresponds to =>
         *    => publish, [BNB provider name],[location], [available from date], [available until date]
         *    “unsubscribe, John Doe, New York City, 12/01/2021, 12/05/2021” corresponds to =>
         *    => unsubscribe, [customer name], [location],[fromDate], [toDate]
         * 3. You need to write the parsing logic for these string commands.
         * 4. Based on the command entered, the parser can take the decision where to route the call. i.e., if the publish
         *    command is fired, after parsing and verifying the input constraints publisher will be called and required data can be passed to it.
         *    Publisher then can have a logic to communicate with the broker.
         * 5. Similarly if the command entered is subscribe or unsubscribe, the Customer or Subscriber will be called, which can
         *    then have a logic to communicate with the broker.
         * 6. To summarize, this method will parse the input and based on the command, will call the appropriate publisher or subscriber class
         *    which can have a mechanism to communicate with the broker
         */
        //Your code goes here ...
        
    }

    public List<String> getAggregatedOutput() {
        /**
         * 1. This method is responsible to return the consolidated notifications.
         * 2. You need to have a class which will format the notifications in the form described in project document
         * Example , John Doe notified of B&B availability in New York City from 11/30/2021 to 12/15/2021 by High-Mountains B&B which corresponds to =>
         *          [customer] notified of B&B availability in [location] from [available from date] to [available until date] by [B&B provider name] B&B.
         * 3. Whenever broker will send out the notification, this notification should be logged by some class which will take care
         *   of output formatting.
         * 4. When we request the "getAggregatedOutput", it is expected to return all the notifications stored/logged by the
         *   formatter.
         */
        //Your code goes here ...
        //change the output to return valid list data.
       return null;
    }

    public void reset() {
        /**
         * This method is responsible to clear out all stored published events, customers subscribed to system
         * and all the information stored with the system which is necessary to process the notifications.
         * This should also reset all the notifications stored with the system which are returned when "getAggregatedOutput" method is
         * called.
         * Your Broker class should provide an operation to clear out stored information. Similarly, your class notification output
         * formatter should provide an operation to clear out the notifications stored with the system.
         */
        //Your code goes here ...
     
    }
}