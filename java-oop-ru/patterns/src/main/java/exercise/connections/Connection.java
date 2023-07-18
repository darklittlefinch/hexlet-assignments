package exercise.connections;

import exercise.TcpConnection;

public interface Connection {
    // BEGIN
    String getCurrentState();
    void connect(TcpConnection connection);
    void disconnect(TcpConnection connection);
    void write(String data);
    // END
}
