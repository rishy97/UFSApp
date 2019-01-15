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
import application.model.Organization;
import application.model.User;
import application.model.Volunteer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class HomeController implements Initializable {
	@FXML
	private Button meetingButton;

	@FXML
	private Button volunteerButton;

	@FXML
	private Button emailButton;

	@FXML
	private AnchorPane anchorPane;

	@FXML
    void loadMeeting(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("../MeetingSignIn.fxml"));
		this.anchorPane = (AnchorPane) loader.load(); Scene scene = new
		Scene(anchorPane, 1280,720); Main.mainStage.setScene(scene);
		Main.mainStage.show();
    }

    @FXML
    void loadVolunteer(ActionEvent event) {
    	
    }

    @FXML
    void loadListOfEmails(ActionEvent event) {
    	
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		System.out.println("hello");
	}
}
