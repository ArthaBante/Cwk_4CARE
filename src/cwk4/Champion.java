package cwk4;

import java.io.Serializable;

/**
 * Represents a champion in the Championships at Rare Earth game.
 * This class holds details about a champion's attributes and their state within the game.
 *
 * @author (author's name)
 * @version (date or version number)
 */
public class Champion implements Serializable {
    private String name;
    private String type;
    private int skillLevel;
    private boolean necromancer;
    private int entryFee;
    private String speciality;
    private String weapon;
    private boolean talks;
    private boolean isActive;
    private boolean isDisqualified;




    /**
     * Constructor to initialize a Champion object.
     * @param name Name of the champion.
     * @param type Type of the champion (Wizard, Warrior, etc.).
     * @param skillLevel Skill level of the champion.
     * @param entryFee Entry fee required to enter the champion into competition.
     */
    public Champion(String name, String type, int skillLevel, boolean necromancer, int entryFee, String speciality, String weapon, boolean talks) {
        this.name = name;
        this.type = type;
        this.skillLevel = skillLevel;
        this.necromancer = necromancer;
        this.entryFee = entryFee;
        this.speciality = speciality;
        this.weapon = weapon;
        this.talks = talks;
        this.isActive = false;
        this.isDisqualified = false;
    }

    // Getter and Setter methods
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getSkillLevel() {
        return skillLevel;
    }

    public boolean isNecromancer() {
        return necromancer;
    }

    public int getEntryFee(){
        return entryFee;
    }

    public String getSpeciality() {
        return speciality;
    }

    public String getWeapon(){
        return weapon;
    }

    public boolean isTalks(){
        return talks;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isDisqualified() {
        return isDisqualified;
    }

    public void setDisqualified(boolean disqualified) {
        isDisqualified = disqualified;
    }



    /**
     * Returns a string representation of the champion details.
     * @return String representing the champion's details.
     */
    @Override
    public String toString() {
        return
                "Name: " + name + '\''+
                ", Type: " + type + '\'' +
                ", Skill: " + skillLevel +
                ", Necromancer? " + necromancer +
                ", Fee: " + entryFee +
                ", Speciality: " + speciality +
                ", Weapon: " + weapon +
                ", Talks? " + talks +
                ", Active? " + isActive +
                ", Disqualified? " + isDisqualified;
    }





    class Wizard extends Champion {

        public Wizard(String name, int skillLevel, boolean necromancer, int entryFee, String speciality) {
            super(name, "Wizard", skillLevel, necromancer, entryFee, speciality, "None", true);
        }

        public boolean canDoChallenge(Challenges challenge) {
            // Wizards can participate in any type of challenge
            return challenge.getType().equals("Magic") || challenge.getType().equals("Fight") || challenge.getType().equals("Mystery");
        }
    }

class Warrior extends Champion {

        public Warrior(String name, int skillLevel, int entryFee, String weapon) {
            super(name, "Warrior", skillLevel, false, entryFee, "None", weapon, false);
        }

        public boolean canDoChallenge(Challenges challenge) {
            // Warriors can only participate in fight challenges
            return challenge.getType().equals("Fight");
        }
    }

class Dragon extends Champion {

        public Dragon(String name, int skillLevel, int entryFee, boolean talks) {
            super(name, "Dragon", skillLevel, false, entryFee, "None", "None", talks);
        }

        public boolean canDoChallenge(Challenges challenge) {
            // Dragons can participate in fight challenges
            // They can also participate in mystery challenges only if they can talk
            return challenge.getType().equals("Fight") || (challenge.getType().equals("Mystery") && this.isTalks());
        }
    }

    /**
     * Checks if the champion can participate in the given challenge.
     *
     * @param challenge The challenge to check.
     * @return true if the champion can participate, otherwise false.
     */
    public boolean canDoChallenge(Challenges challenge) {
        String challengeType = challenge.getType();

        if (this instanceof Wizard) {
            // Wizards can participate in any type of challenge
            return true;
        } else if (this instanceof Warrior) {
            // Warriors can only participate in fight challenges
            return challengeType.equals("Fight");
        } else if (this instanceof Dragon) {
            // Dragons can participate in fight challenges
            // They can also participate in mystery challenges only if they can talk
            if (challengeType.equals("Fight")) {
                return true;
            } else if (challengeType.equals("Mystery")) {
                Dragon dragon = (Dragon) this; // Cast to Dragon type
                return dragon.isTalks();
            } else {
                return false; // Dragons cannot participate in other types of challenges
            }
        } else {
            return false; // Unknown champion type
        }
    }

}
