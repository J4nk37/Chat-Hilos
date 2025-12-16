import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

public class GestorCliente implements Runnable {

    // Atributos de la clase
    private Socket sc;
    private String nombre;

    // Constructor de la clase
    public GestorCliente(Socket socket) {
        this.sc = socket;
    }

    @Override
    public void run() {
        // Inicializamos socket y streams usando try-with-resources
        try (Socket socket = sc;
             DataInputStream in = new DataInputStream(socket.getInputStream());
             DataOutputStream out = new DataOutputStream(socket.getOutputStream()); ) {

            // Leemos el nombre del cliente
            nombre = in.readUTF();
            boolean salir = false;

            // Hacemos un bucle para leer mensajes del cliente hasta que escriba 'FIN'
            do {
                // Leemos el mensaje del cliente y lo mostramos en el servidor
                String mensaje = in.readUTF();
                System.out.println(nombre + ": " + mensaje);

                // Enviamos confirmación al cliente que envió el mensaje
                out.writeUTF("Mensaje enviado correctamente -> " + mensaje);

                // Comprobamos si el usuario escribe 'FIN', en caso correcto,
                // salimos del bucle estableciendo la variable salir en true
                if (mensaje.equals("FIN")) {
                    salir = true;
                }
            } while (!salir);

            System.out.println("Conexion terminada con el cliente " + nombre);

        } catch (EOFException | SocketException e) {
            // Desconexión inesperada
            System.out.println("El cliente [" + nombre + "] se ha desconectado inesperadamente.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}