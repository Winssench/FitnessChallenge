/**
 * Copyright © 2021  	Hethsron Jedaël BOUEYA
 * 						Omar CHICHAOUI
 * 					    Pranamika SOLANKI
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package fr.ensisa.res;
/**
 *		@file            	Parser.java
 *      @details
 *
 *      @author          	Hethsron Jedaël BOUEYA (hethsron-jedael.boueya@uha.fr)
 *      					Omar CHICHAOUI (omar.chichaoui@uha.fr)
 *      				    Pranamika SOLANKI (pranamika.solanki@uha.fr)
 *
 *      @version         	0.0.1
 *      @date            	January, 25th 2021
 *
 *      @Copyright       	GPLv3+ : GNU GPL version 3 or later
 *                       	Licencied Material - Property of Us®
 *                       	© 2020 ENSISA (UHA) - All rights reserved.
 */
import org.w3c.dom.Document;
import javax.ws.rs.core.StreamingOutput;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class Parser {

    public static StreamingOutput XML(Document doc){
        return output -> {
            try {
                // Creates new instance of transformer
                Transformer transformer = TransformerFactory.newInstance().newTransformer();

                // Set Output property of transformer
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");

                // Build a stream result
                StreamResult result = new StreamResult(output);

                // Acts document model as a holder of transformation
                DOMSource source = new DOMSource(doc);

                // Transform the XML source to the result
                transformer.transform(source, result);

                // Flush this output stream
                output.flush();
            }
            catch (TransformerException e) {
                System.err.println(e.getMessage());
            }
        };
    }

}
