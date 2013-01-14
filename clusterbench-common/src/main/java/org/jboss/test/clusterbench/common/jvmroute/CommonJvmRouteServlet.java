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
  public static final String KEY = CommonJvmRouteServlet.class.getName();

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/plain");
    response.getWriter().print(System.getProperty("jvmRoute", "unknown jvmRoute") + ";" 
        + System.getProperty("jboss.mod_cluster.jvmRoute", "unknown jboss.mod_cluster.jvmRoute") + ";" 
        + System.getProperty("instance-id", "unknown instance-id") + ";" 
        + System.getProperty("jboss.domain.web.instance-id", "unknown jboss.domain.web.instance-id") + ";" 
        + System.getProperty("jboss.jvmRoute", "unknown jboss.jvmRoute")
    );
  }

  @Override
  public String getServletInfo() {
    return "By invoking JvmRouteServlet, you get the node's JvmRoute. Warning: It is done via System.getProperty(\"jvmRoute\", \"unknown\")";
  }
}
