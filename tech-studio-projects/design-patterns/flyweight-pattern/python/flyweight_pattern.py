from typing import Dict


class TreeType:
    def __init__(self, name: str, color: str, texture: str):
        self.name = name
        self.color = color
        self.texture = texture

    def render(self, x: int, y: int) -> None:
        print(f"Render {self.name} tree at ({x}, {y}) with color {self.color} and texture {self.texture}")


class TreeFactory:
    _tree_types: Dict[str, TreeType] = {}

    @staticmethod
    def get_tree_type(name: str, color: str, texture: str) -> TreeType:
        key = f"{name}_{color}_{texture}"
        if key not in TreeFactory._tree_types:
            TreeFactory._tree_types[key] = TreeType(name, color, texture)
        return TreeFactory._tree_types[key]


class Tree:
    def __init__(self, x: int, y: int, tree_type: TreeType):
        self.x = x
        self.y = y
        self.tree_type = tree_type

    def render(self) -> None:
        self.tree_type.render(self.x, self.y)


class Forest:
    def __init__(self):
        self.trees = []

    def plant_tree(self, x: int, y: int, name: str, color: str, texture: str) -> None:
        tree_type = TreeFactory.get_tree_type(name, color, texture)
        tree = Tree(x, y, tree_type)
        self.trees.append(tree)

    def draw(self) -> None:
        for tree in self.trees:
            tree.render()


# Client code
if __name__ == "__main__":
    forest = Forest()
    forest.plant_tree(1, 2, "Pine", "Green", "Thick")
    forest.plant_tree(3, 4, "Oak", "Brown", "Thin")
    forest.plant_tree(5, 6, "Pine", "Green", "Thick")

    forest.draw()
