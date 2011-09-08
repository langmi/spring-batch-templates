package de.langmi.spring.batch.templates.importfile.onefile;

import org.springframework.batch.item.file.LineMapper;

/**
 * Simple LineMapper for the BusinessObject.
 * 
 * @author Michael R. Lange <michael.r.lange@langmi.de>
 */
public class BusinessObjectLineMapper implements LineMapper<BusinessObject> {

    /** {@inheritDoc} */
    @Override
    public BusinessObject mapLine(String line, int lineNumber) throws Exception {
        // i could use StringUtils.trimToNull, but i did not want to introduce
        // the maven dependency just for that
        if (line != null && !"".equals(line)) {
            return new BusinessObject(lineNumber, line);
        } else {
            return null;
        }
    }
}
