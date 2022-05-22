/**
 * TCSS 360
 * 
 * Main class used to run program.
 */

package src;

class Main {
	
	/**
	 * Main Class that will act as a driver for TeamName project.
	 *
	 * @author  TeamName
	 * @version 1.0
	 * @since   5/22/2022
	 */

	/**
	 * Main method calls on guiBuilder method from the GUIfile class to display the start place
	 * of our program. Initally the program will load and give the user the option
	 * to either import/export an item, or sign-on.
	 * @param args
	 */
    public static void main(String[] args) {

        new MainView().guiBuilder();

    }

}
