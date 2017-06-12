package com.demien.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class TcpClient {

    public final static String HOST_NAME="localhost";

    public static void main(String[] args) throws IOException {

        System.out.println("Starting.... ");
        try (
                Socket echoSocket = new Socket(HOST_NAME, TcpServer.PORT_NUMBER);
                PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
                BufferedReader stdIn = new BufferedReader( new InputStreamReader(System.in))
        ) {
            System.out.println("Connected to server. Enter message :  ");
            String userInput;
            while ((userInput = stdIn.readLine()) != null) {
                out.println(userInput);
                System.out.println("response from server: " + in.readLine());
            }
        } catch (UnknownHostException e) {
            System.err.println("Unknown host:" + HOST_NAME);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                    HOST_NAME);
            System.exit(1);
        }
    }
}
