package com.felix.agenda.util;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.persistence.AttributeConverter;

public class LocalDateTimeConverter implements AttributeConverter<Date, LocalDateTime> {

	@Override
	public LocalDateTime convertToDatabaseColumn(Date attribute) {
		LocalDateTime ldt = LocalDateTime.ofInstant(attribute.toInstant(), ZoneId.systemDefault());
		return attribute != null ? ldt : null;
	}

	@Override
	public Date convertToEntityAttribute(LocalDateTime dbData) {
		return dbData != null ? Timestamp.valueOf(dbData) : null;		
	}
}