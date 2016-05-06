import java.util.ArrayList;

/**
 * Created by Austin on 3/23/2016.
 *
 * Entity class is extended by the types of entities.
 *
 */

class Entity {

    protected int xLoc, yLoc;
    private String name;
    private char symbol;

    private int team;
    private Entity prevEnt;
    //private double attackPower;
    private int attackDir;
    //private double health, healthRegen;
   // private double emotion;

    Entity() {
        name = "";
        symbol = 'e';

    }

    Entity(String n, char s) {
        name = n;
        symbol = s;

    }

//    Entity(String n, char s, int x, int y, double aP, double h, double hR, double e) {
//        name = n;
//        symbol = s;
//        xLoc = x;
//        yLoc = y;
////        attackPower = aP;
////        health = h;
////        healthRegen = hR;
////        emotion = e;
//    }


    void move(int dir, boolean stay) {
        if (stay) return;

        switch (dir) {
            case 0:
                yLoc--;
                break;
            case 45:
                xLoc--;
                yLoc--;
                break;
            case 90:
                xLoc--;
                break;
            case 135:
                xLoc--;
                yLoc++;
                break;
            case 180:
                yLoc++;
                break;
            case 225:
                xLoc++;
                yLoc++;
                break;
            case 270:
                xLoc++;
                break;
            case 315:
                xLoc++;
                yLoc--;
                break;
            default:
                xLoc--;

        }
    }

    boolean canAttack(ArrayList<ArrayList<Entity>> around) {
        boolean flag = false;


        return flag;
    }


        //Use this later
//    ArrayList<Node> getAttackLocs(ArrayList<ArrayList<Entity>> grid){
//        ArrayList<Node> list = new ArrayList<>();
//
//        return list;
//    }

    void setPrevEnt(Entity e) {
        prevEnt = e;
    }

    void setxLoc(int x) {
        xLoc = x;
    }

    void setyLoc(int y) {
        yLoc = y;
    }

    void setSymbol(char s) {
        symbol = s;
    }

    void setTeam(int t) {
        team = t;
    }

    void setAttackDir(int aD) {
        attackDir = aD;
    }

    Entity getPrevEnt() {
        return prevEnt;
    }

    int getX() {
        return xLoc;
    }

    int getY() {
        return yLoc;
    }

    char getSymbol() {
        return symbol;
    }

    int getTeam() {
        return team;
    }

    int getAttackDir() {
        return attackDir;
    }

    char symbols() {
        return 'e';
    }

    @Override
    public String toString() {
        return String.valueOf(symbol);
    }


}