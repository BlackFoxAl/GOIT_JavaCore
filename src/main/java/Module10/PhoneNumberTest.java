package Module10;
import java.io.*;
import java.util.regex.*;

public class PhoneNumberTest {
    private static final String MASK = "^(\\(\\d{3}\\)\\s|\\d{3}-)\\d{3}\\-\\d{4}";
    public static void main(String[] arg) {
        String filename = "file1.txt";
        String phoneNumber;
        StringBuffer outputPhoneNumbers = new StringBuffer();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))) {

            while ((phoneNumber = bufferedReader.readLine()) != null) {
                Pattern pattern = Pattern.compile(MASK);
                Matcher matcher = pattern.matcher(phoneNumber);
                while (matcher.find()) {
                    outputPhoneNumbers.append(phoneNumber.substring(matcher.start(), matcher.end()));
                    outputPhoneNumbers.append("\n");
                }
            }
            System.out.println("Valid phone numbers : ");
            System.out.print(outputPhoneNumbers.toString());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        };
    }
}
