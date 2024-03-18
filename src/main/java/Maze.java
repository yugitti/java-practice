import java.io.IOException;


public class Maze {

    public static void run(Position start, int [][]map) throws IOException {
        System.out.println("######### Maze Class ############");
        Position current = start;

        var goal = new Position(4,3);
//        var goalFlag = false;
//        while(!goalFlag){
        for(;;){
            displayMap(map, current, goal);
            var next = keyInputAndNext(current);
            if(isGoal(next, goal)){
                break;
            }
            current = stepToNextOrStay(current, next, map);

        }

        System.out.println("FINISH");

    }

    static private void displayMap(int [][]map, Position current, Position goal){
        for(int y=0; y<map.length; y++){
            for(int x=0; x<map[0].length; x++){
                if(x == current.x() && y == current.y()){
                    System.out.print("o");
                }else if(x== goal.x() && y == goal.y()){
                    System.out.print("G");
                }else if(map[y][x] == 1){
                    System.out.print("*");
                }else{
                    System.out.print(".");
                }
            }
            System.out.println();
        }
    }

    static private Position keyInputAndNext(Position current) throws IOException{
        int ch = System.in.read();

        var next = switch(ch){
            case 'a' -> new Position(current.x() - 1, current.y());
            case 's' -> new Position(current.x() + 1, current.y());
            case 'w' -> new Position(current.x(), current.y()-1);
            case 'z' -> new Position(current.x(), current.y()+1);
            default -> current;
        };
        System.in.read();

        return next;
    }

    static private boolean isGoal(Position next, Position goal){
        return next.x() == goal.x() && next.y() == goal.y();
    }
    static private Position stepToNextOrStay(Position current, Position next, int [][]map){
        int mapSizeX = map[0].length;
        int mapSizeY = map.length;
        if(next.x() >= mapSizeX || next.y() >= mapSizeY ){
            System.out.println("not allow this direction");
            return current;
        }else if(map[next.y()][next.x()] == 1){
            System.out.println("can't go to this direction");
            return current;
        }else{
            return next;
        }
    }


}
