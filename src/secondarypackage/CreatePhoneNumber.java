package secondarypackage;

import java.util.Random;

public class CreatePhoneNumber {
	//generates a 10-digits number, that always starts with "69"
	  public static long generatePhoneNumber() {
		  Random random = new Random();
		    char[] digits = new char[10];
	        digits[0] = (char)('6');
	        digits[1] = (char)('9');
		    digits[2] = (char) (random.nextInt(9) + '1');
		    for (int i = 3; i < 10; i++) {
		        digits[i] = (char) (random.nextInt(10) + '0');
		    }
		    long number= Long.parseLong(new String(digits));
		    
		    return number;
		}//end of method generatePhoneNumber
}
