package org.jboss.test.clusterbench.common.load;

import static org.junit.Assert.assertEquals;

import java.util.logging.Logger;

import org.junit.Test;

public class SendTrafficLoadTest {
   private static final Logger log = Logger.getLogger(SendTrafficLoadTest.class.getName());

   private SendTrafficLoad sendTrafficLoad = null; 
   
   @Test
   public void generate1KBRubbishTest() {
       sendTrafficLoad = new SendTrafficLoad();
       assertEquals(1024, SendTrafficLoad.oneKilobyte.length);
       assertEquals(1024,sendTrafficLoad.generateRubbish(1).length());
       assertEquals(100*1024,sendTrafficLoad.generateRubbish(100).length());
   }
   
}
