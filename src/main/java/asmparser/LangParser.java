package asmparser;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import bingenerator.commandinfo.MFALCommand;
import bingenerator.commandinfo.MFALCommandParam;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;


/*
ADD num 3556 reg 23 -> reg 56
s s             RET STACK           RET CAL
s s n           RET NUM 100         RET CAL
s s s           ADD STACK STACK
s s s n         ADD STACK ARR 45
s s n s         ADD REG 20 STACK
s s n s n       ADD REG 20 ARR 45
 */

public class LangParser {
    @Nullable
    @Contract("null -> fail")
    public static MFALCommand parseLine(String line) {
        if (line == null)
            throw new IllegalArgumentException("parsLine: line (String) must be not NULL");

        MFALCommand result = new MFALCommand();
        byte param;
        Scanner scn = new Scanner(line);

        if (!scn.hasNext()) {
            System.out.println("The string is empty");
            return null;
        }
        try {
            result.setCmd(WordConverter.toCmd(scn.next()));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return null;
        }

        if (!scn.hasNext()) {
            System.out.println("Missing first parameter type definition");
            return null;
        }
        MFALCommandParam firstParam = new MFALCommandParam();
        param = WordConverter.toParamType(scn.next());    // Second word
        if (param < 0) {              // is a parameter?
            System.out.println("Unknown parameter type");
            return null;
        }
        firstParam.setType(param); //   set firstParam type as value of second word
        if (param != MFALCommandParam.STACK) {
            if (!scn.hasNextInt()) {
                System.out.println("Error reading a value of the first parameter");
                return null;
            }
            firstParam.setValue(scn.nextInt());
        }
        result.setFirstParam(firstParam);

        if (result.numOfArg() > 1) {
            if (!scn.hasNext()) {
                System.out.println("Missing second parameter type definition for: " +
                        WordConverter.cmdToStr(result.getCmd()) + " requires " + result.numOfArg() + " params");
                return null;
            }
            MFALCommandParam secondParam = new MFALCommandParam();
            param = WordConverter.toParamType(scn.next());  //
            if (param < 0) {                      // is a parameter?
                System.out.println("Unknown parameter type");
                return null;
            }
            secondParam.setType(param); //   set firstParam type as value of second word
            if (param != MFALCommandParam.STACK) {
                if (!scn.hasNextInt()) {
                    System.out.println("Error reading a value of the second parameter");
                    return null;
                }
                secondParam.setValue(scn.nextInt());
            }
            result.setSecondParam(secondParam);
        }

        scn.close();
        return result;
    }

    public static List<MFALCommand> asCommandList(String code) {
        if (code == null)
            throw new IllegalArgumentException("parsLine: line (String) must be not NULL");
        List<MFALCommand> commands = new ArrayList<>(20);
        if (code.trim().startsWith("//") || (code.trim().equals("")))
            return commands;
        Scanner scn = new Scanner(code);
        //scn.useDelimiter("\\[|\\]|\r\n|\n|\r");
        int i = 0;
        while (scn.hasNextLine()) {
            i++;
            String str = scn.nextLine();
            if (str.trim().startsWith("//") || (str.trim().equals("")))
                continue;
            MFALCommand cmd = parseLine(str);
            if (cmd == null) {
                System.out.println("Error in line [" + i + "] -> " + str);
                break;
            } else {
                commands.add(cmd);
            }
        }

        return commands;
    }
}
