package com.shadowthrones.ciphers;

public class RailFenceCipher extends Cipher {
    int letterPerBlock; boolean upper;
    RailFenceCipher(String plainText, String encryptedText, int letterPerBlock){
        super(plainText, encryptedText);
        this.letterPerBlock = letterPerBlock;
        upper = true;
    }

    @Override
    void encrpyt() {
        int length = plainText.length();

        String upperRail = new String();
        String lowerRail = new String();
        int i = 0;

        while(i<= length){
            if(upper == true){
                upper = false;
                try{
                    upperRail = upperRail.concat(plainText.substring(i, i+letterPerBlock));
                }
                catch (StringIndexOutOfBoundsException e){
                    int tempblock = length-i;
                    upperRail = upperRail.concat(plainText.substring(i, i+tempblock));
                }

                i+=letterPerBlock;
            }
            else{
                upper = true;
                try{
                    lowerRail = lowerRail.concat(plainText.substring(i, i+letterPerBlock));
                }
                catch (StringIndexOutOfBoundsException e){
                    int tempblock = length-i;
                    lowerRail = lowerRail.concat(plainText.substring(i, i+tempblock));
                }
                i+=letterPerBlock;
            }
        }
        encryptedText = upperRail.concat(lowerRail);
    }

    @Override
    void decrypt() {
        
    }
}
