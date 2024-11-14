#include <iostream>

/**
 * @brief Provides a command-line interface for OS interaction.
 */
class CommandLineInterface {
public:
    void prompt() {
        std::cout << "Type 'exit' to quit." << std::endl;
        std::string input;
        while (true) {
            std::cout << "> ";
            std::getline(std::cin, input);
            if (input == "exit") break;
            std::cout << "You typed: " << input << std::endl;
        }
    }
};
