/**
 * Created by Austin on 3/27/2016.
 */

class Bishop extends Entity{



    private static int numBishops = 0;

    Bishop(int dim[], int t){
        super("Bishop", 'b');


        setTeam(t);

        if(getTeam() == 0){
            setxLoc(dim[0] - 2);

            if(numBishops % 2 == 0) {
                setyLoc(dim[1] / 2 - 2);
            }else{
                setyLoc(dim[1] / 2 + 1);
            }

        }else if(getTeam() == 1){
            setxLoc(1);

            if(numBishops % 2 == 0) {
                setyLoc(dim[1] / 2 - 2);
            }else{
                setyLoc(dim[1] / 2 + 1);
            }
        }

        numBishops++;
        setSymbol(symbols());
    }

    @Override
    void move(int dir, boolean stay){
        if (stay) return;

        switch(dir){
            case 0:
                xLoc--;
                yLoc--;
                break;
            case 45:
                xLoc--;
                yLoc--;
                break;
            case 90:
                xLoc--;
                yLoc++;
                break;
            case 135:
                xLoc--;
                yLoc++;
                break;
            case 180:
                xLoc++;
                yLoc++;
                break;
            case 225:
                xLoc++;
                yLoc++;
                break;
            case 270:
                xLoc++;
                yLoc--;
                break;
            case 315:
                xLoc++;
                yLoc--;
                break;
            default:
                xLoc++;
                yLoc--;

        }

    }

    @Override
    char symbols(){
        if(getTeam() == 0) {

            if ((xLoc + yLoc) % 2 == 0) {
                return 'b';
            } else {
                return 'B';
            }

        }else{

            if ((xLoc + yLoc) % 2 == 0) {
                return 'v';
            } else {
                return 'V';
            }

        }
    }
}
