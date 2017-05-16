package space.harbour.hw12;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import space.harbour.day11.DBService;
import space.harbour.day11.MyDBService;
import space.harbour.hw12.db.MsgDBService;
import space.harbour.hw12.db.MyMsgDBService;
import space.harbour.hw12.servlets.AddAssessmentServlet;
import space.harbour.hw12.sockets.chat.WebSocketChatServlet;

import javax.servlet.Servlet;

public class Main {
    public static void main(String[] args) throws Exception {
        MsgDBService msgService = new MyMsgDBService();
        DBService userService = new MyDBService();
        try {
            Server server = new Server(8090);

            ResourceHandler resource_handler = new ResourceHandler();
            resource_handler.setDirectoriesListed(true);
            resource_handler.setResourceBase("public_html");

            ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
            Servlet addAssessment = new AddAssessmentServlet(userService);
            context.addServlet(new ServletHolder(addAssessment), "/add_assessment");
            context.addServlet(new ServletHolder(new WebSocketChatServlet(msgService)), "/chat");

            HandlerList handlers = new HandlerList();
            handlers.setHandlers(new Handler[]{resource_handler, context});
            server.setHandler(handlers);

            server.start();
            server.join();
        } finally {
            userService.shutdown();
            msgService.shutdown();
        }
    }
}
