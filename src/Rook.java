/**
 * Created by Austin on 3/27/2016.
 */
class Rook extends Entity{

    private static int numRooks = 0;

    Rook(int dim[], int t){
        super("Rook", 'r');


        setTeam(t);

        if(getTeam() == 0){
            setxLoc(dim[0] - 2);

            if(numRooks % 2 == 0) {
                setyLoc(dim[1] / 2 - 4);
            }else{
                setyLoc(dim[1] / 2 + 3);
            }

        }else if(getTeam() == 1){
            setxLoc(1);

            if(numRooks % 2 == 0) {
                setyLoc(dim[1] / 2 - 4);
            }else{
                setyLoc(dim[1] / 2 + 3);
            }
        }

        numRooks++;
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
                yLoc--;
                break;
            case 90:
                xLoc--;
                break;
            case 135:
                xLoc--;
                break;
            case 180:
                yLoc++;
                break;
            case 225:
                yLoc++;
                break;
            case 270:
                xLoc++;
                break;
            case 315:
                xLoc++;
                break;
            default:
                xLoc--;

        }

    }

    @Override
    char symbols(){
        if(getTeam() == 0) {

            if((xLoc + yLoc) % 2 == 0){
                return 'r';
            }else{
                return 'R';
            }

        }else{

            if ((xLoc + yLoc) % 2 == 0) {
                return 't';
            } else {
                return 'T';
            }

        }
    }

}
