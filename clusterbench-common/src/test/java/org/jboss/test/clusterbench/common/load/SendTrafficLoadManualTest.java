package org.jboss.test.clusterbench.common.load;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.junit.Test;

public class SendTrafficLoadManualTest {
   private static final Logger log = Logger.getLogger(SendTrafficLoadManualTest.class.getName());
   private static final int KILOBYTES = 4;

   @Test
   public void sendTrafficLoadMetricManualTest() throws HttpException, IOException {
      String url = "http://localhost:8080/clusterbench/sendtrafficload?kilobytes="+KILOBYTES;
      HttpClient httpClient = new HttpClient();
      GetMethod getMethod = null;
      httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(30000);
      getMethod = new GetMethod(url);
      httpClient.executeMethod(getMethod);
      String response = getMethod.getResponseBodyAsString();
      log.log(Level.INFO,"RESPONSE:"+response);
      int value = Integer.parseInt(response.split(";")[1]);
      assertEquals(KILOBYTES, value);
   }
}
