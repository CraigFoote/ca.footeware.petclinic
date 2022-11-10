package ca.footeware.petclinic.models;

import java.util.stream.Stream;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class GenderConverter implements AttributeConverter<Gender, String> {

	@Override
	public String convertToDatabaseColumn(Gender gender) {
		if (gender == null) {
			return null;
		}
		return gender.toString();
	}

	@Override
	public Gender convertToEntityAttribute(String code) {
		if (code == null) {
			return null;
		}
		return Stream.of(Gender.values()).filter(g -> g.toString().equals(code)).findFirst()
				.orElseThrow(IllegalArgumentException::new);
	}
}