package org.jboss.test.clusterbench.web.session;

import org.jboss.test.clusterbench.common.session.CommonHttpSessionServlet;

import javax.servlet.annotation.WebServlet;

@WebServlet(name = "HttpSessionServlet", urlPatterns = {"/session", "/session/*"})
public class HttpSessionServlet extends CommonHttpSessionServlet {
    private static final long serialVersionUID = -8035389950697077638L;
    // Silence is golden
}
