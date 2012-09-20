package org.gmxteam.funkydomino.component.loader;

/**
 * Énumération des composants avec leurs loaders respectifs.
 * @deprecated Mauvaise implémentation.
 * @author guillaume
 */
@Deprecated
public enum Loaders {

    /**
     * 
     */
    level(SceneLoader.class),   
    /**
     * 
     */
    ground(GroundLoader.class),
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
     * @return @throws InstantiationException
     * @throws IllegalAccessException
     */
    public Class getLoaderClass() {
        return mClass;
    }
}
