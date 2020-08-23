package com.shadowthrones.ciphers;

public class CaesarCipher extends Cipher {
    int swiftValue;
    double entropy;

    //Constructors
    CaesarCipher(String plainText, String encryptedText, int swiftValue){
        super(plainText, encryptedText);
        this.swiftValue = swiftValue;
        entropy = 0;
    }
    CaesarCipher(String encryptedText){
        super(null, encryptedText);
        entropy = 0;
    }

    //Methods
    void encrypt(){
        int length = plainText.length();
        char[] temp = new char[length];
        temp = plainText.toCharArray();

        for(int i = 0; i<length; i++){
            if(temp[i] >= 65 && temp[i]<= 90){
                temp[i] = (char) (temp[i] + swiftValue);
                if(temp[i] > 90){
                    temp[i] = (char)(temp[i] - 26);
                }
            }
            else if(temp[i] >= 97 && temp[i]<= 122){
                temp[i] = (char) (temp[i] + swiftValue);
                if(temp[i] > 122){
                    temp[i] = (char)(temp[i] - 26);
                }
            }
        }
        encryptedText = String.valueOf(temp);
    }

    void decrypt(){
        int length = encryptedText.length();
        char[] temp = new char[length];
        temp = encryptedText.toCharArray();

        for(int i = 0; i<length; i++){
            if(temp[i] >= 65 && temp[i]<= 90){
                temp[i] = (char) (temp[i] - swiftValue);
                if(temp[i] < 65){
                    temp[i] = (char)(temp[i] + 26);
                }
                entropy = entropy + (EngFreqs.characterFreqs[temp[i]-65] * EngFreqs.characterLogFreqs[temp[i]-65] * -1);
            }
            else if(temp[i] >= 97 && temp[i]<= 122){
                temp[i] = (char) (temp[i] - swiftValue);
                if(temp[i] < 97){
                    temp[i] = (char)(temp[i] + 26);
                }
                entropy = entropy+ (EngFreqs.characterFreqs[temp[i]-97] * EngFreqs.characterLogFreqs[temp[i]-97] * -1);
            }
        }
        plainText = String.valueOf(temp);
    }
    void crack(){
        CaesarCipher[] temp = new CaesarCipher[26];

        temp[0] = new CaesarCipher(null, encryptedText, 1);
        temp[0].decrypt();
        System.out.println("Entropy of Swift Value " + 1 +  " is "  +temp[0].entropy);

        double entropyMax = temp[0].entropy;
        int maxIndex = 0;

        for(int i = 1; i<26; i++){
            temp[i] = new CaesarCipher(null, encryptedText, i+1);
            temp[i].decrypt();
            System.out.println("Entropy of Swift Value " + (i+1) +  " is "  +temp[i].entropy);
            if(temp[i].entropy > entropyMax){
                entropyMax = temp[i].entropy;
                maxIndex = i;
            }
        }
        swiftValue = maxIndex + 1;
        this.decrypt();
    }

    public String getEncryptedText(){
        return encryptedText;
    }
    public String getPlainText(){
        return  plainText;
    }

}
