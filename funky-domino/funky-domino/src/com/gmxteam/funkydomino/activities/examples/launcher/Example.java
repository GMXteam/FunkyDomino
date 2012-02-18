package com.gmxteam.funkydomino.activities.examples.launcher;

import com.gmxteam.funkydomino.activities.AndEngineMainActivity;
import com.gmxteam.funkydomino.activities.examples.AnalogOnScreenControlExample;
import com.gmxteam.funkydomino.activities.examples.AnalogOnScreenControlsExample;
import com.gmxteam.funkydomino.activities.examples.AnimatedSpritesExample;
import com.gmxteam.funkydomino.activities.examples.AugmentedRealityExample;
import com.gmxteam.funkydomino.activities.examples.AugmentedRealityHorizonExample;
import com.gmxteam.funkydomino.activities.examples.AutoParallaxBackgroundExample;
import com.gmxteam.funkydomino.activities.examples.BoundCameraExample;
import com.gmxteam.funkydomino.activities.examples.ChangeableTextExample;
import com.gmxteam.funkydomino.activities.examples.ColorKeyTextureSourceDecoratorExample;
import com.gmxteam.funkydomino.activities.examples.CoordinateConversionExample;
import com.gmxteam.funkydomino.activities.examples.CustomFontExample;
import com.gmxteam.funkydomino.activities.examples.DigitalOnScreenControlExample;
import com.gmxteam.funkydomino.activities.examples.ETC1TextureExample;
import com.gmxteam.funkydomino.activities.examples.ImageFormatsExample;
import com.gmxteam.funkydomino.activities.examples.LevelLoaderExample;
import com.gmxteam.funkydomino.activities.examples.LineExample;
import com.gmxteam.funkydomino.activities.examples.LoadTextureExample;
import com.gmxteam.funkydomino.activities.examples.MenuExample;
import com.gmxteam.funkydomino.activities.examples.ModPlayerExample;
import com.gmxteam.funkydomino.activities.examples.MovingBallExample;
import com.gmxteam.funkydomino.activities.examples.MultiTouchExample;
import com.gmxteam.funkydomino.activities.examples.MusicExample;
import com.gmxteam.funkydomino.activities.examples.PVRCCZTextureExample;
import com.gmxteam.funkydomino.activities.examples.PVRGZTextureExample;
import com.gmxteam.funkydomino.activities.examples.ParticleSystemCoolExample;
import com.gmxteam.funkydomino.activities.examples.ParticleSystemNexusExample;
import com.gmxteam.funkydomino.activities.examples.ParticleSystemSimpleExample;
import com.gmxteam.funkydomino.activities.examples.PathModifierExample;
import com.gmxteam.funkydomino.activities.examples.PauseExample;
import com.gmxteam.funkydomino.activities.examples.PhysicsCollisionFilteringExample;
import com.gmxteam.funkydomino.activities.examples.PhysicsExample;
import com.gmxteam.funkydomino.activities.examples.PhysicsFixedStepExample;
import com.gmxteam.funkydomino.activities.examples.PhysicsJumpExample;
import com.gmxteam.funkydomino.activities.examples.PhysicsMouseJointExample;
import com.gmxteam.funkydomino.activities.examples.PhysicsRemoveExample;
import com.gmxteam.funkydomino.activities.examples.PhysicsRevoluteJointExample;
import com.gmxteam.funkydomino.activities.examples.PinchZoomExample;
import com.gmxteam.funkydomino.activities.examples.R;
import com.gmxteam.funkydomino.activities.examples.RectangleExample;
import com.gmxteam.funkydomino.activities.examples.RepeatingSpriteBackgroundExample;
import com.gmxteam.funkydomino.activities.examples.Rotation3DExample;
import com.gmxteam.funkydomino.activities.examples.RunnablePoolUpdateHandlerExample;
import com.gmxteam.funkydomino.activities.examples.SVGTextureRegionExample;
import com.gmxteam.funkydomino.activities.examples.ScreenCaptureExample;
import com.gmxteam.funkydomino.activities.examples.SoundExample;
import com.gmxteam.funkydomino.activities.examples.SplitScreenExample;
import com.gmxteam.funkydomino.activities.examples.SpriteBatchExample;
import com.gmxteam.funkydomino.activities.examples.SpriteExample;
import com.gmxteam.funkydomino.activities.examples.SpriteRemoveExample;
import com.gmxteam.funkydomino.activities.examples.StrokeFontExample;
import com.gmxteam.funkydomino.activities.examples.SubMenuExample;
import com.gmxteam.funkydomino.activities.examples.TextExample;
import com.gmxteam.funkydomino.activities.examples.TextMenuExample;
import com.gmxteam.funkydomino.activities.examples.TextureOptionsExample;
import com.gmxteam.funkydomino.activities.examples.TickerTextExample;
import com.gmxteam.funkydomino.activities.examples.TouchDragExample;
import com.gmxteam.funkydomino.activities.examples.UnloadResourcesExample;
import com.gmxteam.funkydomino.activities.examples.UpdateTextureExample;
import com.gmxteam.funkydomino.activities.examples.XMLLayoutExample;
import com.gmxteam.funkydomino.activities.examples.ZoomExample;
import com.gmxteam.funkydomino.activities.examples.app.cityradar.CityRadarActivity;
import com.gmxteam.funkydomino.activities.examples.benchmark.ParticleSystemBenchmark;
import com.gmxteam.funkydomino.activities.examples.benchmark.PhysicsBenchmark;
import com.gmxteam.funkydomino.activities.examples.benchmark.SpriteBenchmark;
import com.gmxteam.funkydomino.activities.examples.benchmark.TickerTextBenchmark;
import com.gmxteam.funkydomino.activities.examples.game.snake.SnakeGameActivity;
import org.anddev.andengine.ui.activity.BaseGameActivity;

/**
 * (c) 2010 Nicolas Gramlich
 * (c) 2011 Zynga
 *
 * @author Nicolas Gramlich
 * @since 20:42:27 - 16.06.2010
 */
enum Example {
    // ===========================================================
    // Elements
    // ===========================================================
    
    FUNKYDOMINO(AndEngineMainActivity.class, R.string.app_name),
    
    
    ANALOGONSCREENCONTROL(AnalogOnScreenControlExample.class, R.string.example_analogonscreencontrol),
    ANALOGONSCREENCONTROLS(AnalogOnScreenControlsExample.class, R.string.example_analogonscreencontrols),
    ANIMATEDSPRITES(AnimatedSpritesExample.class, R.string.example_animatedsprites),
    AUGMENTEDREALITY(AugmentedRealityExample.class, R.string.example_augmentedreality),
    AUGMENTEDREALITYHORIZON(AugmentedRealityHorizonExample.class, R.string.example_augmentedrealityhorizon),
    AUTOPARALLAXBACKGROUND(AutoParallaxBackgroundExample.class, R.string.example_autoparallaxbackground),
    BOUNDCAMERA(BoundCameraExample.class, R.string.example_boundcamera),
    CHANGEABLETEXT(ChangeableTextExample.class, R.string.example_changeabletext),
    COLORKEYTEXTURESOURCEDECORATOR(ColorKeyTextureSourceDecoratorExample.class, R.string.example_colorkeytexturesourcedecorator),
    COORDINATECONVERSION(CoordinateConversionExample.class, R.string.example_coordinateconversion),
    CUSTOMFONT(CustomFontExample.class, R.string.example_customfont),
    DIGITALONSCREENCONTROL(DigitalOnScreenControlExample.class, R.string.example_digitalonscreencontrol),
    
    ETC1TEXTURE(ETC1TextureExample.class, R.string.example_etc1texture),
    IMAGEFORMATS(ImageFormatsExample.class, R.string.example_imageformats),
    LEVELLOADER(LevelLoaderExample.class, R.string.example_levelloader),
    LINE(LineExample.class, R.string.example_line),
    LOADTEXTURE(LoadTextureExample.class, R.string.example_loadtexture),
    MENU(MenuExample.class, R.string.example_menu),
    MODPLAYER(ModPlayerExample.class, R.string.example_modplayer),
    MOVINGBALL(MovingBallExample.class, R.string.example_movingball),
    MULTITOUCH(MultiTouchExample.class, R.string.example_multitouch),
    MUSIC(MusicExample.class, R.string.example_music),
    PAUSE(PauseExample.class, R.string.example_pause),
    PATHMODIFIER(PathModifierExample.class, R.string.example_pathmodifier),
    PARTICLESYSTEMNEXUS(ParticleSystemNexusExample.class, R.string.example_particlesystemnexus),
    PARTICLESYSTEMCOOL(ParticleSystemCoolExample.class, R.string.example_particlesystemcool),
    PARTICLESYSTEMSIMPLE(ParticleSystemSimpleExample.class, R.string.example_particlesystemsimple),
    PHYSICSCOLLISIONFILTERING(PhysicsCollisionFilteringExample.class, R.string.example_physicscollisionfiltering),
    PHYSICS(PhysicsExample.class, R.string.example_physics),
    PHYSICSFIXEDSTEP(PhysicsFixedStepExample.class, R.string.example_physicsfixedstep),
    PHYSICSMOUSEJOINT(PhysicsMouseJointExample.class, R.string.example_physicsmousejoint),
    PHYSICSJUMP(PhysicsJumpExample.class, R.string.example_physicsjump),
    PHYSICSREVOLUTEJOINT(PhysicsRevoluteJointExample.class, R.string.example_physicsrevolutejoint),
    PHYSICSREMOVE(PhysicsRemoveExample.class, R.string.example_physicsremove),
    PINCHZOOM(PinchZoomExample.class, R.string.example_pinchzoom),
    PVRCCZTEXTURE(PVRCCZTextureExample.class, R.string.example_pvrccztexture),
    PVRGZTEXTURE(PVRGZTextureExample.class, R.string.example_pvrgztexture),
    RECTANGLE(RectangleExample.class, R.string.example_rectangle),
    REPEATINGSPRITEBACKGROUND(RepeatingSpriteBackgroundExample.class, R.string.example_repeatingspritebackground),
    ROTATION3D(Rotation3DExample.class, R.string.example_rotation3d),
    RUNNABLEPOOLUPDATEHANDLER(RunnablePoolUpdateHandlerExample.class, R.string.example_runnablepoolupdatehandler),
    SCREENCAPTURE(ScreenCaptureExample.class, R.string.example_screencapture),
    SOUND(SoundExample.class, R.string.example_sound),
    SPLITSCREEN(SplitScreenExample.class, R.string.example_splitscreen),
    SPRITEBATCH(SpriteBatchExample.class, R.string.example_spritebatch),
    SPRITE(SpriteExample.class, R.string.example_sprite),
    SPRITEREMOVE(SpriteRemoveExample.class, R.string.example_spriteremove),
    STROKEFONT(StrokeFontExample.class, R.string.example_strokefont),
    SUBMENU(SubMenuExample.class, R.string.example_submenu),
    SVGTEXTUREREGION(SVGTextureRegionExample.class, R.string.example_svgtextureregion),
    TEXT(TextExample.class, R.string.example_text),
    TEXTMENU(TextMenuExample.class, R.string.example_textmenu),
    TEXTUREOPTIONS(TextureOptionsExample.class, R.string.example_textureoptions),
   
    TICKERTEXT(TickerTextExample.class, R.string.example_tickertext),
    TOUCHDRAG(TouchDragExample.class, R.string.example_touchdrag),
    UNLOADRESOURCES(UnloadResourcesExample.class, R.string.example_unloadresources),
    UPDATETEXTURE(UpdateTextureExample.class, R.string.example_updatetexture),
    XMLLAYOUT(XMLLayoutExample.class, R.string.example_xmllayout),
    ZOOM(ZoomExample.class, R.string.example_zoom),
    BENCHMARK_PARTICLESYSTEM(ParticleSystemBenchmark.class, R.string.example_benchmark_particlesystem),
    BENCHMARK_PHYSICS(PhysicsBenchmark.class, R.string.example_benchmark_physics),
    BENCHMARK_SPRITE(SpriteBenchmark.class, R.string.example_benchmark_sprite),
    BENCHMARK_TICKERTEXT(TickerTextBenchmark.class, R.string.example_benchmark_tickertext),
    APP_CITYRADAR(CityRadarActivity.class, R.string.example_app_cityradar),
    GAME_SNAKE(SnakeGameActivity.class, R.string.example_game_snake);
    // ===========================================================
    // Constants
    // ===========================================================
    // ===========================================================
    // Fields
    // ===========================================================
    public final Class<? extends BaseGameActivity> CLASS;
    public final int NAMERESID;

    // ===========================================================
    // Constructors
    // ===========================================================
    private Example(final Class<? extends BaseGameActivity> pExampleClass, final int pNameResID) {
        this.CLASS = pExampleClass;
        this.NAMERESID = pNameResID;
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