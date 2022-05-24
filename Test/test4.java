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

public class test4 {
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
    //@GradedTest(name = "Abnormal 2 - date constraint of notification", max_score = 2)
    public void testDateConstraintInPublishingInformation() {
        // Expected output (nothing)
        // Feed the SD object with some commands
        availabilityDemand.processInput("subscribe, John Doe, New York City, 12/01/2021, 12/05/2021");
        availabilityDemand.processInput("subscribe, William, New York City, 12/10/2021, 12/15/2021");
        // to date needs to be same as or greater than end date of subscribed period
        availabilityDemand.processInput("publish, High-Mountains, New York City, 11/29/2021, 12/02/2021");
        // start date of availability period needs to be later than default date which is 11/27/2021
        availabilityDemand.processInput("publish, High-Mountains, New York City, 11/20/2021, 12/05/2021");
        // start date of stay period needs to be later than default date which is 11/27/2021
        availabilityDemand.processInput("subscribe, Jane Doe, New York City, 11/20/2021, 12/05/2021");

        // Obtain the actual result from your SD object and compare it with the expected output
        List<String> actual = availabilityDemand.getAggregatedOutput();

        assertEquals(0, actual.size());
    }

    
}