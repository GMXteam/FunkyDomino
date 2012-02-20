package com.badlogic.gdx.physics.box2d;


/**
 * 
 * @author guillaume
 */
public class ContactImpulse {
        final World world;
        long addr;
        float[] tmp = new float[2];
        final float[] normalImpulses = new float[2];
        final float[] tangentImpulses = new float[2];
        
        /**
         * 
         * @param world
         * @param addr
         */
        protected ContactImpulse(World world, long addr) {
                this.world = world;
                this.addr = addr;
        }
        
        /**
         * 
         * @return
         */
        public float[] getNormalImpulses() {
                jniGetNormalImpulses(addr, normalImpulses);             
                return normalImpulses;
        }
        
        private native void jniGetNormalImpulses(long addr, float[] values);
        
        /**
         * 
         * @return
         */
        public float[] getTangentImpulses() {
                jniGetTangentImpulses(addr, tangentImpulses);           
                return tangentImpulses;
        }
        
        private native void jniGetTangentImpulses(long addr, float[] values);
}
