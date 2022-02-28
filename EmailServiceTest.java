package com.mockito.JUnitExercise;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class EmailServiceTest {


    @InjectMocks
    EmailService emailService;

    @Test
    public void sendEmailTest(){

        Order o1 = new Order(5 , "biscuits" , 34);
        boolean b = emailService.sendEmail(o1,"somesh kumar");
        assertTrue(b);
    }

    @Test(expected = RuntimeException.class)
    public void setcheckmailException(){
        emailService.sendEmail(new Order());

    }

}
