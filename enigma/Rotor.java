// This is a SUGGESTED skeleton file.  Throw it away if you don't use it.
package enigma;

/** Class that represents a rotor in the enigma machine.
 *  @author
 */
class Rotor  {
    // This needs other methods, fields, and constructors.
    String notch;

    String type;
    Rotor(String type) {
        this.type = type;
    }
    int _setting;
    Rotor(int setting) {
        this._setting = setting;
    }
    /** Size of alphabet used for plaintext and ciphertext. */
    static final int ALPHABET_SIZE = 26;

    /** Assuming that P is an integer in the range 0..25, returns the
     *  corresponding upper-case letter in the range A..Z. */
    static char toLetter(int p) {

        return (char) (65 + p);  // FIXME
    }

    /** Assuming that C is an upper-case letter in the range A-Z, return the
     *  corresponding index in the range 0..25. Inverse of toLetter. */
    public int toIndex(char c) {
        return  c - 'A';
    }

    /** Returns true iff this rotor has a ratchet and can advance. */
    boolean advances() {
        return true;
    }

    /** Returns true iff this rotor has a left-to-right inverse. */
    boolean hasInverse() {
        return true;
    }

    /** Return my current rotational setting as an integer between 0
     *  and 25 (corresponding to letters 'A' to 'Z').  */
    int getSetting() {
        return _setting;
    }

    /** Set getSetting() to POSN.  */
    void set(int posn) {
        assert 0 <= posn && posn < ALPHABET_SIZE;
        _setting = posn;
    }

    /** Return the conversion of P (an integer in the range 0..25)
     *  according to my permutation. */
    int convertForward(int p) {
        if (p < 0) {
            p = 26 + p;
        }
        int s1 = p + _setting;
        char a = 'h';
        s1 %= 26;
        int i;
        for (i = 0; i < 12; i++) {
            if (PermutationData.ROTOR_SPECS[i][0].equals(type)) {
                break;
            }
        }
        if (i < 12) {
            a = PermutationData.ROTOR_SPECS[i][1].charAt(s1);
        }
        if ((toIndex(a) - _setting) >= 0) {
            return (toIndex(a) - _setting);
        } else {
            return (26 + (toIndex(a) - _setting));
        }
    }

    /** Return the conversion of E (an integer in the range 0..25)
     *  according to the inverse of my permutation. */
    int convertBackward(int p) {
        if (p < 0) {
            p = 26 + p;
        }
        char a;
        int s1 = p + _setting;
        s1 %= 26;
        int i;
        for (i = 0; i < 10; i++) {
            if (PermutationData.ROTOR_SPECS[i][0].equals(type)) {
                break;
            }

        }

        a = PermutationData.ROTOR_SPECS[i][2].charAt(s1);
        boolean check = (toIndex(a) - _setting) >= 0;
        if (check) {
            return (toIndex(a) - _setting);
        } else {
            return (26 + (toIndex(a) - _setting));
        }

    }

    /** Returns true iff I am positioned to allow the rotor to my left
     *  to advance. */
    boolean atNotch(Rotor move) {
        for (int i = 0; i < 8; i++) {
            if (PermutationData.ROTOR_SPECS[i][0].equals(move.type)) {
                move.notch = (PermutationData.ROTOR_SPECS[i][3]);
            }
        }
        int a = move._setting;
        int b = toIndex(move.notch.charAt(0));
        return a == b;
    }

    /** Advance me one position. */
    void advance(Rotor move) {

        move._setting++;
        move._setting %= 26;
    }

    /** My current setting (index 0..25, with 0 indicating that 'A'
     *  is showing). */

    int convert(int p) {

        if (p >= ALPHABET_SIZE) {
            p -= ALPHABET_SIZE;
        }

        if (p < 0) {
            p += ALPHABET_SIZE;
        }

        return p;
    }

}
