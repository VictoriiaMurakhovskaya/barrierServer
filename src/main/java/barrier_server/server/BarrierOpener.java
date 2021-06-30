package barrier_server.server;
import java.net.Socket;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;


public class BarrierOpener {

    // параметрі подключения к текстовому шлагбауму
    final String BARRIER_IP = "192.168.3.20";
    final int PORT = 9091;

    private Socket clientSocket; //сокет для общения
    private BufferedWriter out; // поток записи в сокет

    public void open() {
        try {
            try {
                System.out.printf("Подключение к шлагбауму. IP: %S, Port: %d",
                        this.BARRIER_IP, this.PORT);
                clientSocket = new Socket(this.BARRIER_IP, this.PORT); // создание экземпляра клиента

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

