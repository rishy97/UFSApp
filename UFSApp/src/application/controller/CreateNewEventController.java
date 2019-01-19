/** LoginController.java - Controller for the Login.fxml
 * @author xyg752
 * Rishabh Monga
 */
package application.controller;

import javafx.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import application.Main;
import application.model.Event;
import application.model.Organization;
import application.model.TimeComparer;
import application.model.User;
import application.model.Volunteer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class CreateNewEventController implements Initializable {

    @FXML
    private TextField startTimeField;

    @FXML
    private ImageView Background;

    @FXML
    private TextField numberField;

    @FXML
    private TextField eventNameField;

    @FXML
    private Label TitleLabel;

    @FXML
    private TextField endTimeField;

    @FXML
    private Button goBackButton;

    @FXML
    private Button createButton;

    @FXML
    private TextField descriptionField;

    @FXML
    private DatePicker dateSelector;

    @FXML
    private Label ErrorLabel;

	private AnchorPane anchorPane;
    
    @FXML
    void goBack(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("../VolunteerSignIn.fxml"));
		this.anchorPane = (AnchorPane) loader.load(); Scene scene = new Scene(anchorPane, 1280,720); Main.mainStage.setScene(scene);
		Main.mainStage.show();
    }

    @FXML
    void create(ActionEvent event) throws IOException {
    	
    	if( eventNameField.getText().isEmpty() 
    			|| numberField.getText().isEmpty() 
    			|| startTimeField.getText().isEmpty()
    			|| endTimeField.getText().isEmpty()
    			|| descriptionField.getText().isEmpty()
    			|| dateSelector.getValue() == null ) {
    		return;
    	}
    	
    	Event newEvent = new Event();
    	newEvent.setEventName(eventNameField.getText());
    	newEvent.setNumberOfExpectedVolunteers(Integer.parseInt(numberField.getText()));
    	newEvent.setDescription(descriptionField.getText());
    	newEvent.setStartTime(startTimeField.getText());
    	newEvent.setEndTime(TimeComparer.returnPMunfixed(startTimeField.getText(), endTimeField.getText()));
    	newEvent.setDate(dateSelector.getValue().toString());
    	newEvent.saveEvent("data/events/" + newEvent.getEventName() + ".csv");
    	
    	FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("../VolunteerSignIn.fxml"));
		this.anchorPane = (AnchorPane) loader.load(); Scene scene = new Scene(anchorPane, 1280,720); Main.mainStage.setScene(scene);
		Main.mainStage.show();
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		eventNameField.setFocusTraversable(false);
		numberField.setFocusTraversable(false);
		startTimeField.setFocusTraversable(false);
		endTimeField.setFocusTraversable(false);
		descriptionField.setFocusTraversable(false);
		createButton.requestFocus();
	}
}
