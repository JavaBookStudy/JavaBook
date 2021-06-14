package algorithm.boj;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Person {
    private String name;
    private int age;
    private String phoneNumber;

    public Person(String name, int age, String phoneNumber) {
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
    }

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
    
}

public class StreamEx {
    public static void main(String[] args) {
    	List<Person> personList = new ArrayList<>();
    	personList.add(new Person("대연", 29, "010-1234-1234"));
    	personList.add(new Person("정수", 23, "010-2341-2341"));
    	personList.add(new Person("주연", 26, "010-3412-3412"));
    	personList.add(new Person("태현", 27, "010-4105-2747"));
    	
    	Map<String, Person> personMap = personList.stream()
    	        .collect(Collectors.toMap(Person::getName, Function.identity()));
    	System.out.println(personMap);
    	
    	Map<String, Person> personMap2 = personList.stream()
    			.filter(person -> person.getAge() > 24)
    			.collect(Collectors.toMap(Person::getName, Function.identity()));
    	System.out.println(personMap2);
    	
    	Stream<String> stream = Stream.of("대연", "정수", null, "주연", null);
    	List<String> filteredList = stream.filter(Objects::nonNull)
    	        .collect(Collectors.toList());
    	System.out.println(filteredList);
    	
    	personList.stream()
        .sorted(Comparator.comparing(Person::getAge))
        .forEach(p -> System.out.println(p.getName()));
    	
    	
    	personList.stream()
        .sorted(Comparator.comparing(Person::getAge).reversed())
        .forEach(p -> System.out.println(p.getName()));
    }
}