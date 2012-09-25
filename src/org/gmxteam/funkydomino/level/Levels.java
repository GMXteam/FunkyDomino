/*
 *   This file is part of Funky Domino.
 *
 *   Funky Domino is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   Funky Domino is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with Funky Domino.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.gmxteam.funkydomino.level;

/**
 * Énumération des niveaux de jeu.
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

    /**
     * 
     * @return 
     * @deprecated use getPath instead.
     */
    @Override
    @Deprecated
    public String toString() {
        return "level/" + mFileName + "." + EXT;
    }

    /**
     *
     * @return
     */
    public String getPath() {
        return "level/" + mFileName + "." + EXT;

    }
}
