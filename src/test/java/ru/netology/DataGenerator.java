package ru.netology;

import com.github.javafaker.Faker;
import lombok.Value;
import lombok.val;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {
    private DataGenerator() {
    }

    public static String generateDate(int shift) {
        return LocalDate.now().plusDays(shift).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String generateCity() {
        var arrayCities = new String [] {
                "Салехард", "Анадырь", "Ханты-Мансийск", "Нарьян-Мар", "Биробиджан", "Ярославль", "Челябинск", "Ульяновск",
                "Тюмень", "Тула", "Томск", "Астрахань", "Тверь", "Тамбов", "Смоленск", "Петропавловск-Камчатский","Казань",
                "Белгород", "Архангельск", "Брянск", "Краснодар", "Чита", "Барнаул", "Благовещенск", "Пермь", "Красноярск",
                "Иваново", "Волгоград", "Владивосток", "Махачкала", "Магас", "Нальчик","Черкесск", "Абакан", "Владикавказ",
                "Уфа", "Йошкар-Ола", "Симферополь", "Сыктывкар", "Улан-Удэ", "Петрозаводск", "Элиста", "Горно-Алтайск",
                "Мурманск", "Москва", "Санкт-Петербург", "Магадан", "Липецк", "Курск", "Кострома", "Южно-Сахалинск", "Кызыл",
                "Калининград", "Пенза", "Великий Новгород", "Якутск", "Саранск", "Иркутск", "Чебоксары", "Киров", "Орёл",
                "Красноярск", "Хабаровск", "Майкоп", "Ижевск", "Ставрополь", "Грозный", "Владимир", "Вологда", "Екатеринбург",
                "Воронеж", "Саратов", "Самара", "Рязань", "Ростов-на-Дону", "Псков", "Калуга", "Оренбург", "Омск", "Новосибирск",
                "Нижний Новгород", "Кемерово", "Курган"
        };
        return arrayCities[new Random().nextInt(arrayCities.length)];
    }

    public static String generateName(String locale) {
        Faker faker = new Faker(new Locale(locale));
        String name =  faker.name().lastName().replace('Ё', 'Е')
                + " " + faker.name().firstName().replace('Ё', 'Е');
           return name;
    }

    public static String generatePhone(String locale) {
        Faker faker = new Faker(new Locale(locale));
        return String.valueOf(faker.phoneNumber().cellPhone());
    }

    public static class Registration {
        private Registration() {
        }
        public static UserInfo generateUser(String locale) {
            val user = new UserInfo(generateCity(), generateName(locale), generatePhone(locale));
            return user;
        }
    }

    @Value
    public static class UserInfo {
        String city;
        String name;
        String phone;
    }
}
