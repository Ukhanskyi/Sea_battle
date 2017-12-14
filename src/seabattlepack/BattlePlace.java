package seabattlepack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zen on 13.09.2017.
 */

public class BattlePlace {
    enum CellState {
        Sea, Ship, ShipDamaged, ShipKilled, Miss, Border
    }

    class Ship {
        List<CellRef> shipPlace = new ArrayList<>();
        List<CellRef> shipBorderPlace = new ArrayList<>();
        int life;

        Ship(int life, List<CellRef> shipPlace, List<CellRef> shipBorderPlace) {
            this.life = life;
            this.shipPlace.addAll(shipPlace);
            this.shipBorderPlace.addAll(shipBorderPlace);
            for (CellRef cell : this.shipPlace) {
                cell.getCell().setShip(this);
            }
            for (CellRef cell : this.shipBorderPlace) {
                cell.getCell().myState = CellState.Border;
            }
        }

        void attack() {
            life--;
            if (life == 0) {
                for (CellRef cell : this.shipBorderPlace) {
                    cell.getCell().attack();
                }
                aliveShips--;
            } else {
                for (CellRef cell : this.shipPlace) {
                    if (cell.getCell().myState == CellState.Ship)
                        cell.getCell().myState = CellState.ShipDamaged;
                }
            }
        }
    }

    static class Cell {
        CellState myState;
        Ship ship = null;

        Cell() {
            this.myState = CellState.Sea;
        }

        void setShip(Ship ship) {
            this.ship = ship;
            this.myState = CellState.Ship;
        }

        void attack() {
            switch (myState) {
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

    class CellRef {
        int x;
        int y;

        CellRef(int x, int y) {
            this.x = x;
            this.y = y;
        }

        Cell getCell() {
            return cells[x][y];
        }
    }

    Cell[][] cells;
    List<Ship> ships;
    int aliveShips = 10;
    int[] placedShip;

    BattlePlace() {
        cells = new Cell[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                cells[i][j] = new Cell();
            }
        }
        placedShip = new int[4];
        for (int i = 0; i < 4; i++)
            placedShip[i] = 0;
        ships = new ArrayList<>();
    }

    BattlePlace(boolean isAutoGen) {
        cells = new Cell[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                cells[i][j] = new Cell();
            }
        }
        placedShip = new int[4];
        for (int i = 0; i < 4; i++)
            placedShip[i] = 0;
        ships = new ArrayList<>();
        if (isAutoGen)
            AutoGen();
    }

    boolean checkPlace(int x, int y, int dx, int dy) {
        if (dx > 9 || dy > 9) return false;
        List<CellRef> cells = getShipPlace(x, y, dx, dy);
        for (CellRef cell : cells) {
            if (!(cell.getCell().myState == CellState.Sea || cell.getCell().myState == CellState.Border)) return false;
        }
        cells = getShipBorder(x, y, dx, dy);
        for (CellRef cell : cells) {
            if (!(cell.getCell().myState == CellState.Sea || cell.getCell().myState == CellState.Border)) return false;
        }
        return true;
    }

    List<CellRef> getShipPlace(int x, int y, int dx, int dy) {
        List<CellRef> shipPlace = new ArrayList<>();
        for (int i = x; i <= dx; i++)
            for (int j = y; j <= dy; j++)
                shipPlace.add(new CellRef(i, j));
        return shipPlace;
    }

     List<CellRef> getShipBorder(int x, int y, int dx, int dy) {
        List<CellRef> shipBorder = new ArrayList<>();
//        for (int i = x - 1; i <= dx + 1; i++) {
//            if (i < 0 || i > 9) continue;
//            if (y - 1 >= 0)
//                shipBorder.add(new CellRef(i, y - 1));
//            if (dy + 1 <= 9)
//                shipBorder.add(new CellRef(i, dy + 1));
//        }
//        for (int i = y - 1; i <= dy + 1; i++) {
//            if (i < 0 || i > 9) continue;
//            if (x - 1 >= 0)
//                shipBorder.add(new CellRef(x - 1, i));
//            if (dx + 1 <= 9)
//                shipBorder.add(new CellRef(dx + 1, i));
//        }
        int size = (((dx+1) - (x-1))>((dy+1) - (y-1)))?((dx+1) - (x-1)):((dy+1) - (y-1));
        for(int i = -1;i<size;i++){
            if(i+x>=0 && i+x<=9){
                if(y-1>=0)
                    shipBorder.add(new CellRef(i+x,y-1));
                if(dy+1<=9)
                    shipBorder.add(new CellRef(i+x,dy+1));
            }
            if(i+y>=0 && i+y<=9){
                if(x-1>=0)
                    shipBorder.add(new CellRef(x-1,i+y));
                if(dx+1<=9)
                    shipBorder.add(new CellRef(dx+1,i+y));
            }
        }
        return shipBorder;
    }

    void AutoGen() {
        generate(4);
        generate(3);
        generate(3);
        generate(2);
        generate(2);
        generate(2);
        generate(1);
        generate(1);
        generate(1);
        generate(1);
        for (int i = 0; i < 4; i++) {
            placedShip[3 - i] = 4 - i;
        }
    }

    void generate(int size) {
        while (true) {
            int x = (int) (Math.random() * 10);
            int y = (int) (Math.random() * 10);
            int dx = x, dy = y;
            if (Math.random() * 100 > 50)
                dx = x + size - 1;
            else
                dy = y + size - 1;
            if (!checkPlace(x, y, dx, dy)) continue;
            ships.add(
                    new Ship(
                            size,
                            getShipPlace(x, y, dx, dy),
                            getShipBorder(x, y, dx, dy)
                    )
            );
            break;
        }
    }

    boolean ManualPlace(int x, int y, int dx, int dy) {
        if (!checkPlace(x, y, dx, dy)) return false;
        int size = (x != dx) ? dx - x : dy - y;
        if (placedShip[3 - size] == 4 - size) return false;
        ships.add(
                new Ship(
                        size,
                        getShipPlace(x, y, dx, dy),
                        getShipBorder(x, y, dx, dy)
                )
        );
        placedShip[3 - size]++;
        return true;
    }

    void attack(int x, int y) {
        cells[x][y].attack();
    }

    CellState getCellState(int x, int y) {
        return cells[x][y].myState;
    }

    boolean isWin() {
        return !(aliveShips > 0);
    }

    boolean isFull() {
        return placedShip[0] == 1 && placedShip[1] == 2 && placedShip[2] == 3 && placedShip[3] == 4;
    }
}
