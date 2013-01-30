package org.jboss.test.clusterbench.web.jvmroute;

import javax.servlet.annotation.WebServlet;

import org.jboss.test.clusterbench.common.jvmroute.CommonRequestInfoServlet;

@WebServlet(name = "RequestInfoServlet", urlPatterns = { "/requestinfo" })
public class RequestInfoServlet extends CommonRequestInfoServlet {
  private static final long serialVersionUID = -7788063893669970638L;
}