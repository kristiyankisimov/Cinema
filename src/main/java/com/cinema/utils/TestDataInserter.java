package com.cinema.utils;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.servlet.ServletException;

@Singleton
@Startup
public class TestDataInserter {

	 	@EJB
	    private DBUtils utils;
	    
	    public TestDataInserter() {
	    }
	    
	    @PostConstruct
	    public void init() throws ServletException {
	        utils.addTestDataToDB();
	    }
	
}
