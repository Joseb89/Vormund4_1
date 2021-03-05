package JAAB.Apps;

import JAAB.Encryption.EncryptText;
import JAAB.Encryption.EncryptWord;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class PrimaryController {

    @FXML
    private TextField fileSelect;

    private static File selectedFile;

    EncryptText et = new EncryptText();
    EncryptWord ew = new EncryptWord();

    ErrorMessages em = new ErrorMessages();

    /**
     * Opens the File Chooser window to select the desired file.
     * @return The file selected from the File Chooser
     * @throws IOException if file cannot be found
     */
    public File openFile() throws IOException {

        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Word Document", "*.doc", "*.docx"),
                                        new FileChooser.ExtensionFilter("Text Files", "*.txt"));

        selectedFile = fc.showOpenDialog(null);

        if(selectedFile != null)
            fileSelect.setText(selectedFile.getAbsolutePath());

        else
            em.fileNotFoundError();

        return selectedFile;
    }

    /**
     * Encrypts the file upon pressing the "Encrypt" button or "Edit -> Encrypt"
     * @throws IOException if file is not found or recognized
     */
    public void encrypt() throws IOException {

        //Selected file is Word document
        if (selectedFile.getAbsolutePath().contains(".docx") || selectedFile.getAbsolutePath().contains(".doc"))
            ew.encrypt(selectedFile);

        //Selected file is Text file
        else if (selectedFile.getAbsolutePath().contains(".txt"))
            et.encrypt(selectedFile);

        else
            System.out.println("File not recognized.");

        fileSelect.clear();
    }

    /**
     * Decrypts the file upon pressing the "Decrypt" button or "Edit -> Decrypt"
     * @throws IOException if file is not found or recognized
     */
    public void decrypt() throws IOException {

        if (selectedFile.getAbsolutePath().contains(".docx") || selectedFile.getAbsolutePath().contains(".doc"))
            ew.decrypt(selectedFile);

        else if (selectedFile.getAbsolutePath().contains(".txt"))
            et.decrypt(selectedFile);

        else
            em.fileNotRecognizedError();

        fileSelect.clear();
    }

    /**
     * Closes the application via "File -> Close"
     */
    public void closeApp(){

        Platform.exit();
        System.exit(0);
    }

    /**
     * Opens the "About" window via "Help -> About"
     * @throws IOException if unable to launch .fxml file
     */
    public void openAbout() throws IOException {

        Stage primaryStage = new Stage();

        Parent root = FXMLLoader.load(getClass().getResource("About.fxml"));
        primaryStage.setTitle("About");
        primaryStage.setScene(new Scene(root, 300, 200));
        primaryStage.show();
    }
}
