import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.View;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    private JRadioButton lost;
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
    private JRadioButton showAll;
    private JRadioButton showUncollected;
    private JRadioButton showCollected;
    private JTextField searchBar;
    private JLabel seedLabel;
    private final List<Moon> generatedList;
    private MouseListener crossOffListener;

    private enum ViewOptions { All, Collected, Uncollected }
    private enum SortOptions { Kingdom, Visit }

    private String filterKingdom = "Full List";
    private ViewOptions filterView = ViewOptions.All;
    private SortOptions sort = SortOptions.Visit;

    RunWindow(long seed, List<Moon> setGeneratedList, JFrame parentWindow) {
        super("Darker Side Randomizer");

        seedLabel.setText("Seed: " + seed);
        generatedList = setGeneratedList;

        ButtonGroup fullListSorts = new ButtonGroup();
        fullListSorts.add(byVisit);
        fullListSorts.add(byKingdom);

        ButtonGroup listViewButtons = new ButtonGroup();
        //<editor-fold desc="Populating group">
        listViewButtons.add(fullList);
        listViewButtons.add(cap);
        listViewButtons.add(cascade);
        listViewButtons.add(sand);
        listViewButtons.add(lake);
        listViewButtons.add(wooded);
        listViewButtons.add(cloud);
        listViewButtons.add(lost);
        listViewButtons.add(metro);
        listViewButtons.add(snow);
        listViewButtons.add(seaside);
        listViewButtons.add(luncheon);
        listViewButtons.add(ruined);
        listViewButtons.add(bowsers);
        listViewButtons.add(moon);
        listViewButtons.add(mushroom);
        listViewButtons.add(darkSide);
        listViewButtons.add(achievements);
        //</editor-fold>
        ButtonGroup viewOptionButtons = new ButtonGroup();
        viewOptionButtons.add(showAll);
        viewOptionButtons.add(showUncollected);
        viewOptionButtons.add(showCollected);

        attachCrossingOff();

        attachBackButton(parentWindow);
        attachListViewButtons(listViewButtons);
        attachViewOptionButtons();
        attachSortButtons();
        attachSearchBar();

        updateList();
    }

    private void attachListViewButtons(ButtonGroup group) {
        fullList.addItemListener(e -> {
            if (fullList.isSelected()) {
                byVisit.setEnabled(true);
                byKingdom.setEnabled(true);
            } else {
                byVisit.setEnabled(false);
                byKingdom.setEnabled(false);
            }
        });

        Enumeration<AbstractButton> buttons = group.getElements();
        for (JRadioButton button; buttons.hasMoreElements();) {
            button = (JRadioButton) buttons.nextElement();
            JRadioButton finalButton = button;

            finalButton.addActionListener(e -> {
                if (finalButton.isSelected()) {
                    filterKingdom = finalButton.getText();
                    updateList();
                }
            });
        }
    }

    private void attachViewOptionButtons() {
        showAll.addActionListener(e -> {
            if (showAll.isSelected()) {
                filterView = ViewOptions.All;
                updateList();
            }
        });
        showCollected.addActionListener(e -> {
            if (showCollected.isSelected()) {
                filterView = ViewOptions.Collected;
                updateList();
            }
        });
        showUncollected.addActionListener(e -> {
            if (showUncollected.isSelected()) {
                filterView = ViewOptions.Uncollected;
                updateList();
            }
        });
    }

    private void attachSortButtons() {
        byKingdom.addActionListener(e -> {
            if (byKingdom.isSelected()) {
                sort = SortOptions.Kingdom;
                updateList();
            }
        });
        byVisit.addActionListener(e -> {
            if (byVisit.isSelected()) {
                sort = SortOptions.Visit;
                updateList();
            }
        });
    }

    private void attachSearchBar() {
        searchBar.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateList();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateList();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateList();
            }
        });
    }

    private void updateList() {
        Predicate<Moon> kingdomFilter;
        Predicate<Moon> collectedFilter;
        Predicate<Moon> searchFilter = m -> m.getName().toLowerCase().contains(searchBar.getText().toLowerCase());

        if (filterKingdom.equals("Full List")) {
            kingdomFilter = m -> true;
        } else {
            kingdomFilter = m -> m.getKingdom().equals(filterKingdom);
        }

        switch (filterView) {
            case Collected:
                collectedFilter = Moon::getCrossedOff;
                break;
            case Uncollected:
                collectedFilter = m -> !m.getCrossedOff();
                break;
            case All:
            default:
                collectedFilter = m -> true;
                break;
        }

        ListModel temp = new ListModel(
                generatedList.stream()
                .filter(searchFilter)
                .filter(kingdomFilter)
                .filter(collectedFilter)
                .collect(Collectors.toList()));
        if (filterKingdom.equals("Full List")) {
            Comparator<Moon> sortComparator;
            switch (sort) {
                case Kingdom:
                    sortComparator = Moon::compareByKingdom;
                    break;
                case Visit:
                default: // shouldn't happen
                    sortComparator = Moon::compareByVisit;
                    break;
            }
            temp.sort(sortComparator);
        }
        listViewPane.setViewportView(createJList(temp));
    }

    private JList<Moon> createJList(ListModel listModel) {
        JList<Moon> ret  = new JList<>();
        ret.setModel(listModel);
        ret.addMouseListener(crossOffListener);
        return ret;
    }

    private void attachBackButton(JFrame parentWindow) {
        backButton.addActionListener(e -> {
            parentWindow.setVisible(true);
            this.dispose();
        });
    }

    private void attachCrossingOff() {
        crossOffListener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Object c = e.getSource();
                if (!(c instanceof JList)) {
                    return;
                }
                JList list = (JList) c;
                if (e.getClickCount() == 2 || SwingUtilities.isRightMouseButton(e)) {
                    int index = list.locationToIndex(e.getPoint());
                    if (index >= 0) {
                        ((Moon) list.getModel().getElementAt(index)).toggleCrossedOff();
                        listViewPane.updateUI();
                    }
                }
            }
        };
    }

    JPanel getMainScreen() {
        return MainScreen;
    }

    private class ListModel extends AbstractListModel<Moon> {
        List<Moon> list;

        ListModel(List<Moon> setList) {
            list = new ArrayList<>(setList);
        }

        ListModel(ListModel setList) {
            list = setList.getList();
        }

        private List<Moon> getList() {
            return list;
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

        Stream<Moon> stream () {
            return list.stream();
        }
    }
}
