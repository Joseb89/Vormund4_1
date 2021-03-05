package JAAB.Apps;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This class serves as a Controller for the various error message .fxml files
 *
 * Updates:
 * -Created a function ('loadErrorFXML') to create and load the appropriate .fxml file
 *
 * @author Joseph Barr
 * @version 1.1
 */

public class ErrorMessages {

    /**
     * Loads the appropriate .fxml file for the needed error message
     * @param fxmlName The name of the .fxml file
     * @throws IOException if .fxml file is not found
     */
    private void loadErrorFXML(String fxmlName) throws IOException {

        Stage primaryStage = new Stage();

        Parent root = FXMLLoader.load(getClass().getResource(fxmlName));
        primaryStage.setTitle("Error");
        primaryStage.setScene(new Scene(root, 300, 200));
        primaryStage.show();
    }

    /**
     * Returns if no file is selected or file is not found
     * @throws IOException if .fxml file is not found
     */
    public void fileNotFoundError() throws IOException { loadErrorFXML("FileNotFound.fxml"); }

    /**
     * Returns if selected file is not supported
     * @throws IOException if .fxml file is not found
     */
    public void fileNotRecognizedError() throws IOException {loadErrorFXML("FileNotRecognized.fxml");}

    /**
     * Returns if selected file is already encrypted and user attempts to encrypt
     * @throws IOException if .fxml file is not found
     */
    public void isEncryptedError() throws IOException { loadErrorFXML("FileIsEncrypted.fxml"); }

    /**
     * Returns if selected file is not encrypted and user attempts to decrypt
     * @throws IOException if .fxml file is not found
     */
    public void isNotEncryptedError() throws IOException { loadErrorFXML("FileNotEncrypted.fxml"); }
}
