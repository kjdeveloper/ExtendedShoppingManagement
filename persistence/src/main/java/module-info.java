module persistence {
    requires gson;
    requires java.sql;
    exports jankowiak.kamil.persistence.model;
    exports jankowiak.kamil.persistence.model.enums;
    exports jankowiak.kamil.persistence.repository.impl;

    opens jankowiak.kamil.persistence.model;
}