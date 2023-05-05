public class Renderer {

    public void renderGame(World world) {
        for (int y=0; y<20; y++) {
            for (int x=0; x<20; x++) {
                if (world.isWall(x,y))
                    System.out.print("#");
                else
                    System.out.print(" ");
            }
            System.out.println();
        }
    }
}
