class Stepic_2_4_10 {

    static String[] roles = {"����������", "����� ���������", "������� ����������", "���� �����"};
    static String[] textLines = {
            "����������: � ��������� ���, �������, � ���, ����� �������� ��� ������������� ��������: � ��� ���� �������.",
            "����� ���������: ��� �������?",
            "������� ����������: ��� �������?",
            "����������: ������� �� ����������, ���������. � ��� � ��������� ������������.",
            "����� ���������: ��� �� ��!",
            "������� ����������: ��� �� ���� ������, ��� �����!",
            "���� �����: ������� ����! ��� � � ��������� ������������!"
    };

    public static void main(String[] args) {
        System.out.println(printTextPerRole(roles, textLines));
    }

    /**
     * Group the text of the play script by roles
     *
     * @param roles array of names of roles
     * @param textLines play script, each line has the format "role: text"
     * @return lines where the text is grouped by role and numbered
     */
    static String printTextPerRole(String[] roles, String[] textLines) {
        StringBuilder[] textByRoles = new StringBuilder[roles.length];
        for (int i = 0; i < roles.length; i++) {
            textByRoles[i] = new StringBuilder(roles[i] + ":");
        }
        for (int i = 0; i < textLines.length; i++) {
            String[] splitLine = textLines[i].split(": ", 2);
            String role = splitLine[0];
            String text = splitLine[1];
            int j = 0;
            while (!roles[j].equals(role)) {
                j++;
            }
            textByRoles[j].append("\n" + (i + 1) + ") " + text);
        }
        String result = "";
        for (StringBuilder line : textByRoles) {
            result += line + "\n\n";
        }
        return result;
    }
}