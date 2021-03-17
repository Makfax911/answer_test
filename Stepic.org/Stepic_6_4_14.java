import java.util.*;
import java.util.function.*;

public class Stepic_6_4_14 {

    public static void main(String[] args) {
        // Random variables
        String randomFrom = "..."; // ��������� ��������� ������. ������ ������� �� ��������������.
        String randomTo = "...";  // ��������� ��������� ������. ������ ������� �� ��������������.
        int randomSalary = 100;  // ��������� ��������� ����� ������������� �����. ������ ������� ��� ��������������.

        // �������� ������ �� ���� �������� ���������.
        MailMessage firstMessage = new MailMessage(
                "Robert Howard",
                "H.P. Lovecraft",
                "This \"The Shadow over Innsmouth\" story is real masterpiece, Howard!"
        );

        assert firstMessage.getFrom().equals("Robert Howard") : "Wrong firstMessage from address";
        assert firstMessage.getTo().equals("H.P. Lovecraft") : "Wrong firstMessage to address";
        assert firstMessage.getContent().endsWith("Howard!") : "Wrong firstMessage content ending";

        MailMessage secondMessage = new MailMessage(
                "Jonathan Nolan",
                "Christopher Nolan",
                "����, ������ ��� ��� ������ ������ ����, ����� ����������� ��� �������� ������� �. ��� �� ������!"
        );

        MailMessage thirdMessage = new MailMessage(
                "Stephen Hawking",
                "Christopher Nolan",
                "� ��� � �� ����� ������������."
        );

        List<MailMessage> messages = Arrays.asList(
                firstMessage, secondMessage, thirdMessage
        );

        // �������� ��������� �������
        MailService<String> mailService = new MailService<>();

        // ��������� ������ ����� �������� ��������
        messages.stream().forEachOrdered(mailService);

        // ��������� � �������� ������� "��������� �����",
        // ��� �� ���������� ����� �������� ������ ���������, ������� ���� ��� ����������
        Map<String, List<String>> mailBox = mailService.getMailBox();

        assert mailBox.get("H.P. Lovecraft").equals(
                Arrays.asList(
                        "This \"The Shadow over Innsmouth\" story is real masterpiece, Howard!"
                )
        ) : "wrong mailService mailbox content (1)";

        assert mailBox.get("Christopher Nolan").equals(
                Arrays.asList(
                        "����, ������ ��� ��� ������ ������ ����, ����� ����������� ��� �������� ������� �. ��� �� ������!",
                        "� ��� � �� ����� ������������."
                )
        ) : "wrong mailService mailbox content (2)";

        assert mailBox.get(randomTo).equals(Collections.<String>emptyList()) : "wrong mailService mailbox content (3)";


        // �������� ������ �� ���� �������
        Salary salary1 = new Salary("Facebook", "Mark Zuckerberg", 1);
        Salary salary2 = new Salary("FC Barcelona", "Lionel Messi", Integer.MAX_VALUE);
        Salary salary3 = new Salary(randomFrom, randomTo, randomSalary);

        // �������� ��������� �������, ��������������� ��������
        MailService<Integer> salaryService = new MailService<>();

        // ��������� ������ ������� �������� ��������
        Arrays.asList(salary1, salary2, salary3).forEach(salaryService);

        // ��������� � �������� ������� "��������� �����",
        //   ��� �� ���������� ����� �������� ������ �������, ������� ���� ��� ����������
        Map<String, List<Integer>> salaries = salaryService.getMailBox();
        assert salaries.get(salary1.getTo()).equals(Arrays.asList(1)) : "wrong salaries mailbox content (1)";
        assert salaries.get(salary2.getTo()).equals(Arrays.asList(Integer.MAX_VALUE)) : "wrong salaries mailbox content (2)";
        assert salaries.get(randomTo).equals(Arrays.asList(randomSalary)) : "wrong salaries mailbox content (3)";
    }

    static class MailMessage {
        private String from;
        private String to;
        private String textMessage;

        public MailMessage(String from, String to, String text) {
            this.from = from;
            this.to = to;
            this.textMessage = text;
        }

        public String getFrom() {
            return this.from;
        }

        public String getTo() {
            return this.to;
        }

        public String getContent() {
            return this.textMessage;
        }

    }

    static class Salary {
        public Salary(String organization, String name, int salary) {
        }

        public Map<String, List<String>> getMailBox() {
            return null;
        }

        public String getTo() {
            return null;
        }
    }

    static class MailService<T> {
        public Map<T, List<T>> getMailBox() {
            return null;
        }
    }
}