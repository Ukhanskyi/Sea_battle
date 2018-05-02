package seabattlepack.logic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DZYKAN on 13.09.2017.
 */

public class BattlePlace {
    public enum CellState {
        SEA, SHIP, SHIP_DAMAGED, SHIP_KILLED, MISS, BORDER
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
                cell.getCell().myState = CellState.BORDER;
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
                    if (cell.getCell().myState == CellState.SHIP)
                        cell.getCell().myState = CellState.SHIP_DAMAGED;
                }
            }
        }
    }

    static class Cell {
        CellState myState;
        Ship ship = null;

        Cell() {
            this.myState = CellState.SEA;
        }

        void setShip(Ship ship) {
            this.ship = ship;
            this.myState = CellState.SHIP;
        }

        void attack() {
            switch (myState) {
                case SEA:
                case BORDER:
                    this.myState = CellState.MISS;
                    break;
                case SHIP:
                case SHIP_DAMAGED:
                    this.myState = CellState.SHIP_KILLED;
                    this.ship.attack();
                    break;
                case SHIP_KILLED:
                    break;
                case MISS:
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

    public BattlePlace() {
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
        this();
        if (isAutoGen)
            autoGen();
    }

    boolean checkPlace(int x, int y, int dx, int dy) {

        return  (!((dx > 9 || dy > 9) || !(checkCells(getShipPlace(x, y, dx, dy)) && (checkCells(getShipBorder(x, y, dx, dy))))));

    }

    public boolean checkCells(List<CellRef> cells) {

        for (CellRef cell : cells) {
            if (!((cell.getCell().myState == CellState.SEA) || (cell.getCell().myState == CellState.BORDER)))
                return false;
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
        for (int i = x - 1; i <= dx + 1; i++) {
            if (i < 0 || i > 9) continue;
            if (y - 1 >= 0)
                shipBorder.add(new CellRef(i, y - 1));
            if (dy + 1 <= 9)
                shipBorder.add(new CellRef(i, dy + 1));
        }
        for (int i = y - 1; i <= dy + 1; i++) {
            if (i < 0 || i > 9) continue;
            if (x - 1 >= 0)
                shipBorder.add(new CellRef(x - 1, i));
            if (dx + 1 <= 9)
                shipBorder.add(new CellRef(dx + 1, i));
        }
        return shipBorder;
    }

    public void autoGen() {
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
            RandomPoint point = new RandomPoint();
            int x = point.getX();
            int y = point.getY();
            int dx = x;
            int     dy = y;
            if (Math.random() * 100 > 50)
                dx = x + size - 1;
            else
                dy = y + size - 1;
            if (!checkPlace(x, y, dx, dy)) {
                continue;
            }
            ships.add(getShipByCoord(size, x, y, dx, dy));
            break;
        }
    }

    public Ship getShipByCoord(int size, int x, int y, int dx, int dy) {
        return new Ship(
                size,
                getShipPlace(x, y, dx, dy),
                getShipBorder(x, y, dx, dy)
        );
    }

    public boolean manualPlace(int x, int y, int dx, int dy) {
        if (!checkPlace(x, y, dx, dy)) return false;
        int size = (x != dx) ? dx - x : dy - y;
        if (placedShip[3 - size] == 4 - size) return false;

        ships.add(getShipByCoord(size, x, y, dx, dy));
        placedShip[3 - size]++;
        return true;
    }

    void attack(int x, int y) {
        cells[x][y].attack();
    }

    public CellState getCellState(int x, int y) {
        return cells[x][y].myState;
    }

    boolean isWin() {
        return (aliveShips <= 0);
    }

    public boolean isFull() {
        return placedShip[0] == 1 && placedShip[1] == 2 && placedShip[2] == 3 && placedShip[3] == 4;
    }
}
