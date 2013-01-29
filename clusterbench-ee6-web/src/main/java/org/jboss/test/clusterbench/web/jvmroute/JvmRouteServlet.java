package org.jboss.test.clusterbench.web.jvmroute;

import javax.servlet.annotation.WebServlet;

import org.jboss.test.clusterbench.common.jvmroute.CommonJvmRouteServlet;

@WebServlet(name = "JvmRouteServlet", urlPatterns = { "/jvmroute" })
public class JvmRouteServlet extends CommonJvmRouteServlet {
  private static final long serialVersionUID = 1855772223216460567L;
}
