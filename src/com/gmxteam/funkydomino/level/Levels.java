/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmxteam.funkydomino.level;

/**
 *
 * @author guillaume
 */
public enum Levels {

    /**
     *
     */
    MAIN("main"),
    /**
     *
     */
    LEVEL_1("stage1"),
    /**
     *
     */
    LEVEL_2("stage2"),
    /**
     *
     */
    LEVEL_3("stage3"),
    /**
     *
     */
    LEVEL_4("stage4"),
    /**
     *
     */
    LEVEL_5("stage5");
    private static final String EXT = "xml";
    private String mString;

    Levels(String name) {
        mString = name;
    }

    @Override
    public String toString() {
        return mString + "." + EXT;
    }
}
