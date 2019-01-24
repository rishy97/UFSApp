/** LoginController.java - Controller for the Login.fxml
 * @author xyg752
 * Rishabh Monga
 */
package application.controller;

import javafx.application.Platform;
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
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

public class MeetingSignInController implements Initializable {
	public static int validateValue;

	@FXML
	private Button SignInButton;

	@FXML
	private TextField FirstNameField;

	@FXML
	private Label UserNameLabel;

	@FXML
	private TextField LastNameField;

	@FXML
	private TextField EmailField;

	@FXML
	private Label TitleLabel;

	@FXML
	private Label PasswordLabel;

	@FXML
	private ImageView Image;

	@FXML
	private Label ErrorLabel;

	@FXML
	private Label EmailLabel;
	private AnchorPane anchorPane;

	/**
	 * loadNextScreen() method - signin button
	 * 
	 * @param event
	 * @throws InterruptedException 
	 * @throws IOException
	 */
	
	
	public void updateErrorField(String entry) throws InterruptedException {
		ErrorLabel.setText( "*" + entry + "*");
		int time = 3;
		
		
		new Thread(new Runnable() {
		    @Override public void run() {
		        for( int i = 0; i < 1; i++ ) {
			    	try {
						Thread.sleep(time * 800);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			    	Platform.runLater(new Runnable() {
			            @Override public void run() {
			            	if (ErrorLabel.getText().equals("*" + entry + "*"))
			            		ErrorLabel.setText("");	
			            }
			        });
		        }
		    }
		}).start();
	}
	
	@FXML
	public void loadNextScreen(ActionEvent event) throws IOException, InterruptedException {
		
		if ( !FirstNameField.getText().isEmpty() ) {
			if (FirstNameField.getText().toLowerCase().equals("home")) {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(Main.class.getResource("../Home.fxml"));
				this.anchorPane = (AnchorPane) loader.load(); Scene scene = new
				Scene(anchorPane, 1280,720); Main.mainStage.setScene(scene);
				Main.mainStage.show();
			}
		}
		
		if ( !FirstNameField.getText().isEmpty()) {
			if ( FirstNameField.getText().contains(",") ) {
				updateErrorField("First Name cannot contains commas");
				return;
			}
		} 
		if ( !LastNameField.getText().isEmpty()) {
			if ( LastNameField.getText().contains(",") ) {
				updateErrorField("Last Name cannot contains commas");
				return;
			}
		}
		
		if ( !EmailField.getText().isEmpty()) {
			if ( EmailField.getText().contains(",") ) {
				updateErrorField("Email cannot contains commas");
				return;
			}
		}
		
		if (FirstNameField.getText().isEmpty() || LastNameField.getText().isEmpty() ) {
			updateErrorField("Please complete the fields.");
			return;
		}
		
		if ( Main.ufs.findUser(FirstNameField.getText(), LastNameField.getText()) == null && EmailField.getText().isEmpty() ) {
			updateErrorField("New User, Please Enter Email");
			return;
		}
		
		if ( Main.ufs.findUser(FirstNameField.getText(), LastNameField.getText()) != null) {
			User temp = Main.ufs.findUser(FirstNameField.getText(), LastNameField.getText());
			
			temp.setMeetings(temp.getMeetings() + 1);
			temp.setLatestMeetingToToday();
			updateErrorField("Success! Welcome back.");
			Main.ufs.saveUsers("data/users.csv");
			
			FirstNameField.clear();
			LastNameField.clear();
			EmailField.clear();
			
			this.FirstNameField.deselect();
			this.LastNameField.deselect();
			this.EmailField.selectEnd();
			this.EmailField.deselect();
			
			this.SignInButton.requestFocus();
			
			return;
		}
		
		Main.ufs.addUser(FirstNameField.getText(), LastNameField.getText(), EmailField.getText());

		User temp = Main.ufs.findUser(FirstNameField.getText(), LastNameField.getText());
		temp.setMeetings(1);
		temp.setVolunteerEvents(0);
		temp.setTotalVolunteerHours("00:00");
		temp.setLatestMeetingToToday();
		temp.setLastVolunteerEvent("-");
		Main.ufs.saveUsers("data/users.csv");
		
		updateErrorField("Success. Welcome to UFS!");
		
		FirstNameField.clear();
		LastNameField.clear();
		EmailField.clear();
		
		this.FirstNameField.deselect();
		this.LastNameField.deselect();
		this.EmailField.selectEnd();
		this.EmailField.deselect();
		
		this.SignInButton.requestFocus();
	
		
		/*
		 * FXMLLoader loader = new FXMLLoader();
		 * loader.setLocation(Main.class.getResource("../WandShop.fxml"));
		 * this.anchorPane = (AnchorPane) loader.load(); Scene scene = new Scene(anchorPane, 800, 800); Main.mainStage.setScene(scene);
		 * Main.mainStage.show();
		 */
		
		/*
		Clipboard clipboard = Clipboard.getSystemClipboard();
		ClipboardContent content = new ClipboardContent();
		
		content.putString(temp.getEmailAddress());
		clipboard.setContent(content);
		*/
	}

	/**
	 * initialize() method - starts the controller
	 */
	public void initialize(URL location, ResourceBundle resources) {
			this.TitleLabel.setText("Meeting Sign In");
			this.UserNameLabel.setText("First Name:");
			this.PasswordLabel.setText("Last Name:");
			this.EmailLabel.setText("Email:     ");

			this.FirstNameField.setPromptText("e.g. Jordyn");
			this.LastNameField.setPromptText("e.g. Ruiz");
			this.EmailField.setPromptText("(First Time Only)");
			
			//Image image = new Image("file: " + "../../images/ufslog.png");
			//this.Image.setImage(image);
	}

}
