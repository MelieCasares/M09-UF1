package iticbcn.xifratge;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;

public class ClauPublica {
    public KeyPair generaParellClausRSA() throws Exception {
        try {
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");

            
            keyPairGen.initialize(2048);

            KeyPair pair = keyPairGen.generateKeyPair();
    
            return pair;

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public byte[] xifraRSA(String msg, PublicKey clauPublica) throws Exception {
        try {
            Cipher rsa;
            rsa = Cipher.getInstance("RSA");
            rsa.init(Cipher.ENCRYPT_MODE, clauPublica);
            return rsa.doFinal(msg.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String desxifraRSA(byte[] msgXifrat, PrivateKey ClauPrivada) {
        try {
            Cipher rsa;
            rsa = Cipher.getInstance("RSA");
            rsa.init(Cipher.DECRYPT_MODE, ClauPrivada);
            byte[] utf8 = rsa.doFinal(msgXifrat);
            return new String(utf8, "UTF8");
        } catch (Exception e) {
                e.printStackTrace();
        }
        return null;
    }
}
