### Инструкция по работе с проектом

#### Запуск и работа с консолью

1. Откройте проект в вашей IDE.
2. Запустите функцию`main` `fun main()`.
3. Вводите команды в консоль в следующем формате:

##### Создание новой таблицы
- Команда: `Создать новую таблицу Имя_таблицы Тип_данных Тип_данных Тип_данных Тип_данных Тип_данных Тип_данных`
  Из-за того, что проект на котлине и надо вручную прописать все комбинации можно использовать такие параметры:
 `Int String Double Int Double Boolean,   
  String String String String Double String,  
  Int Double Double Double Double Boolean,   
  Int Double String String String String.`
- Пример: `кошки Int String Double Int Double Boolean`
- **Примечания:**
    - Имя таблицы и типы данных разделяются пробелами.
    - Имя таблицы не должно содержать пробелы и может включать только русские и латинские символы.
    - Таблица должна содержать ровно шесть колонок.
    - Допустимые типы данных: `Int`, `String`, `Double`, `Boolean`.

##### Добавление значений в таблицу
- Команда: `добавить Имя_таблицы значение значение значение значение значение значение`
- Пример: `добавить кошки 1 2.45656 Маруся true 456 5.4566`
- **Примечания:**
    - Вводите значения, соответствующие типам данных колонок таблицы.

##### Удаление таблицы
- Команда: `удалить Имя_таблицы`

##### Сохранение таблицы в файл
- Команда: `сохранить Имя_таблицы расположение`
- Пример: `сохранить кошки D:\загрузки\familyTrees\cats.txt`

##### Вывод всей таблицы
- Команда: `показать Имя_таблицы вид_сортировки`
- Пример: `показать кошки по_возрастанию`
- Допустимые значения для вид_сортировки: `по_возрастанию`, `по_убыванию`

##### Вывод определенной колонки
- Команда: `показать Имя_таблицы номер_колонки`
- Пример: `показать кошки 1колонку`
- Допустимые значения для номер_колонки: `1колонку`, `2колонку`, `3колонку`, `4колонку`, `5колонку`, `6колонку`

---

#### Работа без консоли

1. Запустите метод `main` в классе `mvp.refactor_by_solid.mvp.model.tree.Main`.

##### Создание конструктора с 6 параметризованными типами
```kotlin
Tree<Int, String, String, Int, String, Boolean> dogs = new Tree<>();
```
Добавление значений в таблицу
```kotlin
dogs.add(1111, "Бобик", "дворняга", 4, "черный", false);
dogs.add(1112, "Тузик", "дворняга", 6, "пятнистый", false);
dogs.add(1113, "Тобик", "дворняга", 5, "серый", false);
```
Получение ссылки на класс `TreeSorter`
```kotlin
TreeSorter treeSorter = dogs.getTreeSorter();
```
Создание экземпляра класса `Query`
```kotlin
Query q = new Query();
q.setTreeSorter(treeSorter);
```
Использование методов класса `Query`
* Вывод содержимого в определенном порядке:
```kotlin
q.getByAll("по возрастанию");
q.getByAll("по убыванию");
```
* Вывод значений определенной колонки:
``` kotlin 
  q.getByA("по возрастанию");
  q.getByA("по убыванию");
  q.getByB("по возрастанию");
  q.getByB("по убыванию");
  q.getByC("по возрастанию");
  q.getByC("по убыванию");
  q.getByD("по возрастанию");
  q.getByD("по убыванию");
  q.getByE("по возрастанию");
  q.getByE("по убыванию");
  q.getByF("по возрастанию");
  q.getByF("по убыванию");
```
#### Сохранение таблицы в файл
1. Создание экземпляра класса `TreeFileManager`
```kotlin
 TreeFileManager treeFileManager = new TreeFileManager();
 ```
2. Передача `treeSorter`
```kotlin
treeFileManager.setTreeSorter(treeSorter);
```
3. Сохранение таблицы в файл:
   ```kotlin
   treeFileManager.writeToFile("D:\\загрузки\\familyTrees\\dogs.txt");
   ```
4. Чтение таблицы из файла:
```kotlin
treeFileManager.readFromFile("D:\\загрузки\\familyTrees\\dogs.txt");
```

                     Принцип единственной ответственности.
Класс `Tree` был разделен на: `OrderSettings`, `Tree`, `TreeData`, `TreeIterator`, `TreeSorter`.
Класс `Service` был разделен на: `Service` и `CommandProcessor`
Класс `Info` был разделен на: `ObjectDisplayer` и `ObjectSaver`.
Класс `Messages` был разделен на: `displayMessages` и `MessageSaver`.
Каждый класс и интерфейс имеет относительную единственную ответственность.



						  Принцип открытости/закрытости.
Добавлен интерфейс `IMockTextUtils` в нем метод fun `debug(message: String)`хотя он нарушает структуру mvp (не было времени придумывать соответствующее) реализованный в классе `TextUtils` также этот класс реализует `fun tokenize(line: String): List<String>` интерфейса `ITextUtils`.  
Также можно добавить новую сортировку в новом интерфейсе и.т.д.



						 Принцип подстановки Барбары Лисков.
Интерфейсы, такие как `ITreeIterator`, `ITreeSorter` и `ITreeData` и.т.д. гарантируют, что производные классы
могут быть заменены на базовые интерфейсы без ущерба для корректности программы.




						   Принцип разделения интерфейсов.
`IListUtils` и `ListUtils` были удалены за ненадобностью.
Из `RegexUtils` и `IRegexUtils` были удалены:
`fun replaceTextAll(text: String?, pattern: String?, replacement: String?): String?`
`fun findTextAll(text: String?, pattern: String?): Array<String>`
`fun findText(text: String?, pattern: String?): String?`.
`StringUtils` и `IStringUtils` были удалены за ненадобностью.
Из `TextUtils` и `ITextUtils` были удалены:
`fun tokens(input: String): List<String>`
`fun stringToInteger(text: String): Int`
`fun stringToDouble(text: String): Double`
`fun stringToBoolean(text: String): Boolean`.
Из `ITree` и `Tree` были удалены:
`fun getSortType(): Boolean`
Удалены все не использующиеся интерфейсы, классы, и методы.
Интерфейсы хорошо разделены, что гарантирует, что реализующим классам не придется зависеть от
методов, которые они не используют.


                           Принцип инверсии зависимостей.
Все классы зависят от интерфейсов, имеют свои интерфейсы и вызываются через интерфейсы кроме класса `Start` он предназначен для запуска. 
 









