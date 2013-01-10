package org.jboss.test.clusterbench.common.load;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.junit.Test;

public class SendTrafficLoadTest {
   private static final Logger log = Logger.getLogger(SendTrafficLoadTest.class.getName());
   private static final int KILOBYTES = 4;

   private SendTrafficLoad sendTrafficLoad = null; 

   @Test
   public void generate1KBRubbishTest() {
       sendTrafficLoad = new SendTrafficLoad();
       assertEquals(1024, SendTrafficLoad.oneKilobyte.length);
       assertEquals(1024,sendTrafficLoad.generateRubbish(1).length());
       assertEquals(100*1024,sendTrafficLoad.generateRubbish(100).length());
   }

}
