# Модифицирующие формы

## Ссылки

* [Методы для работы с контекстом](https://javalin.io/documentation#context)
* [Подключение стилей Bootstrap](https://getbootstrap.com/docs/5.1/getting-started/introduction/#css)
* [Класс StringUtils из библиотеки Apache Commons](https://commons.apache.org/proper/commons-lang/apidocs/org/apache/commons/lang3/StringUtils.html#startsWithIgnoreCase-java.lang.CharSequence-java.lang.CharSequence-)

## src/main/java/exercise/App.java

Реализуйте обработчики для маршрутов, которые необходимы для создания нового пользователя:

* GET */users/build* — отображает страницу с формой, которую заполняет пользователь

* POST */users* — обрабатывает данные формы. Нормализуйте данные, которые ввел пользователь, прежде, чем их сохранить в репозиторий. Имя и фамилию пользователя нужно [капитализировать](https://en.wikipedia.org/wiki/Capitalization), email привести к нижнему регистру и удалить концевые пробелы. В целях безопасности пароль пользователя должен храниться в репозитории в зашифрованном виде, поэтому перед сохранением нужно зашифровать его. Используйте для этого уже созданный метод `Security.encrypt()`

## src/main/jte/users/build.jte

## Задачи

Реализуйте шаблон для формы создания нового пользователя

### Подсказки

* Для работы с данными используйте методы класса `UserRepository`
* Если хотите сделать красивый вывод в шаблонах, используйте классы Bootstrap
* Присмотритесь к методам класса `StringUtils`. Возможно, какой-то из них вам понадобится
