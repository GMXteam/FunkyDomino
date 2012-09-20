package org.gmxteam.funkydomino.component.loader;

/**
 * Énumération des composants avec leurs loaders respectifs.
 *
 * @author guillaume
 */
public enum Loaders {

    /**
     *
     */
    level(SceneLoader.class),   
    /**
     *
     */
    domino(DominoLoader.class),
    /**
     *
     */
    ball(BallLoader.class),
    /**
     *
     */
    cog(CogLoader.class),    
    /**
     * 
     */
    hud(HUDLoader.class);
    /**
     *
     */
    private final Class mClass;

    Loaders(Class c) {
        mClass = c;
    }

    /**
     *
     * @throws IllegalAccessException
     */
    public Class getLoaderClass() {
        return mClass;
    }
}
