public class Character implements Comparable{
    private int HP;
    private int AP;
    private String name;
    private boolean alivenessStatus;
    private int posI;
    private int posJ;
    private int movementStepNum;
    private int maxHP;

  
    public void doDamage(Object[][] boardArray){}

    public Character(String name, int i, int j){
        this.name=name;
        this.posI=i;
        this.posJ=j;
    }

    public void getDamage(int AP){
        this.HP-=AP;
    }

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
                doDamage(boardArray);
            }
            else{
                System.out.println("Error : Game board boundaries are exceeded. Input line ignored.\n");
                return false;
            }
        }
        endOfMoveSequence(boardArray);
        return true;
    }
    
    public boolean isDead(){
        return !alivenessStatus;
    }


    public void beforeMoveSequence(Object[][] boardArray){}
    public void endOfMoveSequence(Object[][] boardArray){}

    public void getHit(int damage){
        HP-=damage;
        if(HP <= 0)
            alivenessStatus=false;
    }
    
    public void attackTo(Character enemy){
        enemy.getHit(this.AP);
    }
    public int getPosI() {
        return this.posI;
    }

    public int getMovementStepNum() {
        return this.movementStepNum;
    }

    public void setMovementStepNum(int movementStepNum) {
        this.movementStepNum = movementStepNum;
    }
    public void setPosI(int posI) {
        this.posI = posI;
    }

    public int getPosJ() {
        return this.posJ;
    }

    public void setPosJ(int posJ) {
        this.posJ = posJ;
    }

    public int getMaxHP() {
        return this.maxHP;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public int getHP() {
        return this.HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getAP() {
        return this.AP;
    }

    public void setAP(int AP) {
        this.AP = AP;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override 
    public String toString(){
        return this.name;
    }

    public String toString2(){
        return this.name+"\t"+this.HP+"\t("+this.getMaxHP()+")";//\ti->"+this.getPosI()+"\tj->"+this.getPosJ();
    }

    @Override
    public int compareTo(Object o) {
        if(this.name.compareTo(((Character)o).name)<0)
            return -1;
        else if(this.name.compareTo(((Character)o).name)>0)
            return 1;
        return 0;
    }


}
