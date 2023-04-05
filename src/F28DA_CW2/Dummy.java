package F28DA_CW2;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.AllDirectedPaths;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;

public class Dummy {

	public int timeDuration(String start, String end) throws ParseException {
		
		//VARIABLES
		int dayInMS = 86400000; //Number of milliseconds in a day
		
		//DateTime FORMAT INSTANCE
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");

		//Parses strings to DateTime format (specified in DateTime Format Instance)
		long leaveTime = formatter.parse(start).getTime();
		long arriveTime = formatter.parse(end).getTime();
		
		//For when the air time creeps into the next day - Adds a day in milliseconds to arrival time to account for day change
		if(arriveTime < leaveTime) {
			arriveTime += dayInMS;
		}
		
		//Calculates time between start and end in milliseconds
		long duration = arriveTime - leaveTime;
		//Converts miliseconds to minutes
		long durationMinutes = TimeUnit.MILLISECONDS.toMinutes(duration);
		
		return (int) durationMinutes;
	}
	
	public static void main(String[] args) throws ParseException {
		
		FlyingPlanner fi = new FlyingPlanner();
		
		try {
			fi.populate(new FlightsReader());
			
			fi.leastTimeMeetUp("EDI", "KHI", "0600");
			
			
			
			System.out.println("\n=======================================================================\n");
			
			
			
			Journey nclCDG = fi.leastTime("SYD", "CWL");			
			FlyingPlannerMainPartBC.printJourney(nclCDG, fi);
//			System.out.println("Air Time = " + nclCDG.airTime());
//			System.out.println("Con Time = " + nclCDG.connectingTime());
			System.out.println("Start Time = " + fi.timeDuration("0600", fi.flight(nclCDG.getFlights().get(0)).getFromGMTime()));
			System.out.println("Total Time = " + nclCDG.totalTime());
			System.out.println("Hops = " + nclCDG.totalHop());
			
			
			Journey ntlCDG = fi.leastTime("EDI", "CWL");
			FlyingPlannerMainPartBC.printJourney(ntlCDG, fi);
//			System.out.println("Air Time = " + ntlCDG.airTime());
//			System.out.println("Con Time = " + ntlCDG.connectingTime());
			System.out.println("Start Time = " + fi.timeDuration("0600", fi.flight(ntlCDG.getFlights().get(0)).getFromGMTime()));
			System.out.println("Total Time = " + ntlCDG.totalTime());
			System.out.println("Hops = " + ntlCDG.totalHop());
			
			
			
			System.out.println("\n=======================================================================\n");
			
			
			
			Journey sydCWL = fi.leastTime("SYD", "SAT");
			FlyingPlannerMainPartBC.printJourney(sydCWL, fi);
//			System.out.println("Air Time = " + sydCWL.airTime());
//			System.out.println("Con Time = " + sydCWL.connectingTime());
			System.out.println("Start Time = " + fi.timeDuration("0600", fi.flight(sydCWL.getFlights().get(0)).getFromGMTime()));
			System.out.println("Total Time = " + sydCWL.totalTime());
			System.out.println("Hops = " + sydCWL.totalHop() + "\n");
			
			
			Journey ediCWL = fi.leastTime("EDI", "SAT");
			FlyingPlannerMainPartBC.printJourney(ediCWL, fi);
//			System.out.println("Air Time = " + ediCWL.airTime());
//			System.out.println("Con Time = " + ediCWL.connectingTime());
			System.out.println("Start Time = " + fi.timeDuration("0600", fi.flight(ediCWL.getFlights().get(0)).getFromGMTime()));
			System.out.println("Total Time = " + ediCWL.totalTime());
			System.out.println("Hops = " + ediCWL.totalHop() + "\n");
			
			
		} catch (FlyingPlannerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
	/*
	public String leastTimeMeetUp(String at1, String at2, String startTime) throws FlyingPlannerException, ParseException {
		List<String> codeList = codeList(at1, at2);
		
		List<Integer> timeList = new LinkedList<>();
		startTime = Flight.formatTimeString(startTime);
		startTime = startTime.substring(0,2) + ":" + startTime.substring(2);
		for(String code : codeList) {
			Journey j1 = leastTime(at1, code, 4);
			Flight firstFlight1 = flight(j1.getFlights().get(0));
			String ffDepTime1 = firstFlight1.getFromGMTime();
			int time1_1 = j1.timeDuration(startTime, ffDepTime1);
			int time1_2 = j1.totalTime();
			int time1 = time1_1 + time1_2;
			
			Journey j2 = leastTime(at2, code, 4);
			Flight firstFlight2 = flight(j2.getFlights().get(0));
			String ffDepTime2 = firstFlight2.getFromGMTime();
			int time2_1 = j2.timeDuration(startTime, ffDepTime2);
			int time2_2 = j2.totalTime();
			int time2 = time2_1 + time2_2;
			
			if(time1 > time2)
				timeList.add(time1);
			else
				timeList.add(time2);
		}
		
		int minTime = Collections.min(timeList);
		int index = timeList.indexOf(minTime);
		String meetUp = codeList.get(index);
		
		return meetUp;
	}
	*/
	






}
