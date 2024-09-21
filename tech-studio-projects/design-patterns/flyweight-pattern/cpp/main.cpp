#include <iostream>
#include <string>
#include <unordered_map>

// Flyweight interface
class TreeType {
public:
    virtual void render(int x, int y) = 0;
};

// Concrete flyweight
class TreeTypeImpl : public TreeType {
private:
    std::string name;
    std::string color;
    std::string texture;

public:
    TreeTypeImpl(const std::string& name, const std::string& color, const std::string& texture)
        : name(name), color(color), texture(texture) {}

    void render(int x, int y) override {
        std::cout << "Render " << name << " tree at (" << x << ", " << y << ") with color " << color << " and texture " << texture << std::endl;
    }
};

// Flyweight factory
class TreeFactory {
private:
    std::unordered_map<std::string, TreeType*> treeTypes;

public:
    ~TreeFactory() {
        for (auto& entry : treeTypes) {
            delete entry.second; // Clean up allocated TreeType objects
        }
    }

    TreeType* getTreeType(const std::string& name, const std::string& color, const std::string& texture) {
        std::string key = name + "_" + color + "_" + texture;
        if (treeTypes.find(key) == treeTypes.end()) {
            treeTypes[key] = new TreeTypeImpl(name, color, texture);
        }
        return treeTypes[key];
    }
};

// Context
class Tree {
private:
    int x;
    int y;
    TreeType* treeType;

public:
    Tree(int x, int y, TreeType* treeType) : x(x), y(y), treeType(treeType) {}

    void render() {
        treeType->render(x, y);
    }
};

// Client code
int main() {
    TreeFactory treeFactory;
    Tree* trees[3];

    trees[0] = new Tree(1, 2, treeFactory.getTreeType("Pine", "Green", "Thick"));
    trees[1] = new Tree(3, 4, treeFactory.getTreeType("Oak", "Brown", "Thin"));
    trees[2] = new Tree(5, 6, treeFactory.getTreeType("Pine", "Green", "Thick"));

    for (int i = 0; i < 3; ++i) {
        trees[i]->render();
        delete trees[i];
    }

    return 0;
}
