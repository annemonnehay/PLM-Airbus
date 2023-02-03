
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

/**
 * This class manages the product life cycle of Airbus aircrafts
 * @author AnneM
 * 
 */
public class PlmAirbusApp {
	// for coloured console output, to differentiate the menus the user navigates
	public static final String BLACK = "\033[0;30m";
	public static final String PURPLE = "\033[0;35m";
	public static final String CYAN = "\033[0;36m";
	static ArrayList<String> searchResult = null;

	public static void main(String[] args) {
		// Products info
		HashMap<Integer, ArrayList<String>> products = new HashMap<>();
		products.put(001, new ArrayList<>(Arrays.asList("A350", "Passenger", "FEASIBILITY")));
		products.put(002, new ArrayList<>(Arrays.asList("A330", "Passenger", "IN_SERVICE")));
		products.put(003, new ArrayList<>(Arrays.asList("A321XLR", "Passenger", "CONCEPT")));
		products.put(004, new ArrayList<>(Arrays.asList("A300", "Passenger", "STOPPED")));
		products.put(005, new ArrayList<>(Arrays.asList("A400M", "Military", "STOPPED")));
		products.put(006, new ArrayList<>(Arrays.asList("A319CJ", "Business", "IN_SERVICE")));
		products.put(007, new ArrayList<>(Arrays.asList("A380", "Freight", "IN_SERVICE")));

		// Parts info
		HashMap<Integer, ArrayList<String>> parts = new HashMap<>();
		parts.put(001, new ArrayList<>(Arrays.asList("rudder", "engine", "unknown")));
		parts.put(002, new ArrayList<>(Arrays.asList("propeller", "engine", "unknown")));
		parts.put(003, new ArrayList<>(Arrays.asList("instrument panel", "cockpit", "unknown")));

		Scanner scan = new Scanner(System.in);

		// Ask user to choose from a menu
		System.out.println("Bienvenue dans l'application de gestion du cycle de vie d'avions AIRBUS.");
		System.out.println();

		// Menu options
		displayMainMenu();

		while (scan.hasNextInt() == false)
			scan.next();
		int menuSelection = scan.nextInt();
		while (menuSelection != 5) {
			switch (menuSelection) {
			case 1:
				displayAllByID(products);
				System.out.println(BLACK);
//				displayMainMenu();  // Optional
				menuSelection = scan.nextInt();
				break;
			case 2:
				displaySearchMenu(products, scan);
				displayMainMenu();
				menuSelection = scan.nextInt();
				break;
			case 3: // Not finished
//				displayEditMenu(products, scan);
//				displayMainMenu();
//				menuSelection = scan.nextInt();
//				break;
			case 4: // Not finished
//				displayDetailedInfo();
//				displayMainMenu();
//				menuSelection = scan.nextInt();
//				break;
			default:
				displayMainMenu();
				menuSelection = scan.nextInt();
			}
		} 
		if (menuSelection == 5) {
			System.out.println("Au revoir");
			System.exit(0);
			scan.close();
		}
	}

	/**
	 * Displays main menu options
	 */
	public static void displayMainMenu() {
		System.out.println(BLACK + "**Menu principal**\n");
		System.out.println("Faites votre choix dans le menu, saisissez le chiffre correspondant:");
		System.out.println("[1] Afficher tous les avions");
		System.out.println("[2] Afficher tous les avions contenant un mot clé dans le programme");
		System.out.println("[3] Ajouter ou supprimer une pièce pour un avion donné (Work in progress)");
		System.out.println("[4] Afficher un avion avec les infos détaillées de chaque pièce (Work in progress)");
		System.out.println("[5] Quitter l'application");
	}

	/**
	 * Displays all aircrafts
	 * METHOD 1: Iterates HashMap using for-each loop
	 * @param hmap the aircrafts in the data structure
	 */
	public static void displayAllByID(HashMap<Integer, ArrayList<String>> hmap) {
		System.out.println(CYAN + "**Mode affichage**\n");
		System.out.println("ID" + "\t| " + "PROGRAM" + " | " + "TYPE" + " | " + "PHASE");
		System.out.println("---------------------------------------");
		// -----> Iterate HashMap using for-each loop
		for (Map.Entry<Integer, ArrayList<String>> entry : hmap.entrySet()) {
			System.out.print(entry.getKey() + "\t");
			System.out.println(entry.getValue());
		}
		System.out.println("---------------------------------------");

	}

// ******************** Different ways to do the same thing (display data structure)******************

	/**
	 * Displays all aircrafts
	 * METHOD 2: Iterates through HashMap EntrySet using Iterator
	 * @param hmap the aircrafts in the data structure
	 */
	public static void displayAllByID2(HashMap<Integer, ArrayList<String>> hmap) {
		System.out.println(CYAN + "**Mode affichage**\n");
		System.out.println("ID" + "\t| " + "PROGRAM" + " | " + "TYPE" + " | " + "PHASE");
		System.out.println("---------------------------------------");

		// -----> Iterate through HashMap EntrySet using Iterator
		Iterator<Entry<Integer, ArrayList<String>>> iterator = hmap.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<Integer, ArrayList<String>> product = iterator.next();
			System.out.print(product.getKey() + "\t");
			System.out.println(product.getValue());
		}
		System.out.println("---------------------------------------");
	}

	/**
	 * Displays all aircrafts 
	 * METHOD 3: Iterates through HashMap KeySet using Iterator
	 * @param hmap the aircrafts in the data structure
	 */

	public static void displayAllByID3(HashMap<Integer, ArrayList<String>> hmap) {
		System.out.println(CYAN + "**Mode affichage**\n");
		System.out.println("ID" + "\t| " + "PROGRAM" + " | " + "TYPE" + " | " + "PHASE");
		System.out.println("---------------------------------------");

		// -----> Iterate through HashMap KeySet using Iterator
		Iterator<Integer> iterator = hmap.keySet().iterator();
		while (iterator.hasNext()) {
			Integer key = iterator.next();
			System.out.print(key + "\t");
			System.out.println(hmap.get(key));
		}
		System.out.println("---------------------------------------");
	}

	/**
	 * Displays search menu
	 * 
	 * @param productsData the aircrafts in the data structure (ArrayList)
	 * @param scan         user input
	 */
	public static void displaySearchMenu(HashMap<Integer, ArrayList<String>> productsData, Scanner scan) {
		System.out.println(PURPLE + "**Menu recherche**\nSaisissez votre mot clé:");
		Boolean isSearching = true;
		// ask user for keyword
		while (isSearching) {
			String keyword = scan.next();
			// search data for keyword
			searchProductsData(productsData, keyword);

			// Impossible to implement without introducing another bug
//			if(searchResult != null) {
//				System.out.println(searchResult);
//			}
//				else {
//				System.out.println("Pas trouvé");
//			}

			// continue searching or quit
			System.out.println("Une autre recherche? (O/N):");
			String continueSearch = scan.next();
			if (continueSearch.equalsIgnoreCase("o"))
//				searchResult = null;
				System.out.println("Saisissez votre mot clé:");
			if (continueSearch.equalsIgnoreCase("n"))
				isSearching = false;
//			else System.out.println("Saisissez O/N:"); // (bug here - don't know how to fix)
		}
	}

	/**
	 * Searches aircrafts data structure (HashMap) for keyword
	 * 
	 * @param hmap the aircrafts in the data structure
	 * @param keyword user input
	 */
	public static void searchProductsData(HashMap<Integer, ArrayList<String>> hmap, String keyword) {
		System.out.println("Search results:");
		for (Map.Entry<Integer, ArrayList<String>> entry : hmap.entrySet()) {
			ArrayList<String> productsInfo = entry.getValue();
			for (int i = 0; i < productsInfo.size(); i++) {
				if (productsInfo.get(i).contains(keyword))
					System.out.println(productsInfo);
//					searchResult = productsInfo;
			}
		}
		// don't know how to use this return value so switched to sysout + void return type
//		return searchResult; 
	}

// *******************WORK IN PROGRESS**********************

	// enter edit menu to add or remove parts to a specific plane
//	public static ArrayList<String> displayEditMenu(ArrayList<String> productInfo, ArrayList<String> part) {
//		System.out.println("**Edit mode**\n A quel avion souhaitez-vous ajouter/supprimer une pièce?:");
//		searchProductsData(parts, keyword);
//		System.out.println("Quelle pièce voulez-vous ajouter/supprimer?:");
//		displayAllItemsByID(parts);
//		System.out.println("Faites votre choix dans le menu, saisissez le chiffre correspondant:");
//		System.out.println("1: Ajouter une pièce");
//		System.out.println("2: Supprimer une pièce");
//		int editOption = scan.nextInt();
//		if (editOption == 1) {
//		// addPart();
//		}
//		if (editOption == 2) {
//		// removePart();
//		}
//		return editResult;
//	}
}
