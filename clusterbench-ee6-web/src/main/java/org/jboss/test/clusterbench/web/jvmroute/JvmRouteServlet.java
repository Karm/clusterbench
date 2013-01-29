package org.jboss.test.clusterbench.web.jvmroute;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.test.clusterbench.common.jvmroute.CommonJvmRoute;

@WebServlet(name = "JvmRouteServlet", urlPatterns = { "/jvmroute" })
public class JvmRouteServlet extends HttpServlet {
  private static final long serialVersionUID = 1855772223216460567L;

  private CommonJvmRoute commonJvmRoute = new CommonJvmRoute();

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // Gives it a JSESSIONID
    request.getSession();
    response.setContentType("text/plain");
    response.getWriter().print(commonJvmRoute.jvmRoute());
  }

  @Override
  public String getServletInfo() {
    return "By invoking JvmRouteServlet, you get the node's JvmRoute.";
  }
}
