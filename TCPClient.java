import java.net.*;
import java.io.*;

public class TCPClient {

    public static void main(String[] args) throws Exception {

        Socket socket = new Socket("127.0.0.1", 3300);

        BufferedReader keyboard =
                new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Enter the file name: ");
        String fileName = keyboard.readLine();

        PrintWriter out =
                new PrintWriter(socket.getOutputStream(), true);
        out.println(fileName);

        BufferedReader in =
                new BufferedReader(new InputStreamReader(socket.getInputStream()));

        String line;
        System.out.println("\nFile contents:");
        while ((line = in.readLine()) != null) {
            System.out.println(line);
        }

        socket.close();
        keyboard.close();
    }
}
