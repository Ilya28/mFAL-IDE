package bingenerator.tools;

import java.io.*;
import java.util.List;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import bingenerator.commandinfo.MFALCommand;
import bingenerator.commandinfo.MFALCommandParam;

public class BinCreator {
    private static int getCodeSize(List<MFALCommand> cmdList) {
        int result = 0;
        for (MFALCommand cmd : cmdList) {
            result += 1;
            result += MFALCommandParam.getParamSize(cmd.getFirstParam().getType());
            result += MFALCommandParam.getParamSize(cmd.getSecondParam().getType());
        }
        return result;
    }

    private static void writeBytes(Object to, int index, int value, int count){
        /*result[count++] = (byte)(param & 0xFF);
        result[count++] = (byte)((param >> 8) & 0xFF);
        result[count++] = (byte)((param >> 16) & 0xFF);
        result[count++] = (byte)((param >> 24) & 0xFF);*/
        byte[] buf = ByteBuffer.allocate(4).putInt(value).array();
        System.arraycopy(buf, 0 /*4-count*/, to, index, count);
    }

    static ByteBuffer toBin(List<MFALCommand> cmdList) {
        int codeSize = getCodeSize(cmdList);
        //byte[] result = new byte[codeSize];
        ByteBuffer result = ByteBuffer.allocate(codeSize);
        int count = 0;
        for (MFALCommand cmd : cmdList) {
            MFALCommandParam firstParam = cmd.getFirstParam();
            MFALCommandParam secondParam = cmd.getSecondParam();
            int firstValue = firstParam.getValue();
            int secondValue = firstParam.getValue();
            //result[count++] = cmd.getCmd();
            result.put(cmd.getCmd());
            switch (cmd.getFirstParam().getType()) {
                case MFALCommandParam.NUM:
                    result.putInt(firstValue);
                    //writeBytes(result, count, firstValue, 4);
                    //count += MFALCommandParam.getParamSize(firstParam.getType());
                    break;
                case MFALCommandParam.REG:
                    result.put((byte) (firstValue & 0xFF));
                    //writeBytes(result, count, firstValue, 1);
                    //count += MFALCommandParam.getParamSize(firstParam.getType());
                    break;
                case MFALCommandParam.ARR:
                    result.putShort((short) (firstValue & 0xFFFF));
                    //writeBytes(result, count, firstValue, 2);
                    //count += MFALCommandParam.getParamSize(firstParam.getType());
                    break;
                case MFALCommandParam.STACK:
                    // NO ARGUMENT
                    break;
            }
            switch (cmd.getSecondParam().getType()) {
                case MFALCommandParam.NUM:
                    result.putInt(secondValue);
                    break;
                case MFALCommandParam.REG:
                    result.put((byte) (secondValue & 0xFF));
                    break;
                case MFALCommandParam.ARR:
                    result.putShort((short) (secondValue & 0xFFFF));
                    break;
                case MFALCommandParam.STACK:
                    // NO ARGUMENT
                    break;
            }

        }
        return result;
    }

    public static void saveToFile(String fileName, ByteBuffer byteBuffer) {
        File outFile = new File(fileName);
        try {
            outFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileChannel fc;
        try {
            fc = new FileOutputStream(outFile).getChannel();
            byteBuffer.flip();
            try {
                fc.write(byteBuffer);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    fc.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
