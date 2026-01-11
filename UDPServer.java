import java.net.*;
import java.io.*;

class UDPServer {

    public static void main(String[] args) throws Exception {

        DatagramSocket serverSocket = new DatagramSocket(5454);
        BufferedReader br =
                new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Server is ready...");

        byte[] receiveData = new byte[1024];
        byte[] sendData;

        // Receive request from client
        DatagramPacket receivePacket =
                new DatagramPacket(receiveData, receiveData.length);
        serverSocket.receive(receivePacket);

        InetAddress clientAddress = receivePacket.getAddress();
        int clientPort = receivePacket.getPort();

        // Message typed at server side
        System.out.print("Enter message to send to client: ");
        String msg = br.readLine();

        sendData = msg.getBytes();
        DatagramPacket sendPacket =
                new DatagramPacket(sendData, sendData.length,
                        clientAddress, clientPort);

        serverSocket.send(sendPacket);
        serverSocket.close();
    }
}
