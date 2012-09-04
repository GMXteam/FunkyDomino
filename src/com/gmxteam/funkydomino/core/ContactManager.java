/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmxteam.funkydomino.core;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import java.util.HashMap;

/**
 *
 * @author guillaume
 */
public class ContactManager extends HashMap<Body, ContactListener> {

    public ContactListener getContactListener() {
        return mContactListener;

    }

    public void registerContactListener(Body b, ContactListener pContactListener) {
        put(b, pContactListener);

    }
    private ContactListener mContactListener = new ContactListener() {
        public void beginContact(Contact cntct) {

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
    };
}