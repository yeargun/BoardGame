public class Goblin extends Zorde {


    @Override
    public void setHP(int HP) {
        if(HP>=80)
            super.setHP(80);
        else
            super.setHP(HP);
    }

    public Goblin(String name, int x, int y) {
        super(name, x, y);
        super.setHP(80);
        this.setMaxHP(80);
        this.setAP(Constants.goblinAP);
        this.setMovementStepNum(Constants.goblinMaxMove);

    }

    

    /*@Override
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
            else if(Board.isValidPosition(this, nextI, nextJ)){
                boardArray[nextI][nextJ] = this;
                this.setPosI(nextI);
                this.setPosJ(nextJ);
                boardArray[prevI][prevJ]=null;
                this.doDamage(boardArray);
            }
            else{
                System.out.println("Error : Game board boundaries are exceeded. Input line ignored.\n");
                return false;
            }
        }
        endOfMoveSequence(boardArray);
        return true;
    }*/   
}
