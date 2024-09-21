import heapq

class Node:
    def __init__(self, position, parent=None):
        self.position = position
        self.parent = parent
        self.g = 0  # Cost from start to this node
        self.h = 0  # Heuristic cost from this node to the goal
        self.f = 0  # Total cost (g + h)

    def __lt__(self, other):
        return self.f < other.f

def astar(grid, start, goal):
    # Create start and goal nodes
    start_node = Node(start)
    goal_node = Node(goal)
    
    open_list = []
    closed_list = set()
    
    # Add start node to the open list
    heapq.heappush(open_list, start_node)
    
    while open_list:
        # Get the node with the lowest f value
        current_node = heapq.heappop(open_list)
        closed_list.add(current_node.position)
        
        # Check if we have reached the goal
        if current_node.position == goal_node.position:
            path = []
            while current_node:
                path.append(current_node.position)
                current_node = current_node.parent
            return path[::-1]  # Return reversed path
        
        # Generate children nodes
        x, y = current_node.position
        neighbors = [(x-1, y), (x+1, y), (x, y-1), (x, y+1)]  # Adjacent cells

        for next_position in neighbors:
            if not (0 <= next_position[0] < len(grid) and 0 <= next_position[1] < len(grid[0])):
                continue
            if grid[next_position[0]][next_position[1]] == 1:  # 1 represents obstacles
                continue
            
            neighbor_node = Node(next_position, current_node)
            
            if neighbor_node.position in closed_list:
                continue
            
            neighbor_node.g = current_node.g + 1
            neighbor_node.h = (goal_node.position[0] - neighbor_node.position[0]) ** 2 + (goal_node.position[1] - neighbor_node.position[1]) ** 2
            neighbor_node.f = neighbor_node.g + neighbor_node.h
            
            if any(open_node.position == neighbor_node.position and open_node.f <= neighbor_node.f for open_node in open_list):
                continue
            
            heapq.heappush(open_list, neighbor_node)
    
    return None  # Return None if no path is found

# Example usage
if __name__ == "__main__":
    grid = [
        [0, 0, 0, 0, 0],
        [0, 1, 1, 1, 0],
        [0, 0, 0, 1, 0],
        [0, 1, 0, 0, 0],
        [0, 0, 0, 0, 0]
    ]

    start = (0, 0)
    goal = (4, 4)
    
    path = astar(grid, start, goal)
    print("Path found:", path)
