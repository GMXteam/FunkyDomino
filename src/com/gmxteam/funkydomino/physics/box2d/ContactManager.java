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
package com.gmxteam.funkydomino.physics.box2d;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Permet de binder le ContactListener provenant de PhysicsWorld à plusieurs
 * ContactListener afin de pouvoir gérer les événements de collisions depuis
 * plusieurs endroits en même temps.
 *
 * @author Guillaume Poirier-Morency
 */
public class ContactManager implements ContactListener {

    private ConcurrentHashMap<Body, ContactListener> mBodies = new ConcurrentHashMap<Body, ContactListener>();

    /**
     *
     * @param b
     * @param pContactListener
     */
    public void registerContactListener(Body b, ContactListener pContactListener) {
        mBodies.put(b, pContactListener);

    }

    /**
     *
     * @param cntct
     */
    public void beginContact(Contact cntct) {

        /*Debug.v("Contact entre le body " + cntct.getFixtureA().getBody()
         + " et " + cntct.getFixtureB().getBody());
         */
        final ContactListener cl = mBodies.get(cntct.getFixtureA().getBody());

        if (cl != null) {
            cl.beginContact(cntct);
        }



    }

    /**
     *
     * @param cntct
     */
    public void endContact(Contact cntct) {

        final ContactListener cl = mBodies.get(cntct.getFixtureA().getBody());

        if (cl != null) {
            cl.endContact(cntct);
        }



    }

    /**
     *
     * @param cntct
     * @param mnfld
     */
    public void preSolve(Contact cntct, Manifold mnfld) {

        final ContactListener cl = mBodies.get(cntct.getFixtureA().getBody());

        if (cl != null) {
            cl.preSolve(cntct, mnfld);
        }




    }

    /**
     *
     * @param cntct
     * @param ci
     */
    public void postSolve(Contact cntct, ContactImpulse ci) {

        final ContactListener cl = mBodies.get(cntct.getFixtureA().getBody());

        if (cl != null) {
            cl.postSolve(cntct, ci);
        }
    }
}
