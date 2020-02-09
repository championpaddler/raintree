package com.raintree.assignment;

import java.util.Date;
import java.util.Scanner;


/**
 * 
 * @author ShubhamKumar
 *
 */
public class ListLogs {
	   
   public static void main(String[] args) {
     Helpers helpers = new Helpers();
     helpers.listAllFiles("resources");
     Scanner sc = new Scanner(System.in);
     System.out.println("Enter Start Date in DD/MM/YYYY");  
	 Date start = new Date(sc.next());
     System.out.println("Enter End Date in DD/MM/YYYY");
     Date end = new Date(sc.next());
     helpers.filterDisconnects(start, end);
     helpers.filterDrops(start, end);
     helpers.filterTimeExceed(start, end);
   }	
	    
}
