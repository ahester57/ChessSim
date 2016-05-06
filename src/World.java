import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Austin on 3/23/2016.
 *
 * This is the world. The world contains a grid
 * containing entities. These entities move around.
 *
 * Each team gets a turn to move, and the world attempts
 * to choose an entity that is able to attack. If it
 * cannot find an attacker, then an entity is chosen at
 * random.
 *
 */
class World {

    private int generations;
    private int rows, cols;
    private GameBoard board;

    private Random rand = new Random();

    private ArrayList<ArrayList<Entity>> grid;


    private ArrayList<ArrayList<Entity>> teams;
    //private ArrayList<ArrayList<Entity>> dead;

    private static int cycles = 0;

    World(GameBoard b){

        rows = b.getRows();
        cols = b.getRows();
        board = b;

        generations = 1000;

        makeGrid();
    }

    World(GameBoard b, int gen, int r, int c){
        generations = gen; //I'll be honest, I ignored this because
        rows = r+2;            //did not think it would fit into a
        cols = c+2;             //chess sim. I still need to add the
        board = b;              //ending point, when either King dies.

        makeGrid();
    }

    //draws original GUI
    void draw(){
        board.drawGrid(getCharGrid(), this);

    }

    //Updates labels
    private void update(){
        board.updateGrid(getCharGrid());
    }

    void run(){


        int team = cycles % 2;
        int direction;

        Entity e = chooseAttacker(team);


        //picks a random player from the current team if no attacker available
        if(e instanceof OpenSpace) {
            e = teams.get(team).get((int) (rand.nextDouble() * teams.get(team).size()));
            direction = (int)(rand.nextDouble() * 8) * 45;
        }else{
            direction = e.getAttackDir();
        }

        move(e, direction);


    }


    //give the entity a list of entites with possible attack locations.
    private Entity chooseAttacker(int team){
        Entity e = new OpenSpace(' ');
        Entity temp;

        int x, y;

        ArrayList<ArrayList<Entity>> around;

        for(int i = 0; i < teams.get(team).size(); i++){
            temp = teams.get(team).get(i);
            x = temp.getX();
            y = temp.getY();

            around = new ArrayList<>();

            for(int k = 0; k < 3; k++)
                around.add(new ArrayList<>());



            around.get(0).add(getEntity(x-1,y-1));
            around.get(0).add(getEntity(x-1,y));
            around.get(0).add(getEntity(x-1,y+1));

            around.get(1).add(getEntity(x,y-1));
            around.get(1).add(getEntity(x,y));
            around.get(1).add(getEntity(x,y+1));

            around.get(2).add(getEntity(x+1,y-1));
            around.get(2).add(getEntity(x+1,y));
            around.get(2).add(getEntity(x+1,y+1));


            if(teams.get(team).get(i).canAttack(around)){
                e = teams.get(team).get(i);
            }
        }

        //System.out.println(around);

        return e;
    }

    private void move(Entity e, int direction){

        boolean flag = false;

        Entity oldPrev = e.getPrevEnt();
        int oldX = e.getX(), oldY = e.getY();


//        if(e instanceof King){
//            flag = true;
//        }

        e.move(direction, flag);


        //This section uses recursion and move checking
        // to make sure the team got a turn.
        try {
            if(!(getEntity(e.getX(), e.getY()) instanceof Border)) {
                if ((getEntity(e.getX(), e.getY()) instanceof OpenSpace)) {

                    insert(e, e.getX(), e.getY());
                    e.setSymbol(e.symbols());

                    insert(oldPrev, oldX, oldY);

                    cycles++;
                    update();

                }else if ((e.getTeam() != (getEntity(e.getX(), e.getY()).getTeam())) ) {

                    //Checks if pawn trying to attack straight
                    if(!(e instanceof Pawn) || (e.getAttackDir() != 90 && e.getAttackDir() != 270)) {
                        kill(e, e.getX(), e.getY());
                        e.setSymbol(e.symbols());

                        insert(oldPrev, oldX, oldY);

                        cycles++;
                        update();

                    }else{
                        put(e, oldX, oldY);
                        run();
                    }
                }else{
                    put(e, oldX, oldY);
                    run();
                }

            }else {
                put(e, oldX, oldY);
                run();
            }
        }catch(Exception ex){
            put(e, oldX, oldY);
            run();
        }
    }

    void addPlayer(Entity e){

        teams.get(e.getTeam()).add(e);
        int x = e.getX(), y = e.getY();

        e.setPrevEnt(getEntity(x, y));
        grid.get(y).remove(x);
        grid.get(y).add(x, e);
    }

    private void insert(Entity e, int x, int y){
        e.setxLoc(x);
        e.setyLoc(y);


        e.setPrevEnt(getEntity(x, y));


        grid.get(y).remove(x);
        grid.get(y).add(x, e);
    }

    private void kill(Entity e, int x, int y){
        e.setxLoc(x);
        e.setyLoc(y);

        if(getEntity(x,y) instanceof King){
            String winner = (cycles%2 == 0) ? "White" : "Dark";
            System.out.println("King is dead.\n" + winner + " team wins.");
            board.kill();


        }

        teams.get(getEntity(x,y).getTeam()).remove(getEntity(x,y));

        e.setPrevEnt(getEntity(x, y).getPrevEnt());


        grid.get(y).remove(x);
        grid.get(y).add(x, e);
    }

    private void put(Entity e, int x, int y){
        e.setxLoc(x);
        e.setyLoc(y);
    }

    private void makeGrid(){

        teams = new ArrayList<>();
        grid = new ArrayList<>();

        teams.add(new ArrayList<>());
        teams.add(new ArrayList<>());

        char c;
        int maxRow = rows-1, maxCol = cols-1;

        for(int x = 0; x < rows; x++){

            grid.add(new ArrayList<>());

            for(int y = 0; y < cols; y++){


                if(x == 0) {
                    if(y == 0) {
                        grid.get(x).add(new Border('!'));

                    }else if(y == maxCol) {
                        grid.get(x).add(new Border('/'));

                    }else{
                        switch(y){
                            case 1:
                                grid.get(x).add(new Border('à'));
                                break;
                            case 2:
                                grid.get(x).add(new Border('á'));
                                break;
                            case 3:
                                grid.get(x).add(new Border('â'));
                                break;
                            case 4:
                                grid.get(x).add(new Border('ã'));
                                break;
                            case 5:
                                grid.get(x).add(new Border('ä'));
                                break;
                            case 6:
                                grid.get(x).add(new Border('å'));
                                break;
                            case 7:
                                grid.get(x).add(new Border('æ'));
                                break;
                            case 8:
                                grid.get(x).add(new Border('ç'));
                                break;
                            default:
                                grid.get(x).add(new Border('$'));

                        }

                    }


                }else if(x == maxRow) {

                    if(y == 0) {
                        grid.get(x).add(new Border('#'));

                    }else if(y == maxCol) {
                        grid.get(x).add(new Border(')'));

                    }else{
                        grid.get(x).add(new Border('%'));
                    }

                }else {

                    if(y == 0) {
                        grid.get(x).add(new Border('"'));

                    }else if(y == maxCol) {
                        grid.get(x).add(new Border('('));

                    }else{

                        if ((y + x) % 2 == 0) {
                            c = ' ';
                        } else {
                            c = '+';
                        }
                        grid.get(x).add(new OpenSpace(c));
                    }

                }

            }
            //outside inner loop

        }



    }

    private Entity getEntity(int x, int y){
        return grid.get(y).get(x);
    }

    private char[][] getCharGrid(){
        char[][] field = new char[rows][cols];

        for(int x = 0; x < rows; x++){

            for(int y = 0; y < cols; y++){
                field[x][y] = getEntity(y,x).getSymbol();
            }
        }

        return field;
    }

    int getRows(){
        return rows;
    }

    int getCols(){
        return cols;
    }
}
