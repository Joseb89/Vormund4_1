package JAAB.Encryption;

import java.io.File;
import java.io.IOException;

/**
 * This serves as a template to ensure all encryption classes have a encrypt and decrypt function
 */

public interface Encryptable {

    /**
     * Encrypts file contents using encryption key
     * @param file The file to be encrypted
     * @throws IOException if file is cannot be encrypted
     */
    void encrypt(File file) throws IOException;

    /**
     * Returns file to original contents
     * @param file The file tp be encrypted
     * @throws IOException if file cannot be decrypted
     */
    void decrypt(File file) throws IOException;

}
