import java.math.BigInteger;

public class Iban {
    String iban;
    String accountNumber;
    String countryRecognition;
    String bankCode;
    
    public Iban(String country, String bankCode2, String account) {
        this.accountNumber = account;
        this.countryRecognition = country;
        this.bankCode = bankCode2;
        this.iban = splitBan();
        }

    public String accountNumber10() {
    for (int i = accountNumber.length(); i < 10; i++) {
    accountNumber = "0" + accountNumber;
    }
    return accountNumber;
    }
    
    //Zusammenfügen BLZ und Kontonr
    public String bankCodeAccount() {
    
    return bankCode + accountNumber10();
    }
    
    //Länderkenz in Zahlen
    public int letterToNumber(char a) {
    int number = a - 64;
    number += 9;
    
    return number;
    
    }
    
    //Zusammenfügen der beiden BuchstabenZahlen
    public String country() {
    String partOne = Integer.toString(letterToNumber(this.countryRecognition.charAt(0)));
    String partTwo = Integer.toString(letterToNumber(this.countryRecognition.charAt(1)));
    
    return partOne + partTwo + "00";
    
    }
    
    
    //Zusammenfügen von allem
    public String bankCodeAccountCountry() {
    
    return bankCodeAccount() + country();
    }
    
    
    //Prüfsumme
    public String finalCheckSum() {
    
    //Zahlen in BigInt und Modulo und Substrahieren
    BigInteger firstIban = new BigInteger(bankCodeAccountCountry());
    BigInteger modNumber = new BigInteger("97");
    BigInteger subtractor = new BigInteger("98");
    
    BigInteger test = firstIban.mod(modNumber);
    BigInteger test1 = subtractor.subtract(test);
    
    
    //BigInt zurück in Int sonst geht nix
    
    int checkSum = test1.intValue();
    
    //falls Prüfsumme nicht 2Stellig ist, eine 0 hinzufügen
    if (checkSum < 10) {
    return countryRecognition + "0" + checkSum + bankCodeAccount();
    }
    else {
    return countryRecognition + checkSum + bankCodeAccount();
    } 
    } 
    public String splitBan() {
    
    String s1 = finalCheckSum().substring(0, 4);
    String s2 = finalCheckSum().substring(4, 8);
    String s3 = finalCheckSum().substring(8, 12);
    String s4 = finalCheckSum().substring(12, 16);
    String s5 = finalCheckSum().substring(16, 20);
    String s6 = finalCheckSum().substring(20, 22);
    
    return s1 + " " + s2 + " " + s3 + " " + s4 + " " + s5 + " " + s6;
    }
    
    /**
    * Stellt die fertige IBAN zu verfügung
    */
    public void printIban() {
    System.out.println(this.iban); 
    } 
    }