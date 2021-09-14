package com.projekat.UPIB.security;

import com.projekat.UPIB.security.cerficate.CRLManager;
import com.projekat.UPIB.security.cerficate.CertificateGenerator;
import com.projekat.UPIB.security.cerficate.CertificateReader;
import com.projekat.UPIB.security.cerficate.SignedCertificateGenerator;
import com.projekat.UPIB.security.keystore.KeyStoreReader;
import com.projekat.UPIB.security.keystore.KeyStoreWriter;
import com.projekat.UPIB.security.model.IssuerData;
import com.projekat.UPIB.security.model.SubjectData;
import com.projekat.UPIB.security.signature.SignatureManager;
import com.projekat.UPIB.security.signature.SignatureManagerSignedObject;
import com.projekat.UPIB.security.cerficate.*;
import com.projekat.UPIB.security.util.Base64;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.X500NameBuilder;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.springframework.stereotype.Component;

import javax.crypto.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class EnkripcijaDekripcijaUtils {

    public static final String BASE64_ENC_CER_FILE = "./data/jovan2.cer";
    public static final String BIN_ENC_CER_FILE = "./data/jovan1.cer";

    private static final String KEY_STORE_FILE = "./data/test.jks";
    private static final String KEY_STORE_PASS = "test10";
    private static final String KEY_STORE_ALIAS = "test";
    private static final String KEY_STORE_PASS_FOR_PRIVATE_KEY = "test10";
    private static final String KEY_STORE_ALIAS_NEW = "test_NEW";
    private static final String KEY_STORE_DEFAULT_PASS_FOR_PRIVATE_KEYS = "lozinka123";
    private static final String KEY_STORE_ALIAS_NEW_SIGNED = "pera";
    private static final String KEY_STORE_PASS_FOR_PRIVATE_KEY_NEW_SIGNED = "pera";

    private static CertificateReader certificateReader = new CertificateReader();
    private static CertificateGenerator certificateGenerator = new CertificateGenerator();
    private static KeyStoreReader keyStoreReader = new KeyStoreReader();
    private static KeyStoreWriter keyStoreWriter = new KeyStoreWriter();
    private static SignedCertificateGenerator signedCertificateGenerator = new SignedCertificateGenerator();
    private static CRLManager CRLManager = new CRLManager();
    private static SignatureManager signatureManager = new SignatureManager();
    private static SignatureManagerSignedObject signatureManagerSignedObject = new SignatureManagerSignedObject();


    public String dekriptujJBZO(String enkriptovaniJBZO, String emailAsAlias) {
        byte[] dekriptovanText = null;
        KeyStore keyStore = keyStoreWriter.loadKeyStore(KEY_STORE_FILE, KEY_STORE_PASS.toCharArray());
        PrivateKey privateKey = keyStoreReader.getPrivateKeyFromKeyStore(keyStore, emailAsAlias, KEY_STORE_DEFAULT_PASS_FOR_PRIVATE_KEYS.toCharArray());
        try {
            //dekriptovanje poruke privatnim klucem
            Cipher rsaCipherDec = Cipher.getInstance("RSA/ECB/PKCS1Padding", "BC");
            //inicijalizacija za dekriptovanje
            //dekriptovanje se vrsi privatnim kljucem
            rsaCipherDec.init(Cipher.DECRYPT_MODE, privateKey);

            //dekriptovanje
            dekriptovanText = rsaCipherDec.doFinal(Base64.decode(enkriptovaniJBZO));
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return new String(dekriptovanText);
    }

    public String enkriptujJBZO(String JBZO, String emailAsAlias) {
        upisiSertifikatUJKS(emailAsAlias, KEY_STORE_DEFAULT_PASS_FOR_PRIVATE_KEYS);
        Certificate certificate = ucitajSertifikat(emailAsAlias);
        PublicKey publicKey = keyStoreReader.getPublicKeyFromCertificate(certificate);
        byte[] ciphertext = null;
        try {
            Cipher rsaCipherEnc = Cipher.getInstance("RSA/ECB/PKCS1Padding", "BC");
            rsaCipherEnc.init(Cipher.ENCRYPT_MODE, publicKey);
            ciphertext = rsaCipherEnc.doFinal(JBZO.getBytes());
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return Base64.encodeToString(ciphertext);
    }

    private Certificate ucitajSertifikat(String emailAsAlias) {
        // ucitavanje sertifikata
        KeyStore keyStore = keyStoreReader.readKeyStore(KEY_STORE_FILE, KEY_STORE_PASS.toCharArray());
        // preuzimanje sertifikata iz KeyStore-a za zeljeni alias
        Certificate certificate = keyStoreReader.getCertificateFromKeyStore(keyStore, emailAsAlias);

        return certificate;
    }

    private static void upisiSertifikatUJKS(String keyStoreAlias, String lozinka) {
        // generisemo par kljuceva za seritifkat koji se generise
        KeyPair keyPair = certificateGenerator.generateKeyPair();

        // generisemo Self-Signed sertifikat
        X509Certificate certificate = generateSelfSignedCertificate(keyPair);

        // ucitavanje KeyStore fajla
        keyStoreWriter.loadKeyStore(KEY_STORE_FILE, KEY_STORE_PASS.toCharArray());

        // ucitavanje KeyStore fajla
        KeyStore keyStore = keyStoreWriter.loadKeyStore(KEY_STORE_FILE, KEY_STORE_PASS.toCharArray());

        // upisivanje u KeyStore, dodaju se kljuc i sertifikat
        // TODO: Ove ispraviti tako da KEY_STORE_ALIAS_NEW bude mejl odredjenog pacijenta.
        keyStoreWriter.addToKeyStore(keyStore, keyStoreAlias, keyPair.getPrivate(), lozinka.toCharArray(), certificate);

        // cuvanje izmena na disku
        keyStoreWriter.saveKeyStore(keyStore, KEY_STORE_FILE, KEY_STORE_PASS.toCharArray());
    }



    /**
     * Metoda sluzi za generisanje self-signed sertifikata,
     *
     * @return Self-Signed X509 sertifikat
     */
    private static X509Certificate generateSelfSignedCertificate(KeyPair keyPair) {

        /// postavljanje datuma vazenja sertifikata
        SimpleDateFormat iso8601Formater = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = new Date();
        Date endDate = new Date();
        try {
            startDate = iso8601Formater.parse("2020-04-01");
            endDate = iso8601Formater.parse("2025-04-01");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // podaci o vlasniku i izdavacu posto je self signed
        X500NameBuilder builder = new X500NameBuilder(BCStyle.INSTANCE);
        builder.addRDN(BCStyle.CN, "Milos Vlku"); 		// Common Name; puno ime pravnog ili privatnog lica kojem se sertifikat izdaj  (vlasnika sertifikata)
        builder.addRDN(BCStyle.SURNAME, "Vlku"); 		// prezime lica kojem se sertifikat izdaje
        builder.addRDN(BCStyle.GIVENNAME, "Milos"); 		// ime lica kojem se sertifikat izdaje
        builder.addRDN(BCStyle.O, "UNS-FTN"); 			// Organization; naziv organizacije lica kojem se sertifikat izdaje
        builder.addRDN(BCStyle.OU, "Katedra za informatiku"); // Organization Unit; naziv organizacione jedinice lica kojem se sertifikat izdaje
        builder.addRDN(BCStyle.C, "RS"); 				// Country; kod zemlje
        builder.addRDN(BCStyle.E, "milosvlku@hotmail.com"); 	// email vlasnika sertifikata
        builder.addRDN(BCStyle.UID, "12345"); 			// UID (User ID) je ID korisnika

        // serijski broj sertifikata
        String serialNumber = "1";

        // klasa X500NameBuilder pravi X500Name objekat koji nam treba u klasama modela (IssuerData i SubjectData)
        // posto je u pitanju self-signed sertifikat, X500Name ce biti isti i za izdavaca i za vlasnika sertifikata
        X500Name x500Name = builder.build();

        // kreiraju se podaci za izdavaca
        IssuerData issuerData = new IssuerData(keyPair.getPrivate(), x500Name);

        // kreiraju se podaci za vlasnika
        SubjectData subjectData = new SubjectData(keyPair.getPublic(), x500Name, serialNumber, startDate, endDate);

        // generise se sertifikat
        return certificateGenerator.generateCertificate(issuerData, subjectData);
    }

    /**
     * Metoda koja na konzoli ispisuje sertifikat
     *
     * @param certificate - sertifikat koji zelimo da ispisemo
     */
    private static void printCertificate(X509Certificate certificate) {
        System.out.println("ISSUER: " + certificate.getIssuerX500Principal().getName());
        System.out.println("SUBJECT: " + certificate.getSubjectX500Principal().getName());
        System.out.println("Sertifikat:");
        System.out.println("--------------------------------------------------------------------------------------------------------------");
        System.out.println(certificate);
        System.out.println("--------------------------------------------------------------------------------------------------------------");
        System.out.println("--------------------------------------------------------------------------------------------------------------");
    }

    /**
     * Metoda koja sluzi za proveru da li je sertifikat ispravan.
     *
     * @param certificate - Sertifikat koji se validira
     * @param publicKey - javni kljuc koji se vrsi validacija sertifikata
     *
     * Sertifikat se potpisuje privatnim kljucem izdavaca. Sertifikat jedino uspeÅ¡no moÅ¾e da se validira javnim kljucem izdavaca!
     */
    private static boolean validateCertificate(X509Certificate certificate, PublicKey publicKey) {

        // ako validacija nije uspesna desice se exception
        try {
            certificate.verify(publicKey);
            System.out.println("\nVALIDACIJA SERTIFIKATA USPESNA!");
            return true;
        } catch (InvalidKeyException | CertificateException | NoSuchAlgorithmException | NoSuchProviderException
                | SignatureException e) {
            System.out.println("\nVALIDACIJA SERTIFIKATA NIJE USPESNA!");
            return false;
        }
    }

}
