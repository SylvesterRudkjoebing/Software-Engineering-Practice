package org.nypl.journalsystem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class LibrarySystem {
	private List<Journal> journals;
	private List<Author> authors;
	private List<Article> articles;
	
	public LibrarySystem() {
        // Initialize the list of journals
        journals = new ArrayList<>();
		authors = new ArrayList<>();
		articles = new ArrayList<>();

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
		File file = new File("Project 1/Phase 2/Code/Phase 2/data/Authors.csv");
	
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			// Skip the header row
			reader.readLine();
	
			CSVParser csvParser = CSVFormat.DEFAULT.parse(reader);
	
			for (CSVRecord csvRecord : csvParser) {
				int id = Integer.parseInt(csvRecord.get(0)); // Assuming ID is in the first column (index 0)
				String authorName = csvRecord.get(1); // Assuming Name is in the second column (index 1)
				String authorSurname = csvRecord.get(2);

				String fullname = authorSurname + authorName;
				String nameWithoutQuotes = fullname.replace("\"", "");

	
				// Create an Author object and add it to your system
				Author author = new Author(id, nameWithoutQuotes);
				// Add the author to your system's data structure (e.g., a list of authors)
				authors.add(author);
			}
		}
	}
	
	protected void loadArticles() throws FileNotFoundException, IOException {
		File file = new File("Project 1/Phase 2/Code/Phase 2/data/Articles.csv");

		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String line;
			boolean isFirstLine = true; // Flag to skip the first line (header row)
			while ((line = reader.readLine()) != null) {
				if (isFirstLine) {
					isFirstLine = false;
					continue; // Skip the header row
				}
				// Process each line of the CSV file to extract article information
				String[] articleData = line.split(",");
				// Create and add Article objects to your system as needed
				if (articleData.length == 5) {
					int id = Integer.parseInt(articleData[0]);
					String title = articleData[1];
					List<Integer> authorIds = parseAuthorIds(articleData[2]);
					String issn = articleData[3];
					List<Integer> citedIds = parseAuthorIds(articleData[4]);
					
					// Create an Article object and add it to your system
					Article article = new Article(id, title, authorIds, issn, citedIds);
					// Add the article to your system's data structure (e.g., a list of articles)
					articles.add(article);

					for (Journal journal : journals) {
						if (journal.issn().equals(issn.trim())) {
							journal.articles().add(article);
						}
					}
				}
			}
		}
	}
	
	private List<Integer> parseAuthorIds(String authorIdsString) {
		List<Integer> authorIds = new ArrayList<>();
		// Remove square brackets and split by semicolons
		String[] parts = authorIdsString.replace("[", "").replace("]", "").split(";");
		
		for (String part : parts) {
			authorIds.add(Integer.parseInt(part.trim()));
		}
		
		return authorIds;
	}
	
	public void listContents() {
		for (Journal journal : journals) {
			System.out.println("Journal Name: " + journal.name());
			System.out.println("Publisher: " + journal.publisher().name());
			System.out.println("ISSN: " + journal.issn());
	
			if (journal.isFull()) {
				System.out.println("Full Issue: Yes");
			} else {
				System.out.println("Full Issue: No");
			}
	
			System.out.println("Articles:");
	
			for (Article article : journal.articles()) {
				System.out.println("  Article Title:" + article.title());
	
				System.out.print("  Authors:");
				for (Integer authorId : article.authorids()) {
					// Assuming you have a list of authors with their IDs
					String authorname = findAuthorNameById(authorId);
					System.out.print(authorname + ",");


				}
				System.out.println();
				
				System.out.print("  Cited in: ");
				for (Integer citedId : article.citedids()) {
					System.out.print(Integer.toString(citedId) + ", ");
				}
				System.out.println();
			}
	
			System.out.println();
		}
	}
	
	public String findAuthorNameById(int authorId) {
		for (Author author : authors) {
			if (author.id() == authorId) {
				return author.name();
			}
		}
		return "Unknown"; // Author not found
	}

	public static final void main(String[] args) throws Exception {
		LibrarySystem librarySystem = new LibrarySystem();
		
		librarySystem.load();
		
		librarySystem.listContents();
	}
}
