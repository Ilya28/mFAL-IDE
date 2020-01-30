package bingenerator.commandinfo;

import java.util.Objects;

public class MFALCommand {
    public static final byte CMDNUM = 15;
    public static final byte MOV = 0;  // 2 arg
    public static final byte LFM = 1;  // 2 arg
    public static final byte STM = 2;  // 2 arg
    public static final byte ADD = 3;  // 2 arg
    public static final byte SUB = 4;  // 2 arg
    public static final byte MUL = 5;  // 2 arg
    public static final byte DIV = 6;  // 2 arg
    public static final byte JMP = 7;  // 2 arg
    public static final byte RET = 8;  // 1 arg - return int result
    public static final byte CMP = 9;  // 2 arg
    public static final byte JIE = 10;  // 2 arg
    public static final byte JNE = 11; // 2 arg
    public static final byte JIL = 12; // 2 arg
    public static final byte JIG = 13; // 2 arg
    public static final byte CAL = 14; // 1 arg
    public static int numOfArg(byte cmd) {
        if ((cmd == CAL)||(cmd == RET)) {
            return 1;
        } else {
            return 2;
        }
    }

    private byte cmd;
    private MFALCommandParam firstParam, secondParam;

    public MFALCommand() {
        this.firstParam = null;
        this.secondParam = null;
    }

    public MFALCommand(byte cmd, MFALCommandParam first, MFALCommandParam second) {
        this.cmd = cmd;
        this.firstParam = first;
        this.secondParam = second;
    }

    public MFALCommand(byte cmd, int firstValue, byte firstParam, int secondValue, byte secondParam) {
        this.cmd = cmd;
        this.firstParam = new MFALCommandParam(firstValue, firstParam);
        this.secondParam = new MFALCommandParam(secondValue, secondParam);
    }

    public int numOfArg() {
        if ((cmd == CAL)||(cmd == RET)) {
            return 1;
        } else {
            return 2;
        }
    }

    public byte getCmd() {
        return cmd;
    }

    public void setCmd(byte cmd) throws IllegalArgumentException {
        if ((cmd < 0)||(cmd > CMDNUM - 1)) {
            throw new IllegalArgumentException("(" + cmd + ") is not a command");
        }
        this.cmd = cmd;
    }

    public MFALCommandParam getFirstParam() {
        return firstParam;
    }

    public void setFirstParam(MFALCommandParam firstParam) {
        this.firstParam = firstParam;
    }

    public MFALCommandParam getSecondParam() {
        return secondParam;
    }

    public void setSecondParam(MFALCommandParam secondParam) {
        this.secondParam = secondParam;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MFALCommand command = (MFALCommand) o;
        return cmd == command.cmd &&
                Objects.equals(firstParam, command.firstParam) &&
                Objects.equals(secondParam, command.secondParam);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cmd, firstParam, secondParam);
    }

    @Override
    public String toString() {
        return "MFALCommand{" +
                "cmd=" + cmd +
                ", firstParam=" + firstParam +
                ", secondParam=" + secondParam +
                '}';
    }
}
