package aber.uni.assignment.cyphers;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Class represents Caesar Cypher
 * decode and encode text for it
 * @author Daniel Jozef Sikora
 * @version 1 (28th April 2020)
 */
public class CaesarCypher extends Cypher<Integer> {
    private Map<Character,Character> alphabet;

    /**
     * Controller initialize Caesar cyphers.Cypher with decode mode
     */
    public CaesarCypher()
    {
        super(" ",0,true," ");
    }

    /**
     * Constructor 2 initialize object to creates shifted alphabet for caesar cypher
     * @param shift int
     * @param toDecode boolean
     */
    public CaesarCypher(int shift, boolean toDecode)
    {
        super(" ",shift,toDecode," ");
    }

    /**
     * Constructor 3 initialize full object to decode or encode in caesar cypher
     * @param text String
     * @param shift int
     * @param toDecode boolean
     */
    public CaesarCypher(String text, int shift, boolean toDecode)
    {
        super(text,shift,toDecode," ");
    }


    /**
     * Method create encode/decode Caesar cyphers.Cypher alphabet base on cypher mode
     */
    public void createAlphabet()
    {
        alphabet = new HashMap<>();
        if(isToDecode()) {
            toDecode(getKey());
        }
        else
        {
            toEncode(getKey());
        }
    }

    /**
     * Method creates Caesar cyphers.Cypher encoded alphabet(real_alphabet_equivalent, coded value)
     * @param shift int
     */
    private void toEncode(int shift)
    {
        char shiftedLetter;
        for(int i=65; i<=(90-shift); i++)
        {
            shiftedLetter = (char)(shift+i);
            alphabet.put((char)i,shiftedLetter);
        }

        for(int b=91-shift; b<=90; b++)
        {
            shiftedLetter = (char)(((shift+b)%91)+65);
            alphabet.put((char) b,shiftedLetter);
        }

    }

    /**
     * Method creates Keyed Caesar cyphers.Cypher decoded alphabet(coded value, real_alphabet_equivalent)
     * @param shift int
     */
    private  void toDecode(int shift)
    {
        char shiftedLetter;
        for(int i=65; i<=(90-shift); i++)
        {
            shiftedLetter = (char)(shift+i);
            alphabet.put(shiftedLetter,(char)i);
        }

        for(int b=91-shift; b<=90; b++)
        {
            shiftedLetter = (char)(((shift+b)%91)+65);
            alphabet.put(shiftedLetter,(char) b);
        }
    }

    /**
     * Method encode/decode Caesar cyphers.Cypher character base on alphabet
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
     * Methods creates char array with Caesar cyphers.Cypher used in Keyed Caesar cyphers.Cypher
     * @return char[]
     */
    public char[] shiftKeyToArray()
    {
        char[] characters = new char[alphabet.size()];
        for(int i = 90 ; i>=65; i--)
        {
            characters[i-65] = alphabet.get((char)i);
        }
        return characters;
    }

    /**
     * Method decode or encode plain text by Caesar cyphers.Cypher base on which mode user chose
     */
    @Override
    public void coded()
    {
        StringBuilder builder = new StringBuilder();
        createAlphabet();
        for(int i=0; i<this.getPlainText().length();i++)
        {
            char character= this.charAtPlainText(i);
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
                setKey(scanner.nextInt());
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
        return getKey().toString();
    }

    /**
     * Method gets filename where key is saved
     * @return String
     */
    @Override
    public String getFilenameForKey()
    {
        return "resourceTextFiles/caesarKey.txt";
    }

    /**
     * Methods displays Caesar cyphers.Cypher name used in menu
     * @return String
     */
    @Override
    public String cypherName()
    {
        return "Caesar Cypher";
    }

    /**
     * Method gets from user key for caesar cypher
     * @param scanner Scanner
     */
    @Override
    public void setCypherKey(Scanner scanner)
    {
        System.out.println("Please insert integer key");
        setKey(rightIntegerInput(scanner));
    }

    /**
     * Methods displays Caesar cyphers.Cypher Key
     * @return Integer
     */
    @Override
    public Integer displayKey() {
        return getKey();
    }
}
