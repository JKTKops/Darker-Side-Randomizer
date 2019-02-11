public class NecessaryAction extends ListElement {
    private String action;

    NecessaryAction(String act, String king, boolean fV){
        super(act, king, fV);
        action = act;
    }

    @Override
    public String toString() {
        /* A useful debugging toString alternative.
        return "{\n\t\"Action\": \""+action+"\",\n\t\"kingdom\": \""+kingdom+"\",\n\t\"first\": "+ Boolean.toString(firstVisit)+"\n}";
        */
        if (getCrossedOff()) {
            return "<html><strike>" + action + "</strike></html>";
        }
        return action;
    }

    @Override
    public String getName() {
        return action;
    }
}
