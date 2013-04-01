package simulation;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

public class User {

	private int userID;
	private String name;
	private String surname;

	private int[] categoryValues;

	public enum Category {
		CLOTHES, TECHNOLOGY, FOOD, SHOES, JEWELLERY
	}

	public enum Shops {
		BANANA_REPUBLIC, GAP, TOMMY, APPLE, SONY, BEST_BUY, TERIYAKI, BURGER_KING, SHWARMA, CAMPERS, NIKE, ADIDAS, PEOPLES, CHARM, PARIS
	}

	User() {
		readValuesFromXML();
	}

	private void readValuesFromXML() {
		categoryValues = new int[Category.values().length];

		try {

			File fXmlFile = new File("userDB.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();

			NodeList nodeLst = doc.getElementsByTagName("category");
			Node curNode = nodeLst.item(0);
			Element categoryElem = (Element) curNode;
			NodeList subCat = categoryElem.getElementsByTagName("CLOTHES");
			Element clothesCat = (Element) subCat.item(0);
			NodeList clothesCount = clothesCat.getChildNodes();

			categoryValues[0] = Integer.parseInt(clothesCount.item(0)
					.getNodeValue());

			subCat = categoryElem.getElementsByTagName("TECHNOLOGY");
			clothesCat = (Element) subCat.item(0);
			clothesCount = clothesCat.getChildNodes();

			categoryValues[1] = Integer.parseInt(clothesCount.item(0)
					.getNodeValue());

			subCat = categoryElem.getElementsByTagName("FOOD");
			clothesCat = (Element) subCat.item(0);
			clothesCount = clothesCat.getChildNodes();

			categoryValues[2] = Integer.parseInt(clothesCount.item(0)
					.getNodeValue());

			subCat = categoryElem.getElementsByTagName("SHOES");
			clothesCat = (Element) subCat.item(0);
			clothesCount = clothesCat.getChildNodes();

			categoryValues[3] = Integer.parseInt(clothesCount.item(0)
					.getNodeValue());

			subCat = categoryElem.getElementsByTagName("JEWELLERY");
			clothesCat = (Element) subCat.item(0);
			clothesCount = clothesCat.getChildNodes();

			categoryValues[4] = Integer.parseInt(clothesCount.item(0)
					.getNodeValue());

			nodeLst = doc.getElementsByTagName("userInfo");
			curNode = nodeLst.item(0);
			categoryElem = (Element) curNode;
			subCat = categoryElem.getElementsByTagName("id");
			clothesCat = (Element) subCat.item(0);
			clothesCount = clothesCat.getChildNodes();

			userID = Integer.parseInt(clothesCount.item(0).getNodeValue());

			subCat = categoryElem.getElementsByTagName("name");
			clothesCat = (Element) subCat.item(0);
			clothesCount = clothesCat.getChildNodes();

			name = clothesCount.item(0).getNodeValue();

			subCat = categoryElem.getElementsByTagName("surname");
			clothesCat = (Element) subCat.item(0);
			clothesCount = clothesCat.getChildNodes();

			surname = clothesCount.item(0).getNodeValue();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
