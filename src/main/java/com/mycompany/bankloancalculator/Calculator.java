/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//package
package com.mycompany.bankloancalculator;

//imports
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.math.RoundingMode;

/*
 * Calculator.java
 * 23rd December 2021
 * Advanced Programming - TABA
 * @author: Ruby Lennon (x19128355)
 * Description - Bank loan calculator web application which provides the following services: Computation of the monthly payment & Computation of the total repayment
 */

//loan URI path
@Path("/loan")
public class Calculator {
    
    //monthly payment calculator method - accepts loan amount, interest rate (annual) and months total
    public double monthlyPayment(double amount, double interest, int months){
        //declare variable
        double monthlyPayment;
        double discountFactor;
        double monthlyInterest;
        double monthlyPaymentRounded;
        
        //calculate the monthly interest based on the annual interest rate
        monthlyInterest = (interest / 100) / 12;
        
        //calculate and store the discount factor
        discountFactor = ((Math.pow((1 + monthlyInterest),months)) - 1) / (monthlyInterest * (Math.pow((1 + monthlyInterest),months)));
        
        //calculate and store the monthly payment
        monthlyPayment = amount / discountFactor;
        
        //round up the monthly payment value to the nearest 2 decimal number
        BigDecimal bd = new BigDecimal(monthlyPayment).setScale(2, RoundingMode.HALF_UP);
        
        //store the rounded value to a new variable
        monthlyPaymentRounded = bd.doubleValue();
        
        //return the rounded monthly payment value
        return monthlyPaymentRounded;
        
    }
    
    //method to calculate the total repayment value - accepts the loan amount and the repayment months quantity
    public double totalRepayment(double amount, int months){
        //decalre variables
        double totalRepayment;
        double totalRepaymentRounded;
        
        //calculate and store the total repayment value
        totalRepayment = amount * months;
        
        //round up the total repayment value to the nearest 2 decimal number
        BigDecimal bd = new BigDecimal(totalRepayment).setScale(2, RoundingMode.HALF_UP);
        
        //store the rounded value to a new variable
        totalRepaymentRounded = bd.doubleValue();
        
        //return the rounded total repayement value
        return totalRepaymentRounded;
        
    }
    
    //GET method to calculate the monthly payment value
    @GET//request method designator
    @Path("monthlypayment/{amount}/{annualInterestRate}/{months}")//URI path template
    public Response computeMonthlyPayment(
            @PathParam("amount") double mpAmount,//loan amaount path parameter variable name
            @PathParam("annualInterestRate") double mpAnnualInterestRate,//annual interest rate path parameter variable name
            @PathParam("months") int mpMonths) {//total months path parameter variable name
        
        //return the monthly payment amount by calling the monthly payment method using the user input data
        return Response.status(200).entity(monthlyPayment(mpAmount, mpAnnualInterestRate, mpMonths)).build();
    }
    
    //GET method to calculate the total loan repayment value
    @GET//request method designator
    @Path("totalrepayment/{amount}/{months}")//URI path template
    public Response computeTotalRepayment(
            @PathParam("amount") double trAmount,//monthly repayment amount path parameter variable name
            @PathParam("months") int trMonths) {//total months path parameter variable name
        
        //return the total repayment amount by calling the total repayment method using the user input data
        return Response.status(200).entity(totalRepayment(trAmount, trMonths)).build();
    }
}
