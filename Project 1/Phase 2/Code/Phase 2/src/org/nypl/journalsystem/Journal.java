package org.nypl.journalsystem;

import java.util.ArrayList;
import java.util.List;

public record Journal(String name, Publisher publisher, String issn, List<Article> articles) {

    public Journal addArticle(Article article) {
        List<Article> updatedArticles = new ArrayList<>(articles);
        updatedArticles.add(article);
        return new Journal(name(), publisher(), issn(), updatedArticles);
    }

    public boolean isFull() {
        return articles.size() >= 3;
    }
}
