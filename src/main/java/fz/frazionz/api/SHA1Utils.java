package fz.frazionz.api;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA1Utils {

    public static String calcSHA1(File file) throws FileNotFoundException,
            IOException, NoSuchAlgorithmException {

        MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
        try (InputStream input = new FileInputStream(file)) {

            byte[] buffer = new byte[8192];
            int len = input.read(buffer);

            while (len != -1) {
                sha1.update(buffer, 0, len);
                len = input.read(buffer);
            }

            return new HexBinaryAdapter().marshal(sha1.digest());
        }
    }

    public static boolean equals(File file, String sha1) {
        try {
            return equals(calcSHA1(file), sha1);
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean equals(String sha11, String sha12) {
        System.out.println(sha11 + " =?= " + sha12 + " = " + sha11.equalsIgnoreCase(sha12));
        return sha11.equalsIgnoreCase(sha12);
    }

}
