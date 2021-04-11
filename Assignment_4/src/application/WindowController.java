package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;

public class WindowController implements Initializable{
	


    @FXML
    private RadioButton rbAirline;

    @FXML
    private RadioButton rbAirlineNumber;

    @FXML
    private RadioButton rbDepartureAirport;

    @FXML
    private RadioButton rbArrivalAirport;

    @FXML
    private ChoiceBox<String> choiceAirline;

    @FXML
    private ChoiceBox<String> choiceAirlineNumber;

    @FXML
    private ChoiceBox<String> choiceDepartureNumber;

    @FXML
    private ChoiceBox<String> choiceArrivalAirport;

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnClose;

    @FXML
    private ListView<String> MyListView;

    @FXML
    void OnClose(ActionEvent event) {
    	
    	Platform.exit();

    }

    @FXML
    void OnSearch(ActionEvent event) {
    	
    	MyListView.getItems().addAll(data);

    }
    
    @FXML
    void AirlineRadioBtnClick(ActionEvent event) {
    	
		choiceAirline.setDisable(false);

    	
    	choiceAirlineNumber.setDisable(true);
		choiceArrivalAirport.setDisable(true);
		choiceDepartureNumber.setDisable(true);
		
		choiceAirline.getItems().clear();
		
		String a = "Select Airline";
		choiceAirline.getItems().add(a);	
		choiceAirline.setValue(a);
		
		// the following code supports text files of up to 100 lines. As long as the format is as followed:
		// Airline name,airline number in string format,arrival airport,departure airport
		// then the code will generate the choicebox for Airline with each airline in the file once
		
		String [] lines = new String[100];

		int x = 0; //to count how many lines we have in the text file
		// and to read the file
		
			try {
				
				FileReader fr = new FileReader("/Users/saifkarnawi/Desktop/CSIS 2175/Assignment_4/Flight.txt");
				BufferedReader br = new BufferedReader(fr);
				String s;
				while((s = br.readLine())!=null)
				{
					
					lines[x] = s;
					x = x + 1;
					
				}
				
				}
				catch(IOException e) {
					e.printStackTrace();
				}
			
			String [] airlines = new String [x]; //x being the number of lines found in the text file
		
			//adding the airline of each line from our file to a string array airlines[]
			for(int i = 0; i < lines.length; i++)
			{
				int count = 0;
				
				if(lines[i] == null)
					break; //because our array is of size 100. some lines will be null 
				else 
				{
					for(int j = 0; j < lines[i].length(); j++)
					{
						//locating airline from each line and adding it to airlines array
						if(lines[i].charAt(j) == ',')
						{
							count = count + 1; //using this count variable since we have multiple commas in each line
							//we want what is before the first comma:
							if(count == 1)
							{
								airlines[i] = lines[i].substring(0, j);
							}
								
						}
						
					}
				}
				
			}
			
			//now we add the airlines to the airline choicebox while making sure of no duplicates
			//(we don't add an airline twice or more times)
			
			choiceAirline.getItems().add(airlines[0]);
			
			for(int i = 1; i < airlines.length; i++)
			{
				boolean same = false;
				
					for(int j = i-1; j >= 0; j--)
					{
						if(airlines[i].equalsIgnoreCase(airlines[j]))
						{
							same = true;
						}

					}
					
					if(same == false)
						choiceAirline.getItems().add(airlines[i]);
			
			}
			
			
    }

    
    @FXML
    void AirlineNumberRadioBtnClick(ActionEvent event) {
    	// Same exact code for the other three radio buttons. 
    	choiceAirlineNumber.setDisable(false);
    	
    	
		choiceArrivalAirport.setDisable(true);
		choiceDepartureNumber.setDisable(true);
		choiceAirline.setDisable(true);
		
		choiceAirlineNumber.getItems().clear();
		
		String b = "Select Airline Number";
		choiceAirlineNumber.getItems().add(b);
		choiceAirlineNumber.setValue(b);
		
		String [] lines = new String[100];

		int x = 0;
		
			try {
				
				FileReader fr = new FileReader("/Users/saifkarnawi/Desktop/CSIS 2175/Assignment_4/Flight.txt");
				BufferedReader br = new BufferedReader(fr);
				String s;
				while((s = br.readLine())!=null)
				{
					
					lines[x] = s;
					x = x + 1;
					
				}
				
				}
				catch(IOException e) {
					e.printStackTrace();
				}
			
			String [] airlineNumbers = new String [x];

			for(int i = 0; i < lines.length; i++)
			{
				int count = 0;
				int index = 0;
				if(lines[i] == null)
					break;
				else 
				{
					for(int j = 0; j < lines[i].length(); j++)
					{
						//the airline number will be between the first comma and second:
						if(lines[i].charAt(j) == ',')
						{
							count = count + 1;
							if(count == 1)
							{
								index = j;
							}
							
							if(count == 2)
							{
								airlineNumbers[i] = lines[i].substring(index+1, j);
							}
								
						}
						
					}
				}
				
			}
			
			//make sure no duplicates get added:
			choiceAirlineNumber.getItems().add(airlineNumbers[0]);

			for(int i = 1; i < airlineNumbers.length; i++)
			{
				boolean same = false;
				
					for(int j = i-1; j >= 0; j--)
					{
						if(airlineNumbers[i].equalsIgnoreCase(airlineNumbers[j]))
						{
							same = true;
						}

					}
					
					if(same == false)
						choiceAirlineNumber.getItems().add(airlineNumbers[i]);
			
			}
			
			
  
    }

   
    @FXML
    void ArrivalAirpotRadioBtnClick(ActionEvent event) {
    	
		choiceArrivalAirport.setDisable(false);

    	
    	choiceAirlineNumber.setDisable(true);
		choiceDepartureNumber.setDisable(true);
		choiceAirline.setDisable(true);
		
		choiceArrivalAirport.getItems().clear();
		String c = "Select Arrival Airport";
		choiceArrivalAirport.getItems().addAll(c);	
		choiceArrivalAirport.setValue(c);
		
		String [] lines = new String[100];

		int x = 0;
		
			try {
				
				FileReader fr = new FileReader("/Users/saifkarnawi/Desktop/CSIS 2175/Assignment_4/Flight.txt");
				BufferedReader br = new BufferedReader(fr);
				String s;
				while((s = br.readLine())!=null)
				{
					
					lines[x] = s;
					x = x + 1;
					
				}
				
				}
				catch(IOException e) {
					e.printStackTrace();
				}
			
			String [] arrivalAirports = new String [x];

			for(int i = 0; i < lines.length; i++)
			{
				int count = 0;
				int index = 0;
				if(lines[i] == null)
					break;
				else 
				{
					for(int j = 0; j < lines[i].length(); j++)
					{
								
						if(lines[i].charAt(j) == ',')
						{
							count = count + 1;
							if(count == 2)
							{
								index = j;
							}
							
							if(count == 3)
							{
								arrivalAirports[i] = lines[i].substring(index+1, j);
							}
								
						}
						
					}
				}
				
			}
			
			choiceArrivalAirport.getItems().add(arrivalAirports[0]);

			for(int i = 1; i < arrivalAirports.length; i++)
			{
				boolean same = false;
				
					for(int j = i-1; j >= 0; j--)
					{
						if(arrivalAirports[i].equalsIgnoreCase(arrivalAirports[j]))
						{
							same = true;
						}

					}
					
					if(same == false)
						choiceArrivalAirport.getItems().add(arrivalAirports[i]);
			
			}

    }

    @FXML
    void DepartureAirportRadioBtnClick(ActionEvent event) {
    	
		choiceDepartureNumber.setDisable(false);

    	
    	choiceAirlineNumber.setDisable(true);
		choiceArrivalAirport.setDisable(true);
		choiceAirline.setDisable(true);
		
		choiceDepartureNumber.getItems().clear();
		String d = "Select Departure Airport";
		choiceDepartureNumber.getItems().add(d);
		choiceDepartureNumber.setValue(d);
		
		
		String [] lines = new String[100];

		int x = 0;
		
			try {
				
				FileReader fr = new FileReader("/Users/saifkarnawi/Desktop/CSIS 2175/Assignment_4/Flight.txt");
				BufferedReader br = new BufferedReader(fr);
				String s;
				while((s = br.readLine())!=null)
				{
					
					lines[x] = s;
					x = x + 1;
					
				}
				
				}
				catch(IOException e) {
					e.printStackTrace();
				}
			
			String [] departureAirports = new String [x];

			for(int i = 0; i < lines.length; i++)
			{
				int count = 0;
				int index = 0;
				if(lines[i] == null)
					break;
				else 
				{
					for(int j = 0; j < lines[i].length(); j++)
					{
								
						if(lines[i].charAt(j) == ',')
						{
							count = count + 1;
							
							if(count == 3)
							{
								departureAirports[i] = lines[i].substring(j+1);
							}
								
						}
						
					}
				}
				
			}
			
			choiceDepartureNumber.getItems().add(departureAirports[0]);

			for(int i = 1; i < departureAirports.length; i++)
			{
				boolean same = false;
				
					for(int j = i-1; j >= 0; j--)
					{
						if(departureAirports[i].equalsIgnoreCase(departureAirports[j]))
						{
							same = true;
						}

					}
					
					if(same == false)
						choiceDepartureNumber.getItems().add(departureAirports[i]);
			
			}
			
    }
    
 
    
    ObservableList<String> data = FXCollections.observableArrayList();


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		int x = 0;
		String [] lines = new String[100];

		try {
			
			FileReader fr = new FileReader("/Users/saifkarnawi/Desktop/CSIS 2175/Assignment_4/Flight.txt");
			BufferedReader br = new BufferedReader(fr);
			String s;
			while((s = br.readLine())!=null)
			{
				
				lines[x] = s;
				x = x + 1;
				
			}
			
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		
		//info to load when the program starts:
		String a = "Select Airline";
		choiceAirline.getItems().add(a);	
		choiceAirline.setValue(a);

		String b = "Select Airline Number";
		choiceAirlineNumber.getItems().add(b);
		choiceAirlineNumber.setValue(b);
		
		String c = "Select Arrival Airport";
		choiceArrivalAirport.getItems().addAll(c);	
		choiceArrivalAirport.setValue(c);
		
		String d = "Select Departure Airport";
		choiceDepartureNumber.getItems().add(d);
		choiceDepartureNumber.setValue(d);

		
		MyListView.getItems().add("Airline\t\tAirlineNo\t\tDeparture\t\tArrival");
		
		//listen for each choice box. when selection is made, we will find the lines
		//with matching item, and create an object for each one. this way, we can use a method
		// to change the line to the output layout
		
		choiceAirline.setOnAction((event) -> {
		    Object selectedItem = choiceAirline.getSelectionModel().getSelectedItem();

		    if(selectedItem != null && selectedItem != "Select Airline")
		    {
		    	MyListView.getItems().clear();
				MyListView.getItems().add("Airline\t\tAirlineNo\t\tDeparture\t\tArrival");
				data.clear();

				for(int i = 0; i < lines.length; i++)
				{
					int count = 0;
					
					if(lines[i] == null)
						break; 
					else 
					{
						for(int j = 0; j < lines[i].length(); j++)
						{
	
							if(lines[i].charAt(j) == ',')
							{
								//searching for lines with our selected item:
								count = count + 1;
								if(count == 1 && selectedItem.toString().equalsIgnoreCase(lines[i].substring(0, j)))
								{
									OutputFormat o = new OutputFormat(lines[i]);
									String finalOutput = o.output(lines[i]);
									data.add(finalOutput);
								}
									
							}
							
						}
					}
					
				}		
		    }
		    
		    else if(selectedItem == "Select Airline")
		    {
				data.clear();
		    }
		});
		
		choiceAirlineNumber.setOnAction((event) -> {
		    Object selectedItem = choiceAirlineNumber.getSelectionModel().getSelectedItem();
		    
		    if(selectedItem != null && selectedItem != "Select Airline Number")
		    {
		    	MyListView.getItems().clear();
				MyListView.getItems().add("Airline\t\tAirlineNo\t\tDeparture\t\tArrival");
				data.clear();

			    for(int i = 0; i < lines.length; i++)
				{
					int count = 0;
					int index = 0;
					if(lines[i] == null)
						break;
					else 
					{
						for(int j = 0; j < lines[i].length(); j++)
						{
									
							if(lines[i].charAt(j) == ',')
							{
								count = count + 1;
								if(count == 1)
								{
									index = j;
								}
								
								if(count == 2 && selectedItem.toString().equalsIgnoreCase(lines[i].substring(index+1, j)))
								{
									OutputFormat o = new OutputFormat(lines[i]);
									String finalOutput = o.output(lines[i]);
									data.add(finalOutput);
								}
									
							}
							
						}
					}
					
				}

		    }
		    
		    else if(selectedItem == "Select Airline Number")
		    {
				data.clear();
		    }

		    
		});
		
		choiceArrivalAirport.setOnAction((event) -> {
		    Object selectedItem = choiceArrivalAirport.getSelectionModel().getSelectedItem();
		    if(selectedItem != null && selectedItem != "Select Arrival Airport")
		    {
		    	MyListView.getItems().clear();
				MyListView.getItems().add("Airline\t\tAirlineNo\t\tDeparture\t\tArrival");
				data.clear();
				
				for(int i = 0; i < lines.length; i++)
				{
					int count = 0;
					int index = 0;
					if(lines[i] == null)
						break;
					else 
					{
						for(int j = 0; j < lines[i].length(); j++)
						{
									
							if(lines[i].charAt(j) == ',')
							{
								count = count + 1;
								if(count == 2)
								{
									index = j;
								}
								
								if(count == 3 && selectedItem.toString().equalsIgnoreCase(lines[i].substring(index+1, j)))
								{
									OutputFormat o = new OutputFormat(lines[i]);
									String finalOutput = o.output(lines[i]);
									data.add(finalOutput);
								}
									
							}
							
						}
					}
					
				}
		    	
		    }
		    
		    else if(selectedItem == "Select Arrival Airport")
		    {
				data.clear();
		    }

		    
		});
		
		choiceDepartureNumber.setOnAction((event) -> {
		    Object selectedItem = choiceDepartureNumber.getSelectionModel().getSelectedItem();

		    if(selectedItem != null && selectedItem != "Select Departure Airport")
		    {
		    	MyListView.getItems().clear();
				MyListView.getItems().add("Airline\t\tAirlineNo\t\tDeparture\t\tArrival");
				data.clear();
				
				for(int i = 0; i < lines.length; i++)
				{
					int count = 0;
					int index = 0;
					if(lines[i] == null)
						break;
					else 
					{
						for(int j = 0; j < lines[i].length(); j++)
						{
									
							if(lines[i].charAt(j) == ',')
							{
								count = count + 1;
								
								if(count == 3 && selectedItem.toString().equalsIgnoreCase(lines[i].substring(j+1)))
								{			
									OutputFormat o = new OutputFormat(lines[i]);
									String finalOutput = o.output(lines[i]);
									data.add(finalOutput);
								}
									
							}
							
						}
					}
					
				}
				
		    }
		    
		    else if(selectedItem == "Select Departure Airport")
		    {
				data.clear();
		    }
		    
		});
		
	}
	
	

}
