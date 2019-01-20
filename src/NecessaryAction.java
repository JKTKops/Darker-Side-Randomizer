public class NecessaryAction extends Moon {
    private String action;
    private String kingdom;
    private boolean firstVisit;

    NecessaryAction(String act, String king, boolean fV){
        super();
        action = act;
        kingdom = king;
        firstVisit = fV;
    }

    @Override
    public String toString() {
        return "{\n\t\"Action\": \""+action+"\",\n\t\"kingdom\": \""+kingdom+"\",\n\t\"first\": "+ Boolean.toString(firstVisit)+"\n}";
    }

    public String getName() {
        return action;
    }

    public String getKingdom() {
        return kingdom;
    }

    public Boolean getFirstVisit() {
        return firstVisit;
    }
}
