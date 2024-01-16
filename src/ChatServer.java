import java.util.*;
import java.net.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;


public class ChatServer {

    private static List<Socket> list = new ArrayList<>();

    public static void main(String[] args) {
        int port = 8000;
        try {
            ServerSocket server = new ServerSocket(port);
            while (true){
                Socket client = server.accept();
                ClientHandler clientHandler = new ClientHandler(client);
                synchronized(list){
                    list.add(client);
                }
                Thread clientThread = new Thread(clientHandler);
                clientThread.start();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static class ClientHandler implements Runnable {

        Socket client;

        String message;


        public ClientHandler(Socket client) {
            this.client = client;
        }

        @Override
        public void run() {
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                message = bufferedReader.readLine();
                System.out.println(message);
                for (Socket tmp : list) {
                    if ( tmp != client) {
                        PrintWriter tmppr = new PrintWriter(tmp.getOutputStream(), true);
                        tmppr.println(message);
                    }
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

    }
}
