package bingenerator.commandinfo;

import java.util.Objects;

public class MFALCommandParam {
    private static final int[] paramSize = {4, 1, 2, 0};
    public static int getParamSize(byte param) throws IllegalArgumentException {
        if ((param > 3)||(param < 0))
            throw new IllegalArgumentException("Param must be <3 and >0");
        return  paramSize[param];
    }
    public static final byte NUM = 0;
    public static final byte REG = 1;
    public static final byte ARR = 2;
    public static final byte STACK = 3;
//    public static final String[] typeName = {"NUM", "REG", "ARR", "STACK"};
//
//    public static String getTypeNme(byte type) {
//        return typeName[type];
//    }
//    public static byte getTypeCode(String name) {
//        if (name.equals(typeName[0]))
//            return 0;
//        else  if (name.equals(typeName[1]))
//            return 1;
//        else  if (name.equals(typeName[2]))
//            return 2;
//        else  if (name.equals(typeName[3]))
//            return 3;
//        else
//            return -1;
//    }

    private int value;
    private byte type;

    public MFALCommandParam() {
        this.value = 0;
        this.type = 0;
    }
    public MFALCommandParam(int value, byte type) throws IllegalArgumentException {
        if ((type > 3)||(type < 0))
            throw new IllegalArgumentException("Parameter (p) must be (p <= 3) and (p >= 0)");
        this.value = value;
        this.type = type;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) throws IllegalArgumentException {
        if ((type > 3)||(type < 0))
            throw new IllegalArgumentException("Param type must be <3 and >0");
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MFALCommandParam that = (MFALCommandParam) o;
        return value == that.value &&
                type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, type);
    }

    @Override
    public String toString() {
        return "MFALCommandParam{" +
                "value=" + value +
                ", type=" + type +
                '}';
    }
}
