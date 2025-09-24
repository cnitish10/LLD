package org.example.hackathonplatform.model;

import org.example.hackathonplatform.enums.ProblemDifficulty;

import java.util.List;

public class Problem {
    private int problemId;
    private String problemName;
    private ProblemDifficulty difficulty;
    private List<String> tagsList;
    private int maxScore;

    public Problem(int problemId, String problemName, ProblemDifficulty difficulty, List<String> tagsList, int maxScore) {
        this.problemId = problemId;
        this.problemName = problemName;
        this.difficulty = difficulty;
        this.tagsList = tagsList;
        this.maxScore = maxScore;
    }

    public int getProblemId() { return problemId; }
    public String getProblemName() { return problemName; }
    public ProblemDifficulty getDifficulty() { return difficulty; }
    public List<String> getTagsList() { return tagsList; }
    public int getMaxScore() { return maxScore; }

    @Override
    public String toString() {
        return "Problem{" + "id=" + problemId + ", name='" + problemName + '\'' + ", difficulty=" + difficulty + ", maxScore=" + maxScore + '}';
    }
}
