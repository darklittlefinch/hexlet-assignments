package exercise.connections;

import exercise.TcpConnection;

// BEGIN
public class Connected implements Connection {

    private TcpConnection connectedConnection;

    public Connected() {
    }

    @Override
    public String getCurrentState() {
        return "connected";
    }

    @Override
    public void connect(TcpConnection connection) {
        System.out.println("Error! Connection is already connected");
    }

    @Override
    public void disconnect(TcpConnection connection) {
        connection.setConnectionState(new Disconnected());
    }

    @Override
    public void write(String data) {
        System.out.println(data);
    }
}
// END
