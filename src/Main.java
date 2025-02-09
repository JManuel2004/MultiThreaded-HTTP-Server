import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        int port = 8080; // Puerto en el que el servidor escuchará

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Servidor HTTP iniciado en el puerto " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Nueva conexión desde: " + clientSocket.getInetAddress());


                HttpRequest request = new HttpRequest(clientSocket);
                Thread thread = new Thread(request);
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
