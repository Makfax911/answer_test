/**
 * Java. Using search with regexp
 *
 * @see http://stackoverflow.com/questions/20050302/regex-match-two-words
 *
 * @author Sergey Iryupin
 * @version 0.1 dated Apr 3, 2017
 */
import java.util.regex.*;

class SearchUsingRegExp {

    static final String[] msgs = {
        "��� ����",
        "��� ���� ����",
        "������� ���",
        "��� �� ��� ������",
        "�������"
    };

    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("���\\s.*����");
        for (String str : msgs) {
            Matcher matcher = pattern.matcher(str);
            System.out.println(matcher.find());
        }
    }
}