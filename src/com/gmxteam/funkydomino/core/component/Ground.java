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
package com.gmxteam.funkydomino.core.component;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import org.andengine.entity.Entity;
import org.andengine.entity.scene.ITouchArea;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.input.touch.TouchEvent;

/**
 * Objet définissant le sol.
 *
 * @author Guillaume Poirier-Morency
 */
public class Ground extends Component {

    private Body mGroundBody;
    private TiledSprite mGround;
    private Vector2 mVertex[];

    public ITouchArea getTouchArea() {
        return mGround;
    }

    @Override
    protected void onLoadResource() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected void onCreateFixtureDef(FixtureDef fd) {
        this.mVertex = mAttributes.getVector2Value("vector", null);
    }

    @Override
    protected void onPopulatePhysicsWorld(PhysicsWorld pw) {
        mGroundBody = PhysicsFactory.createPolygonBody(pw, mGround, mVertex, BodyDef.BodyType.DynamicBody, mFixtureDef);
    }

    @Override
    protected void onPopulateEntity(Entity e) {

        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean onAreaTouched(TouchEvent te, ITouchArea ita, float f, float f1) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
