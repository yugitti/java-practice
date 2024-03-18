import java.util.ArrayDeque;

public class MazeAuto {
    static public void run(Position start, int [][]map){
        traverseFILO(map, 1, 1);
        char []ch = {'.', '*', 'G', 'o'};
        for(int[]row: map){
            for(int cell: row){
                System.out.print(ch[cell]);
            }
            System.out.println();
        }
    }

    static boolean traverse(int[][] map, int curX, int curY){
        switch(map[curY][curX]){
            case 0: break; // it's load!, can continue to next
            case 2: return true; // it's goal!
            default: return false; // it doesn't allow
        }

        map[curY][curX] = 3; // mark up here is already come
        if(traverse(map, curX + 1, curY) ||
                traverse(map, curX - 1, curY) ||
                traverse(map, curX, curY + 1) ||
                traverse(map, curX, curY - 1)
        ){
            return true;
        }
        map[curY][curX] = 0; // it's not successful to reach to the goal, change back to load.
        return false;
    }

    static boolean traverseFIFO(int[][] map, int curX, int curY){
        record Position(int x, int y){};
        var stack = new ArrayDeque<Position>();
        stack.push(new Position(curX, curY));

        for(Position p; (p = stack.pollFirst()) != null; ){
            switch(map[p.y()][p.x()]){
                case 0: break;
                case 2: return true;
                default: continue;
            }
            map[p.y()][p.x()] = 3;
            stack.push(new Position(p.x()+1, p.y()));
            stack.push(new Position(p.x()-1, p.y()));
            stack.push(new Position(p.x(), p.y()+1));
            stack.push(new Position(p.x(), p.y()-1));
        }
        return false;
    }

    static boolean traverseFILO(int[][] map, int curX, int curY){
        record Position(int x, int y){};
        var stack = new ArrayDeque<Position>();
        stack.push(new Position(curX, curY));

        for(Position p; (p = stack.pollLast()) != null; ){
            switch(map[p.y()][p.x()]){
                case 0: break;
                case 2: return true;
                default: continue;
            }
            map[p.y()][p.x()] = 3;
            stack.push(new Position(p.x()+1, p.y()));
            stack.push(new Position(p.x()-1, p.y()));
            stack.push(new Position(p.x(), p.y()+1));
            stack.push(new Position(p.x(), p.y()-1));
        }
        return false;
    }
}
