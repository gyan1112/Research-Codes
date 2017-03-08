import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;


public class XMLHandler1 extends DefaultHandler 
{
	int flag=0;
	public void startDocument()
	{
	}
	public void endDocument()
	{
		Parser p= new Parser();
		p.enc("End", 6);
	}
	public void startElement(String nameSpaceURI, String localName, String qName, Attributes atts)
	{
		Parser p = new Parser();
		p.enc(qName, 7); //Send name of Start Tag
		for (int i=0; i<atts.getLength(); i++)
      		{
         		qName = atts.getQName(i);
			p.enc(qName, 10); //Send name of attribute name
			String value = atts.getValue(qName);
			p.enc(value, 11); //Send value of attribute
		}
		flag=1;
	}
	public void endElement(String nameSpaceURI, String localName, String qName)
	{
		Parser p = new Parser();
		p.enc(qName, 8); //Send name of end Tag
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
				p.enc(s, 9); //Send content between tag
			}
		}
		flag=0;
	}
}
