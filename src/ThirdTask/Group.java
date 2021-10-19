package ThirdTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class Group
{
    private final String groupName;
    public ConcurrentHashMap<Integer, List<String>> groupList = new ConcurrentHashMap<>();

    public Group(String groupName, int studentsCount)
    {
        this.groupName = groupName;

        initializeGroup(studentsCount);
    }

    public String getName()
    {
        return groupName;
    }

    private void initializeGroup(int studentsCount)
    {
        for (int i = 0; i < studentsCount; i++){
            groupList.put(i + 1, new ArrayList<>());
        }
    }
}
