package org.example.hackathonplatform.service;

import org.example.hackathonplatform.model.Hackathon;
import org.example.hackathonplatform.model.Participant;

import java.util.HashMap;

public class ParticipantService {
    private final HashMap<Integer, Participant> participants;
    private final HackathonService hackathonService;

    public ParticipantService(HackathonService hackathonService) {
        this.participants = new HashMap<>();
        this.hackathonService = hackathonService;
    }

    public void registerForHackathon(Participant participant, int hackathonId) {
        Hackathon hackathon = hackathonService.getHackathonById(hackathonId);
        if (hackathon == null) {
            System.out.println("Hackathon does not exist");
            return;
        }

        participants.putIfAbsent(participant.getParticipantId(), participant);
        hackathonService.addParticipant(participant, hackathonId);
    }

    public Participant getParticipantById(int participantId) {
        return participants.get(participantId);
    }
}
