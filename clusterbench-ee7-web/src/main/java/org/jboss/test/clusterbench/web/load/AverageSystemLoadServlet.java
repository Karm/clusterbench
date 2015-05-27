package org.jboss.test.clusterbench.web.load;

import org.jboss.test.clusterbench.common.load.AverageSystemLoad;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * AverageSystemLoadServlet
 *
 * @author Michal Babacek
 *         <p/>
 *         This simple servlet is used for stressing the server's CPU.
 *         <p/>
 *         Usage:
 *         <p/>
 *         You may GET e.g. this URL: http://localhost:8080/clusterbench/averagesystemload?milliseconds=20000&threads=4 By doing
 *         so, there will be 4 threads with evil active-loops created. These threads will be running for 20000 milliseconds.
 *         <p/>
 *         After the aforementioned time, you shall receive a response saying something like:
 *         <p/>
 *         DONE, I was stressing CPU with 4 evil threads for 20000 milliseconds (including warm-up).
 *         <p/>
 *         NOTE: Do not forget to set some reasonable time-out on your client... That's it. No more functionality.
 */
@WebServlet(name = "AverageSystemLoadServlet", urlPatterns = {"/averagesystemload"})
public class AverageSystemLoadServlet extends HttpServlet {
    private static final long serialVersionUID = 5852747171571276015L;
    private static final Logger log = Logger.getLogger(AverageSystemLoadServlet.class.getName());
    private final AverageSystemLoad averageSystemLoad = new AverageSystemLoad();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int milliseconds = Integer.parseInt(request.getParameter("milliseconds"));
        int numberOfThreads = Integer.parseInt(request.getParameter("threads"));
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        String resultLogMessage = averageSystemLoad.spawnLoadThreads(numberOfThreads, milliseconds);
        log.log(Level.INFO, resultLogMessage);
        response.getWriter().print("DONE");
    }

    @Override
    public String getServletInfo() {
        return "By invoking AverageSystemLoadServlet, you stress CPU.";
    }
}
