import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;


/**
 * This class manages the product life cycle of Airbus aircrafts
 * @author MonnehayA
 *
 */
public class PlmAirbusApp {
	// to format console output, to differentiate the menus the user navigates
	public static final String BLACK = "\033[0;30m";
	public static final String RED = "\033[0;31m";
	public static final String GREEN = "\033[0;32m";
	public static final String BLUE = "\033[0;34m";
	public static final String PURPLE = "\033[0;35m";
	public static final String CYAN = "\033[0;36m";

	public static HashMap<Integer, ArrayList<String>> aircraftsData = new HashMap<>();
	public static HashMap<Integer, ArrayList<String>> partsData = new HashMap<>();
	public static HashMap<Integer, ArrayList<String>> searchResults = new HashMap<>();
	public static HashMap<Integer, ArrayList<String>> udpatedAircraftsData = new HashMap<>();
	static boolean isEditing = false;
	static boolean isAdding = false;
	static boolean isRemoving = false;

	public static void main(String[] args) {
		// Aircrafts data
		aircraftsData.put(1, new ArrayList<>(Arrays.asList("A350", "Passenger", "FEASIBILITY")));
		aircraftsData.put(2, new ArrayList<>(Arrays.asList("A330", "Passenger", "IN_SERVICE")));
		aircraftsData.put(3, new ArrayList<>(Arrays.asList("A321XLR", "Passenger", "CONCEPT")));
		aircraftsData.put(4, new ArrayList<>(Arrays.asList("A300", "Passenger", "STOPPED")));
		aircraftsData.put(5, new ArrayList<>(Arrays.asList("A400M", "Military", "STOPPED")));
		aircraftsData.put(6, new ArrayList<>(Arrays.asList("A319CJ", "Business", "IN_SERVICE")));
		aircraftsData.put(7, new ArrayList<>(Arrays.asList("A380", "Freight", "IN_SERVICE")));

		// Parts data
		partsData.put(1, new ArrayList<>(Arrays.asList("rudder", "engine", "56 275 EUR")));
		partsData.put(2, new ArrayList<>(Arrays.asList("propeller", "engine", "78 960 EUR")));
		partsData.put(3, new ArrayList<>(Arrays.asList("instrument_panel", "cockpit", "45 230 EUR")));

		Scanner scan = new Scanner(System.in);

		// Ask user to choose from a menu
		System.out.println("Bienvenue dans l'application de gestion du cycle de vie d'avions AIRBUS.");
		System.out.println();

		// Menu options
		displayMainMenu();

		while (scan.hasNextInt() == false)
			scan.next();
		int menuSelection = scan.nextInt();
		while (menuSelection != 6) {
			switch (menuSelection) {
			case 1:
				displayAllData(aircraftsData);
				// 4 more different ways to do the same thing
				menuSelection = scan.nextInt();
				break;
			case 2:
				displaySearchSubMenu(aircraftsData, scan);
				displayMainMenu();
				menuSelection = scan.nextInt();
				break;
			case 3: // Not finished, remove() WIP
				int id = 0;
				addPart(aircraftsData, partsData, scan, id);
				displayMainMenu();
				menuSelection = scan.nextInt();
				break;
			case 4:
				int id1 = 0;
				removePart(aircraftsData, partsData, scan, id1);
				displayMainMenu();
				menuSelection = scan.nextInt();
				break;
			case 5:
				int id2 = 0;
//				displayAllData(detailedAircraftsData(aircraftsData, partsData, scan));
			default:
				displayMainMenu();
				menuSelection = scan.nextInt();
			}
		} // end of while loop
		if (menuSelection == 6) {
			System.out.println("Au revoir");
			System.exit(0);
			scan.close();
		}
	}

	/**
	 * Displays main menu options.
	 */
	public static void displayMainMenu() {

		System.out.println(BLACK + "**Main menu**\n");
		System.out.println("Faites votre choix dans le menu, saisissez le chiffre correspondant:");
		System.out.println("[1] Afficher tous les avions");
		System.out.println("[2] Afficher tous les avions contenant un mot clé dans le programme");
		System.out.println("[3] Ajouter une pièce pour un avion donné");
		System.out.println(RED + "[4] Supprimer une pièce pour un avion donné (Not functional)");
		System.out.println(RED + "[5] Afficher un avion avec les infos détaillées de chaque pièce (Not functional)");
		System.out.println(BLACK + "[6] Quitter l'application");
	}

	/**
	 * Displays all data. METHOD 1: Iterates HashMap using forEach loop and lambda
	 * 
	 * @param hmap the aircrafts in the PLM (key: aircraft id, value: aircraft info
	 *             (Program, Type, Phase) or the parts in the PLM (key: part id,
	 *             value: part info (Name, Category, Price)
	 */
	public static void displayAllData(HashMap<Integer, ArrayList<String>> hmap) {
		System.out.println("-------------------------------------------------------------------");
		// 1. Iterate through a HashMap using forEach + lambda
		hmap.forEach((id, dataInfo) -> System.out.println(id + "\t" + dataInfo));
		System.out.println("-------------------------------------------------------------------");

	}

	// ****** Different ways to do the same thing (display hashmap)******
	/**
	 * Displays all data. METHOD 2: Iterates through HashMap KeySet using Iterator
	 * 
	 * @param hmap the aircrafts in the PLM app
	 */
	public static void displayAllById2(HashMap<Integer, ArrayList<String>> hmap) {
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
	 * Displays all data. METHOD 3: Iterates through HashMap EntrySet using Iterator
	 * 
	 * @param hmap the aircrafts in the PLM app
	 */
	public static void displayAllById3(HashMap<Integer, ArrayList<String>> hmap) {
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
	 * Displays all data. METHOD 4: Iterates through HashMap using for-each loop and
	 * Map.Entry, .entrySet(), .getKey() and .getValue()
	 * 
	 * @param hmap the aircrafts in the PLM app
	 */
	public static void displayAllById4(HashMap<Integer, ArrayList<String>> hmap) {
		System.out.println("---------------------------------------");
		// 4. Iterate HashMap using for-each loop + Map.Entry<...hmap...>
		for (Map.Entry<Integer, ArrayList<String>> entry : hmap.entrySet()) {
			System.out.print(entry.getKey() + "\t");
			System.out.println(entry.getValue());
		}
		System.out.println("---------------------------------------");
	}

	/**
	 * Displays all data. METHOD 5: Iterates through HashMap using for-each loop and
	 * Collection, Entry, .entrySet(), .getKey() and .getValue()
	 * 
	 * @param hmap the aircrafts in the PLM app
	 */
	public static void displayAllById5(HashMap<Integer, ArrayList<String>> hmap) {
		System.out.println("---------------------------------------");
		// 5. Iterate HashMap using for-each loop + Collection<Entry<...hmap...>>
		Collection<Entry<Integer, ArrayList<String>>> entrySet1 = hmap.entrySet();
		for (Entry<Integer, ArrayList<String>> entry : entrySet1) {
			System.out.print(entry.getKey() + "\t");
			System.out.println(entry.getValue());
		}
		System.out.println("---------------------------------------");
	}
	// *********************************************************************

	/**
	 * Displays search menu and allows user to search the PLM for a keyword in
	 * aircraft name field. Allows user to search for keywords until exits search
	 * menu.
	 * 
	 * @param aircraftsData the aircrafts in the PLM app (HashMap)
	 * @param scan          user input: keyword (Scanner)
	 */
	public static void displaySearchSubMenu(HashMap<Integer, ArrayList<String>> aircraftsData, Scanner scan) {
		System.out.println(PURPLE + "**Search menu**\n");
		Boolean isSearching = true;
		// ask user for keyword
		while (isSearching) {
			System.out.println("Saisissez votre mot clé:");
			String keyword = scan.next();
			// search data for keyword
			searchAircraftData(aircraftsData, keyword.toUpperCase()); // returns searchResult
			if (searchResults.isEmpty()) {
				System.out.println("Aucun avion ne contient '" + keyword + "' dans son nom.");
			} else {
				displayAllData(searchResults);
			}

			// continue searching or quit
			System.out.println("Une autre recherche? (O/N):");
			String continueSearch = scan.next();
			if (continueSearch.equalsIgnoreCase("n"))
				isSearching = false;
			if (continueSearch.equalsIgnoreCase("o"))
				isSearching = true;
//			else System.out.println("Saisissez O/N:"); // (bug here)
		}
	}

	/**
	 * Allows user to search the PLM for a keyword in aircraft name field
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
	 * Adds a part to an chosen aircraft by Id. Allows user to add parts until quits
	 * 
	 * @param aircraftsData PLM aircraft data
	 * @param partsData     PLM parts data
	 * @param scan          user input for aircraft id to modify, part id
	 * @param id            id of aircraft to add part(s) to
	 * @return aircraftsData : updated aircrafsData with part(s) added
	 */
	public static HashMap<Integer, ArrayList<String>> addPart(HashMap<Integer, ArrayList<String>> aircraftsData,
			HashMap<Integer, ArrayList<String>> partsData, Scanner scan, int id) {
		isAdding = true;
		while (isAdding) {
			// get plane to edit (for both methods)
			System.out.println(CYAN + "Quel avion souhaitez-vous modifier (Ajout de pièces) ? Saisissez le numéro Id:");
			int aircraftId = scan.nextInt();
			ArrayList<String> aircraftInfo = null;
			// System.out.println("plane id: " + aircraftId);
			// display plane corresponding to selected plane
			for (Map.Entry<Integer, ArrayList<String>> aircraft : aircraftsData.entrySet()) {
				if (aircraft.getKey() == aircraftId) {
					System.out.println("Avion à modifier: " + aircraft + "\n");
					aircraftInfo = aircraft.getValue();
				}

			}
			// display parts available to add
			System.out.println("Pièces disponibles:");
			displayAllData(partsData);
			// ask user which part to add
			System.out.println("Quelle pièce souhaitez-vous ajouter? Saisissez le numéro Id:");
			int partId = scan.nextInt();
			// System.out.println("part id: " + partId);
			for (Map.Entry<Integer, ArrayList<String>> part : partsData.entrySet()) {
				if (part.getKey() == partId) {
					System.out.println("Pièce à ajouter: " + part + "\n");
					ArrayList<String> partsToAdd = part.getValue();
					aircraftInfo.add(partsToAdd.get(0));
				}

			}
			aircraftsData.put(aircraftId, aircraftInfo);
			System.out.println(GREEN);
			displayDataById(aircraftsData, aircraftId);
			System.out.println("Modification enregistrée.");
			System.out.println(BLUE);

			// continue editing or back to main menu
			System.out.println("souhaitez-vous ajouter une autre pièce? (O/N):");
			// used for both add and remove methods -- refactor when it works
			String continueEdit = scan.next();
			if (continueEdit.equalsIgnoreCase("n")) {
				System.out.println("Retour au menu principal");
				isAdding = false;

			}
			if (continueEdit.equalsIgnoreCase("o")) {
				isAdding = true;

			}

		} // end of while loop to add item
		return aircraftsData;

	}

	/**
	 * Removes a part to an chosen aircraft by Id. Allows user to remove parts until quits. NOT
	 * WORKING
	 * 
	 * @param aircraftsData PLM aircraft data
	 * @param partsData     PLM parts data
	 * @param scan          user input for aircraft id to modify, part name
	 * @param id            id of aircraft to remove part(s) from
	 * @return aircraftsData : updated aircrafsData with part(s) removed
	 */
	public static HashMap<Integer, ArrayList<String>> removePart(HashMap<Integer, ArrayList<String>> aircraftsData,
			HashMap<Integer, ArrayList<String>> partsData, Scanner scan, int id) {
		isRemoving = true;
		while (isRemoving) {
			// get plane to edit (for both methods)
			System.out.println(
					CYAN + "Quel avion souhaitez-vous modifier (Suppression de pièces) ? Saisissez le numéro Id:");
			int aircraftId = scan.nextInt();
			ArrayList<String> aircraftInfo = null;
			// System.out.println("plane id: " + aircraftId);
			// display plane corresponding to selected plane
			for (Map.Entry<Integer, ArrayList<String>> aircraft : aircraftsData.entrySet()) {
				if (aircraft.getKey() == aircraftId) {
					System.out.println("Avion à modifier: " + aircraft + "\n");
					aircraftInfo = aircraft.getValue();
//					System.out.println("in map loop, aircraftInfo value:" + aircraftInfo);
				}
			}
			// ask user name of part to remove
//			System.out.println("out of map loop, aircraftInfo value:" + aircraftInfo);
			System.out.println("Saisissez le nom de la pièce que souhaitez-vous supprimer:");
			String partName = scan.next();
			System.out.println("part name to remove: " + partName);
//			System.out.println("**new aircraft info BEFORE removal =" + aircraftInfo);

			// not doing what I want it to do...
			// looping through arraylist editedAircraftInfo (modified arraylist);
			for (int i = 0; i < aircraftInfo.size(); i++) {
				if (partName == aircraftInfo.get(i)) {
					System.out.println("**" + partName + " found");
					// if item is partName, remove ?????????
					aircraftInfo.remove(i);
					System.out.println("**" + partName + " removed");
					// why is is not removing it (i) from aircraftInfo ?????????????????
				}
			}

//			System.out.println("after forloop to remove, aircraftInfo value:" + aircraftInfo);
			aircraftsData.put(aircraftId, aircraftInfo);
			System.out.println(GREEN);
			displayDataById(aircraftsData, aircraftId);
			System.out.println("Modification enregistrée.");
			System.out.println(BLUE);

			// continue editing or back to main menu
			System.out.println("souhaitez-vous supprimer une autre pièce? (O/N):");

			// used for both add and remove methods -- refactor when it works
			String continueEdit = scan.next();
			if (continueEdit.equalsIgnoreCase("n")) {
				System.out.println("Retour au menu principal");
				isRemoving = false;
			}
			if (continueEdit.equalsIgnoreCase("o")) {
				isRemoving = true;
			}

		} // end of while loop to remove item
		return aircraftsData;
	}

	/**
	 * Displays aircrafts by Id. Used in addPart() and removePart() to display
	 * modifications
	 * 
	 * @param aircraftsData PLM aircraft data
	 * @param id            id of aircraft we are modifying
	 */
	public static void displayDataById(HashMap<Integer, ArrayList<String>> aircraftsData, int id) {
		System.out.println("-------------------------------------------------------------------");
		// 4. Iterate HashMap using for-each loop + Map.Entry<...hmap...>
		for (Map.Entry<Integer, ArrayList<String>> aircraft : aircraftsData.entrySet()) {
			Integer aircraftId = id;
			if (aircraft.getKey() == aircraftId) {
				System.out.print(aircraft.getKey() + "\t");
				System.out.println(aircraft.getValue());
			}
		}
		System.out.println("-------------------------------------------------------------------");

	}
	
// // WORK IN PROGRESS	
//	public static void detailedAircraftsData(HashMap<Integer, ArrayList<String>> aircraftsData, HashMap<Integer, ArrayList<String>> partsData, Scanner scan) {
//		
//		
//		for (Map.Entry<Integer, ArrayList<String>> aircraft : aircraftsData.entrySet()) {
//			Integer aircraftId = aircraft.getKey(); 
//			ArrayList<String> aircraftInfo = aircraft.getValue();
//		
//		}
//		for (Map.Entry<Integer, ArrayList<String>> part : partsData.entrySet()) {
//			Integer parttId = part.getKey(); 
//			ArrayList<String> partInfo = part.getValue();
//		
//		}
//
//	}

}