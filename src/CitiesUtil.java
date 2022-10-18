import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class CitiesUtil {

    public static List<City> readFileAndParse () {
        List<City> cities = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File("city_ru.csv")); // Получаем данные из файла
            while (scanner.hasNextLine()) { // Заполняем массив
                cities.add(parse(scanner.nextLine()));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
        }
        return cities;
    }

    private static City parse (String line) {
        Scanner scanner = new Scanner(line);
        scanner.useDelimiter(";"); // разделитель в строке
        scanner.skip("\\d*"); // пропускаем нумерацию
        String name = scanner.next();
        String region = scanner.next();
        String district = scanner.next();
        int population = scanner.nextInt();
        String foundation = null;
        if (scanner.hasNext()) {  // проверяем наличие информации об основании
            foundation = scanner.next();
        }
        scanner.close();

        return new City(name, region, district, population, foundation);
    }

    //Выводим список в консоль


    public static List<City> getFilteredCitiesByName(List<City> citiesList) {
       return citiesList.stream()
                .sorted(Comparator.comparing(City::getName))
                .collect(Collectors.toList());
    }

    public static List<City> getFilteredCitiesByDistrictAndName(List<City> citiesList) {
        return citiesList.stream()
                // Через компоратор сравниваем сначала по округу, затем по имени
                .sorted(Comparator.comparing(City::getDistrict).thenComparing(City::getName))
                .collect(Collectors.toList());
    }

    public static void sortByNameV1(List<City> cities) {
    cities.sort((c1, c2) -> c1.getName().compareToIgnoreCase(c2.getName()));
    }

    public static void sortByNameV2(List<City> cities) {
        cities.sort(new Comparator<City>() {
            @Override
            public int compare(City o1, City o2) {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        });
    }

    public static void sortByDistrictAndName(List<City> cities) {
        cities.sort(Comparator.comparing(City::getDistrict).thenComparing(City::getName));
    }

    private static City[] ListToArray(List<City> cities) {
        return cities.toArray(new City[0]);
            }

    public static void getMaxPopulation(List<City> cities) {
        City[] citiesArray = ListToArray(cities);
        int max = 0;
        int index = 0;
        for (int i = 0; i < citiesArray.length; i++) {
            if (citiesArray[i].getPopulation() >= max) {
                max = citiesArray[i].getPopulation();
                index = i;
            }
        }
        System.out.println("["+index+"] = " + max);
        }

    public static void countOfDistricts(List<City> cities) {
        Map<String, Long> cities1 = cities.stream()
                .collect(Collectors.groupingBy(City::getRegion, Collectors.counting()));
        for (Map.Entry entry : cities1.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }

    }

    }
