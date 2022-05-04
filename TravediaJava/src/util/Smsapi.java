package util;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;


/**
 *
 * @author riadh
 */
public class Smsapi {
   


//    //Test account ID and Auth Token
//    public static final String ACCOUNT_SID = "ACa8aaa64f70e5135b0ebeb38124c6dc1b";
//    public static final String AUTH_TOKEN = "c7f536a445b9cbf5b0564ebdc8fe5018";
//
//    public static void sendSMS( String msg) {
//        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
//        Message message = Message.creator(new PhoneNumber("+21624625004"),new PhoneNumber("+14352967669"), msg).create();
//
//        System.out.println(message.getSid());
//
//    }
     // Find your Account Sid and Token at twilio.com/console 
    public static final String ACCOUNT_SID = "AC2234cc4cf8c3c9d895d9d4d2dae7fe7b"; 
    public static final String AUTH_TOKEN = "c7f536a445b9cbf5b0564ebdc8fe5018"; 
 
    public static void sendSMS(String msg) { 
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN); 
        Message message = Message.creator( 
                new com.twilio.type.PhoneNumber("+21624625004"),  
                "MGa18ea90b8e28db3c1f48bbe7d16fea40", 
                msg)      
            .create(); 
 
        System.out.println(message.getSid()); 
    } 

}