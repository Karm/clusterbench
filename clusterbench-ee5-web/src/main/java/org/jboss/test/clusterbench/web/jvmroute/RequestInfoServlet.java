package org.jboss.test.clusterbench.web.jvmroute;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.test.clusterbench.common.jvmroute.CommonJvmRoute;

public class RequestInfoServlet extends HttpServlet {
  private static final long serialVersionUID = 1855772223216460567L;

  private CommonJvmRoute commonJvmRoute = new CommonJvmRoute();

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // Gives it a JSESSIONID
    request.getSession();
    response.setContentType("text/plain");
    StringBuilder responseText = new StringBuilder();
    responseText.append("Request URI: " + request.getRequestURI());
    responseText.append("Path info: " + request.getPathInfo());
    responseText.append("Query string: " + request.getQueryString());
    responseText.append("Host header: " + request.getHeader("Host"));
    responseText.append("JVM route: " + commonJvmRoute.jvmRoute());
    response.getWriter().print(responseText.toString());
  }

  @Override
  public String getServletInfo() {
    return "By invoking RequestInfoServlet, you get the various pieces of information importance of which can hardly be exaggerated :-)";
  }
}