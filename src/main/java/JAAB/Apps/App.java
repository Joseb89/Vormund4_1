package JAAB.Apps;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This is an update of the previous version of Vormund. It now uses Apache Maven to allow the encryption and decryption
 * of Microsoft Office documents.
 *
 * @author Joseph Barr
 * @version 4.0
 *
 * Updates:
 * - Created new interface ("EncryptInt") to provide overloaded functions of 'encrypt' and 'decrypt'
 * - Created new classes for the encryption and decryption of different file types
 * - New GUI
 */
public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("VormundGUI3.fxml"));
        primaryStage.setTitle("Vormund");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}