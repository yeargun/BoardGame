public class Calliance extends Character {

    public Calliance(String name, int x, int y) {
        super(name, x, y);
    }

    @Override
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
                        boardArray[i][j] = null;
                        Board.characters.remove(enemy);
                    }
                }
            }
        }
    }

    
}
