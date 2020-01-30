package asmparser;

import bingenerator.commandinfo.MFALCommand;
import bingenerator.commandinfo.MFALCommandParam;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class LangParserTest {

    @Test
    public void parseLine() {
        //MFALCommand testCmd = LangParser.parseLine("mul reg 5 num 23");
        assertEquals(new MFALCommand(MFALCommand.MUL, 5, MFALCommandParam.REG, 23,  MFALCommandParam.NUM),
                LangParser.parseLine("mul reg 5 num 23"));

        assertEquals(new MFALCommand(MFALCommand.DIV, 0, MFALCommandParam.STACK, -457,  MFALCommandParam.NUM),
                LangParser.parseLine("div stack num -457"));

        assertEquals(new MFALCommand(MFALCommand.SUB, 0, MFALCommandParam.STACK, 0,  MFALCommandParam.STACK),
                LangParser.parseLine("sub STACK STACK"));

        assertEquals(new MFALCommand(MFALCommand.RET, new MFALCommandParam(0, MFALCommandParam.STACK), null),
                LangParser.parseLine("RET STACK"));

        assertEquals(new MFALCommand(MFALCommand.CAL, new MFALCommandParam(9, MFALCommandParam.REG), null),
                LangParser.parseLine("cal reg 9"));

        assertEquals(new MFALCommand(MFALCommand.MOV, 40324, MFALCommandParam.ARR, 123456789,  MFALCommandParam.NUM),
                LangParser.parseLine("MOV ARR 40324 NUM 123456789"));
    }

    @Test
    public void asCommandList() {
        List<MFALCommand> commands = new ArrayList<>(100);
        commands.add(LangParser.parseLine("LFM STACK NUM 0"));
        commands.add(LangParser.parseLine("MOV STACK REG 10"));
        commands.add(LangParser.parseLine("ADD STACK STACK"));
        commands.add(LangParser.parseLine("cal stack"));
        String code =
                "LFM STACK NUM 0\n" +
                "MOV STACK   REG 10\n" +
                "   ADD   STACK STACK // hello\n" +
                "// omg\n" +
                "        \n" +
                "\n" +
                "cal stack\n";
        assertEquals(commands, LangParser.asCommandList(code));
    }
}