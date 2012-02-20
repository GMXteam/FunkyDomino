package org.andengine.extension.svg;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.andengine.extension.svg.adt.ISVGColorMapper;
import org.andengine.extension.svg.adt.SVG;
import org.andengine.extension.svg.exception.SVGParseException;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Picture;


/**
 * TODO Eventually add support for ".svgz" format. (Not totally useful as the apk itself gets zipped anyway. But might be useful, when loading from an external source.)
 * 
 * @author Larva Labs, LLC
 * (c) 2010 Nicolas Gramlich 
 * (c) 2011 Zynga Inc.
 * 
 * @author Nicolas Gramlich
 * @since 17:00:16 - 21.05.2011
 */
public class SVGParser {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

    /**
     * 
     * @param pString
     * @return
     * @throws SVGParseException
     */
    public static SVG parseSVGFromString(final String pString) throws SVGParseException {
		return SVGParser.parseSVGFromString(pString, null);
	}

    /**
     * 
     * @param pString
     * @param pSVGColorMapper
     * @return
     * @throws SVGParseException
     */
    public static SVG parseSVGFromString(final String pString, final ISVGColorMapper pSVGColorMapper) throws SVGParseException {
		return SVGParser.parseSVGFromInputStream(new ByteArrayInputStream(pString.getBytes()), pSVGColorMapper);
	}

        /**
         * 
         * @param pResources
         * @param pRawResourceID
         * @return
         * @throws SVGParseException
         */
        public static SVG parseSVGFromResource(final Resources pResources, final int pRawResourceID) throws SVGParseException {
		return SVGParser.parseSVGFromResource(pResources, pRawResourceID, null);
	}

        /**
         * 
         * @param pResources
         * @param pRawResourceID
         * @param pSVGColorMapper
         * @return
         * @throws SVGParseException
         */
        public static SVG parseSVGFromResource(final Resources pResources, final int pRawResourceID, final ISVGColorMapper pSVGColorMapper) throws SVGParseException {
		return SVGParser.parseSVGFromInputStream(pResources.openRawResource(pRawResourceID), pSVGColorMapper);
	}

        /**
         * 
         * @param pAssetManager
         * @param pAssetPath
         * @return
         * @throws SVGParseException
         * @throws IOException
         */
        public static SVG parseSVGFromAsset(final AssetManager pAssetManager, final String pAssetPath) throws SVGParseException, IOException {
		return SVGParser.parseSVGFromAsset(pAssetManager, pAssetPath, null);
	}

        /**
         * 
         * @param pAssetManager
         * @param pAssetPath
         * @param pSVGColorMapper
         * @return
         * @throws SVGParseException
         * @throws IOException
         */
        public static SVG parseSVGFromAsset(final AssetManager pAssetManager, final String pAssetPath, final ISVGColorMapper pSVGColorMapper) throws SVGParseException, IOException {
		final InputStream inputStream = pAssetManager.open(pAssetPath);
		final SVG svg = SVGParser.parseSVGFromInputStream(inputStream, pSVGColorMapper);
		inputStream.close();
		return svg;
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

        /**
         * 
         * @param pInputStream
         * @param pSVGColorMapper
         * @return
         * @throws SVGParseException
         */
        public static SVG parseSVGFromInputStream(final InputStream pInputStream, final ISVGColorMapper pSVGColorMapper) throws SVGParseException {
		try {
			final SAXParserFactory spf = SAXParserFactory.newInstance();
			final SAXParser sp = spf.newSAXParser();
			final XMLReader xr = sp.getXMLReader();
			final Picture picture = new Picture();
			final SVGHandler svgHandler = new SVGHandler(picture, pSVGColorMapper);
			xr.setContentHandler(svgHandler);
			xr.parse(new InputSource(pInputStream));
			final SVG svg = new SVG(picture, svgHandler.getBounds(), svgHandler.getComputedBounds());
			return svg;
		} catch (final Exception e) {
			throw new SVGParseException(e);
		}
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
