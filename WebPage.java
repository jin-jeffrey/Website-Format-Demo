package homework5;
/**
 * This class creates the WebPage object
 * @author Jeffrey Jin 112167837
 * CSE 214
 */
public class WebPage {
	public String name;
	public WebPage[] links;
	public WebPage prevDepartment;
	
	/**
	 * Constructs a webpage
	 * @param n : name of the webpage
	 * @param w : links for the webpage
	 * @param p : previous page (larger department)
	 */
	public WebPage (String n, WebPage[] w, WebPage p) {
		name = n;
		links = w;
		prevDepartment = p;
	}
	/**
	 * Set the name of the webpage
	 * @param n : name of the webpage, String data type
	 */
	public void setName(String n) {
		name = n;
	}
	/**
	 * Set the links for the webpage
	 * @param l : links for the webpage, WebPage[] data type
	 */
	public void setLinks(WebPage[] l) {
		links = l;
	}
	/**
	 * Set the previous page
	 * @param p : previous page, WebPage data type
	 */
	public void setPrev(WebPage p) {
		prevDepartment = p;
	}
	/**
	 * Retrieve the name of the WebPage
	 * @return String data type
	 */
	public String getName() {
		return name;
	}
	/**
	 * Retrieve the WebPage's links
	 * @return WebPage[] data type
	 */
	public WebPage[] getLinks() {
		return links;
	}
	/**
	 * Retrieeve the previous WebPage
	 * @return WebPage data type
	 */
	public WebPage getPrev() {
		return prevDepartment;
	}
}
