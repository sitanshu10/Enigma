// This is a SUGGESTED skeleton file.  Throw it away if you want.
package enigma;

import java.io.*;

/** Enigma simulator.
 *  @author
 */
public final class Main {

    // WARNING: Not all methods that have code in them are complete!

    /**
     * Process a sequence of encryptions and decryptions, as
     * specified in the input from the standard input.  Print the
     * results on the standard output. Exits normally if there are
     * no errors in the input; otherwise with code 1.
     */
    public static void main(String[] args) {
        Machine M;
        BufferedReader input = null;
        try {
            input = new BufferedReader(
                    new InputStreamReader(new FileInputStream(args[0])));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("No such file found.");
        }

        String outputFilename;
        if (args.length >= 2) {
            outputFilename = args[1];
        } else {
            outputFilename = "output.txt";
        }


        M = null;

        try {
            while (true) {
                String line = input.readLine();
                if (line == null) {
                    break;
                }
                if (isConfigurationLine(line)) {
                    M = new Machine();
                    configure(M, line);
                } else {
                    writeMessageLine(M.convert(standardize(line)),
                            outputFilename);
                }
            }
        } catch (IOException excp) {
            System.err.printf("Input error: %s%n", excp.getMessage());
            System.exit(1);
        }
    }

    /**
     * Return true iff LINE is an Enigma configuration line.
     */
    private static boolean isConfigurationLine(String line) {

        for (int i = 0; i < (line.length() - 1); i++) {
            if ((line.charAt(i) == ' ') && (line.charAt(i + 1) == ' ')) {
                return false;
            }
        }
        String[] a = line.split(" ");
        String u = a[a.length - 1];
        boolean b = false;

        if ((a.length > 7) || (a.length < 7) || (u.length() != 4)) {
            b = false;
        } else {
            if ((a[1].equals("B")) || (a[1].equals("C"))
                 && ((a[2].equals("BETA"))) || (a[2].equals("GAMMA"))) {
                for (int i = 3; i < a.length; i++) {
                    if ((a[i].equals("I")) || (a[i].equals("II"))
                           || (a[i].equals("III")) || (a[i].equals("IV"))
                            || (a[i].equals("V")) || (a[i].equals("VI"))
                            || (a[i].equals("VII")) || (a[i].equals("VIII"))) {
                        for (int j = 0; j < 4; j++) {
                            if (('A' <= (u.charAt(j))) && ((u.charAt(j)) <= 'Z')) {
                                b = true;
                            } else {
                                b = false;
                            }
                        }
                    }
                }
            }
        }

        return b;
    }

    /**
     * Configure M according to the specification given on CONFIG,
     * which must have the format specified in the assignment.
     */

    private static void configure(Machine M, String config) {
        String[] a = config.split(" ");
        M.reflector.type = a[1];
        M.fixed.type = a[2];
        M.r3.type = a[3];
        M.r4.type = a[4];
        M.r5.type = a[5];
        String b = a[6];
        M.setRotors(b);
    }


    /**
     * Return the result of converting LINE to all upper case,
     * removing all blanks and tabs.  It is an error if LINE contains
     * characters other than letters and blanks.
     */
    private static String standardize(String line) {
        String ans = "";
        for (int i = 0; i < line.length(); i++) {
            if (Character.isWhitespace(line.charAt(i))) {
                continue;
            } else {
                if (Character.isLetter(line.charAt(i))) {
                    ans += (line.charAt(i));
                } else {
                    throw new EnigmaException("enter valid characters");

                }
            }
            ans = ans.toUpperCase();
        }
        return ans;
    }



    /** Write MSG in groups of five to out file (except that the last
     *  group may have fewer letters). */

    private static void writeMessageLine(String msg, String filename) {
        try {
            FileWriter writer = new FileWriter(filename, true);
            String outputString = ""; 
            for (int i = 0; i < msg.length(); i += 5) {
                outputString += msg.substring(i, Math.min(i + 5, msg.length()));
                if (i + 5 < msg.length()) {
                    outputString += " ";
                }
            }

            writer.write(outputString + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("IOException when writing: " + e);
        }
    }

    /** Create all the necessary rotors. */
    private static void buildRotors() {


        // FIXME
    }

}

