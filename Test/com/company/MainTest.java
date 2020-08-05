package com.company;


import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import javax.swing.event.CaretListener;
import java.io.IOException;

/*
We will create a test file like this for every class that we write
The imports above import Junit 4 and 5
The @Test keyword is used to signify Tests
*/

public class MainTest {

    /* Assert client can connect to a test server */
    @Test
    public void testStartConnection() throws IOException {
        boolean isConnected = true;
        Client ftpClient = new Client();

        try {
            ftpClient.startConnection("speedtest.tele2.net");
        } catch (IOException e) {
            System.out.print(e.getMessage());
            isConnected = false;
        }

        Assertions.assertTrue(isConnected);
    }

    /* Assert passing null parameters throws an exception */
    @Test
    public void testNullStartConnection() throws IOException {
        Client ftpClient = new Client();

        Assertions.assertThrows(IOException.class, () ->
                ftpClient.startConnection(""));
    }

    @Test
    public void getExistentFileTest() throws IOException
    {
        Client ftpClient = new Client();
        boolean gotFile = true;

        try {
            ftpClient.startConnection("speedtest.tele2.net");
            ftpClient.login("anonymous", "anonymous");
            gotFile = ftpClient.get("1KB.zip"); //check the program directory to ensure that the file was downloaded
            Assertions.assertEquals(true, gotFile); //since the get command always returns false for some reason
        } catch (IOException e) {
            System.out.print(e.getMessage());

        }


    }
    @Test
    public void getNonexistentFileTest() throws IOException {
        Client ftpClient = new Client();
        boolean gotFile = true;

        try {
            ftpClient.startConnection("speedtest.tele2.net");
            ftpClient.login("anonymous", "anonymous");
            gotFile = ftpClient.get("10KB.zip"); //The file will still be created, but it will be empty since the download failed
            Assertions.assertEquals(false, gotFile);
        } catch (IOException e) {
            System.out.print(e.getMessage());

        }
    }

    @Test
    public void testLogoff() throws IOException {
        Client ftpClient = new Client();
        boolean logoff = true;

        try {
            ftpClient.startConnection("speedtest.tele2.net");
            ftpClient.login("anonymous", "anonymous");
            ftpClient.logoff();
        } catch (IOException e) {
            System.out.print(e.getMessage());
            logoff = false;
        }
        Assertions.assertTrue(logoff);
    }
}