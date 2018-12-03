// This is a SUGGESTED skeleton file.  Throw it away if you don't use it.
package enigma;

/** Class that represents a complete enigma machine.
 *  @author
 */
public class Machine {
    Rotor r = new Rotor("");
    Rotor reflector = new Rotor("");
    Rotor fixed = new Rotor("");
    Rotor r3 = new Rotor("");
    Rotor r4 = new Rotor("");
    Rotor r5 = new Rotor("");
// This needs other methods or constructors.

    /**
     * Set my rotors to (from left to right) ROTORS.  Initially, the rotor
     * settings are all 'A'.
     */
    void replaceRotors(Rotor[] rotors) {
        // FIXME
    }

    /**
     * Set my rotors according to SETTING, which must be a string of four
     * upper-case letters. The first letter refers to the leftmost
     * rotor setting.
     */
    void setRotors(String setting) {
        reflector._setting = 0;
        fixed._setting = r.toIndex(setting.charAt(0));
        r3._setting = r.toIndex(setting.charAt(1));
        r4._setting = r.toIndex(setting.charAt(2));
        r5._setting = r.toIndex(setting.charAt(3));

    }

    /**
     * Returns the encoding/decoding of MSG, updating the state of
     * the rotors accordingly.
     */
    String convert(String msg) {
        String s = "";
        int p;
        int l1, l2, l3, l4, l5, l6, l7, l8, l9;
        for (int i = 0; i < msg.length(); i++) {
            if ((r.atNotch(r4)) && (r.atNotch(r5))) {
                r.advance(r3);
                r.advance(r4);
                r.advance(r5);
            } else if (r.atNotch(r5)) {
                r.advance(r5);
                r.advance(r4);
            } else if (r.atNotch(r4)) {
                r.advance(r3);
                r.advance(r4);
                r.advance(r5);
            } else {
                r.advance(r5);
            }
            p = r.toIndex(msg.charAt(i));
            l1 = r5.convertForward(p);
            l2 = r4.convertForward(l1);
            l3 = r3.convertForward(l2);
            l4 = fixed.convertForward(l3);
            l5 = reflector.convertForward(l4);
            l6 = fixed.convertBackward(l5);
            l7 = r3.convertBackward(l6);
            l8 = r4.convertBackward(l7);
            l9 = r5.convertBackward(l8);

            s += r.toLetter(l9);
        }
        return s;
    }
}





