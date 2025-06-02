package org.example.pharmacix;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class Chiffreur {

    private static final String CLE = "VivelebtsSio14@!"; // 16 caractères

    public static void main(String[] args) throws Exception {
        byte[] data = Files.readAllBytes(Paths.get("config.properties"));
        byte[] encrypted = chiffrer(data, CLE);
        Files.write(Paths.get("config.enc"), encrypted);
        System.out.println("Fichier chiffré -> config.enc");
    }

    private static byte[] chiffrer(byte[] data, String cle) throws Exception {
        SecretKeySpec key = new SecretKeySpec(cle.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(data);
    }
}
