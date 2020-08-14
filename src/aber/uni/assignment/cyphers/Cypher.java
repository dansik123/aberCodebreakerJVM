package aber.uni.assignment.cyphers;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Class is Super class holds all common methods and
 * values for rest of cyphers
 * @author Daniel Jozef Sikora
 * @version 1 (28th April 2020)
 */
public abstract class Cypher<T> {
    String plainText;
    T key;
    boolean toDecode;
    String resultText;

    public Cypher(String plainText, T key, boolean toDecode, String resultText)
    {
        this.plainText = plainText;
        this.key = key;
        this.toDecode = toDecode;
        this.resultText = resultText;
    }

    /**
     * Getter for key variable
     * @return T
     */
    public T getKey() {
        return key;
    }

    /**
     * Setter for key variable
     * @param key T
     */
    public void setKey(T key) {
        this.key = key;
    }

    /**
     * Getter for plain text variable
     * @return String
     */
    public String getPlainText() {
        return plainText;
    }

    /**
     * Getter a character from plain text variable
     * @param i index character from plain text
     * @return char
     */
    public char charAtPlainText(int i)
    {
        return plainText.charAt(i);
    }

    /**
     * Getter for result text variable
     * @return String
     */
    public String getResultText() {
        return resultText;
    }


    /**
     * Setter for plain text variable
     * with delete special characters and
     * upper case all letters
     * @param plainText String
     */
    public void setPlainText(String plainText)
    {
        plainText=plainText.replaceAll("[\\x20-\\x40]","").toUpperCase();
        this.plainText = plainText;
    }

    public void setResultText(String resultText) {
        this.resultText = resultText;
    }

    /**
     * Getter for to decode variable
     * @return boolean
     */
    public boolean isToDecode() {
        return toDecode;
    }

    /**
     * Setter for to decode variable
     * @param toDecode boolean
     */
    public void setToDecode(boolean toDecode) {
        this.toDecode = toDecode;
    }


    /**
     * Method read all data from file by file name and save them
     * in plainText variable
     * @param filename String
     */
    public void readFile(String filename)
    {
        StringBuilder builder = new StringBuilder();
        try {
            Scanner sc2 = new Scanner(new FileReader("resources/"+filename));
            while (sc2.hasNextLine()) {
                Scanner s2 = new Scanner(sc2.nextLine());
                s2.useDelimiter("[\\x20-\\x40]");
                while (s2.hasNext()) {
                    builder.append(s2.next().toUpperCase());
                }
                builder.append("\n");
            }
            plainText=builder.toString();
        } catch (FileNotFoundException e) {
            System.out.println("I can't find this file");
        }
    }

    /**
     * Method saves all data to file used filename from
     * textResult variable
     * @param filename String
     */
    public void writeFile(String filename)
    {
        try {
            FileWriter fw = new FileWriter("resources/"+filename);
            fw.write(resultText);
            fw.flush();
            fw.close();
        }catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Method checks that input is a integer
     * @param scanner Scanner
     * @return int
     */
    public int rightIntegerInput(Scanner scanner)
    {
        while(!scanner.hasNextInt())
        {
            System.out.println("I am not recognize this");
            System.out.println("Please insert Integer");
            scanner.next();
        }
        int number = scanner.nextInt();
        if(number>=0 && number<26) {
            return number;
        }
        else{
            System.out.println("Sorry you typed wrong number");
            System.out.println("number must be from range 0-25");
            System.out.println("shift now is equal 0");
            return 0;
        }
    }

    /**
     * Methods gets string input and upper case it
     * @param scanner Scanner
     * @return String
     */
    public String stringInput(Scanner scanner)
    {
        while(!scanner.hasNextLine())
        {
            System.out.println("Please insert String");
            scanner.next();
        }
        return scanner.next().toUpperCase();
    }

    /**
     * Method change text to cypher and back cypher to original text
     */
    public void quickBack()
    {
        plainText = resultText;
        toDecode = !toDecode;
        coded();
    }

    /**
     * Methods get from subclass key values to save
     * @return String
     */
    public abstract String saveKeyParts();

    /**
     * Method read key form filename declared in subclass
     */
    public abstract void readKey();

    /**
     * Method gets filename where key is saved
     * @return String
     */
    public abstract String getFilenameForKey();

    /**
     * Method saved key to file
     * @throws IOException exception
     */
    public void saveKey() throws IOException
    {
        FileWriter fw = new FileWriter(getFilenameForKey());
        fw.write(saveKeyParts());
        fw.flush();
        fw.close();
    }

    /**
     * Method displays cypher name
     * @return String
     */
    public abstract String cypherName();

    /**
     * Method decode or encode plain text base on which mode user chose
     */
    public abstract void coded();

    /**
     * Method gets key cypher from user
     * @param scanner Scanner
     */
    public abstract void setCypherKey(Scanner scanner);

    /**
     * Method displays cypher key
     * @return T
     */
    public abstract T displayKey();

    /**
     * To String method displays all variables from object
     * @return String
     */
    @Override
    public String toString() {
        return "cyphers.Cypher{" +
                "plainText=" + plainText +
                ", key=" + key +
                ", toDecode=" + toDecode +
                ", resultText=" + resultText +
                '}';
    }
}
