package org.nypl.journalsystem;

import java.util.List;

public record Article(int id, String title, List<Integer> authorids, String issn, List<Integer> citedids) {
}