## Задание 

1. Используя команду cat в терминале операционной системы Linux, создать
два файла Домашние животные (заполнив файл собаками, кошками,
хомяками) и Вьючные животными заполнив файл Лошадьми, верблюдами и
ослы), а затем объединить их. Просмотреть содержимое созданного файла.
Переименовать файл, дав ему новое имя (Друзья человека).

![1.png](https://github.com/PurosovMV/FinalWork/blob/main/img/1.png?raw=true)

2. Создать директорию, переместить файл туда.

![2.png](https://github.com/PurosovMV/FinalWork/blob/main/img/2.png?raw=true)

3. Подключить дополнительный репозиторий MySQL. Установить любой пакет
из этого репозитория.

![3.png](https://github.com/PurosovMV/FinalWork/blob/main/img/3.png?raw=true)

4. Установить и удалить deb-пакет с помощью dpkg.

![4.png](https://github.com/PurosovMV/FinalWork/blob/main/img/4.png?raw=true)

5. Выложить историю команд в терминале ubuntu

![5.png](https://github.com/PurosovMV/FinalWork/blob/main/img/5.png?raw=true)

6. Нарисовать диаграмму, в которой есть класс родительский класс, домашние
животные и вьючные животные, в составы которых в случае домашних
животных войдут классы: собаки, кошки, хомяки, а в класс вьючные животные
войдут: Лошади, верблюды и ослы).



7. Cоздать базу данных “Друзья человека”
   
```sql
CREATE DATABASE IF NOT EXISTS HumanFriends;
   USE HumanFriends;
```

8. Создать таблицы с иерархией из диаграммы в БД

```sql
CREATE TABLE Commands
(
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name varchar(30),
    description varchar(255)
);


CREATE TABLE AnimalGroup
(
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name varchar(30)
);

CREATE TABLE AnimalGenius
(
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name varchar(30),
    group_id INT,
    FOREIGN KEY (group_id) REFERENCES AnimalGroup (id)
    ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE KennelAnimal
(
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name varchar(30),
    birthDate DATE,
    genius_id INT,
    FOREIGN KEY (genius_id) REFERENCES AnimalGenius (id)
    ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE AnimalCommands
(
    animal_id INT NOT NULL,
    command_id INT NOT NULL,

    PRIMARY KEY (animal_id, command_id),
    FOREIGN KEY (animal_id) REFERENCES KennelAnimal (id)
     ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (command_id) REFERENCES Commands (id)
     ON DELETE CASCADE  ON UPDATE CASCADE
);
```

9.  Заполнить низкоуровневые таблицы именами(животных), командами
которые они выполняют и датами рождения

```sql
USE HumanFriends;

INSERT INTO commands(name)
VALUES
 ('Принести тапки'),
 ('Вертеться в колесе'),
 ('Галоп!'),
 ('Поклон!'),
 ('КАШ!');

INSERT INTO AnimalGroup (name)
VALUES
 ('Вьючные животные'),
 ('Домашние животные');

INSERT INTO AnimalGenius (name, group_id)
VALUES
 ('Лошадь', 1),
 ('Верблюд', 1),
 ('Осел', 1),
 ('Кошка', 2),
 ('Собака', 2),
 ('Хомяк', 2);

INSERT INTO KennelAnimal (name, birthDate, genius_id)
VALUES
 ('Юлий', '2021-02-04', 1),
 ('Гриша', '2022-12-01', 2),
 ('Глеб', '2020-08-24', 3),
 ('Муся', '2022-05-20', 4),
 ('Бим', '2023-01-24', 5),
 ('Бакс', '2022-12-20', 6);
 

INSERT INTO AnimalCommands (animal_id, command_id)
VALUES
 (1, 3), (2, 3), (3, 4),
 (4, 5), (5, 1), (5, 4), (6, 2);
```
10.   Удалив из таблицы верблюдов, т.к. верблюдов решили перевезти в другой
питомник на зимовку. Объединить таблицы лошади, и ослы в одну таблицу.
```sql
USE HumanFriends;
   DELETE FROM KennelAnimal WHERE genius_id = 2;

   CREATE TABLE HorseAndDonkey AS
   SELECT * from KennelAnimal WHERE genius_id = 1
   UNION
   SELECT * from KennelAnimal WHERE genius_id = 3;
```
11.   Создать новую таблицу “молодые животные” в которую попадут все
животные старше 1 года, но младше 3 лет и в отдельном столбце с точностью
до месяца подсчитать возраст животных в новой таблице

```sql
CREATE TABLE YoungAnimals AS
      SELECT id, name, birthDate, 
      datediff(curdate(),birthDate) DIV 31 as age, genius_id 
      from KennelAnimal 
      WHERE date_add(birthDate, INTERVAL 1 YEAR) < curdate() 
            AND date_add(birthDate, INTERVAL 3 YEAR) > curdate();
```

12.   Объединить все таблицы в одну, при этом сохраняя поля, указывающие на
прошлую принадлежность к старым таблицам.

```sql
SELECT id, name, birthDate, genius_id FROM HorseAndDonkey
UNION
SELECT id, name, birthDate, genius_id FROM YoungAnimals;
```

13.   Создать класс с Инкапсуляцией методов и наследованием по диаграмме.
    
14.   Написать программу, имитирующую работу реестра домашних животных.
В программе должен быть реализован следующий функционал:

    14.1 Завести новое животное

    14.2 определять животное в правильный класс

    14.3 увидеть список команд, которое выполняет животное

    14.4 обучить животное новым командам

    14.5 Реализовать навигацию по меню

1.    Создайте класс Счетчик, у которого есть метод add(), увеличивающий̆
значение внутренней̆int переменной̆на 1 при нажатие “Завести новое
животное” Сделайте так, чтобы с объектом такого типа можно было работать в
блоке try-with-resources. Нужно бросить исключение, если работа с объектом
типа счетчик была не в ресурсном try и/или ресурс остался открыт. Значение
считать в ресурсе try, если при заведения животного заполнены все поля
