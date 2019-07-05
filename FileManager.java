import java.io.File;
import java.io.FileNotFoundException;
import java.lang.SecurityException;
import java.lang.IllegalStateException;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;

public class FileManager{
	private Formatter output;
	private Scanner input;
	private ArrayList<String> oldString = new ArrayList<String>();

	public void openFile(){
		//System.out.println("I work");
		try{
			File historico = new File("historico.txt");
			//System.out.println("historico.exists(): "+historico.exists());
			if(historico.exists()){
				input = new Scanner(historico);

				for(int i=0; input.hasNext(); i++){
					String aux = input.next();
					System.out.println(aux);
					oldString.add(aux);
				}
				oldString.trimToSize();
			}
			output = new Formatter("historico.txt");
		}catch(SecurityException securityException){
			System.err.println("You do not have write access to this file." );
            System.exit( 1 );
		}catch(FileNotFoundException fileNotFoundException){
			System.err.println( "Error opening or creating file." );
            System.exit( 1 );
		}
	}
	
	public void addTexts(String textToBeAdded, String result){
		try{
			if(oldString != null){
				for(int i=0; i<oldString.size(); i++){
					String aux[] = oldString.get(i).split("\\n");
					//System.out.println(aux.length);
					//System.out.println(aux[1]);
					output.format("%s\n", oldString.get(i));
				}
			}
			output.format("%-10s %s\n", textToBeAdded, result);	
		}catch(FormatterClosedException formatterClosedException){
			System.err.println( "Error writing to file." );
        	System.exit( 1 );
		}catch(NoSuchElementException noSuchElementException){
			System.err.println( "Invalid input. Please try again." );
        	System.exit( 1 );
		}catch( IllegalStateException stateException ){
        	System.err.println( "Error reading from file." );
        	System.exit( 1 );
    	}
	}
	
	public void closeFile(){
        if ( output != null ){	output.close();	}
    }
}