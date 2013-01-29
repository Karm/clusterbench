package org.jboss.test.clusterbench.web.session;

import javax.servlet.annotation.WebServlet;
import org.jboss.test.clusterbench.common.session.CommonHttpSessionServlet;

@WebServlet(name = "HttpSessionServlet", urlPatterns = { "/session" })
public class HttpSessionServlet extends CommonHttpSessionServlet {
  private static final long serialVersionUID = -8035389950697077638L;
}
