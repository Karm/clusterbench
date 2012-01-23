package org.jboss.test.clusterbench.web.load;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.test.clusterbench.common.load.SendTrafficLoad;

/**
 * SendTrafficLoadServlet
 * 
 * @author Michal Babacek
 * 
 * This simple servlet is used for stressing the server's outbound bandwidth.
 */
@WebServlet(name = "SendTrafficLoadServlet", urlPatterns = { "/sendtrafficload" })
public class SendTrafficLoadServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(AverageSystemLoadServlet.class.getName());
    private final SendTrafficLoad sendTrafficLoad = new SendTrafficLoad();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int kilobytes = Integer.parseInt(request.getParameter("kilobytes"));
        String rubbish = sendTrafficLoad.generateRubbish(kilobytes);
        log.log(Level.INFO, "DONE");
        response.setContentType("text/plain");
        response.getWriter().print("DONE;"+(rubbish.length()/1024)+"KB of rubbish;"+rubbish);
    }

    @Override
    public String getServletInfo() {
        return "By invoking BusyConnectorsLoadServlet, you stress connector's busyness.";
    }
}
