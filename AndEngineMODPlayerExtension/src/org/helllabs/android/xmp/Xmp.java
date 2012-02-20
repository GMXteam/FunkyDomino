package org.helllabs.android.xmp;


/**
 * 
 * @author guillaume
 */
public class Xmp {
    /**
     * 
     * @return
     */
    public native int init();
        /**
         * 
         * @return
         */
        public native int deinit();
        /**
         * 
         * @param name
         * @return
         */
        public native int testModule(String name);
        /**
         * 
         * @param name
         * @return
         */
        public native int loadModule(String name);
        /**
         * 
         * @return
         */
        public native int releaseModule();
        /**
         * 
         * @return
         */
        public native int startPlayer();
        /**
         * 
         * @return
         */
        public native int endPlayer();
        /**
         * 
         * @return
         */
        public native int playFrame();	
        /**
         * 
         * @return
         */
        public native int softmixer();
        /**
         * 
         * @param size
         * @param buffer
         * @return
         */
        public native short[] getBuffer(int size, short buffer[]);
        /**
         * 
         * @return
         */
        public native int nextOrd();
        /**
         * 
         * @return
         */
        public native int prevOrd();
        /**
         * 
         * @param n
         * @return
         */
        public native int setOrd(int n);
        /**
         * 
         * @return
         */
        public native int stopModule();
        /**
         * 
         * @return
         */
        public native int restartModule();
        /**
         * 
         * @return
         */
        public native int stopTimer();
        /**
         * 
         * @return
         */
        public native int restartTimer();
        /**
         * 
         * @return
         */
        public native int incGvol();
        /**
         * 
         * @return
         */
        public native int decGvol();
        /**
         * 
         * @param time
         * @return
         */
        public native int seek(long time);
        /**
         * 
         * @param name
         * @return
         */
        public native ModInfo getModInfo(String name);
        /**
         * 
         * @return
         */
        public native int time();
        /**
         * 
         * @param seconds
         * @return
         */
        public native int seek(int seconds);
        /**
         * 
         * @return
         */
        public native int getPlayTempo();
        /**
         * 
         * @return
         */
        public native int getPlayBpm();
        /**
         * 
         * @return
         */
        public native int getPlayPos();
        /**
         * 
         * @return
         */
        public native int getPlayPat();
        /**
         * 
         * @return
         */
        public native String getVersion();
        /**
         * 
         * @return
         */
        public native int getFormatCount();
        /**
         * 
         * @return
         */
        public native String[] getFormats();
	
	static {
		System.loadLibrary("xmp");
	}
}
