package apps.g0rba4ev.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashGenerator {

    /**
     * generate SHA-256 hash of string
     * @param message to be hashed
     * @return hash string
     */
    public static String generateSHA256(String message) {
        return hashString(message, "SHA-256");
    }

    /**
     * generate SHA-256 hash of string
     * @param message to be hashed
     * @return hash string
     */
    public static String generateSHA1(String message) {
        return hashString(message, "SHA-1");
    }

    /**
     * generate hash string with required algorithm
     * @param message to be hashed
     * @param algorithm algorithm name for MessageDigest
     * @return String hash or null if an algorithm is not found
     */
    private static String hashString(String message, String algorithm) {
        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            byte[] hashedBytes = digest.digest(message.getBytes(StandardCharsets.UTF_8));

            return convertByteArrayToHexString(hashedBytes);
        } catch (NoSuchAlgorithmException ex) {
            return null;
        }
    }

    private static String convertByteArrayToHexString(byte[] arrayBytes) {
        StringBuilder strBuilder = new StringBuilder();
        for (byte arrayByte : arrayBytes) {
            strBuilder.append(Integer.toString((arrayByte & 0xff) + 0x100, 16).substring(1));
        }
        return strBuilder.toString();
    }
}
