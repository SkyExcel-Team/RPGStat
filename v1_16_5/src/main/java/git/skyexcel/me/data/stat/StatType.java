package git.skyexcel.me.data.stat;

public enum StatType {
    MAX_HEALTH,

    ATTACK_DAMAGE,

    CRITICAL_DAMAGE,

    RANGED_ATTACK_DAMAGE,

    DEFENSE,

    FARM,
    /**
     * Increase chance to get the coal when player's break the block
     */
    MINE,
    /**
     * Increase fish catch speed & Upgrade chance to get more fish
     */
    FISH,
    /**
     * Decrease fall damage with stat * upgrade
     */
    FALL,
    /**
     * Player Movement speed
     */
    SPEED,
    /**
     * When Player's level up
     */
    LEVELUP;


}
