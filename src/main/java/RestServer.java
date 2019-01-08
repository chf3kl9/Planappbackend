import database.repository.PlanningCardRepository;
import logging.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.glassfish.jersey.servlet.ServletContainer;
import restserver.handlers.IPlanHandler;
import restserver.handlers.PlanHandler;
import restserver.services.PlanService;

import javax.servlet.DispatcherType;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.EnumSet;

public class RestServer {
    public static void main(String[] args) throws Exception {
        /*String jdbcUrl = "jdbc:mysql://localhost:3306/planapp?useSSL=false";
        String userid = "planapp";
        String password = "planapp";

        try{
            Connection myConn = DriverManager.getConnection(jdbcUrl, userid, password);
            System.out.println("Success");
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }*/
        ServletContextHandler context = new
                ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        Server jettyServer = new Server(8094);

        //region Origin header
        FilterHolder cors = context.addFilter(CrossOriginFilter.class, "/*", EnumSet.of(DispatcherType.REQUEST));
        cors.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "*");
        cors.setInitParameter(CrossOriginFilter.ACCESS_CONTROL_ALLOW_ORIGIN_HEADER, "*");
        cors.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "GET,POST,PUT,DELETE,HEAD");
        cors.setInitParameter(CrossOriginFilter.ALLOWED_HEADERS_PARAM, "X-Requested-With,Content-Type,Accept,Origin");
        //endregion

        IPlanHandler handler = new PlanHandler();
        PlanService.setHandler(handler);

        jettyServer.setHandler(context);
        ServletHolder jerseyServlet =
                context.addServlet(ServletContainer.class, "/*");
        jerseyServlet.setInitOrder(0);

        // Tells the Jersey Servlet which REST service/class to load.
        jerseyServlet.setInitParameter("jersey.config.server.provider.packages",
                "restserver.services");


        try {
            jettyServer.start();
            jettyServer.join();
        } finally {
            jettyServer.destroy();
        }
    }
}
