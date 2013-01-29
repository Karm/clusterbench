package org.jboss.test.clusterbench.common.jvmroute;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.management.AttributeNotFoundException;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.ReflectionException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommonJvmRouteServlet extends HttpServlet {

  private static final long serialVersionUID = 726150838261866065L;
  private static final Logger log = Logger.getLogger(CommonJvmRouteServlet.class.getName());
  private static final String[] properties = new String[] { "jvmRoute", "jboss.mod_cluster.jvmRoute", "instance-id", "jboss.domain.web.instance-id", "jboss.jvmRoute" };
  private static final String UNKNOWN = "unknown";

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.getSession();
    response.setContentType("text/plain");

    // This should work for both AS5 and AS7 with reasonable set jvmRoute property.
    String jvmRoute1 = UNKNOWN;
    String jvmRoute2 = UNKNOWN;
    String jvmRoute3 = UNKNOWN;
    String jvmRoute4 = UNKNOWN;
    for (String property : properties) {
      String value = System.getProperty(property, UNKNOWN);
      if (!value.equals(UNKNOWN) && value.length() > 0) {
        jvmRoute1 = value;
        break;
      }
    }

    // Hmm, maybe, we are deployed into Tomcat?
    if (jvmRoute1.equals(UNKNOWN)) {
      try {
        MBeanServer mbsc = ManagementFactory.getPlatformMBeanServer();
        ObjectName on = null;
        try {
          on = new ObjectName("Catalina:type=Engine");
          jvmRoute2 = (String) mbsc.getAttribute(on, "jvmRoute");
        } catch (InstanceNotFoundException ex1) {
          try {

            // Crap. It was not Catalina..., let's try jboss.web:
            on = new ObjectName("jboss.web:type=Engine");
            jvmRoute3 = (String) mbsc.getAttribute(on, "jvmRoute");

            // Well, it looks like AS7 with no jvmRoute set, let's try to retrieve instance-id...
          } catch (InstanceNotFoundException ex2) {
            try {
              on = new ObjectName("jboss.as:subsystem=web");
              jvmRoute4 = (String) mbsc.getAttribute(on, "instance-id");
            } catch (InstanceNotFoundException ex3) {
              log.log(Level.WARNING, "We have failed to determine the jvmRoute.");
            }
          }
        }

      } catch (AttributeNotFoundException e) {
        log.log(Level.SEVERE, ":-(");
        e.printStackTrace();
      } catch (MalformedObjectNameException e) {
        log.log(Level.SEVERE, ":-(");
        e.printStackTrace();
      } catch (MBeanException e) {
        log.log(Level.SEVERE, ":-(");
        e.printStackTrace();
      } catch (ReflectionException e) {
        log.log(Level.SEVERE, ":-(");
        e.printStackTrace();
      } catch (NullPointerException e) {
        log.log(Level.SEVERE, ":-(");
        e.printStackTrace();
      }
    }

    String jvmRoute = UNKNOWN;
    if (!jvmRoute1.equals(UNKNOWN)) {
      jvmRoute = jvmRoute1;
    } else if (!jvmRoute2.equals(UNKNOWN)) {
      jvmRoute = jvmRoute2;
    } else if (!jvmRoute3.equals(UNKNOWN)) {
      jvmRoute = jvmRoute3;
    } else if (!jvmRoute4.equals(UNKNOWN)) {
      jvmRoute = jvmRoute4;
    } else {
      log.log(Level.WARNING, "We have failed to determine the jvmRoute.");
    }
    response.getWriter().print(jvmRoute);

  }

  @Override
  public String getServletInfo() {
    return "By invoking JvmRouteServlet, you get the node's JvmRoute. Warning: It is done via System.getProperty(\"jvmRoute\", \"unknown\")";
  }

  protected String getInitParameter(String name, String defaultValue) {
    String value = this.getInitParameter(name);

    if (value == null) {
      value = this.getServletContext().getInitParameter(name);
    }

    return (value != null) ? value : defaultValue;
  }
}
