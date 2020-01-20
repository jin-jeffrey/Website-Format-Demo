package homework5;
import java.util.*;
/**
 * This class creates and utilizes the WebSite class to create and generate trees WebSites
 * @author jinje
 *
 */
public class Generator {
	/**
	 * This is the main method of the application.
	 * In this method, users can choose to import a text file (a tree is built based on the information in the file),
	 * to add a department (adds a department to the current department), to remove a department (removes current department), to identify the current department,
	 * to move to sub departments, to move to the head department, to print the tree, and quit the application.
	 * 
	 * This method is run using user inputs and if any user inputs are incorrectly formatted, the exception will be caught, the user will be notified, and the program will continue.
	 * @param args
	 */
	public static void main(String[] args) {
		boolean runProgram = true;
		System.out.println("Welcome to ARK Website Generator! What would you like to do today?");
		Scanner scan = new Scanner(System.in);
		WebSite web = new WebSite(null);
		WebPage current;
		while (runProgram == true) {
			try {
				System.out.println("Menu");
				System.out.println("	(I) - Import .txt file");
				System.out.println("	(A) - Add Department");
				System.out.println(" 	(R) - Remove Department");
				System.out.println(" 	(C) - Current Department");
				System.out.println(" 	(G) - Go to Sub-department");
				System.out.println(" 	(H) - Head Department");
				System.out.println(" 	(P) - Print format");
				System.out.println(" 	(E) - Empty Tree");
				System.out.println(" 	(Q) - Quit");
				String userInput = scan.nextLine().substring(0,1);
				if (userInput.compareTo("I")==0 || userInput.compareTo("i")==0) {
					System.out.println("Enter the file location");
					String loc = scan.nextLine();
					web = web.buildTree(loc);
				}
				if (userInput.compareTo("A")==0 || userInput.compareTo("a")==0) {
					System.out.println("What would you like to name the department?");
					String name = scan.nextLine();
					if (web.getHome()==null) {
						web = new WebSite (new WebPage(name, new WebPage[0], null));
					}
					else {
						web.addDepartment(name);
					}
				}
				if (userInput.compareTo("R")==0 || userInput.compareTo("r")==0) {
					web.removeDepartment();
				}
				if (userInput.compareTo("C")==0 || userInput.compareTo("c")==0) {
					if (web.getHome()==null) {
						System.out.println("You are currently not in any department!");
					}
					else {
						System.out.println("You are currently at " + web.getCurrent().getName());
					}
				}
				if (userInput.compareTo("G")==0 || userInput.compareTo("g")==0) {
					if (web.getCurrent().getLinks().length == 0) {
						System.out.println("There are no further subdepartments");
					}
					else {
						System.out.println("Which department would you like to go into?");
						for (int x=0;x<web.getCurrent().getLinks().length;x++) {
							System.out.println("	("+(x+1)+") - " + web.getCurrent().getLinks()[x].getName());
						}
						int option = Integer.parseInt(scan.nextLine());
						web.setCurrent(web.getCurrent().getLinks()[option-1]);
						System.out.println("You are currently in " + web.getCurrent().getName());
					}
				}
				if (userInput.compareTo("H")==0 || userInput.compareTo("h")==0) {
					if (web.getCurrent()==null) {
						System.out.println("Sorry, you're currently not in any department");
					}
					if (web.getCurrent().getPrev()==web.getHome()) {
						System.out.println("You are currently in " + web.getHome().getName());
						web.setCurrent(web.getHome());
					}
					else if (web.getCurrent()==web.getHome()) {
						System.out.println("You are currently on the home page and not in any department.");
					}
					else {
						System.out.println("You are currently in " + web.getCurrent().getPrev().getName());
						web.setCurrent(web.getCurrent().getPrev());
					}
				}
				if (userInput.compareTo("P")==0 || userInput.compareTo("p")==0) {
					web.printFormat(web.getHome(), 0);
				}
				if (userInput.compareTo("E")==0 || userInput.compareTo("e")==0) {
					web = new WebSite(null);
				}
				if (userInput.compareTo("Q")==0 || userInput.compareTo("q")==0) {
					System.out.println("Sorry to see you go. Have a good day!");
					runProgram = false;
				}
			} catch (Exception e) {
				if (!(e instanceof NullPointerException)) {
					System.out.println("Please double check your input!");
				}
			}
		}
	}
}
