package org.example.hackathonplatform.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Participant {
    private final int participantId;
    private final String participantName;
    private final List<Hackathon> registeredHackathons;
    private final List<Submission> submissions;

    public Participant(int participantId, String participantName) {
        this.participantId = participantId;
        this.participantName = participantName;
        this.registeredHackathons = new ArrayList<>();
        this.submissions = new ArrayList<>();
    }

    public int getParticipantId() { return participantId; }
    public String getParticipantName() { return participantName; }
    public List<Hackathon> getRegisteredHackathons() { return Collections.unmodifiableList(registeredHackathons); }
    public List<Submission> getSubmissions() { return Collections.unmodifiableList(submissions); }

    public void registerHackathon(Hackathon hackathon) { registeredHackathons.add(hackathon); }
    public void addSubmission(Submission submission) { submissions.add(submission); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Participant)) return false;
        Participant that = (Participant) o;
        return participantId == that.participantId;
    }

    @Override
    public int hashCode() { return Objects.hash(participantId); }

    @Override
    public String toString() { return "Participant{" + "id=" + participantId + ", name='" + participantName + '\'' + '}'; }
}
