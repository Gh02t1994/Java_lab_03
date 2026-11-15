package game.command;

public class Parser {
    private boolean isNumber(String str) {
        if (str == null)
            return false;
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private Command check_arguments(String[] arr_words) {
        int count_args = arr_words.length - 1;
        String[] arguments = new String[count_args];
        System.arraycopy(arr_words, 1, arguments, 0, count_args);

        String name_command = arr_words[0];

        switch (name_command) {
            case "startGame":
            case "endTurn":
            case "print":
                if (count_args == 0) {
                    return new Command(name_command, arguments);
                }
                break;

            case "attack":
                if (count_args == 2) {
                    return new Command(name_command, arguments);
                }
                break;

            case "initMap":
                if (count_args == 2 && isNumber(arguments[0]) && isNumber(arguments[1])) {
                    return new Command(name_command, arguments);
                }
                break;

            case "move":
                if (count_args == 3 && isNumber(arguments[1]) && isNumber(arguments[2])) {
                    return new Command(name_command, arguments);
                }
                break;

            case "addUnit":
            case "build":
                if (count_args == 4 && isNumber(arguments[2]) && isNumber(arguments[3])) {
                    return new Command(name_command, arguments);
                }
                break;

            default:
                throw new IllegalArgumentException("Неверные команды");
        }

        throw new IllegalArgumentException("Неверное количество или тип аргументов");
    }

    public Command parse(String line) {
        if (line == null || line.trim().isEmpty())
            throw new IllegalArgumentException("Пустая команда");

        String[] arr_words = line.trim().split("\\s+");

        return check_arguments(arr_words);
    }
}
