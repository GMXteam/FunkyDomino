package org.andengine.opengl.shader.source.criteria;

import org.andengine.opengl.util.GLState;

/**
 * (c) Zynga 2011
 *
 * @author Nicolas Gramlich <ngramlich@zynga.com>
 * @since 18:10:26 - 12.10.2011
 */
public class LogicalOrShaderSourceCriteria implements IShaderSourceCriteria {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	
	private final IShaderSourceCriteria[] mShaderSourceCriterias;

	// ===========================================================
	// Constructors
	// ===========================================================
	
        /**
         * 
         * @param pShaderSourceCriterias
         */
        public LogicalOrShaderSourceCriteria(final IShaderSourceCriteria ... pShaderSourceCriterias) {
		this.mShaderSourceCriterias = pShaderSourceCriterias;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

        /**
         * 
         * @param pGLState
         * @return
         */
        @Override
	public boolean isMet(final GLState pGLState) {
		for(IShaderSourceCriteria shaderSourceCriteria : this.mShaderSourceCriterias) {
			if(shaderSourceCriteria.isMet(pGLState)) {
				return true;
			}
		}
		return false;
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
