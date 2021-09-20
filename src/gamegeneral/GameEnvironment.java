package gamegeneral;

import gameobjects.Collidable;
import geometry.Line;
import geometry.Rectangle;

import java.util.ArrayList;
import java.util.List;

// ID: 316163922
/**
 * @author Tomer Pardilov
 * collection of objects a gameObjects.Ball can collide with.
 */
public class GameEnvironment {
    //fields
    private List<Collidable> collidableList;

    /**
     * constructor.
     */
    public GameEnvironment() {
        this.collidableList = new ArrayList<>();
    }

    /**
     * addCollidable method -- add the given collidable to the environment.
     * @param c stands for the collidable we want to add to the list.
     */
    public void addCollidable(Collidable c) {
        collidableList.add(c);
    }

    /**
     * addCollidable method -- Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     * @param trajectory stands for the line that is intersecting with object.
     * @return the information about the closest collision that is going to occur.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Rectangle rec = collidableList.get(0).getCollisionRectangle();
        CollisionInfo minima = new CollisionInfo(trajectory.closestIntersectionToStartOfLine(rec),
                collidableList.get(0));

        for (Collidable col : collidableList) {
            rec = col.getCollisionRectangle();
            if (minima.collisionPoint() == null) {
                minima = new CollisionInfo(trajectory.closestIntersectionToStartOfLine(rec), col);
                continue;
            }

            CollisionInfo newInfo = new CollisionInfo(trajectory.closestIntersectionToStartOfLine(rec),
                    col);
            if (newInfo.collisionPoint() == null) {
                continue;
            }
            if (newInfo.collisionPoint().distance(trajectory.start())
                    < minima.collisionPoint().distance(trajectory.start())) {
                minima = newInfo;
            }
        }
        return minima;
    }

    /**
     * accessor.
     * @return gameObjects.Collidable List.
     */
    public List<Collidable> getCollidableList() {
        return this.collidableList;
    }
    /**
     * removeCollidable method -- used to remove object from collidable list.
     * @param c stands for collidable.
     */
    public void removeCollidable(Collidable c) {
        collidableList.remove(c);
    }
}