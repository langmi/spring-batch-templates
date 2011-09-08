package de.langmi.spring.batch.templates.importfile.onefile;

/**
 * Simple BusinessObject.
 *
 * @author Michael R. Lange <michael.r.lange@langmi.de>
 */
public class BusinessObject {

    private int id;
    private String attribute;

    public BusinessObject(int id, String attribute) {
        this.id = id;
        this.attribute = attribute;
    }

    public String getAttribute() {
        return attribute;
    }

    public int getId() {
        return id;
    }
}
