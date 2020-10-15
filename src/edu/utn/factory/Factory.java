package edu.utn.factory;

import edu.utn.manager.Manager;

public interface Factory {

    static Manager create(Object object) {
        return null;
    }
}
