import java.io.*;
import java.net.Socket;

public class TestPySocket {

    private static Socket clientSocket; //сокет для общения
    private static BufferedWriter out; // поток записи в сокет

    public static void main(String[] args) {
        try {
            try {
                // адрес - локальный хост, порт - 9091, такой же как у сервера
                clientSocket = new Socket("192.168.3.20", 9091); // этой строкой мы запрашиваем

                // писать туда же
                out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                // не напишет в консоль
                out.write("open"); // отправляем сообщение на сервер
                out.flush();
            } finally { // в любом случае необходимо закрыть сокет и потоки
                System.out.println("Клиент был закрыт...");
                clientSocket.close();
                out.close();
            }
        } catch (IOException e) {
            System.err.println(e);
        }

    }
}