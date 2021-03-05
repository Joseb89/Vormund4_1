package JAAB.Encryption;

import org.apache.poi.xwpf.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * This class is used for the encryption and decryption of Microsoft Word documents (*.doc, *.docx)
 *
 *@version 1.0
 *@author Joseph Barr
 */

public class EncryptWord extends Encrypt implements Encryptable {

    @Override
    public void encrypt(File file) throws IOException {

        if (checkFileStatus(file))
            em.isEncryptedError();

        else {

            readWord(file);

            if (isEncrypted(file))
                writeWord();
        }
    }

    @Override
    public void decrypt(File file) throws IOException {

        if (!checkFileStatus(file))
            em.isNotEncryptedError();

        else {

            readWord(file);

            if (isDecrypted(file))
                writeWord();

        }
    }

    /**
     * Read the text from Word document and save it to StringBuilder
     * @param file The file to be read
     */
    private void readWord(File file) {

        try (FileInputStream fis = new FileInputStream(file)) {

            XWPFDocument readDocument = new XWPFDocument(fis);

            List<XWPFParagraph> paragraphList = readDocument.getParagraphs();

            for (XWPFParagraph paragraph : paragraphList){

                for (XWPFRun run : paragraph.getRuns()){

                    int textPos = run.getTextPosition();

                    String runText = run.getText(textPos);

                    if (runText.contains("\n"))
                        run.addBreak();

                    text.append(runText);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Write contents of appended StringBuilder to file
     */
    private void writeWord() {

        try (FileOutputStream fos = new FileOutputStream(newFile)) {

            XWPFDocument writeDocument = new XWPFDocument();
            XWPFParagraph paragraph = writeDocument.createParagraph();
            XWPFRun run = paragraph.createRun();

            for (int i = 0; i < text.length(); i++)
                fileContents.append((char) (text.charAt(i) ^ getKey()));

            run.setText(String.valueOf(fileContents));
            writeDocument.write(fos);
            writeDocument.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
