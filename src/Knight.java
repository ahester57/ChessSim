/**
 * Created by Austin on 3/27/2016.
 */

class Knight extends Entity{

    private static int numKnights = 0;

    Knight(int dim[], int t){
        super("Knight", 'n');


        setTeam(t);

        if(getTeam() == 0){
            setxLoc(dim[0] - 2);

            if(numKnights % 2 == 0) {
                setyLoc(dim[1] / 2 - 3);
            }else{
                setyLoc(dim[1] / 2 + 2);
            }

        }else if(getTeam() == 1){
            setxLoc(1);

            if(numKnights % 2 == 0) {
                setyLoc(dim[1] / 2 - 3);
            }else{
                setyLoc(dim[1] / 2 + 2);
            }
        }

        numKnights++;
        setSymbol(symbols());
    }

    @Override
    void move(int dir, boolean stay){
        if (stay) return;

        switch(dir){
            case 0:
                xLoc--;
                yLoc-=2;
                break;
            case 45:
                xLoc-=2;
                yLoc--;
                break;
            case 90:
                xLoc-=2;
                yLoc++;
                break;
            case 135:
                xLoc--;
                yLoc+=2;
                break;
            case 180:
                xLoc++;
                yLoc+=2;
                break;
            case 225:
                xLoc+=2;
                yLoc++;
                break;
            case 270:
                xLoc+=2;
                yLoc--;
                break;
            case 315:
                xLoc++;
                yLoc-=2;
                break;
            default:
                xLoc--;
                yLoc-=2;

        }

    }

    @Override
    char symbols(){
        if(getTeam() == 0) {

            if ((xLoc + yLoc) % 2 == 0) {
                return 'n';
            } else {
                return 'N';
            }

        }else{

            if ((xLoc + yLoc) % 2 == 0) {
                return 'm';
            } else {
                return 'M';
            }

        }
    }
}
