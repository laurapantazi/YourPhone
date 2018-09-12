package secondarypackage;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HasherUserPassword {
public static String hashPassword( String password ){
		
		//Create MessageDigest instance for algorithm SHA 
		MessageDigest md;
        String generatedPassword = null;

		try {
			md = MessageDigest.getInstance("SHA");

			 //Add password bytes to digest
			    md.update(password.getBytes());
			    //Get the hash's bytes 
			    byte[] bytes = md.digest();
			    //This bytes[] has bytes in decimal format;
			    //Convert it to hexadecimal format
			    StringBuilder sb = new StringBuilder();
			    for(int i=0; i< bytes.length ;i++)
			    {
			        sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			    }

			    //Get complete hashed password in hex format
			    generatedPassword = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		 
		 return generatedPassword;
		    }//end of method hashPassword
}