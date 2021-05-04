public class Elf extends Calliance {

    public Elf(String name, int x, int y) {
        super(name, x, y);
        super.setHP(70);
        this.setMaxHP(70);
        this.setAP(Constants.elfAP);
        this.setMovementStepNum(Constants.elfMaxMove);

    }


    @Override
    public void endOfMoveSequence(Object[][] boardArray){
        int posI = this.getPosI();
        int posJ = this.getPosJ();
        Zorde enemy;
        for(int i=posI-2; i<=posI+2 ;i++){
                if(i<0 || i>=Board.getSize())continue;
            for(int j=posJ-2;j<=posJ+2 ;j++){
                if(j<0 || j>=Board.getSize())continue;
                if(boardArray[i][j] != null && boardArray[i][j] instanceof Zorde){
                    enemy = ((Zorde) boardArray[i][j]);
                    enemy.getDamage(Constants.elfRangedAP);
                    if(enemy.getHP()<=0){
                        //System.out.println("Enemy("+enemy.getName()+") is dead");
                        boardArray[i][j] = null;
                        Board.characters.remove(enemy);
                    }
                }
            }
        }
    }

    /*@Override
    public void doDamage(Object[][] boardArray){
        int posI = this.getPosI();
        int posJ = this.getPosJ();
        Zorde enemy;
        for(int i=posI-1;i<=posI+1;i++){
            if(i<0 || i>=Board.getSize())continue;
            for(int j=posJ-1;j<=posJ+1;j++){
                if(j<0 || j>=Board.getSize())continue;
                if(boardArray[i][j] != null && boardArray[i][j] instanceof Zorde){
                    enemy = ((Zorde) boardArray[i][j]);
                    enemy.getDamage(this.getAP());
                    if(enemy.getHP()<=0){
                        //System.out.println("Enemy("+enemy.getName()+") is dead");
                        boardArray[i][j] = null;
                        Board.characters.remove(enemy);
                    }
                }
            }
        }
    }*/

    @Override
    public boolean move(String[] moveSequence){
        Object[][] boardArray = Board.getBoardArray();
        if(moveSequence.length!=this.getMovementStepNum()*2){
            System.out.println("Error : Move sequence contains wrong number of move steps. Input line ignored.\n");
            return false;
        }
        beforeMoveSequence(boardArray);
        int prevI, prevJ, moveI, moveJ, nextI, nextJ;
        for(int i = 0;i<moveSequence.length;i+=2){
            prevI = this.getPosI();
            prevJ = this.getPosJ();
            moveI = Integer.parseInt(moveSequence[i+1]);
            moveJ = Integer.parseInt(moveSequence[i]);
            nextI = prevI+moveI;
            nextJ = prevJ+moveJ;
            if(Board.isFightTillDeathPosition(this, nextI, nextJ)){
                return true;
            }
            if(Board.isValidPosition(this, nextI, nextJ)){
                boardArray[nextI][nextJ] = this;
                this.setPosI(nextI);
                this.setPosJ(nextJ);
                boardArray[prevI][prevJ]=null;

                //elf does 2 range attack at final attack. endOfMoveSequence will achieve that behaviour
                if(i==moveSequence.length-2)
                    break;
                this.doDamage(boardArray);
            }
            else{
                System.out.println("Error : Game board boundaries are exceeded. Input line ignored.\n");
                return false;
            }
        }
        endOfMoveSequence(boardArray);
        return true;
    }
    
}
