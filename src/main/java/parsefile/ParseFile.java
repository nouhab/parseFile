package parsefile;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
/**
 * 
 * @author nouha
 * Parse a file and extract informations
 */

public class ParseFile {

	private String fileName;

	public ParseFile(String fileName) {

		this.fileName = fileName;
	}

	public String printConnectionReport(String serverName, String dateToParse) throws IOException {
		String mostConnectedClient = "";
		String mostRequestedServer= "";
		// list of the connected servers to the specific server
		HashMap<String, Integer> clients = new HashMap<String, Integer>();
		// list of servers which the server try to connect
		HashMap<String, Integer> requestedServers = new HashMap<String, Integer>();
		String line = "";
		String fileSplitter = " ";

		DateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.FRANCE);
		Date date;

		try {

			date = format.parse(dateToParse);

		} catch (ParseException e1) {

			e1.printStackTrace();
			throw new RuntimeException(e1);
		}
		
		Long startUnixTime = (Long) date.getTime() / 1000;
		// add one hour to the start unix date
		Long endUnixTime = startUnixTime + 3600;


		try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {

			while ((line = br.readLine()) != null) {

				String[] lineField = line.split(fileSplitter);

				Long dateField = Long.parseLong(lineField[0]);

				if (dateField >= startUnixTime) {

					if (lineField[1].equals(serverName)) {

						requestedServers.put(lineField[2], MapUtil.incrementValue(requestedServers, lineField[2]));

					} else if (lineField[2].equals(serverName)) {

						clients.put(lineField[1], MapUtil.incrementValue(clients, lineField[1]));

					}
				}

				if (dateField > endUnixTime) {

					break;
				}

			}
			
			System.out.println("connection report from "+startUnixTime + " to "+endUnixTime+": \n"
					+ " connected clients "+clients
							+ "\n Requested servers "+requestedServers);
			
			if (!clients.isEmpty()) {
				
				mostConnectedClient = MapUtil.getKeyWithMaxValue(clients);
			}
			if (!requestedServers.isEmpty()) {
				
				 mostRequestedServer = MapUtil.getKeyWithMaxValue(requestedServers);

			}

		} catch (IOException e) {
			e.printStackTrace();
			 throw new IOException(e);
		}
		return mostConnectedClient+"***"+mostRequestedServer;

	}
}