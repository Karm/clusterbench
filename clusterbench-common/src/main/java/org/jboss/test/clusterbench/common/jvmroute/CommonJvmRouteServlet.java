package org.jboss.test.clusterbench.common.jvmroute;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommonJvmRouteServlet extends HttpServlet {

  @SuppressWarnings("unused")
  private static final Logger log = Logger.getLogger(CommonJvmRouteServlet.class.getName());
  private static final String[] properties = new String[] { "jvmRoute", "jboss.mod_cluster.jvmRoute", "instance-id", "jboss.domain.web.instance-id", "jboss.jvmRoute" };

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String jvmRoute = "unknown";
    for (String property : properties) {
      String value = System.getProperty(property, jvmRoute);
      if (!value.equals(jvmRoute) && value.length() > 0) {
        jvmRoute = value;
        break;
      }
    }
    response.setContentType("text/plain");
    response.getWriter().print(jvmRoute);
  }

  @Override
  public String getServletInfo() {
    return "By invoking JvmRouteServlet, you get the node's JvmRoute. Warning: It is done via System.getProperty(\"jvmRoute\", \"unknown\")";
  }
}
