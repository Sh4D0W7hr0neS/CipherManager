package com.shadowthrones.ciphers;

public class test {
    public static void main(String args[]){
        CaesarCipher cip = new CaesarCipher("Tf Uhtl pz Zhyhuo");
        cip.crack();
        System.out.println(cip.getPlainText());

    }
}
