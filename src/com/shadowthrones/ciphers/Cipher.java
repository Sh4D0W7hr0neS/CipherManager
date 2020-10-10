package com.shadowthrones.ciphers;

public abstract class Cipher {
    String plainText;
    String encryptedText;

    //Constructors
    Cipher(String plainText, String encryptedText){
        this.plainText = plainText;
        this.encryptedText = encryptedText;
    }
    abstract void encrpyt();
    abstract void decrypt();
    public String getEncryptedText(){
        return encryptedText;
    }
    public String getPlainText(){
        return  plainText;
    }
}
