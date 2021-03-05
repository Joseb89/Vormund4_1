package JAAB.Encryption;

import java.io.*;

/**
 * This class is used for the encryption and decryption of text files (*.txt)
 *
 *@version 1.0
 *@author Joseph Barr
 */

public class EncryptText extends Encrypt implements Encryptable {

    @Override
    public void encrypt(File file) throws IOException{

        if (checkFileStatus(file))
            em.isEncryptedError();

        else {

            readText(file);

            setKey();

            if (isEncrypted(file))
                writeText();
        }
    }

    @Override
    public void decrypt(File file) throws IOException {

        if (!checkFileStatus(file))
            em.isNotEncryptedError();

        else {
            readText(file);

            if (isDecrypted(file))
                writeText();
        }
    }

    /**
     * Read the text from text file and save it to StringBuilder
     * @param file The file to be read
     */
    private void readText(File file){

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            String readString;

            while ((readString = br.readLine()) != null)
                text.append(readString).append(newLine);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Write contents of appended StringBuilder to file
     */
    private void writeText(){

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(newFile))) {

            byte OFFSET = 2;

            for (int i = 0; i < text.length() - OFFSET; i++)
                fileContents.append((char) (text.charAt(i) ^ getKey()));

            bw.write(String.valueOf(fileContents));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
