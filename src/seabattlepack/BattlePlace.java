package seabattlepack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zen on 13.09.2017.
 */

public class BattlePlace {
    enum CellState{
        Sea, Ship, ShipDamaged, ShipKilled, Miss, Border
    }

    class Ship{
        List<CellRef> shipPlace = new ArrayList<>();
        List<CellRef> shipBorderPlace = new ArrayList<>();
        int life;

        Ship(int life, List<CellRef> shipPlace,List<CellRef> shipBorderPlace){
            this.life = life;
            this.shipPlace.addAll(shipPlace);
            this.shipBorderPlace.addAll(shipBorderPlace);
            for (CellRef cell: this.shipPlace){
                cell.getCell().setShip(this);
            }
            for(CellRef cell: this.shipBorderPlace){
                cell.getCell().myState = CellState.Border;
            }
        }

        void attack(){
            life--;
            if(life==0){
                for(CellRef cell: this.shipBorderPlace){
                    cell.getCell().attack();
                }
                aliveShips--;
            } else{
                for(CellRef cell: this.shipPlace){
                    if(cell.getCell().myState == CellState.Ship)
                        cell.getCell().myState = CellState.ShipDamaged;
                }
            }
        }
    }
    static class Cell{
        CellState myState;
        Ship ship = null;

        Cell(){
            this.myState = CellState.Sea;
        }

        void setShip(Ship ship){
            this.ship = ship;
            this.myState = CellState.Ship;
        }

        void attack(){
            switch (myState){
                case Sea:
                    this.myState = CellState.Miss;
                    break;
                case Ship:
                    this.myState = CellState.ShipKilled;
                    this.ship.attack();
                    break;
                case ShipDamaged:
                    this.myState = CellState.ShipKilled;
                    this.ship.attack();
                    break;
                case ShipKilled:
                    break;
                case Miss:
                    break;
                case Border:
                    this.myState = CellState.Miss;
                    break;
            }
        }
    }

    class CellRef{
        int x;
        int y;
        CellRef(int x, int y){
            this.x = x;
            this.y = y;
        }
        Cell getCell(){return cells[x][y];}
    }

    Cell[][] cells;
    List<Ship> ships;
    int aliveShips = 10;
    int[] placedShip;

    BattlePlace(){
        cells = new Cell[10][10];
        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                cells[i][j] = new Cell();
            }
        }
        placedShip = new int[4];
        for(int i=0;i<4;i++)
            placedShip[i]=0;
        ships = new ArrayList<>();
    }

    BattlePlace(boolean isAutoGen){
        cells = new Cell[10][10];
        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                cells[i][j] = new Cell();
            }
        }
        placedShip = new int[4];
        for(int i=0;i<4;i++)
            placedShip[i]=0;
        ships = new ArrayList<>();
        if(isAutoGen)
            AutoGen(null);
    }

    boolean checkPlace(int x, int y, int dx, int dy){
        if(dx>9 || dy>9) return false;
        List<CellRef> cells = getShipPlace(x,y,dx,dy);
        for(CellRef cell: cells){
            if(!(cell.getCell().myState==CellState.Sea || cell.getCell().myState==CellState.Border)) return false;
        }
        cells = getShipBorder(x,y,dx,dy);
        for(CellRef cell: cells){
            if(!(cell.getCell().myState==CellState.Sea || cell.getCell().myState==CellState.Border)) return false;
        }
        return true;
    }

    List<CellRef> getShipPlace(int x,int y, int dx, int dy){
        List<CellRef> shipPlace = new ArrayList<>();
        for(int i=x;i<=dx;i++)
            for(int j=y;j<=dy;j++)
                shipPlace.add(new CellRef(i,j));
        return shipPlace;
    }

    List<CellRef> getShipBorder(int x, int y, int dx, int dy) {
        List<CellRef> shipBorder = new ArrayList<>();
        for (int i = x - 1; i <= dx + 1; i++){
            if(i<0||i>9) continue;
            if(y-1>=0)
                shipBorder.add(new CellRef(i,y-1));
            if(dy+1<=9)
                shipBorder.add(new CellRef(i,dy+1));
        }
        for (int i = y - 1; i <= dy + 1; i++){
            if(i<0||i>9) continue;
            if(x-1>=0)
                shipBorder.add(new CellRef(x-1,i));
            if(dx+1<=9)
                shipBorder.add(new CellRef(dx+1,i));
        }
        return shipBorder;
    }

    interface ICallback{
        void exec();
    }

    void AutoGen(ICallback callback){
        generate(4);
        if(callback!=null);
        generate(3);
        if(callback!=null);
        generate(3);
        if(callback!=null);
        generate(2);
        if(callback!=null);
        generate(2);
        if(callback!=null);
        generate(2);
        if(callback!=null);
        generate(1);
        if(callback!=null);
        generate(1);
        if(callback!=null);
        generate(1);
        if(callback!=null);
        generate(1);
        if(callback!=null);
        for (int i=0;i<4;i++){
            placedShip[3 - i]=i + 1;
        }
    }

    void generate(int size){
        while(true) {
            int x = (int) (Math.random() * 10);
            int y = (int) (Math.random() * 10);
            int dx = x, dy = y;
            if (Math.random() * 100 > 50)
                dx = x + size - 1;
            else
                dy = y + size - 1;
            if(!checkPlace(x,y,dx,dy)) continue;
            ships.add(
                    new Ship(
                            size,
                            getShipPlace(x,y,dx,dy),
                            getShipBorder(x,y,dx,dy)
                    )
            );
            break;
        }
    }

    boolean ManualPlace(int x, int y, int dx, int dy){
        if(!checkPlace(x,y,dx,dy)) return false;
        int size = (x!=dx)?dx-x:dy-y;
        if(placedShip[4-(size+1)]==size + 1) return false;
        ships.add(
                new Ship(
                        size,
                        getShipPlace(x,y,dx,dy),
                        getShipBorder(x,y,dx,dy)
                )
        );
        placedShip[4-(size+1)]++;
        return true;
    }

    void attack(int x, int y){
        cells[x][y].attack();
    }

    CellState getCellState(int x, int y){
        return cells[x][y].myState;
    }

    boolean isWin(){return !(aliveShips > 0);}
    boolean isFull(){return placedShip[0]==4 && placedShip[1]==3 && placedShip[2] == 2 && placedShip[3] == 1;}
}





//    void generate(int size){
//        generate(0, 0, 0, 0, size, false);
//    }
//
//    boolean generate(int xx, int yy, int dxx, int dyy, int sized, boolean manual){
//        if(!manual){
//            while(true) {
//                int x = (int) (Math.random() * 10);
//                int y = (int) (Math.random() * 10);
//                int dx = x, dy = y;
//                if (Math.random() * 100 > 50)
//                    dx = x + sized - 1;
//                else
//                    dy = y + sized - 1;
//                if (!checkPlace(x, y, dx, dy)) continue;
//
//                ships.add(
//                        new Ship(
//                                sized,
//                                getShipPlace(x,y,dx,dy),
//                                getShipBorder(x,y,dx,dy)
//                        )
//                );
//
//                return true;
//            }
//        } else {
//            if(!checkPlace(xx,yy,dxx,dyy)) return false;
//            int size = (xx!=dxx)?dxx-xx:dyy-yy;
//            if(placedShip[4-size]==size + 1) return false;
//            ships.add(
//                    new Ship(
//                            sized,
//                            getShipPlace(xx,yy,dxx,dyy),
//                            getShipBorder(xx,yy,dxx,dyy)
//                    )
//            );
//            placedShip[4-size]++;
//            return true;
//        }
//    }
