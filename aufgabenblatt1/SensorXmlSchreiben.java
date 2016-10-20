package aufgabenblatt1;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class SensorXmlSchreiben {
	public static void sensorXmlSchreiben(Sensor sensor, File file) throws ParserConfigurationException, TransformerException {
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement("Sensor");
		rootElement.setAttribute("id", sensor.getID());
		doc.appendChild(rootElement);
		for (Messung element : sensor.getMessungen()) {
			Element childElement = doc.createElement("Messung");
			childElement.setAttribute("Wert", new Double(element.getWert()).toString());
			childElement.setAttribute("Zeitstempel", element.getZeit().toString());
			rootElement.appendChild(childElement);
		}
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(file.getAbsolutePath()));
		transformer.transform(source, result);
	}
}
