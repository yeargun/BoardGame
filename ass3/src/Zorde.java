public class Zorde extends Character {

    public Zorde(String name, int x, int y) {
        super(name, x, y);
    }

    @Override
    public void doDamage(Object[][] boardArray){
        int posI = this.getPosI();
        int posJ = this.getPosJ();
        Calliance enemy;
        for(int i=posI-1;i<=posI+1;i++){
            if(i<0 || i>=Board.getSize())continue;
            for(int j=posJ-1;j<=posJ+1;j++){
                if(j<0 || j>=Board.getSize())continue;
                if(boardArray[i][j] != null && boardArray[i][j] instanceof Calliance){
                    enemy = ((Calliance) boardArray[i][j]);
                    enemy.getDamage(this.getAP());
                    if(enemy.getHP()<=0){
                        //System.out.println("Enemy("+enemy.getName()+") is dead");
                        boardArray[i][j] = null;
                        Board.characters.remove(enemy);
                    }
                }
            }
        }
    }

    
}
