package org.jboss.test.clusterbench.common.load;

import java.util.logging.Level;
import java.util.logging.Logger;

public class NativeMemoryStress {
    private static final Logger log = Logger.getLogger(NativeMemoryStress.class.getName());
    static {
        // We can not rely on this, because we can not set java.library.path
        // System.loadLibrary("MemStressNative");

        String pathToLib = null;
        String osArch = System.getProperty("os.arch").toLowerCase();
        String osName = System.getProperty("os.name").toLowerCase();

        if (osArch.endsWith("64")) {
            if (osName.startsWith("windows")) {
                pathToLib = NativeMemoryStress.class.getResource("NativeMemoryStress.class").getPath().split("/lib/")[0] + "/lib/libMemStressNative64.dll";
            } else if (osName.startsWith("linux")) {
                pathToLib = NativeMemoryStress.class.getResource("NativeMemoryStress.class").getPath().split("/lib/")[0] + "/lib/libMemStressNative64.so";
            } else if (osName.startsWith("sunos")) {
                throw new UnsupportedOperationException("I am sorry, your system \"" + osName + "\" is not supported at the moment.");
            } else {
                throw new UnsupportedOperationException("I am sorry, your system \"" + osName + "\" is not supported at the moment.");
            }
        } else if (osArch.endsWith("86")) {
            if (osName.startsWith("windows")) {
                pathToLib = NativeMemoryStress.class.getResource("NativeMemoryStress.class").getPath().split("/lib/")[0] + "/lib/libMemStressNative32.dll";
            } else if (osName.startsWith("linux")) {
                pathToLib = NativeMemoryStress.class.getResource("NativeMemoryStress.class").getPath().split("/lib/")[0] + "/lib/libMemStressNative32.so";
            } else if (osName.startsWith("sunos")) {
                throw new UnsupportedOperationException("I am sorry, your system \"" + osName + "\" is not supported at the moment.");
            } else {
                throw new UnsupportedOperationException("I am sorry, your system \"" + osName + "\" is not supported at the moment.");
            }
        } else if (osArch.contains("sparc")) {
            if (osName.startsWith("sunos")) {
                throw new UnsupportedOperationException("I am sorry, your system \"" + osName + "\" is not supported at the moment.");
            } else {
                throw new UnsupportedOperationException("I am sorry, your system \"" + osName + "\" is not supported at the moment.");
            }
        } else {
            throw new UnsupportedOperationException("I am sorry, your architecture \"" + osArch + "\" is not supported at the moment.");
        }

        log.log(Level.INFO, "I am gonna load native lib:"+pathToLib);
        System.load(pathToLib);
    }

    public native int allocateMegabytes(int megabytes, int milliseconds);

}
