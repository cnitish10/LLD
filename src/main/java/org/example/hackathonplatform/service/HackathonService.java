package org.example.hackathonplatform.service;

import org.example.hackathonplatform.model.Hackathon;
import org.example.hackathonplatform.model.Participant;
import org.example.hackathonplatform.model.Submission;

import java.util.*;

public class HackathonService {
    private final Map<Integer, Hackathon> hackathons = new HashMap<>();
    private final Map<Integer, Set<Participant>> participantsInHackathon = new HashMap<>();
    private final Map<Integer, Submission> submissionHashMap = new HashMap<>();

    public void addHackathon(Hackathon hackathon) {
        hackathons.putIfAbsent(hackathon.getHackathonId(), hackathon);
    }

    public Hackathon getHackathonById(int hackathonId) {
        return hackathons.get(hackathonId);
    }

    public void addParticipant(Participant participant, int hackathonId) {
        Hackathon hackathon = hackathons.get(hackathonId);
        if (hackathon == null) {
            System.out.println("Hackathon does not exist");
            return;
        }
        participantsInHackathon.putIfAbsent(hackathonId, new HashSet<>());
        Set<Participant> participants = participantsInHackathon.get(hackathonId);
        if (!participants.add(participant)) {
            System.out.println(participant.getParticipantName() + " already registered.");
        } else {
            participant.registerHackathon(hackathon);
            System.out.println(participant.getParticipantName() + " registered successfully!");
        }
    }

    public void submitSolution(Submission submission, int hackathonId) {
        Hackathon hackathon = hackathons.get(hackathonId);
        if (hackathon == null) {
            System.out.println("Hackathon not found!");
            return;
        }

        if (submission.getSubmissionTime() < hackathon.getStartTime() ||
                submission.getSubmissionTime() > hackathon.getEndTime()) {
            System.out.println("Submission rejected: outside hackathon time!");
            return;
        }

        // Save submission
        submissionHashMap.put(submission.getSubmissionId(), submission);

        // Update leaderboard
        Set<Participant> participants = participantsInHackathon.get(hackathonId);
        Participant participant = participants.stream()
                .filter(p -> p.getParticipantId() == submission.getParticipantId())
                .findFirst()
                .orElse(null);

        if (participant == null) {
            System.out.println("Participant not registered in this hackathon!");
            return;
        }

        participant.addSubmission(submission);
        hackathon.getLeaderBoard().updateScore(participant, submission);
    }
}
