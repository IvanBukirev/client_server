import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static final Integer LOCALHOST_PORT = 8080;

    public static void main(String[] args) throws IOException {

        try (ServerSocket serverSocket = new ServerSocket(LOCALHOST_PORT)) {
            System.out.println("Сервер стартовал");
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
                ) {
                    final String name = in.readLine();
                    System.out.printf("Hi %s, your port is %d ", name, clientSocket.getPort());
                    out.println(clientSocket.getPort());
                }
            }
        } catch (IOException e) {
            throw new IOException(e);
        }
    }
}