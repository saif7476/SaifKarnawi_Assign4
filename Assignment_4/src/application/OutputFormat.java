package application;

public class OutputFormat {
	
	private String line;

	public OutputFormat(String line) {
		this.line = line;
	}
	
	public String output(String line)
	{
		int count = 0;
		int index = 0;
		
		String airline = "";
		String airlineNo = "";
		String arrivalAirport = "";
		String departureAirport = "";
		
		for(int j = 0; j < line.length(); j++)
		{
			if(line.charAt(j) == ',')
			{
				count = count + 1;
				if(count == 1)
				{
					airline = line.substring(0, j);
				}
					
			}
			
		}
		
		
		count = 0;
		index = 0;	
		
		for(int j = 0; j < line.length(); j++)
		{
			
			
			if(line.charAt(j) == ',')
			{
				count = count + 1;
				if(count == 1)
				{
					index = j;
				}
				
				if(count == 2)
				{
					airlineNo = line.substring(index+1, j);
				}
					
			}
			
		}
		
		count = 0;
		index = 0;	
		
		for(int j = 0; j < line.length(); j++)
		{
				
			if(line.charAt(j) == ',')
			{
				count = count + 1;
				if(count == 2)
				{
					index = j;
				}
				
				if(count == 3)
				{
					arrivalAirport = line.substring(index+1, j);
				}
					
			}
			
		}
		
		count = 0;
		for(int j = 0; j < line.length(); j++)
		{
			
			
			if(line.charAt(j) == ',')
			{
				count = count + 1;
				
				if(count == 3)
				{
					departureAirport = line.substring(j+1);
				}
					
			}
			
		}
		
		return (airline + "\t\t" + airlineNo + "\t\t" + departureAirport + "\t\t" + arrivalAirport);
	}
	

}
