import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Junit { //tests base cases and edge cases
    @Test
    void convertBinaryToBinary() {
        BaseConverter converter = new BaseConverter();
        String result = converter.fromDecimal(converter.toDecimal("1101", 2), 2);
        assertEquals(result, "1101");
    }

    @Test
    void convertBinaryToOctal() {
        BaseConverter converter = new BaseConverter();
        String result = converter.fromDecimal(converter.toDecimal("1101", 2), 8);
        assertEquals(result, "15");
    }

    @Test
    void convertBinaryToDecimal() {
        BaseConverter converter = new BaseConverter();
        int result = converter.toDecimal("1101", 2);
        assertEquals(result, 13);
    }

    @Test
    void convertBinaryToHexadecimal() {
        BaseConverter converter = new BaseConverter();
        String result = converter.fromDecimal(converter.toDecimal("11010", 2), 16);
        assertEquals(result, "1A");
    }

    @Test
    void convertOctalToBinary() {
        BaseConverter converter = new BaseConverter();
        String result = converter.fromDecimal(converter.toDecimal("6", 8), 2);
        assertEquals(result, "110");
    }

    @Test
    void convertOctalToDecimal() {
        BaseConverter converter = new BaseConverter();
        int result = converter.toDecimal("6", 8);
        assertEquals(result, 6);
    }

    @Test
    void convertOctalToHexadecimal() {
        BaseConverter converter = new BaseConverter();
        String result = converter.fromDecimal(converter.toDecimal("6", 8), 16);
        assertEquals(result, "6");
    }

    @Test
    void convertDecimalToBinary() {
        BaseConverter converter = new BaseConverter();
        String result = converter.fromDecimal(14, 2);
        assertEquals(result, "1110");
    }

    @Test
    void convertDecimalToOctal() {
        BaseConverter converter = new BaseConverter();
        String result = converter.fromDecimal(14, 8);
        assertEquals(result, "16");
    }

    @Test
    void convertDecimalToHexadecimal() {
        BaseConverter converter = new BaseConverter();
        String result = converter.fromDecimal(14, 16);
        assertEquals(result, "E");
    }

    @Test
    void convertHexadecimalToBinary() {
        BaseConverter converter = new BaseConverter();
        String result = converter.fromDecimal(converter.toDecimal("C", 16), 2);
        assertEquals(result, "1100");
    }

    @Test
    void convertHexadecimalToOctal() {
        BaseConverter converter = new BaseConverter();
        String result = converter.fromDecimal(converter.toDecimal("C", 16), 8);
        assertEquals(result, "14");
    }

    @Test
    void convertHexadecimalToDecimal() {
        BaseConverter converter = new BaseConverter();
        int result = converter.toDecimal("C", 16);
        assertEquals(result, 12);
    }

    @Test
    void convertBinaryToBinarySame() {
        BaseConverter converter = new BaseConverter();
        String result = converter.fromDecimal(converter.toDecimal("101010", 2), 2);
        assertEquals(result, "101010");
    }

    @Test
    void convertOctalToOctalSame() {
        BaseConverter converter = new BaseConverter();
        String result = converter.fromDecimal(converter.toDecimal("10", 8), 8);
        assertEquals(result, "10");
    }

    @Test
    void convertDecimalToDecimalSame() {
        BaseConverter converter = new BaseConverter();
        int result = converter.toDecimal("13", 10);
        assertEquals(result, 13);
    }

}