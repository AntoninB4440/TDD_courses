package com.virtualpairprogrammers;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LoanCalculatorControllerTest {

    @Spy
    LoanApplication loanApplication;

    LoanCalculatorController controller;


    @BeforeEach()
    public void setup(){
        loanApplication = spy(new LoanApplication());
        LoanRepository loanRepository = mock(LoanRepository.class);
        JavaMailSender mailSender = mock(JavaMailSender.class);
        RestTemplate restTemplate = mock(RestTemplate.class);
        controller = new LoanCalculatorController();
        controller.setData(loanRepository);
        controller.setMailSender(mailSender);
        controller.setRestTemplate(restTemplate);
    }

    @Test
    void test1YearLoanWholePounds() {
        loanApplication.setPrincipal(1200);
        loanApplication.setTermInMonths(12);
        //TODO : Set the interest rate to 10%
        Mockito.doReturn(new BigDecimal(10)).when(loanApplication).getInterestRate();

        controller.processNewLoanApplication(loanApplication);

        assertEquals(new BigDecimal(110), loanApplication.getRepayment());
    }

    @Test
    void test2YearLoanWholePounds() {
        loanApplication.setPrincipal(1200);
        loanApplication.setTermInMonths(24);
        //TODO : Set the interest rate to 10%
        Mockito.doReturn(new BigDecimal(10)).when(loanApplication).getInterestRate();

        controller.processNewLoanApplication(loanApplication);

        assertEquals(new BigDecimal(60), loanApplication.getRepayment());
    }

    @Test
    void test5YearLoanWholePounds() {
        loanApplication.setPrincipal(1200);
        loanApplication.setTermInMonths(24);
        //TODO : Set the interest rate to 10%
        Mockito.doReturn(new BigDecimal(10)).when(loanApplication).getInterestRate();

        controller.processNewLoanApplication(loanApplication);

        assertEquals(new BigDecimal(60), loanApplication.getRepayment());
    }
}