package exercise.connections;

import exercise.TcpConnection;

// BEGIN
public class Disconnected implements Connection {

    private TcpConnection disconnectedConnection;

    public Disconnected() {
    }

    @Override
    public String getCurrentState() {
        return "disconnected";
    }

    @Override
    public void connect(TcpConnection connection) {
        connection.setConnectionState(new Connected());
    }

    @Override
    public void disconnect(TcpConnection connection) {
        System.out.println("Error! Connection is already disconnected");
    }

    @Override
    public void write(String data) {
        System.out.println("Error! Connection is disconnected");
    }
}
// END
