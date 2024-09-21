package com.fintech.algotrading.logging;

public class ConsoleLogger implements ILogger {

    @Override
    public void logInformation(String message) {
        System.out.println("[INFO] " + message);
    }

    @Override
    public void logWarning(String message) {
        System.out.println("[WARNING] " + message);
    }

    @Override
    public void logError(String message) {
        System.out.println("[ERROR] " + message);
    }

    public void logCritical(String message) {
        System.out.println("[CRITICAL] " + message);
    }

    // Abstract log method from AbstractLogger can be implemented if needed
    public void log(LogLevel level, String module, String message) {
        switch (level) {
            case DEBUG:
                System.out.println("[DEBUG] [" + module + "] " + message);
                break;
            case INFORMATION:
                System.out.println("[INFO] [" + module + "] " + message);
                break;
            case WARNING:
                System.out.println("[WARNING] [" + module + "] " + message);
                break;
            case ERROR:
                System.out.println("[ERROR] [" + module + "] " + message);
                break;
        }
    }

	@Override
	public void debug(String module, String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void debug(String module, Exception exception) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void information(String module, String message) {
		// TODO Auto-generated method stub
	}

	@Override
	public void information(String module, Exception exception) {
		// TODO Auto-generated method stub
	}

	@Override
	public void warning(String module, String message) {
		// TODO Auto-generated method stub
	}

	@Override
	public void warning(String module, Exception exception) {
		// TODO Auto-generated method stub
	}

	@Override
	public void error(String module, String message) {
		// TODO Auto-generated method stub
	}

	@Override
	public void error(String module, Exception exception) {
		// TODO Auto-generated method stub
	}
}