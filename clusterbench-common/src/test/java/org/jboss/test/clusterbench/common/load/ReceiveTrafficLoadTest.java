package org.jboss.test.clusterbench.common.load;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.Test;

public class ReceiveTrafficLoadTest {
   private static final Logger log = Logger.getLogger(ReceiveTrafficLoadTest.class.getName());
   private static final int KILOBYTES = 4;
   
   @Test
   public void receiveTrafficLoadMetricManualTest() throws HttpException, IOException {
      SendTrafficLoad sendTrafficLoad = new SendTrafficLoad();
      String url = "http://localhost:8080/clusterbench/receivetrafficload";
      HttpClient httpClient = new HttpClient();
      PostMethod postMethod = null;
      httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(30000);
      postMethod = new PostMethod(url);
      NameValuePair[] data = { new NameValuePair("data", sendTrafficLoad.generateRubbish(KILOBYTES)) };
      postMethod.setRequestBody(data);
      //postMethod.setFollowRedirects(true);
      httpClient.executeMethod(postMethod);
      String response = postMethod.getResponseBodyAsString();
      log.log(Level.INFO,"RESPONSE:"+response);
      //DONE;Received;KILOBYTES;KB
      int value = Integer.parseInt(response.split(";")[2]);
      assertEquals(KILOBYTES, value);
   }

}
