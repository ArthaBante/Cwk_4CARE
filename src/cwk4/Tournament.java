package cwk4;

import org.junit.Test;

import java.io.*;
import java.util.*;
import java.util.ArrayList;

/**
 * This class represents a Tournament in the "Championships at Rare Earth" game.
 * It handles various functionalities related to the vizier's team, challenges, and game state.
 */
public class Tournament implements CARE {
    private String vizier;
    private List<Champion> reserveChampions; // List of all champions in reserve
    private List<Champion> teamChampions;   // List of all champions in the vizier's team
    private List<Challenges> allChallenges; // List of all challenges
    private List<Champion> getReserve;


    private int treasury; // Money available in the treasury

    public Tournament(String viz) {
        this.vizier = viz;
        this.treasury = 1000; // Example starting amount for the treasury
        this.reserveChampions = new ArrayList<>();
        this.teamChampions = new ArrayList<>();
        this.allChallenges = new ArrayList<>();
        this.getReserve = new ArrayList<>();

        setupChampions();
        setupChallenges();
    }

    public Tournament(String vizierName, String filename) {
        // Set up vizier's name and other initializations
        this.vizier = vizierName;
        readChallenges(filename);  // Load challenges from a file
        setupChampions();          // Load champions by calling existing method
    }


    // Setup champions with default values
    private void setupChampions() {
        getReserve = new ArrayList<>();

        getReserve.add(new Champion("Ganfrank",   "Wizard", 10,true, 400, "Transmutation", "None", false));
        getReserve.add(new Champion("Rudolf", "Wizard", 6, true, 400, "Invisibility", "None", false));
        getReserve.add(new Champion("Elblond", "Warrior", 1, false, 150, "None", "Sword", false));
        getReserve.add(new Champion("Flimsi", "Warrior", 2, false, 200, "None", "Bow", false));
        getReserve.add(new Champion("Drabina", "Dragon", 7, false, 500, "None", "None", false));
        getReserve.add(new Champion("Golum", "Dragon", 7, false, 500, "None", "None", true));
        getReserve.add(new Champion("Argon", "Warrior", 9, false, 900, "None", "Mace", false));
        getReserve.add(new Champion("Neon", "Wizard", 2, false, 300, "Translocation", "None", false));
        getReserve.add(new Champion("Xenon", "Dragon", 7, false, 500, "None", "None", true));
        getReserve.add(new Champion("Atlanta", "Warrior", 5, false, 500, "None", "Bow", false));
        getReserve.add(new Champion("Krypton", "Wizard", 8, false, 300, "Fireballs", "None", false));
        getReserve.add(new Champion("Hedwig", "Wizard", 1, true, 400, "Flying", "None", false));
    }

    // Setup default challenges
    private void setupChallenges() {
        allChallenges = new ArrayList<>();

        // Add challenges to the list
        allChallenges.add(new Challenges(1, "Magic", "Borg", 3, 100));
        allChallenges.add(new Challenges(2, "Fight", "Huns", 3, 120));
        allChallenges.add(new Challenges(3, "Mystery", "Ferengi", 3, 150));
        allChallenges.add(new Challenges(4, "Magic", "Vandal", 9, 200));
        allChallenges.add(new Challenges(5, "Fight", "Borg", 7, 90));
        allChallenges.add(new Challenges(6, "Mystery", "Goth", 8, 45));
        allChallenges.add(new Challenges(7, "Magic", "Frank", 10, 200));
        allChallenges.add(new Challenges(8, "Fight", "Sith", 10, 170));
        allChallenges.add(new Challenges(9, "Mystery", "Cardashian", 9, 300));
        allChallenges.add(new Challenges(10, "Magic", "Jute", 2, 300));
        allChallenges.add(new Challenges(11, "Fight", "Celt", 2, 250));
        allChallenges.add(new Challenges(12, "Mystery", "Celt", 1, 250));
    }

    // Getter for the Vizier's name
    public String getVizier() {
        return this.vizier;
    }

    // Set the treasury value
    public void setTreasury(int amount) {
        this.treasury = amount;
    }

    // Get the treasury value
    public int getMoney() {
        return this.treasury;
    }

    @Override
    public String getReserve() {
        StringBuilder sb = new StringBuilder();
        if (getReserve.isEmpty()) {
            return "No Champions available";
        } else {
            sb.append("************ Champions in Reserve ************\n");
            for (Champion champion : getReserve) {
                sb.append(champion.toString()).append("\n");
            }
            return sb.toString();
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nVizier: ").append(vizier);
        sb.append("\nTreasury: ").append(treasury);
        sb.append("\nTeam Champions: ");
        if (teamChampions.isEmpty()) {
            sb.append("No champions");
        } else {
            teamChampions.forEach(champion -> sb.append(champion.toString()).append("\n"));
        }
        return sb.toString();
    }

    public boolean isDefeated() {
        return treasury <= 0 && teamChampions.isEmpty();
    }

    // Get details of a specific champion by name
    public String getChampionDetails(String name) {
        return getReserve.stream()
                .filter(champion -> champion.getName().equals(name))
                .findFirst()
                .map(Champion::toString)
                .orElse("No such champion");
    }

    public boolean isInReserve(String name) {
        return getReserve.stream()
                .anyMatch(champion -> champion.getName().equals(name));
    }

    public int enterChampion(String name) {
        Champion champ = getReserve.stream()
                .filter(champion -> champion.getName().equals(name))
                .findFirst()
                .orElse(null);

        if (champ == null) {
            return -1; // No such champion
        } else if (teamChampions.contains(champ)) {
            return 1; // Already in the team
        } else if (treasury < champ.getEntryFee()) {
            return 2; // Not enough money
        } else {
            champ.setActive(true);
            teamChampions.add(champ);
            treasury -= champ.getEntryFee();
            return 0; // Successful entry
        }
    }

    public boolean isInViziersTeam(String name) {
        return teamChampions.stream()
                .anyMatch(champion -> champion.getName().equals(name));
    }

    public int retireChampion(String name) {
        Champion champ = teamChampions.stream()
                .filter(champion -> champion.getName().equals(name))
                .findFirst()
                .orElse(null);

        if (champ == null) {
            return -1; // No such champion
        } else if (champ.isDisqualified()) {
            return 1; // Disqualified, cannot retire
        } else {
            teamChampions.remove(champ);
            reserveChampions.add(champ);
            return 0; // Successfully retired to reserves
        }
    }

    public String getTeam() {
        if (teamChampions.isEmpty()) {
            return "No champions entered";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("************ Vizier's Team of champions ********");
            teamChampions.forEach(champion -> sb.append("\n").append(champion.toString()));
            return sb.toString();
        }
    }



    public String getDisqualified() {
        List<Champion> disqualifiedChamps = new ArrayList<>();
        for (Champion champ : teamChampions) {
            if (champ.isDisqualified()) {
                disqualifiedChamps.add(champ);
            }
        }

        if (disqualifiedChamps.isEmpty()) {
            return "No disqualified champions";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("************ Vizier's Disqualified champions ********");
            disqualifiedChamps.forEach(champ -> sb.append("\n").append(champ.toString()));
            return sb.toString();
        }
    }

    public boolean isChallenge(int num) {
        return allChallenges.stream()
                .anyMatch(challenge -> challenge.getChallengeNumber() == num);
    }

    public String getChallenge(int num) {
        Challenges challenge = allChallenges.stream()
                .filter(chal -> chal.getChallengeNumber() == num)
                .findFirst()
                .orElse(null);

        if (challenge == null) {
            return "No such challenge";
        } else {
            return challenge.toString();
        }
    }

    public String getAllChallenges() {
        StringBuilder sb = new StringBuilder();
        if (allChallenges.isEmpty()) {
            return "No challenges available";
        } else {
            sb.append("************ All Challenges ************\n");
            for (Challenges challenge : allChallenges) {
                sb.append(challenge.toString()).append("\n");
            }
            return sb.toString();
        }
    }

    public int meetChallenge(int challengeNumber) {
        Challenges challenge = allChallenges.stream()
                .filter(chal -> chal.getChallengeNumber() == challengeNumber)
                .findFirst()
                .orElse(null);

        if (challenge == null) {
            return -1; // No such challenge
        }

        if (challenge.isCompleted()) {
            return -2; // Already completed
        }

        Optional<Champion> availableChampion = teamChampions.stream()
                .filter(champion -> champion.isActive() && !champion.isDisqualified())
                .max(Comparator.comparingInt(Champion::getSkillLevel));

        if (availableChampion.isEmpty()) {
            treasury -= challenge.getReward(); // Deduct reward if no suitable champion
            return 2; // Challenge lost due to no suitable champion
        }

        Champion champ = availableChampion.get();
        if (champ.getSkillLevel() >= challenge.getSkillrequired()) {
            treasury += challenge.getReward(); // Reward for completing the challenge
            challenge.setCompleted(true);
            return 0; // Challenge won
        } else {
            treasury -= challenge.getReward(); // Penalty for losing
            champ.setDisqualified(true); // Champion disqualified
            return 1; // Challenge lost due to skills
        }
    }

    public void readChallenges(String filename) {
        // Read challenges from a file and populate the allChallenges list
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                int challengeNumber = Integer.parseInt(parts[0]);
                String type = parts[1];
                String enemy = parts[2];
                int skillrequired = Integer.parseInt(parts[3]);
                int reward = Integer.parseInt(parts[4]);
                allChallenges.add(new Challenges(challengeNumber, type, enemy, skillrequired, reward));
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle error reading the file
        }
    }

    public Tournament loadGame(String filename) {
        Tournament tournament = null;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            tournament = (Tournament) in.readObject(); // Deserializes the file into a Tournament object
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return tournament;
    }


    public void saveGame(String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(this); // Serializes the current instance of Tournament
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}