/**
 * TCSS 360
 * 
 * Main class used to run program.
 */

package src;

import java.io.IOException;

class Main {
	
	/**
	 * Main Class that will act as a driver for TeamName project.
	 *
	 * @author  Patrick Tibbals
	 * @version 1.4
	 * @since   6/8/2022
	 *
	 * Main method calls on guiBuilder method from the MainView class to display the start place
	 * of our program. Initally the program will load and give the user the option
	 * to either import/export an item, or sign-on.
	 * @param args
	 */
    public static void main(String[] args) {
		try {
			new src.MainView().guiBuilder(new src.StartUP().fileHubInitializer());
		} catch (IOException exception){
			exception.printStackTrace();
		}
    }

}
