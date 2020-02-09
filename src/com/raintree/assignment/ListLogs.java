package com.raintree.assignment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;


/**
 * 
 * @author ShubhamKumar
 *
 */
public class ListLogs {
	   
   public static void main(String[] args) throws ParseException {
     Helpers helpers = new Helpers();
     helpers.listAllFiles("resources");
     helpers.processInput();
   }	
	    
}
