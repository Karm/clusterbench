package org.jboss.test.clusterbench.common.load;

import java.util.logging.Logger;

public class NativeMemoryStress {
    private static final Logger log = Logger.getLogger(NativeMemoryStress.class.getName());
    static {
        System.loadLibrary("MemStressNative");
    }

    public native int allocateMegabytes(int megabytes, int seconds);

}
