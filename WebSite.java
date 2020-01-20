package homework5;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;
/**
 * This class creates a WebSite object using WebPage objects
 * @author Jeffrey Jin 112167837
 * CSE 214
 */
public class WebSite {
	private WebPage homepage;
	private static WebPage currentPage;
	
	/**
	 * Constructs a WebSite given a WebPage to set as the home
	 * @param home : home page, WebPage data type
	 */
	public WebSite(WebPage home) {
		homepage = home;
		currentPage = home;
	}
	/**
	 * Sets the home page of the WebSite
	 * @param w : WebPage data type
	 */
	public void setHome(WebPage w) {
		homepage = w;
	}
	/**
	 * Sets the current page 
	 * @param c : WebPage data type
	 */
	public void setCurrent (WebPage c) {
		currentPage = c;
	}
	/**
	 * Retrieve the WebSite's home page
	 * @return WebPage data type
	 */
	public WebPage getHome() {
		return homepage;
	}
	/**
	 * Retrieve the current page
	 * @return WebPage data type
	 */
	public WebPage getCurrent() {
		return currentPage;
	}
	
	/**
	 * Add a department to the current department, parameter n provides the name of the new department
	 * Departments are made with no links and a previous page of the previous department
	 * @param n : name of the new WebPage, String data type
	 * @throws Exception : Exception is thrown if a department of the same name exists in the same head department
	 */
	public void addDepartment(String n) throws Exception {
		for (int x=0;x<homepage.getLinks().length;x++) {
			if (homepage.getLinks()[x].getName().compareTo(n)==0) {
				throw new Exception ("This subdepartment already exists!");
			}
		}
		WebPage[] currentLinks = new WebPage[currentPage.getLinks().length+1];
		for (int y=0;y<currentLinks.length-1;y++) {
			currentLinks[y]=currentPage.getLinks()[y];
		}
		currentLinks[currentLinks.length-1]=new WebPage(n, new WebPage[0],null);
		currentPage.setLinks(currentLinks);
		currentPage.getLinks()[currentPage.getLinks().length-1].setPrev(currentPage); 
		this.setCurrent(currentLinks[currentLinks.length-1]);
	}

	/**
	 * Removes the current WebPage from the WebSite
	 */
	public void removeDepartment() {
		if (currentPage == homepage) {
			homepage = null;
		}
		else {
			WebPage temp = currentPage;
			currentPage = currentPage.getPrev();
			int index = 0;
			for (int x=0;x<currentPage.getLinks().length;x++) {
				if (currentPage.getLinks()[x].getName().compareTo(temp.getName())==0) {
					index = x;
				}
			}
			WebPage[] newLinks = new WebPage[currentPage.getLinks().length-1];
			for (int y=0;y<index;y++) {
				newLinks[y] = currentPage.getLinks()[y];
			}
			for (int z=index+1;z<currentPage.getLinks().length;z++) {
				newLinks[z-1]=currentPage.getLinks()[z];
			}
			currentPage.setLinks(newLinks);
		}
	}
	/**
	 * Reads a file and builds a tree 
	 * The file is read and separated based on the name of the department and the position of the department
	 * After being read, the departments are added based on the depth / number of departments from the head
	 * Return the generated WebSite
	 * @param location : location of the file, String data type
	 * @return WebSite data type
	 * @throws Exception : Exception is thrown if no file can be found at the given location
	 */
	public WebSite buildTree(String location) throws Exception {
		FileReader fr = new FileReader (location);
		Scanner scan = new Scanner (fr);
		String line = scan.nextLine();
		String[] temp = line.split(" ");
		String[] pos = new String[1];
		String[] dep = new String[1];
		pos[0] = temp[0];
		dep[0] = temp[1];
		while (scan.hasNextLine()) {
			String currentLine = scan.nextLine();
			String[] temp2 = currentLine.split(" ",2);
			String[] newpos = new String[pos.length+1];
			String[] newdep = new String[dep.length+1];
			for (int x=0;x<pos.length;x++) {
				newpos[x] = pos[x];
			}
			newpos[newpos.length-1] = temp2[0];
			for (int y=0;y<dep.length;y++) {
				newdep[y] = dep[y];
			}
			newdep[newdep.length-1] = temp2[1];
			pos = newpos;
			dep = newdep;
		}
		WebSite web = new WebSite(new WebPage(dep[0], new WebPage[0], null));
		web.setCurrent(web.getHome());
		for (int x=1;x<pos.length;x++) {
			if (pos[x].length()==2){
				web.setCurrent(web.getHome());
				web.addDepartment(dep[x]);
			}
		}
		for (int y=1;y<pos.length;y++) {
			if (pos[y].length()==3) {
				int department = Integer.parseInt(pos[y].substring(1,2));
				WebPage[] currentLinks = web.getHome().getLinks()[(department-1)].getLinks();
				WebPage[] newLinks = new WebPage[currentLinks.length+1];
				for (int z=0;z<currentLinks.length;z++) {
					newLinks[z] = currentLinks[z];
				}
				newLinks[newLinks.length-1] = new WebPage(dep[y],new WebPage[0],web.getHome().getLinks()[department-1]);
				web.getHome().getLinks()[department-1].setLinks(newLinks);
			}
		}
		web.setCurrent(web.getHome());
		return web;
	}
	/**
	 * Prints out the WebSite with a visible grouping based on each department's links
	 * @param home : the home page (also used as the current page on recursive calls), WebPage data type
	 * @param depth : the current depth of the tree, int data type
	 * @throws Exception : exception is thrown when there is an incorrect input.
	 */
	public void printFormat(WebPage home, int depth) throws Exception {
		if (home == null) {
			System.out.println ("There is currently no Website's format to print.");
		}
		if (home.getLinks().length==0) {
			String print = "";
			for (int x=0;x<depth;x++) {
				print+="    ";
			}
			if (depth%2==0 && depth != 0) {
				print+= "-";
			}
			if (depth%2==1) {
				print+= "+";
			}
			System.out.println(print + home.getName());
		}
		else {
			String print = "";
			for (int x=0;x<depth;x++) {
				print+="    ";
			}
			if (depth%2==0 && depth != 0) {
				print+= "-";
			}
			if (depth%2==1) {
				print+= "+";
			}
			System.out.println(print + home.getName());
			for (int x=0;x<home.getLinks().length;x++) {
				printFormat(home.getLinks()[x],depth+1);
			}
		}
	}
}
