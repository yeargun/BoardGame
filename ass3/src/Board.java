import java.util.ArrayList;




public class Board {
    private static int size;
    private static Object[][] boardArray;

    public static Object[][] getBoardArray(){
		return boardArray;
	}

    public static int getSize(){
        return size;
    }

    public void setBoardArray(Object[][] boardArray) {
		this.boardArray = boardArray;
	}

    public static ArrayList<Character> characters;
    Board(String info){
        String[] infoLines = info.split("\n");
        String[] splittedLine;
        int state=0;
        characters = new ArrayList();
        // state=1 -->getting board size
        // state=2 --> initializing callience
        // state=3 --> initializing zorde
        for(String s:infoLines){
            if(s.equals("")) continue;
            if(s.equals("BOARD")){
                state=1;
                continue;
            }
            else if(s.equals("CALLIANCE")){
                state =2;
                continue;
            }
               
            else if(s.equals("ZORDE")){
                state = 3;
                continue;
            }
            if(state == 1){
                size = Integer.parseInt(s.split("x")[0]);
                boardArray = new Object[size][size];
            }
            if(state >=2){
                splittedLine=s.split(" ");
                if(boardArray[Integer.parseInt(splittedLine[3])][Integer.parseInt(splittedLine[2])]!= null){
                    System.out.println("There is already a character on given indices");
                    continue;
                }
                if(splittedLine[0].equals("ELF")){
                    characters.add(new Elf(splittedLine[1],Integer.parseInt(splittedLine[3]),Integer.parseInt(splittedLine[2])));
                }
                else if(splittedLine[0].equals("DWARF")){
                    characters.add(new Dwarf(splittedLine[1],Integer.parseInt(splittedLine[3]),Integer.parseInt(splittedLine[2])));
                }
                else if(splittedLine[0].equals("HUMAN")){
                    characters.add(new Human(splittedLine[1],Integer.parseInt(splittedLine[3]),Integer.parseInt(splittedLine[2])));
                }
                else if(splittedLine[0].equals("ORK")){
                    characters.add(new Ork(splittedLine[1],Integer.parseInt(splittedLine[3]),Integer.parseInt(splittedLine[2])));
                }
                else if(splittedLine[0].equals("GOBLIN")){
                    characters.add(new Goblin(splittedLine[1],Integer.parseInt(splittedLine[3]),Integer.parseInt(splittedLine[2])));
                }
                else if(splittedLine[0].equals("TROLL")){
                    characters.add(new Troll(splittedLine[1],Integer.parseInt(splittedLine[3]),Integer.parseInt(splittedLine[2])));
                }
                boardArray[Integer.parseInt(splittedLine[3])][Integer.parseInt(splittedLine[2])] = characters.get(characters.size()-1);
            }
        }
    }

    public static Object queryWithId(String id){
        for(int i =0;i<boardArray.length;i++){
            for(int j=0;j<boardArray[0].length;j++){
               if(boardArray[i][j] != null && id.equals(boardArray[i][j].toString())){
                    return boardArray[i][j];
               }
            }
        }
        return null;
    }




    

    public static boolean isValidPosition(Character character1, int i, int j){
        if(i>=size || j>=size || i<0 || j<0){
            return false;
        }
        else if(boardArray[i][j] instanceof Zorde && character1 instanceof Zorde){
            System.out.println("zorde on zorde ");
            return false;
        }
        else if(boardArray[i][j] instanceof Calliance && character1 instanceof Calliance){
            System.out.println("callia on calia");
            return false;
        }
        else{
            return true;
        }

    }

    public static boolean isFightTillDeathPosition(Character character1, int i, int j){
        if(i>=size || j>=size || i<0 || j<0){return false;}
        else if(boardArray[i][j] instanceof Zorde && character1 instanceof Zorde){return false;}
        else if(boardArray[i][j] instanceof Calliance && character1 instanceof Calliance){return false;}
        else if(boardArray[i][j]==null){return false;}
        else{
            fightTillDeath(character1, i, j);
            return true;
        } 
    }

    public static void beforeMoveSequence(String id, int moveI, int moveJ){}


    @Override
    public String toString(){
        String result="";
        for(int i=0;i<2*size+2;i++){
            result+="*";
        }
        result+="\n";
        
        for(int i=0;i<size;i++){
            result+="*";
            for(int j=0;j<size;j++){
                if(boardArray[i][j]==null)
                    result += "  ";
                else   
                    result += boardArray[i][j].toString();
            }
            result += "*\n";
        }
        for(int i=0;i<2*size+2;i++){
            result+="*";
        }
        return result;
    }

    public static boolean isGameFinisihed(){
        if(characters.size()==0){
            System.out.print("\nDraw");
            return true;
        }
        Character ch1 = characters.get(0);
        for(int i = 0;i<characters.size();i++){
            if(ch1 instanceof Zorde && characters.get(i) instanceof Calliance)
                return false;
            if(ch1 instanceof Calliance && characters.get(i) instanceof Zorde)
                return false;
        }
        System.out.println("\nGame Finished");
        if(ch1 instanceof Zorde){
            System.out.print("Zorde Wins\n");
            return true;
        }
        else{
            System.out.print("Calliance Wins\n");
            return true;
        }
    }

    public static void fightTillDeath(Character ch1, int nextI, int nextJ) {
        System.out.println("AJAJ");
        Character ch2 = (Character)boardArray[nextI][nextJ];
        ch2.getDamage(ch1.getAP());
        if(ch2.getHP()<0){
            characters.remove(ch2);
            boardArray[nextI][nextJ] = ch1;
            boardArray[ch1.getPosI()][ch1.getPosJ()]=null;   
            ch1.setPosI(nextI);
            ch1.setPosJ(nextJ); 
            return;
        }
        if(ch1.getHP()>ch2.getHP()){
            characters.remove(ch2);
            boardArray[nextI][nextJ] = ch1;
            boardArray[ch1.getPosI()][ch1.getPosJ()]=null;
            ch1.setHP(ch1.getHP()-ch2.getHP());    
            ch1.setPosI(nextI);
            ch1.setPosJ(nextJ);
        }

        else if(ch2.getHP()>ch1.getHP()){
            boardArray[ch1.getPosI()][ch1.getPosJ()] = null;
            ch2.setHP(ch2.getHP()-ch1.getHP());
            characters.remove(ch1);
        }
        else{
            boardArray[ch1.getPosI()][ch1.getPosJ()] = null;
            boardArray[nextI][nextJ] = null;
            characters.remove(ch1);
            characters.remove(ch2);
        }
    }
}
