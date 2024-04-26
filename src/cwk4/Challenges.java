package cwk4;

import java.io.Serializable;

/**
 * Represents a challenge in the Championships at Rare Earth game.
 * This class holds details about challenges and its impact on the game.
 *
 * @author (author's name)
 * @version (date or version number)
 */
public class Challenges implements Serializable {
    private int challengeNumber;
    private String type;
    private String enemy;
    private int skillrequired;
    private int reward;
    private boolean isCompleted;

    /**
     * Constructor to initialize a Challenge object.
     * @param challengeNumber Unique identifier for the challenge.
     * @param type Brief description of what the challenge entails.
     * @param skillrequired Numerical value representing the challenge's difficulty.
     * @param reward The amount of reward given for completing the challenge.
     */
    public Challenges(int challengeNumber, String type, String enemy, int skillrequired, int reward) {
        this.challengeNumber = challengeNumber;
        this.type = type;
        this.enemy = enemy;
        this.skillrequired = skillrequired;
        this.reward = reward;
        this.isCompleted = false;
    }

    // Getter and Setter methods
    public int getChallengeNumber() {
        return challengeNumber;
    }

    public String getType() {
        return type;
    }

    public String getEnemy(){
        return enemy;
    }

    public int getSkillrequired() {
        return skillrequired;
    }

    public int getReward() {
        return reward;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }



    /**
     * Returns a string representation of the challenge details.
     * @return String representing the challenge's details.
     */
    @Override
    public String toString() {
        return
                "Challenge No=" + challengeNumber +
                ", Type='" + type + '\'' +
                ", Enemy='" + enemy +
                ", Skill Required=" + skillrequired +
                ", Reward=" + reward +
                ", isCompleted=" + isCompleted;
    }
}
