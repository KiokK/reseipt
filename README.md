# task5-Reflection
1. Создана реализация кэша, используя алгоритмы LRU и LFU.
2. Алгоритм и максимальный размер коллекции читаються из файла resources/application.yml.
3. Инициализация бинов обработчтков кэша прописана в ___[CacheConfig.java](src/main/java/by/kihtenkoolga/config/CacheConfig.java)___. Коллекция инициализируется 
через фабрику от абстрактного класса ___[CacheAspect<K, V>](src/main/java/by/kihtenkoolga/cache/proxy/CacheAspect.java)___.
4. Код содержит javadoc и описанный README.md.
5. Кеши покрыты тестами.
6. Создана entity - ___[User](src/main/java/by/kihtenkoolga/model/User.java)___, в нем есть поле id и еще 4 поля.
      Добавить поле, проверяемое regex.
7. Созданы слои service и dao для ___User___ (service вызывает слой dao, слой dao - временная замена database). 
В сервисе CRUD операции для работы с entity. Работа через интерфейсы ___[UserServiceImpl](src/main/java/by/kihtenkoolga/service/impl/UserServiceImpl.java)___.
8. Результат работы service синхронизируется с кешем через proxy (кастомные аннотации и ___[UserCacheAspect<Long, User>](src/main/java/by/kihtenkoolga/cache/proxy/UserCacheAspect.java)___). 
При работе с entity оперируем id. Алгоритм работы с кешем:
      - GET - `@GetCache` - ищем в кеше и если там данных нет, то достаем объект из dao, сохраняем в кеш и возвращаем
      - POST - `@PostCache` - сохраняем в dao и потом сохраняем в кеше
      - DELETE - `@DeleteCache` - удаляем из dao и потом удаляем в кеша
      - PUT - `@PutCache` - обновление/вставка в dao и потом обновление/вставка в кеше.
   
**. Реализован контроллер ___[ControllerWriteToXML.java](src/main/java/by/kihtenkoolga/controller/ControllerWriteToXML.java)___ 
для получения информации в формате .xml класса ___[CashRegister](src/main/java/by/kihtenkoolga/builder/CashRegister.java)___,
который аннотирован `@XmlRootElement`, содержащего расчитанной информации для вывода чека и саму строку чека.

## Receipt
## Technologies
* Java 11
* Spring Boot
* Spring Data JPA
* PostgreSQL
* Spring-Boot-Test
* JUnit5
* Mockito
* Lombok

### Сборка и запуск
Собрать испольняемый `jar` файл с помощью `gradle-wrapper`
```
./gradlew build
```

Собрать запустить из консоли испольняемый `jar` с аргументами
```
java -jar build/libs/cashRegister_kiok-1.0-SNAPSHOT.jar 1-4 2-3 3-2 2-1 5-2 4-1 1-3 card-1111
```

### Endpoints

__GET запрос из строки браузера__

1. Вывод чека в браузере
   >- http://localhost:8081/check?itemId=4&itemId=1&itemId=3&card=1111
   >- http://localhost:8081/check?itemId=4&itemId=2&itemId=2&itemId=1&itemId=3itemId=5&card=2222
2. Вывод чека в файл check.xml
   >- http://localhost:8081/check/xml?itemId=4&itemId=1&itemId=3&card=1111

__Запрос из консоли (данные из файла)__

1. Вывод чека в файл вида: OUTPUT<input.txt>
>```
>java -jar build/libs/cashRegister_kiok-1.0-SNAPSHOT.jar input.txt
>```
2. Вывод чека в файл, указанный во 2 аргументе
> ```
> java -jar build/libs/cashRegister_kiok-1.0-SNAPSHOT.jar input.txt output.txt
>  ```