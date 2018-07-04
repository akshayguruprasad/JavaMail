package com.indreams.Mailingapp.main;

import com.indreams.Mailingapp.protocols.Smtp;
import com.indreams.Mailingapp.protocols.interfaces.Mailing;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        Mailing mail=new Smtp();
mail.sendMail();
System.out.println("success in sending the mail");
        
        
    }
}
