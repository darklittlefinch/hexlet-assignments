package exercise;

public class Main {
    public static void main(String[] args) {
        TcpConnection connection = new TcpConnection("132.223.243.88", 2342);
        System.out.println(connection.getCurrentState());

        connection.connect();
        System.out.println(connection.getCurrentState());

        connection.connect();

        connection.write("data");

        connection.disconnect();
        System.out.println(connection.getCurrentState());

        connection.write("data2");
        connection.disconnect();
    }
}
