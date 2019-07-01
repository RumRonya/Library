<%--
  Created by IntelliJ IDEA.
  User: MSI
  Date: 05.06.2019
  Time: 12:20
  To change this template use File | Settings | File Templates.
--%>
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
        <article class="big_book">

            <h1 class="name_book_big">
                ${bookFromServer.nameBook}
            </h1>

            <figure class="big_figure">
                <img class="big_poster" src="${bookFromServer.URLPoster}"/>
            </figure>

            <article class="info_book">
               <p class="read"><a href="read?book=${bookFromServer.idBook}&page=1">
                читать онлайн
               </a></p>

                <p><span class="info_of_book">Автор: </span>
                    <a class="a_in" href="/author?id=${bookFromServer.author.idAuthor}">
                        ${bookFromServer.author.nameAuthor}
                    </a>
                </p>

                <p><span class="info_of_book">Серия: </span>
                    <a class="a_in" href="#">
                        ГП
                    </a>
                </p>

                <p><span class="info_of_book">Год первого издания: </span>
                    ${bookFromServer.yearBook}
                </p>

                <p><span class="info_of_book">Жанры: </span>
                    <c:forEach items="${bookFromServer.genres}" var="genre">
                        |<a href="/genre?id=${genre.id}">${genre.nameGenre}</a>|
                    </c:forEach>
                </p>

                <p><span class="info_of_book">Описание: </span>${bookFromServer.summary}</p>

                <p>

                <a class="download" href="#" download>
                txt
                </a>
                </p>

            </article>


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
