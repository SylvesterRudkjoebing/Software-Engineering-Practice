package org.nypl.journalsystem;

import java.util.List;

public record Citation(int id, List<Integer> citesids, List<Integer> citedbyids) {
}
