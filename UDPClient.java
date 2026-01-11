import java.net.*;

class UDPClient {

    public static void main(String[] args) throws Exception {

        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress IPAddress = InetAddress.getByName("localhost");

        byte[] sendData = "Hello Server".getBytes();
        byte[] receiveData = new byte[1024];

        // Send dummy request
        DatagramPacket sendPacket =
                new DatagramPacket(sendData, sendData.length,
                        IPAddress, 5454);
        clientSocket.send(sendPacket);

        // Receive message from server
        DatagramPacket receivePacket =
                new DatagramPacket(receiveData, receiveData.length);
        clientSocket.receive(receivePacket);

        String message = new String(
                receivePacket.getData(), 0, receivePacket.getLength());

        System.out.println("Message from Server: " + message);

        clientSocket.close();
    }
}
