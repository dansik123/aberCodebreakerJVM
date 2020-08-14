package aber.uni.assignment.cyphers;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Class represents Vigenere Cypher
 * decode and encode text for it
 * @author Daniel Jozef Sikora
 * @version 1 (28th April 2020)
 */
public class VigenereCypher extends Cypher<String> {
    private char[][] vigenereMatrix;

    /**
     * Constructor initialize vigenere  char matrix with decode mode
     */
    public VigenereCypher()
    {
        super(" ","A",true," ");
    }

    /**
     * Constructor 2 initialize full object to decode or encode in vigenere cypher
     * @param text String
     * @param key String
     * @param toDecode boolean
     */
    public VigenereCypher(String text, String key, boolean toDecode)
    {
        super(text,key,toDecode," ");
    }

    /**
     * Method creates matrix for vigenere cypher
     */
    public void initializeMatrix()
    {
        vigenereMatrix = new char[][]
            {
                    {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'},
                    {'B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','A'},
                    {'C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','A','B'},
                    {'D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','A','B','C'},
                    {'E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','A','B','C','D'},
                    {'F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','A','B','C','D','E'},
                    {'G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','A','B','C','D','E','F'},
                    {'H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','A','B','C','D','E','F','G'},
                    {'I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','A','B','C','D','E','F','G','H'},
                    {'J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','A','B','C','D','E','F','G','H','I'},
                    {'K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','A','B','C','D','E','F','G','H','I','J'},
                    {'L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','A','B','C','D','E','F','G','H','I','J','K'},
                    {'M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','A','B','C','D','E','F','G','H','I','J','K','L'},
                    {'N','O','P','Q','R','S','T','U','V','W','X','Y','Z','A','B','C','D','E','F','G','H','I','J','K','L','M'},
                    {'O','P','Q','R','S','T','U','V','W','X','Y','Z','A','B','C','D','E','F','G','H','I','J','K','L','M','N'},
                    {'P','Q','R','S','T','U','V','W','X','Y','Z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O'},
                    {'Q','R','S','T','U','V','W','X','Y','Z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P'},
                    {'R','S','T','U','V','W','X','Y','Z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q'},
                    {'S','T','U','V','W','X','Y','Z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R'},
                    {'T','U','V','W','X','Y','Z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S'},
                    {'U','V','W','X','Y','Z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T'},
                    {'V','W','X','Y','Z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U'},
                    {'W','X','Y','Z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V'},
                    {'X','Y','Z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W'},
                    {'Y','Z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X'},
                    {'Z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y'}
            };
    }

    /**
     * Method encode char character
     * @param character char
     * @param keyCharacter char
     * @return char
     */
    private char encodeToChar(char character, char keyCharacter)
    {
        int x, y;
        x= Arrays.binarySearch(vigenereMatrix[0],keyCharacter);
        y=Arrays.binarySearch(vigenereMatrix[0],character);
        if( y<0 )
        {
            return '?';
        }
        else
        {
            return vigenereMatrix[x][y];
        }
    }

    /**
     * Method decode char character
     * @param character char
     * @param keyCharacter char
     * @return char
     */
    private char decodeToChar(char character, char keyCharacter)
    {
        int x, y;
        x=Arrays.binarySearch(vigenereMatrix[0],keyCharacter);
        if(character-keyCharacter<0)
        {
            y = Arrays.binarySearch(vigenereMatrix[x],26-x,26,character);
        }
        else{
            y = Arrays.binarySearch(vigenereMatrix[x],0,26-x,character);
        }
        if( y<0 )
        {
            return '?';
        }
        else
        {
            return vigenereMatrix[0][y];
        }
    }

    /**
     * Method decode or encode plain text base on which mode user chose
     */
    @Override
    public void coded() {
        StringBuilder builder = new StringBuilder();
        initializeMatrix();
        int newLineCounter = 0;
        char character;
        if(toDecode) {
            for (int i = 0; i  < this.getPlainText().length(); i++) {
                character = charAtPlainText(i);
                if(character =='\n' || character =='\r')
                {
                    newLineCounter++;
                    builder.append(character);
                }
                else{
                    builder.append(decodeToChar(character,key.charAt((i-newLineCounter)%key.length())));
                }
            }
        }
        else
        {
            for (int i = 0; i < this.getPlainText().length(); i++) {
                character = charAtPlainText(i);
                if(character =='\n' || character =='\r')
                {
                    newLineCounter++;
                    builder.append(character);
                }
                else{
                    builder.append(encodeToChar(character,key.charAt((i-newLineCounter)%key.length())));
                }
            }
        }
        resultText = builder.toString();
    }

    /**
     * Method reads key form filename declared in subclass
     */
    @Override
    public void readKey()
    {
        ClassLoader classLoader = this.getClass().getClassLoader();
        URL resourceUrl = classLoader.getResource(getFilenameForKey());
        try {
            Scanner scanner = new Scanner(new FileReader(resourceUrl.getFile()));
            if(scanner.hasNext())
            {
                setKey(scanner.next());
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
        return key;
    }

    /**
     * Method gets filename where key is saved
     * @return String
     */
    @Override
    public String getFilenameForKey()
    {
        return "resourceTextFiles/vigenereKey.txt";
    }

    /**
     * Methods displays cypher name used in menu
     * @return String
     */
    @Override
    public String cypherName() {
        return "Vigenere Cypher";
    }

    /**
     * Method gets from user key for cypher
     * @param scanner Scanner
     */
    @Override
    public void setCypherKey(Scanner scanner) {
        System.out.println("Please insert integer key");
        setKey(scanner.next().toUpperCase());
    }

    /**
     * Method displays key, used in menu
     * @return String
     */
    @Override
    public String displayKey() {
        return key;
    }
}
