package com.mockito.JUnitExercise;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Fail.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

    @Mock
    Order order;

    @Mock
    EmailService email;

    @InjectMocks
    OrderService orderService;


    @Rule
    public ExpectedException expectedException = ExpectedException.none();


    /*
     *  public static OrderService getInstance() {
        if (instance == null) {
            instance = new OrderService();
        }
        return instance;
    }
     */

    @Test
    public void test_getInstance_expectInstanceOfEmailService(){
        OrderService order1 = OrderService.getInstance();
        assertTrue(order1 instanceof OrderService);
    }

    /*
     *  public OrderService() {
        emailService = EmailService.getInstance();
    }
     */
    @Test
    public void testingg() {

        orderService=new OrderService();
        try {
            new OrderService();
        } catch (Exception e) {
            fail(e.getMessage());
        };


    }

    /*
     *  void placeOrder(Order order) {
        double priceWithTax = order.getPrice() * 20 / 100;
        order.setPriceWithTax(priceWithTax);
        emailService.sendEmail(order);
        order.setCustomerNotified(true);
    }
     */
    @Test(expected = RuntimeException.class)
    public void test_placeORderVoidType(){
        //Given
        Order order2 = new Order();
        EmailService emailService = new EmailService();

        //When
        Mockito.when(order.getPrice()).thenReturn(80.0*20/100);
        Mockito.when(order2.setPriceWithTax(80.0*20/100)).thenReturn(new Order());

        //Then


        assertEquals((double) 16,order.getPrice(),0.001);   //First condition check
        assertTrue(order2.setPriceWithTax(90.0*20/100) instanceof Order);   //Second condition check

        Mockito.verify(email).sendEmail(order);
        Mockito.verify(order).setCustomerNotified(false);
    }


	/*
	 *  boolean placeOrder(Order order, String cc) {
	        double priceWithTax = order.getPrice() * 20 / 100;
	        order.setPriceWithTax(priceWithTax);
	        boolean isNotified = emailService.sendEmail(order, cc);
	        order.setCustomerNotified(isNotified);
	        return isNotified;
	    }
	 */

    @Test
    public void test_placeOrderBooleanReturnType(){
        Mockito.when(email.sendEmail(order, "Komal")).thenReturn(true);
        assertTrue(email.sendEmail(order,"Komal"));
    }
}
