import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/*
 * Davide Porcu 5IA
 */

public class DateClient {

    public static void main(String[] args) throws IOException {
        String serverAddress = "localhost"; // server string
        int porta=9090;
        Socket s = new Socket(serverAddress, porta);
        BufferedReader input =
            new BufferedReader(new InputStreamReader(s.getInputStream()));
        String str = "DATA RECEIVED:";
        do {
            System.out.println(str);
            str = input.readLine();
        } while(str != null);
    }
}
