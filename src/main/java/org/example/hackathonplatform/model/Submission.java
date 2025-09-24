package org.example.hackathonplatform.model;

public class Submission {
    private int submissionId;
    private int participantId;
    private int problemId;
    private int score;
    private int submissionTime;

    public Submission(int submissionId, int participantId, int problemId, int score, int submissionTime) {
        this.submissionId = submissionId;
        this.participantId = participantId;
        this.problemId = problemId;
        this.score = score;
        this.submissionTime = submissionTime;
    }

    public int getSubmissionId() { return submissionId; }
    public int getParticipantId() { return participantId; }
    public int getProblemId() { return problemId; }
    public int getScore() { return score; }
    public int getSubmissionTime() { return submissionTime; }

    @Override
    public String toString() {
        return "Submission{" + "id=" + submissionId + ", participantId=" + participantId + ", problemId=" + problemId + ", score=" + score + ", time=" + submissionTime + '}';
    }
}
