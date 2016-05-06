/**
 * Created by Austin on 3/27/2016.
 */
class Queen extends Entity{

    Queen(){
        super("Queen", 'q');
    }

    Queen(int dim[], int t){
        super("Queen", 'q');

        setTeam(t);

        if(getTeam() == 0){
            setxLoc(dim[0] - 2);
            setyLoc(dim[1] / 2 - 1);
        }else if(getTeam() == 1){
            setxLoc(1);
            setyLoc(dim[1] / 2 - 1);
        }


        setSymbol(symbols());
    }

    Queen(int x, int y, int t){
        super("Queen", 'q');

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

    @Override
    char symbols(){
        if(getTeam() == 0) {

            if ((xLoc + yLoc) % 2 == 0) {
                return 'q';
            } else {
                return 'Q';
            }

        }else{

            if ((xLoc + yLoc) % 2 == 0) {
                return 'w';
            } else {
                return 'W';
            }

        }
    }
}
