import java.math.BigInteger;

public class Iban {
    String iban;
    String accountNumber;
    String countryCode;
    String bankCode;
    public Iban(String country, String bankCode2, String account) {
        accountNumber = account;
        countryCode = country;
        bankCode = bankCode2;
        iban = splitBan();
        }
    public String generateBban() {
        for (int i = accountNumber.length(); i < 10; i++) {
            accountNumber = "0" + accountNumber;
    }
        return bankCode + accountNumber;
    }
    public int letterToNumber(char a) {
        int number = a - 64;
        number += 9;
        return number;
    }
    public String country() {
        String first = Integer.toString(letterToNumber(countryCode.charAt(0)));
        String second = Integer.toString(letterToNumber(countryCode.charAt(1)));
        return first + second + "00";
    }
    
    public String bankCodeAccountCountry() {
        return generateBban() + country();
    }
    public String checkSum() {
        BigInteger firstIban = new BigInteger(bankCodeAccountCountry());
        BigInteger modNumber = new BigInteger("97");
        BigInteger subtractor = new BigInteger("98");
        BigInteger test = firstIban.mod(modNumber);
        BigInteger test1 = subtractor.subtract(test);    
        int checkSum = test1.intValue();
        if (checkSum < 10) {
            return countryCode + "0" + checkSum + generateBban();
        }
        else {
            return countryCode + checkSum + generateBban();
        } 
        } 
        public String splitBan() {
        String s1 = checkSum().substring(0, 4);
        String s2 = checkSum().substring(4, 8);
        String s3 = checkSum().substring(8, 12);
        String s4 = checkSum().substring(12, 16);
        String s5 = checkSum().substring(16, 20);
        String s6 = checkSum().substring(20, 22);
        return s1 + " " + s2 + " " + s3 + " " + s4 + " " + s5 + " " + s6;
    }
    public void generateIban() {
        System.out.println(iban); 
    } 
    }