import java.util.*;
import java.util.regex.Pattern;

public class Randomizer {

    private int moonsToPull = 0;
    private int[] achievementProgress = new int[Lists.ACHIEVEMENT_TAGS.length + 1];
    private boolean[] moonRockMoonPulled = new boolean[15];
    private int[] kingdomFirstVisitMoons = new int[16];
    private List<Moon> remainingAchievements = new ArrayList<>();
    private int moonsPulled = 0;
    private List<ListElement> standby = new ArrayList<>();
    private boolean toadette = true;

    List<ListElement> randomize(boolean toadetteAchievements, boolean rollingInCoins, boolean purpleCoins,
            boolean jumpRope, boolean volleyball, long seed, int toPull) {
        //TODO: FMS option?
        //TODO: Peace option?

        /*
        System.out.println("Options:\n\tToadette: " + toadetteAchievements + "\n\tRolling in Coins: " + rollingInCoins
                + "\n\tPurple Coins: " + purpleCoins + "\n\tJump Rope: " + jumpRope + "\n\tVolleyball: " + volleyball
                + "\nSeed: " + seed + "\n");
        */
        toadette = toadetteAchievements;

        Random rnd = new Random(seed);
        List<ListElement> sourcePool = generateCompleteList();

        moonsToPull = toPull;

        /*
         * remove all disabled, prerequisite, and postrequisite moons from sourcePool.
         */
        for(int i = 0; i<sourcePool.size(); i++) {
            String name = sourcePool.get(i).getName();
            if (!toadette && sourcePool.get(i).getKingdom().equals("Achievements")) {
                sourcePool.remove(i);
                i--;
            } else if ((!rollingInCoins && name.equals("Rolling in Coins")) || (!purpleCoins &&
                    (name.equals("Regional Coin Shopper") ||
                            (name.substring(0, 8)).equals("Souvenir")))) {
                sourcePool.remove(i);
                i--;
            }
            if (name.length() > 8 && name.substring(0, 9).equals("Jump-Rope")) {
                if(!jumpRope) {
                    sourcePool.remove(i);
                    i--;
                }
                else{
                    standby.add(sourcePool.remove(i));
                    i--;
                }
            }
            else if (name.substring(0, 5).equals("Beach")) {
                if (!volleyball) {
                    sourcePool.remove(i);
                    i--;
                }
                else{
                    standby.add(sourcePool.remove(i));
                    i--;
                }
            }
            else if(sourcePool.get(i).checkTags("Tourist") ||
               sourcePool.get(i).checkTags("Yoshi") ||
                    (sourcePool.get(i).checkTags("kfr") &&
                            !sourcePool.get(i).getKingdom().equals("Achievements"))){
                standby.add(sourcePool.remove(i));
                i--;
            }
            else if(sourcePool.get(i).checkTags("Trace")) {
                standby.add(sourcePool.remove(i));
                i--;
            }
            else if(name.equals("Peach in the Moon Kingdom") ||
                    name.equals("Princess Peach, Home Again!") ||
                    sourcePool.get(i).getKingdom().equals("Dark Side") ||
                    sourcePool.get(i).getName().matches("Found with Dark Side Art.*")){
                standby.add(sourcePool.remove(i));
                i--;
            }
            else if(name.equals("Atop the Highest Tower") || name.equals("Moon Shards in the Sand")){
                standby.add(sourcePool.remove(i));
                i--;
            }
            else if(name.equals("Hat-and-Seek: Mushroom Kingdom")){
                standby.add(sourcePool.remove(i));
                i--;
            }
            else if(name.substring(0,6).equals("RC Car")){
                standby.add(sourcePool.remove(i));
                i--;
            }
            else if(name.matches("Iceburn Circuit.*")){
                standby.add(sourcePool.remove(i));
                i--;
            }
        }

        /*
         * add (possibly duplicated) prerequisite moons back into sourcePool.
         */
        for(int i = 0; i<standby.size(); i++){
            String name = standby.get(i).getName();
            if(Pattern.matches(".*Regular Cup", name)){
                ListElement m = standby.remove(i);
                sourcePool.add(m);
                sourcePool.add(m);
                i--;
            }
            else if(name.equals("Walking on the Moon!") || name.equals("Walking the Desert!")
                    || name.equals("Walking on Ice!")) {
                ListElement m = standby.remove(i);
                sourcePool.add(m);
                sourcePool.add(m);
                i--;
            }
            else if(name.equals("A Tourist in the Metro Kingdom!")){
                ListElement m = standby.remove(i);
                for(int j = 0; j<6; j++)
                    sourcePool.add(m);
                i--;
            }
            else if(name.equals("Atop the Highest Tower")) {
                ListElement m = standby.remove(i);
                sourcePool.add(m);
                sourcePool.add(m);
                i--;
            }
            else if(name.equals("RC Car Pro!")){
                ListElement m = standby.remove(i);
                sourcePool.add(m);
                sourcePool.add(m);
                i--;
            }
            else if(name.equals("Jump-Rope Hero")){
                ListElement m = standby.remove(i);
                sourcePool.add(m);
                sourcePool.add(m);
                i--;
            }
            else if(name.equals("Iceburn Circuit Class A")){
                ListElement m = standby.remove(i);
                sourcePool.add(m);
                sourcePool.add(m);
                i--;
            }
            else if(name.equals("Beach Volleyball: Champ")){
                ListElement m = standby.remove(i);
                sourcePool.add(m);
                sourcePool.add(m);
                i--;
            }
            else if(name.equals("Gobbling Fruit with Yoshi")){
                ListElement m = standby.remove(i);
                for(int j = 0; j < 3; j++)
                    sourcePool.add(m);
                i--;
            }
            else if(standby.get(i).getKingdom().equals("Dark Side")){
                ListElement m = standby.get(i);
                if(m.getName().equals("Arrival at Rabbit Ridge!") || m.getName().equals("Captain Toad on the Dark Side!")) {
                    sourcePool.add(m);
                    standby.remove(i);
                    i--;
                }
            }
        }

        List<ListElement> output = new ArrayList<>();

        if(toadette) {
            remainingAchievements = generateAchievementList(sourcePool);
            List<ListElement> forcedAchievements = pullAchievements(rnd, sourcePool, output);
            for (ListElement achievement : forcedAchievements) {
                Moon a = (Moon) achievement;
                pullTaggedMoons(a, sourcePool, output, rnd);
            }
            output.addAll(forcedAchievements);
        }

        pullStoryMoons(sourcePool, output);

        Collections.shuffle(sourcePool, rnd);
        for(String kingdom[] : Lists.kingdomFirstVisitReq){
            pullFirstVisitMoons(kingdom[0], sourcePool, output, rnd);
            Collections.shuffle(sourcePool, rnd);
        }

        while(moonsPulled < moonsToPull){
            pullMoon(sourcePool, output, rnd);
        }

        if(kingdomFirstVisitMoons[7] == Integer.parseInt(Lists.kingdomFirstVisitReq[7][1])){
            for (ListElement m : output) {
                if(m.getName().equals("Secret Path to New Donk City")){
                    output.add(new NecessaryAction("Get Metro Painting Moon", "Sand", true));
                    break;
                }
            }
        }
        if(kingdomFirstVisitMoons[10] == Integer.parseInt(Lists.kingdomFirstVisitReq[10][1])){
            for (ListElement m : output) {
                if(m.getName().equals("Secret Path to Mount Volbono!")){
                    output.add(new NecessaryAction("(Lake First) Get Luncheon Painting Moon", "Wooded", true));
                    output.add(new NecessaryAction("(Wooded First) Get Luncheon Painting Moon", "Lake", true));
                    break;
                }
            }
        }

        return output;
}

    private static ArrayList<ListElement> generateCompleteList(){
        ArrayList<ListElement> allMoons = new ArrayList<>();
        for(String[] moon: Lists.moons){
            String name = moon[0];
            String king = moon[1];
            if(moon[1].equals("Achievements")) {
                int level = Integer.parseInt(moon[2]);
                String[] tags = {moon[3], moon[4]};
                allMoons.add(new Moon(name, king, level, tags));
            }
            else{
                boolean firstVisit = false;
                if(moon[2].equals("true"))
                    firstVisit = true;

                String[] tags = {};
                if (moon.length == 4) {
                    tags = new String[]{moon[3]};
                } else if (moon.length == 5) {
                    tags = new String[]{moon[3], moon[4]};
                } else if (moon.length == 6) {
                    tags = new String[]{moon[3], moon[4], moon[5]};
                }
                allMoons.add(new Moon(name, king, firstVisit, tags));
            }
        }
        return allMoons;
    }

    private static ArrayList<ListElement> generateTaggedList(String tag, List<ListElement> parentList){
        ArrayList<ListElement> taggedMoons = new ArrayList<>();
        for (int i = 0; i<parentList.size(); i++) {
            if (parentList.get(i).checkTags(tag)){
                taggedMoons.add(parentList.remove(i));
                i--;
            }
        }
        return taggedMoons;
    }

    private static ArrayList<ListElement> generateFirstVisitList(String kingdom, List<ListElement> source){
        ArrayList<ListElement> taggedMoons = new ArrayList<>();
        for (int i = 0; i<source.size(); i++) {
            if (source.get(i).getKingdom().equals(kingdom) && source.get(i).getFirstVisit()){
                taggedMoons.add(source.remove(i));
                i--;
            }
        }
        return taggedMoons;
    }

    private static ArrayList<Moon> generateAchievementList(List<ListElement> sourceList){
        ArrayList<Moon> achievements = new ArrayList<>();

        for(int i = 0; i < sourceList.size(); i++){
            if(sourceList.get(i).getKingdom().equals("Achievements")){
                ListElement e = sourceList.remove(i);
                Moon m = (Moon) e;
                achievements.add(m);
                i--;
            }
        }
        return achievements;
    }

    private void pullTaggedMoons(Moon achievement, List<ListElement> source, List<ListElement> output, Random rnd){
        int tagValue = -1;
        for(int i = 0; i<Lists.ACHIEVEMENT_TAGS.length; i++)
            if(achievement.getTags()[0].equals(Lists.ACHIEVEMENT_TAGS[i]))
                tagValue = i;
        if(tagValue == -1)
            return;

        ArrayList<ListElement> taggedList = generateTaggedList(achievement.getTags()[0], source);
        Collections.shuffle(taggedList, rnd);
        while(achievementProgress[tagValue]<Integer.parseInt(achievement.getTags()[1])){
            pullMoon(taggedList, output, rnd);
        }
        source.addAll(taggedList);
    }

    private void pullFirstVisitMoons(String kingdom, List<ListElement> source, List<ListElement> output, Random rnd){
        int kingdomValue;
        for (kingdomValue = 0; kingdomValue<Lists.kingdomFirstVisitReq.length; kingdomValue++){
            if(Lists.kingdomFirstVisitReq[kingdomValue][0].equals(kingdom)){
                break;
            }
        }

        ArrayList<ListElement> firstVisitList = generateFirstVisitList(kingdom, source);
        Collections.shuffle(firstVisitList, rnd);
        while(kingdomFirstVisitMoons[kingdomValue] < Integer.parseInt(Lists.kingdomFirstVisitReq[kingdomValue][1])){
            pullMoon(firstVisitList, output, rnd);
        }
        source.addAll(firstVisitList);
    }

    private void pullStoryMoons(List<ListElement> source, List<ListElement> output){
        for (int i = 0; i < source.size(); i++) {
            if(source.get(i).checkTags("Story")){
                ListElement m = source.remove(i);
                i--;
                if(m.checkTags("Multi"))
                    moonsPulled += 3;
                else
                    moonsPulled++;
                for (int j = 0; j < Lists.kingdomFirstVisitReq.length; j++) {
                    if(Lists.kingdomFirstVisitReq[j][0].equals(m.getKingdom())){
                        if (m.checkTags("Multi"))
                            kingdomFirstVisitMoons[j] += 3;
                        else
                            kingdomFirstVisitMoons[j]++;
                        break;
                    }
                }
                output.add(m);
            }
        }
    }

    // RC Race room does NOT require Remotely Captured Car due to an absurdly easy clip
    /**
     * @param source the current location of the moon getting pulled, which must be in index 0
     * @param output the destination of the moon getting pulled, always output
     *
     * Pulls the front moon off source and places it at the end of output. Increments moonsPulled.
     *
     * If pulled moon was a multimoon, increments moonsPulled twice more.
     *
     * If pulled moon was a Tourist moon, find all copies of that moon in the source and replace with the next Tourist.
     *      if pulled moon was specifically Metro tourist, add a NecessaryAction to Sand.
     *
     * If pulled moon was a Regular Cup kfr moon, find the copy of that moon in the source and replace with the Master.
     *
     * If pulled moon was a low-level Trace Walking moon, find the copy and replace it with the followup.
     *
     * If pulled moon was Atop the Highest Tower, find the copy and replace it with Moon Shards in the Sand.
     *
     * If pulled moon was RC Car Pro, find the copy of that moon in the source and replace it with "RC Car Champ!".
     *
     * If pulled moon was Jump-Rope Hero, find the copy and replace with "Jump-Rope Genius!".
     *
     * If pulled moon was Beach Volleyball: Champ, find the copy and replace with "Beach Volleyball: Hero of the Beach!".
     *
     * If pulled moon was Iceburn Circuit Class A, find the copy and replace with "Iceburn Circuit Class S".
     *
     * If pulled moon was the 13th Peach moon, add "Peach on the Moon" to the source from standby.
     * If pulled moon was Peach on the Moon, add "Princess Peach, Home Again!" and "Hat-and-Seek: Mushroom Kingdom" to
     *      the source from standby.
     *
     * If pulled moon had a "yoshi" tag, find the copies in source and replace with the next "yoshi" moon.
     *
     * If pulled moon was "Arrival at Rabbit Ridge!", add all other Dark Side moons to the source.
     *
     * If pulled moon was a Dark Side, level 1 Yoshi moon, find the copy in source and replace with the level 2 moon.
     *
     *
     * If achievements are enabled and the pulled moon has achievement tags, scan the list of possible tags for a match.
     *      When a match is found (since the moon has tags, at least one match will be found), increment the corresponding
     *      achievement progress. Check if this change needs to dynamically add a new achievement to output. If it does,
     *      find that achievement in remainingAchievements, then remove it from remainingAchievements and add it to output.
     *      Increment moonsPulled (as an achievement was pulled) and break out of the search loop as the only possible match
     *      was found.
     *      Return to the search for tag matches and repeat.
     *
     * NecessaryAction handler for hint art.
     */
    private void pullMoon(List<ListElement> source, List<ListElement> output, Random rnd){
        ListElement m = source.remove(0);
        if(m.checkTags("Multi") && moonsPulled > moonsToPull - 3)
            return;
        if(toadette) {
            if (willMeetAchievement(m) + moonsPulled + 1 > moonsToPull)
                return;
            if (!moonRockMoonPulled[14]) {
                moonRockMoonPulled[14] = true;
                for (int i = 0; i < moonRockMoonPulled.length; i++) {
                    if (!moonRockMoonPulled[i]) {
                        moonRockMoonPulled[14] = false;
                        break;
                    }
                }
                if (moonRockMoonPulled[14]) {
                    for (int i = 0; i < remainingAchievements.size(); i++) {
                        if (remainingAchievements.get(i).getName().equals("Moon Rock Liberator")) {
                            output.add(remainingAchievements.remove(i));
                        }
                    }
                }
            }
        }
        output.add(m);
        for (int i = 0; i < Lists.kingdomFirstVisitReq.length; i++) {
            if(Lists.kingdomFirstVisitReq[i][0].equals(m.getKingdom())){
                kingdomFirstVisitMoons[i]++;
                break;
            }
        }
        moonsPulled++;
        if(m.checkTags("Multi"))
            moonsPulled+=2;
        if(moonsPulled == 500)
            return;
        else if(m.checkTags("Tourist")){
            for (int i = 0; i < standby.size(); i++) {
                if(standby.get(i).checkTags("Tourist") && Integer.parseInt(m.getTags()[1]) == Integer.parseInt(standby.get(i).getTags()[1])-1){
                    ListElement nextTourist = standby.remove(i);
                    for (int j = 0; j < source.size(); j++) {
                        if(source.get(j) == m){
                            source.set(j, nextTourist);
                        }
                    }
                    break;
                }
            }
            if(m.getName().matches(".*Metro.*")){
                ListElement sandTourist = new NecessaryAction("Talk to Tourist", "Sand", true);
                output.add(sandTourist);
            }
        }
        else if(m.getName().matches(".*Regular Cup")){
            String kingdom = m.getName().split(" ")[0];
            for (int i = 0; i < standby.size(); i++) {
                if(standby.get(i).getName().matches(kingdom+" Kingdom.*Master Cup")){
                    for (int j = 0; j < source.size(); j++) {
                        if(source.get(j) == m){
                            source.set(j, standby.remove(i));
                        }
                    }
                    break;
                }
            }
        }
        else if(m.getName().equals("Walking the Desert!")){
            for (int i = 0; i < standby.size(); i++) {
                if(standby.get(i).getName().equals("More Walking in the Desert!")){
                    for (int j = 0; j < source.size(); j++) {
                        if(source.get(j) == m){
                            source.set(j, standby.remove(i));
                        }
                    }
                    break;
                }
            }
        }
        else if(m.getName().equals("Walking on Ice!")){
            for (int i = 0; i < standby.size(); i++) {
                if(standby.get(i).getName().equals("Even More Walking on Ice!")){
                    for (int j = 0; j < source.size(); j++) {
                        if(source.get(j) == m){
                            source.set(j, standby.remove(i));
                        }
                    }
                    break;
                }
            }
        }
        else if(m.getName().equals("Walking on the Moon!")){
            for (int i = 0; i < standby.size(); i++) {
                if(standby.get(i).getName().equals("Walking on the Moon: Again!")){
                    for (int j = 0; j < source.size(); j++) {
                        if(source.get(j) == m){
                            source.set(j, standby.remove(i));
                        }
                    }
                    break;
                }
            }
        }
        else if(m.getName().equals("Atop the Highest Tower")){
            for (int i = 0; i < standby.size(); i++) {
                if(standby.get(i).getName().equals("Moon Shards in the Sand")){
                    for (int j = 0; j < source.size(); j++) {
                        if(source.get(j) == m){
                            source.set(j, standby.remove(i));
                        }
                    }
                    break;
                }
            }
        }
        else if(m.getName().equals("RC Car Pro!")){
            for (int i = 0; i < standby.size(); i++) {
                if(standby.get(i).getName().equals("RC Car Champ!")){
                    for (int j = 0; j < source.size(); j++) {
                        if(source.get(j) == m){
                            source.set(j, standby.remove(i));
                        }
                    }
                    break;
                }
            }
        }
        else if(m.getName().equals("Jump-Rope Hero")) {
            for (int i = 0; i < standby.size(); i++) {
                if (standby.get(i).getName().equals("Jump-Rope Genius")) {
                    for (int j = 0; j < source.size(); j++) {
                        if (source.get(j) == m) {
                            source.set(j, standby.remove(i));
                        }
                    }
                    break;
                }
            }
        }else if(m.getName().equals("Iceburn Circuit Class A")){
            for (int i = 0; i < standby.size(); i++) {
                if(standby.get(i).getName().equals("Iceburn Circuit Class S")){
                    for (int j = 0; j < source.size(); j++) {
                        if(source.get(j) == m){
                            source.set(j, standby.remove(i));
                        }
                    }
                    break;
                }
            }
        }else if(m.getName().equals("Beach Volleyball: Champ")){
            for (int i = 0; i < standby.size(); i++) {
                if(standby.get(i).getName().equals("Beach Volleyball: Hero of the Beach!")){
                    for (int j = 0; j < source.size(); j++) {
                        if(source.get(j) == m){
                            source.set(j, standby.remove(i));
                        }
                    }
                    break;
                }
            }
        }
        else if(m.checkTags("Peach") && achievementProgress[14] == 12){
            for (int i = 0; i < standby.size(); i++) {
                if(standby.get(i).getName().equals("Peach in the Moon Kingdom")){
                    for (int j = 0; j < source.size(); j++) {
                        if(source.get(j) == m){
                            source.set(j, standby.remove(i));
                        }
                    }
                    break;
                }
            }
        }
        else if(m.getName().equals("Peach in the Moon Kingdom")){
            for (int i = 0; i < standby.size(); i++) {
                String name = standby.get(i).getName();
                if(name.equals("Princess Peach, Home Again!") || name.equals("Hat-and-Seek: Mushroom Kingdom")){
                    source.add(standby.remove(i));
                    Collections.shuffle(source, rnd);
                }
            }
        }
        else if(m.checkTags("Yoshi")){
            for (int i = 0; i < standby.size(); i++) {
                if(standby.get(i).checkTags("Yoshi")&& Integer.parseInt(m.getTags()[1]) == Integer.parseInt(standby.get(i).getTags()[1])-1){
                    ListElement nextYoshi = standby.remove(i);
                    for (int j = 0; j < source.size(); j++) {
                        if(source.get(j) == m){
                            source.set(j, nextYoshi);
                        }
                    }
                    break;
                }
            }
        }
        else if(m.getKingdom().equals("Dark Side")){
            if(m.getName().equals("Arrival at Rabbit Ridge!")){
                for (int i = 0; i < standby.size(); i++) {
                    if (standby.get(i).getKingdom().equals("Dark Side") || standby.get(i).getName().matches("Found with Dark Side Art.*")) {
                        switch (standby.get(i).getName().substring(0, 5)) {
                            case "Yoshi":
                                source.add(standby.get(i));
                                source.add(standby.remove(i));
                                i--;
                                break;
                            case "Fruit":
                                break;
                            default:
                                source.add(standby.remove(i));
                                i--;
                        }
                    }
                }
                Collections.shuffle(source, rnd);
            }
            else if(m.getName().substring(0,5).equals("Yoshi")) {
                for (int i = 0; i < standby.size(); i++) {
                    switch(m.getName()){
                        case "Yoshi Under Siege":
                            if(standby.get(i).getName().equals("Fruit Feast Under Siege")){
                                for (int j = 0; j < source.size(); j++) {
                                    if(source.get(j) == m) {
                                        source.set(j, standby.remove(i));
                                        i--;
                                        break;
                                    }
                                }
                            }
                            break;
                        case "Yoshi on the Sinking Island":
                            if(standby.get(i).getName().equals("Fruit Feast on the Sinking Island")){
                                for (int j = 0; j < source.size(); j++) {
                                    if(source.get(j) == m) {
                                        source.set(j, standby.remove(i));
                                        i--;
                                        break;
                                    }
                                }
                            }
                            break;
                        case "Yoshi's Magma Swamp":
                            if(standby.get(i).getName().equals("Fruit Feast in the Magma Swamp!")){
                                for (int j = 0; j < source.size(); j++) {
                                    if(source.get(j) == m) {
                                        source.set(j, standby.remove(i));
                                        i--;
                                        break;
                                    }
                                }
                            }
                            break;
                        default: break;
                    }
                }
            }
        }

        if((toadette || m.checkTags("Peach")) && m.getTags().length > 0){
            for(String tag : m.getTags()){
                for(int i = 0; i < Lists.ACHIEVEMENT_TAGS.length; i++){
                    if(tag.equals(Lists.ACHIEVEMENT_TAGS[i])){
                        achievementProgress[i]++;
                        //Lists.ACHIEVEMENT_LEVELS[i] contains the levels for tag[i]
                        for(int j: Lists.ACHIEVEMENT_LEVELS[i]){
                            if(achievementProgress[i] == j){
                                for (int k = 0; k < remainingAchievements.size(); k++) {
                                    ListElement toTest = remainingAchievements.get(k);
                                    if(toTest.getTags()[0].equals(tag) && Integer.parseInt(toTest.getTags()[1]) == j){
                                        output.add(remainingAchievements.remove(k));
                                        moonsPulled++;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        if(m.getName().matches("Found with.*Art.*")){
            String kingdom = m.getName().split(" ")[2];
            ListElement checkArt = new NecessaryAction("@Kops Because I'm Bugged", "Cap", true);
            switch(kingdom){
                case "Cap":
                case "Sand":
                case "Bowser's":
                case "Moon":
                    checkArt = new NecessaryAction("Check Hint Art", kingdom, false);
                    break;
                case "Dark":
                    switch(m.getName().split(" ")[5]){
                        case "1":
                            checkArt = new NecessaryAction("Check Cascade Art", "Dark Side", true);
                            break;
                        case "2":
                            checkArt = new NecessaryAction("Check Metro Art", "Dark Side", true);
                            break;
                        case "3":
                            checkArt = new NecessaryAction("Check Mushroom Art", "Dark Side", true);
                            break;
                        case "4":
                            checkArt = new NecessaryAction("Check Cloud Art", "Dark Side", true);
                            break;
                        case "5":
                            checkArt = new NecessaryAction("Check Snow Art", "Dark Side", true);
                            break;
                        case "6":
                            checkArt = new NecessaryAction("Check Seaside Art", "Dark Side", true);
                            break;
                        case "7":
                            checkArt = new NecessaryAction("Check Lost Art", "Dark Side", true);
                            break;
                        case "8":
                            checkArt = new NecessaryAction("Check Luncheon Art", "Dark Side", true);
                            break;
                        case "9":
                            checkArt = new NecessaryAction("Check Lake Art", "Dark Side", true);
                            break;
                        case "10":
                            checkArt = new NecessaryAction("Check Ruined Art", "Dark Side", true);
                            break;
                        default: break;
                    }
                    break;
                default:
                    checkArt = new NecessaryAction("Check Hint Art", kingdom, true);
            }
            output.add(checkArt);
        }
    }

    private List<ListElement> pullAchievements(Random rnd, List<ListElement> inCaseOfArt, List<ListElement> outputInCaseOfArt){
        ArrayList<Moon> achievementSource = new ArrayList<>();
        ArrayList<ListElement> output = new ArrayList<>();
        for(int i = 0; i<remainingAchievements.size(); i++) {
            String name = remainingAchievements.get(i).getName();
            if (name.equals("Rescue Princess Peach") || name.equals("Achieve World Peace") ||
                    name.equals("Power Moon Knight") || name.equals("Power Moon Wizard") ||
                    name.equals("Checkpoint Flagger") || name.equals("World Warper") ||
                    name.equals("Loaded with Coins") || name.equals("Capturing Novice") ||
                    //TODO: count captures dynamically (probably force apprentice but not master)
                    name.equals("Capturing Apprentice") || name.equals("Capturing Master")) {
                output.add(remainingAchievements.remove(i));
                moonsPulled++;
                i--;
            }
        }
        for(int i = 0; i<remainingAchievements.size(); i++){
            if(remainingAchievements.get(i).getLevel() == 1){
                achievementSource.add(remainingAchievements.remove(i));
                i--;
            }
        }
        Collections.shuffle(achievementSource, rnd);
        for(int i = 0; i<15; i++){
            Moon m = achievementSource.remove(0);
            output.add(m);
            if(m.getName().equals("Art Investigator")){
                for (int j = 0; j < inCaseOfArt.size(); j++) {
                    if(inCaseOfArt.get(j).getName().equals("Arrival at Rabbit Ridge!")){
                        inCaseOfArt.add(0, inCaseOfArt.remove(j));
                        pullMoon(inCaseOfArt, outputInCaseOfArt, rnd);
                    }
                }
            }
            moonsPulled++;
            String tag = m.getTags()[0];
            int level = m.getLevel();
            for(int j = 0; j<remainingAchievements.size(); j++){
                Moon test = remainingAchievements.get(j);
                if(test.getLevel() == level+1 && test.getTags()[0].equals(tag)){
                    achievementSource.add(remainingAchievements.remove(j));
                    Collections.shuffle(achievementSource,rnd);
                    break;
                }
            }
        }
        remainingAchievements.addAll(achievementSource);
        return output;
    }

    private int willMeetAchievement(ListElement toTest){
        int achievementsMet = 0;
        for (int i = 0; i < Lists.ACHIEVEMENT_TAGS.length; i++) {
            if (toTest.checkTags(Lists.ACHIEVEMENT_TAGS[i])) {
                for (int j: Lists.ACHIEVEMENT_LEVELS[i]) {
                    if (achievementProgress[i] == j - 1){
                        achievementsMet++;
                        break;
                    }
                }
            }
        }
        if(!moonRockMoonPulled[14] && !toTest.getFirstVisit()){
            boolean moonRock = true;
            for (String moon: Lists.PG_NOT_MOON_ROCK) {
                if (toTest.getName().equals(moon)){
                    moonRock = false;
                    break;
                }
            }
            if(moonRock){
                switch(toTest.getKingdom()){
                    case "Cap":
                        moonRockMoonPulled[0] = true;
                        break;
                    case "Cascade":
                        moonRockMoonPulled[1] = true;
                        break;
                    case "Sand":
                        moonRockMoonPulled[2] = true;
                        break;
                    case "Lake":
                        moonRockMoonPulled[3] = true;
                        break;
                    case "Wooded":
                        moonRockMoonPulled[4] = true;
                        break;
                    case "Cloud":
                        moonRockMoonPulled[5] = true;
                        break;
                    case "Lost":
                        moonRockMoonPulled[6] = true;
                        break;
                    case "Metro":
                        moonRockMoonPulled[7] = true;
                        break;
                    case "Snow":
                        moonRockMoonPulled[8] = true;
                        break;
                    case "Seaside":
                        moonRockMoonPulled[9] = true;
                        break;
                    case "Luncheon":
                        moonRockMoonPulled[10] = true;
                        break;
                    case "Ruined":
                        moonRockMoonPulled[11] = true;
                        break;
                    case "Bowser's":
                        moonRockMoonPulled[12] = true;
                        break;
                    case "Moon":
                        moonRockMoonPulled[13] = true;
                        break;
                    default: break;
                }
            }
            boolean addMRLib = true;
            for (boolean b: moonRockMoonPulled) {
                if(!b){
                    addMRLib = false;
                    break;
                }
            }
            if(addMRLib){
                moonRockMoonPulled[14] = true;
                achievementsMet++;
            }
        }
        return achievementsMet;
    }
}
