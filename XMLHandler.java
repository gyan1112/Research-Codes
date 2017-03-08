import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.SAXException;
import java.io.IOException;


public class XMLHandler extends DefaultHandler 
{
	static int noOfTags=0;
	static int noOfCharInTags=0;
	static int noOfAttrs=0;
	static int noOfCharInAttrs=0;
	static int noOfAttrVal=0;
	static int noOfCharInAttrVal=0;
	static int noOfVariable=0;
	static int noOfCharInVariable=0;
	int flag=0;
	public void startDocument()
	{
	}
	public void endDocument()
	{
		Parser p= new Parser();
		p.enc("End", 0);
	}
	public void startElement(String nameSpaceURI, String localName, String qName, Attributes atts)
	{
		Parser p = new Parser();
		p.enc(qName, 1); //Send name of Start Tag
		for (int i=0; i<atts.getLength(); i++)
      		{
         		qName = atts.getQName(i);
			p.enc(qName, 4); //Send name of attribute name
			String value = atts.getValue(qName);
			p.enc(value, 5); //Send value of attribute
		}
		flag=1;
		
	}
	public void endElement(String nameSpaceURI, String localName, String qName)
	{
		Parser p = new Parser();
		p.enc(qName, 2); //Send name of end Tag
		flag=0;
	}
	public void characters(char[] ch, int start, int length)
	{
		if(flag==1)
		{
			int ok=0;
			for(int i=start; i<(start+length); i++)
			{
				if(!(0<=ch[i]&&ch[i]<=32))
				{
					ok=1;
					break;
				}
			}
			
			if(ok==1)
			{
				String s = "";
				for(int i=start; i<(start+length); i++)
				{
					s=s+ch[i];
				}
				Parser p = new Parser();
				p.enc(s, 3); //Send content between tag
			}
		}
		flag=0;
	}
}
