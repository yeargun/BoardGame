import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Collection;
import java.util.Collections;

public class Main{

    public static Board initBoard(String initialsPath){
        try  
        {  
            File initialsFile=new File(initialsPath);   
            FileReader fr=new FileReader(initialsFile);  
            BufferedReader br=new BufferedReader(fr); 
            String lines="";
            String line;
            while((line=br.readLine())!=null){  
                lines+=line+"\n";
            }  
            br.close();
            return new Board(lines);
        }    
            catch(IOException e)  
        {  
            e.printStackTrace();  
        }
       return null;

    }
    
   



    public static void main(String[] args) throws Exception {
        PrintStream o=null;
        PrintStream console = System.out;
        try {
            o = new PrintStream(new File("output.txt"));
            System.setOut(o);
        } catch (FileNotFoundException e) {
            System.out.println("Cant write to output.txt");
            e.printStackTrace();
        }
        Board board =initBoard("src/initials4.txt");
        String commandsPath="src/commands4.txt";
        Collections.sort(board.characters);
        System.out.println(board);
        System.out.println();
        for(int i = 0;i<Board.characters.size();i++){
            System.out.println(Board.characters.get(i).toString2());
        }
        System.out.println();
        
        
        try  
        {  
            File commandsFile=new File(commandsPath);   
            FileReader fr=new FileReader(commandsFile);  
            BufferedReader br=new BufferedReader(fr); 
            String line;
            String[] splitted;
            Character ch1;
            while((line=br.readLine())!=null){  
                splitted=line.split(" ");
                String[] moveSequence = splitted[1].split(";");
                ch1 = ((Character) Board.queryWithId(splitted[0]));
                
                //does character's before movement special skill, if character type has any special skill before move
                //System.out.println(line);
                if(!ch1.move(moveSequence)){
                    System.out.println(board+"\n");
                    for(int i = 0;i<Board.characters.size();i++){
                        System.out.println(Board.characters.get(i).toString2());
                        }
                    System.out.println();
                    if(Board.isGameFinisihed())
                        return;
                    System.out.println("Error : Game board boundaries are exceeded. Input line ignored.\n");

                    continue;
                }
                    
            
                //does character's end movement special skill, if character type has any special skill at the end of move

                System.out.println(board+"\n");
                for(int i = 0;i<Board.characters.size();i++){
                    System.out.println(Board.characters.get(i).toString2());
                }
                System.out.println();
                if(Board.isGameFinisihed())
                    return;
            }  
            
            br.close();
        }    
            catch(IOException e)  
        {  
            e.printStackTrace();  
        }
        
        
        /*System.out.println(board);
        String[] wuf = {"0","1"};
        String[] wuf2 = {"0","-1","0","0","0","0"};

        
        ((Character)Board.queryWithId("O0")).move(wuf);
        System.out.println(board);
        for(int i = 0;i<Board.characters.size();i++){
            System.out.println(Board.characters.get(i).toString2());
        }
        ((Character)Board.queryWithId("O0")).move(wuf);
        System.out.println(board);
        for(int i = 0;i<Board.characters.size();i++){
            System.out.println(Board.characters.get(i).toString2());
        }
        ((Character)Board.queryWithId("O0")).move(wuf);
        System.out.println(board);
        for(int i = 0;i<Board.characters.size();i++){
            System.out.println(Board.characters.get(i).toString2());
        }
        ((Character)Board.queryWithId("O0")).move(wuf);
        System.out.println(board);
        for(int i = 0;i<Board.characters.size();i++){
            System.out.println(Board.characters.get(i).toString2());
        }

        /*((Character)Board.queryWithId("H0")).move(wuf2);
        ((Character)Board.queryWithId("H0")).endOfMoveSequence(board.getBoardArray());
        System.out.println(board);
        for(int i = 0;i<Board.characters.size();i++){
            System.out.println(Board.characters.get(i).toString2());
        }*/


        

        
       










    }



}