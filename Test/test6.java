package AvailabilityDemand;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**

 *
 * These tests concern only with the correctness and robustness of your implementation. You are still required to
 * adopt Publisher-Subscriber pattern in your design and make use of good programming practises.
 *
 * The undisclosed part of the test will differ from this test somewhat:
 * - Test cases are automatically generated with random characters (other than commas and leading/trailing spaces).
 * - The generated test cases can be very long and contains unusual sequences.
 * - The generated test cases are based on criteria imposed in the project descriptions, which any implementation is
 *   bound to abide.
 * - Instead of testing the output of your program against a "standard answer", your output would be compared against
 *   the output of a "Reference Implementation" (RI), which is seen as the authoritative answer.
 *  - Could the RI be wrong? Yes, but unlikely. Contact us if you believe it is the case.
 * @version 1.0
 *
 */

public class test6 {
    private static AvailabilityDemand availabilityDemand;

    // Create AvailabilityDemand object to test with
    @BeforeClass
    public static void setupAvailabilityDemand() {
        availabilityDemand = new AvailabilityDemand();
    }

    // Reset the availabilityDemand object every time a test finishes so that it can accept a new batch of commands
    @After
    public void resetAvailabilityDemand() {
        availabilityDemand.reset();
    }

    @Test
    // @GradedTest(name = "Normal 2 - Multi providers subscribe to & unsubscribe from the same type", max_score = 2)
    public void testMultiSubUnsubForValidInputs() {
        // Expected output
        List<String> expected = new ArrayList<>(Arrays.asList(
                "John Doe notified of B&B availability in New York City from 11/30/2021 to 12/15/2021 by High-Mountains B&B",
                "William notified of B&B availability in New York City from 11/30/2021 to 12/15/2021 by High-Mountains B&B",
                "William notified of B&B availability in New York City from 11/30/2021 to 12/15/2021 by High-Mountains B&B",
                "William notified of B&B availability in New York City from 11/30/2021 to 12/15/2021 by AirClouds B&B",
                "John Doe notified of B&B availability in New York City from 11/30/2021 to 12/15/2021 by High-Mountains B&B",
                "John Doe notified of B&B availability in New York City from 11/30/2021 to 12/15/2021 by AirClouds B&B"
        ));
        // Feed the SD object with some commands
        availabilityDemand.processInput("subscribe, John Doe, New York City, 12/01/2021, 12/05/2021");
        availabilityDemand.processInput("subscribe, William, New York City, 12/10/2021, 12/15/2021");
        //both subscribers should get the notification as satisfy the criteria
        availabilityDemand.processInput("publish, High-Mountains, New York City, 11/30/2021, 12/15/2021");
        //one subscriber removed from system
        availabilityDemand.processInput("unsubscribe, William, New York City, 12/10/2021, 12/15/2021");
        //duplicate published event, no action taken
        availabilityDemand.processInput("publish, High-Mountains, New York City, 11/30/2021, 12/15/2021");
        //one subscriber removed from system, no subscribers in system
        availabilityDemand.processInput("unsubscribe, John Doe, New York City, 12/01/2021, 12/05/2021");
        //no subscribers in system, system will store the event
        availabilityDemand.processInput("publish, AirClouds, New York City, 11/30/2021, 12/15/2021");
        // both stored published events will be fired for below customer since this one comes as a new subscription
        availabilityDemand.processInput("subscribe, William, New York City, 12/10/2021, 12/15/2021");
        // both stored published events will be fired for below customer since this one comes as a new subscription
        availabilityDemand.processInput("subscribe, John Doe, New York City, 12/01/2021, 12/05/2021");

        // Obtain the actual result from your availabilityDemand object and compare it with the expected output
        List<String> actual = availabilityDemand.getAggregatedOutput().stream()
                .map(String::strip)
                .map(String::toLowerCase)
                .collect(Collectors.toList());
        expected =  expected.stream().map(String :: toLowerCase).collect(Collectors.toList());

        assertEquals(expected, actual);
    }

    
}