package com.perfecto.connect.sample.server.server;


import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class LocalServer {

    private final Server server;

    public LocalServer() {
        server = new Server(getPort());

    }

    public void start(String msg) throws Exception {
        ServletContextHandler handler = new ServletContextHandler(server, "/");
        handler.addServlet(new ServletHolder(new MainRequest(msg)), "/");
        server.start();
    }

    public void stop() throws Exception {
        server.stop();
    }
    public static int getPort(){
        return 7070;
    }
}
