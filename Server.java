
package chatserverudp;

import java.io.IOException;
import java.net.*;
import java.util.HashSet;

public class Server {
    private int porta; // Porta su cui il server ascolta
    private byte[] buffer;
    private DatagramSocket socket;
    private DatagramPacket packet;
    private HashSet<InetSocketAddress> clients = new HashSet<>(); // Lista dei client connessi
      

    public Server(int porta) {
          this.porta=porta;
          avvio();
    }
       
 private void avvio(){    
        try {
            socket = new DatagramSocket(porta);
        
            while (true) {
                buffer = new byte[1024];
                // Ricezione del pacchetto
                packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                String message = new String(packet.getData(), packet.getOffset(), packet.getLength());
            //socket del client
                InetSocketAddress clientAddress = new InetSocketAddress(packet.getAddress(), packet.getPort());

                // Rimuovi un client se invia "exit"
                if (message.equalsIgnoreCase("exit")) {
                    clients.remove(clientAddress);
                }else{
                   
                // Aggiungi il client alla lista dei connessi
                clients.add(clientAddress);
                // Avvia un thread per gestire l'invio del messaggio agli altri client
                new Risposta(socket, message, clientAddress,clients).start();

                }
                System.out.println("numero di client: "+ clients.size());
            }
        } catch (SocketException ex) {
            System.out.println("Errore nella creazione del socket ");
        } catch (IOException ex) {
            System.out.println("Errore nella ricezione dei messaggi");
            
        }
    }
}

    

