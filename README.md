# Maze-Generation-Solving-and-Visualization
## **Cell**
The Cell class contains the information that defines each cell on the grid used to make the maze.  
## **MazeFrame**
The project runs from the Maze Frame the elements N and M handle the scale of the maze and can be change to increase or decrease the size of the overall maze.  
### **MazeGenerateDepthFirst**
Implement a maze generation algorithm Randomized Depth-First
Search with Backtracking.
Starts from an initial cell and carves passages by knocking down
walls between neighboring cells until every cell in the grid has been visited. Using a stack to help with the process of backtracking.  
### **MazeSolvebfs**
Implemented a pathfinding algorithm to solve the generated maze from a start
point using a queue with the points being the top-left to the exit point bottom-right, while respecting the walls of the maze.  
## **MazeView**
MazeView handles the creation of the GUI element of the maze to make the maze visually cohesive, a slight delay was put on the visual aspect of the maze solver to showcase its routh through the maze.  