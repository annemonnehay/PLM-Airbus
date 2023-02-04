import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

/**
 * This class manages the product life cycle of Airbus aircrafts
 * 
 * @author AnneM
 * 
 */
public class PlmAirbusApp {
	// for coloured console output, to differentiate the menus the user navigates
	public static final String BLACK = "\033[0;30m";
	public static final String PURPLE = "\033[0;35m";
	public static final String CYAN = "\033[0;36m";
	static Entry<Integer, ArrayList<String>> searchResults = null;

	public static void main(String[] args) {
		// Aircrafts info
		HashMap<Integer, ArrayList<String>> aircraftsData = new HashMap<>();
		aircraftsData.put(001, new ArrayList<>(Arrays.asList("A350", "Passenger", "FEASIBILITY")));
		aircraftsData.put(002, new ArrayList<>(Arrays.asList("A330", "Passenger", "IN_SERVICE")));
		aircraftsData.put(003, new ArrayList<>(Arrays.asList("A321XLR", "Passenger", "CONCEPT")));
		aircraftsData.put(004, new ArrayList<>(Arrays.asList("A300", "Passenger", "STOPPED")));
		aircraftsData.put(005, new ArrayList<>(Arrays.asList("A400M", "Military", "STOPPED")));
		aircraftsData.put(006, new ArrayList<>(Arrays.asList("A319CJ", "Business", "IN_SERVICE")));
		aircraftsData.put(007, new ArrayList<>(Arrays.asList("A380", "Freight", "IN_SERVICE")));

		// Parts info
		HashMap<Integer, ArrayList<String>> partsData = new HashMap<>();
		partsData.put(001, new ArrayList<>(Arrays.asList("rudder", "engine", "unknown")));
		partsData.put(002, new ArrayList<>(Arrays.asList("propeller", "engine", "unknown")));
		partsData.put(003, new ArrayList<>(Arrays.asList("instrument panel", "cockpit", "unknown")));

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
				displayAllByID1(aircraftsData);
				// 4 more different ways to do the same thing
//				displayAllByID2(aircrafts);
//				displayAllByID3(aircrafts);
//				displayAllByID4(aircrafts);
//				displayAllByID5(aircrafts);
				System.out.println(BLACK);
//				displayMainMenu();  // Optional
				menuSelection = scan.nextInt();
				break;
			case 2:
				displaySearchMenu(aircraftsData, scan);
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
	 * Displays searchResults
	 * 
	 * @param hmap the aircrafts in the data structure (key: aircraft ID, value:
	 *             aircraft info (Program, Type, Phase)
	 */
	public static void displaySearchResults(HashMap<Integer, ArrayList<String>> hmap) {
		System.out.println(CYAN + "**Mode affichage**\n");
		System.out.println("ID" + "\t| " + "PROGRAM" + " | " + "TYPE" + " | " + "PHASE");
		System.out.println("---------------------------------------");
		// 1. Iterate through a HashMap using forEach + lambda
		hmap.forEach((k, v) -> System.out.println(k + "\t" + v));
		System.out.println("---------------------------------------");

	}
	

	/**
	 * Displays all aircrafts METHOD 1: Iterates HashMap using forEach loop and
	 * lambda
	 * 
	 * @param hmap the aircrafts in the data structure (key: aircraft ID, value:
	 *             aircraft info (Program, Type, Phase)
	 */
	public static void displayAllByID1(HashMap<Integer, ArrayList<String>> hmap) {
		System.out.println(CYAN + "**Mode affichage**\n");
		System.out.println("ID" + "\t| " + "PROGRAM" + " | " + "TYPE" + " | " + "PHASE");
		System.out.println("---------------------------------------");
		// 1. Iterate through a HashMap using forEach + lambda
		hmap.forEach((k, v) -> System.out.println(k + "\t" + v));
		System.out.println("---------------------------------------");

	}

// ******************** Different ways to do the same thing (display data structure)******************
	/**
	 * Displays all aircrafts METHOD 2: Iterates through HashMap KeySet using
	 * Iterator
	 * 
	 * @param hmap the aircrafts in the data structure
	 */
	public static void displayAllByID2(HashMap<Integer, ArrayList<String>> hmap) {
		System.out.println(CYAN + "**Mode affichage**\n");
		System.out.println("ID" + "\t| " + "PROGRAM" + " | " + "TYPE" + " | " + "PHASE");
		System.out.println("---------------------------------------");
		// 3. Iterate through HashMap KeySet using Iterator
		Iterator<Integer> iterator = hmap.keySet().iterator();
		while (iterator.hasNext()) {
			Integer key = iterator.next();
			System.out.print(key + "\t");
			System.out.println(hmap.get(key));
		}
		System.out.println("---------------------------------------");
	}

	/**
	 * Displays all aircrafts METHOD 3: Iterates through HashMap EntrySet using
	 * Iterator
	 * 
	 * @param hmap the aircrafts in the data structure
	 */
	public static void displayAllByID3(HashMap<Integer, ArrayList<String>> hmap) {
		System.out.println(CYAN + "**Mode affichage**\n");
		System.out.println("ID" + "\t| " + "PROGRAM" + " | " + "TYPE" + " | " + "PHASE");
		System.out.println("---------------------------------------");
		// 2. Iterate through HashMap EntrySet using Iterator<Entry<...hmap...>>
		Iterator<Entry<Integer, ArrayList<String>>> it = hmap.entrySet().iterator();
		while (it.hasNext()) {
			Entry<Integer, ArrayList<String>> product = it.next();
			System.out.print(product.getKey() + "\t");
			System.out.println(product.getValue());
		}
		System.out.println("---------------------------------------");

	}

	/**
	 * Displays all aircrafts METHOD 4: Iterates through HashMap using for-each loop
	 * and Map.Entry, .entrySet(), .getKey() and .getValue()
	 * 
	 * @param hmap the aircrafts in the data structure
	 */
	public static void displayAllByID4(HashMap<Integer, ArrayList<String>> hmap) {
		System.out.println(CYAN + "**Mode affichage**\n");
		System.out.println("ID" + "\t| " + "PROGRAM" + " | " + "TYPE" + " | " + "PHASE");
		System.out.println("---------------------------------------");
		// 4. Iterate HashMap using for-each loop + Map.Entry<...hmap...>
		for (Map.Entry<Integer, ArrayList<String>> entry : hmap.entrySet()) {
			System.out.print(entry.getKey() + "\t");
			System.out.println(entry.getValue());
		}
		System.out.println("---------------------------------------");
	}

	/**
	 * Displays all aircrafts METHOD 5: Iterates through HashMap using for-each loop
	 * and Collection, Entry, .entrySet(), .getKey() and .getValue()
	 * 
	 * @param hmap the aircrafts in the data structure
	 */
	public static void displayAllByID5(HashMap<Integer, ArrayList<String>> hmap) {
		System.out.println(CYAN + "**Mode affichage**\n");
		System.out.println("ID" + "\t| " + "PROGRAM" + " | " + "TYPE" + " | " + "PHASE");
		System.out.println("---------------------------------------");
		// 5. Iterate HashMap using for-each loop + Collection<Entry<...hmap...>>
		Collection<Entry<Integer, ArrayList<String>>> entrySet1 = hmap.entrySet();
		for (Entry<Integer, ArrayList<String>> item : entrySet1) {
			System.out.print(item.getKey() + "\t");
			System.out.println(item.getValue());
		}
		System.out.println("---------------------------------------");
	}
// **********************************************************************************

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
			searchAircraftData(productsData, keyword);

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
	 * Searches aircrafts data structure (HashMap) for keyword (String)
	 * 
	 * @param hmap    the aircrafts in the data structure
	 * @param keyword user input
	 */
	public static void searchAircraftData(HashMap<Integer, ArrayList<String>> hmap, String keyword) {
		System.out.println("Search results:");
		for (Map.Entry<Integer, ArrayList<String>> entry : hmap.entrySet()) {
			ArrayList<String> aircraftInfo = entry.getValue();
			for (int i = 0; i < aircraftInfo.size(); i++) {
				if (aircraftInfo.get(i).contains(keyword))
					System.out.println(aircraftInfo);
//					searchResults = entry;
//					System.out.println(searchResults);
			}
		}
	
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
