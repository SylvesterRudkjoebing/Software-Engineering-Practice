package org.nypl.journalsystem.hindex;

import java.util.Arrays;

import org.nypl.journalsystem.LibrarySystem;
import org.nypl.journalsystem.core.IAuthor;

public class CitationCalculatorFixed {
	
	/**
	 * {@inheritDoc}
	 */
	public int calculateHIndex(IAuthor author, LibrarySystem librarySystem) {
		int hIndex = 0;
		return hIndex;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public int calculateHIndex(int[] citationsPerArticle) {
		int hIndex = 0;
		int size = citationsPerArticle.length;

		if (size == 0) {
			return hIndex;
		}

		Arrays.sort(citationsPerArticle);

		for (int i = 0; i < size; i++) {
			// Calculate the number of papers with at least (size - i) citations
			int numPapersWithAtLeastCitations = size - i;
	
			// Check if the current paper has at least (size - i) citations
			if (citationsPerArticle[i] >= numPapersWithAtLeastCitations) {
				hIndex = numPapersWithAtLeastCitations; // Update the H-Index
				break; // No need to check further, as we've found the H-Index
			}
		}
	
		return hIndex;
	}
}
