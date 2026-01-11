import java.net.*;
import java.io.*;

public class TCPServer {

    public static void main(String[] args) throws Exception {

        ServerSocket serverSocket = new ServerSocket(3300);
        System.out.println("Server ready for connection...");

        Socket socket = serverSocket.accept();
        System.out.println("Client connected");

        BufferedReader in =
                new BufferedReader(new InputStreamReader(socket.getInputStream()));

        PrintWriter out =
                new PrintWriter(socket.getOutputStream(), true);

        String fileName = in.readLine();

        File file = new File(fileName);

        if (file.exists()) {
            BufferedReader fileReader =
                    new BufferedReader(new FileReader(file));

            String line;
            while ((line = fileReader.readLine()) != null) {
                out.println(line);
            }
            fileReader.close();
        } else {
            out.println("File not found");
        }

        socket.close();
        serverSocket.close();
    }
}
