package com.epam.mjc;

import java.util.ArrayList;
import java.util.List;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     *      1. access modifier - optional, followed by space: ' '
     *      2. return type - followed by space: ' '
     *      3. method name
     *      4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     *      accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     *      private void log(String value)
     *      Vector3 distort(int x, int y, int z, float magnitude)
     *      public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        String[] parts = signatureString.split("\\s+|\\(|\\)|,");
        int index = 0;

        String accessModifier = null;
        String returnType = null;
        String methodName = null;
        List<MethodSignature.Argument> arguments =  new ArrayList<>();

        if ((parts[0].equals("public") || parts[0].equals("private") || parts[0].equals("protected"))) {
            accessModifier = parts[index++];
        }

        if (index < parts.length) {
            returnType = parts[index++];
        }

        if (index < parts.length) {
            methodName = parts[index++];
        }

        while (index < parts.length) {
            String type = parts[index++];
            if (index < parts.length) {
                String name = parts[index++];
                arguments.add(new MethodSignature.Argument(type, name));
            }
        }

        MethodSignature methodSignature = new MethodSignature(methodName, arguments);
        methodSignature.setAccessModifier(accessModifier);
        methodSignature.setReturnType(returnType);

        return methodSignature;
    }
}
