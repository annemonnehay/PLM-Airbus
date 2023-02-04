import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
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
	// to format console output, to differentiate the menus the user navigates
	public static final String BLACK = "\033[0;30m";
	public static final String PURPLE = "\033[0;35m";
	public static final String CYAN = "\033[0;36m";
	public static HashMap<Integer, ArrayList<String>> searchResults = new HashMap<>();

	public static void main(String[] args) {
		// Aircrafts info
		HashMap<Integer, ArrayList<String>> aircraftsData = new HashMap<>();
		aircraftsData.put(1, new ArrayList<>(Arrays.asList("A350", "Passenger", "FEASIBILITY")));
		aircraftsData.put(2, new ArrayList<>(Arrays.asList("A330", "Passenger", "IN_SERVICE")));
		aircraftsData.put(3, new ArrayList<>(Arrays.asList("A321XLR", "Passenger", "CONCEPT")));
		aircraftsData.put(4, new ArrayList<>(Arrays.asList("A300", "Passenger", "STOPPED")));
		aircraftsData.put(5, new ArrayList<>(Arrays.asList("A400M", "Military", "STOPPED")));
		aircraftsData.put(6, new ArrayList<>(Arrays.asList("A319CJ", "Business", "IN_SERVICE")));
		aircraftsData.put(7, new ArrayList<>(Arrays.asList("A380", "Freight", "IN_SERVICE")));

		// Parts info
		HashMap<Integer, ArrayList<String>> partsData = new HashMap<>();
		partsData.put(1, new ArrayList<>(Arrays.asList("rudder", "engine", "unknown")));
		partsData.put(2, new ArrayList<>(Arrays.asList("propeller", "engine", "unknown")));
		partsData.put(3, new ArrayList<>(Arrays.asList("instrument panel", "cockpit", "unknown")));

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
				displayAllById1(aircraftsData);
				// 4 more different ways to do the same thing
//				displayAllById2(aircraftsData);
//				displayAllById3(aircraftsData);
//				displayAllById4(aircraftsData);
//				displayAllById5(aircraftsData);
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
//				displayEditMenu(aircraftsData, partsData, scan);
//				displayMainMenu();
//				menuSelection = scan.nextInt();
//				break;
			case 4: // Not finished
//				displayDetailedInfo(aircraftsData);
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
	 * Displays all aircrafts by id, program, type and phase. METHOD 1: Iterates
	 * HashMap using forEach loop and lambda
	 * 
	 * @param hmap the aircrafts in the PLM (key: aircraft id, value: aircraft info
	 *             (Program, Type, Phase)
	 */
	public static void displayAllById1(HashMap<Integer, ArrayList<String>> hmap) {
		System.out.println(CYAN + "**Mode affichage**\n");
		System.out.println("ID" + "\t| " + "PROGRAM" + " | " + "TYPE" + " | " + "PHASE");
		System.out.println("---------------------------------------");
		// 1. Iterate through a HashMap using forEach + lambda
		hmap.forEach((id, aircraftInfo) -> System.out.println(id + "\t" + aircraftInfo));
		System.out.println("---------------------------------------");

	}

// ******************** Different ways to do the same thing (display data structure)******************
	/**
	 * Displays all aircrafts by id, program, type and phase. METHOD 2: Iterates
	 * through HashMap KeySet using Iterator
	 * 
	 * @param hmap the aircrafts in the PLM app
	 */
	public static void displayAllById2(HashMap<Integer, ArrayList<String>> hmap) {
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
	 * Displays all aircrafts by id, program, type and phase. METHOD 3: Iterates
	 * through HashMap EntrySet using Iterator
	 * 
	 * @param hmap the aircrafts in the PLM app
	 */
	public static void displayAllById3(HashMap<Integer, ArrayList<String>> hmap) {
		System.out.println(CYAN + "**Mode affichage**\n");
		System.out.println("ID" + "\t| " + "PROGRAM" + " | " + "TYPE" + " | " + "PHASE");
		System.out.println("---------------------------------------");
		// 2. Iterate through HashMap EntrySet using Iterator<Entry<...hmap...>>
		Iterator<Entry<Integer, ArrayList<String>>> it = hmap.entrySet().iterator();
		while (it.hasNext()) {
			Entry<Integer, ArrayList<String>> entry = it.next();
			System.out.print(entry.getKey() + "\t");
			System.out.println(entry.getValue());
		}
		System.out.println("---------------------------------------");

	}

	/**
	 * Displays all aircrafts by id, program, type and phase. METHOD 4: Iterates
	 * through HashMap using for-each loop and Map.Entry, .entrySet(), .getKey() and
	 * .getValue()
	 * 
	 * @param hmap the aircrafts in the PLM app
	 */
	public static void displayAllById4(HashMap<Integer, ArrayList<String>> hmap) {
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
	 * Displays all aircrafts by id, program, type and phase. METHOD 5: Iterates
	 * through HashMap using for-each loop and Collection, Entry, .entrySet(),
	 * .getKey() and .getValue()
	 * 
	 * @param hmap the aircrafts in the PLM app
	 */
	public static void displayAllById5(HashMap<Integer, ArrayList<String>> hmap) {
		System.out.println(CYAN + "**Mode affichage**\n");
		System.out.println("ID" + "\t| " + "PROGRAM" + " | " + "TYPE" + " | " + "PHASE");
		System.out.println("---------------------------------------");
		// 5. Iterate HashMap using for-each loop + Collection<Entry<...hmap...>>
		Collection<Entry<Integer, ArrayList<String>>> entrySet1 = hmap.entrySet();
		for (Entry<Integer, ArrayList<String>> entry : entrySet1) {
			System.out.print(entry.getKey() + "\t");
			System.out.println(entry.getValue());
		}
		System.out.println("---------------------------------------");
	}
// **********************************************************************************

	/**
	 * Displays search menu and allows user to search the PLM for a keyword in
	 * aircraft name field. Allows user to search for keywords until exits search
	 * menu.
	 * 
	 * @param aircraftsData the aircrafts in the PLM app (HashMap)
	 * @param userInput     user input (Scanner)
	 */
	public static void displaySearchMenu(HashMap<Integer, ArrayList<String>> aircraftsData, Scanner userInput) {
		System.out.println(PURPLE + "**Menu de recherche**\n");
		System.out.println("Saisissez votre mot clé:");
		Boolean isSearching = true;
		// ask user for keyword
		while (isSearching) {
			String keyword = userInput.next();
			// search data for keyword
			searchAircraftData(aircraftsData, keyword); // returns searchResult
			if (searchResults.isEmpty()) {
				System.out.println("Aucun avion ne contient '" + keyword + "' dans son nom.");
			} else {
				displaySearchResult(searchResults);
			}

			// continue searching or quit
			System.out.println("Une autre recherche? (O/N):");
			String continueSearch = userInput.next();
			if (continueSearch.equalsIgnoreCase("o"))
//				searchResult = null;
				System.out.println("Saisissez votre mot clé:");
			if (continueSearch.equalsIgnoreCase("n"))
				isSearching = false;
//			else System.out.println("Saisissez O/N:"); // (bug here - don't know how to fix)
		}
	}

	/**
	 * allows user to search the PLM (HashMap) for a keyword (String) in aircraft
	 * name field (ArrayList index)
	 * 
	 * @param aircraftsData the aircrafts in the PLM app (HashMap)
	 * @param keyword       user input (Scanner)
	 * @return searchResults the aircraft(s) which name(s) contain(s) keyword / part
	 *         of the keyword
	 * 
	 */
	public static HashMap<Integer, ArrayList<String>> searchAircraftData(
			HashMap<Integer, ArrayList<String>> aircraftsData, String keyword) {
		System.out.println();
		System.out.println("Résultat(s) de la recherche:");

		searchResults = new HashMap<>();
		for (Map.Entry<Integer, ArrayList<String>> entry : aircraftsData.entrySet()) {
			int id = entry.getKey();
			ArrayList<String> aircraftInfo = entry.getValue();
			if (aircraftInfo.get(0).contains(keyword)) {
				// populate searchResults HashMap with key and value of Map.Entry containing
				// keyword
				searchResults.put(id, aircraftInfo);
			}
		}
		return searchResults;
	}

	/**
	 * Displays search result (HashMap)
	 * 
	 * @param searchResults the aircraft(s) which name(s) contain(s) keyword / part
	 *                      of the keyword
	 */
	public static void displaySearchResult(HashMap<Integer, ArrayList<String>> searchResults) {
//		System.out.println("ID" + "\t| " + "PROGRAM" + " | " + "TYPE" + " | " + "PHASE");
		System.out.println("---------------------------------------");
		searchResults.forEach((id, aircraftInfo) -> System.out.println(id + "\t" + aircraftInfo));
		System.out.println("---------------------------------------");

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
