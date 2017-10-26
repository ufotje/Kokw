package be.kokw.utility;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Date;
import java.time.LocalDate;

/**
 * Created by ufotje on 26/10/2017.
 */

@Converter(autoApply = true)
public class DateConverter implements AttributeConverter<LocalDate, Date> {

@Override
public Date convertToDatabaseColumn(LocalDate locDate) {
        return (locDate == null ? null : Date.valueOf(locDate));
        }

@Override
public LocalDate convertToEntityAttribute(Date sqlDate) {
        return (sqlDate == null ? null : sqlDate.toLocalDate());
        }
        }
