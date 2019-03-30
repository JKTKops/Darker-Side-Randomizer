import java.util.Objects;

@Deprecated
abstract class ListElement {
    private String name;
    private String kingdom;
    private boolean firstVisit;

    ListElement(String name, String kingdom, boolean firstVisit) {
        this.name = name;
        this.kingdom = kingdom;
        this.firstVisit = firstVisit;
    }

    private boolean crossedOff;

    String getName() {
        return name;
    }

    String getKingdom() {
        return kingdom;
    }

    boolean getFirstVisit() {
        return firstVisit;
    }

    String[] getTags() {
        return new String[]{};
    }

    boolean checkTags(String tag) {
        return false;
    }

    void toggleStrike() {
        crossedOff = !crossedOff;
    }

    boolean getCrossedOff() {
        return crossedOff;
    }

    static int compareByVisit(ListElement m1, ListElement m2) {
        ListElement[] comparands = {m1, m2};
        int[] comparandBucket = {1, 2};
        for (int i = 0; i < comparands.length; i++) {
            ListElement m = comparands[i];
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
        return Lists.indexOfElement(m1) < Lists.indexOfElement(m2) ? -1 : 1;
    }

    static int compareByKingdom(ListElement m1, ListElement m2) {
        ListElement[] comparands = {m1, m2};
        int[] comparandBucket = {1, 2};
        for (int i = 0; i < comparands.length; i++) {
            ListElement m = comparands[i];
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
        return Lists.indexOfElement(m1) < Lists.indexOfElement(m2) ? -1 : 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListElement e = (ListElement) o;
        return Objects.equals(name, e.name);
    }
}
