package experiments;

import java.sql.Timestamp;

public class Demo {
	public static void main(String[] args) {
				//System.getProperties().list(System.out);
		
				System.out.println(System.getProperty("os.name"));
				System.out.println(System.getProperty("user.name"));
				System.out.println(System.getProperty("java.version"));
				
		        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
		        System.out.println("Formatted Timestamp: " + currentTimestamp.toString().replace("-", "_").replace(" ", "_").replace(":", "_").replace(".", "_"));
		   	
		    }
	
	
	}

