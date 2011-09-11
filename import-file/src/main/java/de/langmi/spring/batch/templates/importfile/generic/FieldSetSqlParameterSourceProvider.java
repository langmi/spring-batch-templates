/**
 * Copyright 2011 Michael R. Lange <michael.r.lange@langmi.de>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.langmi.spring.batch.templates.importfile.generic;

import java.util.Map.Entry;
import org.springframework.batch.item.database.ItemSqlParameterSourceProvider;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

/**
 * Implementation for {@link ItemSqlParameterSourceProvider}, 
 * creates {@link MapSqlParameterSource} from {@link FieldSet}.
 * 
 * @author Michael R. Lange <michael.r.lange@langmi.de>
 */
public class FieldSetSqlParameterSourceProvider implements ItemSqlParameterSourceProvider<FieldSet>{

    @Override
    public SqlParameterSource createSqlParameterSource(FieldSet item) {
        // map FieldSet to MapSqlParameterSource
        MapSqlParameterSource sps = new MapSqlParameterSource();
        for (Entry<Object, Object> entry : item.getProperties().entrySet()) {
            sps.addValue(entry.getKey().toString(), entry.getValue());
        }
        
        return sps;

    }
    
}
