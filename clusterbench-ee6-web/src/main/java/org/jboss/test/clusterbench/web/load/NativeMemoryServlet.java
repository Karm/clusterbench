package org.jboss.test.clusterbench.web.load;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.test.clusterbench.common.load.NativeMemoryStress;

/**
 * NativeMemoryServlet
 * 
 * @author Michal Babacek
 *
 * This simple servlet is used for stressing native system memory.
 */
@WebServlet(name = "NativeMemoryServlet", urlPatterns = { "/nativememory" })
public class NativeMemoryServlet extends HttpServlet {
   private static final Logger log = Logger.getLogger(NativeMemoryServlet.class.getName());
   private final NativeMemoryStress nativeMemoryStress = new NativeMemoryStress();
   
   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      int milliseconds = Integer.parseInt(request.getParameter("milliseconds"));
      int megabytes = Integer.parseInt(request.getParameter("megabytes")); 
      response.setContentType("text/plain");
      response.getWriter().print(nativeMemoryStress.allocateMegabytes(megabytes, milliseconds));
   }

   @Override
   public String getServletInfo() {
      return "By invoking NativeMemoryServlet, you stress system native memory.";
   }

}
