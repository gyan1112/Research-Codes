import java.io.IOException;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class Driver 
{
	public static void main(String[] args) throws SAXException, IOException
	{
		XMLReader p = XMLReaderFactory.createXMLReader();
		p.setContentHandler(new XMLHandler()); //XMLHandler calls Parser for symbol table based encryption
		p.parse("api1.xml");
		XMLReader p1 = XMLReaderFactory.createXMLReader();
		p1.setContentHandler(new XMLHandler1()); //XMLHandler1 calls parser for tag table based encryption
		p1.parse("api1.xml");
	}
}
