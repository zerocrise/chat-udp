
package chatserverudp;

import java.net.*;
import java.util.HashSet;
public class Risposta extends Thread {
        private DatagramSocket socket;
        private String message;
        private InetSocketAddress sender;
        private HashSet<InetSocketAddress> clients;

        public Risposta(DatagramSocket socket, String message, InetSocketAddress sender, HashSet<InetSocketAddress> clients) {
            this.socket = socket;
            this.message = message;
            this.sender = sender;
            this.clients=clients;
        }

        @Override
        public void run() {
            
            for (InetSocketAddress client : clients) {
                if (!client.equals(sender)) { // Non inviare il messaggio al mittente
                    try {
                        byte[] buffer = message.getBytes();
                        DatagramPacket sendPacket = new DatagramPacket(buffer, buffer.length, client.getAddress(), client.getPort());
                        socket.send(sendPacket); // Invio del pacchetto
                    } catch (Exception e) {
                        System.out.println("Errore nell'invio al client: " + client);
                    }
                }
            }
        }
    }
