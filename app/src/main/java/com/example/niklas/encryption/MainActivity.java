package com.example.niklas.encryption;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //button
        Button b1 = (Button) findViewById(R.id.button);
        b1.setOnClickListener(myhandler);

    }

    View.OnClickListener myhandler = new View.OnClickListener() {
        public void onClick(View v) {
            //editText
            EditText input = (EditText) findViewById(R.id.editText);
            String text = input.getText().toString();
            Log.v("Text", "Text: " + text);
            try {

                //Encryption Objekt erstellen
                Encryption encryption = new Encryption(Encryption.newAESkey(256),"AES");
                String encrypted = encryption.encrypt(text);
                Log.v("Encrypted", "Encrypted text: " + encrypted);
                String decrypted = encryption.decrypt(encrypted);
                Log.v("Decrypted", "Decrypted text: " + decrypted);

                KeyPair rsaKeypair = Encryption.newRSAkeys(2048);
                Encryption rsaPrivate = new Encryption(rsaKeypair.getPrivate(),"RSA");
                Encryption rsaPublic  = new Encryption(rsaKeypair.getPublic(),"RSA");

                Log.v("RSA - text", "Text: " + text);
                String rsaEncrypt = rsaPrivate.encrypt(text);
                String result = rsaPublic.decrypt(rsaEncrypt);
                Log.v("RSA - encrypted", "Encrypted text: " + rsaEncrypt);
                Log.v("RSA - decrypted", "Decrypted text: " + result);



            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (BadPaddingException e) {
                e.printStackTrace();
            } catch (IllegalBlockSizeException e) {
                e.printStackTrace();
            } catch (NoSuchPaddingException e) {
                e.printStackTrace();
            } catch (InvalidKeyException e) {
                e.printStackTrace();
            }

        }
    };

}
