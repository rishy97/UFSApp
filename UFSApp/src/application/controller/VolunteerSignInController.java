/** LoginController.java - Controller for the Login.fxml
 * @author xyg752
 * Rishabh Monga
 */
package application.controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import java.io.File;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

public class VolunteerSignInController implements Initializable {
	File[] listOfFiles;
	String[] fileNames;
	int fileIndex;
	int oldFileIndex;
	Event currentEvent;

	@FXML
	private Button CreateEventButton;

	@FXML
	private ImageView Background;

	@FXML
	private TextField FirstNameField;

	@FXML
	private TextField LastNameField;

	@FXML
	private Button DeleteEventButton;

	@FXML
	private ImageView Image;

	@FXML
	private Label PasswordLabel;

	@FXML
	private Label ErrorLabel;

	@FXML
	private ComboBox<String> SelectEvent;

	@FXML
	private TextArea VolunteersTextField;

	@FXML
	private Label EmailLabel;

	@FXML
	private Button EnterButton;

	@FXML
	private Label TitleLabel;

	@FXML
	private RadioButton SignInRadio;

	@FXML
	private RadioButton SignOutRadio;

	@FXML
	private TextField EmailField;

	@FXML
	private Label UserNameLabel;

	@FXML
	private Button SignAllOutButton;

	private AnchorPane anchorPane;
	
	public void updateVolunteersField() throws IOException {
		VolunteersTextField.setText("List of Volunteers:" + "\t\t\t\t " + TimeComparer.returnPMFixed(currentEvent.getStartTime()) + " - " + TimeComparer.returnPMFixed(currentEvent.getEndTime()) + "\n---------------------------------------------------------------\n");
		VolunteersTextField.appendText(currentEvent.displayVolunteers());
		currentEvent.saveEvent(listOfFiles[oldFileIndex].getPath());
	}

	public void updateErrorField(String entry) throws InterruptedException {
		ErrorLabel.setText("*" + entry + "*");
		int time = 3;

		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 1; i++) {
					try {
						Thread.sleep(time * 800);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							if (ErrorLabel.getText().equals("*" + entry + "*"))
								ErrorLabel.setText("");
						}
					});
				}
			}
		}).start();
	}

	@FXML
	public void onEnter(ActionEvent event) throws IOException, InterruptedException {

		if (!FirstNameField.getText().isEmpty()) {
			if (FirstNameField.getText().toLowerCase().equals("home")) {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(Main.class.getResource("../Home.fxml"));
				this.anchorPane = (AnchorPane) loader.load();
				Scene scene = new Scene(anchorPane, 1280, 720);
				Main.mainStage.setScene(scene);
				Main.mainStage.show();
			}
		}

		if (!FirstNameField.getText().isEmpty()) {
			if (FirstNameField.getText().contains(",")) {
				updateErrorField("First Name cannot contain commas");
				return;
			}
		}
		if (!LastNameField.getText().isEmpty()) {
			if (LastNameField.getText().contains(",")) {
				updateErrorField("Last Name cannot contain commas");
				return;
			}
		}

		if (!EmailField.getText().isEmpty()) {
			if (EmailField.getText().contains(",")) {
				updateErrorField("Email cannot contain commas");
				return;
			}
		}

		if (FirstNameField.getText().isEmpty() || LastNameField.getText().isEmpty()) {
			updateErrorField("Please complete the fields.");
			return;
		}

		if (Main.ufs.findUser(FirstNameField.getText(), LastNameField.getText()) == null
				&& EmailField.getText().isEmpty()) {
			updateErrorField("New User, Please Enter Email");
			return;
		}

		if (!SignInRadio.isSelected() && !SignOutRadio.isSelected()) {
			updateErrorField("Please Sign In or Sign Out");
			return;
		}

		if (Main.ufs.findUser(FirstNameField.getText(), LastNameField.getText()) != null) {
			
			if (SignInRadio.isSelected()) {
				User temp = Main.ufs.findUser(FirstNameField.getText(), LastNameField.getText());
				
				for( Volunteer currentVolunteer : currentEvent.getVolunteers()) {
					if( currentVolunteer.getFirstName().equals(FirstNameField.getText()) &&  currentVolunteer.getLastName().equals(LastNameField.getText()) ) {
						currentVolunteer.setStartTimeToNow();
						updateVolunteersField();
						break;
					}
				}

				temp.setVolunteerEvents(temp.getVolunteerEvents() + 1);
				temp.setLastVolunteerEventToToday();
				updateErrorField("Success! Thank You For Helping!");
				Main.ufs.saveUsers("data/users.csv");

				FirstNameField.clear();
				LastNameField.clear();
				EmailField.clear();

				this.FirstNameField.deselect();
				this.LastNameField.deselect();
				this.EmailField.selectEnd();
				this.EmailField.deselect();

				this.EnterButton.requestFocus();

				return;
			} else if ( SignOutRadio.isSelected()) {
				User temp = Main.ufs.findUser(FirstNameField.getText(), LastNameField.getText());

				updateErrorField("Goodbye!");
				
				for( Volunteer currentVolunteer : currentEvent.getVolunteers()) {
					if( currentVolunteer.getFirstName().equals(temp.getFirstName()) && currentVolunteer.getLastName().equals(temp.getLastName()) ) {
						currentVolunteer.setEndTimeToNow();
						updateVolunteersField();
						break;
					}
				}
				//Main.ufs.saveUsers("data/users.csv");

				FirstNameField.clear();
				LastNameField.clear();
				EmailField.clear();

				this.FirstNameField.deselect();
				this.LastNameField.deselect();
				this.EmailField.selectEnd();
				this.EmailField.deselect();

				this.EnterButton.requestFocus();

				return;
			}
		}
		
		if (SignOutRadio.isSelected()) {
			updateErrorField("Please Join the organization");
			return;
		}

		Main.ufs.addUser(FirstNameField.getText(), LastNameField.getText(), EmailField.getText());

		User temp = Main.ufs.findUser(FirstNameField.getText(), LastNameField.getText());
		temp.setVolunteerEvents(1);
		temp.setLastVolunteerEventToToday();
		Main.ufs.saveUsers("data/users.csv");

		Volunteer newVolunteer = new Volunteer(temp.getFirstName(), temp.getLastName());
		newVolunteer.setStartTimeToNow();
		currentEvent.addVolunteer(newVolunteer);
		updateVolunteersField();
		
		updateErrorField("Success. Welcome to UFS!");

		FirstNameField.clear();
		LastNameField.clear();
		EmailField.clear();

		this.FirstNameField.deselect();
		this.LastNameField.deselect();
		this.EmailField.selectEnd();
		this.EmailField.deselect();

		this.EnterButton.requestFocus();

		/*
		 * FXMLLoader loader = new FXMLLoader();
		 * loader.setLocation(Main.class.getResource("../WandShop.fxml"));
		 * this.anchorPane = (AnchorPane) loader.load(); Scene scene = new
		 * Scene(anchorPane, 800, 800); Main.mainStage.setScene(scene);
		 * Main.mainStage.show();
		 */

		/*
		 * Clipboard clipboard = Clipboard.getSystemClipboard(); ClipboardContent
		 * content = new ClipboardContent();
		 * 
		 * content.putString(temp.getEmailAddress()); clipboard.setContent(content);
		 */
	}

	@FXML
	public void onSignAllOut(ActionEvent event) {

	}

	@FXML
	public void onCreate(ActionEvent event) {

	}

	@FXML
	public void onDelete(ActionEvent event) {

	}
	
	@FXML
	public void onCombo(ActionEvent event) throws IOException {
		for( int i = 0; i < fileNames.length; i++ ) {
			if ( fileNames[i].equals(SelectEvent.getValue()) ) {
				currentEvent = new Event();
				currentEvent.loadEvent(listOfFiles[i].getPath());
				updateVolunteersField();
				if (fileIndex != -1 ) {
					oldFileIndex = fileIndex;
				}
				fileIndex = i;
				return;
			}
		}
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
		this.EmailField.setPromptText("(Optional)");

		this.FirstNameField.setFocusTraversable(false);
		this.LastNameField.setFocusTraversable(false);
		this.EmailField.setFocusTraversable(false);
		this.EnterButton.setFocusTraversable(false);
		
		VolunteersTextField.setEditable(false);

		currentEvent = new Event();
		fileIndex = -1;
		oldFileIndex = -1;
		ToggleGroup group = new ToggleGroup();

		SignInRadio.setToggleGroup(group);
		SignOutRadio.setToggleGroup(group);

		File folder = new File("data/events");
		listOfFiles = folder.listFiles();
		fileNames = new String[listOfFiles.length];

		ObservableList<String> options = FXCollections.observableArrayList();

		for (int i = 0; i < listOfFiles.length; i++) {
			String name = listOfFiles[i].getName();
			fileNames[i] = name.substring(0, name.length() - 4);
			options.add(fileNames[i]);
		}

		SelectEvent.setItems(options);
	}
}
