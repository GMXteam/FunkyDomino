package com.gmxteam.funkydomino.activities.examples.launcher;

import com.gmxteam.funkydomino.activities.examples.R;

/**
 * (c) 2010 Nicolas Gramlich
 * (c) 2011 Zynga
 *
 * @author Nicolas Gramlich
 * @since 11:13:34 - 27.06.2010
 */
public enum ExampleGroup {
    // ===========================================================
    // Elements
    // ===========================================================

    /**
     * 
     */
    SIMPLE(R.string.examplegroup_simple, Example.FUNKYDOMINO,
    Example.LINE, Example.RECTANGLE, Example.SPRITE, Example.SPRITEREMOVE, Example.SPRITEBATCH),
    /**
     * 
     */
    MODIFIER_AND_ANIMATION(R.string.examplegroup_modifier_and_animation,
    Example.MOVINGBALL, Example.PATHMODIFIER, Example.ANIMATEDSPRITES, Example.ROTATION3D),
    /**
     * 
     */
    TOUCH(R.string.examplegroup_touch,
    Example.TOUCHDRAG, Example.MULTITOUCH, Example.ANALOGONSCREENCONTROL, Example.DIGITALONSCREENCONTROL, Example.ANALOGONSCREENCONTROLS, Example.COORDINATECONVERSION, Example.PINCHZOOM),
    /**
     * 
     */
    PARTICLESYSTEM(R.string.examplegroup_particlesystems,
    Example.PARTICLESYSTEMSIMPLE, Example.PARTICLESYSTEMCOOL, Example.PARTICLESYSTEMNEXUS),
    /**
     * 
     */
    MULTIPLAYER(R.string.examplegroup_multiplayer),
    /**
     * 
     */
    PHYSICS(R.string.examplegroup_physics,
    Example.PHYSICS, Example.PHYSICSFIXEDSTEP, Example.PHYSICSCOLLISIONFILTERING, Example.PHYSICSJUMP, Example.PHYSICSREVOLUTEJOINT, Example.PHYSICSMOUSEJOINT, Example.PHYSICSREMOVE),
    /**
     * 
     */
    TEXT(R.string.examplegroup_text,
    Example.TEXT, Example.TICKERTEXT, Example.CHANGEABLETEXT, Example.CUSTOMFONT, Example.STROKEFONT),
    /**
     * 
     */
    AUDIO(R.string.examplegroup_audio,
    Example.SOUND, Example.MUSIC, Example.MODPLAYER),
    /**
     * 
     */
    ADVANCED(R.string.examplegroup_advanced,
    Example.SPLITSCREEN, Example.BOUNDCAMERA), // Example.AUGMENTEDREALITY, Example.AUGMENTEDREALITYHORIZON),
    /**
     * 
     */
    BACKGROUND(R.string.examplegroup_background,
    Example.REPEATINGSPRITEBACKGROUND, Example.AUTOPARALLAXBACKGROUND),
    /**
     * 
     */
    OTHER(R.string.examplegroup_other,
    Example.SCREENCAPTURE, Example.PAUSE, Example.MENU, Example.SUBMENU, Example.TEXTMENU, Example.ZOOM, Example.IMAGEFORMATS, Example.PVRCCZTEXTURE, Example.PVRGZTEXTURE, Example.ETC1TEXTURE, Example.TEXTUREOPTIONS, Example.COLORKEYTEXTURESOURCEDECORATOR, Example.LOADTEXTURE, Example.UPDATETEXTURE, Example.RUNNABLEPOOLUPDATEHANDLER, Example.SVGTEXTUREREGION, Example.XMLLAYOUT, Example.LEVELLOADER),
    /**
     * 
     */
    APP(R.string.examplegroup_app,
    Example.APP_CITYRADAR),
    /**
     * 
     */
    BENCHMARK(R.string.examplegroup_benchmark,
    Example.BENCHMARK_TICKERTEXT, Example.BENCHMARK_PARTICLESYSTEM, Example.BENCHMARK_PHYSICS);
    // ===========================================================
    // Constants
    // ===========================================================
    // ===========================================================
    // Fields
    // ===========================================================
    /**
     * 
     */
    public final Example[] mExamples;
    /**
     * 
     */
    public final int mNameResourceID;

    // ===========================================================
    // Constructors
    // ===========================================================
    private ExampleGroup(final int pNameResourceID, final Example... pExamples) {
        this.mNameResourceID = pNameResourceID;
        this.mExamples = pExamples;
    }
    // ===========================================================
    // Getter & Setter
    // ===========================================================
    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================
    // ===========================================================
    // Methods
    // ===========================================================
    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================
}
