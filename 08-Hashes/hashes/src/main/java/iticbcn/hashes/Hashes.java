package iticbcn.hashes;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HexFormat;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Hashes {
    public int npass = 0;
    public String getSHA512AmbSalt(String pw, String salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = md.digest(pw.getBytes(StandardCharsets.UTF_8));
            HexFormat hex = HexFormat.of();
            String hash = hex.formatHex(bytes);
            return hash;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;    
    }
    public String getPBKDF2AmbSalt(String pw, String salt) {
        try {
            PBEKeySpec spec = new PBEKeySpec(pw.toCharArray(), salt.getBytes(), 1000, 512);
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
            byte[] bytes = skf.generateSecret(spec).getEncoded();
            HexFormat hex = HexFormat.of();
            String hash = hex.formatHex(bytes);
            return hash;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return null;
    }
    public String forcaBruta(String alg, String hash, String salt) {
        char[] fbPasswd = new char[6];
        String fbPasswdHash;
        char[] charset = "abcdefABCDEF1234567890!".toCharArray();
        for (int i = 0; i < charset.length ; i++) {
            npass++;
            fbPasswd[0] = charset[i];
            fbPasswdHash = (alg.equals("SHA-512")) ? getSHA512AmbSalt(new String(fbPasswd).substring(0, 1), salt) : getPBKDF2AmbSalt(new String(fbPasswd).substring(0, 1), salt);
            if (fbPasswdHash.equals(hash)) return new String(fbPasswd).substring(0, 1);
            for (int j = 0; j < charset.length ; j++) {
                npass++;
                fbPasswd[1] = charset[j];
                fbPasswdHash = (alg.equals("SHA-512")) ? getSHA512AmbSalt(new String(fbPasswd).substring(0, 2), salt) : getPBKDF2AmbSalt(new String(fbPasswd).substring(0, 2), salt);
                if (fbPasswdHash.equals(hash)) return new String(fbPasswd).substring(0, 2);
                for (int n = 0; n < charset.length ; n++) {
                    npass++;
                    fbPasswd[2] = charset[n];
                    fbPasswdHash = (alg.equals("SHA-512")) ? getSHA512AmbSalt(new String(fbPasswd).substring(0, 3), salt) : getPBKDF2AmbSalt(new String(fbPasswd).substring(0, 3), salt);
                    if (fbPasswdHash.equals(hash)) return new String(fbPasswd).substring(0, 3);
                    for (int m = 0; m < charset.length ; m++) {
                        npass++;
                        fbPasswd[3] = charset[m];
                        fbPasswdHash = (alg.equals("SHA-512")) ? getSHA512AmbSalt(new String(fbPasswd).substring(0, 4), salt) : getPBKDF2AmbSalt(new String(fbPasswd).substring(0, 4), salt);
                        if (fbPasswdHash.equals(hash)) return new String(fbPasswd).substring(0, 4);
                        for (int l = 0; l < charset.length ; l++) {
                            npass++;
                            fbPasswd[4] = charset[l];
                            fbPasswdHash = (alg.equals("SHA-512")) ? getSHA512AmbSalt(new String(fbPasswd).substring(0, 5), salt) : getPBKDF2AmbSalt(new String(fbPasswd).substring(0, 5), salt);
                            if (fbPasswdHash.equals(hash)) return new String(fbPasswd).substring(0, 5);
                            for (int b = 0; b < charset.length ; b++) {
                                npass++;
                                fbPasswd[5] = charset[b];
                                fbPasswdHash = (alg.equals("SHA-512")) ? getSHA512AmbSalt(new String(fbPasswd).substring(0, 6), salt) : getPBKDF2AmbSalt(new String(fbPasswd).substring(0, 6), salt);
                                if (fbPasswdHash.equals(hash)) return new String(fbPasswd).substring(0, 6);
                            }
                        }
                    }
                }
            }
            
        }
        return null;
    }
    public String getInterval(long t1, long t2) {
        long millis = t2 - t1;
        long seconds = millis / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        long days = hours / 24;
        return String.format("%d dies / %d hores / %d minuts / %d segons / %d millis",
                days, hours % 24, minutes % 60, seconds % 60, millis % 1000);
    }

    public static void main(String[] args) {
        String salt = "qpoweiruañslkdfjz";
        String pw = "ca";
        Hashes h = new Hashes();
        String[] aHashes = { h.getSHA512AmbSalt(pw, salt),
        h.getPBKDF2AmbSalt(pw, salt) };
        String pwTrobat = null;
        String[] algorismes = {"SHA-512", "PBKDF2"};
        for(int i=0; i< aHashes.length; i++){
        System.out.printf("===========================\n");
        System.out.printf("Algorisme: %s\n", algorismes[i]);
        System.out.printf("Hash: %s\n",aHashes[i]);
        System.out.printf("---------------------------\n");
        System.out.printf("-- Inici de força bruta ---\n");
        
        long t1 = System.currentTimeMillis();
        pwTrobat = h.forcaBruta(algorismes[i], aHashes[i], salt);
        long t2 = System.currentTimeMillis();
        
        System.out.printf("Pass : %s\n", pwTrobat);
        System.out.printf("Provats: %d\n", h.npass);
        System.out.printf("Temps : %s\n", h.getInterval(t1, t2));
        System.out.printf("---------------------------\n\n");
        }
    }
}
