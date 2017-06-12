package com.demien.networking;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class UdpServer {
    public final static int PORT_NUMBER = 6666;

    public static String extractString(byte[] array) {
        List<Byte> wrapper=new ArrayList<>();
        for (int i=0; i<array.length;i++) {
            if (array[i]!=0) {
                wrapper.add(array[i]);
            } else {
                break;
            }
        }
        byte[] resultArray=new byte[wrapper.size()];
        for (int i=0; i<wrapper.size();i++) {
            resultArray[i]=wrapper.get(i);
        }


        return  new String(resultArray);
    }

    public static void main(String args[]) throws Exception {
        System.out.println("Starting.... waiting for client connections......");
        try (
                DatagramSocket serverSocket = new DatagramSocket(PORT_NUMBER);
        ) {
            byte[] receiveData = new byte[1024];
            byte[] sendData;
            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);
                String inputLine = extractString(receivePacket.getData());
                System.out.println("received: " + inputLine);
                InetAddress IPAddress = receivePacket.getAddress();
                int port = receivePacket.getPort();
                sendData = inputLine.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                serverSocket.send(sendPacket);
            }
        }
    }
}
