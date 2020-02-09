package com.raintree.assignment;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Stream;

public class Helpers {

	List<Computer> list = new ArrayList<Computer>();

	public void readContent(Path filePath) throws IOException {
		List<String> fileList = Files.readAllLines(filePath);
		for (String fil : fileList) {
			String[] data = fil.split("\\|");
			try {
				if (data.length == 3 && data[0] != null && data[1] != null && data[2] != null) {

					String pcName = data[0].split(" ")[1].split("\\:")[1];
					String date = data[1].split(" ")[1].substring(1).split("/")[1] + "/"
							+ data[1].split(" ")[1].substring(1).split("/")[0] + "/2012";
					String faultLog = data[2];
					addComputer(pcName, date, faultLog);
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void listAllFiles(String path) {
		try (Stream<Path> paths = Files.walk(Paths.get(path))) {
			paths.forEach(filePath -> {
				if (Files.isRegularFile(filePath)) {
					try {
						readContent(filePath);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void addComputer(String pcName, String date, String faultLog) {
		FaultType type = null;
		if (faultLog.contains("Drop count")) {
			type = FaultType.DROP;
		} else if (faultLog.contains("is disconnected")) {
			type = FaultType.DISCONNECT;
		} else if (faultLog.contains("Average limit")) {
			type = FaultType.EXCEED;
		}
		if (type != null) {
			Computer c = new Computer(pcName, date, type);
			boolean found = true;
			for (Computer c1 : list) {
				if (c1.getName().equals(c.getName()) && (c1.getFaultDate().compareTo(c.getFaultDate()) == 0)
						&& c1.getFaultType().equals(c.getFaultType())) {
					found = false;
					c1.setFaultCount(c1.getFaultCount() + 1);
				}
			}
			if (found) {
				list.add(c);
			}
		}
	}

	public void filter(Date start, Date end,String message,FaultType fault) {
		System.out.println("Computer Name\tNumber of "+message);
		HashMap<String,Integer> hmap = new HashMap<String,Integer>();
		list.forEach(e -> {
			if (e.isValid(start, end) && e.getFaultType().equals(fault)) {
				if(hmap.containsKey(e.getName())) {
					hmap.put(e.getName(), hmap.get(e.getName())+e.getFaultCount());
				} else {
					hmap.put(e.getName(), e.getFaultCount());
				}
			}
		});
		hmap.forEach((k,v)->System.out.println(k + "\t" + v));
		System.out.println("\n");
	}

	public void processInput() {
		Scanner sc = new Scanner(System.in);
	     SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
	     System.out.println("Enter Start Date in DD-MM-YYYY");  
		 Date start;
		try {
			start = formatter.parse(sc.next());
			System.out.println("Enter End Date in DD-MM-YYYY");
			 Date end = formatter.parse(sc.next());
		     System.out.println("Date :"+ new SimpleDateFormat("dd-MM-yyyy").format(start)+" to "+new SimpleDateFormat("dd-MM-yyyy").format(end)+"\n");
		     filter(start, end,"Disconnects",FaultType.DISCONNECT);
		     filter(start, end,"Drops",FaultType.DROP);
		     filter(start, end,"Average limit exceeded",FaultType.EXCEED);
		} catch (ParseException e) {
			e.printStackTrace();
		}		
	}

}