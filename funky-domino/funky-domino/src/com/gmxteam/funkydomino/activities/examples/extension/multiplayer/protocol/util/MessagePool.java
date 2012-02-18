package org.anddev.andengine.extension.multiplayer.protocol.util;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.List;

import org.anddev.andengine.extension.multiplayer.protocol.adt.message.IMessage;
import org.anddev.andengine.util.Debug;
import org.anddev.andengine.util.pool.GenericPool;
import org.anddev.andengine.util.pool.MultiPool;

/**
 * (c) 2010 Nicolas Gramlich 
 * (c) 2011 Zynga Inc.
 * 
 * @param <M> 
 * @author Nicolas Gramlich
 * @since 11:33:23 - 02.03.2011
 */
public class MessagePool<M extends IMessage> {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private final MultiPool<M> mMessageMultiPool = new MultiPool<M>();

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

        /**
         * 
         * @param pFlag
         * @param pMessageClass
         */
        public void registerMessage(final short pFlag, final Class<? extends M> pMessageClass) {
		this.mMessageMultiPool.registerPool(pFlag,
			new GenericPool<M>() {
				@Override
				protected M onAllocatePoolItem() {
					try {
						return pMessageClass.newInstance();
					} catch (final Throwable t) {
						Debug.e(t);
						return null;
					}
				}
			}
		);
	}

        /**
         * 
         * @param pFlag
         * @return
         */
        public M obtainMessage(final short pFlag) {
		return this.mMessageMultiPool.obtainPoolItem(pFlag);
	}

        /**
         * 
         * @param pFlag
         * @param pDataInputStream
         * @return
         * @throws IOException
         */
        public M obtainMessage(final short pFlag, final DataInputStream pDataInputStream) throws IOException {
		final M message = this.mMessageMultiPool.obtainPoolItem(pFlag);
		if(message != null) { 
			message.read(pDataInputStream);
			return message;
		} else {
			throw new IllegalArgumentException("No message found for pFlag='" + pFlag + "'.");
		}
	}

        /**
         * 
         * @param pMessage
         */
        public void recycleMessage(final M pMessage) {
		this.mMessageMultiPool.recyclePoolItem(pMessage.getFlag(), pMessage);
	}

        /**
         * 
         * @param pMessages
         */
        public void recycleMessages(final List<? extends M> pMessages) {
		final MultiPool<M> messageMultiPool = this.mMessageMultiPool;
		for(int i = pMessages.size() - 1; i >= 0; i--) {
			final M message = pMessages.get(i);
			messageMultiPool.recyclePoolItem(message.getFlag(), message);
		}
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
