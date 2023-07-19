package exercise;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import java.util.Map;



class ValidationTest {

    @Test
    void testValidate() {
        Address address1 = new Address("Russia", "Ufa", "Lenina", "54", null);
        List<String> result1 = Validator.validate(address1);
        List<String> expected1 = List.of();
        assertThat(result1).isEqualTo(expected1);

        Address address2 = new Address(null, "London", "1-st street", "5", "1");
        List<String> result2 = Validator.validate(address2);
        List<String> expected2 = List.of("country");
        assertThat(result2).isEqualTo(expected2);

        Address address3 = new Address("USA", null, null, null, "1");
        List<String> result3 = Validator.validate(address3);
        List<String> expected3 = List.of("city", "street", "houseNumber");
        assertThat(result3).isEqualTo(expected3);
    }

    // BEGIN
    @Test
    void testAdvancedValidate() {
        Address address1 = new Address("Russia", "Ufa", "Lenina", "54", null);
        Map<String, List<String>> result1 = Validator.advancedValidate(address1);
        Map<String, List<String>> expected1 = Map.of("city", List.of(Validator.MIN_LENGTH_ERROR_MESSAGE));
        assertThat(result1).isEqualTo(expected1);

        Address address2 = new Address(null, "London", "1-st street", "5", "1");
        Map<String, List<String>> result2 = Validator.advancedValidate(address2);
        Map<String, List<String>> expected2 = Map.of("country", List.of(Validator.NOT_NULL_ERROR_MESSAGE));
        assertThat(result2).isEqualTo(expected2);

        Address address3 = new Address("USA", null, null, null, "1");
        Map<String, List<String>> result3 = Validator.advancedValidate(address3);
        Map<String, List<String>> expected3 = Map.of(
                "country", List.of(Validator.MIN_LENGTH_ERROR_MESSAGE),
                "city", List.of(Validator.NOT_NULL_ERROR_MESSAGE),
                "street", List.of(Validator.NOT_NULL_ERROR_MESSAGE),
                "houseNumber", List.of(Validator.NOT_NULL_ERROR_MESSAGE)
                );
        assertThat(result3).isEqualTo(expected3);

        Address address4 = new Address("Italia", "Rome", "Medici", "54", "32");
        Map<String, List<String>> result4 = Validator.advancedValidate(address4);
        Map<String, List<String>> expected4 = Map.of();
        assertThat(result4).isEqualTo(expected4);
    }
    // END
}
