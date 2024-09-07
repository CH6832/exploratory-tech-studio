#ifndef RAFT_H
#define RAFT_H

#include <vector>
#include <memory>
#include <thread>
#include <mutex>
#include <condition_variable>
#include <chrono>
#include <atomic>

class RaftServer;

enum class State {
    Follower,
    Candidate,
    Leader
};

class RaftServer {
public:
    RaftServer(int id, int totalServers);
    void start();
    void stop();
    void onTimeout();
    void onHeartbeat();
    void onRequestVote(int candidateId, int term);
    void onAppendEntries(int leaderId, int term, const std::vector<int>& entries);
    void becomeLeader();
    void becomeFollower();
    void becomeCandidate();
    
private:
    int id;
    int term;
    int votedFor;
    State state;
    int leaderId;
    std::vector<int> log;
    std::vector<std::shared_ptr<RaftServer>> peers;

    std::mutex mtx;
    std::condition_variable cv;
    std::atomic<bool> running;
    std::thread electionThread;
    
    void electionRoutine();
    void sendHeartbeat();
    void requestVotes();
    void replicateLog();
};

#endif // RAFT_H
