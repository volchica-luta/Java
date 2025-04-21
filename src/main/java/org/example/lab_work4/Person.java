package org.example.lab_work4;
/**
 * Класс, представляющий человека с базовыми характеристиками.
 */
class Person {
    private final int id;
    private final String name;
    private final String gender;
    private final String department;
    private final double salary;
    private final String birthDate;

    /**
     * Создает новый экземпляр человека.
     *
     * @param id         уникальный числовой идентификатор человека (положительное число)
     * @param name       полное имя человека (не может быть пустым или null)
     * @param gender     пол человека, допустимые значения: "Male" или "Female" (регистрозависимо)
     * @param department название подразделения, в котором работает человек
     * @param salary     размер зарплаты (неотрицательное число)
     * @param birthDate  дата рождения в формате "день.месяц.год" ("dd.MM.yyyy")
     */
    public Person(int id, String name, String gender, String department, double salary, String birthDate) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.department = department;
        this.salary = salary;
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return String.format(
                "Person(id=%d, name='%s', gender='%s', department='%s', salary=%.2f, birthDate='%s')",
                id, name, gender, department, salary, birthDate
        );
    }
}