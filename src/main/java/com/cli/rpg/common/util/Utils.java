package com.cli.rpg.common.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Optional;
import java.util.StringTokenizer;

public class Utils {
	
    private static final boolean PRINT_ERRORS = false;
    private static final BufferedReader IN = new BufferedReader(new InputStreamReader(System.in));
    private static String currentToken = null;
    private static StringTokenizer reader;
	
	public Utils() {
    }

    public static <E> Optional<E> promptForElement(Collection<E> collection) {
        int index = 0;
        for (E element : collection) {
            ++index;
            System.out.println(index + ". " + element.toString());
        }

        int choice = readInt();

        index = 0;
        for (E element : collection) {
            ++index;
            if (index == choice) {
                return Optional.ofNullable(element);
            }
        }

        System.out.println("Invalid choice; selecting randomly.");
        return getRandomElement(collection);
    }

    public static <E> Optional<E> getRandomElement(Collection<E> collection) {
        return collection.stream().skip((long) (collection.size() * Math.random())).findFirst();
    }

    public static <E, T> Optional<E> promptForElementWithAlternative(Collection<E> collection, T alternative) {
        if (alternative == null) {
            throw new IllegalArgumentException("Alternative option cannot be null!");
        }

        int index = 0;
        for (E element : collection) {
            ++index;
            System.out.println(index + ". " + element.toString());
        }
        System.out.println(++index + ". " + alternative.toString());

        int choice = readInt();

        index = 0;
        for (E element : collection) {
            ++index;
            if (index == choice) {
                return Optional.ofNullable(element);
            }
        }
        if (++index == choice) {
            return Optional.empty();
        }

        System.out.println("Invalid choice; selecting randomly.");
        return getRandomElement(collection);
    }

    public static String toCamelCase(String str) {
        return str.charAt(0) + str.toLowerCase().substring(1);
    }
    
    public static String readString() {
        StringBuilder str;

        try {
            str = new StringBuilder(getNextToken(false));
            while (!endOfLine()) {
                str.append(getNextToken(false));
            }
        } catch (Exception exception) {
            error("Error reading String data, null value returned.");
            str = null;
        }
        return (str != null) ? str.toString() : null;
    }

    private static String getNextToken(boolean skip) {
        String token;

        if (currentToken == null) {
            token = getNextInputToken(skip);
        } else {
            token = currentToken;
            currentToken = null;
        }

        return token;
    }

    private static boolean endOfLine() {
        return !reader.hasMoreTokens();
    }

    private static void error(String str) {
        if (PRINT_ERRORS) {
            System.out.println(str);
        }
    }

    private static String getNextInputToken(boolean skip) {
        @SuppressWarnings("HardcodedLineSeparator") final String delimiters = " \t\n\r\f";
        String token = null;

        try {
            if (reader == null) {
                reader = new StringTokenizer(IN.readLine(), delimiters, true);
            }

            while (token == null || ((delimiters.contains(token)) && skip)) {
                while (!reader.hasMoreTokens()) {
                    reader = new StringTokenizer(IN.readLine(), delimiters, true);
                }

                token = reader.nextToken();
            }
        } catch (Exception exception) {
            token = null;
        }

        return token;
    }

    public static String readWord() {
        String token;
        try {
            token = getNextToken();
        } catch (Exception exception) {
            error("Error reading String data, null value returned.");
            token = null;
        }
        return token;
    }

    private static String getNextToken() {
        return getNextToken(true);
    }

    public static boolean readBoolean() {
        String token = getNextToken();
        boolean bool;
        try {
            if ("true".equals(token.toLowerCase())) {
                bool = true;
            } else if ("false".equals(token.toLowerCase())) {
                bool = false;
            } else {
                error("Error reading boolean data, false value returned.");
                bool = false;
            }
        } catch (Exception exception) {
            error("Error reading boolean data, false value returned.");
            bool = false;
        }
        return bool;
    }

    public static char readChar() {
        String token = getNextToken(false);
        char value;
        try {
            if (token.length() > 1) {
                currentToken = token.substring(1, token.length());
            } else
                currentToken = null;

            value = token.charAt(0);
        } catch (Exception exception) {
            error("Error reading char data, MIN_VALUE value returned.");
            value = Character.MIN_VALUE;
        }

        return value;
    }

    public static int readInt() {
        String token = getNextToken();
        int value;
        try {
            value = Integer.parseInt(token);
        } catch (Exception exception) {
            error("Error reading int data, MIN_VALUE value returned.");
            value = Integer.MIN_VALUE;
        }
        return value;
    }

    public static long readLong() {
        String token = getNextToken();
        long value;
        try {
            value = Long.parseLong(token);
        } catch (Exception exception) {
            error("Error reading long data, MIN_VALUE value returned.");
            value = Long.MIN_VALUE;
        }
        return value;
    }

    public static float readFloat() {
        String token = getNextToken();
        float value;
        try {
            value = new Float(token);
        } catch (Exception exception) {
            error("Error reading float data, NaN value returned.");
            value = Float.NaN;
        }
        return value;
    }

    public static double readDouble() {
        String token = getNextToken();
        double value;
        try {
            value = new Double(token);
        } catch (Exception exception) {
            error("Error reading double data, NaN value returned.");
            value = Double.NaN;
        }
        return value;
    }
}
