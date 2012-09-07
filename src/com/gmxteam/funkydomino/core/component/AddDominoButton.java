/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmxteam.funkydomino.core.component;

import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.gmxteam.funkydomino.activity.GameActivity;
import com.gmxteam.funkydomino.core.ContactManager;
import com.gmxteam.funkydomino.core.component.factory.ComponentAttributes;
import com.gmxteam.funkydomino.core.component.factory.ComponentFactory;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.andengine.entity.Entity;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.ButtonSprite;
import org.andengine.entity.sprite.ButtonSprite.OnClickListener;
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
    public final int DOMINO_WIDTH = 32, DOMINO_HEIGHT = 64;

    @Override
    protected void onLoadResource() {

        BitmapTextureAtlas mBitmapTextureAtlas = new BitmapTextureAtlas(getTextureManager(), DOMINO_WIDTH, DOMINO_HEIGHT, GameActivity.TEXTURE_OPTION);


        getTextureManager().loadTexture(mBitmapTextureAtlas);


        TextureRegion mDominoTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mBitmapTextureAtlas, getContext(), "domino.png", 0, 0);


        mAddDominoButtonSprite = new ButtonSprite(0.0f, 0.0f, mDominoTextureRegion, this.getVertexBufferObjectManager());
        mAddDominoButtonSprite.setOnClickListener(this);
    }

    public void setOnClickListener(OnClickListener ocl) {
        mAddDominoButtonSprite.setOnClickListener(ocl);
    }

    @Override
    protected void onCreateFixtureDef(FixtureDef pFixtureDef, ComponentAttributes pAttributes) {
    }

    @Override
    protected void onPopulatePhysicsWorld(PhysicsWorld pPhysicsWorld, ComponentAttributes pAttributes) {
    }

    @Override
    protected void onPopulateEntity(Entity e) {
        e.attachChild(mAddDominoButtonSprite);
    }

    @Override
    protected void onRegisterTouchAreas(Scene pScene) {
        pScene.registerTouchArea(mAddDominoButtonSprite);
    }

    @Override
    protected void onRegisterContactListener(ContactManager pContactManager) {
    }

    public void onClick(ButtonSprite bs, float f, float f1) {
        try {
            mGameActivity.mScene.attachChild(ComponentFactory.createDomino(0.0f, 0.0f));
        } catch (InstantiationException ex) {
            Logger.getLogger(AddDominoButton.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(AddDominoButton.class.getName()).log(Level.SEVERE, null, ex);
        }


    }
}
