package asmparser;

import bingenerator.commandinfo.MFALCommand;
import bingenerator.commandinfo.MFALCommandParam;

public class WordConverter {
    private static final String[] commands = {
            "MOV",
            "LFM",
            "STM",
            "ADD",
            "SUB",
            "MUL",
            "DIV",
            "JMP",
            "RET",
            "CMP",
            "JIE",
            "JNE",
            "JIL",
            "JIG",
            "CAL",
            "xxx"
    };
    private static final String[] types = {
            "NUM",
            "REG",
            "ARR",
            "STACK",
    };
    public static byte toCmd(String str) {
        for (int i = 0; i < commands.length; i ++) {
            if (str.toUpperCase().equals(commands[i])) {
                return (byte) (i & 0xFF);
            }
        }
        return -1;
    }
    public static String cmdToStr(byte c) {
        if ((c >= 0) || (c < MFALCommand.CMDNUM))
            return commands[c];
        else
            return "unknown command";
    }
    public static byte toParamType(String str) {
        for (int i = 0; i < types.length; i ++) {
            if (str.toUpperCase().equals(types[i])) {
                return (byte) (i & 0xFF);
            }
        }
        return -1;
    }
    public static MFALCommand assembleCmd(String cmd, String param1, String param2) {
        MFALCommand result = new MFALCommand();
        byte cmdNum = toCmd(cmd);
        byte param1Num = toParamType(param1);
        byte param2Num = toParamType(param2);
        if ((cmdNum < 0) || (param1Num < 0) || (param2Num < 0)) {
            return null;
        } else {
            result.setCmd(cmdNum);
            result.setFirstParam(new MFALCommandParam(0, param1Num));
            result.setSecondParam(new MFALCommandParam(0, param2Num));
            return  result;
        }
    }
}
