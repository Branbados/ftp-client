package com.company;

import org.apache.commons.net.ftp.FTP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    //Main method
    public static void main(String[] args) throws IOException
    {
        //Initialize necessary objects
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Client ftpClient = new Client();

        //Variables for storing connection information
        String hostname;
        String username;
        String password;
        String input;

        //Get the hostname
        System.out.println("Please enter the FTP Server hostname:");
        try {
            hostname = "speedtest.tele2.net"; System.out.println(hostname); //For testing purposes
            //hostname = reader.readLine();
            ftpClient.startConnection(hostname);
        }catch(IOException e){
            System.out.println(e.getMessage());
            return;
        }

        //Get the username and password
        System.out.println("Please enter your username:");
        username = "anonymous"; System.out.println(username); //For testing purposes
        //username = reader.readLine();
        System.out.println("Please enter your password:");
        password = "anonymous"; System.out.println(password); //For testing purposes
        //password = reader.readLine();
        try{
            ftpClient.login(username, password);
        }catch(IOException e){
            System.out.println(e.getMessage());
            return;
        }

        /* Loop for input until user decides to exit */
        do{
            input = reader.readLine();
            if(input.equals("ls")) ftpClient.list();
            if(input.equals("logoff"))
                ftpClient.logoff();
            if(input.equals("login"))
                ftpClient.login(username, password);
            if(input.equals("get")) {
                System.out.println("Enter the file to get: ");
                String toGet = reader.readLine();
                ftpClient.get(toGet);
            }
            if(input.equals("put"))
            {
                System.out.println("Enter the name of the file to upload: ");
                String toPut = reader.readLine();
                System.out.println("What would you like to call " + toPut + " on remote?");
                String remoteName = reader.readLine();
                ftpClient.put(toPut, remoteName);
            }

        }while(!input.equals("exit"));
    }
}
