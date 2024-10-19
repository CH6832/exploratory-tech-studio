package src.helpers; // Update the package name as necessary

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ErrorHandler {

    private static final Logger logger = Logger.getLogger(ErrorHandler.class.getName());

    public static class BaseCustomException extends Exception {
        public BaseCustomException(String message) {
            super(message);
            logger.severe("Error: " + message);
        }
    }

    public static class FileNotFoundError extends BaseCustomException {
        public FileNotFoundError(String filepath, String message) {
            super(message + ": " + filepath);
            logger.severe("FileNotFoundError: " + message + " at " + filepath);
        }
    }

    public static class ValidationError extends BaseCustomException {
        public ValidationError(String field, String message) {
            super(message + ": " + field);
            logger.severe("ValidationError: " + message + " in field " + field);
        }
    }

    public static class InvalidFileFormatError extends BaseCustomException {
        public InvalidFileFormatError(String fileFormat, String message) {
            super(message + ": " + fileFormat);
            logger.severe("InvalidFileFormatError: " + message + " for format " + fileFormat);
        }
    }

    public static <T, R> Function<T, R> handleError(Function<T, R> func) {
        return arg -> {
            try {
                return func.apply(arg);
            } catch (BaseCustomException e) {
                logger.severe("Custom error caught: " + e.getMessage());
                throw e;
            } catch (Exception e) {
                logger.severe("Unhandled exception: " + e.getMessage());
                throw e;
            }
        };
    }

    // Example usage
    public static void main(String[] args) {
        Function<Integer, Double> divide = handleError(a -> {
            int b = 0; // This will simulate a division by zero for demonstration
            if (b == 0) {
                throw new ValidationError("b", "Division by zero is not allowed");
            }
            return (double) a / b;
        });

        try {
            double result = divide.apply(10);
            System.out.println("Result: " + result);
        } catch (Exception e) {
            logger.severe("An error occurred: " + e.getMessage());
        }
    }
}
