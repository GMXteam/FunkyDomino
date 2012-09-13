/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmxteam.funkydomino.core.component;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.gmxteam.funkydomino.activity.GameActivity;
import com.gmxteam.funkydomino.core.physics.box2d.ContactManager;
import com.gmxteam.funkydomino.core.component.factory.ComponentAttributes;
import com.gmxteam.funkydomino.core.component.factory.ComponentFactory;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.andengine.entity.Entity;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.ButtonSprite;
import org.andengine.entity.sprite.ButtonSprite.OnClickListener;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.TextureRegion;

/**
 *
 * @author Usager
 */
public class AddDominoButton extends Component implements OnClickListener {

    ButtonSprite mAddDominoButtonSprite;
    /**
     *
     */
    public final int DOMINO_WIDTH = 32,
    /**
     *
     */
    DOMINO_HEIGHT = 64;
    private TextureRegion mDominoTextureRegion;

    /**
     *
     */
    @Override
    protected void onLoadResource() {

        BitmapTextureAtlas mBitmapTextureAtlas = new BitmapTextureAtlas(getTextureManager(), DOMINO_WIDTH, DOMINO_HEIGHT, GameActivity.TEXTURE_OPTION);


        getTextureManager().loadTexture(mBitmapTextureAtlas);


         mDominoTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mBitmapTextureAtlas, getContext(), "domino.png", 0, 0);


       
    }
    
    
    /**
     *
     * @param pX
     * @param pY
     * @param angle
     */
    @Override
    protected void onCreateSprite(float pX, float pY, float angle) {
       mAddDominoButtonSprite = new ButtonSprite(pX, pY, mDominoTextureRegion, this.getVertexBufferObjectManager());
       mAddDominoButtonSprite.setRotation(angle);
        mAddDominoButtonSprite.setOnClickListener(this);
    }

    /**
     *
     * @param ocl
     */
    public void setOnClickListener(OnClickListener ocl) {
        mAddDominoButtonSprite.setOnClickListener(ocl);
    }

    

    @Override
    protected void onPopulatePhysicsWorld(PhysicsWorld pPhysicsWorld) {
    }

    @Override
    protected void onPopulateEntity(Entity e) {
        e.attachChild(mAddDominoButtonSprite);
    }

    @Override
    protected void onRegisterTouchAreas(Scene pScene) {
        pScene.registerTouchArea(mAddDominoButtonSprite);
    }

    /**
     *
     * @param pContactManager
     */
    @Override
    protected void onRegisterContactListener(ContactManager pContactManager) {
    }

    /**
     *
     * @param bs
     * @param f
     * @param f1
     */
    public void onClick(ButtonSprite bs, float f, float f1) {
        try {
            mFunkyDominoBaseActivity.getScene().attachChild(ComponentFactory.createDomino(0.0f, 0.0f));
        } catch (InstantiationException ex) {
            Logger.getLogger(AddDominoButton.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(AddDominoButton.class.getName()).log(Level.SEVERE, null, ex);
        }


    }


}
