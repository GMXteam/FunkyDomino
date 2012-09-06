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
package com.gmxteam.funkydomino.core;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import java.util.HashMap;
import org.andengine.util.debug.Debug;

/**
 *
 * @author guillaume
 */
public class ContactManager extends HashMap<Body, ContactListener> implements ContactListener {

    public void registerContactListener(Body b, ContactListener pContactListener) {
        put(b, pContactListener);

    }

    public void beginContact(Contact cntct) {

        Debug.v("Contact entre le body " + cntct.getFixtureA().getBody()
                + " et " + cntct.getFixtureB().getBody());

        ContactListener cl;

        if ((cl = get(cntct.getFixtureA().getBody())) != null) {
            cl.beginContact(cntct);
        }



    }

    public void endContact(Contact cntct) {

        ContactListener cl;

        if ((cl = get(cntct.getFixtureA().getBody())) != null) {
            cl.endContact(cntct);
        }



    }

    public void preSolve(Contact cntct, Manifold mnfld) {

        ContactListener cl;

        if ((cl = get(cntct.getFixtureA().getBody())) != null) {
            cl.preSolve(cntct, mnfld);
        }




    }

    public void postSolve(Contact cntct, ContactImpulse ci) {

        ContactListener cl;

        if ((cl = get(cntct.getFixtureA().getBody())) != null) {
            cl.postSolve(cntct, ci);
        }
    }
}