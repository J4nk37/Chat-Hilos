import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Host del servidor
        final String Host = "localhost";

        // Puerto del servidor
        final int PORT = 5000;

        boolean salir = false;
        String nombre;

        // Inicializamos socket y streams usando try-with-resources
        try (
             Socket socket = new Socket(Host, PORT);
             DataInputStream in = new DataInputStream(socket.getInputStream());
             DataOutputStream out = new DataOutputStream(socket.getOutputStream()); ) {

            // Pedimos el nombre de usuario y lo enviamos a servidor
            System.out.print("Introduzca su nombre de usuario: ");
            nombre = sc.nextLine();
            out.writeUTF(nombre);

            do {
                // Le pedimos al usuario que escriba el mensaje que quiera
                System.out.println("Escribe un mensaje al servidor:");
                String texto = sc.nextLine();

                // Enviamos el mensaje que escribe el usuario al servidor
                out.writeUTF(texto);

                // Recibimos la respuesta del servidor y la mostramos
                String mensaje = in.readUTF();
                System.out.println(mensaje);

                // Comprobamos si el usuario escribe 'FIN', en caso correcto,
                // salimos del bucle estableciendo la variable salir en true
                if (texto.equals("FIN")) {
                    salir = true;
                }
            } while(!salir);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}