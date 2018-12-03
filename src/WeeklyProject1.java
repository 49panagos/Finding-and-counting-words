

import java.io.*;
import java.util.*;
import java.text.*;

public class WeeklyProject1 {
	
	

	public static void main(String[] args) {
		
		if(args.length==2) {
			
			
			String path = args[0];
			String command = args[1];
			String line;
			
			String[] parts = path.split("\\\\");
			String h = parts[parts.length-1];
			
			
			try {
				File file = new File(path);
				FileInputStream fileStream = new FileInputStream(file);
				
				
				int p = h.lastIndexOf(".");
				String e= h.substring(p+1);
			    if(p<0 ||!(e.equals("txt"))){
			    	fileStream.close();
			    	throw new FileNotFoundException();
				}
			  
				DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
				DateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy");
				System.out.println(dateFormat.format(new Date()) + ": File " + h + " found!");
					
				InputStreamReader input = new InputStreamReader(fileStream);
				BufferedReader reader = new BufferedReader(input);
				    
				PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("wordcount.txt", true)));
					
				        
				if(command.equals("wc")) {
					
					int wordCount = 0;
				        
				        
				    System.out.println(dateFormat.format(new Date()) + ": Total word count started");
				        
				    while((line = reader.readLine() )!= null)
					{
						String[] wordList = line.split("\\s+");
				        
						int u = 0;
						for(String a: wordList) {
							if(a.equals("")) {
								u = u + 1;
							}
						}
						
				        wordCount += wordList.length - u;
					}
				        
				    System.out.println(dateFormat.format(new Date()) +": Word count finished. Counted " + wordCount + " words");
				    
					String pad = String.format("%-12s"+"%-11s"+"%-10s"+"%-20s"+ wordCount, dateFormat2.format(new Date()), dateFormat.format(new Date()), command, h);
				    writer.println(pad);
				
				}
				else if(command.equals("find")) {
					Scanner scanner = new Scanner(System.in);  // Reading from System.in
					System.out.print("Enter the word you wish to search in the file: ");
					String s = scanner.nextLine();
					scanner.close();
	    		
					int count = 0;
					
					System.out.println(dateFormat.format(new Date()) + ": Counting occurences of the word " + s);
				
			    
					while((line = reader.readLine()) != null){
						String[] words = line.split(" ");
	
						for (String word : words) {
							// Making the search non case sensitive
							if (word.matches("(?i:.*"+s+".*)")) {
								count++;
								
							}
						}
					}
	
			        System.out.println(dateFormat.format(new Date())+": Count of word " + s + " finished." + " Occurences found: " + count);
			        
			        String pad = String.format("%-12s"+"%-11s"+"%-10s"+"%-20s"+s+":"+count, dateFormat2.format(new Date()), dateFormat.format(new Date()), command, h);
			        
			        writer.println(pad);
				}
				else
					System.out.println("Argument " + command + " is invalid. Only wc and find are supported");
				
				
				reader.close();
			            
				writer.close();
				
				
				
			}
			catch(Exception e){
				if(e instanceof FileNotFoundException)
					System.out.println(h + " is not a valid file path");
				else
				e.printStackTrace();
			}
			
			
			
			
		
			}
		else {
			System.out.println("Please provide valid arguments\n"+"argument 1: path to text file\n"+
					"argument 2: command to apply (wc or find)");
		}


	}
	
	

}
