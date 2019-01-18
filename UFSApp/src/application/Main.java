/** Main.java - class that begins the application
 * @author xyg752
 * Rishabh Monga
 */
package application;
	
import application.model.Organization;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public class Main extends Application {
	public AnchorPane anchorpane;
	public static Stage mainStage;
	public static Organization ufs;
	
	/** start method - begins the application
	 * @param primaryStage - Stage
	 */
	@Override
	public void start(Stage primaryStage) {
		mainStage = primaryStage;
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation( Main.class.getResource("../Home.fxml"));
			this.anchorpane = (AnchorPane) loader.load();
			Scene scene = new Scene(anchorpane,1280,720);
			primaryStage.setScene(scene);
			primaryStage.centerOnScreen();
			primaryStage.show();
			ufs = new Organization("Unite For Sight");
			ufs.loadUsers("data/users.csv");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/** main method
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}