package com.example.demo;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.owasp.esapi.ESAPI;
import org.owasp.esapi.codecs.Codec;
import org.owasp.esapi.codecs.MySQLCodec;
import org.owasp.esapi.errors.IntrusionException;
import org.owasp.esapi.errors.ValidationException;
import org.owasp.html.HtmlPolicyBuilder;
import org.owasp.html.PolicyFactory;
import org.springframework.stereotype.Component;

public class Cleanup{

    public void canonic(String dirtyString)
    {
        String stringFiltered = ESAPI.encoder().canonicalize(dirtyString);

        System.out.println("\nCANONIC - Dirty: " + stringFiltered);

    }

    public void validate(String dirtyString)
    {
        System.out.println("\nVALID - EXCEPT: ");

        try {

            ESAPI.validator().getValidInput("Validation", dirtyString, "SafeString", 1024, false);

        } catch (ValidationException | IntrusionException ex) {
            Logger.getLogger(Cleanup.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sanit(String dirtyString)
    {

        PolicyFactory policy = new HtmlPolicyBuilder().toFactory();

        String sanit = policy.sanitize(dirtyString);

        System.out.println("\nSANIT - Dirty: " + dirtyString + " - Sanit: " + sanit);

    }

    public void encod(String dirtyString, String type)
    {
        String ret = null;

        switch (type.toLowerCase()) {
            case "js":
                ret = ESAPI.encoder().encodeForJavaScript(dirtyString);
                break;

            case "html":
                ret = ESAPI.encoder().encodeForHTML(dirtyString);
                break;

            case "htmlatt":
                ret = ESAPI.encoder().encodeForHTMLAttribute(dirtyString);
                break;

            case "css":
                ret = ESAPI.encoder().encodeForCSS(dirtyString);
                break;

            case "sql":
                //MYSQL-MODE=EMBARALHA TEXTO
                Codec codecSQL = new MySQLCodec(MySQLCodec.MYSQL_MODE);

                //ANSI-MODE=TEXTO COMUM, NAO EMBARALHA
                //Codec codecSQL = new MySQLCodec(MySQLCodec.ANSI_MODE);
                ret = ESAPI.encoder().encodeForSQL(codecSQL, dirtyString);
                break;

        }

        System.out.println("\nENCOD - Dirty: " + dirtyString + " - Encod: " + ret);
    }

}
