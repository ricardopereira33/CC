import java.util.Comparator; 

public class MyComparator implements Comparator<ServerInfo> {

    public int compare(ServerInfo s1, ServerInfo s2) {

        return (int)(s1.getMedia() - s2.getMedia());
    }
}
