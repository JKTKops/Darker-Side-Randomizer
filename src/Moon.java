import java.util.Objects;

public class Moon {
    private String name;
    private String king;
    private String[] achTags;
    private int achLevel;
    private boolean firstVisit;
    private boolean crossedOff = false;

    //not intended for use, just necessary to allow NecessaryAction to inherit from Moon
    Moon () {
        achTags = new String[0];
    }

    Moon(String moonName, String kingdom, boolean first, String... tagArray) {
        name = moonName;
        king = kingdom;
        achTags = tagArray;
        firstVisit = first;
    }

    Moon(String moonName, String kingdom, int level, String... tagArray) {
        name = moonName;
        king = kingdom;
        achTags = tagArray;
        achLevel = level;
    }

    public String toString() {
        /* A useful debugging toString alternative.
        if(!king.equals("Achievements"))
            return "{\n\t\"name\": \""+name+"\",\n\t\"kingdom\": \""+king+"\",\n\t\"first\": "+ getFirstVisit().toString()+"\n}";
        return "{\n\t\"name\": \""+name+"\",\n\t\"kingdom\": \""+king+"\",\n\t\"tag\": \""+achTags[0]+"\",\n\t\"count\": \""+achTags[1]+"\"\n}";
        */
        String out = name;

        if (king.equals("Achievements")) {
            out += ": " + achTags[1];
        }

        if (crossedOff) {
            return "<html><strike>" + out + "</strike></html>";
        }
        return out;
    }

    public String getName() {
        return name;
    }

    String[] getTags(){
        return achTags;
    }

    public String getKingdom() {
        return king;
    }

    public Boolean getFirstVisit() {
        return firstVisit;
    }

    int getLevel() {
        return achLevel;
    }

    void toggleCrossedOff() {
        crossedOff = !crossedOff;
    }

    boolean getCrossedOff() {
        return crossedOff;
    }

    boolean checkTags(String target) {
        boolean tagged = false;
        for (String s: achTags){
            if(s.equals(target))
                tagged = true;
        }
        return tagged;
    }

    static int compareByVisit(Moon m1, Moon m2) {
        Moon[] comparands = {m1, m2};
        int[] comparandBucket = {1, 2};
        for (int i = 0; i < comparands.length; i++) {
            Moon m = comparands[i];
            String king = m.getKingdom();
            boolean fV = m.getFirstVisit();
            if (king.equals("Cascade") && fV)
                comparandBucket[i] = 0;
            else if (king.equals("Sand") && fV)
                comparandBucket[i] = 1;
            else if (king.equals("Lake") && fV)
                comparandBucket[i] = 2;
            else if (king.equals("Wooded") && fV)
                comparandBucket[i] = 3;
            else if (king.equals("Cloud") && fV)
                comparandBucket[i] = 4;
            else if (king.equals("Lost") && fV)
                comparandBucket[i] = 5;
            else if (king.equals("Metro") && fV)
                comparandBucket[i] = 6;
            else if (king.equals("Snow") && fV)
                comparandBucket[i] = 7;
            else if (king.equals("Seaside") && fV)
                comparandBucket[i] = 8;
            else if (king.equals("Luncheon") && fV)
                comparandBucket[i] = 9;
            else if (king.equals("Ruined") && fV)
                comparandBucket[i] = 10;
            else if (king.equals("Bowser's") && fV)
                comparandBucket[i] = 11;
            else if (king.equals("Moon") && fV)
                comparandBucket[i] = 12;
            else if (king.equals("Mushroom"))
                comparandBucket[i] = 13;
            else if (king.equals("Snow") && !fV)
                comparandBucket[i] = 14;
            else if (king.equals("Cascade") && !fV)
                comparandBucket[i] = 15;
            else if (king.equals("Bowser's") && !fV)
                comparandBucket[i] = 16;
            else if (king.equals("Seaside") && !fV)
                comparandBucket[i] = 17;
            else if (king.equals("Lake") && !fV)
                comparandBucket[i] = 18;
            else if (king.equals("Sand") && !fV)
                comparandBucket[i] = 19;
            else if (king.equals("Metro") && !fV)
                comparandBucket[i] = 20;
            else if (king.equals("Wooded") && !fV)
                comparandBucket[i] = 21;
            else if (king.equals("Luncheon") && !fV)
                comparandBucket[i] = 22;
            else if (king.equals("Cap") && !fV)
                comparandBucket[i] = 23;
            else if (king.equals("Cloud") && !fV)
                comparandBucket[i] = 24;
            else if (king.equals("Lost") && !fV)
                comparandBucket[i] = 25;
            else if (king.equals("Ruined") && !fV)
                comparandBucket[i] = 26;
            else if (king.equals("Moon") && !fV)
                comparandBucket[i] = 27;
            else if (king.equals("Dark Side"))
                comparandBucket[i] = 28;
            else if (king.equals("Achievements"))
                comparandBucket[i] = 29;
        }
        if (comparandBucket[0] < comparandBucket[1])
            return -1;
        if (comparandBucket[0] > comparandBucket[1])
            return 1;
        return Lists.indexOfMoon(m1) < Lists.indexOfMoon(m2) ? -1 : 1;
    }

    static int compareByKingdom(Moon m1, Moon m2) {
        Moon[] comparands = {m1, m2};
        int[] comparandBucket = {1, 2};
        for (int i = 0; i < comparands.length; i++) {
            Moon m = comparands[i];
            String king = m.getKingdom();
            switch (king) {
                case "Cap":
                    comparandBucket[i] = 0;
                    break;
                case "Cascade":
                    comparandBucket[i] = 1;
                    break;
                case "Sand":
                    comparandBucket[i] = 2;
                    break;
                case "Lake":
                    comparandBucket[i] = 3;
                    break;
                case "Wooded":
                    comparandBucket[i] = 4;
                    break;
                case "Cloud":
                    comparandBucket[i] = 5;
                    break;
                case "Lost":
                    comparandBucket[i] = 6;
                    break;
                case "Metro":
                    comparandBucket[i] = 7;
                    break;
                case "Snow":
                    comparandBucket[i] = 8;
                    break;
                case "Seaside":
                    comparandBucket[i] = 9;
                    break;
                case "Luncheon":
                    comparandBucket[i] = 10;
                    break;
                case "Ruined":
                    comparandBucket[i] = 11;
                    break;
                case "Bowser's":
                    comparandBucket[i] = 12;
                    break;
                case "Moon":
                    comparandBucket[i] = 13;
                    break;
                case "Mushroom":
                    comparandBucket[i] = 14;
                    break;
                case "Dark Side":
                    comparandBucket[i] = 15;
                    break;
                case "Achievements":
                    comparandBucket[i] = 16;
                    break;
            }
        }
        if (comparandBucket[0] < comparandBucket[1])
            return -1;
        if (comparandBucket[0] > comparandBucket[1])
            return 1;
        return Lists.indexOfMoon(m1) < Lists.indexOfMoon(m2) ? -1 : 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Moon moon = (Moon) o;
        return Objects.equals(name, moon.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
