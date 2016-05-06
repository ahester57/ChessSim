import java.util.ArrayList;

/**
 * Created by Austin on 3/27/2016.
 *
 * King is currently able to target enemies
 */

class King extends Entity{

    King(){
        super("King", 'k');

    }

    King(int dim[], int t){
        super("King", 'k');

        setTeam(t);

        if(getTeam() == 0){
            setxLoc(dim[0] - 2);
            setyLoc(dim[1] / 2);
        }else if(getTeam() == 1){
            setxLoc(1);
            setyLoc(dim[1] / 2);
        }


        setSymbol(symbols());
    }

    King(int x, int y, int t){
        super("King", 'k');

        setTeam(t);

        setxLoc(x);
        setyLoc(y);

        setSymbol(symbols());
    }

    @Override
    void move(int dir, boolean stay){
        if (stay) return;

        switch(dir){
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

    boolean canAttack(ArrayList<ArrayList<Entity>> grid){
        boolean flag = false;
        Entity temp;

        for(int i = 0; i < grid.size(); i++){

            for(int k = 0; k < grid.get(0).size(); k++){
                temp = grid.get(i).get(k);
                if(!(temp instanceof Border) && !(temp instanceof OpenSpace)){

                    if(temp.getTeam() != this.getTeam()){

                        flag = true;

                        switch(i){
                            case 0:
                                switch(k){
                                    case 0:
                                        setAttackDir(45);
                                        break;
                                    case 1:
                                        setAttackDir(90);
                                        break;
                                    case 2:
                                        setAttackDir(135);
                                        break;
                                }
                                break;
                            case 1:
                                switch(k){
                                    case 0:
                                        setAttackDir(0);
                                        break;
                                    case 1:
                                        setAttackDir(90);
                                        break;
                                    case 2:
                                        setAttackDir(180);
                                        break;
                                }
                                break;
                            case 2:
                                switch(k){
                                    case 0:
                                        setAttackDir(315);
                                        break;
                                    case 1:
                                        setAttackDir(270);
                                        break;
                                    case 2:
                                        setAttackDir(225);
                                        break;
                                }
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
                return 'k';
            }else{
                return 'K';
            }

        }else{

            if ((xLoc + yLoc) % 2 == 0) {
                return 'l';
            } else {
                return 'L';
            }

        }
    }


}
