<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>Электронная библиотека: главная</title>
    <link rel="shortcut icon" href="/img/logo_ico.ico" type="image/x-icon">
    <link href="/css/style.css" rel="stylesheet" type="text/css">
    <link href="/css/reset.css" rel="stylesheet" type="text/css">
</head>
<body>
<header>
    <a href="/index">
        <figure id="for_logo">
            <img id="logo_img" src="/img/logo.png" width="100"/>
            <figcaption>
                <h2 id="library">Электронная библиотека</h2>
                <h1 id="name_library">Библчервь</h1>
            </figcaption>
        </figure>
    </a>



    <nav id="main">

        <img id="humb" src="/img/humb.png" onclick="humb_open(this)">

        <ul>
            <li class="main_nav"><a href="/about">О нас</a></li>
            <li class="main_nav"><a href="/books">Книги</a></li>
            <li class="main_nav"><a href="/authors">Авторы</a></li>
            <li class="main_nav" id="for_humb"><a href="genres.php">Жанры</a></li>
            <li class="main_nav"><a href="/serias">Серии</a></li>
            <li class="main_nav"><a href="faq.php">FAQ</a></li>
        </ul>

        <form method="post" id="search">
            <input id="search_line" type="search" name="search" size="30"/>
            <input type="submit" name="submit_search" value="Поиск"/>
        </form>

        <p id='for_line'></p>
    </nav>

</header>


<article id="content">

    <nav id="nav_genre">
        <ul>
            <c:forEach items="${genresFromServer}" var="genre">
            <li>
                <a href="/genre?id=${genre.id}">${genre.nameGenre}</a>
            </li>
            </c:forEach>
        </ul>
    </nav>

    <article id="right_content">
        <h3 class="three_book_main">Самые популярные книги</h3>
        <article class="books">
            <c:forEach items="${booksPopularFromServer}" var="book">
                <article class="one_book">
                    <figure class="poster">
                        <img src="${book.URLPoster}"/>
                    </figure>

                    <article class="info_book">
                        <h3 class="name_book_h">
                            <a class="name_book" href="/book?id=${book.idBook}">
                                    ${book.nameBook}
                            </a>
                        </h3>

                        <p><span class="info_of_book">Автор:</span>
                            <a class="a_in" href="/author?id=${book.author.idAuthor}">
                                    ${book.author.nameAuthor}
                            </a>
                        </p>

                        <p><span class="info_of_book">Серия:</span>
                            <a class="a_in" href="#">
                                ГП
                            </a>
                        </p>

                        <p><span class="info_of_book">Год первого издания:</span>
                                ${book.yearBook}
                        </p>

                        <p><span class="info_of_book">Жанры: </span>
                            <c:forEach items="${book.genres}" var="genre">
                                |<a href="/genre?id=${genre.id}">${genre.nameGenre}</a>|
                            </c:forEach>
                        </p>

                        <p><span class="info_of_book">Описание:</span>
                                ${book.summary}
                        </p>
                    </article>
                </article>
            </c:forEach>
        </article>
        <h3 class="three_book_main">Недавно добавленые книги</h3>
        <article class="books">
            <c:forEach items="${booksAddFromServer}" var="book">
                <article class="one_book">
                    <figure class="poster">
                        <img src="${book.URLPoster}"/>
                    </figure>

                    <article class="info_book">
                        <h3 class="name_book_h">
                            <a class="name_book" href="/book?id=${book.idBook}">
                                    ${book.nameBook}
                            </a>
                        </h3>

                        <p><span class="info_of_book">Автор:</span>
                            <a class="a_in" href="/author?id=${book.author.idAuthor}">
                                    ${book.author.nameAuthor}
                            </a>
                        </p>

                        <p><span class="info_of_book">Серия:</span>
                            <a class="a_in" href="#">
                                ГП
                            </a>
                        </p>

                        <p><span class="info_of_book">Год первого издания:</span>
                                ${book.yearBook}
                        </p>

                        <p><span class="info_of_book">Жанры: </span>
                            <c:forEach items="${book.genres}" var="genre">
                                |<a href="/genre?id=${genre.id}">${genre.nameGenre}</a>|
                            </c:forEach>
                        </p>

                        <p><span class="info_of_book">Описание:</span>
                                ${book.summary}
                        </p>
                    </article>
                </article>
            </c:forEach>
        </article>
        <h3 class="three_book_main">Новые книги</h3>
        <article class="books">
            <c:forEach items="${booksNewFromServer}" var="book">
                <article class="one_book">
                    <figure class="poster">
                        <img src="${book.URLPoster}"/>
                    </figure>

                    <article class="info_book">
                        <h3 class="name_book_h">
                            <a class="name_book" href="/book?id=${book.idBook}">
                                    ${book.nameBook}
                            </a>
                        </h3>

                        <p><span class="info_of_book">Автор:</span>
                            <a class="a_in" href="/author?id=${book.author.idAuthor}">
                                    ${book.author.nameAuthor}
                            </a>
                        </p>

                        <p><span class="info_of_book">Серия:</span>
                            <a class="a_in" href="#">
                                ГП
                            </a>
                        </p>

                        <p><span class="info_of_book">Год первого издания:</span>
                                ${book.yearBook}
                        </p>

                        <p><span class="info_of_book">Жанры: </span>
                            <c:forEach items="${book.genres}" var="genre">
                                |<a href="/genre?id=${genre.id}">${genre.nameGenre}</a>|
                            </c:forEach>
                        </p>

                        <p><span class="info_of_book">Описание:</span>
                                ${book.summary}
                        </p>
                    </article>
                </article>
            </c:forEach>
        </article>
    </article>

</article>
    <footer>
    <section id="developer">
        <p>Дизайн</p>
        <p>Кузьмич Вероника</p>
    </section>

    <section id="vlad">
        <p>Электроная библиотека</p>
        <p>"Библчервь"</p>
    </section>
</footer>
</body>
</html>