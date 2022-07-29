import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ThreadServer implements Runnable {

    @Override
    public void run() {
        try (ServerSocket servSocket = new ServerSocket(11111)) {
            while (true) {
                try (Socket clientSocket = servSocket.accept();
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                    String msg;
                    while ((msg = in.readLine()) != null) {
                        long result = fib(Integer.parseInt(msg));
                        out.println(result);
                    }
                    break;
                } catch (IOException ex) {
                    ex.printStackTrace(System.out);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public long fib(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            return fib(n - 1) + fib(n - 2);
        }
    }
}
