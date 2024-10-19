#ifndef HEARTBEATMANAGER_H
#define HEARTBEATMANAGER_H

#include <thread>

class HeartbeatManager {
public:
    HeartbeatManager();
    void start();
    void stop();

private:
    void sendHeartbeats();
    bool running;
};

#endif // HEARTBEATMANAGER_H
