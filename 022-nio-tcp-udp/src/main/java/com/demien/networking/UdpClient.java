package com.demien.networking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpClient {
    public static void main(String args[]) throws Exception {
        System.out.println("Starting.... ");
        try (
                BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
                DatagramSocket clientSocket = new DatagramSocket();
        ) {
            InetAddress IPAddress = InetAddress.getByName("localhost");
            byte[] sendData;
            byte[] receiveData = new byte[1024];
            System.out.println("Enter message :  ");
            while (true) {
                String userInput = inFromUser.readLine();
                sendData = userInput.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, UdpServer.PORT_NUMBER);
                clientSocket.send(sendPacket);
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                clientSocket.receive(receivePacket);
                String receivedSentence = UdpServer.extractString(receivePacket.getData());
                System.out.println("response from server:" + receivedSentence);
            }
        }

    }
}
