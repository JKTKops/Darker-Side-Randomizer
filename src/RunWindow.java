import javax.swing.*;
import java.util.*;
import java.util.function.Predicate;

public class RunWindow extends JFrame {
    private JPanel MainScreen;
    private JRadioButton fullList;
    private JRadioButton cap;
    private JRadioButton cascade;
    private JRadioButton sand;
    private JRadioButton lake;
    private JRadioButton wooded;
    private JRadioButton byVisit;
    private JRadioButton byKingdom;
    private JRadioButton cloud;
    private JRadioButton metro;
    private JRadioButton snow;
    private JRadioButton seaside;
    private JRadioButton luncheon;
    private JRadioButton ruined;
    private JRadioButton bowsers;
    private JRadioButton moon;
    private JRadioButton mushroom;
    private JRadioButton darkSide;
    private JRadioButton achievements;
    private JButton backButton;
    private JScrollPane listViewPane;
    private Map<String, JList<Moon>> moonLists;
    private final List<Moon> generatedList;

    RunWindow(List<Moon> setGeneratedList, JFrame parentWindow) {
        super("Darker Side Randomizer");

        moonLists = new HashMap<>();
        generatedList = setGeneratedList;

        ButtonGroup fullListSorts = new ButtonGroup();
        fullListSorts.add(byVisit);
        fullListSorts.add(byKingdom);

        ButtonGroup viewOptions = new ButtonGroup();
        //<editor-fold desc="Populating group">
        viewOptions.add(fullList);
        viewOptions.add(cap);
        viewOptions.add(cascade);
        viewOptions.add(sand);
        viewOptions.add(lake);
        viewOptions.add(wooded);
        viewOptions.add(cloud);
        viewOptions.add(metro);
        viewOptions.add(snow);
        viewOptions.add(seaside);
        viewOptions.add(luncheon);
        viewOptions.add(ruined);
        viewOptions.add(bowsers);
        viewOptions.add(moon);
        viewOptions.add(mushroom);
        viewOptions.add(darkSide);
        viewOptions.add(achievements);
        //</editor-fold>

        JList<Moon> toSet = createJList(generatedList);
        moonLists.put("full", toSet);
        listViewPane.setViewportView(toSet);

        attachBackButton(parentWindow);
        attachListViewButtons(viewOptions);
        attachSortButtons();
    }

    private void attachBackButton(JFrame parentWindow) {
        backButton.addActionListener(e -> {
            parentWindow.setVisible(true);
            this.dispose();
        });
    }
    private void attachListViewButtons(ButtonGroup group) {
        fullList.addItemListener(e -> {
            if (fullList.isSelected()) {
                byVisit.setEnabled(true);
                byKingdom.setEnabled(true);
                listViewPane.setViewportView(moonLists.get("full"));
            } else {
                byVisit.setEnabled(false);
                byKingdom.setEnabled(false);
            }
        });

        Iterator<AbstractButton> buttons = group.getElements().asIterator();
        for (JRadioButton button; buttons.hasNext();) {
            button = (JRadioButton) buttons.next(); // check first hasNext() before so that we get the last one

            final JRadioButton finalButton = button; // duplicate reference to satisfy compiler bc lambdas

            if (finalButton == fullList) continue;
            finalButton.addActionListener(e -> {
                if (finalButton.isSelected()) {
                    setKingdomFilter(finalButton.getText());
                }
            });
        }
    }

    private void attachSortButtons() {
        byVisit.addActionListener(e -> {
            if (byVisit.isSelected()) {
                JList currentList = (JList) listViewPane.getViewport().getView();
                ((ListModel) currentList.getModel()).sort(Moon::compareByVisit);
            }
        });

        byKingdom.addActionListener(e -> {
            if (byKingdom.isSelected()) {
                JList currentList = (JList) listViewPane.getViewport().getView();
                ((ListModel) currentList.getModel()).sort(Moon::compareByKingdom);
            }
        });
    }

    private JList<Moon> createJList(List<Moon> list) {
        ListModel temp = new ListModel(list);
        JList<Moon> ret  = new JList<>();
        ret.setModel(temp);
        return ret;
    }

    private void setKingdomFilter(String kingdom) {
        JList<Moon> toSet = moonLists.get(kingdom);
        if (toSet == null) {
            toSet = createJList(generatedList);
            ((ListModel) toSet.getModel()).removeIf(m -> !m.getKingdom().equals(kingdom));
            moonLists.put(kingdom, toSet);
        }
        listViewPane.setViewportView(toSet);
    }

    JPanel getMainScreen() {
        return MainScreen;
    }

    private class ListModel extends AbstractListModel<Moon> {
        List<Moon> list;

        ListModel(List<Moon> setList) {
            list = new ArrayList<>(setList);
        }

        @Override
        public int getSize() {
            return list.size();
        }

        @Override
        public Moon getElementAt(int index) {
            return list.get(index);
        }

        void sort(Comparator<Moon> comparator) {
            list.sort(comparator);
            fireContentsChanged(this, 0, list.size());
        }

        void removeIf(Predicate<Moon> condition) {
            list.removeIf(condition);
            fireIntervalRemoved(this, 0, 0);
        }
    }
}
