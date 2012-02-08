package org.jboss.test.clusterbench.common.load;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.management.JMException;

import org.junit.Test;

public class NativeMemoryStressManualTest {
   private static final Logger log = Logger.getLogger(NativeMemoryStressManualTest.class.getName());

   private NativeMemoryStress nativeMemoryStress = null; 
   
   @Test
   public void getFreePercentTest() throws JMException {
      nativeMemoryStress = new NativeMemoryStress();
      log.log(Level.INFO,"Gonna call nativeMemoryStress.allocateMegabytes(500,5)");
      //500MB for 5000ms
      log.log(Level.INFO,"Return code:"+nativeMemoryStress.allocateMegabytes(500,5));
   }
   
}
