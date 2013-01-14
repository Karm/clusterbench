package org.jboss.test.clusterbench.web.jvmroute;

import javax.servlet.annotation.WebServlet;

import org.jboss.test.clusterbench.common.jvmroute.CommonJvmRouteServlet;

@WebServlet(name = "JvmRouteServlet", urlPatterns = { "/jvmroute" })
public class JvmRouteServlet extends CommonJvmRouteServlet {
}
