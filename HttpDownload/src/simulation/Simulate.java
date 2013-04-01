package simulation;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

public class Simulate {
	private static int userID;
	private static String name;
	private static String surname;

	private static int[] categoryValues;

	public static enum Category {
		CLOTHES, TECHNOLOGY, FOOD, SHOES, JEWELLERY
	}

	public enum Shops {
		BANANA_REPUBLIC, GAP, TOMMY, APPLE, SONY, BEST_BUY, TERIYAKI, BURGER_KING, SHWARMA, CAMPERS, NIKE, ADIDAS, PEOPLES, CHARM, PARIS
	}

	public Simulate() {

	}

	public int simulation() {
		Network network = new Network();
		int simTime = Integer.MAX_VALUE;
		int[] catValues = new int[5];
		int cat = 0;
		for (int i = 0; i < 5; i++) {
			catValues[i] = 0;
		}
		for (int i = 0; i < simTime; i++) {
			Integer cur = network.getStrongestWireless();

			if (cur == -1) {
				// System.out.println("No Connection !!!");
			} else if (cur == -2) {
				// System.out.println("FINISHED!!!");
				break;
			} else {
				cat = (int) Math.floor(cur / 3.0);
				// System.out.println("Current: " + Shops.values()[cur].name() +
				// " Type: " + Category.values()[cat].name() );
				catValues[cat]++;

				if (catValues[cat] == 3) {
					break;

				}
			}
			/*
			 * try { Thread.sleep(1000); } catch (InterruptedException e) { //
			 * TODO Auto-generated catch block e.printStackTrace(); }
			 */

		}
		return cat;
	}

	private static void readValuesFromXML() {
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