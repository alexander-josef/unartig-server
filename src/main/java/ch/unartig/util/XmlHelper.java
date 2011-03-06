/*-*
 *
 * FILENAME  :
 *    $RCSfile$
 *
 *    @author alex$             
 *    @since Nov 21, 2005$
 *
 * Copyright (c) 2005 unartig AG  --  All rights reserved
 *
 * STATUS  :
 *    $Revision$, $State$, $Name$
 *
 *    $Author$, $Locker$
 *    $Date$
 *
 *************************************************
 * $Log$
 * Revision 1.1  2007/03/01 18:23:42  alex
 * initial commit maven setup no history
 *
 * Revision 1.2  2006/08/25 23:27:58  alex
 * payment i18n
 *
 * Revision 1.1  2005/11/21 17:52:59  alex
 * no account action , photo order
 *
 ****************************************************************/
package ch.unartig.util;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import java.io.IOException;
import java.io.InputStream;

public class XmlHelper
{

    /**
     * @param url the url
     * @return root Element that is returned by calling the passed url
     * @throws Exception
     */
    public static Element getJdomRootElement(String url) throws IOException, JDOMException
    {
        HttpUtil httpUtil = new HttpUtil(url);
        InputStream is;

        is = httpUtil.downloadFileOverApacheHttpClient();


        return getJdomRootElement(is);
    }

    public static Element getJdomRootElement(InputStream is) throws IOException, JDOMException
    {
        Element rootElement = null;
        Document doc;

        doc = new SAXBuilder().build(is);
        rootElement = doc.getRootElement();

        return rootElement;
    }


}
