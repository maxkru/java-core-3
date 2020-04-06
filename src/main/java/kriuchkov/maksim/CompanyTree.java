package kriuchkov.maksim;

import java.util.*;

public class CompanyTree {
    private List<Company> roots = new ArrayList<>();
    private List<Company> orphans = new LinkedList<>();

    public CompanyTree() {

    }

    public void add(Company company) {
        if (company.getParentId() == 0)
            roots.add(company);
        else {
            Company parentCandidate = null;
            for (Company rootCompany : roots) {
                parentCandidate = findCompany(rootCompany, company.getParentId());

                if (parentCandidate != null) {
                    parentCandidate.addChild(company);
                    break;
                }
            }
            if (parentCandidate == null) {
                for (Company orphanCompany : orphans) {
                    parentCandidate = findCompany(orphanCompany, company.getParentId());

                    if (parentCandidate != null) {
                        parentCandidate.addChild(company);
                        break;
                    }
                }
            }
            if (parentCandidate == null) {
                orphans.add(company);
            }
        }

        Iterator<Company> it = orphans.iterator();
        while(it.hasNext()) {
            Company orphan = it.next();
            if(company.getId() == orphan.getParentId()) {
                company.addChild(orphan);
                it.remove();
            }
        }

    }

    private Company findCompany(Company c, int id) {
        if(c.getId() == id)
            return c;
        for(Company child : c.getChildren()) {
            Company candidate = findCompany(child, id);
            if(candidate != null) {
                return candidate;
            }
        }
        return null;
    }

    public void print() {
        HashSet<Integer> set = new HashSet<>();
        for(Company root : roots) {
            root.print(0, set);
        }
    }
}
