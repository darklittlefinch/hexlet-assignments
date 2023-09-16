package exercise;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import exercise.servlet.HelloServlet;

public final class App {

    private static int getPort() {
        String port = System.getenv().getOrDefault("PORT", "7070");
        return Integer.valueOf(port);
    }

    public static Server getApp(int port) {
        Server server = new Server(getPort());
        ServletContextHandler handler = new ServletContextHandler();
        handler.addServlet(new ServletHolder(new HelloServlet()), "/");
        server.setHandler(handler);

        return server;
    }

    public static void main(String[] args) throws Exception {
        Server app = getApp(getPort());
        app.start();
        app.join();
    }
}
