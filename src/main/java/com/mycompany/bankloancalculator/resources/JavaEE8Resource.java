//package
package com.mycompany.bankloancalculator.resources;

//imports
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/*
 * JavaEE8Resource.java
 * 23rd December 2021
 * Advanced Programming - TABA
 * @author: Ruby Lennon (x19128355)
 * Description - Java EE 8 Resource
 */

@Path("javaee8")
public class JavaEE8Resource {
    
    @GET
    public Response ping(){
        return Response
                .ok("ping")
                .build();
    }
}
