package parsefile;


public class Main {

	public static void main(String[] args) {
		
		String date=args[0];
		String serverName = args[1];
		String fileName = "/home/nouha/Bureau/t.txt";
		ParseFile parseFile=new ParseFile(fileName);
		parseFile.printConnectionReport(serverName, date);
		
		
	}

}
