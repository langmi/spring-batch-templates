package de.langmi.spring.batch.templates.importfile.onefile;

import java.util.HashMap;
import org.springframework.batch.item.database.ItemSqlParameterSourceProvider;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

/**
 * BusinessObjectSqlParameterSourceProvider for SQL with named parameters.
 *
 * @author Michael R. Lange <michael.r.lange@langmi.de>
 */
public class BusinessObjectSqlParameterSourceProvider implements ItemSqlParameterSourceProvider<BusinessObject> {

    /** {@inheritDoc} */
    @Override
    public SqlParameterSource createSqlParameterSource(final BusinessObject item) {
        return new MapSqlParameterSource(new HashMap<String, Object>() {

            {
                put("attribute", item.getAttribute());
            }
        });
    }
}
