package com.testres.testrest.util;

import org.springframework.stereotype.Component;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * @author konst
 */
@Component
public class ZonedDateTimeConverter {
    public Timestamp convertToDatabaseColumn(ZonedDateTime entityAttribute) {
        return entityAttribute != null
                ? Timestamp.from(entityAttribute.toInstant())
                : null;
    }

    public ZonedDateTime convertToEntityAttribute(Timestamp databaseColumn) {
        return (databaseColumn != null)
                ? ZonedDateTime.ofInstant(databaseColumn.toInstant(),ZoneId.systemDefault())
                : null;
    }
}
