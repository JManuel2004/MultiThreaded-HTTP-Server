import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.StringTokenizer;

public class HttpRequest implements Runnable {
    private Socket clientSocket;

    public HttpRequest(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             OutputStream out = clientSocket.getOutputStream()) {

           
            String requestLine = in.readLine();
            if (requestLine != null) {
                System.out.println("Solicitud recibida: " + requestLine);
            }

           
            StringTokenizer partesLinea = new StringTokenizer(requestLine);
            String method = partesLinea.nextToken(); // Método (GET)
            String nombreArchivo = partesLinea.nextToken(); // Archivo solicitado
            nombreArchivo = nombreArchivo.equals("/") ? "/index.html" : nombreArchivo; // Usar /index.html si no se especifica archivo

            // Construir la ruta completa al archivo en la carpeta "src"
            String filePath = "src" + nombreArchivo;
            File archivo = new File(filePath);  

            System.out.println("Buscando archivo: " + archivo.getAbsolutePath());  // Imprimir la ruta completa para depurar

            
            String lineaDeEstado = null;
            String lineaDeTipoContenido = null;

            if (archivo.exists() && !archivo.isDirectory()) {

                lineaDeEstado = "HTTP/1.1 200 OK\r\n";
                lineaDeTipoContenido = "Content-Type: " + contentType(nombreArchivo) + "\r\n";

                enviarString(lineaDeEstado, out);
                enviarString(lineaDeTipoContenido, out);
                enviarString("\r\n", out);

                // Enviar el archivo solicitado
                try (FileInputStream fis = new FileInputStream(archivo)) {
                    enviarBytes(fis, out);
                }

            } else {

                lineaDeEstado = "HTTP/1.0 404 Not Found\r\n";
                lineaDeTipoContenido = "Content-Type: text/html\r\n";
                enviarString(lineaDeEstado, out);
                enviarString(lineaDeTipoContenido, out);
                enviarString("\r\n", out);

                // Si no se encuentra el archivo solicitado enviar una página de error 404
                File archivo404 = new File("./src/404.html");  
                if (archivo404.exists()) {
                    try (FileInputStream fis = new FileInputStream(archivo404)) {
                        enviarBytes(fis, out);
                    }
                } else {
                    enviarString("<html><body><h1>404 Not Found</h1></body></html>", out);
                }
            }

            out.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void enviarString(String line, OutputStream os) throws IOException {
        os.write(line.getBytes(StandardCharsets.UTF_8));
    }

    private static void enviarBytes(InputStream fis, OutputStream os) throws IOException {
        byte[] buffer = new byte[1024];
        int bytes;
        while ((bytes = fis.read(buffer)) != -1) {
            os.write(buffer, 0, bytes);
        }
    }

    private static String contentType(String nombreArchivo) {
        if (nombreArchivo.endsWith(".html") || nombreArchivo.endsWith(".htm")) {
            return "text/html";
        } else if (nombreArchivo.endsWith(".jpeg") || nombreArchivo.endsWith(".jpg")) {
            return "image/jpeg";
        } else if (nombreArchivo.endsWith(".gif")) {
            return "image/gif";
        } else if (nombreArchivo.endsWith(".css")) {
            return "text/css";
        } else if (nombreArchivo.endsWith(".js")) {
            return "application/javascript";
        }
        return "application/octet-stream"; // Si el tipo es desconocido
    }
}

