package com.avans2018.klasd.cineapp;

import com.avans2018.klasd.cineapp.application_logic_layer.StringKeys;

import org.junit.Test;

import static com.avans2018.klasd.cineapp.application_logic_layer.TicketPrices.ADULT_TICKET_PRICE;
import static com.avans2018.klasd.cineapp.application_logic_layer.TicketPrices.CHILD_TICKET_PRICE;
import static com.avans2018.klasd.cineapp.application_logic_layer.TicketPrices.SENIOR_TICKET_PRICE;
import static com.avans2018.klasd.cineapp.application_logic_layer.TicketPrices.STUDENT_TICKET_PRICE;
import static org.junit.Assert.*;


public class CineAppUnitTest {

    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    // Unit Tests om prijswaarden te valideren
    @Test
    public void testAdultTicketPrice() {
        double actual = ADULT_TICKET_PRICE;
        double expected = 5.99;

        assertEquals("Adult Price test failed.", expected, actual, 0.001);
    }

    @Test
    public void testChildTicketPrice() {
        double actual = CHILD_TICKET_PRICE;
        double expected = 5.99;

        assertEquals("Child Price test failed.", expected, actual, 0.001);
    }

    @Test
    public void testStudentTicketPrice() {
        double actual = STUDENT_TICKET_PRICE;
        double expected = 2.99;

        assertEquals("Student Price test failed.", expected, actual, 0.001);
    }

    @Test
    public void testSeniorTicketPrice() {
        double actual = SENIOR_TICKET_PRICE;
        double expected = 4.99;

        assertEquals("Senior Price test failed.", expected, actual, 0.001);
    }

    // Unit Test om API key lengte te valideren
    @Test
    public void testApiKeyLengthValidity() {
        int actual = StringKeys.API_KEY.length();
        int expected = 32;

        assertEquals("Conversion from celsius to fahrenheit failed", expected, actual, 0.001);
    }


}