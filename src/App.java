import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        // Создание объекта Scanner для считывания ввода пользователя с консоли
        Scanner scanner = new Scanner(System.in);

        try {
            // Запрос пользовательского ввода данных
            System.out.println("Введите данные: 'Фамилия' 'Имя' 'Отчество' 'Дата рождения' 'Номер телефона' 'Пол' (в указанном порядке, разделенные пробелом):");
            String input = scanner.nextLine();
            // Разделение введенной строки на отдельные значения
            String[] data = input.split(" ");

            // Проверка количества введенных данных
            if (data.length != 6) {
                throw new IllegalArgumentException("Неверное количество данных");
            }

            // Извлечение данных из массива
            String lastName = data[0];
            String firstName = data[1];
            String middleName = data[2];
            String birthDate = data[3];
            long phoneNumber = Long.parseLong(data[4]);
            char gender = data[5].charAt(0);

            // Проверка корректности введенного пола
            if (gender != 'm' && gender != 'f') {
                throw new IllegalArgumentException("Неверный формат пола");
            }

            // Запись данных в файл
            try (FileWriter writer = new FileWriter(lastName + ".txt", true)) {
                writer.write(lastName + " " + firstName + " " + middleName + " " + birthDate + " " + phoneNumber + " " + gender + "\n");
                System.out.println("Данные успешно записаны в файл " + lastName + ".txt");
            } catch (IOException e) {
                // Обработка исключения при возникновении проблемы с записью в файл
                e.printStackTrace();
            }

        } catch (NumberFormatException e) {
            // Обработка исключения при неверном формате номера телефона
            System.out.println("Ошибка: Неверный формат номера телефона");
        } catch (IllegalArgumentException e) {
            // Обработка исключения при неверном вводе данных
            System.out.println("Ошибка: " + e.getMessage());
        } finally {
            // Закрытие Scanner для предотвращения утечек ресурсов
            scanner.close();
        }
    }
}
