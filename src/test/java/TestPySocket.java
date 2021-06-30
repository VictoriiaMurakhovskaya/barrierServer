import java.io.*;
import java.net.Socket;

public class TestPySocket {

    private static Socket clientSocket; //сокет для общения
    private static BufferedWriter out; // поток записи в сокет

    public static void main(String[] args) {
        try {
            try {
                // адрес - IP адрес сервера преграды, порт - 9091, такой же как у сервера
                clientSocket = new Socket("192.168.3.20", 9091); // создание экземпляра клиента

                // создание экземпляра объекта, "пишущего" в сокет
                out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                // отправка сообщения на сервер
                out.write("open");
                out.flush();
            } finally { // закрытие сокета и потоков
                System.out.println("Клиент был закрыт...");
                clientSocket.close();
                out.close();
            }
        } catch (IOException e) {
            System.err.println(e);
        }

    }
}