package info.dt.qlcv.common;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Date;

public  class Common {

	//Create token for login
    public static String createToken(String email, String password) {

        String token = "";
        try {

            String hashEmail = getMD5(email);

            String hashPassword = getMD5(password);

            Date date = new Date();


            token = hashEmail
                    +"."+ hashPassword
                    +"."+date.getTime();
        }
        catch (Exception ex){

        }
        return token;
    }
    
    //Hash Password
    public static String hashPass(String password) {
        return getMD5(password);
    }

    //MD5 for password
    private static String getMD5 (String hashText){
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");

            byte[] hashPassword = md5.digest(hashText.getBytes(StandardCharsets.UTF_8));
            BigInteger no = new BigInteger(1, hashPassword);

            String hashResult = no.toString(16);
            while (hashResult.length() < 32) {
                hashResult = "0" + hashResult;
            }
            return  hashResult;
        }
        catch (Exception ex){
            return "";
        }
    }
}
