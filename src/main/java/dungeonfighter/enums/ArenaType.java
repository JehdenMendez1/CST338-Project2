package dungeonfighter.enums;

/**
 * Explanation:
 *
 * @author Tharindu Amarasinghage
 * @since 4/28/26
 */
public enum ArenaType {
    FIRE ("Fire Dungeon","/FireArenaBG.png", "-fx-text-fill: orange;" ),
    ICE ("Ice Dungeon","/IceArenaBG.png", "-fx-text-fill: blue;" ),
    JUNGLE ("Wilderness Dungeon","/WildenessArenaBG.png", "-fx-text-fill: green;" );

    private final String dungeonName;
    private final String imageBGPath;
    private final String fontColor;

    ArenaType(String dungeonName, String imageBGPath, String fontColor){
        this.dungeonName = dungeonName;
        this.imageBGPath = imageBGPath;
        this.fontColor = fontColor;
    }

    public String getDungeonName(){
        return dungeonName;
    }

    public String getImageBGPath(){
        return imageBGPath;
    }

    public String getFontColor(){
        return fontColor;
    }
}
