import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class WorldTest {

    @Test
    void isWorldCreatedCorrectly(){
        World world = World.getInstance();
        world.createWorld();
        int wallCount = 0;
        int emptyCount = 0;
        int blockCount = 0;
        for(int i = 0;  i < 20; i++){
            for(int j = 0; j < 20; j++){
                if(world.isEmpty(i, j)) {
                    emptyCount++;
                }
                else if(world.isWall(i,j)) {
                    wallCount++;
                }
                else if(world.isTetrominoe(i,j)){
                    blockCount++;
                }
            }
        }
        assertEquals(emptyCount, 18*18);
        assertEquals(blockCount, 0);
        assertEquals(wallCount, 2*20+2*18);
    }

    @Test
    void isTetrominoeOnTop(){
        World world = World.getInstance();
        world.createWorld();
        world.addNewTetrominoe();
        boolean isOnTop = false;
        for(int i = 0;  i < 20; i++){
            if(world.isTetrominoe(1, i)){
                isOnTop = true;
            }
        }
        assertTrue(isOnTop);
    }

    @Test
    void areAllTetrominoesBeingSpawned(){
        World world = World.getInstance();
        world.createWorld();
        Random rand = new Random();
        Set<String> arr = new HashSet<String>();
        int TetrominoeTypes = 7;
       for(int i = 0; i < 50; i++){
           int shapeType = rand.nextInt(TetrominoeTypes);
           TetrominoeTypeNames[] tetrominoeTypeNames = TetrominoeTypeNames.values();
           TetrominoeTypeNames tetrominoeTypeNames1 = tetrominoeTypeNames[shapeType];
           arr.add(tetrominoeTypeNames1.toString());
        }
        assertEquals(arr.size(), TetrominoeTypes);
    }
}