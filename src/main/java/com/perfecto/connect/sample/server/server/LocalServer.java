package com.perfecto.connect.sample.server.server;


import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.net.Inet4Address;
import java.net.UnknownHostException;

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

    private static int getPort(){
        return 7070;
    }

    private static String getIP(){
        try {
            return Inet4Address.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            return "localhost";
        }
    }

    public String getHost(){
        return "http://" + getIP() + ":" + getPort();
    }

}
