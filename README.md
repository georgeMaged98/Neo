# Neo

This is a project for AI course in semester 9 at the German university in Cairo. The project aims at solving a search-based problem using various search algorithms.

### Problem Description
There is a city that is a 2D-matrix and there is Neo which is a search-based agent which aims at rescuing citizens. 
There are multiple citizens whose health decreases every time step and also some bad agents whom Neo should kill to achieve the mission and rescue the citizens.

This is a search-based problem in which Neo should try rescuing the citizens and the output is either the detailed steps Neo should perform to rescue them if possible.

## Approach:
Since this is a search-based problem, various search techniques are used. 
The search algorithms used are:
* Breadth First Search: The data structure used is a queue which follows the FIFO
  approach in inserting the nodes.
* Depth First Search: The data structure used is a stack which follows the LIFO
  approach in inserting the nodes.
* Iterative Deepening Search: The data structure used is a stack which uses LIFO approach and
  it also considers the current level. If the max level is reached, no new
  nodes are added to the stack. For each node, a normal DF is used till
  the stack is empty. Then we increase the level by 1, insert the root
  node and run DF search with updated depth level.
* Greedy Search: The data structure used is a priority queue which inserts the
  nodes such that the node with the least number of expected deaths shall
  be inserted first. That’s the first heuristic h1(nBF).
* A* search: The data structure used is a priority queue which inserts the
  nodes such that the node with the minimum number of actual deaths
  and kills from the root g(n) and minimum number of expected deaths
  shall be inserted first. That's the number of deaths and kills from root
  (actual cost) g(n) and the expected number of deaths till a goal state
  is reached h(n).