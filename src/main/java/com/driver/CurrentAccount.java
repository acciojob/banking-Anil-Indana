package com.driver;

public class CurrentAccount extends BankAccount{
    String tradeLicenseId; //consists of Uppercase English characters only

    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception
        super(name,balance,5000);
        this.tradeLicenseId = tradeLicenseId;
    }

    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception
        if(!isValid(tradeLicenseId)){
            String validId = rearrange(tradeLicenseId);
            if(validId.equals("")){
                throw  new Exception("Valid License can not be generated");
            }
            else this.tradeLicenseId = validId;
        }

    }
    public boolean isValid(String s){
        for(int i=0;i<s.length()-1;i++){
            if(s.charAt(i) == s.charAt(i+1)) return false;
        }
        return true;
    }
    public String rearrange(String s){
        int[] freq = new int[26];
        // storing the freq of characters in String
        for(int i=0;i<s.length();i++){
            freq[s.charAt(i) - 'a']++;
        }
        // maxFreq char
        int max = 0,c = 0;
        for(int i=0;i<26;i++){
            if(max < freq[i]){
                max = freq[i];
                c = i;
            }
        }
        if(max > (s.length()+1)/2) return "";
        char[] ch = new char[s.length()];
        int idx = 0;
        while(freq[c] > 0){
            char letter = (char) (c+'a');
            ch[idx] = letter;
            idx += 2;
            freq[c]--;
        }
        for(int i=0;i<s.length();i++){
            while(freq[i] > 0){
                if(idx >= s.length()){
                    idx = 1;
                }
                ch[idx] = (char)(i+'a');
                idx += 2;
                freq[i]--;
            }
        }
        return String.valueOf(ch);
    }

    public String getTradeLicenseId() {
        return tradeLicenseId;
    }
}
