package edu.utc._2015cpsc2100.ejkk;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class SearchCLI
 */
@Stateless
@Asynchronous
public class SearchCLI implements SearchViewRemote, SearchViewLocal {

    /**
     * Default constructor. 
     */
    public SearchCLI() {
        // TODO Auto-generated constructor stub
    }

    
}
