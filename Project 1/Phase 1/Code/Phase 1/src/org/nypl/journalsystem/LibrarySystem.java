package org.nypl.journalsystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LibrarySystem {
	private List<Journal> journals;
	
	public LibrarySystem() {
        // Initialize the list of journals
        journals = new ArrayList<>();

        // Create and add default journals
        Publisher springer = new Publisher("Springer", "Germany");
        Publisher elsevier = new Publisher("Elsevier", "Netherlands");
        Publisher natureResearch = new Publisher("Nature Research", "Great Britain");

        Journal higherEducation = new Journal("Higher Education", springer, "0018-1560", new ArrayList<>());
		Journal system = new Journal("System", elsevier, "0346-2511", new ArrayList<>());
		Journal chem = new Journal("Chem", elsevier, "2451-9294", new ArrayList<>());
		Journal nature = new Journal("Nature", natureResearch, "1476-4687", new ArrayList<>());
		Journal society = new Journal("Society", springer, "0147-2011", new ArrayList<>());

		// Add the default journals to the list
		journals.add(higherEducation);
		journals.add(system);
		journals.add(chem);
		journals.add(nature);
		journals.add(society);
		}

	public List<Journal> getJournals() {
		return journals;
	}

	public void load() throws FileNotFoundException, IOException {
		loadAuthors();
		loadArticles();
	}
	
	protected void loadAuthors() throws FileNotFoundException, IOException {
		File file = new File("data/Authors.csv");

		//TODO: Load authors from file
	}
	
	protected void loadArticles() throws FileNotFoundException, IOException {
		File file = new File("data/Articles.csv");

		//TODO: Load articles from file and assign them to appropriate journal
	}
	
	
	public void listContents() {
		//TODO: Print all journals with their respective articles and authors to the console.
	}
	
	public static final void main(String[] args) throws Exception {
		LibrarySystem librarySystem = new LibrarySystem();
		
		librarySystem.load();
		librarySystem.listContents();
	}
}
