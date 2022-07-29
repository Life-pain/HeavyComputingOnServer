import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client implements Runnable {
    @Override
    public void run() {
        try (Socket socket = new Socket("127.0.0.1", 11111);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
             Scanner scanner = new Scanner(System.in)) {

            String line;
            while (true) {
                System.out.println("Введите число Фибоначчи, для завершения введите \"end\": ");
                line = scanner.nextLine();
                if (line.equals("end")) break;
                out.println(line);
                System.out.printf("%s-е число Фибоначчи = %s\n", line, in.readLine());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
