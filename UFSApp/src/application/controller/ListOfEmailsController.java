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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

public class ListOfEmailsController implements Initializable {
	@FXML
	private Button copyAllButton;

	@FXML
	private ImageView Background;

	@FXML
	private Button backButton;

	@FXML
	private TextArea EmailsArea;

	@FXML
	private AnchorPane anchorPane;
	
	@FXML
	void copyAllEmails(ActionEvent event) {
		Clipboard clipboard = Clipboard.getSystemClipboard();
		ClipboardContent content = new ClipboardContent();
		
		content.putString(Main.ufs.getEmailAddreses());
		clipboard.setContent(content);
	}

	@FXML
	void goBack(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("../Home.fxml"));
		this.anchorPane = (AnchorPane) loader.load(); Scene scene = new Scene(anchorPane, 1280,720); Main.mainStage.setScene(scene);
		Main.mainStage.show();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		EmailsArea.setText(Main.ufs.getEmailAddreses());
	}

}
