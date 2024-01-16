import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client1 {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 8000);
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printWriter.println("Client1 hello");
            String line = "";
            while ((line = bufferedReader.readLine())!=null){
                System.out.println(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }



}