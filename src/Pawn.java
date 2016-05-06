import java.util.ArrayList;

/**
 * Created by Austin on 3/27/2016.
 *
 * Pawn is currently able to target enemies.
 */

class Pawn extends Entity{

    private static int numPawns = 0;


    Pawn(int dim[], int t){
        super("Pawn", 'p');


        setTeam(t);

        if(getTeam() == 0){
            setxLoc(dim[0] - 3);

            setyLoc(dim[1] / 2 - 4 + (numPawns % 8));


        }else if(getTeam() == 1){
            setxLoc(2);

            setyLoc(dim[1] / 2 - 4 + (numPawns % 8));
        }

        numPawns++;
        setSymbol(symbols());
    }

    @Override
    void move(int dir, boolean stay){
        if (stay) return;

//        switch(getTeam()){
//            case 0:
//                xLoc--;
//                break;
//            case 1:
//                xLoc++;
//                break;
//            default:
//                xLoc--;
//
//        }

        switch(getAttackDir()){
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
        }


    }

    boolean canAttack(ArrayList<ArrayList<Entity>> grid){
        boolean flag = false;
        Entity temp;



        if(getTeam() == 0){
            setAttackDir(90);
            for(int k = 0; k < grid.get(0).size(); k+=2) {
                temp = grid.get(0).get(k);
                if (!(temp instanceof Border) && !(temp instanceof OpenSpace)) {

                    if (temp.getTeam() != this.getTeam()) {

                        flag = true;


                        switch (k) {
                            case 0:
                                setAttackDir(45);
                                break;
                            case 2:
                                setAttackDir(135);
                                break;
                        }
                    }
                }
            }

        }else if(getTeam() == 1){
            setAttackDir(270);
            for(int k = 0; k < grid.get(0).size(); k+=2) {
                temp = grid.get(2).get(k);
                if (!(temp instanceof Border) && !(temp instanceof OpenSpace)) {

                    if (temp.getTeam() != this.getTeam()) {

                        flag = true;


                        switch (k) {
                            case 0:
                                setAttackDir(315);
                                break;
                            case 2:
                                setAttackDir(225);
                                break;
                        }
                    }
                }
            }
        }


        return flag;
    }



    @Override
    char symbols(){
        if(getTeam() == 0) {

            if((xLoc + yLoc) % 2 == 0){
                return 'p';
            }else{
                return 'P';
            }

        }else{

            if ((xLoc + yLoc) % 2 == 0) {
                return 'o';
            } else {
                return 'O';
            }

        }
    }
}
