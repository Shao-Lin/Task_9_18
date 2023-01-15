package Task;

import java.util.ArrayList;
import java.util.List;

public class Logic {
    public static List<Integer> createNewList(List<Integer> list, List<Integer> list2) {
        List<Integer> list3 = new ArrayList<>();

        if (list.size() == 0 || list2.size() == 0 ) {
        return list 
        }


        int numberElementList1 = -1;
        int numberElementList2 = list.size();
        for (int i = 0; i < list.size(); i++) {
            numberElementList1++;
            list3.add(list.get(numberElementList1));
        }
        for (int i = 1; i < list.size() ; i += 2) {
                list3.set(i, list2.get(i));
            }

        if(list.size() < list2.size()){
        for (int i = 0; i < list2.size() - list.size(); i++) {
            list3.add(list2.get(numberElementList2));
            numberElementList2++;
        }
       }
        return list3;
        }

    }
