package exercise;

import exercise.connections.Connection;
import exercise.connections.Disconnected;

// BEGIN
public class TcpConnection {
    private final String ip;
    private final int port;
    private Connection connection = new Disconnected();

    public TcpConnection(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

//    @Override
    public String getCurrentState() {
        return connection.getCurrentState();
    }

//    @Override
    public void connect() {
        connection.connect(this);
    }

//    @Override
    public void disconnect() {
        connection.disconnect(this);
    }

//    @Override
    public void write(String data) {
        connection.write(data);
    }

    public void setConnectionState(Connection connectionState) {
        this.connection = connectionState;
    }
}
// END
