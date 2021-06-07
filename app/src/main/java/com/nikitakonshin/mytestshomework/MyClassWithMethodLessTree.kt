package com.nikitakonshin.mytestshomework


class MyClassWithMethodLessTree {
    companion object{
        fun getArray() = arrayOf(1,2,3,4)

        fun factorial(number: Int): Int{
            var cur = 1;
            for (i in 1..number){
                cur *= i
            }
            return cur
        }

        fun getPersons(name: String): List<Person>? {
            val persons = arrayListOf<Person>(
                Person("Anna", 16),
                Person("Viktor", 19),
                Person("Bob", 20),
                Person("Sam", 36),
            )
            val person = persons.filter { it.name.equals(name, ignoreCase = true) }
            if (person.isEmpty()){
                return null
            }
            return person
        }
    }
}

data class Person(
    var name: String,
    var age: Int,
)