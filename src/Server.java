import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {

        // Socket que escucha las conexiones
        ServerSocket servidor = null;

        // Socket para hablar con el cliente
        Socket sc = null;

        // Puerto en el que escucha el servidor
        final int PUERTO = 5000;

        try {
            // Se crea el servidor en el puerto 5000
            servidor = new ServerSocket(PUERTO);
            System.out.println("Servidor a la espera...");

            // Hacemos un while true, para que el servidor siempre este activo
            while (true) {
                // Esperar a que un cliente realice una peticion
                // Se queda parado a la espera
                sc = servidor.accept();

                // Cogemos la IP del usuario para mostrarla cuando un cliente se conecte
                InetAddress ip = sc.getInetAddress();
                System.out.println("Cliente conectado desde: [" + ip + "]");

                // Creamos y lanzamos un hilo que funcionara como cliente
                GestorCliente gestor = new GestorCliente(sc);
                new Thread(gestor).start();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}