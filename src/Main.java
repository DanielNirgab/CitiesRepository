import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<City> citiesList = CitiesUtil.readFileAndParse();
//        CitiesUtil.getFilteredCitiesByName(citiesList); // Фильтрация по имени
//        printCities(citiesList);
//        CitiesUtil.getFilteredCitiesByDistrictAndName(citiesList); // Фильтрация по окруту и имени
//        printCities(citiesList);
//
//        // Etalone code
//        CitiesUtil.sortByNameV1(citiesList);
//        printCities(citiesList);
//        CitiesUtil.sortByNameV2(citiesList);
//        printCities(citiesList);
//        CitiesUtil.sortByDistrictAndName(citiesList);
//        printCities(citiesList);

//        CitiesUtil.getMaxPopulation(citiesList);
        CitiesUtil.countOfDistricts(citiesList);
    }

    public static void printCities(List<City> cities) {
        cities.forEach(System.out::println);
    }



}