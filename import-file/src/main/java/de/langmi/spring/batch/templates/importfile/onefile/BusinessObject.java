package de.langmi.spring.batch.templates.importfile.onefile;

/**
 * Simple BusinessObject.
 *
 * @author Michael R. Lange <michael.r.lange@langmi.de>
 */
public class BusinessObject {

    private String attribute;

    public BusinessObject(String attribute) {
        this.attribute = attribute;
    }

    public String getAttribute() {
        return attribute;
    }
}
