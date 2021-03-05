package JAAB.Encryption;

import JAAB.Apps.ErrorMessages;

import java.io.*;
import java.util.Random;

public abstract class Encrypt {

    public static final StringBuilder text = new StringBuilder(); //Used to store text from reading file
    public static final StringBuilder fileContents = new StringBuilder(); //Used to append 'text' with the key

    public static File newFile = null; //Renamed file after encryption or decryption
    private final String encrypted = "_encrypted";

    String newLine = System.lineSeparator();

    ErrorMessages em = new ErrorMessages();

    /**
     * Renames the file to 'filename_encrypted' if file is encrypted
     *
     * @param file File to be renamed
     * @return true and renames file
     */
    public boolean isEncrypted(File file) {

        int index = file.getName().lastIndexOf('.'); //Reads file name up to file extension

        //Adds "_encrypted" to the end of the file name
        newFile = new File(file.getPath().replace(file.getName().substring(0, index),
                file.getName().substring(0, index) + encrypted));

        return file.renameTo(newFile);
    }

    /**
     * Renames the file to original name if file is decrypted
     *
     * @param file File to be renamed
     * @return true and renames file
     */
    public boolean isDecrypted(File file) {

        //Removes "_encrypted" from the file name
        newFile = new File(file.getPath().replace(encrypted, ""));

        return file.renameTo(newFile);
    }

    /**
     * Checks to see if file contains "_encrypted", indicating the file is encrypted
     * @param file  The selected file
     * @return true if file name contains "_encrypted"
     */
    public boolean checkFileStatus(File file){return file.getName().contains(encrypted);}

    /**
     * Establishes a key using 'Random' and exports it to 'Key.dat' for later retrieval
     *
     * @throws IOException if file cannot be saved
     */
    public void setKey() throws IOException {

        Random rand = new Random();

        long key = rand.nextLong();

        try (BufferedWriter bufferedWriter = new BufferedWriter(
                new FileWriter("C:\\Users\\jbarr\\OneDrive\\Documents\\Vormund\\Key.dat"))) {

            bufferedWriter.write(Long.toString(key));
        }
    }

    /**
     * Reads 'Key.dat' to retrieve key for encryption and decryption
     *
     * @return The encryption key
     */
    public long getKey() {

        long key = 0;

        try (BufferedReader bufferedReader = new BufferedReader(
                new FileReader("C:\\Users\\jbarr\\OneDrive\\Documents\\Vormund\\Key.dat"))) {

            key = Long.parseLong(bufferedReader.readLine());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return key;
    }
}
