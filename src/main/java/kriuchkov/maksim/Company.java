package kriuchkov.maksim;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Company {
    private int id;
    private int parentId;
    private List<Company> children;

    public Company(int id, int parentId) {
        this.id = id;
        this.parentId = parentId;
        children = new ArrayList<>();
    }


    List<Company> getChildren() {
        return children;
    }

    public int getId() {
        return id;
    }

    public int getParentId() {
        return parentId;
    }

    void print(int level, Set<Integer> levelsForVertical) {
        System.out.print(id);
        if(children.size() > 0)
            System.out.println();
        for (int i = 0; i < children.size(); i++) {
            printLevelAlign(level, levelsForVertical);
            System.out.print(((i == children.size() - 1) ? "\\-- " : "+-- "));
            if(i == 0)
                levelsForVertical.add(level);
            children.get(i).print(level + 1, levelsForVertical);
        }
        levelsForVertical.remove(level);
        if(children.size() == 0)
            System.out.println();
    }

    void printLevelAlign(int level, Set<Integer> levelsForVertical) {
        for (int i = 0; i < level; i++) {
            System.out.print(levelsForVertical.contains(i) ? "|   " : "    ");
        }
    }

    void addChild(Company child) {
        if(child.getParentId() != this.getId())
            throw new IllegalArgumentException("Child candidate has incorrect parentId!");

        this.getChildren().add(child);
    }
}