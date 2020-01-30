package asmparser;

import bingenerator.commandinfo.MFALCommand;
import org.junit.Test;

import asmparser.WordConverter;

import static org.junit.Assert.*;

public class WordConverterTest {

    @Test
    public void toCmd() {
        byte b = WordConverter.toCmd("div");
        assertEquals(3, b);

        b = WordConverter.toCmd("MUL");
        assertEquals(2, b);

    }

    @Test
    public void toParamType() {
        byte b = WordConverter.toParamType("arr");
        assertEquals(2, b);

        b = WordConverter.toParamType("STACK");
        assertEquals(3, b);
    }

    @Test
    public void cmdToStr() {
        assertEquals("DIV", WordConverter.cmdToStr(MFALCommand.DIV));
    }

}