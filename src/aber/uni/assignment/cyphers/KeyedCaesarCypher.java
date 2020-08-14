package aber.uni.assignment.cyphers;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Class represents Keyed Caesar Cypher
 * decode and encode text for it
 * @author Daniel Jozef Sikora
 * @version 1 (28th April 2020)
 */
public class KeyedCaesarCypher extends Cypher<String> {
    private int shift;
    private Map<Character,Character> alphabet;

    /**
     * Controller initialize Keyed Caesar cyphers.Cypher with decode mode
     */
    public KeyedCaesarCypher(){
        super(" ","A",true," ");
        shift = 0;
    }

    /**
     * Constructor 2 initialize full object to decode or encode in keyed caesar cypher
     * @param text String
     * @param shift int
     * @param key String
     * @param toDecode toDecode
     */
    public KeyedCaesarCypher(String text, int shift, String key, boolean toDecode){
        super(text,key,toDecode," ");
        this.shift = shift;
    }

    /**
     * Getter for shift variable
     * @return int
     */
    public int getShift() {
        return shift;
    }

    /**
     * setter for shift variable
     * @param shift int
     */
    public void setShift(int shift) {
        this.shift = shift;
    }

    /**
     * Method create encode/decode Keyed Caesar cyphers.Cypher alphabet base on cypher mode
     */
    public void createAlphabet()
    {
        alphabet = new HashMap<>();
        if(isToDecode()) {
            toDecode();
        }
        else
        {
            toEncode();
        }
    }

    /**
     * Method creates Keyed Caesar cyphers.Cypher encoded alphabet(real_alphabet_equivalent, coded value)
     */
    private void toEncode()
    {
        alphabet = new HashMap<>();
        char[] shiftedArray;
        CaesarCypher caesarCypher = new CaesarCypher(shift,!isToDecode());
        caesarCypher.createAlphabet();
        shiftedArray = caesarCypher.shiftKeyToArray();
        char character;
        int beginAlphabet = 0;
        for (int i = 0; i < key.length(); i++) {
            character = key.charAt(i);
            if (!alphabet.containsValue(character)) {
                alphabet.put(shiftedArray[beginAlphabet], character);
                beginAlphabet++;
            }
        }
        for (int i = 65; i <=90; i++) {
            character = (char) i;
            if (!alphabet.containsValue(character)) {
                alphabet.put(shiftedArray[beginAlphabet], character);
                beginAlphabet++;
            }
        }
    }

    /**
     * Method creates Keyed Caesar cyphers.Cypher decoded alphabet(coded value, real_alphabet_equivalent)
     */
    private void toDecode()
    {
        alphabet = new HashMap<>();
        char[] shiftedArray;
        CaesarCypher caesarCypher = new CaesarCypher(shift,isToDecode());
        caesarCypher.createAlphabet();
        shiftedArray = caesarCypher.shiftKeyToArray();
        char character;
        int beginAlphabet = 0;
        for(int i=0; i<key.length(); i++)
        {
            character = key.charAt(i);
            if(!alphabet.containsKey(character))
            {
                alphabet.put(character,shiftedArray[beginAlphabet]);
                beginAlphabet++;
            }
        }
        for(byte i=65;i<=90;i++)
        {
            character = (char) i;
            if(!alphabet.containsKey(character))
            {
                alphabet.put(character,shiftedArray[beginAlphabet]);
                beginAlphabet++;
            }
        }
    }

    /**
     * Method encode/decode Keyed Caesar cyphers.Cypher character base on alphabet
     * @param letter Character
     * @return char
     */
    private char codedOneCharacter(Character letter)
    {
        if(letter=='\n')
        {
            return '\n';
        }
        else {
            Character result = alphabet.get(letter);
            if (!(result == null)) {
                return result;
            } else {
                return '?';
            }
        }
    }

    /**
     * Method decode or encode plain text by Keyed Caesar cyphers.Cypher base on which mode user chose
     */
    @Override
    public void coded() {
        StringBuilder builder = new StringBuilder();
        createAlphabet();
        char character;
        for(int i=0; i<this.getPlainText().length();i++)
        {
            character= charAtPlainText(i);
            builder.append(codedOneCharacter(character));
        }
        setResultText(builder.toString());
    }

    /**
     * Method read key form filename declared in subclass
     */
    @Override
    public void readKey()
    {
        ClassLoader classLoader = this.getClass().getClassLoader();
        URL resourceUrl = classLoader.getResource(getFilenameForKey());
        try {
            Scanner scanner = new Scanner(new FileReader(resourceUrl.getFile()));
            if(scanner.hasNextInt())
            {
                shift = scanner.nextInt();

                if(scanner.hasNext())
                {
                    setKey(scanner.next());
                }
                else
                {
                    System.out.println("Sorry second argument sould be string");
                }
            }
            else{
                System.out.println("Sorry I am not recognise this key");
            }
        } catch (FileNotFoundException e) {
            System.out.println("I can't find this file");
        }
    }

    /**
     * Methods get from subclass key values to save
     * @return String
     */
    @Override
    public String saveKeyParts() {
        return shift+"\r\n"+key;
    }

    /**
     * Method gets filename where key is saved
     * @return String
     */
    @Override
    public String getFilenameForKey()
    {
        return "resourceTextFiles/keyedCaesarKey.txt";
    }

    /**
     * Methods displays Keyed Caesar cyphers.Cypher name used in menu
     * @return String
     */
    @Override
    public String cypherName()
    {
        return "Keyed Caesar Cypher";
    }

    /**
     * Method gets from user key for caesar keyed cypher
     * @param scanner Scanner
     */
    @Override
    public void setCypherKey(Scanner scanner) {
        System.out.println("Please insert shift");
        shift = rightIntegerInput(scanner);
        System.out.println("Please insert key:");
        key=stringInput(scanner);
    }

    /**
     * Methods displays Keyed Caesar cyphers.Cypher Key
     * @return String
     */
    @Override
    public String displayKey() {
        return shift+key;
    }
}
