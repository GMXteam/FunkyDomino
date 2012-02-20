package org.andengine.extension.physics.box2d;

import java.util.Iterator;
import java.util.List;

import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.engine.handler.runnable.RunnableHandler;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactFilter;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.DestructionListener;
import com.badlogic.gdx.physics.box2d.Joint;
import com.badlogic.gdx.physics.box2d.JointDef;
import com.badlogic.gdx.physics.box2d.QueryCallback;
import com.badlogic.gdx.physics.box2d.RayCastCallback;
import com.badlogic.gdx.physics.box2d.World;

/**
 * (c) 2010 Nicolas Gramlich 
 * (c) 2011 Zynga Inc.
 * 
 * @author Nicolas Gramlich
 * @since 15:18:19 - 15.07.2010
 */
public class PhysicsWorld implements IUpdateHandler {
	// ===========================================================
	// Constants
	// ===========================================================

	static {
		System.loadLibrary( "andenginephysicsbox2dextension" );
	}

        /**
         * 
         */
        public static final int VELOCITY_ITERATIONS_DEFAULT = 8;
        /**
         * 
         */
        public static final int POSITION_ITERATIONS_DEFAULT = 8;

	// ===========================================================
	// Fields
	// ===========================================================

        /**
         * 
         */
        protected final PhysicsConnectorManager mPhysicsConnectorManager = new PhysicsConnectorManager();
        /**
         * 
         */
        protected final RunnableHandler mRunnableHandler = new RunnableHandler();
        /**
         * 
         */
        protected final World mWorld;

        /**
         * 
         */
        protected int mVelocityIterations = VELOCITY_ITERATIONS_DEFAULT;
        /**
         * 
         */
        protected int mPositionIterations = POSITION_ITERATIONS_DEFAULT;

	// ===========================================================
	// Constructors
	// ===========================================================

        /**
         * 
         * @param pGravity
         * @param pAllowSleep
         */
        public PhysicsWorld(final Vector2 pGravity, final boolean pAllowSleep) {
		this(pGravity, pAllowSleep, VELOCITY_ITERATIONS_DEFAULT, POSITION_ITERATIONS_DEFAULT);
	}

        /**
         * 
         * @param pGravity
         * @param pAllowSleep
         * @param pVelocityIterations
         * @param pPositionIterations
         */
        public PhysicsWorld(final Vector2 pGravity, final boolean pAllowSleep, final int pVelocityIterations, final int pPositionIterations) {
		this.mWorld = new World(pGravity, pAllowSleep);
		this.mVelocityIterations = pVelocityIterations;
		this.mPositionIterations = pPositionIterations;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	//	public World getWorld() {
	//		return this.mWorld;
	//	}

        /**
         * 
         * @return
         */
        public int getPositionIterations() {
		return this.mPositionIterations;
	}

        /**
         * 
         * @param pPositionIterations
         */
        public void setPositionIterations(final int pPositionIterations) {
		this.mPositionIterations = pPositionIterations;
	}

        /**
         * 
         * @return
         */
        public int getVelocityIterations() {
		return this.mVelocityIterations;
	}

        /**
         * 
         * @param pVelocityIterations
         */
        public void setVelocityIterations(final int pVelocityIterations) {
		this.mVelocityIterations = pVelocityIterations;
	}

        /**
         * 
         * @return
         */
        public PhysicsConnectorManager getPhysicsConnectorManager() {
		return this.mPhysicsConnectorManager;
	}

        /**
         * 
         */
        public void clearPhysicsConnectors() {
		this.mPhysicsConnectorManager.clear();
	}

        /**
         * 
         * @param pPhysicsConnector
         */
        public void registerPhysicsConnector(final PhysicsConnector pPhysicsConnector) {
		this.mPhysicsConnectorManager.add(pPhysicsConnector);
	}

        /**
         * 
         * @param pPhysicsConnector
         */
        public void unregisterPhysicsConnector(final PhysicsConnector pPhysicsConnector) {
		this.mPhysicsConnectorManager.remove(pPhysicsConnector);
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public void onUpdate(final float pSecondsElapsed) {
		this.mRunnableHandler.onUpdate(pSecondsElapsed);
		this.mWorld.step(pSecondsElapsed, this.mVelocityIterations, this.mPositionIterations);
		this.mPhysicsConnectorManager.onUpdate(pSecondsElapsed);
	}

	@Override
	public void reset() {
		// TODO Reset all native physics objects !?!??!
		this.mPhysicsConnectorManager.reset();
		this.mRunnableHandler.reset();
	}

	// ===========================================================
	// Methods
	// ===========================================================

        /**
         * 
         * @param pRunnable
         */
        public void postRunnable(final Runnable pRunnable) {
		this.mRunnableHandler.postRunnable(pRunnable);
	}

        /**
         * 
         */
        public void clearForces() {
		this.mWorld.clearForces();
	}

        /**
         * 
         * @param pDef
         * @return
         */
        public Body createBody(final BodyDef pDef) {
		return this.mWorld.createBody(pDef);
	}

        /**
         * 
         * @param pDef
         * @return
         */
        public Joint createJoint(final JointDef pDef) {
		return this.mWorld.createJoint(pDef);
	}

        /**
         * 
         * @param pBody
         */
        public void destroyBody(final Body pBody) {
		this.mWorld.destroyBody(pBody);
	}

        /**
         * 
         * @param pJoint
         */
        public void destroyJoint(final Joint pJoint) {
		this.mWorld.destroyJoint(pJoint);
	}

        /**
         * 
         */
        public void dispose() {
		this.mWorld.dispose();
	}

        /**
         * 
         * @return
         */
        public boolean getAutoClearForces() {
		return this.mWorld.getAutoClearForces();
	}

        /**
         * 
         * @return
         */
        public Iterator<Body> getBodies() {
		return this.mWorld.getBodies();
	}

        /**
         * 
         * @return
         */
        public int getBodyCount() {
		return this.mWorld.getBodyCount();
	}

        /**
         * 
         * @return
         */
        public int getContactCount() {
		return this.mWorld.getContactCount();
	}

        /**
         * 
         * @return
         */
        public List<Contact> getContactList() {
		return this.mWorld.getContactList();
	}

        /**
         * 
         * @return
         */
        public Vector2 getGravity() {
		return this.mWorld.getGravity();
	}

        /**
         * 
         * @return
         */
        public Iterator<Joint> getJoints() {
		return this.mWorld.getJoints();
	}

        /**
         * 
         * @return
         */
        public int getJointCount() {
		return this.mWorld.getJointCount();
	}

        /**
         * 
         * @return
         */
        public int getProxyCount() {
		return this.mWorld.getProxyCount();
	}

        /**
         * 
         * @return
         */
        public boolean isLocked() {
		return this.mWorld.isLocked();
	}

        /**
         * 
         * @param pCallback
         * @param pLowerX
         * @param pLowerY
         * @param pUpperX
         * @param pUpperY
         */
        public void QueryAABB(final QueryCallback pCallback, final float pLowerX, final float pLowerY, final float pUpperX, final float pUpperY) {
		this.mWorld.QueryAABB(pCallback, pLowerX, pLowerY, pUpperX, pUpperY);
	}

        /**
         * 
         * @param pFlag
         */
        public void setAutoClearForces(final boolean pFlag) {
		this.mWorld.setAutoClearForces(pFlag);
	}

        /**
         * 
         * @param pFilter
         */
        public void setContactFilter(final ContactFilter pFilter) {
		this.mWorld.setContactFilter(pFilter);
	}

        /**
         * 
         * @param pListener
         */
        public void setContactListener(final ContactListener pListener) {
		this.mWorld.setContactListener(pListener);
	}

        /**
         * 
         * @param pFlag
         */
        public void setContinuousPhysics(final boolean pFlag) {
		this.mWorld.setContinuousPhysics(pFlag);
	}

        /**
         * 
         * @param pListener
         */
        public void setDestructionListener(final DestructionListener pListener) {
		this.mWorld.setDestructionListener(pListener);
	}

        /**
         * 
         * @param pGravity
         */
        public void setGravity(final Vector2 pGravity) {
		this.mWorld.setGravity(pGravity);
	}

        /**
         * 
         * @param pFlag
         */
        public void setWarmStarting(final boolean pFlag) {
		this.mWorld.setWarmStarting(pFlag);
	}

        /**
         * 
         * @param pRayCastCallback
         * @param pPoint1
         * @param pPoint2
         */
        public void rayCast(final RayCastCallback pRayCastCallback, final Vector2 pPoint1, final Vector2 pPoint2) {
		this.mWorld.rayCast(pRayCastCallback, pPoint1, pPoint2);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
