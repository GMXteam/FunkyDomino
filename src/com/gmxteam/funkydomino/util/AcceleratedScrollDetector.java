/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmxteam.funkydomino.util;

import org.andengine.input.touch.detector.ScrollDetector;

/**
 *
 * @author guillaume
 */
public class AcceleratedScrollDetector extends ScrollDetector {

    public AcceleratedScrollDetector(final IScrollDetectorListener pScrollDetectorListener) {
        super(pScrollDetectorListener);
    }

    public AcceleratedScrollDetector(final float pTriggerScrollMinimumDistance, final IScrollDetectorListener pScrollDetectorListener) {
        super(pTriggerScrollMinimumDistance, pScrollDetectorListener);

    }
}
