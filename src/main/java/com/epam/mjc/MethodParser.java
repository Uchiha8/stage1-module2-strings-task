package com.epam.mjc;

import java.util.*;

public class MethodParser {
    public MethodSignature parseFunction(String signatureString) {
        signatureString = signatureString.trim();

        // Extract method parameters inside parentheses
        int openParenIndex = signatureString.indexOf('(');
        int closeParenIndex = signatureString.indexOf(')');

        if (openParenIndex == -1 || closeParenIndex == -1 || closeParenIndex < openParenIndex) {
            throw new IllegalArgumentException("Invalid method signature format");
        }

        String methodHeader = signatureString.substring(0, openParenIndex).trim();
        String parametersString = signatureString.substring(openParenIndex + 1, closeParenIndex).trim();

        // Split method header by space
        String[] headerParts = methodHeader.split("\\s+");
        int index = 0;

        String accessModifier = null;
        String returnType;
        String methodName;

        // Check if the first part is an access modifier
        if (headerParts[index].equals("public") || headerParts[index].equals("private") ||
                headerParts[index].equals("protected")) {
            accessModifier = headerParts[index++];
        }

        if (index >= headerParts.length) {
            throw new IllegalArgumentException("Invalid method signature format");
        }

        returnType = headerParts[index++];
        if (index >= headerParts.length) {
            throw new IllegalArgumentException("Invalid method signature format");
        }

        methodName = headerParts[index];

        // Parse method arguments
        List<MethodSignature.Argument> arguments = new ArrayList<>();
        if (!parametersString.isEmpty()) {
            String[] paramParts = parametersString.split("\\s*,\\s*");

            for (String param : paramParts) {
                String[] paramElements = param.split("\\s+");
                if (paramElements.length != 2) {
                    throw new IllegalArgumentException("Invalid argument format: " + param);
                }
                arguments.add(new MethodSignature.Argument(paramElements[0], paramElements[1]));
            }
        }

        // Create and populate MethodSignature object
        MethodSignature methodSignature = new MethodSignature(methodName, arguments);
        methodSignature.setAccessModifier(accessModifier);
        methodSignature.setReturnType(returnType);

        return methodSignature;
    }
}
