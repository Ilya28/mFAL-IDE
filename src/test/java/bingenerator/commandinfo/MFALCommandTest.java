package bingenerator.commandinfo;

import org.junit.Test;

import static org.junit.Assert.*;

public class MFALCommandTest {

    @Test
    public void numOfArg() {
        MFALCommand c = new MFALCommand((byte) 0, 0, (byte) 0, 0, (byte) 0);

        c.setCmd(MFALCommand.SUB);
        assertEquals(2, c.numOfArg());

        c.setCmd(MFALCommand.MOV);
        assertEquals(2, c.numOfArg());

        c.setCmd(MFALCommand.CAL);
        assertEquals(1, c.numOfArg());

        c.setCmd(MFALCommand.RET);
        assertEquals(1, c.numOfArg());
    }
}