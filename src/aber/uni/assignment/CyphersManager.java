package aber.uni.assignment;

import aber.uni.assignment.cyphers.Cypher;
import aber.uni.assignment.cyphers.CaesarCypher;
import aber.uni.assignment.cyphers.KeyedCaesarCypher;
import aber.uni.assignment.cyphers.VigenereCypher;
import java.io.IOException;
import java.util.Scanner;

/**
 * Class manage all cyphers in program
 * @author Daniel Jozef Sikora
 * @version 1 (28th April 2020)
 */
public class CyphersManager {
    private Cypher<?> cypher;
    private final Scanner scanner;

    /**
     * Constructor initialise manager to run
     */
    public CyphersManager()
    {
        scanner = new Scanner(System.in);
        cypher = new CaesarCypher();
    }

    /**
     * Method manage all program features
     */
    public void chooseCypher()
    {
        int answer;
        do {
            menu();
            answer = cypher.rightIntegerInput(scanner);
            switch (answer) {
                case 1:
                    workWithCaesar();
                    break;
                case 2:
                    workWithKeyedCypher();
                    break;
                case 3:
                    workWithVigenereCypher();
                    break;
                case 4:
                    setKey();
                    break;
                case 5:
                    displayCypherKey();
                    break;
                case 6:
                    addPlainText();
                    break;
                case 7:
                    displayPlainText();
                    break;
                case 8:
                    changeToDecode();
                    break;
                case 9:
                    useTextFile();
                    break;
                case 10:
                    codedText();
                    break;
                case 11:
                    displayResultText();
                    break;
                case 12:
                    saveToFileResult();
                    break;
                case 13:
                    backToPreviousText();
                    break;
                case 14:
                    loadKey();
                    break;
                case 15:
                    saveKeyToFile();
                    break;
                case 0:
                    System.out.println("Thank you");
                    break;
                default:
                    System.out.println("I am not recognize this :(");
                    break;
            }
        }while (answer!=0);
    }

    /**
     * Method loads key to chose cypher
     */
    private void loadKey() {
        cypher.readKey();
    }

    /**
     * Method save chose cypher key to file
     */
    private void saveKeyToFile() {
        try {
            cypher.saveKey();
        }catch (IOException e)
        {
            System.out.println("I can't find key file");
        }
    }

    /**
     * Method switch cypher to Vigenere Cypher
     */
    private void workWithVigenereCypher() {
        cypher = new VigenereCypher();
    }

    /**
     * Method switch cypher to Caesar Cypher
     */
    private void workWithCaesar() {
        cypher = new CaesarCypher();
    }

    /**
     * Method switch cypher to Keyed Caesar Cypher
     */
    private void workWithKeyedCypher()
    {
        cypher = new KeyedCaesarCypher();
    }

    /**
     * Method back from textResult to original text
     * inserted to cypher
     */
    private void backToPreviousText()
    {
        cypher.quickBack();
    }

    /**
     * Method sets key to chose cypher
     */
    private void setKey()
    {
        cypher.setCypherKey(scanner);
    }

    /**
     * Method displays cypher key
     */
    private void displayCypherKey()
    {
        Object key = cypher.displayKey();
        if(key == null)
        {
            System.out.println("Sorry You didn't inserted the key");
        }
        else
        {
            System.out.println(key);
        }
    }

    /**
     * Method change cypher mode (decode/encode)
     */
    private void changeToDecode()
    {
        boolean toDecode = !cypher.isToDecode();
        cypher.setToDecode(toDecode);
    }

    /**
     * Method adds plain text to cypher
     */
    private void addPlainText()
    {
            scanner.nextLine();
            System.out.println("Please insert plain text");
            String line = scanner.nextLine();
            cypher.setPlainText(line);
    }

    /**
     * Method get text from file
     */
    private void useTextFile()
    {
        System.out.println("Please insert file with extension(example.txt):");
        cypher.readFile(scanner.next());
    }

    /**
     * Method displays plain text to user
     */
    private void displayPlainText()
    {
        String text = cypher.getPlainText();
        if(!text.isEmpty()) {
            System.out.println(text);
        }
        else{
            System.out.println("Sorry you didn't insert plain text value");
            System.out.println("or you did't chose cypher");
        }
    }

    /**
     * Method displays result of (decode, encode) cypher to user
     */
    private void displayResultText()
    {
        String text = cypher.getResultText();
        if(!text.isEmpty()) {
            System.out.println(text);
        }
        else{
            System.out.println("Sorry you didn't insert plain text value");
            System.out.println("or you did't chose cypher");
        }
    }

    /**
     * Method decode/encode text base on mode,key and text
     */
    private void codedText()
    {
        if(!cypher.getPlainText().isEmpty() && cypher.getKey()!=null)
        {
            cypher.coded();
        }
        else{
            System.out.println("Sorry but you haven't plain text or key inserted");
        }
    }

    /**
     * Method saves result of (decode, encode) cypher to file
     */
    private void saveToFileResult()
    {
        System.out.println("Please type file which cypher will be saved(example.txt)");
        cypher.writeFile(scanner.next());
    }

    /**
     * Method displays menu with options for user
     */
    private void menu(){
        System.out.println("You currently use:");
        System.out.println(cypher.cypherName()+" with "+(cypher.isToDecode()?"decode":"encode")+" mode");
        System.out.println("Options to chose:");
        System.out.println("1.Use Caesar Cypher       2.Keyed Caesar Cypher");
        System.out.println("3.Vigenere Cypher         4.Set key");
        System.out.println("5. Display key            6. Add plain text");
        System.out.println("7. Display plain text     8. Change cypher mode(encode/decode)");
        System.out.println("9. Use file               10. Code text");
        System.out.println("11. Display result        12. Save result to file");
        System.out.println("13.Back to original text  14. Read Key From File");
        System.out.println("15.Save key to file        0. EXIT");
        System.out.println("chose one option:");
    }
}
