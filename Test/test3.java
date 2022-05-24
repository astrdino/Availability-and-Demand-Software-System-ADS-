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

public class test3 {
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
    //@GradedTest(name = "Abnormal 1 - Illegal parameter lengths", max_score = 2)
    public void testIllegalParamLens() {

        //Date format invalid: DD/MM/YYYY
        availabilityDemand.processInput("subscribe, John Doe, New York City, 15/01/2022, 30/01/2022");
        //Date format invalid: DD MMM YYYY
        availabilityDemand.processInput("publish, High-Mountains, New York City, 14 Jan 2022, 30 Jan 2022");
        //stay period to date must be smaller than stay period from date
        availabilityDemand.processInput("subscribe, John Doe, New York City, 30/01/2022, 15/01/2022");
        //extra parameter in the publish method
        availabilityDemand.processInput("publish, High-Mountains, New York City, 14 Jan 2022, 30 Jan 2022, great view and lot of space");
        //available till date must be smaller than available from date
        availabilityDemand.processInput("publish, AirClouds, New York City, 30/01/2022, 15/01/2022");
        // Obtain the actual result from your SD object and compare it with the expected output
        List<String> actual = availabilityDemand.getAggregatedOutput();
        // Expected output (nothing)
        assertEquals(0, actual.size());
    }

    
}