package org.example.hackathonplatform;

import org.example.hackathonplatform.enums.ProblemDifficulty;
import org.example.hackathonplatform.model.*;
import org.example.hackathonplatform.service.HackathonService;
import org.example.hackathonplatform.service.ParticipantService;

import java.util.Arrays;

public class HackathonPlatformApp {
    public static void main(String[] args) {
        // Create problems
        Problem problem1 = new Problem(1, "TwoSum", ProblemDifficulty.EASY, Arrays.asList("Array", "Sorting"), 20);
        Problem problem2 = new Problem(2, "CoinChange", ProblemDifficulty.MEDIUM, Arrays.asList("DP"), 50);

        // Create hackathon
        Hackathon hackathon1 = new Hackathon(1, "H1", 100, 200, Arrays.asList(problem1, problem2));

        HackathonService hackathonService = new HackathonService();
        hackathonService.addHackathon(hackathon1);

        // Create participants
        Participant participant1 = new Participant(1, "Nitish");
        Participant participant2 = new Participant(2, "Tarun");

        ParticipantService participantService = new ParticipantService(hackathonService);
        participantService.registerForHackathon(participant1, 1);
        participantService.registerForHackathon(participant2, 1);

        // Submissions
        hackathonService.submitSolution(new Submission(1, 1, 1, 15, 120), 1);
        hackathonService.submitSolution(new Submission(2, 1, 2, 50, 130), 1);
        hackathonService.submitSolution(new Submission(3, 2, 1, 20, 125), 1);

        // Print Leaderboard
        hackathon1.getLeaderBoard().printLeaderBoard();
    }
}
