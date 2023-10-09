package org.nypl.journalsystem;

import org.nypl.journalsystem.core.IAuthor;

public record Author(int id, String name) implements IAuthor {

    @Override
    public String getName() {
        return name;
        }
    }
