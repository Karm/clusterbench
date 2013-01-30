package org.jboss.test.clusterbench.common.jvmroute;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CommonRequestInfoServlet extends HttpServlet {

  private static final long serialVersionUID = -2126246174508889343L;
  private CommonJvmRoute commonJvmRoute = new CommonJvmRoute();

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // Gives it a JSESSIONID
    HttpSession session = request.getSession();
    response.setContentType("text/plain");
    response.setCharacterEncoding("UTF-8");
    StringBuilder responseText = new StringBuilder();
    responseText.append("Request URI: " + request.getRequestURI());
    responseText.append("\n");
    responseText.append("Path info: " + request.getPathInfo());
    responseText.append("\n");
    responseText.append("Query string: " + request.getQueryString());
    responseText.append("\n");
    responseText.append("Host header: " + request.getHeader("Host"));
    responseText.append("\n");
    responseText.append("JVM route: " + commonJvmRoute.jvmRoute());
    responseText.append("\n");
    responseText.append("Session ID: " + session.getId());
    responseText.append("\n");
    responseText.append("Session isNew: " + session.isNew());
    responseText.append("\n");
    responseText.append("Session ServletContext: " + session.getServletContext());
    responseText.append("\n");
    response.getWriter().print(responseText.toString());
  }

  @Override
  public String getServletInfo() {
    return "By invoking RequestInfoServlet, you get the various pieces of information importance of which can hardly be exaggerated :-)";
  }
}
