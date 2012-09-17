/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gmxteam.funkydomino.level;

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
    private String mFileName;
    /**
     *
     */
    public final String mName;
    /**
     *
     */
    public final String mDescription;

    Levels(String name, String pName, String pDescription) {
        mFileName = name;
        mName = pName;
        mDescription = pDescription;
    }

    Levels(String name, String pName) {
        this(name, pName, "");
    }

    Levels(String name) {
        this(name, name, "");
    }

    @Override
    public String toString() {
        return "level/" + mFileName + "." + EXT;
    }
}
