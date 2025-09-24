package org.example.hackathonplatform.model;

import java.util.*;

public class LeaderBoard {
    // Participant -> ProblemId -> BestScore
    private final Map<Participant, Map<Integer, Integer>> participantScores = new HashMap<>();

    public void updateScore(Participant participant, Submission submission) {
        participantScores.putIfAbsent(participant, new HashMap<>());
        Map<Integer, Integer> scores = participantScores.get(participant);

        int prevBest = scores.getOrDefault(submission.getProblemId(), 0);
        if (submission.getScore() > prevBest) {
            scores.put(submission.getProblemId(), submission.getScore());
        }
    }

    public int getTotalScore(Participant participant) {
        return participantScores.getOrDefault(participant, Collections.emptyMap())
                .values()
                .stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    public void printLeaderBoard() {
        List<Map.Entry<Participant, Integer>> rankingList = new ArrayList<>();
        for (Participant p : participantScores.keySet()) {
            rankingList.add(new AbstractMap.SimpleEntry<>(p, getTotalScore(p)));
        }
        rankingList.sort((a, b) -> b.getValue() - a.getValue());
        int rank = 1;
        for (Map.Entry<Participant, Integer> entry : rankingList) {
            System.out.println(rank + ". " + entry.getKey().getParticipantName() + " -> " + entry.getValue());
            rank++;
        }
    }
}
