package org.example.hackathonplatform.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hackathon {
    private final int hackathonId;
    private final String hackathonName;
    private final int startTime;
    private final int endTime;
    private final List<Problem> problems;
    private final LeaderBoard leaderBoard;

    public Hackathon(int hackathonId, String hackathonName, int startTime, int endTime, List<Problem> problems) {
        this.hackathonId = hackathonId;
        this.hackathonName = hackathonName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.problems = new ArrayList<>(problems);
        this.leaderBoard = new LeaderBoard();
    }

    public int getHackathonId() { return hackathonId; }
    public String getHackathonName() { return hackathonName; }
    public int getStartTime() { return startTime; }
    public int getEndTime() { return endTime; }
    public List<Problem> getAllProblems() { return Collections.unmodifiableList(problems); }
    public LeaderBoard getLeaderBoard() { return leaderBoard; }
}
