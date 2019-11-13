//Author: Sushi
//Simple GUI using JavaFX for making list of targets of Spoon Assassins

import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene; 
import javafx.scene.control.*; 
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage; 

public class VisualizeSpoon extends Application { 

	//Spoon instance
	private Spoon<String> spoon = new Spoon<>();

	// launch the application 
	public void start(Stage s) 
	{  
		//set title
		s.setTitle("Spoon Assassin Assignment"); 

		//new elements
		TextField b = new TextField(); 
		SplitPane sp = new SplitPane(); 
		Button addButton = new Button("Add New Assassin");
		Button generateButton = new Button("Generate New Target List");
		Button removeButton = new Button("Remove Assassin");

		//List of labels
		ArrayList<Label> playerList = new ArrayList<Label>();

		VBox leftControl  = new VBox(new Label("Existing Assassins"));
		VBox rightControl = new VBox(new Label("Add New Assassins"));
		rightControl.getChildren().add(b);
		rightControl.getChildren().add(addButton);
		rightControl.getChildren().add(removeButton);
		rightControl.getChildren().add(generateButton);		

		//handle events from button being clicked
		EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() { 
			public void handle(ActionEvent e) 
			{ 
				//create a new label for new assassin every time the button is pressed
				Label a1 = new Label(b.getText());
				playerList.add(a1);
				leftControl.getChildren().add(a1);
				spoon.player.add(b.getText());
			} 
		}; 
		addButton.setOnAction(event);

		//generate a new list of targets
		EventHandler<ActionEvent> event1 = new EventHandler<ActionEvent>() { 
			public void handle(ActionEvent e) 
			{ 
				//a new list of label with all target names
				Label list = new Label(spoon.assignment(spoon.player));
				leftControl.getChildren().add(list);
			} 
		}; 
		generateButton.setOnAction(event1);

		//remove a player from list
		EventHandler<ActionEvent> event2 = new EventHandler<ActionEvent>() { 
			public void handle(ActionEvent e) 
			{ 
				//remove existing name
				playerList.get(playerList.size() - 1).setText("");
				leftControl.getChildren().remove(playerList.size());
				playerList.remove(playerList.size() - 1);
				spoon.player.remove(spoon.player.size()-1);
			} 
		}; 
		removeButton.setOnAction(event2);

		sp.getItems().addAll(leftControl, rightControl);


		// create a scene 
		Scene sc = new Scene(sp, 600, 400); 

		// set the scene 
		s.setScene(sc); 
		s.show(); 
	} 

	public static void main(String args[]) 
	{ 
		// launch the application 
		launch(args); 
	} 
} 